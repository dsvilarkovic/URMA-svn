package controler.mainFrameActions;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import app.App;
import controler.Open;
import controler.parsers.IParser;
import model.InformationResource;
import model.Package;
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
		Image newProjectImg = Toolkit.getDefaultToolkit().getImage("resources/loadScheme.png");
		Image scaledImage3 = newProjectImg.getScaledInstance(23, 23, Image.SCALE_SMOOTH);
		putValue(SMALL_ICON, (new ImageIcon(scaledImage3)));
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
		ResourceBundle rb = App.INSTANCE.getResourceBundle();
		InformationResource model;
		try {

			Object[] possibilities = { "json", "xml", "db" };
			String s = (String) JOptionPane.showInputDialog(null, "Choose parser", "Parser chooser Dialog",
					JOptionPane.PLAIN_MESSAGE, null, possibilities, "json");
			App.INSTANCE.setFactory(s);

			IResourceFactory factory = App.INSTANCE.getFactory();
			IParser parser = factory.createParser();
			model = parser.parse();

		} catch (NullPointerException npe) {
			return;
		}
		
		if (model == null) {
			JOptionPane.showMessageDialog(null, rb.getString("parser.error"), rb.getString("message.error"), JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		App.INSTANCE.setModel(model);

		// Pravi paket koji ce sadrzati sve ostale pakete (Najstariji cvor stabla)
		Package elderPackage = new Package();
		elderPackage.setTitle("InfResource");
		elderPackage.setCode("INFRES");
		for (Package value : App.INSTANCE.getModel().getPackages().values())
			elderPackage.addChildPackages(value);
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",
				Locale.getDefault());
		int selectedOption = JOptionPane.showConfirmDialog(null, resourceBundle.getString("languagePack.load"), "",
				JOptionPane.YES_NO_OPTION);
		if (selectedOption == JOptionPane.YES_OPTION) {
			String path = (String) new Open().getPath("lang");
			LoadLanguagePackAction.loadLanguagePack(path);
		}else
			// Ako je rekao da nece ili nije nista selektovano punimo sa "default" =>
			// vrednost iz modela
			LoadLanguagePackAction.loadDefaultLanguagePack(model);

		App.INSTANCE.getMainAppFrame().getTreePanel().removeAll();
		App.INSTANCE.getMainAppFrame().getTreePanel().init(new AdapterPackage(elderPackage));
	}

	@Override
	public void updateLanguage() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",
				Locale.getDefault());

		putValue(NAME, resourceBundle.getString("schema.load"));
	}

}