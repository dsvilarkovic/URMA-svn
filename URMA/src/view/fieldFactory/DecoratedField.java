package view.fieldFactory;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 * 
 * @author jelena
 *
 */

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
		return field;
	}

	@Override
	public void setField(Object o) {
		field = (IField) o;
	}

	@Override
	public void setValue(Object o) {
		if(o != null) {
			checkbox.setSelected((boolean) o);
		}else {
			checkbox.setSelected(false);
		}
	}

	@Override
	public Object getValue() {
		return checkbox.isSelected();
	}

}