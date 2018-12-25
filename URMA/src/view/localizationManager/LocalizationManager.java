package view.localizationManager;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import app.App;

/**
 * Lokalizacioni menadzer koji radi po principu observer sablona 
 * Obavestava i namece promenu jezika preko combobox-a statusbar-a
 * @author Dusan
 *
 */
public class LocalizationManager {

	private List<LocalizationObserver> localizationObservers = new ArrayList<>();
	public static DateFormat currentDateFormat = null;
	public static NumberFormat currentNumberFormat = null;
	public static List<DateFormat> dateFormats = new ArrayList<>();
	public static List<NumberFormat> numberFormats = new ArrayList<>();
 	
	/**
	 * Lokalizacioni menadzer koji radi po principu observer sablona 
	 * Obavestava i namece promenu jezika preko combobox-a statusbar-a
	 * @author Dusan
	 *
	 */
	public LocalizationManager() {
		dateFormats.add(new SimpleDateFormat("yyyy-MM-dd"));
		dateFormats.add(DateFormat.getDateInstance(DateFormat.DEFAULT, new Locale("sr", "RS")));
		dateFormats.add(DateFormat.getDateInstance(DateFormat.DEFAULT, new Locale("en", "UK")));
	}
	
	/**
	 * Ovime se podesava jezik u svim jezicki zavisnim komponentama
	 * @param language
	 */
	public void updateLanguage(String language) {		
		switch(language) {
			case "Srpski - RS":
				Locale.setDefault(new Locale("sr", "RS")); 
				break;
			case "English - EN":
				Locale.setDefault(new Locale("en", "UK")); 
				break;
		}
		
		for (int i = 0; i < localizationObservers.size(); i++) {
			localizationObservers.get(i).updateLanguage();
		}
		
		
	}
	
	
	
	/**
	 * Ovako se dodaju lokalizacioni menadzeri
	 * @param localizationObserver
	 */
	public void addLocalizationObserver(LocalizationObserver localizationObserver) {
		localizationObservers.add(localizationObserver);
	}
	
	/**
	 * Parser i formater u odgovarajuci lokalizacioni tip datumske vrednosti. <br>
	 * Primer, Ameri koriste format za 17.jun 1996. godine kao 06/17/1996. <br>
	 * Evropljani koriste format 17/06/1996.
	 * 
	 * <br> <br>
	 * Napomena: trenutno formatira na osnovu onoga kako je u bazi prikazano, a to je yyyy-MM-dd format.
	 * @author Dusan
	 * @param dateString - string po kojem se radi parsiranje i lokalizacija, pretpostavka je da se<br>
	 * tu nalazi datum u stringu. Ukoliko ne, adekvatno se obradjuje.
	 * @return tacan datum ako se nalazio u stringu <br>
	 * "none" - kao vrednost pri debugovanju <br>
	 * "date_format_error" - kao nepostojanje datuma u dateString stringu
	 */
	public static String formatDateString(String dateString) {
		DateFormat newDateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
		
		setDateFormats(dateString);
		
		String returnDateString = "none";
		try {	
			Date d = (Date)currentDateFormat.parse(dateString);
			returnDateString = newDateFormat.format(d);
		}
		catch (Exception e) {
			returnDateString = "date_format_error";
		}
		return returnDateString;
	}
	
	
	/**
	 * Metoda koja sluzi za parsiranje stringa i formatiranje u adekvatni {@link Date} oblik.
	 * U obzir se uvode i podrzane lokalizacije.
	 * @author Dusan
	 * @param dateString - string iz tabele koji ce se parsirati
	 * 
	 * @return - adekvatni {@link Date} objekat
	 */
	public static Date formatDate(String dateString) {
		setDateFormats(dateString);
		Date returnDate = null;
		try {	
			returnDate = (Date)currentDateFormat.parse(dateString);
		}
		catch (Exception e) {
		}
		return returnDate;
	}
	/**
	 * Podesava trenutni dateformat po kojem se postavlja, proverava se da li odgovara
	 * trenutnom formatu	
	 * @param dateFormats - 
	 */
	public static void setDateFormats(String dateString) {		
		for (DateFormat dateFormat : dateFormats) {
			if(checkDateFormatIfEligible(dateFormat,dateString)) {
				currentDateFormat = dateFormat;
				return;
			}
		}
	}
	
	public static boolean checkDateFormatIfEligible(DateFormat dateFormat, String dateString) {
		try {
			dateFormat.parse(dateString);
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}
	
	
	
	/**
	 * Sluzi za konvertovanje objekta u string
	 * @author Dusan
	 * @param object
	 * @return
	 */
	public static String convertToString(Object object) {
		try {
			return object.toString();
		}
		catch (ClassCastException e) {
			if(object instanceof Integer) {
				return Integer.toString((Integer) object);
			}
			else if(object instanceof Double) {
				return Double.toString((Double) object);
			}
			else if(object instanceof Boolean) {
				return Boolean.toString((Boolean) object);
			}
		}
		return null;
	}

	/**
	 * Metoda za ponistavanje prethodnih formata lokalizacije, poziva se kada se tabele ponovo ucitavaju iz baze
	 * @author Dusan
	 */
	public static void nulifyFormats() {
		currentDateFormat = null;
		currentNumberFormat = null;
		
	}

	
	

}
