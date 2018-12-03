package view.table;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Table;

public class ParentTablePanel extends TablePanelProba {

	private static final long serialVersionUID = 6101952337575963863L;
	private TableModel tableModel;
	public ParentTablePanel(String title) {
		super(title);
		super.setChangeableButtonAction("Parent");
		
		initParentTable();
		// Zaglavlje kolone se ne mora ručno ubacivati. JScrollPane će odraditi
		// taj posao.
		JScrollPane jScrollPane = new JScrollPane(tblStudenti);
		add(jScrollPane);
		
	}
	
	private void initParentTable() {
		this.tableModel = new TableModel(null);

		tblStudenti = new JTable(this.tableModel);

		// Poželjna veličina pogleda tabele u okviru scrollpane-a. Layout
		// manager uzima ovu osobinu u obzir.
		
		tblStudenti.setPreferredScrollableViewportSize(new Dimension(500, 200));
		
		
		// Širenje tabele kompletno po visini pogleda scrollpane-a.
		tblStudenti.setFillsViewportHeight(true);
	}
	
	public void setParentModel(TableModel tableModel) {
		this.tableModel = tableModel;
		revalidate();
		repaint();
	}

	/**
	 * Vraca tabelu koja je u roditeljskom panelu
	 * @return
	 */
	public Table getParentTable() {
		return tableModel.getTable();
	}
	
	


}
