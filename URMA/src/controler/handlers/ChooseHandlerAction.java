package controler.handlers;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import app.App;
import controler.parsers.IParser;
import model.InformationResource;
import model.resourceFactory.IResourceFactory;
import view.dialogs.ChooseSourceDialog;
import view.dialogs.DBParserConnectionDialog;

/**
 * Akcija za izbor hendlera
 * @author Dusan
*
 */
@SuppressWarnings("serial")
public class ChooseHandlerAction extends AbstractAction{

	/**
	 * Akcija za izbor hendlera
	 * @author Dusan
	*
	 */
	public ChooseHandlerAction() {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		(new ChooseSourceDialog()).setVisible(true);;		
	}

}
