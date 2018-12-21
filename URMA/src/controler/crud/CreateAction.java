package controler.crud;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;

import app.App;
import controler.handlers.IHandler;
import model.resourceFactory.IResourceFactory;
import view.CrudWindow;
import view.fieldFactory.IField;
import view.table.ParentTablePanel;

/**
 * Akcija koja služi za create nad bazom
 * @author Jelena
 *
 */

public class CreateAction extends AbstractAction{
	private static final long serialVersionUID = 1L;
	public CrudWindow parentCaller;
	
	public CreateAction(CrudWindow parentCaller) {
		putValue(NAME, "Create");
		this.parentCaller = parentCaller;
	}

	/**
		Akcija koja se poziva nakon popunjavanja polja za create akciju u CrudWindow-u da bi izvršila akciju nad bazom		
		@author - Jelena
	**/
	@Override
	public void actionPerformed(ActionEvent e) {
		Map<String, Object> fields = parentCaller.getFields();
		System.out.println("CREATE");
//		for (String mapKey : fields.keySet()) {
//			System.out.println(mapKey + " " + ((IField)fields.get(mapKey)).getValue().toString());
//		}
		App.INSTANCE.setFactory("db");
		IResourceFactory factory = App.INSTANCE.getFactory();
		IHandler handler = factory.createHandler();
		
		handler.create(parentCaller.getTable(), (HashMap<String, Object>) fields);
		parentCaller.dispose();
//		System.out.println(parentCaller.getClass());
	}
}
