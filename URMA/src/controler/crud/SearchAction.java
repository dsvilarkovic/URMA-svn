package controler.crud;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;

import app.App;
import controler.handlers.IHandler;
import view.CrudWindow;

/**
 * Akcija koja služi za search nad bazom
 * @author JelenaS
 */

public class SearchAction extends AbstractAction{
	private static final long serialVersionUID = 1L;
	public CrudWindow parentCaller;
	
	public SearchAction(CrudWindow parentCaller) {
		putValue(NAME, "Create");
		this.parentCaller = parentCaller;
	}

	/**
		Akcija koja se poziva nakon popunjavanja polja za search akciju u CrudWindow-u da bi izvršila akciju nad bazom		
		@author - Jelena
	**/
	@Override
	public void actionPerformed(ActionEvent e) {
		Map<String, Object> fields = parentCaller.getFields();
		System.out.println("SEARCH");
		
		IHandler handler = App.INSTANCE.getFactory().createHandler();
		
		if(handler.create(parentCaller.getTable(), (HashMap<String, Object>) fields)){
			parentCaller.dispose();
		}
		
		
	}
}
