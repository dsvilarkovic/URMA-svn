package view.mainFrame;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import app.App;
import controler.ParseSchemaAction;
import controler.mainframe.OpenSchemaEditor;
import view.localizationManager.LocalizationObserver;

public class MainAppMenuBar extends JMenuBar implements LocalizationObserver{

	private static final long serialVersionUID = -1119472832127166620L;
	
	private JMenu tools;
	private JMenu edit;
	private JMenu help;

	public MainAppMenuBar() {
		//JMenu file = new JMenu("File");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		App.INSTANCE.getLocalizationManager().addLocalizationObserver(this);
		
		tools = new JMenu(resourceBundle.getString("menu.tools"));
		edit = new JMenu(resourceBundle.getString("menu.edit"));
		help = new JMenu(resourceBundle.getString("menu.help"));
				
		JMenuItem OpenSchemaEditorItem = new JMenuItem(new OpenSchemaEditor());
		tools.add(OpenSchemaEditorItem);
		
		JMenuItem ParscheSchemaItem = new JMenuItem(new ParseSchemaAction());
		tools.add(ParscheSchemaItem);
		
		//add(file);
		add(tools);
		add(edit);
		add(help);
		
		validate();
	}

	@Override
	public void updateLanguage(String language) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		
		tools.setText(resourceBundle.getString("menu.tools"));
		edit.setText(resourceBundle.getString("menu.edit"));
		help.setText(resourceBundle.getString("menu.help"));
		
	}

}