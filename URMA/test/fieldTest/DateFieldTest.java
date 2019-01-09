package fieldTest;

import static org.junit.Assert.assertEquals;

import org.jdatepicker.impl.JDatePickerImpl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import view.fieldFactory.DateField;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.swing.*")
@PrepareForTest(fullyQualifiedNames = "view.localizationManager.LocalizationManager")
//@PrepareForTest({DateField.class})
public class DateFieldTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testValidateFieldTrue() {
		DateField field = new DateField();
		((JDatePickerImpl) field.getField()).getModel().setDate(2009, 12,02);
		assertEquals(true, field.validateField(true, true, 1, 1));
	}
	
	@Test
	public void testValidateFieldDefault() {
		DateField field = new DateField();
		((JDatePickerImpl) field.getField()).getModel().setDate(2000, 0,1);
		assertEquals(false, field.validateField(true, true, 1, 1));
	}
	
	@Test
	public void testValidateFieldNotReq() {
		DateField field = new DateField();
		((JDatePickerImpl) field.getField()).getModel().setDate(2009, 12,02);
		assertEquals(true, field.validateField(false, false, 1, 1));
	}
	
	@Test
	public void testValidateFieldDefaultNotReqEmpty() {
		DateField field = new DateField();
		((JDatePickerImpl) field.getField()).getModel().setDate(2000, 0,1);
		assertEquals(true, field.validateField(false, false, 1, 1));
	}
}
