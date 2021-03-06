package view.fieldFactory;

import javax.swing.JTextField;

import view.table.TableModel;

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
		@param isReq - da li je polje obavezno
		@param isPK - da li je polje primarni ključ
		@param maxLen - maksimalna dužina polja
		@param precision - preciznost(potrebna za double)
		@return true ako je validno, false ako nije
	**/
	@Override
	public Boolean validateField(Boolean isReq, Boolean isPK, int maxLen, int precision) {
		if(isReq && field.getText().equals("")) {
			System.out.println("Requiref field is empty");
			return false;
		}
		if(isPK && field.getText().equals("")) {
			System.out.println("PK field is empty");
			return false;
		}
		System.out.println(maxLen);
		if(field.getText().length() > maxLen) {
			System.out.println("VarcharField");
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
		return field.getText();
	}

	@Override
	public void setEditable(Boolean editable) {
		field.setEditable(editable);
	}

}
