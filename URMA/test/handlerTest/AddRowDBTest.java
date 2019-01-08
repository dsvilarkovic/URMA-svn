package handlerTest;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controler.handlers.DBHandler;
import controler.handlers.IHandler;
import model.Table;
import view.fieldFactory.CharField;
import view.fieldFactory.DoubleField;

public class AddRowDBTest {

	private static Connection conn = null;	
	
	
	@Before
	public void setUpBefore() throws Exception {
		//konektuj se na bazu i kreiraj mock tabelu
		try {
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1","psw-2018-tim7-1","tim7-19940718");
			DatabaseMockTable.createDatabaseTable(conn);
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		
	}
	
	@After
	public void tearDownAfter() throws Exception {
		try {
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1","psw-2018-tim7-1","tim7-19940718");
			DatabaseMockTable.dropDatabaseTable(conn);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	@Test
	/**
	 * Testiranje, pozitivnog, tacnog unosa
	 */
	public void testPositiveAdd() {
		Table table = DatabaseMockTable.createMockTable();
		
		
		//napravi podatke
		HashMap<String, Object> rowMap = DatabaseMockTable.getRowMap();
		
		IHandler iHandler = new DBHandler();
		//ubaci torku u tabelu
		
		try {
			iHandler.create(table, rowMap);
		}
		catch(NullPointerException npe) {
			//izuzetak za graficki prikaz
		}
		
		//procitaj celu tabelu iz baze
		Vector<Vector<Object>> valueList = iHandler.read(table);
		
	
				
		boolean rowNotExists = true;
		for (int i = 0; i < valueList.size(); i++) {
			Vector<Object> row = valueList.get(i);
			if(DatabaseMockTable.isThatRow(row, rowMap) == true){
				rowNotExists = false;
				break;
			}
		}
		
		assertEquals(false, rowNotExists);	
	}
	
	@Test
	/**
	 * Testiranje pozitivnog ulaza, tj kad se prosledi null vrednost za neki od dozvoljenih
	 */
	public void testPositiveNullAdd() {
		Table table = DatabaseMockTable.createMockTable();
		
		
		//napravi podatke
		HashMap<String, Object> rowMap = DatabaseMockTable.getRowMap();
		
		/**
		 * Podesavanje null vrednosti za MOCK_DOUBLE, za koji je dozvoljeno da se podesi
		 */
	
		((DoubleField)rowMap.get("MOCK_DOUBLE")).setValue(null);
		
		
		IHandler iHandler = new DBHandler();
		
		
		
		//ubaci torku u tabelu u bazis		
		try {
			iHandler.create(table, rowMap);
		}
		catch(NullPointerException npe) {
			//izuzetak za graficki prikaz
		}
		//procitaj celu tabelu iz abze
		Vector<Vector<Object>> valueList = iHandler.read(table);
		
		
	
				
		boolean rowNotExists = true;
		for (int i = 0; i < valueList.size(); i++) {
			Vector<Object> row = valueList.get(i);
			if(DatabaseMockTable.isThatRow(row, rowMap) == true){
				rowNotExists = false;
				break;
			}
		}
		
		assertEquals(false, rowNotExists);			
	}
	
	@Test
	/**
	 * Testiranje negativnog ulaza, tj kad se prosledi null vrednost za kljuc
	 */
	public void testNegativeAdd() {
		Table table = DatabaseMockTable.createMockTable();
		
		
		//napravi podatke
		HashMap<String, Object> rowMap = DatabaseMockTable.getRowMap();
		
		/**
		 * Podesavanje null vrednosti za MOCK_DOUBLE, za koji je dozvoljeno da se podesi
		 */
		((CharField)rowMap.get("MOCK_CHAR")).setValue(null);
		
		IHandler iHandler = new DBHandler();
		//ubaci torku u tabelu u bazu
		
		try {
			iHandler.create(table, rowMap);
		}
		catch(NullPointerException npe) {
			//izuzetak za graficki prikaz
		}
		catch(MissingResourceException mre) {
			//izuzetak za resource bundle
		}
		
		//procitaj celu tabelu iz baze
		Vector<Vector<Object>> valueList = iHandler.read(table);
		
		
		
		boolean rowNotExists = true;
		for (int i = 0; i < valueList.size(); i++) {
			Vector<Object> row = valueList.get(i);
			if(DatabaseMockTable.isThatRow(row, rowMap) == true){
				rowNotExists = false;
				break;
			}
		}
		
		assertEquals(true, rowNotExists);	
	}
	

}