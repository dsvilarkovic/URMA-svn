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
		LocalizationManager.nulifyFormats();
		
		try {
			Vector<Vector<Object>> valueList = handler.read(table);
			//idi po redovima
			for (int i = 0; i < valueList.size(); i++) {
				//sad idi po vrednostima 
				Vector<Object> row = valueList.get(i);//localize(valueList.get(i));				
				this.addRow(row);
			}
		}catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "Wrong file", "Invalid scheme", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//podesi lokalizovanje datuma
		updateLanguage();
		
	}
	
	/**
	 * Funkcija namenjenja da zameni podatke u tabeli sa onim sto vrati search
	 * @author filip
	 * @param valueList - vektor redova podataka posle search operacije
	 * @return {@link Void}
	 */	
	public void searchUpdate(Vector<Vector<Object>> valueList) {
		this.setRowCount(0);
		for (int i = 0; i < valueList.size(); i++) {
			this.addRow(valueList.get(i));
		}
	}

	
//	/**
//	 * Metoda za lokalizovanje pojedinih delova vektora objekata,ukoliko je potrebno.
//	 * @param row - red koji se lokalizuje
//	 * @return
//	 */
//	private Vector<Object> localize(Vector<Object> row) {
//		
//		//za svaku vrednost torke
//		for (int i = 0; i < row.size(); i++) {
//			//proveri da li je datum
//			Object value = row.get(i);	
//			value = LocalizationManager.formatDateString(value.toString());
//			//ako nije datum vrati u value vrednost
//			if(value.equals("date_format_error")) {
//				value = row.get(i);
//			}
//			//u suprotnom je podesi i teraj dalje
//			else {
//				System.out.println(i);
//				row.set(i, value);
//				continue;
//			}
//			//proveri da li je broj
//			if(value instanceof Number) {
//				//TODO: neki kod
//			}
//						
//		}
//		
//		//podesi novi rezim za date i number format lokalizacije
//		LocalizationManager.currentDateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
//		LocalizationManager.currentNumberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
//		return row;
//	}

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
		}
		//TODO: ovde je, al treba izmeniti
		
		//LocalizationManager.currentDateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
	}

	
	
}
