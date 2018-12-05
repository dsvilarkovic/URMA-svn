package view.mainframe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.Border;

import view.table.ChildTablePanel;
import view.table.ParentTablePanel;

public class MainAppPanel extends JPanel {

	private ChildTablePanel childTablePanel = new ChildTablePanel("Child");
	private ParentTablePanel parentTablePanel = new ParentTablePanel("Parent");
	
	public MainAppPanel() {
		
		
		
		setLayout(new BorderLayout());
		add(parentTablePanel, BorderLayout.NORTH);
		add(childTablePanel, BorderLayout.CENTER);
		
	}

	/**
	 * @return the childTablePanel
	 */
	public ChildTablePanel getChildTablePanel() {
		return childTablePanel;
	}

	/**
	 * @param childTablePanel the childTablePanel to set
	 */
	public void setChildTablePanel(ChildTablePanel childTablePanel) {
		this.childTablePanel = childTablePanel;
	}

	/**
	 * @return the parentTablePanel
	 */
	public ParentTablePanel getParentTablePanel() {
		return parentTablePanel;
	}

	/**
	 * @param parentTablePanel the parentTablePanel to set
	 */
	public void setParentTablePanel(ParentTablePanel parentTablePanel) {
		this.parentTablePanel = parentTablePanel;
	}
	
	

}
