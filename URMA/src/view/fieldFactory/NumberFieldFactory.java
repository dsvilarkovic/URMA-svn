package view.fieldFactory;

public class NumberFieldFactory  implements IFieldFactory{

	@Override
	public IField createField() {
		return new NumberField();
	}

	@Override
	public IField[] createDoubleField() {
		IField[] searchField = new IField[2];
		searchField[0] = new NumberField();
		searchField[1] = new NumberField();
		return searchField;
	}

}
