package view.table;


import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import app.App;
import model.Table;
import view.localizationManager.LocalizationObserver;

/**
 * Tabela za roditeljski panel
 * @author Dusan
 *
 */
public class ParentTablePanel extends TablePanel implements LocalizationObserver {

	private static final long serialVersionUID = 6101952337575963863L;
	private TableModel tableModel;
	private JTabbedPane tab = new JTabbedPane();
	private JScrollPane jScrollPane;
	
	/**
	 * Konstruktor tabela za roditeljski panel
	 * @author Dusan
	 *
	 */
	public ParentTablePanel(String title) {
		super(title);
		//super.setChangeableButtonAction("Parent");
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		App.INSTANCE.getLocalizationManager().addLocalizationObserver(this);
		super.setChangeableButtonAction("Parent");
		
		// Zaglavlje kolone se ne mora ruÄ�no ubacivati. JScrollPane Ä‡e odraditi
		// taj posao.
		jScrollPane = new JScrollPane(tableView);
		jScrollPane.setName(resourceBundle.getString("table.tab.title"));
		tab.add(jScrollPane);
		addTableTabs(tab);
		
		addSelectionEvent();
	}
	
	
	private void addSelectionEvent() {
		tableView.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (!e.getValueIsAdjusting()
								&& tableView.getSelectedRow() != -1) {
														
//							System.out.println("Selektovana torka: ");
//							for (int i = 0; i < tableView.getColumnCount(); i++) {
//								TableModel tableModel = (TableModel)tableView.getModel();
//								//ispis obelezja i vrednosti
//								String columnName = tableView.getColumnName(i);
//								String columnValue = (String) tableView.getValueAt(tableView.getSelectedRow(), i);
//								System.out.println("{"+ columnName + "["+ tableModel.getColumnsCode().get(i) + "]: " + columnValue +"}");
//							}
//							System.out.println("_________________________");
							
							Map<String, String> parentRowValues = new TreeMap<>();
							for (int i = 0; i < tableView.getColumnCount(); i++) {
								TableModel tableModel = (TableModel)tableView.getModel();
								//ispis obelezja i vrednosti
								//String columnName = tableView.getColumnName(i);
								String columnCode = tableModel.getColumnsCode().get(i);
								Object objectColumnValue = tableView.getValueAt(tableView.getSelectedRow(), i);
								String columnValue = null;
								if(objectColumnValue instanceof Boolean) {
									columnValue = Boolean.toString((Boolean)objectColumnValue);
								}
								else if(objectColumnValue instanceof Double) {
									columnValue = Double.toString((Double)objectColumnValue);
								}
								else if(objectColumnValue instanceof Integer) {
									columnValue = Integer.toString((Integer) objectColumnValue);
								}
								else {
									columnValue = (String) tableView.getValueAt(tableView.getSelectedRow(), i);
								}
								
								parentRowValues.put(columnCode, columnValue);
								
								
								
								
								
								//System.out.println("{"+ columnName + "["+ tableModel.getColumnsCode().get(i) + "]: " + columnValue +"}");
							
							}
							filter(parentRowValues);
						}
					}					
		});
		tableView.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		
	}
	
	private void filter(Map<String, String> parentRowValues) {
		App.INSTANCE.getTableMediator().setParentRowValues(parentRowValues);
		
		System.out.println("###########################");
		
		App.INSTANCE.getTableMediator().callTableSorters();
	}
	


	public void setParentModel(TableModel tableModel) {
		this.tableModel = tableModel;
		tableView.setModel(tableModel);
		tab.setTitleAt(0, tableModel.getTable().getTitle());
		
		//podesi podatke
		tableModel.setUpData();
		revalidate();
		repaint();
	}

	/**
	 * Vraca tabelu koja je u roditeljskom panelu
	 * @return
	 */
	public Table getParentTable() {
		return tableModel.getTable();
	}
	
	/**
	 * Vraca odgovarajuci pogled na tabelu, posto nam trebaju funkcije iz nje
	 * @return
	 */
	public JTable getParentTableView() {
		return tableView;
	}


	public TableModel getParentTableModel() {
		// TODO Auto-generated method stub
		return tableModel;
	}
	
	@Override
	public void updateLanguage() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		tab.setTitleAt(0,resourceBundle.getString("table.tab.title"));
		tableModel.updateLanguage();
		
		
		super.setChangeableButtonAction("Parent");
		
		//mora ovako, problem naslednjivanja
		super.updateLanguage();
	}
	
}
