package controler.editorActions;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import app.App;
import controler.Open;

/**
 * Akcija za izbor meta seme sa kojom ce se vrsiti validacija (nisam siguran da se koristi)
 * @author filip
 */
public class ChoseMetaSchemeAndValidateAction extends AbstractAction {

	private static final long serialVersionUID = 8475599976877544200L;

	public ChoseMetaSchemeAndValidateAction() {
		int prefferedSize = 23;
		putValue(NAME, "Chose meta-scheme and validate");
		Image newProjectImg = Toolkit.getDefaultToolkit().getImage("resources/document.png");
		Image scaledImage3 = newProjectImg.getScaledInstance(prefferedSize, prefferedSize, Image.SCALE_SMOOTH);

		putValue(SMALL_ICON, (new ImageIcon(scaledImage3)));
		putValue(MNEMONIC_KEY, KeyEvent.VK_Z);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JSONObject rawSchema = new JSONObject(new JSONTokener((String) new Open().open("sch")));
		Schema schema = SchemaLoader.load(rawSchema);
		
		JSONObject input = new JSONObject(new JSONTokener(App.INSTANCE.getEditorWindow().getMainPanel().getTextArea().getText()));
		
		System.out.println("Validation messages:");
		try {
			schema.validate(input);
		} catch (ValidationException exeption) {
			System.out.println(exeption.getAllMessages());
		}
	}
}
