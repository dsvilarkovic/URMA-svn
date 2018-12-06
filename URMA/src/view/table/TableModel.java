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

	//TODO namestiti da se komunicira preko dogadjaja
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
		//this.addRow(new Object[] { "ra1/2011", "Petar", "Petrović", true });
		
		//otvaranje konekcije
//		try {
//			Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1","psw-2018-tim7-1","tim7-19940718");
//			String tableCode = table.getCode();
//			
//			String sql = "select * from " + tableCode;
//			
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			
//			//izvrsavanje upita
//			ResultSet rset = pstmt.executeQuery();
//			
//			//provera da li ima podataka u resultset-u
//			if(!rset.isBeforeFirst()) {
//				System.out.println("Nema podataka");
//			}
//			
//			//TODO brisi redove
//			//obrisi sve redove prethodno
//			int rowCount = this.getRowCount();
//			for (int i = 0; i < rowCount ; i++) {
//				this.removeRow(i);
//			}
//			this.fireTableRowsDeleted(0, rowCount);
//			
//			//haj ho radi u rudniku trpaj
//			while(rset.next()) {
////				//citanje vrednosti po indeksu kolone
////				System.out.println(rset.getString(1));
////				//citanje vrednosti po nazivu kolone
////				System.out.println(rset.getString("DRZ_NAZIV"));
//				
//				
//				//prodji kroz sve atribute i trpaj u model redove
//				Vector<Object> valueList = new Vector<Object>();
//				for (int i = 1; i <= table.getAttributes().size(); i++) {
//					Object object = rset.getObject(i);
//					valueList.add(object);
//				}
//				
//				//ubaci red
//				this.addRow(valueList);
//			}
//			
//			rset.close();
//			pstmt.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		App.INSTANCE.setFactory("db");
		IResourceFactory factory = App.INSTANCE.getFactory();
		IHandler handler = factory.createHandler();
		
		Vector<Vector<Object>> valueList = handler.read(table	);
		
		for (int i = 0; i < valueList.size(); i++) {
			this.addRow(valueList.get(i));
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
