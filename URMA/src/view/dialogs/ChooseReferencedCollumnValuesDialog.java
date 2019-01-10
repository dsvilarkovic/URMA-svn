package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Attribute;
import model.Relation;
import model.Table;
import view.table.TableModel;
import app.App;
import controler.crud.ForeignKeyAction;

/**
 * Klasa koja sluÅ¾i za predstavu dijaloga u kojem ce se nalaziti tabela
 * odakle Ä‡e se birati torka sa referenciranim vrednostima u stranom kljuÄ�u.
 * VraÄ‡a odabranu torku sa vrednostima koje se biraju kako je zapisano.
 * @author Dusan
 *
 */
public class ChooseReferencedCollumnValuesDialog extends JDialog {

	private static final long serialVersionUID = -274698934217211351L;
	private TableModel tableModel = null;
	private JTable tableView = null;
	private JTabbedPane tabbedPane = new JTabbedPane();
	private JButton chooseButton = null;
	private ForeignKeyAction parentCaller = null;
	
	
	/**
	 * 
	 * Klasa koja sluÅ¾i za predstavu dijaloga u kojem ce se nalaziti tabela
	 * odakle Ä‡e se birati torka sa referenciranim vrednostima u stranom kljuÄ�u.
	 * VraÄ‡a odabranu torku sa vrednostima koje se biraju kako je zapisano.
	 * @author Dusan
	 * @param table - tabela po kojoj ce se praviti dijalog
	 * @param parentCaller - akcija iz koje se poziva dijalog
	 */
	public ChooseReferencedCollumnValuesDialog(Table table, ForeignKeyAction parentCaller) {
		this.parentCaller = parentCaller;
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		
		JPanel chooseButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		chooseButton = new JButton("Confirm selected row");		
		chooseButton.setEnabled(false);
		
		
		chooseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closeDialog();
			}
		});
		chooseButtonPanel.add(chooseButton);
		
		setTitle("Choose referenced collumn values");
		
		//TODO @Jelena, @Dusan stavi zakomentarisano
		tableModel = new TableModel(table);//findTableModel(table);
		tableModel.setUpData();
		
		tableView = init_table(tableModel);
		
		
		tabbedPane.add(new JScrollPane(tableView));
		tabbedPane.setTitleAt(0, table.getTitle());
		
		
		
		
		contentPanel.add(chooseButtonPanel,BorderLayout.SOUTH);
		contentPanel.add(tabbedPane, BorderLayout.CENTER);
		
		
		add(contentPanel);
		setSize(400,300);
		setVisible(true);
	}
	
	/**
	 * Metoda koja se koristi za gasenje dijaloga.
	 * @author Dusan
	 */
	private void closeDialog() {
		Map<String, Object> selectedRow = getSelectedRowValues();
		dispose();
		parentCaller.fillFields(selectedRow);
	}

	/**
	 * Inicijalizacija tabele sa svim njenim potrebnim funkcionalnostima
	 * @author Dusan
	 * @param tableModel - model po kojem se podesavaju podaci
	 * @return - inicijalizovanu {@link JTable}
	 */
	private JTable init_table(TableModel tableModel) {
		JTable tableView = new JTable(tableModel);
		tableView.setPreferredScrollableViewportSize(new Dimension(400, 200));
		tableView.setFillsViewportHeight(true);
		
		tableView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//podesi za neselektovanje da je zakljucano dugme
		tableView.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				boolean isButtonDisabled = tableView.getSelectedRow() == -1;
				chooseButton.setEnabled(!isButtonDisabled);
			}
		});
		
		return tableView;
	}
	/**
	 * Nalazi selektovani {@link TableModel} na osnovu cijeg table-a je radjen CRUD prozor
	 * @author Dusan
	 * @param table - tabela po kojoj se trazi, tipa {@code Table}
	 * @return - odgovarajuca pozivana {@link TableModel} tabela
	 */
	@SuppressWarnings("unused")
	private TableModel findTableModel(Table table) {
		return App.INSTANCE.getTableMediator().getCalledTableModel(table);
	}
	
	/**
	 * Vraca vrednosti odabrane kolone u tabeli u  {@code Map<String, Object>}  obliku
	 * @author Dusan
	 * @return mapa objekata vrednosti selektovane torke
	 */
	private Map<String, Object> getSelectedRowValues(){
		Map<String, Object> selectedRowRelation = new HashMap<String, Object>();
		Map<String, Object> selectedRow = new HashMap<String, Object>();
		int selectedRowIndex = tableView.getSelectedRow();
		Table table = tableModel.getTable();
		
		for (int columnIndex = 0; columnIndex < tableView.getColumnCount(); columnIndex++) {
			
//			Object colValue = tableView.getValueAt(selectedRowIndex, columnIndex);
//			System.out.println(tableView.getColumnName(columnIndex) + " " + tableView.getValueAt(selectedRowIndex, columnIndex));
			selectedRowRelation.put(tableView.getColumnName(columnIndex), tableView.getValueAt(selectedRowIndex, columnIndex))	;		
		}
		
		Relation relation = findRelation(parentCaller.getParentCaller().getTable(), table);
		
		Iterator<Attribute> iterSource = relation.getSourceKeys().iterator();
		Iterator<Attribute> iterDestination = relation.getDestinationKeys().iterator();
		

		while(iterSource.hasNext()) {			
			Attribute sourceAttribute = iterSource.next();
			Attribute destinationAttribute = iterDestination.next();
			
//			System.out.println(sourceAttribute.getTitle() + " + " + destinationAttribute.getTitle());
//			System.out.println(selectedRowRelation.get(sourceAttribute.getTitle()));
			System.out.println("value: " + destinationAttribute.getTitle() + " " + selectedRowRelation.get(sourceAttribute.getTitle()));
			selectedRow.put(destinationAttribute.getTitle(), selectedRowRelation.get(sourceAttribute.getTitle()));
		}
		
		
		
		return selectedRow;
	}
	
	/**
	 * Sluzi za trazenje relacije po kojoj su <code>childTableModel</code> i <br>
	 *  <code>parentTableModel</code> povezani
	 * @author Dusan
	 * @param childTable - tabela koja se nalazi u donjem TablePanelu
	 * @param parentTable- tabela koja se nalazi u gornjem TablePanel-u
	 */
	public Relation findRelation(Table childTable, Table parentTable) {
		
		//nadji tu relaciju
		Map<String, Relation> relations = App.INSTANCE.getModel().getRelations();
		System.out.println(relations);
		//nadji relaciju gde se ove dve tabele nalaze
		for (String relationKey : relations.keySet()) {
			Relation relationValue = relations.get(relationKey);
			if(relationValue.getDestinationTable().getCode().equals(childTable.getCode())
					&& relationValue.getSourceTable().getCode().equals(parentTable.getCode())) {
				return relationValue;
			}
				
		}
		
		return null;
	}
}
