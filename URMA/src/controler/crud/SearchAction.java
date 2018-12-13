package controler.crud;

import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.AbstractAction;

import view.CrudWindow;
import view.fieldFactory.DecoratedField;
import view.fieldFactory.IField;

/**
 * 
 * @author jelena
 *
 */

public class SearchAction extends AbstractAction{
	private static final long serialVersionUID = 1L;
	public CrudWindow parentCaller;
	
	public SearchAction(CrudWindow parentCaller) {
		putValue(NAME, "Create");
		this.parentCaller = parentCaller;
	}

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
