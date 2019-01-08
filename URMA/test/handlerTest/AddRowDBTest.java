package handlerTest;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.spy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Vector;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import controler.handlers.DBHandler;
import controler.handlers.IHandler;
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

public class AddRowDBTest {

	private static Connection conn = null;	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//konektuj se na bazu i kreiraj mock tabelu
		try {
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1","psw-2018-tim7-1","tim7-19940718");
			DatabaseMockTable.createDatabaseTable(conn);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		try {
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1","psw-2018-tim7-1","tim7-19940718");
			DatabaseMockTable.dropDatabaseTable(conn);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	@Test(expected=NullPointerException.class)
	/**
	 * Testiranje, pozitivnog, tacnog unosa
	 */
	public void testPositiveAdd() {
		Table table = DatabaseMockTable.createMockTable();
		
		
		//napravi podatke
		HashMap<String, Object> rowMap = getRowMap();
		
		IHandler iHandler = new DBHandler();
		//ubaci torku u tabelu
		
		//when(App.INSTANCE.getTableMediator().showTable(table)).thenReturn();
		iHandler.create(table, rowMap);
		
		//procitaj celu tabelu
		Vector<Vector<Object>> valueList = iHandler.read(table);
		
		//iscitaj kodove kolona u tabeli	
		List<String> columnCodes = DatabaseMockTable.getMockColumnHeaders(conn);// new ArrayList<>();
		
				
		boolean assertation = true;
		for (int i = 0; i < valueList.size(); i++) {
			Vector<Object> row = valueList.get(i);
			if(isThatRow(row, rowMap, columnCodes) == false){
				assertation = false;
				break;
			}
		}
		
		assertEquals(true, assertation);
	}
	
	@Test(expected=MissingResourceException.class)
	/**
	 * Testiranje pozitivnog ulaza, tj kad se prosledi null vrednost za neki od dozvoljenih
	 */
	public void testPositiveNullAdd() {
		Table table = DatabaseMockTable.createMockTable();
		
		
		//napravi podatke
		HashMap<String, Object> rowMap = getRowMap();
		
		/**
		 * Podesavanje null vrednosti za MOCK_DOUBLE, za koji je dozvoljeno da se podesi
		 */
		((DoubleField)rowMap.get("MOCK_DOUBLE")).setValue(null);
		
		IHandler iHandler = new DBHandler();
		//ubaci torku u tabelu
		
		//when(App.INSTANCE.getTableMediator().showTable(table)).thenReturn();
		iHandler.create(table, rowMap);
		
		//procitaj celu tabelu
		Vector<Vector<Object>> valueList = iHandler.read(table);
		
		
		
		//iscitaj kodove kolona u tabeli	
		List<String> columnCodes = DatabaseMockTable.getMockColumnHeaders(conn);// new ArrayList<>();
		
				
		boolean assertation = true;
		for (int i = 0; i < valueList.size(); i++) {
			Vector<Object> row = valueList.get(i);
			if(isThatRow(row, rowMap, columnCodes) == false){
				assertation = false;
				break;
			}
		}
		
		assertEquals(true, assertation);		
	}
	
	@Test(expected=MissingResourceException.class)
	/**
	 * Testiranje negativnog ulaza, tj kad se prosledi null vrednost za kljuc
	 */
	public void testNegativeAdd() {
		Table table = DatabaseMockTable.createMockTable();
		
		
		//napravi podatke
		HashMap<String, Object> rowMap = getRowMap();
		
		/**
		 * Podesavanje null vrednosti za MOCK_DOUBLE, za koji je dozvoljeno da se podesi
		 */
		((CharField)rowMap.get("MOCK_CHAR")).setValue(null);
		
		IHandler iHandler = new DBHandler();
		//ubaci torku u tabelu
		
		//when(App.INSTANCE.getTableMediator().showTable(table)).thenReturn();
		iHandler.create(table, rowMap);
		
		//procitaj celu tabelu
		Vector<Vector<Object>> valueList = iHandler.read(table);
		
		
		
		//iscitaj kodove kolona u tabeli	
		List<String> columnCodes = DatabaseMockTable.getMockColumnHeaders(conn);// new ArrayList<>();
		
				
		boolean assertation = true;
		for (int i = 0; i < valueList.size(); i++) {
			Vector<Object> row = valueList.get(i);
			if(isThatRow(row, rowMap, columnCodes) == false){
				assertation = false;
				break;
			}
		}
		
		assertEquals(false, assertation);	
	}
	
	private boolean isThatRow(Vector<Object> row, HashMap<String, Object> rowMap, List<String> columnsCode) {
		boolean isThat = true;
		
		for (int i = 0; i < row.size(); i++) {
			Object value = ((IField) rowMap.get(columnsCode.get(i))).getValue();
			System.out.println("Comparing: " + value + " and " + row.get(i));
			if(row.get(i).equals(value) == false) {
				isThat = false;
				break;
			}
		}		
		
		return isThat;
	}
	
	
	/**
	 * Pravljenje torke na osnovu fiktivne forme
	 * @return
	 */
	private HashMap<String, Object> getRowMap(){
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
		copyDoubleField.setValue("1,25");
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
	
	
	

}
