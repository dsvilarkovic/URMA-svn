package view.fieldFactory;

/**
 * Fabrika koja kreira integer polja za crud akcije
 * @author Jelena
 *
 */

public class IntegerFieldFactory  implements IFieldFactory{

	/**
		Fabrika za kreiranje  integer polja
		@author - Jelena
		@return - integer polje
	**/
	@Override
	public IField createField() {
		return new IntegerField();
	}

	/**
		Fabrika za kreiranje 2 integer polja, potrebno za search(od-do)
		@author - Jelena
		@return - integer polje
	**/
	@Override
	public IField[] createDoubleField() {
		IField[] searchField = new IField[2];
		searchField[0] = new IntegerField();
		searchField[1] = new IntegerField();
		return searchField;
	}

}
