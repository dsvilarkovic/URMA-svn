package view.mainframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

/**
 * 
 * @author Dusan
 *
 */
public class MainAppFrame extends JFrame {

	private static final long serialVersionUID = 6322913707486736787L;
	private MainAppToolbar mainAppToolbar = new MainAppToolbar();
	private MainAppTreePanel treePanel = new MainAppTreePanel();
	private MainAppMenuBar mainAppMenubar = new MainAppMenuBar();
	private MainAppPanel mainAppPanel = new MainAppPanel();

	/**
	 * Glavni prozor aplikacije
	 */
	public MainAppFrame() {
		// ResourceBundle resourceBundle =
		// ResourceBundle.getBundle("localisationresources.localisationresources",
		// Locale.getDefault());
		// podesavanje izgleda glavnog prozora
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int width = (int) screenSize.getWidth() * 3 / 4;
		int height = (int) screenSize.getHeight() * 3 / 4;
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		// setTitle(resourceBundle.getString("menu.title"));
		setLayout(new BorderLayout());
		setVisible(true);

		setJMenuBar(mainAppMenubar);

		// toolBar.setBackground(Color.GREEN);
		getContentPane().add(mainAppToolbar, BorderLayout.NORTH);

		JSplitPane splitPane;

		// treePanel.setBackground(Color.cyan);
		treePanel.setPreferredSize(new Dimension((int) (width * 0.3), height));

		mainAppPanel.setPreferredSize(new Dimension((int) (width * 0.7), height));

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePanel, mainAppPanel);

		getContentPane().add(splitPane, BorderLayout.WEST);
	}

	public MainAppPanel getMainAppPanel() {
		return mainAppPanel;
	}

	public void setMainAppPanel(MainAppPanel mainAppPanel) {
		this.mainAppPanel = mainAppPanel;
	}

	public MainAppTreePanel getTreePanel() {
		return treePanel;
	}

}
