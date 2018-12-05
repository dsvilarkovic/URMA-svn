package controller.tableactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import app.App;
import model.Table;
import view.dialogs.ChooseParentTableDialog;
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
	
	public void showTable(Table table) {
		setPanels();
		//TODO @Dusan za dalje faze ovo sacuvaj
		//0. pitaj da li zeli da sacuva prethodne izmene 
		
		//1. uzmi tabelu i smesti je u parentTablePanel
		//parentTablePanel.setParentModel(new TableModel(table));
		parentTablePanel.setParentModel(new TableModel(table));
		
		//2. podesi u childTablePanel decu ako ima
		Map<String, Table> childTableMap = table.getChildTables();
		childTablePanel.setTableModelMap(childTableMap);		
		
		
		setChildVisibility(childTableMap);
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
		Map<String, Table> newChildTableMap;
		newChildTableMap = oldChildTable.getChildTables();
		
		//4. podesi novu decu u childTablePanel (obrati paznju da izmenis metodu da ne ubacuje praznu listu na tabbedpane)
		//childTablePanel.setChildModelList(newChildTableList);
		childTablePanel.setTableModelMap(newChildTableMap);
		
		
		//podesi vidljivost dece
		setChildVisibility(newChildTableMap);
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
		Map<String, Table> parentTableMap = oldParentTable.getParentTables();
		
		//3.ako nema odustani od operacije i obavesti dialog porukom korisnika
		if(parentTableMap == null || parentTableMap.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Error! There is no parent for this"
											   +" parent in order to be demoted",
					"Error", JOptionPane.ERROR_MESSAGE);
			
			return;
		}
		
		//4.ako ima vise, ponudi korisniku da odabere roditelja kojeg zeli da podesi
		Table newParentTable = parentTableMap.get(parentTableMap.keySet().iterator().next());
		if(parentTableMap.size() > 1) {
			String retVal = (new ChooseParentTableDialog(parentTableMap)).getSelected();
			
			newParentTable = parentTableMap.get(retVal);
		}
		//5.funkcija koja radi tu funkciju treba da vrati listu dece koje taj ima i odabranog roditelja
		//6.strpaj odabranog novog roditelja kao roditelja u parentTablePanel
		parentTablePanel.setParentModel(new TableModel(newParentTable));
		
		//7.trpaj decu od roditelja starog roditelja u childTablePanel
		childTablePanel.setTableModelMap(newParentTable.getChildTables());
		
		setChildVisibility(newParentTable.getChildTables());
	}

	
	/**
	 * Podesavanje panela <code>childTablePanel </code>  i <code> parentTablePanel</code>, kratki snippet
	 */
	private void setPanels() {
		if(childTablePanel == null || parentTablePanel == null) {
			childTablePanel = App.INSTANCE.getMainAppFrame().getMainAppPanel().getChildTablePanel();
			parentTablePanel = App.INSTANCE.getMainAppFrame().getMainAppPanel().getParentTablePanel();
		}
	}
	
	
	private void setChildVisibility(Map<String, Table> childTableMap) {
		boolean isNotVisible = childTableMap == null || childTableMap.isEmpty();
		childTablePanel.setVisible(!isNotVisible);
	}
	
	
	
	
}
