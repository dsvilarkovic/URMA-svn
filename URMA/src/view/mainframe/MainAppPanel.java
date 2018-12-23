package view.mainframe;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import view.table.ChildTablePanel;
import view.table.ParentTablePanel;

public class MainAppPanel extends JPanel {

	private static final long serialVersionUID = -4595102150851785070L;
	private ChildTablePanel childTablePanel = new ChildTablePanel("child");
	private ParentTablePanel parentTablePanel = new ParentTablePanel("parent");

	public MainAppPanel() {		
		setLayout(new GridLayout(2, 1));
		add(parentTablePanel);
		add(childTablePanel);
	}

	public ChildTablePanel getChildTablePanel() {
		return childTablePanel;
	}

	public void setChildTablePanel(ChildTablePanel childTablePanel) {
		this.childTablePanel = childTablePanel;
	}

	public ParentTablePanel getParentTablePanel() {
		return parentTablePanel;
	}

	public void setParentTablePanel(ParentTablePanel parentTablePanel) {
		this.parentTablePanel = parentTablePanel;
	}
}
