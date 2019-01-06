package model;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Model jezičkog paketa jednog resursa. Iz ove klase se povlače nazivi svih
 * elemenata modela u okviru aplikacije. Ona se popunjava ili po podrazumevanom
 * principu ili uz pomoć posebno učitanog .lang paketa.
 * @author Boris
 *
 */
public class TitleLanguagePack {
	private Map<String,Map<String, String>> tableTitles;
	private Map<String,Map<String, String>> packageTitles;
	private Map<String,Map<String,String>> attributeTitles;
	private boolean languagePackLoaded;
	
	public TitleLanguagePack() {
		this.tableTitles = new HashMap<String, Map<String,String>>();
		this.packageTitles = new HashMap<String, Map<String,String>>();
		this.attributeTitles = new HashMap<String, Map<String,String>>();
		this.setLanguagePackLoaded(false);
	}
	
	/**
	 * Metoda koji puni model sa nazivima atributa u različitim jezicima.
	 * @param attributeCode - Kod atributa.
	 * @param tableCode - Kod matične tabele.
	 * @param values - Mapa čije ključ skraćenica jezika po ISO 639-1 standardu,
	 * a vrednosti su nazivi atributa na tom jeziku.
	 */
	public void addAttributeTitles(String attributeCode, String tableCode, Map<String,String> values) {
		// Ovako zagarantovana jedinstvenost
		attributeTitles.put(tableCode.toLowerCase() + attributeCode.toUpperCase(), values);
	}
	
	/**
	 * Metoda koji puni model sa nazivima tabela u različitim jezicima.
	 * @param tableCode - Kod tabele.
	 * @param values - Mapa čije ključ skraćenica jezika po ISO 639-1 standardu,
	 * a vrednosti su nazivi tabele na tom jeziku.
	 */
	public void addTableTitles(String tableCode, Map<String, String> values) {
		tableTitles.put(tableCode, values);
	}
	
	/**
	 * Metoda koji puni model sa nazivima paketa u različitim jezicima.
	 * @param packageCode - Kod tabele.
	 * @param values - Mapa čije ključ skraćenica jezika po ISO 639-1 standardu,
	 * a vrednosti su nazivi paketa na tom jeziku.
	 */
	public void addPackageTitles(String packageCode, Map<String,String> values) {
		packageTitles.put(packageCode, values);
	}
	
	/**
	 * Metoda kojoj se pristupi pri traženju naziva atributa jedne tabele.
	 * @param attrtibuteCode - Kod atributa.
	 * @param tableCode - Kod tabele sadržaoca.
	 * @return Naziv atributa u zavisnosti od konfigurisanog jezika.
	 */
	public String getAttributeTitle(String attrtibuteCode, String tableCode) {
		Map<String, String> values = attributeTitles.get(tableCode.toLowerCase() + attrtibuteCode.toUpperCase());
		if (values == null) {
			return null;
		}
		Locale locale = Locale.getDefault();
		String lang = locale.getLanguage();
		if (this.isLanguagePackLoaded() && values.containsKey(lang)) {
			return values.get(locale.getLanguage());
		}
		else {
			return values.get("default");
		}
	}
	
	/**
	 * Metoda kojoj se pristupi pri traženju naziva tabele.
	 * @param code - Kod tabele.
	 * @return Naziv tabele u zavisnosti od konfigurisanog jezika.
	 */
	public String getTableTitle(String code) {
		Map<String, String> values = tableTitles.get(code);
		if (values == null) {
			return null;
		}
		Locale locale = Locale.getDefault();
		String lang = locale.getLanguage();
		if (this.isLanguagePackLoaded() && values.containsKey(lang)) {
			return values.get(locale.getLanguage());
		}
		else {
			return values.get("default");
		}
	}
	
	/**
	 * Metoda kojoj se pristupi pri traženju naziva paketa.
	 * @param code - Kod paketa.
	 * @return Naziv paketa u zavisnosti od konfigurisanog jezika.
	 */
	public String getPackageTitle(String code) {
		Map<String, String> values = packageTitles.get(code);
		if (values == null) {
			return null;
		}
		Locale locale = Locale.getDefault();
		String lang = locale.getLanguage();
		if (this.isLanguagePackLoaded() && values.containsKey(lang)) {
			return values.get(locale.getLanguage());
		}
		else {
			return values.get("default");
		}
	}

	public boolean isLanguagePackLoaded() {
		return languagePackLoaded;
	}

	public void setLanguagePackLoaded(boolean languagePackLoaded) {
		this.languagePackLoaded = languagePackLoaded;
	}
}
