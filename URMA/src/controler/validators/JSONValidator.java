/**
 * 
 */
package controler.validators;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import app.App;
import controler.Open;

/**
 * @author filip
 *
 */
public class JSONValidator implements IValidator {

	@Override
	public void validate() {
		String path = App.INSTANCE.getEditorWindow().getToolbar().getPath().getText();
		JTextArea console = App.INSTANCE.getEditorWindow().getConsolePanel().getTextArea();

		JSONObject rawSchema = new JSONObject(new JSONTokener((String) new Open().openThis(path)));
		Schema schema = SchemaLoader.load(rawSchema);
		JSONObject input = null;

		try {
			input = new JSONObject(new JSONTokener(App.INSTANCE.getEditorWindow().getMainPanel().getTextArea().getText()));
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Your scheme is no good", "Validator message",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}

		String message = new String();
		message = "Validation messages:";
		try {
			schema.validate(input);
			message += " Validation passed!";
		} catch (ValidationException exeption) {
			for (int i = 0; i < exeption.getAllMessages().size(); i++) {
				message += "\n" + exeption.getAllMessages().get(i);
			}
		}
		console.setText(message);
		System.out.println(this.getClass() + ": Validate scheme action execution finnished");

	}

}
