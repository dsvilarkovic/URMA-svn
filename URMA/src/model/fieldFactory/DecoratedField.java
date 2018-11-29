package model.fieldFactory;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class DecoratedField extends JPanel implements IField{
	private static final long serialVersionUID = 1L;
	
	IField field = null;
	JCheckBox checkbox = null;
	
	public DecoratedField(IField field) {
		super();
		this.field = field;
		this.checkbox = new JCheckBox("");
		
		add((Component) field.getField());
		add(checkbox);
		
		validate();
	}
	
	public JCheckBox getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(JCheckBox checkbox) {
		this.checkbox = checkbox;
	}


	@Override
	public Boolean validateField() {
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