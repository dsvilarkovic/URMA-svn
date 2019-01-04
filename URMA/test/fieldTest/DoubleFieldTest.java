package fieldTest;
import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import view.fieldFactory.DoubleField;

public class DoubleFieldTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testValidateField() {
		DoubleField field = new DoubleField();
		DoubleField copy = spy(field);
		when(copy.splitChar()).thenReturn(",");
		copy.setValue("12,22");
		assertEquals(copy.validateField(true, true, 12, 2), true);
	}
}
