package controler.mainFrameActions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import app.App;
import controler.Open;
import controler.parsers.IParser;
import model.Attribute;
import model.InformationResource;
import model.Package;
import model.Table;
import model.TitleLanguagePack;
import model.resourceFactory.IResourceFactory;
import model.treeAdapter.AdapterPackage;
import view.localizationManager.LocalizationObserver;

/**
 * Akcija za parsiranje seme i inicijalizaciju stabla
 * 
 * @author filip
 */
public class ParseSchemaAction extends AbstractAction implements LocalizationObserver {

	private static final long serialVersionUID = 5566983032113661361L;

	public ParseSchemaAction() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",
				Locale.getDefault());
		App.INSTANCE.getLocalizationManager().addLocalizationObserver(this);
		putValue(NAME, resourceBundle.getString("schema.load"));
		putValue(MNEMONIC_KEY, KeyEvent.VK_L);
	}

	/**
	 * Akcija koja se izvrsi klikom na dugme, pokrece parsiranje i ako je parsiranje
	 * uspesno inicira iscrtavanje satabla
	 * 
	 * @author filip
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {

			Object[] possibilities = { "json", "xml", "db" };
			String s = (String) JOptionPane.showInputDialog(null, "Choose parser", "Parser chooser Dialog",
					JOptionPane.PLAIN_MESSAGE, null, possibilities, "json");
			App.INSTANCE.setFactory(s);

			String str = (String) new Open().getPath("sch/json/xml/db");
			// App.INSTANCE.setFactory(FilenameUtils.getExtension(str));
			IResourceFactory factory = App.INSTANCE.getFactory();
			IParser parser = factory.createParser();
			parser.parse(str);

		} catch (NullPointerException npe) {
			return;
		}

		// Pravi paket koji ce sadrzati sve ostale pakete (Najstariji cvor stabla)
		Package elderPackage = new Package();
		elderPackage.setTitle("InfResource");
		for (Package value : App.INSTANCE.getModel().getPackages().values())
			elderPackage.addChildPackages(value);

		App.INSTANCE.getMainAppFrame().getTreePanel().removeAll();
		App.INSTANCE.getMainAppFrame().getTreePanel().init(new AdapterPackage(elderPackage));

		// Language paket
		App.INSTANCE.setTitleLanguagePack(new TitleLanguagePack());

		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",
				Locale.getDefault());
		int selectedOption = JOptionPane.showConfirmDialog(null, resourceBundle.getString("languagePack.load"), "",
				JOptionPane.YES_NO_OPTION);
		if (selectedOption == JOptionPane.YES_OPTION) {
			String path = (String) new Open().getPath("lang");
			if (LoadLanguagePackAction.loadLanguagePack(path)) {
				return;
			}
		}

		// Ako je rekao da nece ili nije nista selektovano punimo sa "default" =>
		// vrednost iz modela
		InformationResource ir = App.INSTANCE.getModel();
		TitleLanguagePack tlp = new TitleLanguagePack();
		for (Package pack : ir.getPackages().values()) {
			HashMap<String, String> values = new HashMap<>();
			values.put("default", pack.getTitle());
			tlp.addPackageTitles(pack.getCode(), values);
		}
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
	}

	@Override
	public void updateLanguage() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",
				Locale.getDefault());

		putValue(NAME, resourceBundle.getString("schema.load"));
	}

}