package view.fieldFactory;

public interface IField {
	public Boolean validateField() ;
	public Object getField();
	public void setField(Object o);
	public void setValue(Object o);
	public Object getValue();
}