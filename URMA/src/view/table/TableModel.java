package view.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import app.App;
import controler.handlers.IHandler;
import model.Attribute;
import model.Table;
import model.resourceFactory.IResourceFactory;

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

	/**
	 * @return the columnsCode
	 */
	public List<String> getColumnsCode() {
		return columnsCode;
	}

	/**
	 * Model tabele po <code> JTable </code> formatu. <br>
	 * Nasledjuje <code> DefaultTableModel </code>
	 * @param table
	 */
	public TableModel(Table table) {
		this.table = table;
		
		this.table.setTitle(table.getTitle());
		//this.table.setTitle("Naslov tabele");
		

		
		
//		this.table.addAttributes(new Attribute("title1", "code1", true, true, "string", 0, this.table));
//		this.table.addAttributes(new Attribute("title2", "code2", false, true, "string", 0, this.table));
//		this.table.addAttributes(new Attribute("title3", "code3", false, true, "string", 0, this.table));
//		this.table.addAttributes(new Attribute("title4", "code4", true, true, "string", 0, this.table));
//		
		setUpColumns(this.table);
		//setUpData();
	}
	
	public void setUpColumns(Table table) {
		Map<String, Attribute> attributes = table.getAttributes();
		for (String attributeKey : attributes.keySet()) {
			Attribute attribute = attributes.get(attributeKey);
			this.addColumn(attribute.getTitle());
			this.columnsCode.add(attributeKey);
		}
	}
	
	
	//TODO promeniti svrhu funkcije
	/**
	 * @author Dusan
	 * Prvobitno za test sluzi da se poziva prilikom klika na njega u stablu ili ako je dete, LAZY FETCH.
	 */	
	public void setUpData() {

		App.INSTANCE.setFactory("db");
		IResourceFactory factory = App.INSTANCE.getFactory();
		IHandler handler = factory.createHandler();
		
		try {
			Vector<Vector<Object>> valueList = handler.read(table);
			
			for (int i = 0; i < valueList.size(); i++) {
				this.addRow(valueList.get(i));
			}
		}catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "Wrong file", "Invalid scheme", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
	}

	
	
	/**
	 * (non-Javadoc)
	 * 
	 * 
	 * Provera da li je strani kljuc ili primarni
	 **/
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		//String columnName = this.columnsCode.get(columnIndex);  
		//Attribute attribute = this.table.getAttributes().get(columnName);
		
		
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
