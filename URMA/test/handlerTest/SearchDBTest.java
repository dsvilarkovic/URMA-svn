/**
 * 
 */
package handlerTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.powermock.api.mockito.PowerMockito.doNothing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import controler.handlers.DBHandler;
import controler.handlers.IHandler;
import model.Table;
import view.fieldFactory.BooleanFieldFactory;
import view.fieldFactory.CharField;
import view.fieldFactory.CharFieldFactory;
import view.fieldFactory.DateFieldFactory;
import view.fieldFactory.DecoratedField;
import view.fieldFactory.DoubleFieldFactory;
import view.fieldFactory.IField;
import view.fieldFactory.IntegerFieldFactory;
import view.fieldFactory.VarcharFieldFactory;

/**
 * @author filip
 *
 */
public class SearchDBTest {

	private static Connection conn = null;
	private static Table table = null;
	private static IHandler iHandler = null;
	private static HashMap<String, Object> fields;
//	private static HashMap<String, Object> rowMap1 = null;
//	private static HashMap<String, Object> rowMap2 = null;
//	private static HashMap<String, Object> rowMap3 = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// konektuj se na bazu i kreiraj mock tabelu
		try {
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1",
					"psw-2018-tim7-1", "tim7-19940718");
			DatabaseMockTable.createDatabaseTable(conn);
			// conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		table = DatabaseMockTable.createMockTable();
//		// napravi podatke
//		HashMap<String, Object> rowMap1 = DatabaseMockTable.getRowMap();
//		HashMap<String, Object> rowMap2 = DatabaseMockTable.getRowMap();
//		HashMap<String, Object> rowMap3 = DatabaseMockTable.getRowMap();

		iHandler = new DBHandler();
		// ubaci torku u tabelu

		/**
		 * Bitno znati, ovo je dodato u tabelu prethodno
		 * 
		 */
		try {
//			((CharField)rowMap1.get("MOCK_CHAR")).setValue("111");
//			SearchDBTest.rowMap1 = rowMap1;
////			System.out.println("Prelepa");
////			iHandler.create(table, rowMap);
//			((CharField)rowMap2.get("MOCK_CHAR")).setValue("222");
//			((VarcharField)rowMap2.get("MOCK_VARCHAR")).setValue("druga_torka");
//			((IntegerField)rowMap2.get("MOCK_INT")).setValue(200);
//			((DoubleField)rowMap2.get("MOCK_DOUBLE")).setValue(222.22);
//			((BooleanField)rowMap2.get("MOCK_BOOLEAN")).setValue(false);
//			DateField date = (DateField)rowMap2.get("MOCK_DATE");
//			((JDatePickerImpl) date.getField()).getModel().setDate(2019, 01,02);
//			SearchDBTest.rowMap2 = rowMap2;
////			System.out.println("Najlepsa");
////			iHandler.create(table, rowMap);
//			((CharField)rowMap3.get("MOCK_CHAR")).setValue("333");
//			((VarcharField)rowMap3.get("MOCK_VARCHAR")).setValue("tri");
//			((IntegerField)rowMap3.get("MOCK_INT")).setValue(300);
//			((DoubleField)rowMap3.get("MOCK_DOUBLE")).setValue(333.33);
//			((BooleanField)rowMap3.get("MOCK_BOOLEAN")).setValue(true);
//			date = (DateField)rowMap3.get("MOCK_DATE");
//			((JDatePickerImpl) date.getField()).getModel().setDate(2019, 01,03);
//			SearchDBTest.rowMap3 = rowMap3;
////			System.out.println("Jedina");
////			iHandler.create(table, rowMap);
			fields = new HashMap<String, Object>();
			DecoratedField df;

			IField[] charFild = new CharFieldFactory().createDoubleField();
			charFild[0].setValue(null);
			df = new DecoratedField(charFild[0]);
			fields.put("MOCK_CHAR", new DecoratedField[] { df, null });

			IField[] intFild = new IntegerFieldFactory().createDoubleField();
			intFild[0].setValue(null);
			df = new DecoratedField(intFild[0]);
			fields.put("MOCK_INT", new DecoratedField[] { df, null });

			IField[] varCharFild = new VarcharFieldFactory().createDoubleField();
			varCharFild[0].setValue(null);
			df = new DecoratedField(varCharFild[0]);
			fields.put("MOCK_VARCHAR", new DecoratedField[] { df, null });

			IField[] doubleFild = new DoubleFieldFactory().createDoubleField();
			doubleFild[0].setValue(null);
			df = new DecoratedField(doubleFild[0]);
			fields.put("MOCK_DOUBLE", new DecoratedField[] { df, null });

			IField[] dateFild = new DateFieldFactory().createDoubleField();
			dateFild[0].setValue(null);
			df = new DecoratedField(dateFild[0]);
			fields.put("MOCK_DATE", new DecoratedField[] { df, null });

			IField[] boolFild = new BooleanFieldFactory().createDoubleField();
			boolFild[0].setValue(null);
			df = new DecoratedField(boolFild[0]);
			fields.put("MOCK_BOOLEAN", new DecoratedField[] { df, null });		
			

			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(
					"insert into MOCK_TABLE(MOCK_CHAR,MOCK_INT,MOCK_VARCHAR,MOCK_DOUBLE,MOCK_DATE,MOCK_BOOLEAN) values ('111','1','mock_varchar','12525',NULL,'true');");
			pstmt.execute();
			pstmt = conn.prepareStatement(
					"insert into MOCK_TABLE(MOCK_CHAR,MOCK_INT,MOCK_VARCHAR,MOCK_DOUBLE,MOCK_DATE,MOCK_BOOLEAN) values ('222','200','druga_torka','22222','2-2-2019','false');");
			pstmt.execute();
			pstmt = conn.prepareStatement(
					"insert into MOCK_TABLE(MOCK_CHAR,MOCK_INT,MOCK_VARCHAR,MOCK_DOUBLE,MOCK_DATE,MOCK_BOOLEAN) values ('333','300','tri','33333','2-3-2019','true');");
			pstmt.execute();
			pstmt.close();

		} catch (NullPointerException npe) {
			// nista ne radi, ovo je zbog grafickog prikaza
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		try {
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1",
					"psw-2018-tim7-1", "tim7-19940718");
			DatabaseMockTable.dropDatabaseTable(conn);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSearchKeyPositive() throws Exception {

		DecoratedField[] flds = (DecoratedField[]) fields.get("MOCK_CHAR");
		DecoratedField firstFld = flds[0];
		firstFld.getCheckbox().setSelected(true);
		((CharField) firstFld.getField()).setValue("111");		

		Vector<Vector<Object>> valueSearch = null;
		// valueSearch = iHandler.search(table, fields);

		DBHandler spied = PowerMockito.spy(new DBHandler());
		doNothing().when(spied).updateTableInfo(anyObject(), anyObject());
		valueSearch = spied.search(table, fields);

		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='333';");
			pstmt.execute();
			pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='222';");
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		deleteRow(rowMap2);
//		deleteRow(rowMap3);
		Vector<Vector<Object>> valueList = null;
		valueList = iHandler.read(table);

		assertEquals(true, compare(valueSearch, valueList));
	}

//	private void deleteRow(HashMap<String, Object> x) {
//		Vector<Object> rowToDelete = new Vector<Object>();
//		for (String stringKey : x.keySet()) {
//			Object value = ((IField) x.get(stringKey)).getValue();
//			rowToDelete.add(value);
//		}
//
//		// izvrsi delete naredbu
//		try {
//			iHandler.delete(table, rowToDelete);
//		} catch (NullPointerException npe) {
//			// zbog grafickog prikaza
//		}
//	}

	private boolean compare(Vector<Vector<Object>> x, Vector<Vector<Object>> y) {

		if (x == null || y == null)
			return false;

		if (x.size() != y.size())
			return false;

		for (int i = 0; i < x.size(); i++) {
			Vector<Object> vectorX = x.get(i);
			Vector<Object> vectorY = y.get(i);
			for (int j = 0; j < vectorX.size(); j++) {
				if (vectorX.get(j) != null && vectorY.get(j) != null)
					if (!(vectorX.get(j).equals(vectorY.get(j))))
						return false;
			}
		}

		return true;
	}

}
