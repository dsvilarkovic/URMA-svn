package controler.mainFrameActions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import app.App;
import controler.Open;
import model.TitleLanguagePack;
import view.localizationManager.LocalizationObserver;

public class LoadLanguagePackAction extends AbstractAction implements LocalizationObserver {
	
	public LoadLanguagePackAction() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		App.INSTANCE.getLocalizationManager().addLocalizationObserver(this);
		putValue(NAME, resourceBundle.getString("languagePack.menu"));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String path = (String) new Open().getPath("lang");
		loadLanguagePack(path);
	}
	
	public static boolean loadLanguagePack(String path) {
		TitleLanguagePack tlp = App.INSTANCE.getTitleLanguagePack();
		if (path != null) {
			JSONTokener tokener = new JSONTokener((String) new Open().openThis(path));
			JSONObject languagePackJSON = new JSONObject(tokener);
			
			JSONArray packageArrayJSON = languagePackJSON.getJSONArray("packages");
			for (Object packageObject : packageArrayJSON) {
				JSONObject packageJSON = (JSONObject) packageObject;
				String code = packageJSON.getString("code");
				
				JSONArray values = packageJSON.getJSONArray("values");
				HashMap<String, String> translation = new HashMap<>(); 
				for (Object valuesObject : values) {
					JSONObject mapJSON = (JSONObject) valuesObject;
					String lang = mapJSON.getString("lang");
					String value = mapJSON.getString("value");
					translation.put(lang, value);
				}
				tlp.addPackageTitles(code, translation);
			}
			
			JSONArray tableArrayJSON = languagePackJSON.getJSONArray("tables");
			for (Object tableObject : tableArrayJSON) {
				JSONObject tableJSON = (JSONObject) tableObject;
				String code = tableJSON.getString("code");
				
				JSONArray values = tableJSON.getJSONArray("values");
				HashMap<String, String> translation = new HashMap<>();
				for (Object valuesObject : values) {
					JSONObject mapJSON = (JSONObject) valuesObject;
					String lang = mapJSON.getString("lang");
					String value = mapJSON.getString("value");
					translation.put(lang, value);
				}
				tlp.addTableTitles(code, translation);
			}
			
			JSONArray attributeArrayJSON = languagePackJSON.getJSONArray("attributes");
			for (Object attributeObject : attributeArrayJSON) {
				JSONObject attrJSON = (JSONObject) attributeObject;
				String attributeCode = attrJSON.getString("code");
				String tableCode = attrJSON.getString("table_code");
				
				JSONArray values = attrJSON.getJSONArray("values");
				HashMap<String, String> translation = new HashMap<>(); 
				for (Object valuesObject : values) {
					JSONObject mapJSON = (JSONObject) valuesObject;
					String lang = mapJSON.getString("lang");
					String value = mapJSON.getString("value");
					translation.put(lang, value);
				}
				tlp.addAttributeTitles(attributeCode, tableCode, translation);
			}
			tlp.setLanguagePackLoaded(true);
			App.INSTANCE.setTitleLanguagePack(tlp);
			return true;
		}
		return false;
	}
	
	@Override
	public void updateLanguage(String language) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		putValue(NAME, resourceBundle.getString("languagePack.menu"));	
	}

}
