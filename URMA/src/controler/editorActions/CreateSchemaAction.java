package controler.editorActions;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import app.App;
import controler.Save;

public class CreateSchemaAction extends AbstractAction {
	
	private static final long serialVersionUID = -1109003278878012826L;

	/**
	 * Konstruktor za kreiranje seme
	 * 
	 */
	public CreateSchemaAction() {
		int prefferedSize = 23;
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		putValue(NAME, resourceBundle.getString("button.createScheme"));
		Image newProjectImg = Toolkit.getDefaultToolkit().getImage("resources/new_schema.png");
		Image scaledImage3 = newProjectImg.getScaledInstance(prefferedSize, prefferedSize, Image.SCALE_SMOOTH);

		putValue(SMALL_ICON, (new ImageIcon(scaledImage3)));
		putValue(MNEMONIC_KEY, KeyEvent.VK_S);
	}

	@Override
	/**
	 * Sluzi da se proveri da li je nesto uvezeno u editor, ako jeste, ponudi da snimi, ili da samo pregazi
	 */
	public void actionPerformed(ActionEvent e) {
		String textArea = App.INSTANCE.getEditorWindow().getMainPanel().getTextArea().getText().toString();
		if(textArea.isEmpty() != true) {
			ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
			int a=JOptionPane.showConfirmDialog(null,resourceBundle.getString("message.saveBefore"));  
			if(a==JOptionPane.YES_OPTION){  
				new Save().saveAs(App.INSTANCE.getEditorWindow().getMainPanel().getTextArea().getText(), "sch");
			} 
		}
		
		App.INSTANCE.getEditorWindow().getMainPanel().getTextArea().setText("");
		System.out.println(this.getClass() + ": New scheme action execution finnished");
	}

}
