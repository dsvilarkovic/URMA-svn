package handlerTest;

import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.spy;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import model.Attribute;
import model.Table;
import view.fieldFactory.BooleanField;
import view.fieldFactory.BooleanFieldFactory;
import view.fieldFactory.CharField;
import view.fieldFactory.CharFieldFactory;
import view.fieldFactory.DateField;
import view.fieldFactory.DateFieldFactory;
import view.fieldFactory.DoubleField;
import view.fieldFactory.DoubleFieldFactory;
import view.fieldFactory.IField;
import view.fieldFactory.IntegerField;
import view.fieldFactory.IntegerFieldFactory;
import view.fieldFactory.VarcharField;
import view.fieldFactory.VarcharFieldFactory;

/**
 * Klasa koja sluzi za kreiranje tabele u bazi nad kojom ce se proveravati rad  <br>
 * Kljuc mock tabele je MOCK_CHAR atribut.
 * @author Dusan
 *
 */
public class DatabaseMockTable {
	
	
	private static String mockTableName = "MOCK_TABLE";
	private static String createMockTableStatement = "CREATE TABLE \""+ mockTableName +"\" (\r\n" + 
			"	\"MOCK_CHAR\" CHAR(3) NOT NULL,\r\n" + 
			"	\"MOCK_VARCHAR\" VARCHAR(128) NULL DEFAULT NULL,\r\n" + 
			"	\"MOCK_INT\" INT NOT NULL,\r\n" + 
			"	\"MOCK_DOUBLE\" FLOAT(53) NULL DEFAULT NULL,\r\n" + 
			"	\"MOCK_BOOLEAN\" BIT NULL DEFAULT NULL,\r\n" + 
			"	\"MOCK_DATE\" DATE NULL DEFAULT NULL,\r\n" + 
			"	PRIMARY KEY (\"MOCK_CHAR\")\r\n" + 
			")\r\n" + 
			";";
	private static String dropMockTableStatement = "DROP TABLE "+mockTableName+";";
	
	public DatabaseMockTable() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Klasa koja kreira tabelu tipa {@code model.Table} sa definisanim atributima iz baze
	 * @return
	 */
	static Table createMockTable() {
		Table table = new Table();
		
		table.setCode("MOCK_TABLE");
		table.setTitle("MOCK_TABLE");
		
		//Attribute attribute_char = new Attribute("Mock Char","MOCK_CHAR", true, true, "char",
		Attribute mockChar = new Attribute("MOCK_CHAR", "MOCK_CHAR", true, true, "char", 3, table);
		Attribute mockVarchar = new Attribute("MOCK_VARCHAR", "MOCK_VARCHAR", false, false, "varchar", 128, table);
		Attribute mockInt = new Attribute("MOCK_INT", "MOCK_INT", false, true, "int", 10, table);
		Attribute mockDouble = new Attribute("MOCK_DOUBLE", "MOCK_DOUBLE", false, false, "double", 53, table);
		Attribute mockBoolean = new Attribute("MOCK_BOOLEAN", "MOCK_BOOLEAN", false, false, "boolean", 1, table);
		Attribute mockDate = new Attribute("MOCK_DATE", "MOCK_DATE", false, false, "date", 50, table);
		
		mockDouble.setPrecision(2);
		
		table.addAttributes(mockChar);
		table.addAttributes(mockVarchar);
		table.addAttributes(mockInt);
		table.addAttributes(mockDouble);
		table.addAttributes(mockBoolean);
		table.addAttributes(mockDate);
		
		
		return table;
	}
	
	
	/**
	 * Metoda koja kreira tabelu u bazi podataka.
	 * @param conn -konekcija na kojoj ce se kreirati tabela u bazi podataka
	 */
	static void createDatabaseTable(Connection conn) {
		PreparedStatement preparedStatement;
		
		//kreiraj tabelu
		try {
			preparedStatement = conn.prepareStatement(createMockTableStatement);
			preparedStatement.execute();
			preparedStatement.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Metoda koja unistava tabelu u bazi podataka.
	 * @param conn - konekcija na kojoj ce se unistiti tabela u bazi podataka
	 */
	static void dropDatabaseTable(Connection conn) {
		PreparedStatement preparedStatement;
		//obrisi tabelu
		try {
			preparedStatement = conn.prepareStatement(dropMockTableStatement);
			preparedStatement.execute();
			preparedStatement.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Provera da li u mock tabeli u bazi postoji mock tabela
	 * @param conn - konekcija na kojoj ce se proveravati da li mock tabela postoji  u bazi podataka
	 * @return false ako ne postoji , true ako postoji 
	 */
	static boolean mockTableExists(Connection conn) {
		boolean tableExists = false;
		try {
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, mockTableName, null);			
			while(tables.next()) {
				String tName = tables.getString("TABLE_NAME");
	            if (tName != null && tName.equals(mockTableName)) {
	                tableExists = true;
	                //ako nadjes, puca petlja
	                break;
	            }
			}
			
			tables.close();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return tableExists;
	}
	
	static List<String> getMockColumnHeaders(Connection conn){
		String findColumnsQuery = "SELECT column_name\r\n" + 
				"FROM information_schema.columns\r\n" + 
				"WHERE table_schema = 'dbo' \r\n" + 
				"AND table_name = 'MOCK_TABLE';";
		
		List<String> columnCodes = new ArrayList<>();
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(findColumnsQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String columnCode = resultSet.getString(1);
				columnCodes.add(columnCode);
			}		
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return columnCodes;
	}
	
	
	/**
	 * Pravljenje torke na osnovu fiktivne forme
	 * @return
	 */
	static HashMap<String, Object> getRowMap(){
		HashMap<String, Object> rowMap = new HashMap<>();
		//ispuni podacima		
		CharField charField = (CharField) new CharFieldFactory().createField();
		charField.setValue("xyz");
		VarcharField varcharField = (VarcharField) new VarcharFieldFactory().createField();
		varcharField.setValue("mock_varchar");
		IntegerField integerField = (IntegerField) new IntegerFieldFactory().createField();
		integerField.setValue((Integer) 1);
		DoubleField doubleField = (DoubleField) new DoubleFieldFactory().createField();
		DoubleField copyDoubleField = spy(doubleField);
		doReturn(",").when(copyDoubleField).splitChar();
		copyDoubleField.setValue("125.25");
		BooleanField booleanField = (BooleanField) new BooleanFieldFactory().createField();
		booleanField.setValue((Boolean)true); 
		DateField dateField = (DateField) new DateFieldFactory().createField();
				
		rowMap.put("MOCK_CHAR", charField);
		rowMap.put("MOCK_VARCHAR", varcharField);
		rowMap.put("MOCK_INT", integerField);
		rowMap.put("MOCK_DOUBLE", copyDoubleField);
		rowMap.put("MOCK_BOOLEAN", booleanField);
		rowMap.put("MOCK_DATE", dateField);

		
		return rowMap;
	}
	
	
	/**
	 * Sluzi za proveru da li je to bas taj red
	 * @param row - red iz baze
	 * @param rowMap - red iz poslate torke za bazu
	 * @return
	 */
	static boolean isThatRow(Vector<Object> row, HashMap<String, Object> rowMap) {
		boolean isThat = true;
		List<Object> valuesNew = new ArrayList<Object>(rowMap.values());
		
//		System.out.println();
//		System.out.println("row");
//		for (int i = 0; i < row.size(); i++) {
//			System.out.print(row.get(i) + "|");
//		}
//		System.out.println();
//		System.out.println("valuesNew");
//		for (int i = 0; i < valuesNew.size(); i++) {
//			System.out.print(((IField)valuesNew.get(i)).getValue() + "|");
//		}
//		System.out.println();
		
		
		
		for (int i = 0; i < row.size(); i++) {
			Object value = ((IField)valuesNew.get(i)).getValue();
			//System.out.println(value + " "  + row.get(i));
			if(value == null && row.get(i) == null) {
				continue;
			}
			
			
			if((value == null || row.get(i) == null) ||
					value.toString().equals(row.get(i).toString()) == false) {
				
				System.out.println("Fejlovao za " + value);
				isThat = false;
				break;
			}
		}
			
		
		return isThat;
	}
	
	
	static boolean rowNotExistsInDatabaseTable(Vector<Vector<Object>> valueList,
			HashMap<String, Object> rowMap) {
		boolean rowNotExists = true;
		for (int i = 0; i < valueList.size(); i++) {
			Vector<Object> row = valueList.get(i);
			if(DatabaseMockTable.isThatRow(row, rowMap) == true){
				rowNotExists = false;
				break;
			}
		}
		
		return rowNotExists;
	}
}
