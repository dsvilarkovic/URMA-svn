package fieldTest;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import view.fieldFactory.VarcharField;

public class VarcharFieldTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testValidateFieldMaxLenFalse() {
		VarcharField field = new VarcharField();
		field.setValue("test");
		assertEquals(field.validateField(true, true, 1, 1), false);
	}
	
	@Test
	public void testValidateFieldMaxLenTrue() {
		VarcharField field = new VarcharField();
		field.setValue("test");
		assertEquals(field.validateField(true, true, 10, 1), true);
	}
	
	@Test
	public void testValidateFieldRequiredTrue() {
		VarcharField field = new VarcharField();
		field.setValue("test");
		assertEquals(field.validateField(true, true, 10, 1), true);
	}
	
	@Test
	public void testValidateFieldRequiredFalse() {
		VarcharField field = new VarcharField();
		field.setValue(null);
		assertEquals(field.validateField(true, true, 10, 1), false);
	}
	
	@Test
	public void testValidateFieldRequiredTrue1() {
		VarcharField field = new VarcharField();
		field.setValue(null);
		assertEquals(field.validateField(false, true, 10, 1), false);
	}
	
	@Test
	public void testValidateFieldPKTrue() {
		VarcharField field = new VarcharField();
		field.setValue("test");
		assertEquals(field.validateField(true, true, 10, 1), true);
	}
	
	@Test
	public void testValidateFieldPKFalse() {
		VarcharField field = new VarcharField();
		field.setValue(null);
		assertEquals(field.validateField(true, true, 10, 1), false);
	}
	
	@Test
	public void testValidateFieldPKTrue1() {
		VarcharField field = new VarcharField();
		field.setValue(null);
		assertEquals(field.validateField(true, false, 10, 1), false);
	}
}
