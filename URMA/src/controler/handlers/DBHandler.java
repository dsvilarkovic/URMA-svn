/**
 * 
 */
package controler.handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;

import app.App;
import model.Attribute;
import model.Table;
import view.fieldFactory.DecoratedField;
import view.fieldFactory.IField;

/**
 * Handler za rad sa bazom
 * @author filip
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
	
	/**
		Create akcija nad bazom - unos torke sa zadatim parametrima		
		@author - Jelena
		@param param1 - Tabela nad kojom se izvršava akcija
		@param param2 - podatci koji se unose u tabelu u obliku mape(kolona, podatak)  
		@return - uspesnost operacije
	**/
	@Override
	public Boolean create(Table table, HashMap<String, Object> data) {
		String coloumn_str = "";
		String value_str = "'";
		Map<String, Attribute> attributes = table.getAttributes();
		for (String key : attributes.keySet()) {
			coloumn_str += key + ",";
			Attribute attribute = attributes.get(key);
			IField field = (IField)data.get(attribute.getTitle());
			if(!field.validateField(attribute.getIsRequired(), attribute.getIsPrimaryKey(), attribute.getMaxLength())){
				JOptionPane.showMessageDialog(null, attributes.get(key).getTitle() + " not valid");
				return false;
			}
			if(field.getValue() == null) {
				value_str = value_str.substring(0, value_str.length() - 1) + "NULL,'";
			}else {
				value_str += field.getValue().toString() + "','";
			}
		}
		coloumn_str = coloumn_str.substring(0, coloumn_str.length() - 1);
		value_str = value_str.substring(0, value_str.length() - 2);
		String sql = "insert into " + table.getCode() + "(" + coloumn_str + ")" + " values (" + value_str + ");";
		System.out.println(sql);
		
		PreparedStatement pstmt;
		

//		/**
//		 * @author Dusan
//		 */
//		String urlEduroam = "jdbc:jtds:sqlserver://192.168.77.230/psw-2018-tim7-1";
//		String urlHome = "jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1";
//		String [] urls = {urlEduroam, urlHome};
//		boolean stop_other_connections = false; //sluzi za proveru da li treba isprobavati dalje konektovanje
//		
//		for (String url : urls) {
//			try {
//				conn = DriverManager.getConnection(url,"psw-2018-tim7-1","tim7-19940718");
//				pstmt = conn.prepareStatement(sql);
//				pstmt.execute();
//				pstmt.close();
//				stop_other_connections = true; 
//			} catch (SQLException e) {
////				String text = "<html>No such table <strong style=\"color: red;\">"+ table.getTitle() +"</strong> in database or no connection</html>";
////				JOptionPane.showMessageDialog(null, text, "INVALID SQL", JOptionPane.ERROR_MESSAGE);
//				e.printStackTrace();
//			}			
//			if(stop_other_connections) {
//				break;
//			}
//		}
		
		
		try {
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1","psw-2018-tim7-1","tim7-19940718");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1","psw-2018-tim7-1","tim7-19940718");
			pstmt = conn.prepareStatement(sql);
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
//			String text = "<html>No such table <strong style=\"color: red;\">"+ table.getTitle() +"</strong> in database or no connection</html>";
//			JOptionPane.showMessageDialog(null, text, "INVALID SQL", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		App.INSTANCE.getTableMediator().showTable(table);
		return true;
	}

	/**
	 * Metoda za ucitavanje podataka tabele iz baze
	 * @author filip
	 * @param table - tabela za koju treba da se izvuku podaci
	 * @return {@link Vector}&lt;{@link Vector}&lt;{@link Object}>> - vektor redova u tabeli
	 */
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
			return executeStatement(sql, table);
		} catch (SQLException e) {
			String text = "<html>No such table <strong style=\"color: red;\">"+ table.getTitle() +"</strong> in database or no connection</html>";
			JOptionPane.showMessageDialog(null, text, "INVALID SQL", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		
		
//		/**
//		 * @author Dusan 
//		 */
//		String urlEduroam = "jdbc:jtds:sqlserver://192.168.77.230/psw-2018-tim7-1";
//		String urlHome = "jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1";
//		String [] urls = {urlEduroam, urlHome};
//		boolean stop_other_connections = false; //sluzi za proveru da li treba isprobavati dalje konektovanje
//		Vector<Vector<Object>> valueMap = null;
//		
//		
//		for (String url : urls) {
//			try {
//				conn = DriverManager.getConnection(url,"psw-2018-tim7-1","tim7-19940718");
//				
//				pstmt = conn.prepareStatement(sql);
//				ResultSet rset = pstmt.executeQuery();
//							
//				valueMap = new Vector<Vector<Object>>();
//				while(rset.next()) {
//					
//					Vector<Object> valueList = new Vector<Object>();
//					for (int i = 1; i <= table.getAttributes().size(); i++) {
//						Object object = rset.getObject(i);
//						valueList.add(object);
//					}
//					valueMap.add(valueList);
//				}
//							
//				rset.close();
//				pstmt.close();
//				stop_other_connections = true; 
//			} catch (SQLException e) {
//				String text = "<html>No such table <strong style=\"color: red;\">"+ table.getTitle() +"</strong> in database or no connection</html>";
//				
//				
//				JOptionPane.showMessageDialog(null, text, "INVALID SQL", JOptionPane.ERROR_MESSAGE);
//				
//				if(url.contains("192.168.77.230")) {
//					
//					String message = "Do you want to connect to 147.91.175.155?";
//					int response = JOptionPane.showConfirmDialog(null, message, "New route option", JOptionPane.YES_NO_OPTION);
//					if(response == JOptionPane.YES_OPTION) {
//						valueMap = null;
//						continue;
//					}
//				}
//				return null;
//				
//			}			
//			if(stop_other_connections) {
//				break;
//			}
//		}
//		
//		return valueMap;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Table table, Vector<Object> values) {
		// TODO Auto-generated method stub
		String sql = "delete from " + table.getCode() + " where ";
		Map<String, Attribute> attributes = table.getAttributes();
		int i = 0;
		for (String key : attributes.keySet()) {
			sql += key + "='" + values.get(i) + "' AND ";
			i++;
		}
		sql = sql.substring(0, sql.length() - 5);
		sql += ";";
		System.out.println(sql);
		PreparedStatement pstmt;
		
		try {
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1","psw-2018-tim7-1","tim7-19940718");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1","psw-2018-tim7-1","tim7-19940718");
			pstmt = conn.prepareStatement(sql);
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		App.INSTANCE.getTableMediator().showTable(table);
	}

	/**
	 * Metoda za search akciju
	 * @author filip
	 * @param table - tabela za koju treba da se izvuku podaci
	 * @param data - hashMapa koja sadrzi naziv atributa i vrednosti atributa zadat u search prozoru
	 * @return {@link Boolean} - true da unisti search prozor
	 */
	@Override
	public Boolean search(Table table, HashMap<String, Object> data) {
		String where = " where ";
		String and = " and ";
		
		//iteracija kroz prosledjenja polja koja su u vidu dekorisanih polja atributa
		for (String key : data.keySet()) {
			DecoratedField[] decField = (DecoratedField[]) data.get(key); //posto iz nekog razloga se salje kao niz dekorisanih polja
			
			DecoratedField first = decField[0];
			DecoratedField second = decField[1];
			
			//ako je selektovano polje znaci po njemu cemo traziti
			if(((boolean) first.getValue())) {
				IField field = (IField) first.getField();
				String code = "";
				String type = "";
				
				//posto iz HashMapa prosledjuje title a ne code obelezjea moramo naci code obelezja te iteriramo dog ga ne nadjemo
				for (Attribute atrib : table.getAttributes().values()) {
					if (atrib.getTitle().equals(key)) {
						code = atrib.getCode();
						type = atrib.getType();
						break;
					}
				}
						
				switch (type) {
				case "varchar":
				case "char": where = where + code + " like " + "'%" + field.getValue().toString() + "%'"+ and;					
					break;
				case "boolean": where = where + code + "='" + field.getValue().toString() + "'" + and;
					break;
				case "int":
				case "double": where = where + code + " between " + field.getValue().toString() + and + ((IField) second.getField()).getValue().toString() + and; break;
				case "date": where = where + code + " between '" + field.getValue().toString() + "'" + and + "'" + ((IField) second.getField()).getValue().toString() + "'" + and; break;
				default:
					break;
				}					
			}	
		}
		// pravljenje sql upita
		where = where.substring(0, where.length() - 5);
		String sql = "Select * from " + table.getCode() + where;
		System.out.println(sql);
		
		//slanje upita ka bazi
		try {
			Vector<Vector<Object>> valueMap = executeStatement(sql, table);
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return true;
	}
	
	private Vector<Vector<Object>> executeStatement(String sql, Table table) throws SQLException{
		PreparedStatement pstmt;
		//conn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.77.230/psw-2018-tim7-1","psw-2018-tim7-1","tim7-19940718");
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
	}
}
