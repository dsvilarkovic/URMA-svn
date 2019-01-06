package view.table;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import app.App;
import controler.handlers.IHandler;
import model.Attribute;
import model.Table;
import model.TitleLanguagePack;
import model.resourceFactory.IResourceFactory;
import view.localizationManager.LocalizationManager;
import view.localizationManager.LocalizationObserver;

/**
 * Tabela modela koji cuva podatke za tabelu. Koristi <code>DefaultTableModel</code>
 * @author Dusan
 *
 */
public class TableModel extends DefaultTableModel implements LocalizationObserver {
	private static final long serialVersionUID = -5330690347427736920L;
	public static final String reservedNullValue = "(NULL)";
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
	
	/**
	 * Sluzi za inicijalno podesavanje imena kolona koje ce stojati za ovu tabelu
	 * @param table - tabela tipa {@link Table} iz koje ce izvlaciti tipovi atributa iz {@link Attribute}
	 */
	public void setUpColumns(Table table) {
		Map<String, Attribute> attributes = table.getAttributes();
		for (String attributeKey : attributes.keySet()) {
			Attribute attribute = attributes.get(attributeKey);
			TitleLanguagePack titleLanguagePack = App.INSTANCE.getTitleLanguagePack();
			String localizedTitle = titleLanguagePack.getAttributeTitle(attributeKey, table.getCode());
			this.addColumn(attribute.getTitle());
			this.columnsCode.add(attributeKey);
		}
	}
	
	
	/**
	 * @author Dusan
	 * Prvobitno za test sluzi da se poziva prilikom klika na njega u stablu ili ako je dete, LAZY FETCH.
	 */	
	public void setUpData() {

		App.INSTANCE.setFactory("db");
		IResourceFactory factory = App.INSTANCE.getFactory();
		IHandler handler = factory.createHandler();
		LocalizationManager.nulifyFormats();
		
		
		try {
			Vector<Vector<Object>> valueList = handler.read(table);
			//idi po redovima
			for (int i = 0; i < valueList.size(); i++) {
				//sad idi po vrednostima 
				Vector<Object> row = valueList.get(i);		
				row = formatNullValues(row);
				this.addRow(row);
			}
		}catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "Wrong file", "Invalid scheme", JOptionPane.ERROR_MESSAGE);
			
			//return;
		}
		
		
		updateLanguage();
	}
	
	/**
	 * Funkcija namenjenja da zameni podatke u tabeli sa onim sto vrati search
	 * @author filip
	 * @param valueList - vektor redova podataka posle search operacije
	 * @return {@link Void}
	 */	
	public void searchUpdate(Vector<Vector<Object>> valueList) {
		LocalizationManager.nulifyFormats();
		this.setRowCount(0);
		for (int i = 0; i < valueList.size(); i++) {
			Vector<Object> row = valueList.get(i);		
			row = formatNullValues(row);
			this.addRow(row);
		}
		// TODO Dusan od Filipa
		updateLanguage();
	}
	
	/**
	 * Pomocna funkcija namenjena za vodjenje racuna o null vrednostima
	 * @param row
	 * @return
	 */
	private Vector<Object> formatNullValues(Vector<Object> row){
		for (int i = 0; i < row.size(); i++) {
			if(row.get(i) == null) {
				row.set(i, TableModel.reservedNullValue);
			}
		}
		
		return row;
	}
	
	
	


	/**
	 * (non-Javadoc)
	 * 
	 * 
	 * Provera da li je strani kljuc ili primarni
	 **/
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {		
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

	@Override
	public void updateLanguage() {
		//readData();
		//idi kroz sve kolone koje su tipa date i number
		Map<String, Attribute> attributes = table.getAttributes();
		for (int i = 0; i < this.getColumnCount(); i++) {
			Attribute attribute = attributes.get(columnsCode.get(i));
			//ako je tipa datum
			if(attribute.getType().equals("date")) {
				//idi kroz celu kolonu i lokalizuj
				for (int j = 0; j < this.getRowCount(); j++) {
					String value = (String) this.getValueAt(j, i);
					value = LocalizationManager.formatDateString(value);
					this.setValueAt(value, j, i);
				}
				
			}
			//ako je tipa broj
			if(attribute.getType().equals("double") || attribute.getType().equals("int")) {
				//idi kroz celu kolonu i lokalizuj
				for (int j = 0; j < this.getRowCount(); j++) {
					String value = LocalizationManager.convertToString(this.getValueAt(j, i));
					value = LocalizationManager.formatNumberString(value);
					this.setValueAt(value, j, i);
				}
			}
		}
		
		
		
		setLocalizedIdentifiers();
	}	
	
	/**
	 * Podesavanje lokalizovanih vrednosti atributa tabele.
	 * @author Dusan
	 */
	private void setLocalizedIdentifiers() {
		List<String> newIdentifiersList = new ArrayList<>();
		TitleLanguagePack titleLanguagePack = App.INSTANCE.getTitleLanguagePack();
		for (int i = 0; i < this.getColumnCount(); i++) {
			String attributeCode = columnsCode.get(i);
			String localizedTitle = titleLanguagePack.getAttributeTitle(attributeCode, table.getCode());
			newIdentifiersList.add(localizedTitle);
		}
		this.setColumnIdentifiers(newIdentifiersList.toArray());
	}
	
	
	/**
	 * Za debagovnje sluzi, iscitava vrednosti tabele tableModel-a
	 */
	public void readData() {
		System.out.println("___Pocetak pre udpdate____");
		for (int i = 0; i < getRowCount(); i++) {
			for (int j = 0; j < getColumnCount(); j++) {
				System.out.print(this.getValueAt(i, j) + "|\t");
			}
			System.out.println();
		}
		System.out.println("___Kraj___________________");
	}
}
