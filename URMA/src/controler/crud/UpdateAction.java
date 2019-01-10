package controler.crud;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;

import app.App;
import controler.handlers.IHandler;
import model.resourceFactory.IResourceFactory;
import view.CrudWindow;

/**
 * Akcija koja služi za update nad bazom
 * @author Jelena
 *
 */

public class UpdateAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CrudWindow parentCaller;
	
	/**
		Konstruktor akcije koja se poziva nakon popunjavanja polja za update akciju u CrudWindow-u da bi izvršila akciju nad bazom		
		@author - Jelena
		@param parentCaller - prozor iz kojeg je pozvana akcija
	**/
	public UpdateAction(CrudWindow parentCaller) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		putValue(NAME, resourceBundle.getString("button.update"));
		this.parentCaller = parentCaller;
	}

	/**
		Akcija koja se poziva nakon popunjavanja polja za update akciju u CrudWindow-u da bi izvršila akciju nad bazom		
		@author - Jelena
		@param e - event
	**/
	@Override
	public void actionPerformed(ActionEvent e) {
		Map<String, Object> fields = parentCaller.getFields();
		App.INSTANCE.setFactory(App.INSTANCE.getHandlerType());
		//App.INSTANCE.setFactory("db");
		IResourceFactory factory = App.INSTANCE.getFactory();
		IHandler handler = factory.createHandler();
		
		if(handler.update(parentCaller.getTable(), (HashMap<String, Object>) fields)){
			parentCaller.dispose();
		}
	}
}
