package view.table;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

import app.App;
import model.Table;
import view.localizationManager.LocalizationObserver;

/**
 * Panel koji sluzi za predstavljanje dece, u njemu se nalaze i metode za ponovno desavanje dece pri 
 * odredjenim dogadjajima.
 * @author Dusan
 *
 */
public class ChildTablePanel extends TablePanel implements LocalizationObserver{
	private static final long serialVersionUID = 607934006292817924L;
	
	
	private JTabbedPane childTabs = new JTabbedPane();
	private List<TableRowSorter<TableModel>> tableSorters = new ArrayList<TableRowSorter<TableModel>>();
	private List<RowPrimaryKeyFilter<TableModel>> primaryKeyFilters = new ArrayList<RowPrimaryKeyFilter<TableModel>>();
	private JScrollPane jScrollPane;
	public ChildTablePanel(String title) {
		super("child");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		App.INSTANCE.getLocalizationManager().addLocalizationObserver(this);
		super.setChangeableButtonAction(resourceBundle.getString("table.child"));
	
			
		
		jScrollPane = new JScrollPane(tableView);
		jScrollPane.setName(resourceBundle.getString("table.tab.title"));
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
		//i row filtera
		tableSorters.clear();
		primaryKeyFilters.clear();
		
		
		for (String tableKey : this.tableModelMap.keySet()) {
			TableModel tableModelInsert = this.tableModelMap.get(tableKey);
			
			//podesi podatke
			tableModelInsert.setUpData();
			JTable stagod = new JTable(tableModelInsert);
			JScrollPane jScrollPane = new JScrollPane(stagod);
			jScrollPane.setName(tableModelInsert.getTable().getTitle());
			this.childTabs.add(jScrollPane);
			
			//podesi sorter (jedan za svako dete)
			TableRowSorter<TableModel> tableSorter = new TableRowSorter<TableModel>(tableModelInsert);

			TableModel parentTableModel = App.INSTANCE.getMainAppFrame().getMainAppPanel().getParentTablePanel().getParentTableModel();
			
			tableSorter.setRowFilter(constructFilter(tableModelInsert, parentTableModel));
			tableSorters.add(tableSorter);
			stagod.setRowSorter(tableSorter);	
			
					
			
		}
		
		//ponovno iscrtavanje pogleda
		revalidate();
		repaint();
	}
	
	
	
	/**
	 * @return the tableSorters
	 */
	public List<TableRowSorter<TableModel>> getTableSorters() {
		return tableSorters;
	}


	/**
	 * @return the primaryKeyFilters
	 */
	public List<RowPrimaryKeyFilter<TableModel>> getPrimaryKeyFilters() {
		return primaryKeyFilters;
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
	 * Sluzi da vrati model aktivne tabele u child panelu
	 * @return - tabelu modela aktivnog child-a
	 */
	public TableModel getSelectedChildTableModel() {
		JTable selectedTable = getSelectedChildTableView();
		
		return (TableModel) selectedTable.getModel();
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
	
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private RowFilter<TableModel, Integer> constructFilter(TableModel childTableModel, TableModel parentTableModel) {
		//RowPrimaryKeyFilter<TableModel> primaryKeyFilter = new RowPrimaryKeyFilter<>(childTableModel,parentTableModel);
		RowPrimaryKeyFilter primaryKeyFilter = new RowPrimaryKeyFilter<>(childTableModel, parentTableModel);
		
		primaryKeyFilters.add(primaryKeyFilter);
		
		return primaryKeyFilter;
	}


	@Override
	public void updateLanguage(String language) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		jScrollPane.setName(resourceBundle.getString("table.tab.title"));
		super.setChangeableButtonAction(resourceBundle.getString("table.child"));

	}
}
