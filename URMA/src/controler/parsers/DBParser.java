package controler.parsers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import app.App;
import model.Attribute;
import model.InformationResource;
import model.Package;
import model.Relation;
import model.Table;
import view.dialogs.DBParserConnectionDialog;

/**
 * Parser za bazu podataka. Ponaša se po predefinisanim pravilima i nazivima tabelama
 * u okviru tražene baze.
 * @author Boris
 */
public class DBParser implements IParser {
	
	private final static String preIP = "jdbc:jtds:sqlserver://";
	//TN = Table Name
	private final static String PACKAGESTN = "PODSISTEM";
	private final static String TABLESTN = "TABELE";
	private final static String ATTRIBUTESTN = "ATRIBUTI";
	private final static String RELATIONSTN = "STRANI_KLJUC";
	private final static String PACKAGESTRUCTURETN = "STRUKTURA_PODSISTEMA";
	private final static String FOREIGNKEYSTN = "KOLONE_U_STRANOM_KLJUCU";
	

	/* (non-Javadoc)
	 * @see controler.parsers.IParser#parse()
	 */
	@Override
	public InformationResource parse() {
		DBParserConnectionDialog dbpcd = new DBParserConnectionDialog();
		dbpcd.setVisible(true);
		if (!dbpcd.isDataValid()) {
			dbpcd.dispose();
			return null;
		}
		String ip = dbpcd.getIP();
		String user = dbpcd.getUser();
		String pass = dbpcd.getPassword();
		return parseThis(ip, user, pass);
	}
	
	public InformationResource parseThis(String ip, String user, String pass) {
		ResourceBundle rb = App.INSTANCE.getResourceBundle();
		Connection con;
		try {
			con = DriverManager.getConnection(preIP + ip + '/' + user, user, pass);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, 
					rb.getString("database.connection.error"), 
					rb.getString("database.connection"), 
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		InformationResource model = new InformationResource();
		
		//paketi
		HashMap<String, Package> allPack = new HashMap<>();
		
		String sql = "SELECT * FROM " + PACKAGESTN;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rset = ps.executeQuery();
			
			while(rset.next()) {
				String pcode = rset.getString(1);
				String ptitle = rset.getString(2);
				
				if (!pcode.equals("PSW2017")) {
					Package p = new Package(pcode, ptitle);
					allPack.put(pcode, p);
				}
			}
			rset.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		//struktura paketa
		ArrayList<String> parentCodes = new ArrayList<>();
		ArrayList<String> childCodes = new ArrayList<>();
		ArrayList<String> possParents = new ArrayList<>();
		
		sql = "SELECT * FROM " + PACKAGESTRUCTURETN;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rset = ps.executeQuery();
			
			while (rset.next()) {
				String pcode = rset.getString(1);
				String ccode = rset.getString(2);
				
				//prvi nivo
				if (pcode.equals("PSW2017")) {
					Package p = allPack.get(ccode);
					model.addPackages(p);
					possParents.add(ccode);
				}
				else {
					parentCodes.add(pcode);
					childCodes.add(ccode);
				}
			}
			rset.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		// parsiranje paketa
		while(!possParents.isEmpty()) {
			ArrayList<String> newPossParents = new ArrayList<>();
			
			for (int i = 0; i < parentCodes.size(); i++) {
				if (possParents.contains(parentCodes.get(i))) {
					Package parent = allPack.get(parentCodes.get(i));
					Package child = allPack.get(childCodes.get(i));
					parent.addChildPackages(child);
					
					newPossParents.add(childCodes.get(i));
				}
			}
			
			possParents = newPossParents;			
		}
		
		
		//tabele
		sql = "SELECT * FROM " + TABLESTN;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rset = ps.executeQuery();
			
			while (rset.next()) {
				String tpack = rset.getString(1);
				String tcode = rset.getString(2);
				String ttitle = rset.getString(3);
				
				Package p = allPack.get(tpack);
				Table t = new Table(ttitle, tcode);
				model.addAllTables(t);
				p.addTables(t);
			}
			rset.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		//atributi
		sql = "SELECT * FROM " + ATTRIBUTESTN;
		HashMap<String, Table> allTables = (HashMap<String, Table>) model.getAllTables();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rset = ps.executeQuery();
			
			while (rset.next()) {
				Table t = allTables.get(rset.getString(2));
				String acode = rset.getString(3);
				String atitle = rset.getString(4);
				String atype = rset.getString(5);
				// numeric & decimal -> double
				if (atype.toLowerCase().equals("numeric") || atype.toLowerCase().equals("decimal")) {
					atype = "double";
				}
				if (atype.equals("datetime")) {
					atype = "date";
				}
				boolean areq = rset.getBoolean(6);
				int alen = rset.getInt(7);
				if (atype.equals("int")) {
					alen = Integer.MAX_VALUE;
				}
				int prec = rset.getInt(8);
				boolean aipk = rset.getBoolean(9);
				Attribute a = new Attribute(atitle, acode, aipk, areq, atype, alen, prec);
				
				a.setTable(t);				
			}
			rset.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		//relacije
		sql = "SELECT * FROM " + RELATIONSTN;
		HashMap<String, Relation> allRelations = (HashMap<String, Relation>) model.getRelations(); 
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rset = ps.executeQuery();
			
			while (rset.next()) {
				Table tpar = allTables.get(rset.getString(2));
				Table tchi = allTables.get(rset.getString(4));
				if (tpar == null || tchi == null) {
					return null;
				}
				String rcode = rset.getString(5);
				String rtitle = rset.getString(6);
				
				Relation r  = new Relation(rtitle, rcode);
				r.setSourceTable(tpar);
				r.setDestinationTable(tchi);
				allRelations.put(rcode, r);
			}
			rset.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		//kljucevi u relacijama
		sql = "SELECT * FROM " + FOREIGNKEYSTN;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rset = ps.executeQuery();
			
			while (rset.next()) {
				Table tpar = allTables.get(rset.getString(2));
				Table tchi = allTables.get(rset.getString(4));
				Relation r = allRelations.get(rset.getString(5));
				if (tpar == null || tchi == null || r == null) {
					return null;
				}
				Attribute source = tpar.getAttribute(rset.getString(6));
				Attribute dest = tchi.getAttribute(rset.getString(7));
				if (source == null || dest == null) {
					return null;
				}
				r.addSourceKeys(source);
				r.addDestinationKeys(dest);
				
				//identifikaciona zavisnost
				if (dest.getIsPrimaryKey()) {
					tpar.addChildTables(tchi);
					tchi.addParentTables(tpar);
				}
			}
			rset.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return model;
	}
}
