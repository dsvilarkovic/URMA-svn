package fieldTest;
import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.doReturn;

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
	public void testValidateFieldPrecisionTrue() {
		DoubleField field = new DoubleField();
		DoubleField copy = spy(field);
		doReturn(",").when(copy).splitChar();
		copy.setValue("12,22");
		assertEquals(true, copy.validateField(true, true, 12, 2));
	}
	
	@Test
	public void testValidateFieldPrecisionFalse() {
		DoubleField field = new DoubleField();
		DoubleField copy = spy(field);
		doReturn(",").when(copy).splitChar();
		copy.setValue("12,222");
		assertEquals(false, copy.validateField(true, true, 12, 2));
	}
	
	@Test
	public void testValidateFieldPrecisionTrue1() {
		DoubleField field = new DoubleField();
		DoubleField copy = spy(field);
		doReturn(".").when(copy).splitChar();
		copy.setValue("10,002.22");
		assertEquals(true, copy.validateField(true, true, 12, 2));
	}
	
	@Test
	public void testValidateFieldPrecisionFalse1() {
		DoubleField field = new DoubleField();
		DoubleField copy = spy(field);
		doReturn("\\.").when(copy).splitChar();
		copy.setValue("12.222");
		assertEquals(false, copy.validateField(true, true, 12, 2));
	}
	
	@Test
	public void testValidateFieldNegative() {
		DoubleField field = new DoubleField();
		DoubleField copy = spy(field);
		doReturn(",").when(copy).splitChar();
		copy.setValue("a");
		assertEquals(false, copy.validateField(true, true, 12, 2));
	}
	
	@Test
	public void testValidateFieldNegative1() {
		DoubleField field = new DoubleField();
		field.setValue("2a2");
		assertEquals(false, field.validateField(true, true, 12, 2));
	}
	
	@Test
	public void testValidateFieldNull() {
		DoubleField field = new DoubleField();
		DoubleField copy = spy(field);
		doReturn(",").when(copy).splitChar();
		copy.setValue(null);
		assertEquals(false, copy.validateField(true, false, 12, 2));
	}
	
	@Test
	public void testValidateFieldNull1() {
		DoubleField field = new DoubleField();
		DoubleField copy = spy(field);
		doReturn(",").when(copy).splitChar();
		copy.setValue(null);
		assertEquals(true, copy.validateField(false, false, 12, 2));
	}
}
