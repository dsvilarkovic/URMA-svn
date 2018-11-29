package model.fieldFactory;

import javax.swing.JCheckBox;

public class DecoratedField implements IField{

	IField field = null;
	JCheckBox checkbox = null;
	
	public DecoratedField(IField field) {
		super();
		this.field = field;
		this.checkbox = new JCheckBox("");
	}
	
	public JCheckBox getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(JCheckBox checkbox) {
		this.checkbox = checkbox;
	}


	@Override
	public Boolean validate() {
		return null;
	}

	@Override
	public Object getField() {
		return field.getField();
	}

	@Override
	public void setField(Object o) {
		field = (IField) o;
	}

}