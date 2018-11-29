package app;


import javax.swing.JOptionPane;

import model.factory.DBFactory;
import model.factory.IResourceFactory;
import model.factory.JSONFactory;
import model.factory.XMLFactory;
import view.EditorWindow;
import view.mainframe.MainAppFrame;

/**
 * 
 * @author filip
 *
 */
public enum App {
	INSTANCE;

	private IResourceFactory factory = null;
	
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
}
