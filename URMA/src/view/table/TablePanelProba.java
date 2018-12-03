 package view.table;

import java.awt.Color;

import javax.swing.BorderFactory;

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
public class TablePanelProba extends JPanel {

	
	private static final long serialVersionUID = 1988065650430728153L;
	protected JTable tblStudenti;
	private JButton addRow = new JButton("Add");
	private JButton removeRow = new JButton("Remove");
	private JButton updateRow = new JButton("Update");
	private JButton search = new JButton("Search");
	private JButton changeableButton = new JButton("");
	private ButtonGroup buttonGroup = new ButtonGroup();
		

	public TablePanelProba(String title) {
		Border blackline = BorderFactory.createMatteBorder(1,1, 1, 1, Color.black);
		Border titledBorder = BorderFactory.createTitledBorder(blackline, title);
		this.setBorder(titledBorder);
		
		buttonGroup.add(addRow);
		buttonGroup.add(removeRow);
		buttonGroup.add(updateRow);
		buttonGroup.add(search);
		buttonGroup.add(changeableButton);
		
		
		add(addRow);
	    add(removeRow);
		add(updateRow);
		add(search);
		add(changeableButton);		

	}	
	
	

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
