package view.fieldFactory;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class NumberField extends JPanel implements IField{
	private static final long serialVersionUID = 1L;
	
	private JFormattedTextField field;

	public NumberField() {
		this.field = new JFormattedTextField();
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
            NumberFormat nf = new DecimalFormat("####.00");
            nf.setMaximumIntegerDigits(10);
            nf.parse(field.getText());
            return true;
        } catch (ParseException e) {
            return false;
        }
	}

	@Override
	public Boolean validateField() {
		return validateJFormatedTextField(this.field);
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
