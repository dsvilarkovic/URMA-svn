package controler.mainFrameActions;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import app.App;
import view.localizationManager.LocalizationObserver;

/**
 * Akcija za pokretanje Editora seme baze
 * @author filip 
 */
public class OpenSchemaEditor extends AbstractAction implements LocalizationObserver{

	private static final long serialVersionUID = 4574103938667763415L;

	public OpenSchemaEditor() {

		this(23);
	}
	
	public OpenSchemaEditor(int prefferedSize) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		App.INSTANCE.getLocalizationManager().addLocalizationObserver(this);
		Image newProjectImg = Toolkit.getDefaultToolkit().getImage("resources/openschema.png");
		Image scaledImage3 = newProjectImg.getScaledInstance(prefferedSize, prefferedSize, Image.SCALE_SMOOTH);
		putValue(SMALL_ICON, (new ImageIcon(scaledImage3)));
		putValue(NAME, resourceBundle.getString("schema.open"));
		
		putValue(MNEMONIC_KEY, KeyEvent.VK_E);
		putValue(ACCELERATOR_KEY, 
				KeyStroke.getKeyStroke(
						KeyEvent.VK_E, 
						KeyEvent.CTRL_DOWN_MASK));
	}

	/**
	 * Akcija koja se izvrsi klikom na dugme, editor postaje vidljiv
	 * @author filip
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		App.INSTANCE.getEditorWindow().setVisible(true);
	}

	@Override
	public void updateLanguage(String language) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		putValue(NAME, resourceBundle.getString("schema.open"));
	}
}
