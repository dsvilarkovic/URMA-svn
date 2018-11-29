package view;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TreePanel extends JPanel {

	public TreePanel() {
		/*ovde ubaciti u JScrollPane(treeView)*/
		JScrollPane jScrollPane = new JScrollPane(); 
		setLayout(new BorderLayout());
		add(jScrollPane);
	}

}
