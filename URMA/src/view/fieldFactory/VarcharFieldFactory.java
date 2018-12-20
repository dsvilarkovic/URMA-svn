package view.fieldFactory;

/**
 * 
 * @author jelena
 *
 */

public class VarcharFieldFactory implements IFieldFactory{

	@Override
	public IField createField() {
		return new VarcharField();
	}

	@Override
	public IField[] createDoubleField() {
		IField[] searchField = new IField[2];
		searchField[0] = new VarcharField();
		searchField[1] = null;
		return searchField;
	}
}
