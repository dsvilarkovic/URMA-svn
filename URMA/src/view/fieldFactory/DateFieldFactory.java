package view.fieldFactory;

public class DateFieldFactory implements IFieldFactory{

	@Override
	public IField createField() {
		return new DateField();
	}

}
