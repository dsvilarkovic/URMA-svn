package controler.mainframe;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import app.App;

/**
 * Akcija za pokretanje Editora seme baze
 * @author filip 
 */
public class OpenSchemaEditor extends AbstractAction {

	private static final long serialVersionUID = 4574103938667763415L;

	public OpenSchemaEditor() {

		this(23);
		//TODO lokalizacija
		//putValue(NAME, MyApp.getInstance().getResourceBundle().getString("page.new"));
	}
	
	public OpenSchemaEditor(int prefferedSize) {
		Image newProjectImg = Toolkit.getDefaultToolkit().getImage("resources/openschema.png");
		Image scaledImage3 = newProjectImg.getScaledInstance(prefferedSize, prefferedSize, Image.SCALE_SMOOTH);
		putValue(SMALL_ICON, (new ImageIcon(scaledImage3)));
		putValue(NAME, "Open Schema Editor");
		
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
}
