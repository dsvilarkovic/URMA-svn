package controler.mainFrameActions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.plaf.BorderUIResource.TitledBorderUIResource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import app.App;
import controler.Open;
import model.Attribute;
import model.InformationResource;
import model.Package;
import model.Table;
import model.TitleLanguagePack;
import view.localizationManager.LocalizationObserver;

/**
 * Akcija kojom se učitavaju paketi jezika šema. Očekuju se json fajlovi sa .lang ekstenzijom.
 * @author Boris
 *
 */
public class LoadLanguagePackAction extends AbstractAction implements LocalizationObserver {

	private static final long serialVersionUID = 7367662698511828186L;

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
	
	private static void recursivePackages(Package pack, TitleLanguagePack tlp) {
		for (Package child : pack.getChildPackages().values()) {
			HashMap<String, String> values = new HashMap<>();
			values.put("default", child.getTitle());
			tlp.addPackageTitles(child.getCode(), values);
			recursivePackages(child, tlp);
		}
	}
	
	/**
	 * Metoda koja iz informacionog resursa izvlači nazive, da bi se koristili kao podrazumevani u lokalizaciji
	 * @param ir - Informacioni resurs koji u sebi sadrži potrebne nazive.
	 * @return Potvrda uspešnosti metode.
	 */
	public static boolean loadDefaultLanguagePack(InformationResource ir) {
		if (ir == null) {
			return false;
		}
		TitleLanguagePack tlp = new TitleLanguagePack();
		for (Package pack : ir.getPackages().values()) {
			HashMap<String, String> values = new HashMap<>();
			values.put("default", pack.getTitle());
			tlp.addPackageTitles(pack.getCode(), values);
			recursivePackages(pack, tlp);
		}
		
		//TODO: Hardcode, jbg. (Boris)
		HashMap<String, String> val = new HashMap<>();
		val.put("default", "InfResource");
		tlp.addPackageTitles("INFRES", val);
		
		for (Table tab : ir.getAllTables().values()) {
			HashMap<String, String> values = new HashMap<>();
			values.put("default", tab.getTitle());
			tlp.addTableTitles(tab.getCode(), values);

			for (Attribute attr : tab.getAttributes().values()) {
				HashMap<String, String> values1 = new HashMap<>();
				values1.put("default", attr.getTitle());
				tlp.addAttributeTitles(attr.getCode(), tab.getCode(), values1);
			}
		}
		tlp.setLanguagePackLoaded(false);
		App.INSTANCE.setTitleLanguagePack(tlp);
		
		return true;
	}
	
	/**
	 * Metoda koja učitava jezičke pakete sa date putanje. Očekuju se datoteka json formata sa .lang ekstenzijom.
	 * @param path - Putanja na kojoj se nalazi paket. 
	 * @return Potvrda uspešnosti metode.
	 */
	public static boolean loadLanguagePack(String path) {
		loadDefaultLanguagePack(App.INSTANCE.getModel());
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
	public void updateLanguage() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		putValue(NAME, resourceBundle.getString("languagePack.menu"));	
	}

}
