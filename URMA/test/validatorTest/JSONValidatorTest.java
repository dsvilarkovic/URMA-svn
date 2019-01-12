package validatorTest;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import app.App;
import controler.Open;
import controler.validators.JSONValidator;

public class JSONValidatorTest {

	private JSONValidator jsonValidator;
	@Before
	public void setUp() throws Exception {
		//napravi validator
		jsonValidator = new JSONValidator();
		//startuj aplikaciju
		App.INSTANCE.start();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	/**
	 * Testira se validator za dobre unose
	 */
	public void positiveValidationTest() {
		//podesi aplikacijski filepath za meta semu
		App.INSTANCE.getEditorWindow().getToolbar().getPath().setText("resources/ourMetaSchema.json");
		//podesi aplikacijski text za semu
		String str = null;
		try {
			str = (String) new Open().openThis("resources/ourSchema.json");
		}
		catch(NullPointerException npe) {
			return; //vraca se funkcija ne treba je izvrsavati
		}
		App.INSTANCE.getEditorWindow().getMainPanel().getTextArea().setText(str);
		
		boolean assertation = jsonValidator.validate();
		assertEquals(true, assertation);
	}
	
	@Test
	/**
	 * Provera za negativnu semu, izbaceni obavezni atributi relationa
	 */
	public void negativeValidationTest() {
		//podesi aplikacijski filepath za meta semu
		App.INSTANCE.getEditorWindow().getToolbar().getPath().setText("resources/ourMetaSchema.json");
		//podesi aplikacijski text za semu
		String str = null;
		try {
			//obrisani kljucevi u relaciji
			str = (String) new Open().openThis("resources/badSchema.json");
		}
		catch(NullPointerException npe) {
			return; //vraca se funkcija ne treba je izvrsavati
		}
		App.INSTANCE.getEditorWindow().getMainPanel().getTextArea().setText(str);
		
		boolean assertation = jsonValidator.validate();
		assertEquals(false, assertation);
	}
	
	@Test
	/**
	 * Provera kad je non-JSON tekst upucen na editor
	 */
	public void totallyNegativeValidationTest() {
		//podesi aplikacijski filepath za meta semu
		App.INSTANCE.getEditorWindow().getToolbar().getPath().setText("resources/ourMetaSchema.json");
		//podesi aplikacijski text za semu
		//totalno pogresan
		String str = "[dcackcaiicd";
		App.INSTANCE.getEditorWindow().getMainPanel().getTextArea().setText(str);
		
		boolean assertation = jsonValidator.validate();
		assertEquals(false, assertation);

	}

}
