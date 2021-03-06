package view.fieldFactory;

import javax.swing.JPanel;
import javax.swing.JCheckBox;

/**
 * Klasa koja opisuje boolean tip polja koja su potrebna za crud akcije
 * @author Jelena
 *
 */

public class BooleanField  extends JPanel implements IField{
	private static final long serialVersionUID = 1L;
	private JCheckBox field;

	/**
		Konstruktor - kreira polje boolean tipa 		
		@author - Jelena
	**/
	public BooleanField() {
		field = new JCheckBox();
		
		add(field);
		
		validate();
	}

	public JCheckBox getJCheckBox() {
		return  field;
	}

	public void setJCheckBox(JCheckBox field) {
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
		return true;
	}

	@Override
	public Object getField() {
		return field;
	}

	@Override
	public void setField(Object o) {
		field = (JCheckBox)o;
	}

	@Override
	public void setValue(Object o) {
		if(o != null) {
			field.setSelected((boolean) o);
		}else {
			field.setSelected(false);
		}
	}

	@Override
	public Object getValue() {
		return field.isSelected();
	}

	@Override
	public void setEditable(Boolean editable) {
		field.setEnabled(editable);
	}
}
