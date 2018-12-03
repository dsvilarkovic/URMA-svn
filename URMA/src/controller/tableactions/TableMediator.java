package controller.tableactions;

import java.util.List;

import javax.swing.JOptionPane;

import app.App;
import model.Table;
import view.table.ChildTablePanel;
import view.table.ParentTablePanel;
import view.table.TableModel;

/**
 * Medijator za promenu modela u panelima predstavljenim
 * @author Dusan
 *
 */
public class TableMediator {

	private ChildTablePanel childTablePanel = null;
	private ParentTablePanel parentTablePanel = null;
	/**
	 * Medijator za promenu modela u panelima predstavljenim
	 */
	public TableMediator() {
	}
	
	public void promoteChild() {
		
		setPanels();
		//TODO @Dusan ispuni kod ovde
		
		
		//1.izvuci dete iz panela childTablePanel
		Table oldChildTable;
		try {
			oldChildTable = childTablePanel.getSelectedChildTable();
		}
		catch (NullPointerException npe) {
			JOptionPane.showMessageDialog(null, "Error: Table not found", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//2.podesi dete kao novog roditelja u parentTablePanel
		parentTablePanel.setParentModel(new TableModel(oldChildTable));
		
		//3. uzmi i nadji decu koju ima
		List<Table> newChildTableList;
		try {
			newChildTableList = App.INSTANCE.getRepository().getChildTables(oldChildTable);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		//4. podesi novu decu u childTablePanel (obrati paznju da izmenis metodu da ne ubacuje praznu listu na tabbedpane)
		childTablePanel.setChildModelList(newChildTableList);
	}
	
	/**
	 * Sva logika za podesavanje spustanja roditelja se vrsi ovde, zabraniti ukoliko roditelj nema svog roditelja
	 */
	public void demoteParent() {
		setPanels();
		//TODO @Dusan ispuni kod ovde
 		//1.izvuci iz panela parentTablePanel tabelu 
		Table oldParentTable = parentTablePanel.getParentTable();
		
		//2. proveri ima li roditelja
		List<Table> parentTableList;
		try {
			parentTableList = App.INSTANCE.getRepository().getParentTables(oldParentTable);
		} catch (Exception e) {
			// Repozitorijum jos nije kreiran, implementirace @Boris
			e.printStackTrace();
			return;
		}
		
		//3.ako nema odustani od operacije i obavesti dialog porukom korisnika
		if(parentTableList == null) {
			JOptionPane.showMessageDialog(null, "Error! There is no parent for this"
											   +" parent in order to be demoted",
					"Error", JOptionPane.ERROR_MESSAGE);
			
			return;
		}
		
		//4.ako ima vise, ponudi korisniku da odabere roditelja kojeg zeli da podesi
		
		
		
		//5.funkcija koja radi tu funkciju treba da vrati listu dece koje taj ima i odabranog roditelja
		
		
		//6.strpaj odabranog novog roditelja kao roditelja u parentTablePanel
		
		//7.trpaj decu od roditelja starog roditelja u childTablePanel
	}

	
	/**
	 * Podesavanje panela <code>childTablePanel </code>  i <code> parentTablePanel</code>, kratki snippet
	 */
	public void setPanels() {
		childTablePanel = App.INSTANCE.getMainAppFrame().getMainAppPanel().getChildTablePanel();
		parentTablePanel = App.INSTANCE.getMainAppFrame().getMainAppPanel().getParentTablePanel();
	}
}
