package controler;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import app.App;

/**
 * Akcija za snimanje sadrzaja u editoru
 * @author filip
 */
public class SaveSchemeAction extends AbstractAction {

	private static final long serialVersionUID = 811594364295494198L;

	public SaveSchemeAction() {
		int prefferedSize = 23;
		putValue(NAME, "Save scheme");
		Image newProjectImg = Toolkit.getDefaultToolkit().getImage("resources/save.png");
		Image scaledImage3 = newProjectImg.getScaledInstance(prefferedSize, prefferedSize, Image.SCALE_SMOOTH);

		putValue(SMALL_ICON, (new ImageIcon(scaledImage3)));
		putValue(MNEMONIC_KEY, KeyEvent.VK_Z);
	}

	/**
	 * Akcija koja se izvrsi klikom na dugme, pokretanje dijaloga za izbor
	 * lokacije seme i snimanje seme
	 * @author filip
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		new Save().saveAs(App.INSTANCE.getEditorWindow().getMainPanel().getTextArea().getText(), "sch");
		System.out.println(this.getClass() + ": Scheme saved action execution finished");
	}
}
