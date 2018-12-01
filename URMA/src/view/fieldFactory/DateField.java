package view.fieldFactory;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class DateField extends JPanel implements IField{
	private static final long serialVersionUID = 1L;
	
	private JDatePickerImpl field;
//	private JFormattedTextField field;

	public DateField() {
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		field = new JDatePickerImpl(datePanel, new DateLabelFormatter());
//		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
//		this.field = new JFormattedTextField(df);
//		this.field.addFocusListener(new FocusAdapter() {
//		    public void focusLost(FocusEvent e) {
//		      if (!validateJFormatedTextField((JFormattedTextField)e.getComponent())){
//		        JOptionPane.showMessageDialog(null, "Please Enter Valid Date");
//		      }
//		    }
//		});
		
		add(field);
		
		validate();
	}

	public JDatePickerImpl getJDatePickerImpl() {
		return  field;
	}

	public void setJDatePickerImpl(JDatePickerImpl field) {
		this.field = field;
	}
	
//	private boolean validateJFormatedTextField(JFormattedTextField field) {
//		try {
//            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
//            df.setLenient(false);
//            df.parse(field.getText());
//            return true;
//        } catch (ParseException e) {
//            return false;
//        }
//	}

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

}
