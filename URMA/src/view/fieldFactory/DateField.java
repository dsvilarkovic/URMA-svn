package view.fieldFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import view.localizationManager.LocalizationManager;

/**
 * Klasa koja opisuje date tip polja koja su potrebna za crud akcije
 * @author Jelena
 *
 */

public class DateField extends JPanel implements IField{
	private static final long serialVersionUID = 1L;
	
	private JDatePickerImpl field;

	/**
		Konstruktor - kreira i podešava polje date tipa 		
		@author - Jelena
	**/
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
		field = (JDatePickerImpl)o;
	}

	@Override
	public void setValue(Object o) {
		if(o != null) {
			String date = (String)o;
//			String[] dates = date.split("-");
//			field.getModel().setDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1])-1, Integer.parseInt(dates[2]));
			
			//TODO: @Dusan dirao - begin
			//TODO : @Dusan delete ne radi
			Date dateObject  = LocalizationManager.formatDate(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateObject);
			field.getModel().setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
			//TODO: @Dusan dirao - end
			
			field.getModel().setSelected(true);
		}
	}

	@Override
	public Object getValue() {
		int day = field.getModel().getDay();
		int month = field.getModel().getMonth() + 1;
		int year = field.getModel().getYear();
		
		String date_db = "";
		date_db += year + "-" + month  + "-" + day;
		return date_db;
	}

	@Override
	public void setEditable(Boolean editable) {
		field.getComponent(1).setEnabled(false);
	}

}
