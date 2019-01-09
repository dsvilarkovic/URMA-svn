package controler.editorActions;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import app.App;
import controler.Open;
/**
 * Akcija za izbor meta seme sa kojom ce se vrsiti validacija
 * @author filip
 */
public class ChoseMetaSchemeAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public ChoseMetaSchemeAction() {
		int prefferedSize = 23;
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		putValue(NAME, resourceBundle.getString("button.chooseMetaSch"));
		Image newProjectImg = Toolkit.getDefaultToolkit().getImage("resources/document.png");
		Image scaledImage3 = newProjectImg.getScaledInstance(prefferedSize, prefferedSize, Image.SCALE_SMOOTH);

		putValue(SMALL_ICON, (new ImageIcon(scaledImage3)));
		putValue(MNEMONIC_KEY, KeyEvent.VK_Z);
	}

	/**
	 * Akcija koja se izvrsi klikom na dugme, pokretanje dijaloga za izbor meta seme
	 * i pamcenje izabrane meta seme
	 * @author filip
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String path = new Open().getPath("sch/json");
		JTextField path_field = App.INSTANCE.getEditorWindow().getToolbar().getPath();
		path_field.setText(path);
		System.out.println(this.getClass() + ": Chose meta-scheme action execution finnished");
	}

}