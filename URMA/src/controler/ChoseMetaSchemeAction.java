package controler;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import app.App;

public class ChoseMetaSchemeAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public ChoseMetaSchemeAction() {
		int prefferedSize = 23;
		putValue(NAME, "Chose meta-scheme");
		Image newProjectImg = Toolkit.getDefaultToolkit().getImage("resources/document.png");
		Image scaledImage3 = newProjectImg.getScaledInstance(prefferedSize, prefferedSize, Image.SCALE_SMOOTH);

		putValue(SMALL_ICON, (new ImageIcon(scaledImage3)));
		putValue(MNEMONIC_KEY, KeyEvent.VK_Z);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String path = new Open().getPath("sch/json");
		JTextField path_field = App.INSTANCE.getEditorWindow().getToolbar().getPath();
		path_field.setText(path);
		System.out.println(this.getClass() + ": Chose meta-scheme action execution finnished");
	}

}
