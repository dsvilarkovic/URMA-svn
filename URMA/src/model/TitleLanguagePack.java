package model;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
	
	public void addAttributeTitles(String attributeCode, String tableCode, Map<String,String> values) {
		// Ovako zagarantovana jedinstvenost
		attributeTitles.put(tableCode.toLowerCase() + attributeCode.toUpperCase(), values);
	}
	
	public void addTableTitles(String tableCode, Map<String, String> values) {
		tableTitles.put(tableCode, values);
	}
	
	public void addPackageTitles(String packageCode, Map<String,String> values) {
		packageTitles.put(packageCode, values);
	}
	
	/**
	 * Metoda kojoj se pristupi pri traženju naziva atributa jedne tabele.
	 * @param attrtibuteCode = Kod atributa.
	 * @param tableCode = Kod tabele sadržaoca.
	 * @return Vraća naziv atributa u zavisnosti od konfigurisanog jezika.
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
