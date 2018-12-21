package view.fieldFactory;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 * Klasa koja dekoriše polja boolean poljem na crud prozoru
 * @author Jelena
 *
 */

public class DecoratedField extends JPanel implements IField{
	private static final long serialVersionUID = 1L;
	
	IField field = null;
	JCheckBox checkbox = null;
	
	/**
		Konstruktor - kreira polje boolean tipa koje dekoriše polja ostalih tipova - potrebno za search		
		@author - Jelena
	**/
	public DecoratedField(IField field) {
		super();
		this.field = field;
		this.checkbox = new JCheckBox("");
		
		add((Component) field.getField());
		add(checkbox);
		
		validate();
	}
	
	public JCheckBox getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(JCheckBox checkbox) {
		this.checkbox = checkbox;
	}

	/**
		Metoda koja validira polje 		
		@author - Jelena
	**/
	@Override
	public Boolean validateField(Boolean isReq, Boolean isPK, int maxLen) {
		return true;
	}

	@Override
	public Object getField() {
		return field;
	}

	@Override
	public void setField(Object o) {
		field = (IField) o;
	}

	@Override
	public void setValue(Object o) {
		if(o != null) {
			checkbox.setSelected((boolean) o);
		}else {
			checkbox.setSelected(false);
		}
	}

	@Override
	public Object getValue() {
		return checkbox.isSelected();
	}

}