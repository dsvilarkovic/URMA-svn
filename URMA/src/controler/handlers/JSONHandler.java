/**
 * 
 */
package controler.handlers;

import java.util.HashMap;
import java.util.Vector;

import model.Table;

/**
 * Handler za rad sa jason-om
 * @author filip
 */
public class JSONHandler implements IHandler {

	/* (non-Javadoc)
	 * @see controler.handlers.IHandler#create()
	 */
	@Override
	public Boolean create(Table table, HashMap<String, Object> datav) {
		return true;
	}

	/* (non-Javadoc)
	 * @see controler.handlers.IHandler#read()
	 */
	@Override
	public Vector<Vector<Object>> read(Table table) {
		return null;

	}

	/* (non-Javadoc)
	 * @see controler.handlers.IHandler#update()
	 */
	@Override
	public Boolean update(Table table, HashMap<String, Object> data) {
		return null;

	}

	/* (non-Javadoc)
	 * @see controler.handlers.IHandler#delete()
	 */
	@Override
	public void delete(Table table, Vector<Object> values) {
	}

	@Override
	public Vector<Vector<Object>> search(Table table, HashMap<String, Object> data) {
		return null;
	}
}
