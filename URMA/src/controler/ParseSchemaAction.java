package controler;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import app.App;
import controler.parsers.IParser;
import model.Package;
import model.resourceFactory.IResourceFactory;
import model.treeAdapter.AdapterPackage;
import view.dialogs.ChooseParserDialog;

public class ParseSchemaAction extends AbstractAction {

	private static final long serialVersionUID = 5566983032113661361L;

	public ParseSchemaAction() {
		putValue(NAME, "Load Schema");
		putValue(MNEMONIC_KEY, KeyEvent.VK_L);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ChooseParserDialog cpd = new ChooseParserDialog();
		String chosenParser = cpd.getSelected();
		cpd.dispose();

		if (chosenParser != null) {
			App.INSTANCE.setFactory(chosenParser.toLowerCase());
			IResourceFactory factory = App.INSTANCE.getFactory();
			IParser parser = factory.createParser();

			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int result = fc.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				String path = fc.getSelectedFile().getAbsolutePath();
				parser.parse(path);
			}
		}
		
		Package elderPackage = new Package();
		elderPackage.setTitle("InfResource");
		
		//System.out.println(App.INSTANCE.getModel().getPackages().size() + "    "   + App.INSTANCE.getModel().getPackages());
		
		for (Package value : App.INSTANCE.getModel().getPackages().values()) {
			System.out.println(value.getTitle() + "    "   + value + "  " + value.getChildPackages().size());
			System.out.println(value.getChildPackages().get("sub").getTitle() + "    "   + value.getChildPackages().get("sub") + "  " + value.getChildPackages().get("sub").getChildPackages());
			//elderPackage = value;
			//if(value.getChildPackages().size() == 0)			
				elderPackage.addChildPackages(value);
		}
		
		App.INSTANCE.getMainAppFrame().getTreePanel().init(new AdapterPackage(elderPackage));
	}

}