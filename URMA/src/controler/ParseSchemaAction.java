package controler;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import org.apache.commons.io.FilenameUtils;

import app.App;
import controler.parsers.IParser;
import model.Package;
import model.resourceFactory.IResourceFactory;
import model.treeAdapter.AdapterPackage;

public class ParseSchemaAction extends AbstractAction {

	private static final long serialVersionUID = 5566983032113661361L;

	public ParseSchemaAction() {
		putValue(NAME, "Load Schema");
		putValue(MNEMONIC_KEY, KeyEvent.VK_L);
	}

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

		App.INSTANCE.getMainAppFrame().getTreePanel().init(new AdapterPackage(elderPackage));
	}

}