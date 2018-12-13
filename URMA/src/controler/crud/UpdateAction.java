package controler.crud;

import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.AbstractAction;
import view.CrudWindow;
import view.fieldFactory.IField;

/**
 * 
 * @author jelena
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Map<String, Object> fields = parentCaller.getFields();
		System.out.println("UPDATE");
		for (String mapKey : fields.keySet()) {
			System.out.println(mapKey + " " + ((IField)fields.get(mapKey)).getValue().toString());
		}
	}
}
