package view.localizationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Lokalizacioni menadzer koji radi po principu observer sablona 
 * Obavestava i namece promenu jezika preko combobox-a statusbar-a
 * @author Dusan
 *
 */
public class LocalizationManager {

	private List<LocalizationObserver> localizationObservers = new ArrayList<>();
	
	/**
	 * Lokalizacioni menadzer koji radi po principu observer sablona 
	 * Obavestava i namece promenu jezika preko combobox-a statusbar-a
	 * @author Dusan
	 *
	 */
	public LocalizationManager() {
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
		
		for (LocalizationObserver localizationObserver : localizationObservers) {
			localizationObserver.updateLanguage(language);
		}
	}
	
	/**
	 * Ovako se dodaju lokalizacioni menadzeri
	 * @param localizationObserver
	 */
	public void addLocalizationObserver(LocalizationObserver localizationObserver) {
		localizationObservers.add(localizationObserver);
	}

}
