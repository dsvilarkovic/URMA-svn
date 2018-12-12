package view.table;


import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import model.Table;

/**
 * Tabela za roditeljski panel
 * @author Dusan
 *
 */
public class ParentTablePanel extends TablePanel {

	private static final long serialVersionUID = 6101952337575963863L;
	private TableModel tableModel;
	private JTabbedPane tab = new JTabbedPane();
	
	/**
	 * Konstruktor tabela za roditeljski panel
	 * @author Dusan
	 *
	 */
	public ParentTablePanel(String title) {
		super(title);
		super.setChangeableButtonAction("Parent");
		
		// Zaglavlje kolone se ne mora ručno ubacivati. JScrollPane će odraditi
		// taj posao.
		JScrollPane jScrollPane = new JScrollPane(tableView);
		jScrollPane.setName("Tabela naslov");
		tab.add(jScrollPane);
		addTableTabs(tab);
		
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
	
}
