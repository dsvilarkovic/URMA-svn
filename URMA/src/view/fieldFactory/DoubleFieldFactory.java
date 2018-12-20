package view.fieldFactory;

/**
 * 
 * @author jelena
 *
 */

public class DoubleFieldFactory  implements IFieldFactory{

	@Override
	public IField createField() {
		return new DoubleField();
	}

	@Override
	public IField[] createDoubleField() {
		IField[] searchField = new IField[2];
		searchField[0] = new DoubleField();
		searchField[1] = new DoubleField();
		return searchField;
	}

}
