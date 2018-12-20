package view.fieldFactory;

/**
 * 
 * @author jelena
 *
 */

public class CharFieldFactory implements IFieldFactory{

	@Override
	public IField createField() {
		return new CharField();
	}

	@Override
	public IField[] createDoubleField() {
		IField[] searchField = new IField[2];
		searchField[0] = new CharField();
		searchField[1] = null;
		return searchField;
	}
}
