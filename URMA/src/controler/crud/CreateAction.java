package controler.crud;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import app.App;
import controler.handlers.IHandler;
import model.resourceFactory.IResourceFactory;
import view.CrudWindow;

/**
 * Akcija koja služi za create nad bazom
 * @author Jelena
 *
 */

public class CreateAction extends AbstractAction{
	private static final long serialVersionUID = 1L;
	public CrudWindow parentCaller;
	
	/**
		Konstruktor akcije koja se poziva nakon popunjavanja polja za create akciju u CrudWindow-u da bi izvršila akciju nad bazom		
		@author - Jelena
		@param parentCaller - prozor iz kojeg je pozvana akcija
	**/
	public CreateAction(CrudWindow parentCaller) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		putValue(NAME, resourceBundle.getString("button.create"));
		this.parentCaller = parentCaller;
	}

	/**
		Akcija koja se poziva nakon popunjavanja polja za create akciju u CrudWindow-u da bi izvršila akciju nad bazom		
		@author - Jelena
	**/
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Map<String, Object> fields = parentCaller.getFields();
	//		for (String mapKey : fields.keySet()) {
	//			System.out.println(mapKey + " " + ((IField)fields.get(mapKey)).getValue().toString());
	//		}
			App.INSTANCE.setFactory("db");
			IResourceFactory factory = App.INSTANCE.getFactory();
			IHandler handler = factory.createHandler();
			
			if(handler.create(parentCaller.getTable(), (HashMap<String, Object>) fields)){
				parentCaller.dispose();
			}
		}catch (Exception e1) {
			ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
			JOptionPane.showMessageDialog(null, resourceBundle.getString("table.emptyerror"), resourceBundle.getString("table.error"),
					  JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
}
