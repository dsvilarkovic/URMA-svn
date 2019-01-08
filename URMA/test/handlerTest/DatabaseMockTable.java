package handlerTest;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Attribute;
import model.Table;

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
}
