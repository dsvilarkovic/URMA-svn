/**
 * 
 */
package controler.handlers;

import java.util.HashMap;
import java.util.Vector;

import model.Table;

/**
 * Handler za rad sa xml-om
 * @author filip
 */
public class XMLHandler implements IHandler {

	/* (non-Javadoc)
	 * @see controler.handlers.IHandler#create()
	 */
	@Override
	public Boolean create(Table table, HashMap<String, Object> data) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see controler.handlers.IHandler#read()
	 */
	@Override
	public Vector<Vector<Object>> read(Table table) {
		// TODO Auto-generated method stub
		return null;

	}

	/* (non-Javadoc)
	 * @see controler.handlers.IHandler#update()
	 */
	@Override
	public Boolean update(Table table, HashMap<String, Object> data) {
		return null;
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see controler.handlers.IHandler#delete()
	 */
	@Override
	public void delete(Table table, Vector<Object> values) {
		// TODO Auto-generated method stub
	}

	@Override
	public Boolean search(Table table, HashMap<String, Object> data) {
		// TODO Auto-generated method stub
		return null;
	}
}
