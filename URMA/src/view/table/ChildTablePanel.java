package view.table;

import java.awt.BorderLayout;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import model.Table;

/**
 * Panel koji sluzi za predstavljanje dece, u njemu se nalaze i metode za ponovno desavanje dece pri 
 * odredjenim dogadjajima.
 * @author Dusan
 *
 */
public class ChildTablePanel extends TablePanel {
	private static final long serialVersionUID = 607934006292817924L;
	
	
	private JTabbedPane childTabs = new JTabbedPane();
	
	public ChildTablePanel(String title) {
		
		super("Child");
		super.setChangeableButtonAction("Child");
	
			
		
		JScrollPane jScrollPane = new JScrollPane(tableView);
		jScrollPane.setName("Tabela naslov");
		childTabs.add(jScrollPane);
		addTableTabs(childTabs);
	}
	
	/**
	 * Podesavanje mape dece
	 * @param tableMap
	 */
	private Map<String, TableModel>  tableModelMap = new TreeMap<String, TableModel>();
	public void setTableModelMap(Map<String, Table> tableMap) {
		this.tableModelMap.clear();
			
		//dodavanje dece kao tabelaModela i ubacivanje novih tabova kao jtbableova
		for (String tableKey : tableMap.keySet()) {
			Table tableInsert = tableMap.get(tableKey);
			this.tableModelMap.put(tableInsert.getCode(), new TableModel(tableInsert));		
		}
		
		updateChildTabs();
	}
	
	
	/**
	 * @author Dusan
	 * 
	 * Implementacija podesavanje novih izmena u tabeli dece
	 */
	private void updateChildTabs() {
		//brisanje postojecih tabova
		childTabs.removeAll();	
		
		
		for (String tableKey : this.tableModelMap.keySet()) {
			TableModel tableModelInsert = this.tableModelMap.get(tableKey);
			
			//podesi podatke
			tableModelInsert.setUpData();
			JScrollPane jScrollPane = new JScrollPane(new JTable(tableModelInsert));
			jScrollPane.setName(tableModelInsert.getTable().getTitle());
			this.childTabs.add(jScrollPane);
		}
		
		//ponovno iscrtavanje pogleda
		revalidate();
		repaint();
	}
	
	
	
	/**
	 * Na osnovu indeks JTabbedPane nalazi odabranu tabelu u kolekciji childModelList i vraca odgovarajucu tabelu
	 * @return tabelu selektovanu, ili null ako nije nista nasao
	 */
	public Table getSelectedChildTable() {
		//uzmi panel
		JScrollPane selectedTablePane = (JScrollPane) childTabs.getComponent(childTabs.getSelectedIndex());
		//uzmi jtable
		JTable selectedTable = (JTable) selectedTablePane.getViewport().getComponent(0);
		
		TableModel selectedTableModel = (TableModel) selectedTable.getModel();
		
		//vrati tabelu u modelu		
		
		return selectedTableModel.getTable();
	}
	
	/**
	 * Na osnovu indeks JTabbedPane nalazi odabrani pogled na tabelu i vraca odgovarajucu tabelu
	 * @return pogled na tabelu selektovanu, ili null ako nije nista nasao
	 */
	public JTable getSelectedChildTableView() {
		//uzmi panel
		JScrollPane selectedTablePane = (JScrollPane) childTabs.getComponent(childTabs.getSelectedIndex());
		//uzmi jtable
		JTable selectedTable = (JTable) selectedTablePane.getViewport().getComponent(0);
			
		
		return selectedTable;
	}
	
	

}
