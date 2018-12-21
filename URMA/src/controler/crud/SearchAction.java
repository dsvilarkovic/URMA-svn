package controler.crud;

import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.AbstractAction;

import view.CrudWindow;
import view.fieldFactory.DecoratedField;
import view.fieldFactory.IField;

/**
 * Akcija koja služi za search nad bazom
 * @author Jelena
 *
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
		for (String mapKey : fields.keySet()) {
			if(((DecoratedField[])fields.get(mapKey))[1] != null){
				System.out.println(mapKey + " " + ((IField)((DecoratedField[])fields.get(mapKey))[0].getField()).getValue().toString() + "(" + ((DecoratedField[])fields.get(mapKey))[0].getValue().toString() + ") - " + ((IField)((DecoratedField[])fields.get(mapKey))[1].getField()).getValue().toString() + "(" + ((DecoratedField[])fields.get(mapKey))[1].getValue().toString() + ")");
			}else {
				System.out.println(mapKey + " " + ((IField)((DecoratedField[])fields.get(mapKey))[0].getField()).getValue().toString() + "(" + ((DecoratedField[])fields.get(mapKey))[0].getValue().toString() + ")");
			}
		}
	}
}
