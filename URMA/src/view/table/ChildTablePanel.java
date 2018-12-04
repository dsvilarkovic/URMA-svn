package view.table;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
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
	
	
	private List<TableModel> childModelList = new ArrayList<TableModel>();
	private JTabbedPane childTabs = new JTabbedPane();
	private Table selectedChildTable = null;
	
	public ChildTablePanel(String title) {
		
		super("Child");
		super.setChangeableButtonAction("Child");
	
		//podesi childTabs prvi indeks
		//TODO: (Dusan) Popravi ovo. Love, Boris
		//childTabs.setSelectedIndex(0);
		

		initChildTables();
		
		
		JScrollPane jScrollPane = new JScrollPane(tblStudenti);
		jScrollPane.setName("Tabela naslov");
		childTabs.add(jScrollPane);
		add(childTabs);
	}
	
	private void initChildTables() {
		tblStudenti = new JTable();

		// PoÅ¾eljna veliÄ�ina pogleda tabele u okviru scrollpane-a. Layout
		// manager uzima ovu osobinu u obzir.
		
		tblStudenti.setPreferredScrollableViewportSize(new Dimension(500, 200));
		
		
		// Å irenje tabele kompletno po visini pogleda scrollpane-a.
		tblStudenti.setFillsViewportHeight(true);
	}
	
	
	/**
	 * Podesavanje nove dece pri promociji jednog od dece
	 * @param childModelList
	 */
	public void setChildModelList(List<Table> tableList) {
		this.childModelList.clear();
		
		//brisanje postojecih tabova
		childTabs.removeAll();		
		//dodavanje dece kao tabelaModela i ubacivanje novih tabova kao jtbableova
		for (Table table : tableList) {
			TableModel tableModel = new TableModel(table);
			this.childModelList.add(tableModel);	
					
			JScrollPane jScrollPane = new JScrollPane(new JTable(tableModel));
			jScrollPane.setName(table.getTitle());
			this.childTabs.add(jScrollPane);
		}
		
		//podesi childTabs prvi indeks
		childTabs.setSelectedIndex(0);
		//ponovno iscrtavanje pogleda
		revalidate();
		repaint();
	}
	
	
	/**
	 * Podesavanje mape dece
	 * @param tableMap
	 */
	private Map<String, Table>  tableMap = new TreeMap<String, Table>();
	public void setTableMap(Map<String, Table> tableMap) {
		this.tableMap.clear();
			
		//dodavanje dece kao tabelaModela i ubacivanje novih tabova kao jtbableova
		for (String tableKey : tableMap.keySet()) {
			Table tableInsert = tableMap.get(tableKey);
			this.tableMap.put(tableInsert.getCode(), tableInsert);		
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
		
		
		for (String tableKey : this.tableMap.keySet()) {
			Table tableInsert = this.tableMap.get(tableKey);
			TableModel tableModel = new TableModel(tableInsert);
			
			JScrollPane jScrollPane = new JScrollPane(new JTable(tableModel));
			jScrollPane.setName(tableInsert.getTitle());
			this.childTabs.add(jScrollPane);
		}
		
		
		
		//podesi childTabs prvi indeks
		//childTabs.setSelectedIndex(0);
		//ponovno iscrtavanje pogleda
		revalidate();
		repaint();
	}
	
	
	
	/**
	 * Na osnovu indeks JTabbedPane nalazi odabranu tabelu u kolekciji childModelList i vraca odgovarajucu tabelu
	 * @return tabelu selektovanu, ili null ako nije nista nasao
	 */
	public Table getSelectedChildTable() {
		
		int selectedIndex = childTabs.getSelectedIndex();
		Table selectedTable = childModelList.get(selectedIndex).getTable();
		
		
		return selectedTable;
	}
	

}
