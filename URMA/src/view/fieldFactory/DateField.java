package view.fieldFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import view.localizationManager.LocalizationManager;
import view.table.TableModel;

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
		model.setDate(2000, 0, 1);
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
	public Boolean validateField(Boolean isReq, Boolean isPK, int maxLen, int precision) {
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
		if(o != null && !o.equals(TableModel.reservedNullValue)) {
			String date = (String)o;
//			System.out.println(date);
//			String[] dates = date.split("-");
//			field.getModel().setDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1])-1, Integer.parseInt(dates[2]));
			
			//TODO: @Dusan dirao - begin
			Date dateObject  = LocalizationManager.formatDate(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateObject);
			field.getModel().setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
			//TODO: @Dusan dirao - end
			
			field.getModel().setSelected(true);
		}else {
			field.getModel().setDate(2000, 0, 1);
		}
	}

	@Override
	public Object getValue() {
		int day = field.getModel().getDay();
		int month = field.getModel().getMonth() + 1;
		int year = field.getModel().getYear();
		System.out.println("YEAR" + year);
		if(day == 1 && month == 1 && year == 2000) {
			return null;
		}
		
		String date_db = "";
		date_db += month + "-" + day  + "-" + year;
		return date_db;
	}

	@Override
	public void setEditable(Boolean editable) {
		field.getComponent(1).setEnabled(editable);
	}

}
