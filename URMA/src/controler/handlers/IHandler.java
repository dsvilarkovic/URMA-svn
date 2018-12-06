package controler.handlers;

import java.util.Vector;

/**
 * @author filip
 *
 */
public interface IHandler {
	public void create();
	public Vector<Vector<Object>> read(String tableCode, int attributeNumber);
	public void update();
	public void delete();
}
