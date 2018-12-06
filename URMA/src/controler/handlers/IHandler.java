package controler.handlers;

import java.util.Vector;

import model.Table;

/**
 * @author filip
 *
 */
public interface IHandler {
	public void create();
	public Vector<Vector<Object>> read(Table table);
	public void update();
	public void delete();
}
