package view.table;


import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import app.App;
import model.Table;
import model.TitleLanguagePack;
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
	 * @param title - naziv tabele po kojoj se podesava
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
	
	/**
	 * Metoda za pozivanje fitriranje dece po {@code parentRowValues}
	 * @param parentRowValues - mapa po kojoj se izvrsava filtriranje,<br>
	 *  kljuc je kod atributa, a vrednost vrednost tog atributa
	 */
	private void filter(Map<String, String> parentRowValues) {
		App.INSTANCE.getTableMediator().setParentRowValues(parentRowValues);
		
		System.out.println("###########################");
		
		App.INSTANCE.getTableMediator().callTableSorters();
	}
	

	/**
	 * Podesavanje roditeljske tabele u {@code parentTablePanel}
	 * @param tableModel
	 */
	public void setParentModel(TableModel tableModel) {
		this.tableModel = tableModel;
		tableView.setModel(tableModel);
		TitleLanguagePack titleLanguagePack = App.INSTANCE.getTitleLanguagePack();
		String localizedTitle = titleLanguagePack.getTableTitle(tableModel.getTable().getCode());
		tab.setTitleAt(0, localizedTitle);
		
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
		return tableModel;
	}
	
	@Override
	public void updateLanguage() {
		TitleLanguagePack titleLanguagePack = App.INSTANCE.getTitleLanguagePack();
		try {
			String localizedTitle = titleLanguagePack.getTableTitle(tableModel.getTable().getCode());
			tab.setTitleAt(0, localizedTitle);
		}
		catch(NullPointerException npe) {
			System.err.println("No table found inserted in parent. Skipping localization here");
		}
		//tab.setTitleAt(0,resourceBundle.getString("table.tab.title"));
		try {
			tableModel.updateLanguage();
		} 
		catch(NullPointerException npe) {
			System.err.println("No parent found. Skipping localization");
		}
		
		
		super.setChangeableButtonAction("Parent");
		
		//mora ovako, problem naslednjivanja
		super.updateLanguage();
	}
	
}
