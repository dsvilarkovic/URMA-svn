package view.fieldFactory;

/**
 * Fabrika koja kreira boolean polja za crud akcije
 * @author Jelena
 *
 */

public class BooleanFieldFactory implements IFieldFactory{

	/**
		Fabrika za kreiranje boolean polja	
		@author - Jelena
		@return - boolean polje
	**/
	@Override
	public IField createField() {
		return new BooleanField();
	}
	
	/**
		Fabrika za kreiranje boolean polja	
		@author - Jelena
		@return - boolean polje
	**/
	@Override
	public IField[] createDoubleField() {
		IField[] searchField = new IField[2];
		searchField[0] = new BooleanField();
		searchField[1] = null;
		return searchField;
	}

}
