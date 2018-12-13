package view.fieldFactory;

/**
 * 
 * @author jelena
 *
 */

public class DateFieldFactory implements IFieldFactory{

	@Override
	public IField createField() {
		return new DateField();
	}

	@Override
	public IField[] createDoubleField() {
		IField[] searchField = new IField[2];
		searchField[0] = new DateField();
		searchField[1] = new DateField();
		return searchField;
	}

}
