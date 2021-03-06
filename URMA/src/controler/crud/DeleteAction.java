package controler.crud;

import java.awt.event.ActionEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import app.App;
import controler.handlers.IHandler;
import model.Table;
import model.resourceFactory.IResourceFactory;
import view.table.TableModel;
import view.table.TablePanel;

/**
 * Akcija koja služi za brisanje reda u tabeli
 * @author Jelena
 *
 */

public class DeleteAction extends AbstractAction{
	private static final long serialVersionUID = 1L;
	public TablePanel parentCaller;
	private JTable tableCalling;
	
	/**
		Konstruktor akcije koja briše izabrano polje iz tabele		
		@author - Jelena
		@param parentCaller - tabela iz koje je pozvana akcija
	**/
	public DeleteAction(TablePanel parentCaller) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		putValue(NAME, resourceBundle.getString("button.delete"));
		this.parentCaller = parentCaller;
	}

	/**
		Akcija brisanja		
		@author - Jelena
		@param e - event
	**/
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.tableCalling  = App.INSTANCE.getTableMediator().getCalledTable(parentCaller);
		} catch (Exception e1) {
			ResourceBundle resourceBundle = App.INSTANCE.getResourceBundle();
			String errorActive = resourceBundle.getString("delete.action.error.activetable");
			String errorActiveAdditional = resourceBundle.getString("delete.action.error.tables");
			JOptionPane.showMessageDialog(null, errorActive, errorActiveAdditional,
										  JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//uzmi tabelu nad kojom radis
		Table table = ((TableModel) tableCalling.getModel()).getTable();
		
		Vector<Object> values = this.getSelectedRowValues();
		
		App.INSTANCE.setFactory(App.INSTANCE.getHandlerType());
		//App.INSTANCE.setFactory("db");
		IResourceFactory factory = App.INSTANCE.getFactory();
		IHandler handler = factory.createHandler();
		
		handler.delete(table, values);
	}
	
	private Vector<Object> getSelectedRowValues(){
		//TODO: @Dusan ispravi ovo za selected iz Paskala
		int selectedRow = tableCalling.getSelectedRow();
		@SuppressWarnings("unchecked")
		Vector<Object> v = (Vector<Object>) (((TableModel)  tableCalling.getModel()).getDataVector()).elementAt(selectedRow);
		return v;
	}
}
