package controler.tableActions;

import java.awt.event.ActionEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;

import app.App;
import view.localizationManager.LocalizationObserver;

/**
 * Akcija koja sluzi da preveze poziv na medijatora <code> TableMediator </code> koji radi ostatak posla
 * @author Dusan
 *
 */
public class PromoteChildAction extends AbstractAction implements LocalizationObserver {


	private static final long serialVersionUID = -2572792513503326488L;

	/**
	 * Akcija koja sluzi da preveze poziv na medijatora <code> TableMediator </code> koji radi ostatak posla <br>
	 * vezanog za {@code childTablePanel} i {@code parentTablePanel} i promovisanje deteta.
	 * @author Dusan
	 */
	public PromoteChildAction() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		App.INSTANCE.getLocalizationManager().addLocalizationObserver(this);
		putValue(NAME, resourceBundle.getString("table.promote"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		App.INSTANCE.getTableMediator().promoteChild();
	}

	@Override
	public void updateLanguage() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		putValue(NAME, resourceBundle.getString("table.promote"));
		
	}

}
