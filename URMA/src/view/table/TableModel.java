package view.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import model.Attribute;
import model.Table;

/**
 * Tabela modela koji cuva podatke za tabelu. Koristi <code>DefaultTableModel</code>
 * @author Dusan
 *
 */
public class TableModel extends DefaultTableModel {
	private static final long serialVersionUID = -5330690347427736920L;

	private Table table;
	
	/**
	 * Sluzi za cuvanje kodova atributa, za laksi pristup
	 */
	private List<String> columnsCode = new ArrayList<>();


	//TODO namestiti da se komunicira preko dogadjaja
	public TableModel(Table table) {
		this.table = new Table();
		this.table.setTitle("Naslov tabele");
		

		
		
		this.table.addAttributes(new Attribute("title1", "code1", true, true, "string", 0, this.table));
		this.table.addAttributes(new Attribute("title2", "code2", false, true, "string", 0, this.table));
		this.table.addAttributes(new Attribute("title3", "code3", false, true, "string", 0, this.table));
		this.table.addAttributes(new Attribute("title4", "code4", true, true, "string", 0, this.table));
		
		setUpColumns(this.table);
		setUpData();
	}
	
	public void setUpColumns(Table table) {
		Map<String, Attribute> attributes = table.getAttributes();
		for (String attributeKey : attributes.keySet()) {
			Attribute attribute = attributes.get(attributeKey);
			this.addColumn(attribute.getTitle());
			this.columnsCode.add(attributeKey);
		}
	}
	
	public void setUpData() {
		this.addRow(new Object[] { "ra1/2011", "Petar", "Petrović", true });
	}

	
	
	/**
	 * (non-Javadoc)
	 * 
	 * 
	 * Provera da li je strani kljuc ili primarni
	 **/
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		String columnName = this.columnsCode.get(columnIndex);  
		Attribute attribute = this.table.getAttributes().get(columnName);
		
		
		/*
		if(attribute.getIsPrimaryKey()) {
			return false;
		}
		*/
		
		return false;
	}

	/**
	 * @return the table
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(Table table) {
		this.table = table;
	}

	
	
}
