package controler;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import app.App;
/**
 * 
 * @author filip
 *
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

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = (String) new Open().open("sch/json/txt/text/doc/docx/odt");
		App.INSTANCE.getEditorWindow().getMainPanel().getTextArea().setText(str);
		System.out.println(this.getClass() + ": Scheme imported action execution finnished");
	}

}
