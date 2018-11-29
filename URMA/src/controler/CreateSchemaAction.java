package controler;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import app.App;

/**
 * 
 * @author Dusan
 *
 */
public class CreateSchemaAction extends AbstractAction {
	
	private static final long serialVersionUID = -1109003278878012826L;

	/**
	 * Konstruktor za kreiranje seme
	 */
	public CreateSchemaAction() {
		int prefferedSize = 23;
		putValue(NAME, "Create new schema");
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
			int a=JOptionPane.showConfirmDialog(null,"There has been another schema opened in editor. Would you like to save it?");  
			if(a==JOptionPane.YES_OPTION){  
				new Save().saveAs(App.INSTANCE.getEditorWindow().getMainPanel().getTextArea().getText(), "sch");
			} 
		}
		
		App.INSTANCE.getEditorWindow().getMainPanel().getTextArea().setText("");
		System.out.println(this.getClass() + ": New scheme action execution finnished");
	}

}
