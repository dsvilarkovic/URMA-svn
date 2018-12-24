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

import app.App;
import controler.tableActions.CreateCrudWindowAction;
import controler.tableActions.DemoteParentAction;
import controler.tableActions.PromoteChildAction;
import model.Table;
import view.localizationManager.LocalizationObserver;


/**
 * Sluzi za gornji deo dugmati koja sluze za upravljanje
 * @author Dusan
 *
 */
public class TablePanel extends JPanel implements LocalizationObserver {

	
	private static final long serialVersionUID = 1988065650430728153L;
	protected JTable tableView;
	private JButton addRow = new JButton();
	private JButton removeRow = new JButton();
	private JButton updateRow = new JButton();
	private JButton search = new JButton();
	private JButton changeableButton = new JButton("");
	private ButtonGroup buttonGroup = new ButtonGroup();
	private String title;
	private Border titledBorder;

	public TablePanel(String title) {
		this.title = title;
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		App.INSTANCE.getLocalizationManager().addLocalizationObserver(this);
		
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
		
		addRow.setText(resourceBundle.getString("table.button.add")); 
		removeRow.setText(resourceBundle.getString("table.button.remove"));
		updateRow.setText(resourceBundle.getString("table.button.update"));
		search.setText(resourceBundle.getString("table.button.search"));
	}
	
	/**
	 * Sluzi za postavljajne akcija na dugmice
	 */
	private void setButtonActions() {
		//podesavanje akcija
		
		addRow.setAction(new CreateCrudWindowAction(this, addRow.getText()));
		removeRow.setAction(new CreateCrudWindowAction(this, removeRow.getText()));
		updateRow.setAction(new CreateCrudWindowAction(this, updateRow.getText()));
		search.setAction(new CreateCrudWindowAction(this, search.getText()));
	}

	private void initTables() {
		tableView = new JTable();
		
		tableView.setModel(new TableModel(new Table()));

		// Poželjna veličina pogleda tabele u okviru scrollpane-a. Layout
		// manager uzima ovu osobinu u obzir.
		Toolkit kit = Toolkit.getDefaultToolkit();
	    Dimension screenSize = kit.getScreenSize();
		tableView.setPreferredScrollableViewportSize(new Dimension(500, screenSize.height/4));
		
		// Širenje tabele kompletno po visini pogleda scrollpane-a.
		tableView.setFillsViewportHeight(true);
	}
	/**
	 * Funkcija za podesavanje dugmadi
	 */
	private void setButtons() {				
		
		buttonGroup.add(addRow);
		buttonGroup.add(removeRow);
		buttonGroup.add(updateRow);
		buttonGroup.add(search);
		//buttonGroup.add(changeableButton);
		
		
		
		Box box = Box.createHorizontalBox();
		box.add(Box.createHorizontalStrut(5));
		box.add(addRow);
		box.add(Box.createHorizontalStrut(5));
		box.add(removeRow);
		box.add(Box.createHorizontalStrut(5));
		box.add(updateRow);
		box.add(Box.createHorizontalStrut(5));
		box.add(search);
		box.add(Box.createHorizontalGlue());
		box.add(changeableButton);
		box.add(Box.createHorizontalStrut(15));
		
		//add(box);		
		add(box, BorderLayout.NORTH);
	}
	
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
	public void updateLanguage(String language) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		((TitledBorder) titledBorder).setTitle(resourceBundle.getString("table." + title));
		initButtons();
		repaint();
	}


}
