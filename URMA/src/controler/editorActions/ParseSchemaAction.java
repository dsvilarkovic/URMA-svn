package controler.editorActions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;

import org.apache.commons.io.FilenameUtils;

import app.App;
import controler.Open;
import controler.parsers.IParser;
import model.Package;
import model.resourceFactory.IResourceFactory;
import model.treeAdapter.AdapterPackage;
import view.localizationManager.LocalizationObserver;
/**
 * Akcija za parsiranje seme i inicijalizaciju stabla
 * @author filip
 */
public class ParseSchemaAction extends AbstractAction implements LocalizationObserver{

	private static final long serialVersionUID = 5566983032113661361L;

	public ParseSchemaAction() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		App.INSTANCE.getLocalizationManager().addLocalizationObserver(this);
		putValue(NAME, resourceBundle.getString("schema.load"));
		putValue(MNEMONIC_KEY, KeyEvent.VK_L);
	}

	/**
	 * Akcija koja se izvrsi klikom na dugme, pokrece parsiranje i ako je 
	 * parsiranje uspesno inicira iscrtavanje satabla
	 * @author filip
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String str = (String) new Open().getPath("sch/json/xml/db");
			App.INSTANCE.setFactory(FilenameUtils.getExtension(str));
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
	}

	@Override
	public void updateLanguage(String language) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());

		putValue(NAME, resourceBundle.getString("schema.load"));		
	}

}