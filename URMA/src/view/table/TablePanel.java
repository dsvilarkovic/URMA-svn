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

import controller.tableactions.CreateCrudWindowAction;
import controller.tableactions.DemoteParentAction;
import controller.tableactions.PromoteChildAction;
import model.Table;


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
		setButtonActions();
	}	
	
	private void setButtonActions() {
		//podesavanje akcija
		
		addRow.setAction(new CreateCrudWindowAction(this, addRow.getText()));
		removeRow.setAction(new CreateCrudWindowAction(this, removeRow.getText()));
		updateRow.setAction(new CreateCrudWindowAction(this, updateRow.getText()));
		search.setAction(new CreateCrudWindowAction(this, search.getText()));
	}

	private void initTables() {
		tableView = new JTable();
		
		tableView.setModel(new TableModel(new Table()));

		// Poželjna veličina pogleda tabele u okviru scrollpane-a. Layout
		// manager uzima ovu osobinu u obzir.
		
		tableView.setPreferredScrollableViewportSize(new Dimension(500, 200));
		
		
		// Širenje tabele kompletno po visini pogleda scrollpane-a.
		tableView.setFillsViewportHeight(true);
	}
	/**
	 * Funkcija za podesavanje dugmadi
	 */
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
	}
	
	
	/**
	 * Podesavanje dugmadi za promociju i demociju clanova tabele.
	 * @param className - parametar imena dugmeta za koje se podesava akcija
	 */
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
