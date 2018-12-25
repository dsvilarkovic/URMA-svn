package controler.tableActions;

import java.awt.event.ActionEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import app.App;
import model.Table;
import view.CrudWindow;
import view.table.TableModel;
import view.table.TablePanel;

/**
 * Akcija koja služi za kreiranje CRUD prozora koja će se koristiti za CRUD operacije
 * @author Dusan
 *
 */
public class CreateCrudWindowAction extends AbstractAction {

	/**
	 * Tabela za koju se odgovarajuca CRUD operacija poziva
	 */
	private JTable tableCalling = null;
	/**
	 * Ime buttona za koji ce se ovo pozivati
	 */
	private String buttonName = null;	
	
	private TablePanel panelCalling = null;
	/**
	 * Konstruktor akcija koja služi za kreiranje CRUD prozora koja će se koristiti za CRUD operacije
	 * @param tableCalling - tabela za koju ce se pozvati CRUD prozor
	 * @param buttonName - ime dugmeta za koje se poziva CRUD
	 */
	public CreateCrudWindowAction(TablePanel panelCalling, String buttonName) {
		putValue(NAME, buttonName);
		//uzmi tabelu i sacuvaj referencu 
		this.panelCalling = panelCalling;
		//uzmi ime dugmeta
		this.buttonName = buttonName;		
	}

	
	
	/**
	 * Updateovana akcija, ova ce raditi sa medijatorom da nadje za koji aktivan
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//uzmi pogled na tabelu nad kojom se vrse operacije

		/**
		 *Nova funkcija za implementaciju koda
		 */
		
		System.out.println("Pelepa");
		
		
		
		
		try {
			this.tableCalling  = App.INSTANCE.getTableMediator().getCalledTable(panelCalling);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Error, no table active error", "Error with tables",
										  JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//uzmi tabelu nad kojom radis
		Table table = ((TableModel) tableCalling.getModel()).getTable();
		
		try {		
			//uzmi buttonName u switch		
//			switch(buttonName) {
//			//vidi kog je tipa dugme
//			case "Add":
//				addRow(table);
//				break;
//			case "Remove":
//				removeRow(table);
//				break;
//			case "Update":
//				updateRow(table);
//				break;
//			case "Search":
//				searchRows(table);
//				break;
//			}
			ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
			String add = resourceBundle.getString("table.button.add");
			String remove = resourceBundle.getString("table.button.remove");
			String update = resourceBundle.getString("table.button.update");
			String search = resourceBundle.getString("table.button.search");
			if(buttonName.equals(add)) {
				addRow(table);
				return;
			}
			if(buttonName.equals(remove)) {
				removeRow(table);
				return;
			}
			if(buttonName.equals(update)) {
				updateRow(table);
				return;
			}
			if(buttonName.equals(search)) {
				searchRows(table);
			}
		}
		catch (ArrayIndexOutOfBoundsException indexException) {
			JOptionPane.showMessageDialog(null, "Error, there is no table defined for action", "Error",
											JOptionPane.ERROR_MESSAGE);
		}
	}
	


	/**
	 * Akcija za pretragu u tabeli
	 * @param table
	 */
	private void searchRows(Table table) {
		(new CrudWindow(table, true)).setVisible(true);
	}
	
	/**
	 * Metoda za uklanjanje torke
	 * @param table
	 */
	private void removeRow(Table table) {
		//TODO: akcija za uklanjanje torke
	}

	/**
	 * Akcija za azuriranje torke
	 * @param table
	 */
	private void updateRow(Table table) {
		Vector<Object> values = this.getSelectedRowValues();
		(new CrudWindow(table, values)).setVisible(true);
	}
	
	/**
	 * Akcija za dodavanje torke
	 * @param table
	 */
	private void addRow(Table table) {
		//TODO: ubaciti kod
		(new CrudWindow(table, false)).setVisible(true);;
	}

	/**
	 * Vraca vrednsot selektovanog reda
	 * @return
	 */
	private Vector<Object> getSelectedRowValues(){
		int selectedRow = tableCalling.getSelectedRow();
		Vector<Object> values = (Vector<Object>) (((TableModel)  tableCalling.getModel()).getDataVector()).elementAt(selectedRow);
		return values;
	}

}
