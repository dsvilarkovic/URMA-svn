package view.editorFrame;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import controler.ChoseMetaSchemeAction;
import controler.CreateSchemaAction;
import controler.ImportSchemeAction;
import controler.SaveSchemeAction;
import controler.ValidateAction;

/**
 * Klasa toolbar za Editor, gde se nalaze sve akcije za Editor u vidu dugmica.
 * @author filip
 */
public class Toolbar extends JToolBar {

	private static final long serialVersionUID = 4297039877594590786L;
	JButton validateScheme = new JButton(new ValidateAction());
	JButton chooseMetaScheme = new JButton(new ChoseMetaSchemeAction());
	JButton createSchema = new JButton(new CreateSchemaAction());
	JButton scheme = new JButton(new ImportSchemeAction());
	JButton saveScheme = new JButton(new SaveSchemeAction());
	JTextField path = new JTextField();

	public Toolbar() {
		path.setEditable(false);
		path.setFocusable(false);
		
		add(createSchema);
		add(scheme);
		add(saveScheme);
		add(chooseMetaScheme);
		add(validateScheme);

		add(path);

		setVisible(true);
		validate();
	}

	public JTextField getPath() {
		return path;
	}
	public JButton getChoseMetaSchemeButton() {
		return chooseMetaScheme;
	}
}
