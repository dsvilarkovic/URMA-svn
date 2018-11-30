package view.fieldFactory;

import javax.swing.JPanel;
import javax.swing.JCheckBox;

public class BooleanField  extends JPanel implements IField{
	private static final long serialVersionUID = 1L;
	private JCheckBox field;

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

	@Override
	public Boolean validateField() {
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
}
