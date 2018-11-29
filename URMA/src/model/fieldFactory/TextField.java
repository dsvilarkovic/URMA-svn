package model.fieldFactory;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class TextField extends JPanel implements IField{

	private static final long serialVersionUID = 1L;
	private JTextField field;

	public TextField() {
		this.field = new JTextField();
		
		add(field);
		
		validate();
	}

	public JTextField getJTextField() {
		return  field;
	}

	public void setJTextField(JTextField field) {
		this.field = field;
	}

	@Override
	public Boolean validateField() {
		return false;
	}

	@Override
	public Object getField() {
		return field;
	}

	@Override
	public void setField(Object o) {
		field = (JTextField)o;
	}

}
