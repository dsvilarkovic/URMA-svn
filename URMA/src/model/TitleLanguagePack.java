package model;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import app.App;

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
		
		//dodaj informacioni resurs po kojem se menja
		Map<String, String> infResValues = new HashMap<>();
		infResValues.put("en", "Information Resource");
		infResValues.put("sr", "Informacioni Resurs");
		addPackageTitles(new Integer(App.INSTANCE.getModel().hashCode()).toString(), infResValues);
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
		
		if (this.isLanguagePackLoaded()) {
			Locale locale = Locale.getDefault();			
			return values.get(locale.getLanguage());
		}
		else {
			return values.values().iterator().next();
		}
	}
	
	public String getTableTitle(String code) {
		Map<String, String> values = tableTitles.get(code);
		if (this.isLanguagePackLoaded()) {
			Locale locale = Locale.getDefault();
			return values.get(locale.getLanguage());
		}
		else {
			return values.values().iterator().next();
		}
	}
	
	public String getPackageTitle(String code) {
		Map<String, String> values = packageTitles.get(code);
		if (this.isLanguagePackLoaded()) {
			Locale locale = Locale.getDefault();
			return values.get(locale.getLanguage());
		}
		else {
			return values.values().iterator().next();
		}
	}

	public boolean isLanguagePackLoaded() {
		return languagePackLoaded;
	}

	public void setLanguagePackLoaded(boolean languagePackLoaded) {
		this.languagePackLoaded = languagePackLoaded;
	}
}
