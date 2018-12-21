package view.fieldFactory;

import javax.swing.JTextField;

import javax.swing.JPanel;

/**
 * Klasa koja opisuje varchar tip polja koja su potrebna za crud akcije
 * @author Jelena
 *
 */

public class VarcharField extends JPanel implements IField{

	private static final long serialVersionUID = 1L;
	private JTextField field;

	/**
		Konstruktor - kreira polje varchar tipa 		
		@author - Jelena
	**/
	public VarcharField() {
		field = new JTextField();
		field.setColumns(10);
		
		add(field);
		
		validate();
	}

	public JTextField getJTextField() {
		return  field;
	}

	public void setJTextField(JTextField field) {
		this.field = field;
	}

	/**
		Metoda koja validira polje 		
		@author - Jelena
	**/
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
