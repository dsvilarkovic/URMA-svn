package view.mainframe;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import view.table.ChildTablePanel;
import view.table.ParentTablePanel;

public class MainAppPanel extends JPanel {

	private static final long serialVersionUID = -4595102150851785070L;
	private ChildTablePanel childTablePanel = new ChildTablePanel("Child");
	private ParentTablePanel parentTablePanel = new ParentTablePanel("Parent");

	public MainAppPanel() {

		setLayout(new BorderLayout());
		add(parentTablePanel, BorderLayout.NORTH);
		add(childTablePanel, BorderLayout.CENTER);
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
