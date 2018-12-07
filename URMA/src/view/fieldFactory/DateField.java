package view.fieldFactory;

import java.util.Properties;

import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class DateField extends JPanel implements IField{
	private static final long serialVersionUID = 1L;
	
	private JDatePickerImpl field;

	public DateField() {
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		field = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		add(field);
		
		validate();
	}

	public JDatePickerImpl getJDatePickerImpl() {
		return  field;
	}

	public void setJDatePickerImpl(JDatePickerImpl field) {
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
		field = (JDatePickerImpl)o;
	}

	@Override
	public void setValue(Object o) {
		if(o != null) {
			Integer[] date = (Integer[]) o;
			field.getModel().setDate(date[2], date[1], date[0]);
			field.getModel().setSelected(true);
		}
	}

	@Override
	public Object getValue() {
		return field.getModel().getValue();
	}

}
