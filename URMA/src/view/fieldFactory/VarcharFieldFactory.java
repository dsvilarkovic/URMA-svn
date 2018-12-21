package view.fieldFactory;

/**
 * Fabrika koja kreira varchar polja za crud akcije
 * @author Jelena
 *
 */

public class VarcharFieldFactory implements IFieldFactory{

	/**
		Fabrika za kreiranje varchar polja	
		@author - Jelena
		@return - varchar polje
	**/
	@Override
	public IField createField() {
		return new VarcharField();
	}
	
	/**
		Fabrika za kreiranje varchar polja	
		@author - Jelena
		@return - varchar polje
	**/
	@Override
	public IField[] createDoubleField() {
		IField[] searchField = new IField[2];
		searchField[0] = new VarcharField();
		searchField[1] = null;
		return searchField;
	}
}
