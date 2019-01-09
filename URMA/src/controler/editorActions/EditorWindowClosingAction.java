package controler.editorActions;

import java.awt.event.WindowAdapter;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import app.App;
import controler.Save;

/**
 * Akcija koja se desava prilikom pokretanja zatvaranja editora.
 * Nudi korisniku mogucnost da snimi semu ako je zaboravio to da uradi.
 * @author filip
 */
public class EditorWindowClosingAction extends WindowAdapter {
	@Override
	public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		if (!App.INSTANCE.getEditorWindow().getMainPanel().getTextArea().getText().equals(""))
			if (JOptionPane.showConfirmDialog(null, resourceBundle.getString("message.saveBefore"), resourceBundle.getString("menu.close"),
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				new Save().saveAs(App.INSTANCE.getEditorWindow().getMainPanel().getTextArea().getText(), "sch");
				System.out.println(this.getClass() + ": Scheme saved during exit execution finished");
				System.exit(0);
			}
	}
}
