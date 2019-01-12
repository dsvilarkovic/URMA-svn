package handlerTest;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controler.handlers.DBHandler;
import controler.handlers.IHandler;
import model.Table;
import view.fieldFactory.IField;
import view.fieldFactory.IntegerField;

public class DeleteRowDBTest {

	private static Connection conn = null;
	private static Table table = null;
	private static IHandler iHandler = null;
	
	@Before
	public void setUpBefore(){
		Locale.setDefault(new Locale("en", "UK"));
		
		//konektuj se na bazu i kreiraj mock tabelu
		try {
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1","psw-2018-tim7-1","tim7-19940718");
			DatabaseMockTable.createDatabaseTable(conn);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		
		table = DatabaseMockTable.createMockTable();		
		//napravi podatke
		HashMap<String, Object> rowMap = DatabaseMockTable.getRowMap();
		
		iHandler = new DBHandler();
		//ubaci torku u tabelu
		
		/**
		 * Bitno znati, ovo je dodato u tabelu prethodno
		 */
		try {
			iHandler.create(table, rowMap);
		}
		catch(NullPointerException npe) {
			//nista ne radi, ovo je zbog grafickog prikaza
		}
	}

	@After
	public void tearDownAfter(){
		Locale.setDefault(new Locale("sr", "RS"));
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
	 * Obrise se i primer stvarno ne postoji
	 */
	public void testPositiveDelete() {
		HashMap<String, Object> rowMap = DatabaseMockTable.getRowMap();
		
		//uzme se i napravi struktura pogodna za dbHandler.delete parametre
		Vector<Object> rowToDelete = new Vector<Object>();
		for (String stringKey : rowMap.keySet()) {
			Object value = ((IField)rowMap.get(stringKey)).getValue();
			rowToDelete.add(value);
		}
				
		//izvrsi delete naredbu
		try {
			iHandler.delete(table, rowToDelete);
		}catch(NullPointerException npe) {
			//zbog grafickog prikaza
		}
				
		//procitaj celu tabelu iz baze
		Vector<Vector<Object>> valueList = iHandler.read(table);
				
		//proveri se da li uopste rowMap-a sa svojim vrednostima postoji ovde		
		boolean rowNotExists = DatabaseMockTable.rowNotExistsInDatabaseTable(valueList, rowMap);
		assertEquals(true, rowNotExists);		
	}

	
	@Test 
	/**
	 * Test negativnog delete-a
	 * Podesicemo da je MOCK_INT namerno 300
	 */
	public void testNegativeDelete() {
		
		HashMap<String, Object> rowMap = DatabaseMockTable.getRowMap();
		
		//uzme se i napravi struktura pogodna za dbHandler.delete parametre
		Vector<Object> rowToDelete = new Vector<Object>();
		for (String stringKey : rowMap.keySet()) {
			Object value = ((IField)rowMap.get(stringKey)).getValue();
			if(rowMap.get(stringKey) instanceof IntegerField) {
				//test negativne vrednosti
				value = 300;
			}
			rowToDelete.add(value);
		}
				
		//izvrsi delete naredbu
		try {
			iHandler.delete(table, rowToDelete);
		}catch(NullPointerException npe) {
			//zbog grafickog prikaza
		}
				
		//procitaj celu tabelu iz baze
		Vector<Vector<Object>> valueList = iHandler.read(table);
				
		//proveri se da li uopste rowMap-a sa svojim vrednostima postoji ovde		
		boolean rowNotExists = DatabaseMockTable.rowNotExistsInDatabaseTable(valueList, rowMap);
		assertEquals(false, rowNotExists);	
	}
}
