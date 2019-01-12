package view.mainFrame;

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
import view.localizationManager.LocalizationObserver;

/**
 * Klasa panel za stablo u aplikaciji. <br>
 * Sadrzi TreeModel i Tree u sebi
 * @author filip
 */
public class MainAppTreePanel extends JPanel implements LocalizationObserver {

	private static final long serialVersionUID = 198264279977978230L;
	private JTree tree;
	private static DefaultTreeModel treeModel = null;
	InformationResource infRes;

	/**
	 * Konstruktor za kreiranje panela za stablo. <br>
	 * Inicijalno se kreira samo prazan panel.
	 * @author filip
	 * 
	 */
	public MainAppTreePanel() {
		App.INSTANCE.getLocalizationManager().addLocalizationObserver(this);
		setLayout(new BorderLayout());
		setBackground(Color.orange);
	}

	/**
	 * Metoda za inicijalizaciju stabla (Iscrtavanje stabla u panelu)
	 * @author filip
	 * @param elderNodek - nastariji vrhovni cvor u stablu iz koga svi krecu. <br>
	 * On kao i svi ostali cvorovi stabla moraju biti tipa {@link TreeParts}.
	 * 
	 */
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
				tree.clearSelection();
			}
		});

		JScrollPane spTree = new JScrollPane(tree);
		add(spTree);
		repaint();
		revalidate();

	}

	/**
	 * Rekurzivna metoda za pravljenje stabla. <br>
	 * Metoda koja ce vratiti ceo sistem pod cvorova stabla za dati cvor. <br>
	 * Stoga ako joj se prosledi najstariji cvor dobije se celo podstablo.
	 * @author filip
	 * @param node - cvor za koji hocemo da konstruisemo stablo. <br>
	 * Mora svaki cvor za pravilno iscrtavanje treba da implemetira {@link TreeParts}
	 * @return {@link DefaultMutableTreeNode} - vraca nastariji cvor (sa njim povezani i svi ostali cvorovi). <br>
	 * Efektivno vrati stablo spremno za crtanje
	 */
	public DefaultMutableTreeNode updateTreeModel(TreeParts node) {
		DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(node);
		
		System.out.println(node.getName());
		if (node.getContent(infRes) != null)
			for (TreeParts k : node.getContent(infRes)) {
				parentNode.add(updateTreeModel(k));
			}

		return parentNode;
	}

	public JTree getTree() {
		return tree;
	}

	@Override
	public void updateLanguage() {
		//ako jos nista nije ucitano u stablo
		if(treeModel == null) {
			return;
		}
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
		if(root != null) {
			updateNode(root);
		}
	}
	
	/**
	 * Rekurzivni pristup obavestavanju cvorova
	 * @param node
	 */
	private void updateNode(DefaultMutableTreeNode node) {
		if(node != null) {
			for (int i = 0; i < node.getChildCount(); i++) {
				updateNode((DefaultMutableTreeNode)node.getChildAt(i));
			}
			treeModel.nodeChanged(node);
		}
	}
}
