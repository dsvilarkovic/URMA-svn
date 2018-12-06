package controler;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import org.apache.commons.io.FilenameUtils;

import app.App;
import controler.validators.IValidator;

public class ValidateAction extends AbstractAction {

	private static final long serialVersionUID = -3285634366755856783L;

	public ValidateAction() {
		int prefferedSize = 23;
		putValue(NAME, "Validate");
		Image newProjectImg = Toolkit.getDefaultToolkit().getImage("resources/validate.png");
		Image scaledImage3 = newProjectImg.getScaledInstance(prefferedSize, prefferedSize, Image.SCALE_SMOOTH);

		putValue(SMALL_ICON, (new ImageIcon(scaledImage3)));
		putValue(MNEMONIC_KEY, KeyEvent.VK_Z);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String path = App.INSTANCE.getEditorWindow().getToolbar().getPath().getText();
		if (path.equals("")) {
			//TODO Filip: pitaj Peru za ovo
			//App.INSTANCE.getApp().getToolbar().getChoseMetaSchemeButton().doClick();
			
			path = new Open().getPath("sch/json");
			JTextField path_field = App.INSTANCE.getEditorWindow().getToolbar().getPath();
			path_field.setText(path);
			
			return;
		}
		App.INSTANCE.setFactory(FilenameUtils.getExtension(App.INSTANCE.getEditorWindow().getToolbar().getPath().getText()));

		IValidator validator = App.INSTANCE.getFactory().createValidator();
		validator.validate();

	}

}
