package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.App;
import controler.crud.CreateAction;
import controler.crud.SearchAction;
import controler.crud.UpdateAction;
import model.Attribute;
import model.Table;
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
		@param param1 - tabela za koju se kreira crud window  
		@param param2 - vektor trenutnih vrednosti u tabeli koji se menjaju  
	**/
	public CrudWindow(Table t, Vector<Object> v) {	//edit
		this.table = t;
		this.values = v;
		this.fields = new HashMap<String, Object>();
		JButton button = new JButton();
		button.setText("update");
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

		validate();
	}
	
	
	/**
		Metoda kreira i prikazuje crud window za akcije create i search	
		@author - Jelena
		@param param1 - tabela za koju se kreira crud window  
		@param param2 - parametar koji odredjuje koja je akcija u pitanju(true za search, false za create)  
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
			button.setText("search");
			button.addActionListener(new SearchAction(this));
		}else {
			button.setText("create");
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
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(true);
		setTitle("CRUD");
		setLayout(new GridLayout(0,3));
		setIconImage((new ImageIcon("resources/palm-tree.png")).getImage());
	}
}

