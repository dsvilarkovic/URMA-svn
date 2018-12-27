package controler.crud;

import java.awt.event.ActionEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;

import model.Table;
import view.CrudWindow;
import view.dialogs.ChooseReferencedCollumnValuesDialog;

public class ForeignKeyAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CrudWindow parentCaller;
	public Table table = null;
	
	public ForeignKeyAction(CrudWindow parentCaller, Table table, String attribute) {
		putValue(NAME, attribute);
		this.parentCaller = parentCaller;
		this.table = table;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		ChooseReferencedCollumnValuesDialog testDialog = new ChooseReferencedCollumnValuesDialog(this.table, this);	
	}
	
	public void fillFields() {
		System.out.println("USPEO");
	}

}
