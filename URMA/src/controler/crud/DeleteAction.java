package controler.crud;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import app.App;
import controler.handlers.IHandler;
import model.Table;
import model.resourceFactory.IResourceFactory;
import view.CrudWindow;
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
	
	public DeleteAction(TablePanel parentCaller) {
		putValue(NAME, "Delete");
		this.parentCaller = parentCaller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.tableCalling  = App.INSTANCE.getTableMediator().getCalledTable(parentCaller);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Error, no table active error", "Error with tables",
										  JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//uzmi tabelu nad kojom radis
		Table table = ((TableModel) tableCalling.getModel()).getTable();
		
		Vector<Object> values = this.getSelectedRowValues();
		
		App.INSTANCE.setFactory("db");
		IResourceFactory factory = App.INSTANCE.getFactory();
		IHandler handler = factory.createHandler();
		
		handler.delete(table, values);
	}
	
	private Vector<Object> getSelectedRowValues(){
		int selectedRow = tableCalling.getSelectedRow();
		Vector<Object> v = (Vector<Object>) (((TableModel)  tableCalling.getModel()).getDataVector()).elementAt(selectedRow);
		return v;
	}
}
