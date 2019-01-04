package fieldTest;
import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.spy;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import view.fieldFactory.IntegerField;

public class IntegerFieldTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testValidateFieldPositive() {
		IntegerField field = new IntegerField();
		field.setValue("12");
		assertEquals(field.validateField(false, true, 12, 2), true);
	}
	
	@Test
	public void testValidateFieldNegative() {
		IntegerField field = new IntegerField();
		field.setValue("a");
		assertEquals(field.validateField(true, true, 12, 2), false);
	}
	
	@Test
	public void testValidateFieldNegative1() {
		IntegerField field = new IntegerField();
		field.setValue("2a2");
		assertEquals(field.validateField(true, true, 12, 2), false);
	}
	
	@Test
	public void testValidateFieldNull() {
		IntegerField field = new IntegerField();
		field.setValue(null);
		assertEquals(field.validateField(true, false, 12, 2), false);
	}
	
	@Test
	public void testValidateFieldNull1() {
		IntegerField field = new IntegerField();
		IntegerField copy = spy(field);
		copy.setValue(null);
		assertEquals(copy.validateField(false, false, 12, 2), true);
	}
}
