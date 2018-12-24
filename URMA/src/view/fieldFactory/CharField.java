package view.fieldFactory;

import javax.swing.JTextField;

import javax.swing.JPanel;

/**
 * Klasa koja opisuje char tip polja koja su potrebna za crud akcije
 * @author Jelena
 *
 */

public class CharField extends JPanel implements IField{

	private static final long serialVersionUID = 1L;
	private JTextField field;

	/**
		Konstruktor - kreira polje char tipa 		
		@author - Jelena
	**/
	public CharField() {
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
	public Boolean validateField(Boolean isReq, Boolean isPK, int maxLen) {
		if(isReq && field.getText().equals("")) {
			System.out.println("Requiref field is empty");
			return false;
		}
		if(isPK && field.getText().equals("")) {
			System.out.println("PK field is empty");
			return false;
		}
		if(field.getText().length() != maxLen) {
			System.out.println("wrong len");
			return false;
		}
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
		if(field.getText().equals("")) {
			return null;
		}
		return field.getText();
	}

}
