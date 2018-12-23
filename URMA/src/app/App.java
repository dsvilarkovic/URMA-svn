package app;


import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import controler.tableActions.TableMediator;
import model.InformationResource;
import model.resourceFactory.DBFactory;
import model.resourceFactory.IResourceFactory;
import model.resourceFactory.JSONFactory;
import model.resourceFactory.XMLFactory;
import view.editorFrame.EditorWindow;
import view.fieldFactory.BooleanFieldFactory;
import view.fieldFactory.DateFieldFactory;
import view.fieldFactory.DoubleFieldFactory;
import view.fieldFactory.IFieldFactory;
import view.fieldFactory.IntegerFieldFactory;
import view.fieldFactory.VarcharFieldFactory;
import view.localizationManager.LocalizationManager;
import view.mainFrame.MainAppFrame;
import view.fieldFactory.CharFieldFactory;

/**
 * Enumeracija koja predstavlja singletone klasu
 * @author filip
 */
public enum App {
	INSTANCE;

	private EditorWindow editorWindow;
	private MainAppFrame mainAppFrame;
	
	private IResourceFactory factory = null;
	private IFieldFactory fieldFactory = null;
	private TableMediator tableMediator;
	private InformationResource model;
	private ResourceBundle resourceBundle;
	private LocalizationManager localizationManager;
	
	/**
	 * Metoda koja inicijalizuje prozore u aplikaciji
	 * @author filip
	 * @param none
	 * @return {@link Void}
	 */
	public void start() {
		//postavljanje lokalizacije
		localizationManager = new LocalizationManager();
		Locale.setDefault(new Locale("sr", "RS"));
		resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());

		editorWindow = new EditorWindow();
		mainAppFrame = new MainAppFrame();
		tableMediator = new TableMediator();
		
		mainAppFrame.setVisible(true);
		
	}

	/**
	 * @return the localizationManager
	 */
	public LocalizationManager getLocalizationManager() {
		return localizationManager;
	}

	/**
	 * @param localizationManager the localizationManager to set
	 */
	public void setLocalizationManager(LocalizationManager localizationManager) {
		this.localizationManager = localizationManager;
	}

	/**
	 * @return the resourceBundle
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public MainAppFrame getMainAppFrame() {
		return mainAppFrame;
	}

	public EditorWindow getEditorWindow() {
		return editorWindow;
	}
	
	public IResourceFactory getFactory() {
		return factory;
	}
	
	/**
		Izbor fabrike za kreiranje parsera, handlera i validatora		
		@author - Jelena (zapravo mislim da sam ja autor @author filip)
		@param param1 - String, ekstenzija fajla metaseme na osnovu kojeg se formira fabrika(json, xml, db, sch)  
		@return - fabrika izabranog tipa
	**/
	public void setFactory(String extension) {
		switch (extension) {
		case "sch": factory = new JSONFactory(); break;
		case "json": factory = new JSONFactory(); break;
		case "xml": factory = new XMLFactory(); break;
		case "db": factory = new DBFactory(); break;
		default: JOptionPane.showMessageDialog(null, "We dont know that resource", "Invalid resource",
				JOptionPane.ERROR_MESSAGE);	break;
		}
	}
	
	public IFieldFactory getFieldFactory() {
		return fieldFactory;
	}
	
	/**
		Izbor fabrike za kreiranje polja različitih tipova		
		@author - Jelena
		@param param1 - tip polja koje je potrebno kreirati(char, varchar, int, double, date, boolean)  
		@return - fabrika izabranog tipa
	**/
	public void createFieldFactory(String type) {
		switch (type) {
			case "char": fieldFactory = new CharFieldFactory(); break;
			case "varchar": fieldFactory = new VarcharFieldFactory(); break;
//			case "string" : fieldFactory = new VarcharFieldFactory(); break; 
			case "date": fieldFactory = new DateFieldFactory(); break;
			case "double": fieldFactory = new DoubleFieldFactory(); break;
			case "int": fieldFactory = new IntegerFieldFactory(); break;
//			case "number": fieldFactory = new IntegerFieldFactory(); break;
			case "boolean": fieldFactory = new BooleanFieldFactory(); break;
			default: JOptionPane.showMessageDialog(null, "We dont know that type", "Invalid type",
					JOptionPane.ERROR_MESSAGE);	break;
		}
	}

	/**
	 * @return the tableMediator
	 */
	public TableMediator getTableMediator() {
		return tableMediator;
	}

	public InformationResource getModel() {
		return model;
	}

	public void setModel(InformationResource model) {
		this.model = model;
	}

}
