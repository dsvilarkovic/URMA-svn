package view.fieldFactory;

/**
 * Interfejs fabrika koja kreira polja za crud akcije
 * @author Jelena
 *
 */

public interface IFieldFactory {
	/**
		Fabrika za kreiranje polja različitih tipova, implementiraju je konkretne fabrike za svaki tip	
		@author - Jelena
		@return - polje izabranog tipa
	**/
	IField createField();
	
	/**
		Fabrika za kreiranje po 2 polja različitih tipova, implementiraju je konkretne fabrike za svaki tip. Potrebna je za search akciju
		@author - Jelena
		@return - polje izabranog tipa
	**/
	IField[] createDoubleField();
}