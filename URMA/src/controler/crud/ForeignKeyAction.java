package controler.crud;

import java.awt.event.ActionEvent;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.AbstractAction;

import model.Table;
import view.CrudWindow;
import view.dialogs.ChooseReferencedCollumnValuesDialog;

public class ForeignKeyAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CrudWindow parentCaller;
	private Table table = null;
	
	public CrudWindow getParentCaller() {
		return parentCaller;
	}

	public void setParentCaller(CrudWindow parentCaller) {
		this.parentCaller = parentCaller;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public ForeignKeyAction(CrudWindow parentCaller, Table table, String attribute) {
		putValue(NAME, attribute);
		this.parentCaller = parentCaller;
		this.table = table;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		new ChooseReferencedCollumnValuesDialog(this.table, this);	
	}
	
	public void fillFields(Map<String, Object> selectedRow) {
		parentCaller.fillFields(selectedRow);
	}

}
