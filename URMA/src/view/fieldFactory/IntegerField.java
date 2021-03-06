package view.fieldFactory;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import view.localizationManager.LocalizationManager;
import view.table.TableModel;

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
			Number formattedNumber = LocalizationManager.formatNumber(field.getText());
			if(formattedNumber == null) {
				throw new Exception("Bad format exception");
			}
			if((formattedNumber instanceof Integer) == false &&
					(formattedNumber instanceof Long) == false) {
				throw new Exception("Not integer type exception");
			}
			
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	/**
		Metoda koja validira polje 		
		@author - Jelena
		@param isReq - da li je polje obavezno
		@param isPK - da li je polje primarni kljuÄ�
		@param maxLen - maksimalna duÅ¾ina polja
		@param precision - preciznost(potrebna za double)
		@return true ako je validno, false ako nije
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
			System.out.println(maxLen);
			if(field.getText().length() > maxLen) {
				System.out.println("IntegerField");
				System.out.println("wrong len");
				return false;
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
		if(o != null && !o.equals(TableModel.reservedNullValue)) {
			field.setText(o.toString());
		}else {
			field.setText("");
		}
	}

	@Override
	public Object getValue() {
		if(field.getText().equals("") || field.getText().equals(TableModel.reservedNullValue)) {
			return null;
		}
		Number formattedNumber = LocalizationManager.formatNumber(field.getText());
		return formattedNumber;
		//Jelena radila
		//return field.getText();
	}

	@Override
	public void setEditable(Boolean editable) {
		field.setEditable(editable);
	}

}
