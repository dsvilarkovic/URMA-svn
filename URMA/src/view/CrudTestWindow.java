package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class CrudTestWindow extends JFrame{

	private static final long serialVersionUID = 1L;

	public CrudTestWindow() {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setSize((int) (screenSize.getWidth() * 0.71), (int) (screenSize.getHeight() * 0.71));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//addWindowListener(new EditorWindowClosingAction());
		setLocationRelativeTo(null);
		setResizable(true);
		setTitle("CRUD");
		setLayout(new BorderLayout());
		setIconImage((new ImageIcon("resources/palm-tree.png")).getImage());
		
		

		validate();
	}
}
