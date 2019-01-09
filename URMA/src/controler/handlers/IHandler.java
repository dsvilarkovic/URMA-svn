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
	/**
		Create akcija nad informacionim resursom		
		@author - Jelena
		@param table - Tabela nad kojom se izvršava akcija
		@param data - podatci koji se unose u tabelu u obliku mape(kolona, podatak)  
		@return - uspesnost operacije
	**/
	public Boolean create(Table table, HashMap<String, Object> data);
	public Vector<Vector<Object>> read(Table table);
	
	/**
		Update akcija nad resursom		
		@author - Jelena
		@param table - Tabela nad kojom se izvršava akcija
		@param data - podatci koji se unose u tabelu u obliku mape(kolona, podatak)  
		@return - uspesnost operacije
	**/
	public Boolean update(Table table, HashMap<String, Object> data);
	
	/**
		Delete akcija nad resursom		
		@author - Jelena
		@param table - Tabela nad kojom se izvršava akcija
		@param values - podatci koji se brišu
	**/
	public void delete(Table table, Vector<Object> values);
	public Boolean search(Table table, HashMap<String, Object> data);
}
