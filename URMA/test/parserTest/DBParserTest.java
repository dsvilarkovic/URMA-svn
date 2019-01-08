package parserTest;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import controler.parsers.DBParser;
import controler.parsers.JSONParser;
import model.Attribute;
import model.InformationResource;
import model.Package;
import model.Relation;
import model.Table;

public class DBParserTest {
	private final static String DBIP = "147.91.175.155";
	private final static String DBUSER = "psw-2018-tim7-1";
	private final static String DBPASS = "tim7-19940718";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	public InformationResource prepareIRTestOK1() {
		InformationResource ir = new InformationResource();
		
		Package main = new Package("MAIN", "Main");
		
		Table parent = new Table("Parent", "PAR");
		Attribute a1 = new Attribute("ParentName", "NAM", true, true, "char", 14, 0);
		Attribute a2 = new Attribute("Address", "ADD", false, true, "varchar", 128, 0);
		Attribute a3 = new Attribute("Age", "AGE", false, false, "int", Integer.MAX_VALUE, 0);
		Attribute a4 = new Attribute("Date of birth", "DOB", false, false, "date", 0, 0);
		parent.addAttributes(a1);
		parent.addAttributes(a2);
		parent.addAttributes(a3);
		parent.addAttributes(a4);
		
		Table child = new Table("Child", "CHI");
		Attribute c1 = new Attribute("ParentName", "NAM", true, true, "char", 14, 0);
		Attribute c2 = new Attribute("Surname", "SUR", true, true, "varchar", 128, 0);
		Attribute c3 = new Attribute("Number of presents", "NOP", false, false, "int", Integer.MAX_VALUE, 0);
		child.addAttributes(c1);
		child.addAttributes(c2);
		child.addAttributes(c3);
		
		parent.addChildTables(child);
		child.addParentTables(parent);
		
		main.addTables(parent);
		main.addTables(child);
		
		ir.addAllTables(parent);
		ir.addAllTables(child);
		ir.addPackages(main);
		
		Relation pc = new Relation("Parent-Child", "PAR_CHI");
		pc.setSourceTable(parent);
		pc.setDestinationTable(child);
		pc.addSourceKeys(a1);
		pc.addDestinationKeys(c1);
		
		ir.addRelations(pc);
		
		return ir;
	}
	
	private InformationResource prepareIRTestOK2() {
InformationResource ir = new InformationResource();
		
		Package main = new Package("MAIN", "Main");
		Package subp = new Package("SUB", "SubPackage");
		
		Table parent = new Table("Parent", "PAR");
		Attribute a1 = new Attribute("ParentName", "NAM", true, true, "char", 14, 0);
		Attribute a2 = new Attribute("Address", "ADD", false, true, "varchar", 128, 0);
		Attribute a3 = new Attribute("Age", "AGE", false, false, "int", Integer.MAX_VALUE, 0);
		Attribute a4 = new Attribute("Date of birth", "DOB", false, false, "date", 0, 0);
		parent.addAttributes(a1);
		parent.addAttributes(a2);
		parent.addAttributes(a3);
		parent.addAttributes(a4);
		
		Table child = new Table("Child", "CHI");
		Attribute c1 = new Attribute("ParentName", "NAM", true, true, "char", 14, 0);
		Attribute c2 = new Attribute("Surname", "SUR", true, true, "varchar", 128, 0);
		Attribute c3 = new Attribute("Number of presents", "NOP", false, false, "int", Integer.MAX_VALUE, 0);
		child.addAttributes(c1);
		child.addAttributes(c2);
		child.addAttributes(c3);
		
		parent.addChildTables(child);
		child.addParentTables(parent);
		
		main.addTables(parent);
		subp.addTables(child);
		
		main.addChildPackages(subp);
		
		ir.addAllTables(parent);
		ir.addAllTables(child);
		ir.addPackages(main);
		
		Relation pc = new Relation("Parent-Child", "PAR_CHI");
		pc.setSourceTable(parent);
		pc.setDestinationTable(child);
		pc.addSourceKeys(a1);
		pc.addDestinationKeys(c1);
		
		ir.addRelations(pc);
		
		return ir;
	}
	
	@Test
	public void testOK1() {
		InformationResource preparedIR = prepareIRTestOK1();
		DBParser parser = new DBParser();
		InformationResource parsedIR = null;
		try {
			parsedIR = parser.parseThis(DBIP, DBUSER, DBPASS);
		} catch (Exception e) {
			fail("Error while connecting to DB. Unable to parse.");
		}
		
		try {
			assertTrue(preparedIR.equals(parsedIR));
		} catch (Exception e) {
			fail("Error while comparing. Compare failed.");
		}
	}
	
	@Test
	public void testOK2() {
		InformationResource preparedIR = prepareIRTestOK2();
		DBParser parser = new DBParser();
		InformationResource parsedIR = null;
		try {
			parsedIR = parser.parseThis(DBIP, DBUSER, DBPASS);
		} catch (Exception e) {
			fail("Error while connecting to DB. Unable to parse.");
		}
		
		try {
			assertTrue(preparedIR.equals(parsedIR));
		} catch (Exception e) {
			fail("Error while comparing. Compare failed.");
		}
	}
	
	@Test
	public void testOK3() {
		InformationResource preparedIR = new InformationResource();
		DBParser parser = new DBParser();
		InformationResource parsedIR = null;
		try {
			parsedIR = parser.parseThis(DBIP, DBUSER, DBPASS);
		} catch (Exception e) {
			fail("Error while connecting to DB. Unable to parse.");
		}
		
		try {
			assertTrue(preparedIR.equals(parsedIR));
		} catch (Exception e) {
			fail("Error while comparing. Compare failed.");
		}
	}
	
	@Test(expected=Exception.class)
	public void testWrongCredentials() {
		DBParser parser = new DBParser();
		parser.parseThis("Something", "Something", "Dark Side");
	}
	
	@Test
	public void testMissingAttribute() {
		InformationResource preparedIR = prepareIRTestOK2();
		DBParser parser = new DBParser();
		InformationResource parsedIR = null;
		try {
			parsedIR = parser.parseThis(DBIP, DBUSER, DBPASS);
		} catch (Exception e) {
			fail("Error while connecting to DB. Unable to parse.");
		}
		
		try {
			assertFalse(preparedIR.equals(parsedIR));
		} catch (Exception e) {
			fail("Error while comparing. Compare failed.");
		}
	}
	
	@Test
	public void testPackageStructureChanged() {
		InformationResource preparedIR = prepareIRTestOK2();
		DBParser parser = new DBParser();
		InformationResource parsedIR = null;
		try {
			parsedIR = parser.parseThis(DBIP, DBUSER, DBPASS);
		} catch (Exception e) {
			fail("Error while connecting to DB. Unable to parse.");
		}
		
		try {
			assertFalse(preparedIR.equals(parsedIR));
		} catch (Exception e) {
			fail("Error while comparing. Compare failed.");
		}
	}

}
