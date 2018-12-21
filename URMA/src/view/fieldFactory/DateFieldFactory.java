package view.fieldFactory;

/**
 * Fabrika koja kreira date polja za crud akcije
 * @author Jelena
 *
 */

public class DateFieldFactory implements IFieldFactory{

	/**
		Fabrika za kreiranje date polja	
		@author - Jelena
		@return - date polje
	**/
	@Override
	public IField createField() {
		return new DateField();
	}

	/**
		Fabrika za kreiranje 2 date polja, potrebno za search(od-do)
		@author - Jelena
		@return - date polje
	**/
	@Override
	public IField[] createDoubleField() {
		IField[] searchField = new IField[2];
		searchField[0] = new DateField();
		searchField[1] = new DateField();
		return searchField;
	}

}
