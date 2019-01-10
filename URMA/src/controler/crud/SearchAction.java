package controler.crud;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;

import app.App;
import controler.handlers.IHandler;
import view.CrudWindow;

/**
 * Akcija koja služi za search nad bazom
 * @author Jelena
 */

public class SearchAction extends AbstractAction{
	private static final long serialVersionUID = 1L;
	public CrudWindow parentCaller;
	
	public SearchAction(CrudWindow parentCaller) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		putValue(NAME, resourceBundle.getString("button.search"));
		this.parentCaller = parentCaller;
	}

	/**
		Akcija koja se poziva nakon popunjavanja polja za search akciju u CrudWindow-u da bi izvršila akciju nad bazom		
		@author - Jelena
		@param e - event
	**/
	@Override
	public void actionPerformed(ActionEvent e) {
		Map<String, Object> fields = parentCaller.getFields();
		System.out.println("SEARCH");
		
		//App.INSTANCE.setFactory("db");
		//IResourceFactory factory = App.INSTANCE.getFactory();
		IHandler handler = App.INSTANCE.getFactory().createHandler();
		
		if(handler.search(parentCaller.getTable(), (HashMap<String, Object>) fields) != null){
			parentCaller.dispose();
		}
		
		
	}
}
