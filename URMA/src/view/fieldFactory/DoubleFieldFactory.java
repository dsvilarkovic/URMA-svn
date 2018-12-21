package view.fieldFactory;

/**
 * Fabrika koja kreira double polja za crud akcije
 * @author Jelena
 *
 */

public class DoubleFieldFactory  implements IFieldFactory{

	/**
		Fabrika za kreiranje  double polja
		@author - Jelena
		@return - double polje
	**/
	@Override
	public IField createField() {
		return new DoubleField();
	}

	/**
		Fabrika za kreiranje 2 double polja, potrebno za search(od-do)
		@author - Jelena
		@return - double polje
	**/
	@Override
	public IField[] createDoubleField() {
		IField[] searchField = new IField[2];
		searchField[0] = new DoubleField();
		searchField[1] = new DoubleField();
		return searchField;
	}

}
