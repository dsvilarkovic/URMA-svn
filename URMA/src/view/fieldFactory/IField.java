package view.fieldFactory;

/**
 * Intefrfejs koja opisuje polja koja su potrebna za crud akcije
 * @author Jelena
 *
 */

public interface IField {
	/**
		Metoda koja validira polje, implementiraju je validatori za konkretna polja	
		@author - Jelena
	**/
	public Boolean validateField() ;
	public Object getField();
	public void setField(Object o);
	public void setValue(Object o);
	public Object getValue();
}