package fieldTest;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import view.fieldFactory.DateField;

public class DateFieldTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testValidateField() {
		DateField field = new DateField();
//		field.setValue("2018-12-18");
		assertEquals(field.validateField(true, true, 1, 1), false);
	}
}
