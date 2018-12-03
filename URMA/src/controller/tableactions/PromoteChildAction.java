package controller.tableactions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import app.App;

/**
 * Akcija koja sluzi da preveze poziv na medijatora <code> TableMediator </code> koji radi ostatak posla
 * @author Dusan
 *
 */
public class PromoteChildAction extends AbstractAction {


	private static final long serialVersionUID = -2572792513503326488L;

	/**
	 * Akcija koja sluzi da preveze poziv na medijatora <code> TableMediator </code> koji radi ostatak posla
	 *
	 */
	public PromoteChildAction() {
		putValue(NAME, "Promote");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		App.INSTANCE.getTableMediator().promoteChild();
	}

}
