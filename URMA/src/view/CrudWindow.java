package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.App;
import controler.crud.CreateAction;
import controler.crud.ForeignKeyAction;
import controler.crud.SearchAction;
import controler.crud.UpdateAction;
import model.Attribute;
import model.Table;
import view.dialogs.ChooseReferencedCollumnValuesDialog;
import view.fieldFactory.DecoratedField;
import view.fieldFactory.IField;

/**
 * Prozor koji služi za popunjavanje polja za create, update i search akcije nad podacima
 * @author Jelena
 *
 */

public class CrudWindow extends JDialog{

	private static final long serialVersionUID = 1L;
	private Table table;
	Vector<Object> values;
	Map<String, Object> fields;

	/**
		Metoda kreira i prikazuje crud window za akciju update	
		@author - Jelena
		@param t - tabela za koju se kreira crud window  
		@param v - vektor trenutnih vrednosti u tabeli koji se menjaju  
	**/
	public CrudWindow(Table t, Vector<Object> v) {	//edit
		this.table = t;
		this.values = v;
		this.fields = new HashMap<String, Object>();
		JButton button = new JButton();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		button.setText(resourceBundle.getString("button.update"));
		button.addActionListener(new UpdateAction(this));
		CreateWindow();
		add(new JPanel());
		JPanel jp = new JPanel();
		jp.add(new JLabel(table.getTitle()));
		add(jp);
		add(new JPanel());
		Map<String, Attribute> attributes = table.getAttributes();
		int i = 0;
		for (String attributeKey : attributes.keySet()) {
			Attribute attribute = attributes.get(attributeKey);
			App.INSTANCE.createFieldFactory(attribute.getType());
			jp = new JPanel();
			jp.add(new JLabel(attribute.getTitle()));
			add(jp);
			IField field = App.INSTANCE.getFieldFactory().createField();
			field.setValue(values.get(i));
			if(attribute.getIsPrimaryKey()) {
				field.setEditable(false);
			}
			add((Component) field);
			fields.put(attribute.getTitle(), field);
			add(new JPanel());
			i++;
		}
		
		add(new JPanel());
		jp = new JPanel();
		jp.add(button);
		add(jp);

		for (String key : t.getParentTables().keySet()) {
			Table table = t.getParentTables().get(key);
			JButton b = new JButton();
			JPanel j = new JPanel();
			b.addActionListener(new ForeignKeyAction(this, table, key));
			b.setText(key);
			j.add(b);
			add(j);
//			ChooseReferencedCollumnValuesDialog testDialog = new ChooseReferencedCollumnValuesDialog(t.getParentTables().get(table));
		}
		
		validate();
	}
	
	
	/**
		Metoda kreira i prikazuje crud window za akcije create i search	
		@author - Jelena
		@param t - tabela za koju se kreira crud window  
		@param search - parametar koji odredjuje koja je akcija u pitanju(true za search, false za create)  
	**/
	public CrudWindow(Table t, boolean search) {//new/search
		this.table = t;
		this.values = new Vector<Object>();
		this.fields = new HashMap<String, Object>();
		JButton button = new JButton();
		
		CreateWindow();
		add(new JPanel());
		JPanel jp = new JPanel();
		jp.add(new JLabel(table.getTitle()));
		add(jp);
		add(new JPanel());
		Map<String, Attribute> attributes = table.getAttributes();
		for (String attributeKey : attributes.keySet()) {
			Attribute attribute = attributes.get(attributeKey);
			App.INSTANCE.createFieldFactory(attribute.getType());
			jp = new JPanel();
			jp.add(new JLabel(attribute.getTitle()));
			add(jp);
			if(search) {
				IField[] field = App.INSTANCE.getFieldFactory().createDoubleField();
				field[0].setValue(null);
				DecoratedField df = new DecoratedField(field[0]);
				add((Component) df);
				if(field[1] != null) {
					field[1].setValue(null);
					DecoratedField df1 = new DecoratedField(field[1]);
					add((Component) df1);
					fields.put(attribute.getTitle(), new DecoratedField[]{df, df1});
				}else {
					add(new JPanel());
					fields.put(attribute.getTitle(), new DecoratedField[]{df, null});
				}
			}else {
				IField field = App.INSTANCE.getFieldFactory().createField();
				field.setValue(null);
				add((Component) field);
				fields.put(attribute.getTitle(), field);
				add(new JPanel());				
			}
		}
		
		if(search) {
			ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
			button.setText(resourceBundle.getString("button.search"));
			button.addActionListener(new SearchAction(this));
		}else {
			ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
			button.setText(resourceBundle.getString("button.create"));
			button.addActionListener(new CreateAction(this));
		}

		add(new JPanel());
		jp = new JPanel();
		jp.add(button);
		add(jp);
		
		validate();
	}
	
	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Vector<Object> getValues() {
		return values;
	}

	public void setValues(Vector<Object> values) {
		this.values = values;
	}

	public Map<String, Object> getFields() {
		return fields;
	}

	public void setFields(Map<String, Object> fields) {
		this.fields = fields;
	}
	
	/**
		Konstruktor - podešava parametre prozora	
		@author - Jelena
	**/
	private void CreateWindow() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setSize((int) (screenSize.getWidth() * 0.4), (int) (screenSize.getHeight() * 0.71));
//		setModal(true);
		setLocationRelativeTo(null);
		setResizable(true);
		setTitle("CRUD");
		setLayout(new GridLayout(0,3));
		setIconImage((new ImageIcon("resources/palm-tree.png")).getImage());
	}
}

