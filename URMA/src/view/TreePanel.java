package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TreePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public TreePanel() {
		/*ovde ubaciti u JScrollPane(treeView)*/
		JScrollPane jScrollPane = new JScrollPane(); 
		setLayout(new BorderLayout());
		add(jScrollPane);
	}

}
