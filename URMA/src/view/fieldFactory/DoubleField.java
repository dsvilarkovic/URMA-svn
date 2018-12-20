package view.fieldFactory;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * @author jelena
 *
 */

public class DoubleField extends JPanel implements IField{
	private static final long serialVersionUID = 1L;
	
	private JFormattedTextField field;

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
