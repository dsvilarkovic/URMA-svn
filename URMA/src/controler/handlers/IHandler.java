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
	public Boolean update(Table table, HashMap<String, Object> data);
	public void delete(Table table, Vector<Object> values);
	public Boolean search(Table table, HashMap<String, Object> data);
}
