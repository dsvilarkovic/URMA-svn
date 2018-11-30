package view.fieldFactory;

public class NumberFieldFactory  implements IFieldFactory{

	@Override
	public IField createField() {
		return new NumberField();
	}

}
