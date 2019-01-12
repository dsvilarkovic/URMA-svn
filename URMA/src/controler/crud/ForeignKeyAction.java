package controler.crud;

import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.AbstractAction;

import model.Table;
import view.CrudWindow;
import view.dialogs.ChooseReferencedCollumnValuesDialog;

/**
 * Akcija za popunjavanje polja iz foreign key-a
 * @author jelena
 */
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

	/**
		Konstruktor akcije koja se poziva da bi se popunila polja na osnovu strane tabele		
		@author - Jelena
		@param parentCaller - prozor iz kojeg je pozvana akcija
		@param table - tabela za koju se poziva akcija
		@param attribute - naziv akcije
	**/
	public ForeignKeyAction(CrudWindow parentCaller, Table table, String attribute) {
		putValue(NAME, attribute);
		this.parentCaller = parentCaller;
		this.table = table;
	}
	
	/**
		Akcija koja se poziva da bi se popunila polja na osnovu strane tabele		
		@author - Jelena
		@param arg0 - event
	**/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		new ChooseReferencedCollumnValuesDialog(this.table, this);	
	}
	
	/**
		Metoda koja popunjava polja na osnovu stranog kljuca		
		@author - Jelena
		@param selectedRow - mapa&lt;Polje,Vrednost&gt;
	**/
	public void fillFields(Map<String, Object> selectedRow) {
		parentCaller.fillFields(selectedRow);
	}

}
