package controler.editorActions;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.apache.commons.io.FilenameUtils;

import app.App;
import controler.validators.IValidator;

/**
 * Akcija za validacije seme u editoru izabranom meta semom ako nema izbrane meta seme
 * inicira se biranje meta seme
 * @author filip
 */
public class ValidateAction extends AbstractAction {

	private static final long serialVersionUID = -3285634366755856783L;

	public ValidateAction() {
		int prefferedSize = 23;
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		putValue(NAME, resourceBundle.getString("button.validate"));
		Image newProjectImg = Toolkit.getDefaultToolkit().getImage("resources/validate.png");
		Image scaledImage3 = newProjectImg.getScaledInstance(prefferedSize, prefferedSize, Image.SCALE_SMOOTH);

		putValue(SMALL_ICON, (new ImageIcon(scaledImage3)));
		putValue(MNEMONIC_KEY, KeyEvent.VK_Z);
	}

	/**
	 * Akcija koja se izvrsi klikom na dugme, pokretanje dijaloga za izbor meta seme
	 * ako nije izabrana i pokretanje validacije
	 * @author filip
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String path = App.INSTANCE.getEditorWindow().getToolbar().getPath().getText();
		if (path.equals(""))
			App.INSTANCE.getEditorWindow().getToolbar().getChoseMetaSchemeButton().doClick();

		App.INSTANCE.setFactory(FilenameUtils.getExtension(App.INSTANCE.getEditorWindow().getToolbar().getPath().getText()));

		IValidator validator = App.INSTANCE.getFactory().createValidator();
		validator.validate();

	}

}
