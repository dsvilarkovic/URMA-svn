package view.fieldFactory;

public class BooleanFieldFactory implements IFieldFactory{

	@Override
	public IField createField() {
		return new BooleanField();
	}

}
