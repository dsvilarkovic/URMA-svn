package view.fieldFactory;

public class BooleanFieldFactory implements IFieldFactory{

	@Override
	public IField createField() {
		return new BooleanField();
	}

	@Override
	public IField[] createDoubleField() {
		IField[] searchField = new IField[2];
		searchField[0] = new BooleanField();
		searchField[1] = null;
		return searchField;
	}

}
