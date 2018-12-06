 package view.table;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;

import controller.tableactions.DemoteParentAction;
import controller.tableactions.PromoteChildAction;


/**
 * Sluzi za gornji deo dugmati koja sluze za upravljanje
 * @author Dusan
 *
 */
public class TablePanel extends JPanel {

	
	private static final long serialVersionUID = 1988065650430728153L;
	protected JTable tableView;
	private JButton addRow = new JButton("Add");
	private JButton removeRow = new JButton("Remove");
	private JButton updateRow = new JButton("Update");
	private JButton search = new JButton("Search");
	private JButton changeableButton = new JButton("");
	private ButtonGroup buttonGroup = new ButtonGroup();
		

	public TablePanel(String title) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Border blackline = BorderFactory.createMatteBorder(1,1, 1, 1, Color.black);
		Border titledBorder = BorderFactory.createTitledBorder(blackline, title);
		this.setBorder(titledBorder);
		
		
		initTables();
		
		setButtons();
	}	
	
	private void initTables() {
		tableView = new JTable();

		// Poželjna veličina pogleda tabele u okviru scrollpane-a. Layout
		// manager uzima ovu osobinu u obzir.
		
		tableView.setPreferredScrollableViewportSize(new Dimension(500, 200));
		
		
		// Širenje tabele kompletno po visini pogleda scrollpane-a.
		tableView.setFillsViewportHeight(true);
	}
	private void setButtons() {
		buttonGroup.add(addRow);
		buttonGroup.add(removeRow);
		buttonGroup.add(updateRow);
		buttonGroup.add(search);
		//buttonGroup.add(changeableButton);
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		Box box = Box.createHorizontalBox();
		box.add(Box.createHorizontalStrut(5));
		box.add(addRow);
		box.add(Box.createHorizontalStrut(5));
		box.add(removeRow);
		box.add(Box.createHorizontalStrut(5));
		box.add(updateRow);
		box.add(Box.createHorizontalStrut(5));
		box.add(search);
		box.add(Box.createHorizontalGlue());
		box.add(changeableButton);
		box.add(Box.createHorizontalStrut(15));
		add(box);
		
//		buttonPanel.add(addRow);
//		buttonPanel.add(removeRow);
//		buttonPanel.add(updateRow);
//		buttonPanel.add(search);
		//buttonPanel.add(changeableButton);		
		
	}
	
	
//	/**
//	 * Podesavanje tabela u tabbedPane za oba pogleda
//	 * @param tableModelMap
//	 */
//	private Map<String, Table> tableModelMap = new TreeMap<String, Table>();
//	public void setTableModelMap(Map<String, Table> tableModelMap) {
//		this.tableModelMap.clear();
//		
//		//brisanje postojecih tabova
//		childTabs.removeAll();		
//		//dodavanje dece kao tabelaModela i ubacivanje novih tabova kao jtbableova
//		for (Table table : tableList) {
//			TableModel tableModel = new TableModel(table);
//			this.childModelList.add(tableModel);	
//					
//			JScrollPane jScrollPane = new JScrollPane(new JTable(tableModel));
//			jScrollPane.setName(table.getTitle());
//			this.childTabs.add(jScrollPane);
//		}
//		
//		//podesi childTabs prvi indeks
//		childTabs.setSelectedIndex(0);
//		//ponovno iscrtavanje pogleda
//		revalidate();
//		repaint();
//	}

	public void setChangeableButtonAction(String className) {
		switch (className) {
		case "Child":
			changeableButton.setAction(new PromoteChildAction());
			break;

		case "Parent":
			changeableButton.setAction(new DemoteParentAction());
			break;
		default:
			break;
		}
		
	}


}
