package view.table;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.RowFilter;

import app.App;
import model.Attribute;
import model.Relation;

public class RowPrimaryKeyFilter<M> extends RowFilter<TableModel, Integer> {

	private Relation relation = null;
	
	@Override
	public boolean include(Entry<? extends TableModel, ? extends Integer> entry) {	
		if(parentRowValues == null || parentRowValues.isEmpty())
			return true;
		List<String> columnsCode = entry.getModel().getColumnsCode();
		
		System.out.println("Kodovi kolona childa");
		for (String string : columnsCode) {
			System.out.println(string);
		}
		System.out.println("_________________");
		
		System.out.println("Kodovi kolona parenta");
		for (String string : parentRowValues.keySet()) {
			System.out.println(string);
		}
		System.out.println("_________________");
		
		Iterator<Attribute> iterSource = relation.getSourceKeys().iterator();
		Iterator<Attribute> iterDestination = relation.getDestinationKeys().iterator();
		
		
		
		while(iterSource.hasNext()) {			
			Attribute sourceAttribute = iterSource.next();
			Attribute destinationAttribute = iterDestination.next();
			
			int childValueIndex = getColumnIndex(columnsCode, destinationAttribute.getCode());
					
					
			String parentValue = parentRowValues.get(sourceAttribute.getCode());
			String childValue = (String) entry.getValue(childValueIndex);
			System.out.println("AKADOKOAKOKOKOKOOKKOOK");
			if(parentValue.equals(childValue) == false) {
				return false;
			}
		}
		
		
		return true;
	}
	
	private int getColumnIndex (List<String> columnsCode, String header) {
	    for (int i=0; i < columnsCode.size(); i++) {
	    	System.out.println(columnsCode.get(i) + ": " + header);
	        if (columnsCode.get(i).equals(header)) return i;
	    }
	    return -1;
	}
	
	public RowPrimaryKeyFilter(TableModel childTableModel, TableModel parentTableModel){
		//nadji relaciju po kojoj se vezuju
		relation = findRelation(childTableModel, parentTableModel);
		
		
		//nadji korespodentna obelezja iz obe tabele u relaciji i ubaci ih 
		//u mape Map<string,string> gde <Kod_atributa, indeks
		
	}
	/**
	 * Trazenje relacije koja povezuje destination sa child a source sa paretn
	 * @param childTableModel
	 * @param parentTableModel
	 * @return
	 */
	public Relation findRelation(TableModel childTableModel, TableModel parentTableModel) {
		
		//nadji tu relaciju
		Map<String, Relation> relations = App.INSTANCE.getModel().getRelations();
		
		//nadji relaciju gde se ove dve tabele nalaze
		for (String relationKey : relations.keySet()) {
			Relation relationValue = relations.get(relationKey);
			if(relationValue.getDestinationTable().getCode().equals(childTableModel.getTable().getCode())
					&& relationValue.getSourceTable().getCode().equals(parentTableModel.getTable().getCode())) {
				return relationValue;
			}
				
		}
		
		return null;
	}

	
	private Map<String,String> parentRowValues = null;
	public void setRowValues(Map<String, String> parentRowValues) {
		this.parentRowValues = parentRowValues;
		
	}

	/**
	 * @return the parentRowValues
	 */
	public Map<String, String> getParentRowValues() {
		return parentRowValues;
	}

	/**
	 * @param parentRowValues the parentRowValues to set
	 */
	public void setParentRowValues(Map<String, String> parentRowValues) {
		this.parentRowValues = parentRowValues;
	}
	
	

}
