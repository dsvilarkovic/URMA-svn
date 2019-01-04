package view.localizationManager;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

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
	private static Map<String, DateFormat> dateFormats = new TreeMap<>();
	private static Map<String, NumberFormat> numberFormats = new TreeMap<>();
 	
	/**
	 * Lokalizacioni menadzer koji radi po principu observer sablona <br>
	 * Obavestava i namece promenu jezika preko combobox-a statusbar-a
	 * @author Dusan
	 *
	 */
	public LocalizationManager() {
		dateFormats.put( "Simple", new SimpleDateFormat("yyyy-MM-dd"));
		dateFormats.put( "RS", DateFormat.getDateInstance(DateFormat.DEFAULT, new Locale("sr", "RS")));
		dateFormats.put( "UK", DateFormat.getDateInstance(DateFormat.DEFAULT, new Locale("en", "UK")));
		
		
		numberFormats.put("RS", NumberFormat.getInstance(new Locale("sr", "RS")));
		numberFormats.put("UK", NumberFormat.getInstance(new Locale("en", "UK")));
		numberFormats.put("US", NumberFormat.getInstance(new Locale("en", "US")));
	}
	
	/**
	 * Ovime se podesava jezik u svim jezicki zavisnim komponentama
	 * @author Dusan
	 * @param language - jezik po kojem se podesava lokalizaciji
	 */
	public void updateLanguage(String language) {
		
		if(currentDateFormat == null || currentDateFormat.equals(dateFormats.get("Simple"))) {
			currentDateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
		}
		if(currentNumberFormat == null || currentNumberFormat.equals(numberFormats.get("US"))) {
			currentNumberFormat = NumberFormat.getInstance(Locale.getDefault());
		}
		switch(language) {
			case "Srpski - RS":
				Locale.setDefault(new Locale("sr", "RS")); 
				break;
			case "English - UK":
				Locale.setDefault(new Locale("en", "UK")); 
				break;
		}
		
		
		
		for (int i = 0; i < localizationObservers.size(); i++) {
			localizationObservers.get(i).updateLanguage();
		}
		
		currentDateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
		currentNumberFormat = NumberFormat.getInstance(Locale.getDefault());
	}
	
	
	
	/**
	 * Dodavanje observera u lokalizacionog menadzera
	 * @author Dusan
	 * @param localizationObserver - interfejs observera koji se dodaje
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
		
		
		if(currentDateFormat == null) {
			currentDateFormat = dateFormats.get("Simple");
		}
		
		
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
		Date returnDate = null;
		currentDateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
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
	 * @author Dusan
	 * @param dateString - string po kojem se proverava prolaznost parsiranja za neki dateFormat
	 */
	public static void setDateFormats(String dateString) {
		DateFormat dateFormat;
		for (String dateFormatKey : dateFormats.keySet()) {
			dateFormat = dateFormats.get(dateFormatKey);
			if(checkDateFormatIfEligible(dateFormat,dateString)) {
				currentDateFormat = dateFormat;
				return;
			}
		}
	}
	
	
	/**
	 * Proverava da li se moze parsirati po datom dateFormat-u
	 * @author Dusan
	 * @param dateFormat - format za koji se proverava, tipa {@link DateFormat}
	 * @param dateString - vrednost po kojom se formatira.
	 * @return - true/false u zavisnosti od uspeha parsiranja.
	 */
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
	 * Sluzi za konvertovanje tipa wrapper objekta u string.
	 * @author Dusan
	 * @param object - vrednost koji se konvertuje.
	 * @return - {@literal null} ako ne uspe da konvertuje u bilo koji vrednost
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

	/**
	 * Podesava na osnovu jezika format datuma
	 * @param language
	 */
	public static void setCurrentFormat(String language) {
		if(language.equals("")) {
			currentDateFormat = dateFormats.get("Simple");
			
		}
		if(language.contains("UK")) {
			System.out.println("UK");
			currentNumberFormat = numberFormats.get("UK");
			currentDateFormat = dateFormats.get("UK");
			return;
		}
		if(language.contains("RS")) {
			System.out.println("RS");
			currentNumberFormat = numberFormats.get("RS");
			currentDateFormat = dateFormats.get("RS");
			return;
		}
		
	}

	
	/**
	 * Ispisuje tip trenutnog formata sa kojim se radi.
	 */
	@SuppressWarnings("unused")
	private static void printCurrentFormatType() {
		
		for (String dateFormatKey : dateFormats.keySet()) {
			DateFormat dateFormat = dateFormats.get(dateFormatKey);
			
			if(dateFormat.equals(currentDateFormat)) {
				System.out.println("currentDateFormat = " + dateFormatKey);
				return;
			}
		}
		
		System.out.println("Nije nista nasao");
	}

	/**
	  * Parser i formater u odgovarajuci lokalizacioni tip brojne vrednosti. <br>
	 * Primer, Ameri koriste format za decimalni broj 17000.355 kao 17,000.355 <br>
	 * Srbi koriste format 17.000,355
	 * 
	 * <br> <br>
	 * Napomena: trenutno formatira na osnovu onoga kako je u bazi prikazano, a to je US format.
	 * @author Dusan
	 * @param numberString - string po kojem se radi parsiranje i lokalizacija, pretpostavka je da se<br>
	 * tu nalazi broj u stringu. Ukoliko ne, adekvatno se obradjuje.
	 * @return tacan broj ako se nalazio u stringu <br>
	 * "none" - kao vrednost pri debugovanju <br>
	 * "number_format_error" - kao nepostojanje broja u numberString stringu
	 */
	public static String formatNumberString(String numberString) {
		NumberFormat newNumberFormat = NumberFormat.getInstance(Locale.getDefault());
		
		
		if(currentNumberFormat == null) {
			currentNumberFormat = numberFormats.get("US");
		}
		
		
		String returnNumberString = "none";
		try {	
			Number n = (Number)currentNumberFormat.parse(numberString);
			returnNumberString = newNumberFormat.format(n);
		}
		catch (Exception e) {
			returnNumberString = "number_format_error";
		}
		return returnNumberString;
	}
	
	
	/**
	 * Metoda koja sluzi za parsiranje numbera i formatiranje u adekvatni {@link Number} oblik.
	 * U obzir se uvode i podrzane lokalizacije.
	 * @author Dusan
	 * @param numberString - string iz tabele koji ce se parsirati
	 * 
	 * @return - adekvatni {@link Number} objekat ili null ako ne odgovara format
	 */
	public static Number formatNumber(String numberString) {
		Number returnNumber = null;
		currentNumberFormat = NumberFormat.getInstance(Locale.getDefault());
		try {	
			ParsePosition pp = new ParsePosition(0);
			returnNumber = (Number)currentNumberFormat.parse(numberString,pp);
			if(pp.getIndex() != numberString.length()) {
				returnNumber = null;
				throw new Exception("Suffix exception");
			}
		}
		catch (Exception e) {
		}
		return returnNumber;
	}
	

	
	/**
	 * Vraca u date oblik koji se koristi u bazi podataka i koji prihvata, a to je yyyy-MM-dd format.
	 * @param dateString - string iz vrednosti tabele
	 * @return - odgovarajuci {@link String} oblik
	 */
	public static String formatDateStringDatabase(String dateString) {
		Date returnDate = null;
		String returnDateString = "none";
		DateFormat fromTableFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
		DateFormat databaseFormat = dateFormats.get("Simple");
		try {
			returnDate = (Date) fromTableFormat.parse(dateString);
			returnDateString = databaseFormat.format(returnDate);
		}
		catch(Exception e){
			returnDateString = "date_format_error";
		}
		return returnDateString;
	}
	
	
	

}
