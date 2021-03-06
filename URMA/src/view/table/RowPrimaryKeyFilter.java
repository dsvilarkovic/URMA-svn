package view.table;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
		
		if(parentRowValues.size() != relation.getSourceTable().getAttributes().size()) {
			return true;
		}
		List<String> columnsCode = entry.getModel().getColumnsCode();
		
		
		Iterator<Attribute> iterSource = relation.getSourceKeys().iterator();
		Iterator<Attribute> iterDestination = relation.getDestinationKeys().iterator();
		
		
		
		while(iterSource.hasNext()) {			
			Attribute sourceAttribute = iterSource.next();
			Attribute destinationAttribute = iterDestination.next();
			
			int childValueIndex = getColumnIndex(columnsCode, destinationAttribute.getCode());
					
					
			String parentValue = parentRowValues.get(sourceAttribute.getCode());
			String childValue = (String) entry.getValue(childValueIndex);
			
			if(parentValue.equals(childValue) == false) {
				System.out.println("false");
				return false;
			}
		}
		System.out.println("true");
		return true;
	}
	
	/**
	 * Vraca indeks za ime kolone po kojoj se trazi
	 * @param columnsCode - kodovi tabele po kojoj se trazi
	 * @author Dusan
	 * @param header - naziv ciljanog atributa
	 * @return tacan indeks, ili -1 ako nije nista nasao
	 */
	private int getColumnIndex (List<String> columnsCode, String header) {
	    for (int i=0; i < columnsCode.size(); i++) {
	        if (columnsCode.get(i).equals(header)) return i;
	    }
	    return -1;
	}
	
	/**
	 * Pomocna funkcija za {link findRelation()} metodu
	 * @author Dusan
	 * @param childTableModel - tabela koja se nalazi u donjem TablePanelu
	 * @param parentTableModel - tabela koja se nalazi u gornjem TablePanel-u
	 */
	public RowPrimaryKeyFilter(TableModel childTableModel, TableModel parentTableModel){
		//nadji relaciju po kojoj se vezuju
		relation = findRelation(childTableModel, parentTableModel);
			
	}

	
	/**
	 * Sluzi za trazenje relacije po kojoj su <code>childTableModel</code> i <br>
	 *  <code>parentTableModel</code> povezani
	 * @author Dusan
	 * @param childTableModel - tabela koja se nalazi u donjem TablePanelu
	 * @param parentTableModel - tabela koja se nalazi u gornjem TablePanel-u
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
	
	/**
	 * Sluzi za podesavanje selektovane torke po maperskom principu gde je kljuc <br>
	 * naziv atributa a vrednost je ustvari vrednost atributa.
	 * @param parentRowValues - mapa vrednosti po gore navedenom principu.
	 */
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
