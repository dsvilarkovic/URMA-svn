package parserTest;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import controler.parsers.JSONParser;
import model.Attribute;
import model.InformationResource;
import model.Package;
import model.Relation;
import model.Table;

public class JSONParserTest {
	
	private final static String TEST_FOLDER_NAME = "testSchemas";
	private final static String TEST_OK1_PATH = System.getProperty("user.dir") + File.separator + TEST_FOLDER_NAME + File.separator + "testok1.json";
	private final static String TEST_OK2_PATH = System.getProperty("user.dir") + File.separator + TEST_FOLDER_NAME + File.separator + "testok2.json";
	private final static String TEST_OK3_PATH = System.getProperty("user.dir") + File.separator + TEST_FOLDER_NAME + File.separator + "testok3.json";
	private final static String TEST_FAIL1_PATH = System.getProperty("user.dir") + File.separator + TEST_FOLDER_NAME + File.separator + "testEmptyJSON.json";
	private final static String TEST_FAIL2_PATH = System.getProperty("user.dir") + File.separator + TEST_FOLDER_NAME + File.separator + "testNoPackages.json";
	private final static String TEST_FAIL3_PATH = System.getProperty("user.dir") + File.separator + TEST_FOLDER_NAME + File.separator + "testNoRelations.json";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private InformationResource prepareIRTestOk1() {
		InformationResource ir = new InformationResource();
		
		Package main = new Package("MAIN", "main");
		
		Table parent = new Table("Parent", "PAR");
		Attribute a1 = new Attribute("ParentName", "NAM", true, true, "char", 14, 0);
		Attribute a2 = new Attribute("Address", "ADD", false, true, "varchar", 128, 0);
		Attribute a3 = new Attribute("Age", "AGE", false, false, "int", 5, 0);
		Attribute a4 = new Attribute("Date of birth", "DOB", false, false, "date", 0, 0);
		parent.addAttributes(a1);
		parent.addAttributes(a2);
		parent.addAttributes(a3);
		parent.addAttributes(a4);
		
		Table child = new Table("Child", "CHI");
		Attribute c1 = new Attribute("ChildName", "NAM", true, true, "char", 14, 0);
		Attribute c2 = new Attribute("Surname", "SUR", true, true, "varchar", 128, 0);
		Attribute c3 = new Attribute("Number of presents", "NOP", false, false, "int", 5, 0);
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
		
		Relation pc = new Relation("Parent-Child", "P-C");
		pc.setSourceTable(parent);
		pc.setDestinationTable(child);
		pc.addSourceKeys(a1);
		pc.addDestinationKeys(c1);
		
		ir.addRelations(pc);
		
		return ir;
	}
	
	private InformationResource prepareIRTestOk2() {
		InformationResource ir = new InformationResource();
		
		Package main = new Package("MAIN", "main");
		
		Table parent = new Table("Parent", "PAR");
		Attribute a1 = new Attribute("ParentName", "NAM", true, true, "char", 14, 0);
		Attribute a2 = new Attribute("Address", "ADD", false, true, "varchar", 128, 0);
		Attribute a3 = new Attribute("Age", "AGE", false, false, "int", 5, 0);
		Attribute a4 = new Attribute("Date of birth", "DOB", false, false, "date", 0, 0);
		parent.addAttributes(a1);
		parent.addAttributes(a2);
		parent.addAttributes(a3);
		parent.addAttributes(a4);
		
		main.addTables(parent);
		
		Package sub = new Package("SUB", "SubPackage");
		
		Table child = new Table("Child", "CHI");
		Attribute c1 = new Attribute("ChildName", "NAM", true, true, "char", 14, 0);
		Attribute c2 = new Attribute("Surname", "SUR", true, true, "varchar", 128, 0);
		Attribute c3 = new Attribute("Number of presents", "NOP", false, false, "int", 5, 0);
		child.addAttributes(c1);
		child.addAttributes(c2);
		child.addAttributes(c3);
		
		sub.addTables(child);
		main.addChildPackages(sub);
		
		parent.addChildTables(child);
		child.addParentTables(parent);
		
		
		
		ir.addAllTables(parent);
		ir.addAllTables(child);
		ir.addPackages(main);
		
		Relation pc = new Relation("Parent-Child", "P-C");
		pc.setSourceTable(parent);
		pc.setDestinationTable(child);
		pc.addSourceKeys(a1);
		pc.addDestinationKeys(c1);
		
		ir.addRelations(pc);
		
		return ir;
	}
	
	private InformationResource prepareIRTestOk3() {
		InformationResource ir = new InformationResource();
		
		return ir;
	}
	
	@Test
	public void testOK1() {
		InformationResource preparedIR = prepareIRTestOk1();
		JSONParser parser = new JSONParser();
		InformationResource parsedIR = null;
		try {
			parsedIR = parser.parseThis(TEST_OK1_PATH);
		} catch (Exception e) {
			fail("Error while parsing. Unable to parse.");
		}
		
		try {
			assertTrue(preparedIR.equals(parsedIR));
		} catch (Exception e) {
			fail("Error while comparing. Compare failed.");
		}
	}
	
	@Test
	public void testOK2() {
		InformationResource preparedIR = prepareIRTestOk2();
		JSONParser parser = new JSONParser();
		InformationResource parsedIR = null;
		try {
			parsedIR = parser.parseThis(TEST_OK2_PATH);
		} catch (Exception e) {
			fail("Error while parsing. Unable to parse.");
		}
		
		try {
			assertTrue(preparedIR.equals(parsedIR));
		} catch (Exception e) {
			fail("Error while comparing. Compare failed.");
		}
	}
	
	@Test
	public void testOK3() {
		InformationResource preparedIR = prepareIRTestOk3();
		JSONParser parser = new JSONParser();
		InformationResource parsedIR = null;
		try {
			parsedIR = parser.parseThis(TEST_OK3_PATH);
		} catch (Exception e) {
			fail("Error while parsing. Unable to parse.");
		}
		
		try {
			assertTrue(preparedIR.equals(parsedIR));
		} catch (Exception e) {
			fail("Error while comparing. Compare failed.");
		}
	}
	
	@Test
	public void testEmptyJSON() {
		JSONParser parser = new JSONParser();
		InformationResource parsedIR = null;
		try {
			parsedIR = parser.parseThis(TEST_FAIL1_PATH);
		} catch (Exception e) {
			fail("Error while parsing. Unable to parse.");
		}
		
		try {
			assertNull(parsedIR);
		} catch (Exception e) {
			fail("Did not return null.");
		}
	}
	
	@Test
	public void testNoPackages() {
		JSONParser parser = new JSONParser();
		InformationResource parsedIR = null;
		try {
			parsedIR = parser.parseThis(TEST_FAIL2_PATH);
		} catch (Exception e) {
			fail("Error while parsing. Unable to parse.");
		}
		
		try {
			assertNull(parsedIR);
		} catch (Exception e) {
			fail("Did not return null.");
		}
	}
	
	@Test
	public void testNoRelations() {
		JSONParser parser = new JSONParser();
		InformationResource parsedIR = null;
		try {
			parsedIR = parser.parseThis(TEST_FAIL3_PATH);
		} catch (Exception e) {
			fail("Error while parsing. Unable to parse.");
		}
		
		try {
			assertNull(parsedIR);
		} catch (Exception e) {
			fail("Did not return null.");
		}
	}

}
