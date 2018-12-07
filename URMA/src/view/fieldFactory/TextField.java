package view.fieldFactory;

import javax.swing.JTextField;

import javax.swing.JPanel;

public class TextField extends JPanel implements IField{

	private static final long serialVersionUID = 1L;
	private JTextField field;

	public TextField() {
		field = new JTextField();
		
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
		return true;
	}

	@Override
	public Object getField() {
		return field;
	}

	@Override
	public void setField(Object o) {
		field = (JTextField)o;
	}

	@Override
	public void setValue(Object o) {
		if(o != null) {
			field.setText(o.toString());
		}else {
			field.setText("");
		}
	}

	@Override
	public Object getValue() {		
		return field.getText();
	}

}
