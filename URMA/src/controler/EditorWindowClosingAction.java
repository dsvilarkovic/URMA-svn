package controler;

import java.awt.event.WindowAdapter;

import javax.swing.JOptionPane;

import app.App;

/**
 * 
 * @author filip
 *
 */
public class EditorWindowClosingAction extends WindowAdapter {
	@Override
	public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		if (!App.INSTANCE.getEditorWindow().getMainPanel().getTextArea().getText().equals(""))
			if (JOptionPane.showConfirmDialog(null, "Do you want to save file before exiting", "Window Closing?",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				new Save().saveAs(App.INSTANCE.getEditorWindow().getMainPanel().getTextArea().getText(), "sch");
				System.out.println(this.getClass() + ": Scheme saved during exit execution finished");
				System.exit(0);
			}
	}
}
