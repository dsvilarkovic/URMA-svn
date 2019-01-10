 package view.table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import model.Table;
import view.localizationManager.LocalizationObserver;
import controler.crud.DeleteAction;
import controler.tableActions.CreateCrudWindowAction;
import controler.tableActions.DemoteParentAction;
import controler.tableActions.PromoteChildAction;


/**
 * Sluzi za gornji deo dugmati koja sluze za upravljanje
 * @author Dusan
 *
 */
public class TablePanel extends JPanel implements LocalizationObserver {

	
	private static final long serialVersionUID = 1988065650430728153L;
	protected JTable tableView;

	private JButton addButton = new JButton();
	private JButton removeButton = new JButton();
	private JButton updateButton = new JButton();
	private JButton searchButton = new JButton();

	private JButton changeableButton = new JButton("");
	private ButtonGroup buttonGroup = new ButtonGroup();
	private String title;
	private Border titledBorder;

	/**
	 * Konstruktor nadpanela kojim se opisuju i {@link ParentTablePanel} i {@link ChildTablePanel}
	 * @param title - naslov pod kojim ce se nazivati panel
	 */
	public TablePanel(String title) {
		this.title = title;
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		
		
		setLayout(new BorderLayout());
		Border blackline = BorderFactory.createMatteBorder(1,1, 1, 1, Color.black);
		titledBorder = BorderFactory.createTitledBorder(blackline, resourceBundle.getString("table." + title));

		this.setBorder(titledBorder);
		
		
		
		
		
		initTables();
		
		initButtons();
		setButtons();
		setButtonActions();
	}	
	
	/**
	 * Inicijalizacija dugmica na odgovarajucem jeziku
	 */
	private void initButtons() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		
		addButton.setText(resourceBundle.getString("table.button.add")); 
		removeButton.setText(resourceBundle.getString("table.button.remove"));
		updateButton.setText(resourceBundle.getString("table.button.update"));
		searchButton.setText(resourceBundle.getString("table.button.search"));
	}
	
	/**
	 * Sluzi za postavljajne akcija na dugmice
	 */
	private void setButtonActions() {
		//podesavanje akcija
		addButton.setAction(new CreateCrudWindowAction(this, addButton.getText()));
//		removeRow.setAction(new CreateCrudWindowAction(this, removeRow.getText()));
		removeButton.addActionListener(new DeleteAction(this));
		updateButton.setAction(new CreateCrudWindowAction(this, updateButton.getText()));
		searchButton.setAction(new CreateCrudWindowAction(this, searchButton.getText()));
	}

	private void initTables() {
		tableView = new JTable();
		
		tableView.setModel(new TableModel(new Table()));

		// PoÅ¾eljna veliÄ�ina pogleda tabele u okviru scrollpane-a. Layout
		// manager uzima ovu osobinu u obzir.
		Toolkit kit = Toolkit.getDefaultToolkit();
	    Dimension screenSize = kit.getScreenSize();
		tableView.setPreferredScrollableViewportSize(new Dimension(500, screenSize.height/4));
		
		// Å irenje tabele kompletno po visini pogleda scrollpane-a.
		tableView.setFillsViewportHeight(true);
	}
	
	public TableModel getTableModel() {
		return (TableModel) tableView.getModel();
	}
	
	/**
	 * Funkcija za podesavanje dugmadi u vizuelnoj predstavi
	 */
	private void setButtons() {				
		
		buttonGroup.add(addButton);
		buttonGroup.add(removeButton);
		buttonGroup.add(updateButton);
		buttonGroup.add(searchButton);
		//buttonGroup.add(changeableButton);
		
		
		
		Box box = Box.createHorizontalBox();
		box.add(Box.createHorizontalStrut(5));
		box.add(addButton);
		box.add(Box.createHorizontalStrut(5));
		box.add(removeButton);
		box.add(Box.createHorizontalStrut(5));
		box.add(updateButton);
		box.add(Box.createHorizontalStrut(5));
		box.add(searchButton);
		box.add(Box.createHorizontalGlue());
		box.add(changeableButton);
		box.add(Box.createHorizontalStrut(15));
		
		//add(box);		
		add(box, BorderLayout.NORTH);
	}
	/**
	 * Dodavanje tabova za {@link TablePanel}
	 * @param jTabbedPane
	 */
	public void addTableTabs(JTabbedPane jTabbedPane) {
		add(jTabbedPane, BorderLayout.CENTER);
	}
	
	/**
	 * Podesavanje dugmadi za promociju i demociju clanova tabele.
	 * @param className - parametar imena dugmeta za koje se podesava akcija
	 */
	public void setChangeableButtonAction(String className) {
		switch (className) {
		case "Child":
			changeableButton.setAction(new PromoteChildAction());
			break;

		case "Parent":
			changeableButton.setAction(new DemoteParentAction());
			break;
		default:
			break;
		}
		
	}

	@Override
	public void updateLanguage() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		((TitledBorder) titledBorder).setTitle(resourceBundle.getString("table." + title));
		initButtons();
		setButtonActions();
		repaint();
	}


}
