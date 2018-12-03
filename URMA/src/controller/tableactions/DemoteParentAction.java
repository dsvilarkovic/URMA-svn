package controller.tableactions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import app.App;

/**
 * Akcija koja sluzi da preveze poziv na medijatora <code> TableMediator </code> koji radi ostatak posla
 * @author Dusan
 *
 */
public class DemoteParentAction extends AbstractAction {

	private static final long serialVersionUID = -7321060857740238634L;

	/**
	 * Akcija koja sluzi da preveze poziv na medijatora <code> TableMediator </code> koji radi ostatak posla
	 *
	 */
	public DemoteParentAction() {
		putValue(NAME, "Demote");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		App.INSTANCE.getTableMediator().demoteParent();		
	}

}
