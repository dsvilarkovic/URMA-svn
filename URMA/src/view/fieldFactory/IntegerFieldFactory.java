package view.fieldFactory;

/**
 * 
 * @author jelena
 *
 */

public class IntegerFieldFactory  implements IFieldFactory{

	@Override
	public IField createField() {
		return new IntegerField();
	}

	@Override
	public IField[] createDoubleField() {
		IField[] searchField = new IField[2];
		searchField[0] = new IntegerField();
		searchField[1] = new IntegerField();
		return searchField;
	}

}
