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
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import model.Table;

import org.jdatepicker.impl.JDatePickerImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import view.fieldFactory.BooleanField;
import view.fieldFactory.BooleanFieldFactory;
import view.fieldFactory.CharField;
import view.fieldFactory.CharFieldFactory;
import view.fieldFactory.DateField;
import view.fieldFactory.DateFieldFactory;
import view.fieldFactory.DecoratedField;
import view.fieldFactory.DoubleField;
import view.fieldFactory.DoubleFieldFactory;
import view.fieldFactory.IField;
import view.fieldFactory.IntegerField;
import view.fieldFactory.IntegerFieldFactory;
import view.fieldFactory.VarcharField;
import view.fieldFactory.VarcharFieldFactory;
import controler.handlers.DBHandler;
import controler.handlers.IHandler;

/**
 * @author filip
 *
 */
public class SearchDBTest {

	private static Connection conn = null;
	private static Table table = null;
	private static IHandler iHandler = null;
	private static HashMap<String, Object> fields;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//TODO : @Filip testiranje: Locale.setDefault(new Locale("en", "UK")) / Locale.setDefault(new Locale("sr", "RS"));
		conn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.77.230/psw-2018-tim7-1", "psw-2018-tim7-1",
				"tim7-19940718");
		DatabaseMockTable.createDatabaseTable(conn);
		table = DatabaseMockTable.createMockTable();
		
		Locale.setDefault(new Locale("uk", "UK"));

		// pravljenje spijuniranog handlera gde se updejt gui-a mockuje
		DBHandler spied = PowerMockito.spy(new DBHandler());
		doNothing().when(spied).updateTableInfo(anyObject(), anyObject());
		iHandler = spied;

		// ubacivanje torki u tabelu
		PreparedStatement pstmt;
		pstmt = conn.prepareStatement(
				"insert into MOCK_TABLE(MOCK_CHAR,MOCK_INT,MOCK_VARCHAR,MOCK_DOUBLE,MOCK_DATE,MOCK_BOOLEAN) values ('111','1','mock_varchar','125.25',NULL,'true');");
		pstmt.execute();
		pstmt = conn.prepareStatement(
				"insert into MOCK_TABLE(MOCK_CHAR,MOCK_INT,MOCK_VARCHAR,MOCK_DOUBLE,MOCK_DATE,MOCK_BOOLEAN) values ('222','200','druga_torka','222.22','2-2-2019','false');");
		pstmt.execute();
		pstmt = conn.prepareStatement(
				"insert into MOCK_TABLE(MOCK_CHAR,MOCK_INT,MOCK_VARCHAR,MOCK_DOUBLE,MOCK_DATE,MOCK_BOOLEAN) values ('333','300','tri','333.33','2-3-2019','true');");
		pstmt.execute();
		pstmt.close();

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		conn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.77.230/psw-2018-tim7-1", "psw-2018-tim7-1",
				"tim7-19940718");
		DatabaseMockTable.dropDatabaseTable(conn);
		conn.close();
	}

	@After
	public void tearDownAfter() throws Exception {
		PreparedStatement pstmt;
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where 1=1");
		pstmt.execute();
		pstmt = conn.prepareStatement(
				"insert into MOCK_TABLE(MOCK_CHAR,MOCK_INT,MOCK_VARCHAR,MOCK_DOUBLE,MOCK_DATE,MOCK_BOOLEAN) values ('111','1','mock_varchar','125.25',NULL,'true');");
		pstmt.execute();
		pstmt = conn.prepareStatement(
				"insert into MOCK_TABLE(MOCK_CHAR,MOCK_INT,MOCK_VARCHAR,MOCK_DOUBLE,MOCK_DATE,MOCK_BOOLEAN) values ('222','200','druga_torka','222.22','2-2-2019','false');");
		pstmt.execute();
		pstmt = conn.prepareStatement(
				"insert into MOCK_TABLE(MOCK_CHAR,MOCK_INT,MOCK_VARCHAR,MOCK_DOUBLE,MOCK_DATE,MOCK_BOOLEAN) values ('333','300','tri','333.33','2-3-2019','true');");
		pstmt.execute();
		pstmt.close();
	}

	@Before
	public void setUpBefore() throws Exception {
		// pravljenje dekorisanih objekata
		fields = new HashMap<String, Object>();
		DecoratedField df, df1;

		// Char
		IField[] charFild = new CharFieldFactory().createDoubleField();
		charFild[0].setValue(null);
		df = new DecoratedField(charFild[0]);
		fields.put("MOCK_CHAR", new DecoratedField[] { df, null });

		// Integer
		IField[] intFild = new IntegerFieldFactory().createDoubleField();
		intFild[0].setValue(null);
		df = new DecoratedField(intFild[0]);
		intFild[1].setValue(null);
		df1 = new DecoratedField(intFild[1]);
		fields.put("MOCK_INT", new DecoratedField[] { df, df1 });

		// VarChar
		IField[] varCharFild = new VarcharFieldFactory().createDoubleField();
		varCharFild[0].setValue(null);
		df = new DecoratedField(varCharFild[0]);
		fields.put("MOCK_VARCHAR", new DecoratedField[] { df, null });

		// Double
		IField[] doubleFild = new DoubleFieldFactory().createDoubleField();
		doubleFild[0].setValue(null);
		df = new DecoratedField(doubleFild[0]);
		doubleFild[1].setValue(null);
		df1 = new DecoratedField(doubleFild[1]);
		fields.put("MOCK_DOUBLE", new DecoratedField[] { df, df1 });

		// Date
		IField[] dateFild = new DateFieldFactory().createDoubleField();
		dateFild[0].setValue(null);
		df = new DecoratedField(dateFild[0]);
		dateFild[1].setValue(null);
		df1 = new DecoratedField(dateFild[1]);
		fields.put("MOCK_DATE", new DecoratedField[] { df, df1 });

		// Boolean
		IField[] boolFild = new BooleanFieldFactory().createDoubleField();
		boolFild[0].setValue(null);
		df = new DecoratedField(boolFild[0]);
		fields.put("MOCK_BOOLEAN", new DecoratedField[] { df, null });

	}

	@Test
	public void testSearchKeyPositive() throws Exception {

		DecoratedField[] flds = (DecoratedField[]) fields.get("MOCK_CHAR");
		DecoratedField firstFld = flds[0];
		firstFld.getCheckbox().setSelected(true);
		((CharField) firstFld.getField()).setValue("111");

		Vector<Vector<Object>> valueSearch = null;
		valueSearch = iHandler.search(table, fields);

		PreparedStatement pstmt;
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='333';");
		pstmt.execute();
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='222';");
		pstmt.execute();

		Vector<Vector<Object>> valueList = null;
		valueList = iHandler.read(table);

		assertEquals(true, compare(valueSearch, valueList));
	}

	@Test
	public void testSearchKeyNotFound() throws Exception {

		DecoratedField[] flds = (DecoratedField[]) fields.get("MOCK_CHAR");
		DecoratedField firstFld = flds[0];
		firstFld.getCheckbox().setSelected(true);
		((CharField) firstFld.getField()).setValue("515");

		Vector<Vector<Object>> valueSearch = null;
		valueSearch = iHandler.search(table, fields);

		PreparedStatement pstmt;
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='111';");
		pstmt.execute();
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='333';");
		pstmt.execute();
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='222';");
		pstmt.execute();

		Vector<Vector<Object>> valueList = null;
		valueList = iHandler.read(table);

		assertEquals(true, compare(valueSearch, valueList));
	}

	@Test
	public void testSearchIntPositive() throws Exception {

		DecoratedField[] flds = (DecoratedField[]) fields.get("MOCK_INT");
		DecoratedField firstFld = flds[0];
		firstFld.getCheckbox().setSelected(true);
		((IntegerField) firstFld.getField()).setValue(150);
		DecoratedField scndFld = flds[1];
		scndFld.getCheckbox().setSelected(true);
		((IntegerField) scndFld.getField()).setValue(500);

		Vector<Vector<Object>> valueSearch = null;
		valueSearch = iHandler.search(table, fields);

		PreparedStatement pstmt;
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='111';");
		pstmt.execute();

		Vector<Vector<Object>> valueList = null;
		valueList = iHandler.read(table);

		assertEquals(true, compare(valueSearch, valueList));
	}
	
	@Test
	public void testSearchIntNotFound() throws Exception {

		DecoratedField[] flds = (DecoratedField[]) fields.get("MOCK_INT");
		DecoratedField firstFld = flds[0];
		firstFld.getCheckbox().setSelected(true);
		((IntegerField) firstFld.getField()).setValue(450);
		DecoratedField scndFld = flds[1];
		scndFld.getCheckbox().setSelected(true);
		((IntegerField) scndFld.getField()).setValue(500);

		Vector<Vector<Object>> valueSearch = null;
		valueSearch = iHandler.search(table, fields);

		PreparedStatement pstmt;
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='111';");
		pstmt.execute();
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='222';");
		pstmt.execute();
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='333';");
		pstmt.execute();

		Vector<Vector<Object>> valueList = null;
		valueList = iHandler.read(table);

		assertEquals(true, compare(valueSearch, valueList));
	}

	@Test
	public void testSearchVarCharPositive() throws Exception {

		DecoratedField[] flds = (DecoratedField[]) fields.get("MOCK_VARCHAR");
		DecoratedField firstFld = flds[0];
		firstFld.getCheckbox().setSelected(true);
		((VarcharField) firstFld.getField()).setValue("torka");

		Vector<Vector<Object>> valueSearch = null;
		valueSearch = iHandler.search(table, fields);

		PreparedStatement pstmt;
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='333';");
		pstmt.execute();
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='111';");
		pstmt.execute();

		Vector<Vector<Object>> valueList = null;
		valueList = iHandler.read(table);

		assertEquals(true, compare(valueSearch, valueList));
	}
	
	@Test
	public void testSearchVarCharMultiPositive() throws Exception {

		DecoratedField[] flds = (DecoratedField[]) fields.get("MOCK_VARCHAR");
		DecoratedField firstFld = flds[0];
		firstFld.getCheckbox().setSelected(true);
		((VarcharField) firstFld.getField()).setValue("a");

		Vector<Vector<Object>> valueSearch = null;
		valueSearch = iHandler.search(table, fields);

		PreparedStatement pstmt;
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='333';");
		pstmt.execute();

		Vector<Vector<Object>> valueList = null;
		valueList = iHandler.read(table);

		assertEquals(true, compare(valueSearch, valueList));
	}

	@Test
	public void testSearchVarCharNotFound() throws Exception {

		DecoratedField[] flds = (DecoratedField[]) fields.get("MOCK_VARCHAR");
		DecoratedField firstFld = flds[0];
		firstFld.getCheckbox().setSelected(true);
		((VarcharField) firstFld.getField()).setValue("uiafdaskfasld");

		Vector<Vector<Object>> valueSearch = null;
		valueSearch = iHandler.search(table, fields);

		PreparedStatement pstmt;
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='333';");
		pstmt.execute();
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='222';");
		pstmt.execute();
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='111';");
		pstmt.execute();

		Vector<Vector<Object>> valueList = null;
		valueList = iHandler.read(table);

		assertEquals(true, compare(valueSearch, valueList));
	}
	
	@Test
	public void testSearchDoublePositive() throws Exception {

		DecoratedField[] flds = (DecoratedField[]) fields.get("MOCK_DOUBLE");
		DecoratedField firstFld = flds[0];
		firstFld.getCheckbox().setSelected(true);
		((DoubleField) firstFld.getField()).setValue("125,27");
		DecoratedField scndFld = flds[1];
		scndFld.getCheckbox().setSelected(true);
		((DoubleField) scndFld.getField()).setValue(500);

		Vector<Vector<Object>> valueSearch = null;
		valueSearch = iHandler.search(table, fields);

		PreparedStatement pstmt;
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='111';");
		pstmt.execute();

		Vector<Vector<Object>> valueList = null;
		valueList = iHandler.read(table);

		assertEquals(true, compare(valueSearch, valueList));
	}
	
	@Test
	public void testSearchDoubleNotFound() throws Exception {

		DecoratedField[] flds = (DecoratedField[]) fields.get("MOCK_DOUBLE");
		DecoratedField firstFld = flds[0];
		firstFld.getCheckbox().setSelected(true);
		((DoubleField) firstFld.getField()).setValue("450,23");
		DecoratedField scndFld = flds[1];
		scndFld.getCheckbox().setSelected(true);
		((DoubleField) scndFld.getField()).setValue("500,345");

		Vector<Vector<Object>> valueSearch = null;
		valueSearch = iHandler.search(table, fields);

		PreparedStatement pstmt;
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='111';");
		pstmt.execute();
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='222';");
		pstmt.execute();
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='333';");
		pstmt.execute();

		Vector<Vector<Object>> valueList = null;
		valueList = iHandler.read(table);

		assertEquals(true, compare(valueSearch, valueList));
	}

	@Test
	public void testSearchDatePositive() throws Exception {

		DecoratedField[] flds = (DecoratedField[]) fields.get("MOCK_DATE");
		DecoratedField firstFld = flds[0];
		firstFld.getCheckbox().setSelected(true);
		DateField field = ((DateField) firstFld.getField());
		((JDatePickerImpl) field.getField()).getModel().setDate(2009, 02, 03);
		DecoratedField scndFld = flds[1];
		scndFld.getCheckbox().setSelected(true);
		field = ((DateField) scndFld.getField());
		((JDatePickerImpl) field.getField()).getModel().setDate(2019, 05, 02);

		Vector<Vector<Object>> valueSearch = null;
		valueSearch = iHandler.search(table, fields);

		PreparedStatement pstmt;
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='111';");
		pstmt.execute();

		Vector<Vector<Object>> valueList = null;
		valueList = iHandler.read(table);

		assertEquals(true, compare(valueSearch, valueList));
	}
	
	@Test
	public void testSearchDateNotFound() throws Exception {

		DecoratedField[] flds = (DecoratedField[]) fields.get("MOCK_DATE");
		DecoratedField firstFld = flds[0];
		firstFld.getCheckbox().setSelected(true);
		DateField field = ((DateField) firstFld.getField());
		((JDatePickerImpl) field.getField()).getModel().setDate(2019, 05, 03);
		DecoratedField scndFld = flds[1];
		scndFld.getCheckbox().setSelected(true);
		field = ((DateField) firstFld.getField());
		((JDatePickerImpl) field.getField()).getModel().setDate(2019, 05, 02);

		Vector<Vector<Object>> valueSearch = null;
		valueSearch = iHandler.search(table, fields);

		PreparedStatement pstmt;
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='111';");
		pstmt.execute();
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='222';");
		pstmt.execute();
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='333';");
		pstmt.execute();

		Vector<Vector<Object>> valueList = null;
		valueList = iHandler.read(table);

		assertEquals(true, compare(valueSearch, valueList));
	}
	
	@Test
	public void testSearchBoolPositive() throws Exception {

		DecoratedField[] flds = (DecoratedField[]) fields.get("MOCK_BOOLEAN");
		DecoratedField firstFld = flds[0];
		firstFld.getCheckbox().setSelected(true);
		((BooleanField) firstFld.getField()).setValue(true);

		Vector<Vector<Object>> valueSearch = null;
		valueSearch = iHandler.search(table, fields);

		PreparedStatement pstmt;
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='222';");
		pstmt.execute();

		Vector<Vector<Object>> valueList = null;
		valueList = iHandler.read(table);

		assertEquals(true, compare(valueSearch, valueList));
	}

	@Test
	public void testSearchBoolNegative() throws Exception {

		DecoratedField[] flds = (DecoratedField[]) fields.get("MOCK_BOOLEAN");
		DecoratedField firstFld = flds[0];
		firstFld.getCheckbox().setSelected(true);
		((BooleanField) firstFld.getField()).setValue(false);

		Vector<Vector<Object>> valueSearch = null;
		valueSearch = iHandler.search(table, fields);

		PreparedStatement pstmt;
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='111';");
		pstmt.execute();
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='333';");
		pstmt.execute();

		Vector<Vector<Object>> valueList = null;
		valueList = iHandler.read(table);

		assertEquals(true, compare(valueSearch, valueList));
	}

	@Test
	public void testSearchDoubleAndBoolPositive() throws Exception {

		DecoratedField[] flds = (DecoratedField[]) fields.get("MOCK_DOUBLE");
		DecoratedField firstFld = flds[0];
		firstFld.getCheckbox().setSelected(true);
		((DoubleField) firstFld.getField()).setValue("200,10");
		DecoratedField scndFld = flds[1];
		scndFld.getCheckbox().setSelected(true);
		((DoubleField) scndFld.getField()).setValue(500);
		
		flds = (DecoratedField[]) fields.get("MOCK_BOOLEAN");
		firstFld = flds[0];
		firstFld.getCheckbox().setSelected(true);
		((BooleanField) firstFld.getField()).setValue(true);

		Vector<Vector<Object>> valueSearch = null;
		valueSearch = iHandler.search(table, fields);

		PreparedStatement pstmt;
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='111';");
		pstmt.execute();
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='222';");
		pstmt.execute();

		Vector<Vector<Object>> valueList = null;
		valueList = iHandler.read(table);

		assertEquals(true, compare(valueSearch, valueList));
	}
	
	@Test
	public void testSearchVarCharAndIntPositive() throws Exception {

		DecoratedField[] flds = (DecoratedField[]) fields.get("MOCK_VARCHAR");
		DecoratedField firstFld = flds[0];
		firstFld.getCheckbox().setSelected(true);
		((VarcharField) firstFld.getField()).setValue("a");
		
		flds = (DecoratedField[]) fields.get("MOCK_INT");
		firstFld = flds[0];
		firstFld.getCheckbox().setSelected(true);
		((IntegerField) firstFld.getField()).setValue(0);
		DecoratedField scndFld = flds[1];
		scndFld.getCheckbox().setSelected(true);
		((IntegerField) scndFld.getField()).setValue(500);

		Vector<Vector<Object>> valueSearch = null;
		valueSearch = iHandler.search(table, fields);

		PreparedStatement pstmt;
		pstmt = conn.prepareStatement("DELETE FROM MOCK_TABLE where MOCK_CHAR='333';");
		pstmt.execute();

		Vector<Vector<Object>> valueList = null;
		valueList = iHandler.read(table);

		assertEquals(true, compare(valueSearch, valueList));
	}
	
	private boolean compare(Vector<Vector<Object>> x, Vector<Vector<Object>> y) {

		System.out.println(x);
		System.out.println(y);

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
