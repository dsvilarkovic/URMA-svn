package controler.editorActions;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import app.App;
import controler.Open;
/**
 * Akcija za izbor seme sa kojom treba da se validira pomocu meta seme
 * @author filip
 */
public class ImportSchemeAction extends AbstractAction {

	private static final long serialVersionUID = 115275816323333594L;

	public ImportSchemeAction() {
		int prefferedSize = 23;
		putValue(NAME, "Import scheme");
		Image newProjectImg = Toolkit.getDefaultToolkit().getImage("resources/page.png");
		Image scaledImage3 = newProjectImg.getScaledInstance(prefferedSize, prefferedSize, Image.SCALE_SMOOTH);

		putValue(SMALL_ICON, (new ImageIcon(scaledImage3)));
		putValue(MNEMONIC_KEY, KeyEvent.VK_Z);
	}

	/**
	 * Akcija koja se izvrsi klikom na dugme, pokretanje dijaloga za izbor seme
	 * i ispis njenog sadrzaja u editor
	 * @author filip
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = null;
		try {
		 str = (String) new Open().open("sch/json/txt/text/doc/docx/odt");
		}
		catch(NullPointerException npe) {
			return; //vraca se funkcija ne treba je izvrsavati
		}
		App.INSTANCE.getEditorWindow().getMainPanel().getTextArea().setText(str);
		System.out.println(this.getClass() + ": Scheme imported action execution finnished");
	}

}
