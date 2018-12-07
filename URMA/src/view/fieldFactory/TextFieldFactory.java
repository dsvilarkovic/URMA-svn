package view.fieldFactory;

public class TextFieldFactory implements IFieldFactory{

	@Override
	public IField createField() {
		return new TextField();
	}

	@Override
	public IField[] createDoubleField() {
		IField[] searchField = new IField[2];
		searchField[0] = new TextField();
		searchField[1] = null;
		return searchField;
	}
}
