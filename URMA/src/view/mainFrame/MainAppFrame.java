package view.mainFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

import app.App;
import view.localizationManager.LocalizationObserver;

/**
 * 
 * @author Dusan
 *
 */
public class MainAppFrame extends JFrame implements LocalizationObserver {

	private static final long serialVersionUID = 6322913707486736787L;
	private MainAppToolbar mainAppToolbar = new MainAppToolbar();
	private MainAppTreePanel treePanel = new MainAppTreePanel();
	private MainAppMenuBar mainAppMenubar = new MainAppMenuBar();
	private MainAppPanel mainAppPanel = new MainAppPanel();
	private MainAppStatusBar mainAppStatusBar = new MainAppStatusBar();

	/**
	 * Glavni prozor aplikacije
	 */
	public MainAppFrame() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		setTitle(resourceBundle.getString("menu.title"));
		App.INSTANCE.getLocalizationManager().addLocalizationObserver(this);
		
		// podesavanje izgleda glavnog prozora
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int width = (int) screenSize.getWidth() * 3 / 4;
		int height = (int) screenSize.getHeight() * 3 / 4;
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		//setovana the palma za ikonicu
		setIconImage((new ImageIcon("resources/palm-tree.png")).getImage());
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
		
		mainAppStatusBar.setPreferredSize(new Dimension(width, 30));
		
		
		getContentPane().add(mainAppStatusBar, BorderLayout.SOUTH);
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
	
	
	/**
	 * @return the mainAppStatusBar
	 */
	public MainAppStatusBar getMainAppStatusBar() {
		return mainAppStatusBar;
	}

	/**
	 * @param mainAppStatusBar the mainAppStatusBar to set
	 */
	public void setMainAppStatusBar(MainAppStatusBar mainAppStatusBar) {
		this.mainAppStatusBar = mainAppStatusBar;
	}

	@Override
	public void updateLanguage(String language) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		setTitle(resourceBundle.getString("menu.title"));
	}

}
