package view.fieldFactory;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Klasa koja opisuje integer tip polja koja su potrebna za crud akcije
 * @author Jelena
 *
 */

public class IntegerField extends JPanel implements IField{
	private static final long serialVersionUID = 1L;
	
	private JFormattedTextField field;

	/**
		Konstruktor - kreira polje integer tipa i dodaje akciju za validaciju formata broja	
		@author - Jelena
	**/
	public IntegerField() {
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
//		try {
//			ParsePosition pp = new ParsePosition(0);
//            NumberFormat nf = new DecimalFormat("####.00");
//            nf.setMaximumIntegerDigits(10);
//            nf.parse(field.getText(), pp);
//            return pp.getIndex() == field.getText().length() ? true : false;
//        } catch (Exception e) {
//            return false;
//        }
		try {
			Integer.parseInt(field.getText());
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
