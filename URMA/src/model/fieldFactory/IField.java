package model.fieldFactory;

import javax.swing.text.JTextComponent;

public interface IField {
	public Boolean validateField() ;
	public Object getField();
	public void setField(Object o);
}