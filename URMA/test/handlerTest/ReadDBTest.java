/**
 * 
 */
package handlerTest;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Vector;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import controler.handlers.DBHandler;
import controler.handlers.IHandler;
import model.Table;

/**
 * @author filip
 *
 */
public class ReadDBTest {

	private static Connection conn = null;
	private static Table table = null;
	private static IHandler iHandler = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		conn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.77.230/psw-2018-tim7-1", "psw-2018-tim7-1","tim7-19940718");
		DatabaseMockTable.createDatabaseTable(conn);
		table = DatabaseMockTable.createMockTable();

		// pravljenje spijuniranog handlera gde se updejt gui-a mockuje
		iHandler = new DBHandler();

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
		conn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.77.230/psw-2018-tim7-1", "psw-2018-tim7-1","tim7-19940718");
		DatabaseMockTable.dropDatabaseTable(conn);
		conn.close();
	}

	@Test
	public void readPositive() throws Exception {

		Vector<Vector<Object>> valueList = null;
		valueList = iHandler.read(table);
		
		Vector<Vector<Object>> valueNeeded = new Vector<Vector<Object>>();
		Vector<Object> row = new Vector<Object>();
		row.add("111");
		row.add(1);
		row.add("mock_varchar");
		row.add(125.25);
		row.add("null");
		row.add(true);
		valueNeeded.addElement(row);
		row = new Vector<Object>();
		row.add("222");
		row.add(200);
		row.add("druga_torka");
		row.add(222.22);
		row.add("2019-02-02");
		row.add(false);
		valueNeeded.addElement(row);
		row = new Vector<Object>();
		row.add("333");
		row.add(300);
		row.add("tri");
		row.add(333.33);
		row.add("2019-02-03");
		row.add(true);
		valueNeeded.addElement(row);

		assertEquals(true, compare(valueNeeded, valueList));
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
