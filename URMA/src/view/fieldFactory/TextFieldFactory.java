package view.fieldFactory;

public class TextFieldFactory implements IFieldFactory{

	@Override
	public IField createField() {
		return new TextField();
	}

}
