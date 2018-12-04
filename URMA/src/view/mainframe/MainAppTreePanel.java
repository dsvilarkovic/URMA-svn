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
import model.Relation;
import model.Table;
import model.treeAdapter.AdapterTable;
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
		
		
//		Table tb1 = new Table();
//		tb1.setTitle("elderNode");
//		tb1.setCode("1");
//		
//		Table tb2 = new Table();
//		tb2.setTitle("paretnNode");
//		tb2.setCode("2");
//		
//		Table tb3 = new Table();
//		tb3.setTitle("childNode");
//		tb3.setCode("3");
//				
//		infRes.addAllTables(tb1);
//		infRes.addAllTables(tb2);
//		infRes.addAllTables(tb3);
//		
//		Relation rl1 = new Relation();
//		rl1.setSourceTable(tb1);
//		rl1.setDestinationTable(tb2);
//		
//		Relation rl2 = new Relation();
//		rl2.setSourceTable(tb2);
//		rl2.setDestinationTable(tb3);
//		
//		infRes.addRelations(rl1);
//		infRes.addRelations(rl2);
		
		
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
		//System.out.println(node.getName() + " " + node.getContent(infRes));
		//System.out.println("# " + node.getContent(infRes) + "   -   " + node.getContent(infRes).size());

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
