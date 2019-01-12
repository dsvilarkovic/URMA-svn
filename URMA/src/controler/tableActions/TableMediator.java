package controler.tableActions;

import java.awt.GridLayout;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

import app.App;
import model.Table;
import view.dialogs.ChooseParentTableDialog;
import view.mainFrame.MainAppPanel;
import view.table.ChildTablePanel;
import view.table.ParentTablePanel;
import view.table.RowPrimaryKeyFilter;
import view.table.TableModel;
import view.table.TablePanel;

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
	 * @author Dusan
	 */
	public TableMediator() {
	}
	
	/**
	 * Sluzi za predstavljanje tabela u {@code childTablePanel} i {@code parentTablePanel}
	 * @author Dusan
	 * @param table - tabela tipa {@link Table} po kojoj se ona podesava za roditeljsku tabelu,
	 * a njegova deca za child tabele.
	 */
	public void showTable(Table table) {
		setPanels();
		//0. pitaj da li zeli da sacuva prethodne izmene 
		
		//1. uzmi tabelu i smesti je u parentTablePanel
		//parentTablePanel.setParentModel(new TableModel(table));
		parentTablePanel.setParentModel(new TableModel(table));
		
		//2. podesi u childTablePanel decu ako ima
		Map<String, Table> childTableMap = table.getChildTables();
		childTablePanel.setTableModelMap(childTableMap);		
		
		
		setChildVisibility(childTableMap);
	}
	
	/**
	 * @author Dusan
	 * Akcija za promociju selektovane tabele u {@code childTablePanel} u {@code parentTablePanel}. <br>
	 */
	public void promoteChild() {
		
		setPanels();
		
		
		//1.izvuci dete iz panela childTablePanel
		Table oldChildTable;
		try {
			oldChildTable = childTablePanel.getSelectedChildTable();
			if(oldChildTable.getCode() == null || oldChildTable.getCode() == null) {
				throw new NullPointerException();
			}
		}
		catch (NullPointerException npe) {
			ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
			String message = resourceBundle.getString("table.emptyerror");
			JOptionPane.showMessageDialog(null, message, "", JOptionPane.ERROR_MESSAGE);
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
	 * Sva logika za podesavanje spustanja roditelja se vrsi ovde, zabraniti ukoliko roditelj nema svog roditelja. <br>
	 * Takodje ukoliko spustana tabela iz {@code parentTablePanel} ima vise svojih roditelja, nudi se <br>
	 * dijalog prozor za spustanje.
	 * @author Dusan
	 */
	public void demoteParent() {
		setPanels();
 		//1.izvuci iz panela parentTablePanel tabelu 
		Table oldParentTable = null;
		try {
			oldParentTable = parentTablePanel.getParentTable();
		} catch (NullPointerException e) {
			ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
			String message = resourceBundle.getString("table.emptyerror");
			JOptionPane.showMessageDialog(null, message, "", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		//2. proveri ima li roditelja
		Map<String, Table> parentTableMap = oldParentTable.getParentTables();
		
		
		
		//3.ako nema odustani od operacije i obavesti dialog porukom korisnika
		ResourceBundle resourceBundle = App.INSTANCE.getResourceBundle();
		if(parentTableMap == null || parentTableMap.isEmpty()) {
			//TODO: @Dusan @lokalizacija not done
			JOptionPane.showMessageDialog(null, resourceBundle.getString("table.parent.demote.error"),
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
	 * @author Dusan
	 */
	private void setPanels() {
		if(childTablePanel == null || parentTablePanel == null) {
			childTablePanel = App.INSTANCE.getMainAppFrame().getMainAppPanel().getChildTablePanel();
			parentTablePanel = App.INSTANCE.getMainAppFrame().getMainAppPanel().getParentTablePanel();
		}
	}
	
	/**
	 * Podesavanje vidljivosti child tabela u {@code childTablePanel}
	 * @param childTableMap - mapa child tabela koje ce se ubaciti
	 */
	private void setChildVisibility(Map<String, Table> childTableMap) {
		MainAppPanel mainAppPanel = App.INSTANCE.getMainAppFrame().getMainAppPanel();
		boolean isNotVisible = childTableMap == null || childTableMap.isEmpty();
		
		if(isNotVisible) {
			mainAppPanel.setLayout(new GridLayout(1, 1));
			mainAppPanel.removeAll();
			mainAppPanel.add(parentTablePanel);			
		}
		else {
			mainAppPanel.setLayout(new GridLayout(2, 1));
			mainAppPanel.removeAll();
			mainAppPanel.add(parentTablePanel);	
			mainAppPanel.add(childTablePanel);
		}	
	}

	/**
	 * Tabela koja ce vratiti pozvani pogled na tabelu putem crud operacija
	 * @author Dusan
	 * @param panelCalling - panel iz kog se poziva tbaela
	 * @return pogled na tabelu u obliku <code>JTable</code>
	 * @throws Exception - baca izuzetak ako mu je prosledio pogresnu tabelu
	 */
	public JTable getCalledTable(TablePanel panelCalling) throws Exception {
		JTable calledTable = null;
		if(panelCalling instanceof ParentTablePanel) {
			calledTable = ((ParentTablePanel) panelCalling).getParentTableView();
		}
		else
		if(panelCalling instanceof ChildTablePanel) { 
			calledTable = ((ChildTablePanel) panelCalling).getSelectedChildTableView(); 
		}
		else {
			throw new Exception("Invalid table found");
		}
		
		return calledTable;
	}
	
	/**
	 * Sluzi da nadje odgovarajuci TableModel za referenciranu tabelu iz pogleda
	 * @author Dusan
	 * @param table  - pozivana tabela tipa {@link Table}
	 * @return odgovarajuci model za poziv
	 */
	public TableModel getCalledTableModel(Table table) {
		TableModel chosenTableModel = null;
		//uzmi aktivni roditeljski table model iz pogleda, ako se tabele poklapaju vrati taj model
		TableModel activeParentTableModel = parentTablePanel.getParentTableModel();
		
		if(activeParentTableModel.getTable().getCode().equals(table.getCode())) {
			chosenTableModel = activeParentTableModel;
		}
		
		//uzmi aktivni child table model iz pogleda, ako se tabele poklapaju vrati taj model
		TableModel activeChildTableModel = childTablePanel.getSelectedChildTableModel();
		if(activeChildTableModel.getTable().getCode().equals(table.getCode())) {
			chosenTableModel = activeChildTableModel;
		}
		
		return chosenTableModel;
	}

	/**
	 * Sluzi da se child tabelama uvede novi uslov za filtriranje u njihove filtere.
	 * Svaki od childova ima svoj filter.
	 * @author Dusan
	 * @param parentRowValues - vrednosti po mapi odabrane torke iz roditelja kljuc kod atributa <br>
	 * za vrednost mape vrednost atributa.
	 */
	public void setParentRowValues(Map<String, String> parentRowValues) {
		
		System.out.println("############################");
		System.out.println("_______" + parentRowValues);
		
		
		List<RowPrimaryKeyFilter<TableModel>> primaryKeyFilters =  childTablePanel.getPrimaryKeyFilters();
		//podesiti svima novu vrednost
		for (RowPrimaryKeyFilter<TableModel> rowPrimaryKeyFilter : primaryKeyFilters) {
			rowPrimaryKeyFilter.setRowValues(parentRowValues);
			System.out.println(rowPrimaryKeyFilter);
			System.out.println(childTablePanel);
			System.out.println(childTablePanel.getTableSorters());
		}
				
	}

	/**
	 * Poziv za realtime sortiranje trazenih pretraga za svako dete u {@code childTablePanel}
	 * @author Dusan
	 */
	public void callTableSorters() {
		List<TableRowSorter<TableModel>> tableSorters =  childTablePanel.getTableSorters();
		
		//System.out.println("NOCNO NEBO");
		
		for (TableRowSorter<TableModel> tableRowSorter : tableSorters) {
			
			System.out.println(tableRowSorter);
			
			tableRowSorter.sort();
		}
		
	}
	
	
	
}
