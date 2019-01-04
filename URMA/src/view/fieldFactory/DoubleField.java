package view.fieldFactory;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import view.localizationManager.LocalizationManager;

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
//		this.field.addFocusListener(new FocusAdapter() {
//		    public void focusLost(FocusEvent e) {
//		      if (!validateJFormatedTextField((JFormattedTextField)e.getComponent())){
//		        JOptionPane.showMessageDialog(null, "Please Enter Valid Number");
//		      }
//		    }
//		});
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
			//TODO: @Dusan uradio, @Jelena da proveri
			Number formattedNumber = LocalizationManager.formatNumber(field.getText());
			if(formattedNumber == null) {
				throw new Exception("Bad format exception");
			}
//			if((formattedNumber instanceof Double) == false) {
//				throw new Exception("Not double type exception");
//			}
			//Jelena prethodno radila
			//Double.parseDouble(field.getText());
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
	public Boolean validateField(Boolean isReq, Boolean isPK, int maxLen, int precision) {
		if(validateJFormatedTextField(field) || field.getText().equals("")) {
			if(isReq && field.getText().equals("")) {
				System.out.println("Required field is empty");
				return false;
			}
			if(isPK && field.getText().equals("")) {
				System.out.println("PK field is empty");
				return false;
			}
			if(field.getText().length() > maxLen) {
				System.out.println("wrong len");
				return false;
			}
			ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
			String[] parts = field.getText().split(resourceBundle.getString("double.parse"));
			if(parts.length > 1) {
				if(parts[1].length() > precision) {
					System.out.println("wrong precision");
					return false;
				}
				
			}
			return true;
		}
		return false;
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
		if(field.getText().equals("")) {
			return null;
		}
		//TODO: @Dusan radio, @Jelena da proveri
		Number formattedNumber = LocalizationManager.formatNumber(field.getText());
		return formattedNumber;
		//Jelena radila
		//return field.getText();
	}

	@Override
	public void setEditable(Boolean editable) {
		field.setEditable(false);
	}

}
