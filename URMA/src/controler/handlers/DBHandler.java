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

import javax.swing.JOptionPane;

import model.Attribute;
import model.Table;

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
	public Vector<Vector<Object>> read(Table table) {
		
		String str = "";
		for (Attribute attrib : table.getAttributes().values()) {
			str += attrib.getCode() + ",";
		}
		str = str.substring(0, str.length() - 1);
		String sql = "select " + str + " from " + table.getCode();
		System.out.println(sql);
		
		PreparedStatement pstmt;
		try {
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1","psw-2018-tim7-1","tim7-19940718");
			pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();
			
			Vector<Vector<Object>> valueMap = new Vector<Vector<Object>>();
			while(rset.next()) {
			
				Vector<Object> valueList = new Vector<Object>();
				for (int i = 1; i <= table.getAttributes().size(); i++) {
					Object object = rset.getObject(i);
					valueList.add(object);
				}
				valueMap.add(valueList);
			}
			
			rset.close();
			pstmt.close();
			
			return valueMap;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "NO SUCH TABLE OR NO DATABESE", "INVALID SQL", JOptionPane.ERROR_MESSAGE);
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
