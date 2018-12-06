package view.mainframe;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import app.App;
import model.InformationResource;
import model.treeAdapter.TreeParts;

/**
 * 
 * @author filip
 *
 */
public class MainAppTreePanel extends JPanel {

	private static final long serialVersionUID = 198264279977978230L;
	private JTree tree;
	private static DefaultTreeModel treeModel;
	InformationResource infRes;

	public MainAppTreePanel() {
		setLayout(new BorderLayout());
		setBackground(Color.orange);
	}

	public void init(TreeParts elderNodek) {

		infRes = App.INSTANCE.getModel();
		treeModel = new DefaultTreeModel(updateTreeModel(elderNodek));
		tree = new JTree(treeModel);
		tree.setCellRenderer(new MainAppTreeRenderer());

		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				System.out.println("Akcija u drvetu " + e.getSource());

				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (!(node == null))
					((TreeParts) node.getUserObject()).action();
			}
		});

		JScrollPane spTree = new JScrollPane(tree);
		add(spTree);
		repaint();
		revalidate();

	}

	public DefaultMutableTreeNode updateTreeModel(TreeParts node) {
		DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(node);

		if (node.getContent(infRes) != null)
			for (TreeParts k : node.getContent(infRes)) {
				parentNode.add(updateTreeModel(k));
			}

		return parentNode;
	}

	public JTree getTree() {
		return tree;
	}
}
