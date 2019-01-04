package fieldTest;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import view.fieldFactory.CharField;

public class CharFieldTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testValidateFieldMaxLenFalse() {
		CharField field = new CharField();
		field.setValue("test");
		assertEquals(field.validateField(true, true, 1, 1), false);
	}
	
	@Test
	public void testValidateFieldMaxLenFalse1() {
		CharField field = new CharField();
		field.setValue("test");
		assertEquals(field.validateField(true, true, 10, 1), false);
	}
	
	@Test
	public void testValidateFieldMaxLenTrue() {
		CharField field = new CharField();
		field.setValue("test");
		assertEquals(field.validateField(true, true, 4, 1), true);
	}
	
	@Test
	public void testValidateFieldRequiredTrue() {
		CharField field = new CharField();
		field.setValue("test");
		assertEquals(field.validateField(true, true, 4, 1), true);
	}
	
	@Test
	public void testValidateFieldRequiredFalse() {
		CharField field = new CharField();
		field.setValue(null);
		assertEquals(field.validateField(true, true, 4, 1), false);
	}
	
	@Test
	public void testValidateFieldRequiredTrue1() {
		CharField field = new CharField();
		field.setValue(null);
		assertEquals(field.validateField(false, true, 4, 1), false);
	}
	
	@Test
	public void testValidateFieldPKTrue() {
		CharField field = new CharField();
		field.setValue("test");
		assertEquals(field.validateField(true, true, 4, 1), true);
	}
	
	@Test
	public void testValidateFieldPKFalse() {
		CharField field = new CharField();
		field.setValue(null);
		assertEquals(field.validateField(true, true, 4, 1), false);
	}
	
	@Test
	public void testValidateFieldPKTrue1() {
		CharField field = new CharField();
		field.setValue(null);
		assertEquals(field.validateField(true, false, 4, 1), false);
	}
}
