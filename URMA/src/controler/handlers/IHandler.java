package controler.handlers;

import java.util.HashMap;
import java.util.Vector;

import model.Table;

/**
 * Interfejs koji sadrzi CRUD operacije
 * da ga naslede zasebni handleri
 * @author filip
 */
public interface IHandler {
	public Boolean create(Table table, HashMap<String, Object> data);
	public Vector<Vector<Object>> read(Table table);
	public void update();
	public void delete();
}
