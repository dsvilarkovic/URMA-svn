package app;


import javax.swing.JOptionPane;

import controller.tableactions.TableMediator;
import model.InformationResource;
import model.Table;
import model.resourceFactory.DBFactory;
import model.resourceFactory.IResourceFactory;
import model.resourceFactory.JSONFactory;
import model.resourceFactory.XMLFactory;
import view.EditorWindow;
import view.fieldFactory.BooleanFieldFactory;
import view.fieldFactory.DateFieldFactory;
import view.fieldFactory.DoubleFieldFactory;
import view.fieldFactory.IFieldFactory;
import view.fieldFactory.IntegerFieldFactory;
import view.fieldFactory.VarcharFieldFactory;
import view.fieldFactory.CharFieldFactory;
import view.mainframe.MainAppFrame;
import view.table.ChildTablePanel;
import view.table.ParentTablePanel;
import view.table.TablePanel;

/**
 * 
 * @author filip
 *
 */
public enum App {
	INSTANCE;

	private EditorWindow editorWindow;
	private MainAppFrame mainAppFrame;
	
	private IResourceFactory factory = null;
	private IFieldFactory fieldFactory = null;
	private TableMediator tableMediator;
	private InformationResource model;
	
	public void start() {
		editorWindow = new EditorWindow();
		mainAppFrame = new MainAppFrame();
		tableMediator = new TableMediator();
		
		mainAppFrame.setVisible(true);
		
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
			case "string": fieldFactory = new VarcharFieldFactory(); break;
			case "date": fieldFactory = new DateFieldFactory(); break;
			case "number": fieldFactory = new DoubleFieldFactory(); break;
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
