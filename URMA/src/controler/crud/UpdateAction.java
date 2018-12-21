package controler.crud;

import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.AbstractAction;
import view.CrudWindow;
import view.fieldFactory.IField;

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
	
	public UpdateAction(CrudWindow parentCaller) {
		putValue(NAME, "Update");
		this.parentCaller = parentCaller;
	}

	/**
		Akcija koja se poziva nakon popunjavanja polja za update akciju u CrudWindow-u da bi izvršila akciju nad bazom		
		@author - Jelena
	**/
	@Override
	public void actionPerformed(ActionEvent e) {
		Map<String, Object> fields = parentCaller.getFields();
		System.out.println("UPDATE");
		for (String mapKey : fields.keySet()) {
			System.out.println(mapKey + " " + ((IField)fields.get(mapKey)).getValue().toString());
		}
	}
}
