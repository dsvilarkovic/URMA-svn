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
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JOptionPane;

import app.App;
import model.Attribute;
import model.Table;
import view.fieldFactory.DecoratedField;
import view.fieldFactory.IField;
import view.localizationManager.LocalizationManager;
import view.table.TableModel;

/**
 * Handler za rad sa bazom
 * 
 * @author filip
 */
public class DBHandler implements IHandler {

	Connection conn;

	public DBHandler() {

		// pitaj Peru da li da se svaki put otvara konkcija ili da ovako nekako otvoris
		// konekciju i da ostane

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
		@param table - Tabela nad kojom se izvršava akcija
		@param data - podatci koji se unose u tabelu u obliku mape(kolona, podatak)  
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
			IField field = (IField) data.get(attribute.getTitle());
			if (!field.validateField(attribute.getIsRequired(), attribute.getIsPrimaryKey(),
					attribute.getMaxLength())) {
				ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
				JOptionPane.showMessageDialog(null, attributes.get(key).getTitle() + " " + resourceBundle.getString("table.attribute.notvalid"));
				return false;
			}
			if (field.getValue() == null) {
				value_str = value_str.substring(0, value_str.length() - 1) + "NULL,'";
			} else {
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

//		try {
//			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1","psw-2018-tim7-1","tim7-19940718");
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		try {
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1",
					"psw-2018-tim7-1", "tim7-19940718");
			pstmt = conn.prepareStatement(sql);
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
			JOptionPane.showMessageDialog(null, resourceBundle.getString("table.constraints.notvalid"));
		}

		App.INSTANCE.getTableMediator().showTable(table);
		return true;
	}

	/**
	 * Metoda za ucitavanje podataka tabele iz baze
	 * 
	 * @author filip
	 * @param table - tabela za koju treba da se izvuku podaci
	 * @return {@link Vector}&lt;{@link Vector}&lt;{@link Object}>> - vektor redova
	 *         u tabeli
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

		try {
			return executeStatement(sql, table);
		} catch (SQLException e) {
			String text = "<html>No such table <strong style=\"color: red;\">" + table.getTitle()
					+ "</strong> in database or no connection</html>";
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

	/**
		Update akcija nad bazom - izmena torke sa zadatim parametrima		
		@author - Jelena
		@param table - Tabela nad kojom se izvršava akcija
		@param data - podatci koji se unose u tabelu u obliku mape(kolona, podatak)  
		@return - uspesnost operacije
	**/
	@Override
	public Boolean update(Table table, HashMap<String, Object> data) {
		String sql = "update " + table.getCode() + " set ";
		String sql_where = " where ";
		Map<String, Attribute> attributes = table.getAttributes();
		for (String key : attributes.keySet()) {
			Attribute attribute = attributes.get(key);
			IField field = (IField)data.get(attribute.getTitle());
			if(attribute.getIsPrimaryKey()) {
				sql_where += key + "='" + field.getValue().toString() + "' AND ";
			}else {
				sql += key + "='";
				if(!field.validateField(attribute.getIsRequired(), attribute.getIsPrimaryKey(), attribute.getMaxLength())){
					ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
					JOptionPane.showMessageDialog(null, attributes.get(key).getTitle() + " " + resourceBundle.getString("table.attribute.notvalid"));
					return false;
				}
				if(field.getValue() == null) {
					sql+= "NULL, "; 
				}else {
					sql += field.getValue().toString() + "', ";
				}
			}
		}
		sql = sql.substring(0, sql.length() - 2);
		sql_where = sql_where.substring(0, sql_where.length() - 5);
		sql += sql_where + ";";
		System.out.println(sql);
		
		PreparedStatement pstmt;
		
		try {
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1","psw-2018-tim7-1","tim7-19940718");
			pstmt = conn.prepareStatement(sql);
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
			JOptionPane.showMessageDialog(null, resourceBundle.getString("table.constraints.notvalid"));
		}
		
		App.INSTANCE.getTableMediator().showTable(table);
		
		return true;
	}

	/**
		Delete akcija nad bazom - brisanje torke koja je izabrana u tabeli		
		@author - Jelena
		@param table - Tabela nad kojom se izvršava akcija
		@param values - podatci koji se brišu
	**/
	@Override
	public void delete(Table table, Vector<Object> values) {
		// TODO Auto-generated method stub
		System.out.println("DELETE");
		String sql = "delete from " + table.getCode() + " where ";
		Map<String, Attribute> attributes = table.getAttributes();
		int i = 0;
		for (String key : attributes.keySet()) {
			//TODO: @Dusan radio, Jelena da proveri
			Attribute attribute = table.getAttribute(key);
			String type = attribute.getType();
			String value = values.get(i).toString();
			sql += key + "='";
			value = convertValue(value, type);		
			sql += value + "' AND ";
			//kraj doradjivanja by @Dusan
			
			//Jelena radila
			//sql += key + "='" + values.get(i) + "' AND ";
			i++;
		}
		sql = sql.substring(0, sql.length() - 5);
		sql += ";";
		System.out.println(sql);
		PreparedStatement pstmt;
		

		try {
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1",
					"psw-2018-tim7-1", "tim7-19940718");
			pstmt = conn.prepareStatement(sql);
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		App.INSTANCE.getTableMediator().showTable(table);
	}
	
	private String convertValue(String value, String type) {
		switch(type) {
			case "double":{
				value = LocalizationManager.formatNumber(value).toString();
				break;
			}
			case "int":{
				value = LocalizationManager.formatNumber(value).toString();
				break;
			}
			case "date":{
				value = LocalizationManager.formatDateStringDatabase(value);
				break;
			}
			case "bool":{
				if(value.equals("true")) {
					value = "1";
				}
				else if(value.equals("false")) {
					value = "0";
				}
			}
		}
		return value;
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
				// ako je postoji drugo polje za unose sa 
				IField field2 = null;
				if(second != null) {
					field2 = (IField) second.getField();
					if(field2.getValue() == null)
						field2 = field;
				}
						
				switch (type) {
				case "varchar":
				case "char": where = where + code + " like " + "'%" + field.getValue().toString() + "%'"+ and;					
					break;
				case "boolean": where = where + code + "='" + field.getValue().toString() + "'" + and;
					break;
				case "int":
				case "double": where = where + code + " between " + field.getValue().toString() + and + field2.getValue().toString() + and; break;
				case "date": where = where + code + " between '" + field.getValue().toString() + "'" + and + "'" + field2.getValue().toString() + "'" + and; break;
				default:
					break;
				}					
			}	
		}
		// pravljenje sql upita
		where = where.substring(0, where.length() - 5);
		String str = "";
		for (Attribute attrib : table.getAttributes().values()) {
			str += attrib.getCode() + ",";
		}
		str = str.substring(0, str.length() - 1);
		String sql = "select " + str + " from " + table.getCode() + where;
		System.out.println(sql);
		
		//slanje upita ka bazi i updejtovanje modela tabele
		try {
			Vector<Vector<Object>> valuesFromDB = executeStatement(sql, table);
			TableModel menjajOvu = App.INSTANCE.getMainAppFrame().getMainAppPanel().getParentTablePanel().getParentTableModel();
			if(menjajOvu.getTable().getTitle().equals(table.getTitle())) {
				menjajOvu.searchUpdate(valuesFromDB);
			}else {
				menjajOvu = App.INSTANCE.getMainAppFrame().getMainAppPanel().getChildTablePanel().getSelectedChildTableModel();
				menjajOvu.searchUpdate(valuesFromDB);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return true;
	}

	private Vector<Vector<Object>> executeStatement(String sql, Table table) throws SQLException {
		PreparedStatement pstmt;
		// conn =
		// DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.77.230/psw-2018-tim7-1","psw-2018-tim7-1","tim7-19940718");
		conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim7-1", "psw-2018-tim7-1",
				"tim7-19940718");

		pstmt = conn.prepareStatement(sql);
		ResultSet rset = pstmt.executeQuery();

		Vector<Vector<Object>> valueMap = new Vector<Vector<Object>>();
		while (rset.next()) {

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
