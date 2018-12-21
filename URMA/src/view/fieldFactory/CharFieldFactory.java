package view.fieldFactory;

/**
 * Fabrika koja kreira char polja za crud akcije
 * @author Jelena
 *
 */

public class CharFieldFactory implements IFieldFactory{

	/**
		Fabrika za kreiranje char polja	
		@author - Jelena
		@return - char polje
	**/
	@Override
	public IField createField() {
		return new CharField();
	}

	/**
		Fabrika za kreiranje char polja	
		@author - Jelena
		@return - char polje
	**/
	@Override
	public IField[] createDoubleField() {
		IField[] searchField = new IField[2];
		searchField[0] = new CharField();
		searchField[1] = null;
		return searchField;
	}
}
