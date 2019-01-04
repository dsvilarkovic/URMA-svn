package fieldTest;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import view.fieldFactory.BooleanField;

public class BooleanFieldTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testValidateField() {
		BooleanField field = new BooleanField();
		field.setValue(false);
		assertEquals(field.validateField(true, true, 1, 1), true);
	}
	
	@Test
	public void testValidateField1() {
		BooleanField field = new BooleanField();
		field.setValue(true);
		assertEquals(field.validateField(false, false, 1, 1), true);
	}
}
