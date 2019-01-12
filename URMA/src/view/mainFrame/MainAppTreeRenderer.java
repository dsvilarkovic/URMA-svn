package view.mainFrame;

import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import model.treeAdapter.TreeParts;

/**
 * Klasa zaduzeno za iscrtavanje cvorova u stablu.
 * Kupi informaciju o imenu i slici koju treba da nalepi za svaki cvor
 * @author filip
 */

//TODO: @Dusan @lokalizacija not done ->> proveri da li i zasoto ovo koristimo i proveri boolean checkbox
public class MainAppTreeRenderer extends DefaultTreeCellRenderer{

	private static final long serialVersionUID = 1257570282495001153L;

	/**
	 * Akcija koja se poziva prilikom iscrtavanja svakog cvora
	 * Kupi informaciju o imenu i slici koju treba da nalepi za svaki cvor
	 * @author filip
	 */
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		String path = ((TreeParts) node.getUserObject()).getImgPath();
		Image scaleImg = Toolkit.getDefaultToolkit().getImage(path);
		scaleImg = scaleImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(scaleImg));
		setText(((TreeParts) node.getUserObject()).getName());
		revalidate();
		repaint();

		return this;
	}
	
}
