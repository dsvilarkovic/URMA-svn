package view.fieldFactory;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Klasa koja opisuje double tip polja koja su potrebna za crud akcije
 * @author Jelena
 *
 */

public class DoubleField extends JPanel implements IField{
	private static final long serialVersionUID = 1L;
	
	private JFormattedTextField field;

	/**
		Konstruktor - kreira polje double tipa i dodaje akciju za proveru formata broja 		
		@author - Jelena
	**/
	public DoubleField() {
		this.field = new JFormattedTextField();
		field.setColumns(10);
		this.field.addFocusListener(new FocusAdapter() {
		    public void focusLost(FocusEvent e) {
		      if (!validateJFormatedTextField((JFormattedTextField)e.getComponent())){
		        JOptionPane.showMessageDialog(null, "Please Enter Valid Number");
		      }
		    }
		});
		add(field);
		
		validate();
	}

	public JFormattedTextField getJTextField() {
		return  field;
	}

	public void setJTextField(JFormattedTextField field) {
		this.field = field;
	}
	
	private boolean validateJFormatedTextField(JFormattedTextField field) {
		try {
			Double.parseDouble(field.getText());
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	/**
		Metoda koja validira polje 		
		@author - Jelena
	**/
	@Override
	public Boolean validateField() {
//		return validateJFormatedTextField(this.field);
		return null; // ovde ide validacija na osnovu kolone
	}

	@Override
	public Object getField() {
		return field;
	}

	@Override
	public void setField(Object o) {
		field = (JFormattedTextField)o;
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
