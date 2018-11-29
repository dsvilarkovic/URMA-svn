package model.fieldFactory;

import javax.swing.JTextField;

public class TextField implements IField{

	private static final long serialVersionUID = 1L;
	private JTextField field;

	public TextField() {
		this.field = new JTextField();
	}

	public JTextField getJTextField() {
		return  field;
	}

	public void setJTextField(JTextField field) {
		this.field = field;
	}

	@Override
	public Boolean validate() {
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
