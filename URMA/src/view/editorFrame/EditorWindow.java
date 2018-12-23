package view.editorFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;

import controler.editorActions.EditorWindowClosingAction;

/**
 * Klasa koja pravi Editor Panel i sve njegove komponente.
 * @author filip
 */
public class EditorWindow extends JDialog {

	private static final long serialVersionUID = -8130256364738099887L;
	private MainPanel mainPanel = new MainPanel();
	private Toolbar toolbar = new Toolbar();
	private ConsolePanel console = new ConsolePanel();

	public EditorWindow() {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setSize((int) (screenSize.getWidth() * 0.71), (int) (screenSize.getHeight() * 0.71));
		addWindowListener(new EditorWindowClosingAction());
		setLocationRelativeTo(null);
		setResizable(true);
		setTitle("Editor");
		setModal(true);
		setLayout(new BorderLayout());
		setIconImage((new ImageIcon("resources/palm-tree.png")).getImage());

		add(toolbar, BorderLayout.NORTH);
		add(mainPanel);
		add(console, BorderLayout.SOUTH);

		validate();
	}

	public Toolbar getToolbar() {
		return toolbar;
	}

	public MainPanel getMainPanel() {
		return mainPanel;
	}

	public ConsolePanel getConsolePanel() {
		return console;
	}
}
