package app;


import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import controller.tableactions.TableMediator;
import model.InformationResource;
import model.fieldFactory.DecoratedField;
import model.fieldFactory.IFieldFactory;
import model.fieldFactory.TextField;
import model.fieldFactory.TextFieldFactory;
import model.resourceFactory.DBFactory;
import model.resourceFactory.IResourceFactory;
import model.resourceFactory.JSONFactory;
import model.resourceFactory.XMLFactory;
import view.EditorWindow;
import view.mainframe.MainAppFrame;

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
	private InformationResource repository;
	private InformationResource model;
	
	public void start() {
		editorWindow = new EditorWindow();
		mainAppFrame = new MainAppFrame();
		tableMediator = new TableMediator();
		repository = new InformationResource();
		
		mainAppFrame.setVisible(true);
		
//		App.INSTANCE.createFieldFactory("text");
//		TextField textField = (TextField) App.INSTANCE.getFieldFactory().createField();
//		textField.getJTextField().setText("jelena");
//		System.out.println(textField.getJTextField().getText());
//		
//		DecoratedField df = new DecoratedField(textField);
//		System.out.println(((JTextField)df.getField()).getText());
//		System.out.println(df.getCheckbox().isSelected());
		
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
		case "text": fieldFactory = new TextFieldFactory(); break;
//		case "textdecorator": fieldFactory = new DecoratedTextFieldFactory(); break;
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

	public InformationResource getRepository() throws Exception {
		System.err.println("Jos uvek nije implementirano");
		throw new Exception();
		//return repository;
	}

	public void setRepository(InformationResource repository) throws Exception{
		//this.repository = repository;
		
		System.err.println("Jos uvek nije implementirano");
		throw new Exception();
	}

	public InformationResource getModel() {
		return model;
	}

	public void setModel(InformationResource model) {
		this.model = model;
	}
	
	
}
