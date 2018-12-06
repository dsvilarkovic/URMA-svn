/**
 * 
 */
package controler.handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author filip
 *
 */
public class DBHandler implements IHandler {
	
	Connection conn;
	public DBHandler() {
		
		//pitaj Peru da li da se svaki put otvara konkcija ili da ovako nekako otvoris konekciju i da ostane
		
//		try {
//			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1","psw-2018-tim7-1","tim7-19940718");
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(null, "COnnection Error", "Connection couldnt be established",JOptionPane.ERROR_MESSAGE);
//			e.printStackTrace();
//		}
	}
	
	@Override
	public void create() {
	}

	@Override
	public Vector<Vector<Object>> read(String tableCode, int attributeNumber) {
		String sql = "select * from " + tableCode;
		
		PreparedStatement pstmt;
		try {
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1","psw-2018-tim7-1","tim7-19940718");
			pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();
			
			Vector<Vector<Object>> valueMap = new Vector<Vector<Object>>();
			while(rset.next()) {
			
				Vector<Object> valueList = new Vector<Object>();
				for (int i = 1; i <= attributeNumber; i++) {
					Object object = rset.getObject(i);
					valueList.add(object);
				}
				valueMap.add(valueList);
			}
			
			rset.close();
			pstmt.close();
			
			return valueMap;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}
}
