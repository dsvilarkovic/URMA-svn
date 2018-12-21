package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import app.App;
import model.Table;
import view.table.TableModel;

/**
 * Klasa koja služi za predstavu dijaloga u kojem ce se nalaziti tabela
 * odakle će se birati torka sa referenciranim vrednostima u stranom ključu.
 * Vraća odabranu torku sa vrednostima koje se biraju kako je zapisano.
 * @author Dusan
 *
 */
public class ChooseReferencedCollumnValuesDialog extends JDialog {

	private TableModel tableModel = null;
	private JTable tableView = null;
	private JTabbedPane tabbedPane = new JTabbedPane();
	private JButton chooseButton = null;
	
	
	/**
	 * 
	 * Klasa koja služi za predstavu dijaloga u kojem ce se nalaziti tabela
	 * odakle će se birati torka sa referenciranim vrednostima u stranom ključu.
	 * Vraća odabranu torku sa vrednostima koje se biraju kako je zapisano.
	 * @param table - tabela po kojoj ce se praviti dijalog
	 */
	public ChooseReferencedCollumnValuesDialog(Table table) {
		
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
	 */
	private void closeDialog() {
		//TODO: @Jelena implementiraj kako ti bude potrebno
		String text = "Odabrani red je :  \n";
		
		Vector<Object> selectedRow = getSelectedRowValues();
		for (Object object : selectedRow) {
			text += object.toString() + " | ";
		}
		JOptionPane.showMessageDialog(null,text,"Naslov",JOptionPane.INFORMATION_MESSAGE);
		dispose();
	}
	
	/**
	 * Inicijalizacija tabele sa svim njenim potrebnim funkcionalnostima
	 * @param tableModel - model po kojem se podesavaju podaci
	 * @return - inicijalizovanu jtabelu
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
	 * Nalazi selektovani tableModel na osnovu cijeg table-a je radjen CRUD prozor
	 * @param table
	 * @return
	 */
	private TableModel findTableModel(Table table) {
		return App.INSTANCE.getTableMediator().getCalledTableModel(table);
	}
	
	/**
	 * Vraca vrednosti odabrane kolone u tabeli
	 * @return
	 */
	private Vector<Object> getSelectedRowValues(){
		Vector<Object> selectedRow = new Vector<>();
		int selectedRowIndex = tableView.getSelectedRow();
		
		for (int columnIndex = 0; columnIndex < tableView.getColumnCount(); columnIndex++) {
			
			Object colValue = tableView.getValueAt(selectedRowIndex, columnIndex);
			selectedRow.add(colValue);			
		}
		
		return selectedRow;
	}
}
