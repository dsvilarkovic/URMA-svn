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
		@param isReq - da li je polje obavezno
		@param isPK - da li je polje primarni ključ
		@param maxLen - maksimalna dužina polja
		@param precision - preciznost(potrebna za double)
		@return true ako je validno, false ako nije
	**/
	public Boolean validateField(Boolean isReq, Boolean isPK, int maxLen, int precision) ;
	public void setEditable(Boolean editable) ;
	public Object getField();
	public void setField(Object o);
	public void setValue(Object o);
	public Object getValue();
}