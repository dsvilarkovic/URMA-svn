package handlerTest;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Vector;

import model.Table;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import view.fieldFactory.CharField;
import view.fieldFactory.DoubleField;
import view.fieldFactory.IntegerField;
import controler.handlers.DBHandler;
import controler.handlers.IHandler;

public class UpdateRowDBTest {
	
	private static Connection conn = null;	
	private static Table table = null;
	private static IHandler iHandler = null;
	@Before
	public void setUpBefore() {
		Locale.setDefault(new Locale("en", "UK"));
		
		//konektuj se na bazu i kreiraj mock tabelu
		try {
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1","psw-2018-tim7-1","tim7-19940718");
			DatabaseMockTable.createDatabaseTable(conn);
			//conn.close();
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
	public void tearDownAfter() {
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
	 * Azuriraj tabelu nekom non-null vrendoscu, npr MOCK_INT dodaj da prikazuje 100
	 */
	public void testPositiveUpdate() {
		//isti row koji je prethodno dodat
		HashMap<String, Object> rowMap = DatabaseMockTable.getRowMap();
		//izmeni ga
		((IntegerField)rowMap.get("MOCK_INT")).setValue(100);
		
		
		try {
			iHandler.update(table, rowMap);
		}
		catch(NullPointerException npe) {
			
		}
		
		//proveri da li novoazuirrana torka postoji
		
		//procitaj celu tabelu
		Vector<Vector<Object>> valueList = iHandler.read(table);
		
		

				
		boolean rowNotExists = DatabaseMockTable.rowNotExistsInDatabaseTable(valueList, rowMap);
		
		assertEquals(false, rowNotExists);		
		
	}
	
	@Test 
	/**
	 * Testiranje ubacivanja null vrednosti na mesto koje sme, npr MOCK_DOUBLE
	 */
	public void testPositiveNullUpdate() {
		//isti row koji je prethodno dodat
		HashMap<String, Object> rowMap = DatabaseMockTable.getRowMap();
		//izmeni ga
		((DoubleField)rowMap.get("MOCK_DOUBLE")).setValue(null);
		
		
		
		try {
			iHandler.update(table, rowMap);
		}
		catch(NullPointerException npe) {
			//zbog grafickog prikaza 
		}
				
		//procitaj celu tabelu iz baze
		Vector<Vector<Object>> valueList = iHandler.read(table);
				
		//proveri da li postoji u bazi
		boolean rowNotExists = DatabaseMockTable.rowNotExistsInDatabaseTable(valueList, rowMap);
		assertEquals(false, rowNotExists);		
		
	}
	
	@Test
	/**
	 * Azuriranje trojke promenom na null obelezja koje zabranjuje null vrednost,
	 * tj kljuca
	 */
	public void testNegativeNullKeyUpdate() {
		//isti row koji je prethodno dodat
		HashMap<String, Object> rowMap = DatabaseMockTable.getRowMap();
		//izmeni ga
		((CharField)rowMap.get("MOCK_CHAR")).setValue(null);
		
		
		
		try {
			
			boolean result = iHandler.update(table, rowMap);
			System.out.println("Rezultat je: " + result);
		}
		catch(NullPointerException npe) {
			//zbog grafickog prikaza 
		}
				
		//procitaj celu tabelu iz baze
		Vector<Vector<Object>> valueList = iHandler.read(table);
				
		//proveri da li postoji u bazi
		boolean rowNotExists = DatabaseMockTable.rowNotExistsInDatabaseTable(valueList, rowMap);
		assertEquals(true, rowNotExists);		
			
	}
	
	@Test
	public void testNegativeNullUpdate() {
		//isti row koji je prethodno dodat
		HashMap<String, Object> rowMap = DatabaseMockTable.getRowMap();
		//izmeni ga
		((IntegerField)rowMap.get("MOCK_INT")).setValue(null);
		
		
		
		try {
			iHandler.update(table, rowMap);
		}
		catch(NullPointerException npe) {
			//zbog grafickog prikaza 
		}
		catch(MissingResourceException mre) {
			//zbog kljuca, jer se ne uzdamo u validaciju
		}
				
		//procitaj celu tabelu iz baze
		Vector<Vector<Object>> valueList = iHandler.read(table);
				
		//proveri da li postoji u bazi
		boolean rowNotExists = DatabaseMockTable.rowNotExistsInDatabaseTable(valueList, rowMap);
		assertEquals(true, rowNotExists);	
	}

}
