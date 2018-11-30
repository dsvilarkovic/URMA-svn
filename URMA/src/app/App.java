package app;


import javax.swing.JOptionPane;

import model.resourceFactory.DBFactory;
import model.resourceFactory.IResourceFactory;
import model.resourceFactory.JSONFactory;
import model.resourceFactory.XMLFactory;
import view.EditorWindow;
import view.fieldFactory.BooleanFieldFactory;
import view.fieldFactory.DateFieldFactory;
import view.fieldFactory.IFieldFactory;
import view.fieldFactory.NumberFieldFactory;
import view.fieldFactory.TextFieldFactory;
import view.mainframe.MainAppFrame;

/**
 * 
 * @author filip
 *
 */
public enum App {
	INSTANCE;

	private IResourceFactory factory = null;
	
	private IFieldFactory fieldFactory = null;
	
	private EditorWindow editorWindow = new EditorWindow();
	
	private MainAppFrame appWindow = new MainAppFrame();
	
	public void start() {
		//pokretanje aplikacije
		appWindow.setVisible(true);
	}
	
	//pokretanje prozora
	public EditorWindow getEditorWindow() {
		return editorWindow;	
	}
	
	public IResourceFactory getFactory() {
		return factory;
	}
	
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
	
	public void createFieldFactory(String type) {
		switch (type) {
		case "text": fieldFactory = new TextFieldFactory(); break;
		case "date": fieldFactory = new DateFieldFactory(); break;
		case "number": fieldFactory = new NumberFieldFactory(); break;
		case "boolean": fieldFactory = new BooleanFieldFactory(); break;
		default: JOptionPane.showMessageDialog(null, "We dont know that type", "Invalid type",
				JOptionPane.ERROR_MESSAGE);	break;
		}
}
}
