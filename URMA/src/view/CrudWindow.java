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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.App;
import controler.crud.CreateAction;
import controler.crud.SearchAction;
import controler.crud.UpdateAction;
import controler.handlers.IHandler;
import model.Attribute;
import model.Table;
import model.resourceFactory.IResourceFactory;
import view.fieldFactory.DecoratedField;
import view.fieldFactory.IField;

public class CrudWindow extends JDialog{

	private static final long serialVersionUID = 1L;
	private Table table;
	Vector<Object> values;
	Map<String, Object> fields;

	//edit
	public CrudWindow(Table t, Vector<Object> v) {
		this.table = t;
		this.values = v;
		this.fields = new HashMap<String, Object>();
		JButton button = new JButton();
		button.setText("update");
		button.addActionListener(new UpdateAction(this));
		
		CreateWindow();
		add(new JLabel(table.getTitle()));
		add(new JPanel());
		add(new JPanel());
		Map<String, Attribute> attributes = table.getAttributes();
		int i = 0;
		for (String attributeKey : attributes.keySet()) {
			Attribute attribute = attributes.get(attributeKey);
			App.INSTANCE.createFieldFactory(attribute.getType());
			add(new JLabel(attribute.getTitle()));
			IField field = App.INSTANCE.getFieldFactory().createField();
			field.setValue(values.get(i));
			add((Component) field);
			fields.put(attribute.getTitle(), field);
			add(new JPanel());
			i++;
		}
		
		add(button);

		validate();
	}
	
	//new/search
	public CrudWindow(Table t, boolean search) {
		this.table = t;
		this.values = new Vector<Object>();
		this.fields = new HashMap<String, Object>();
		JButton button = new JButton();
		
		CreateWindow();
		add(new JLabel(table.getTitle()));
		add(new JPanel());
		add(new JPanel());
		Map<String, Attribute> attributes = table.getAttributes();
		int i = 0;
		for (String attributeKey : attributes.keySet()) {
			Attribute attribute = attributes.get(attributeKey);
			App.INSTANCE.createFieldFactory(attribute.getType());
			add(new JLabel(attribute.getTitle()));
			if(search) {
				System.out.println("usao");
				IField[] field = App.INSTANCE.getFieldFactory().createDoubleField();
				field[0].setValue(null);
				DecoratedField df = new DecoratedField(field[0]);
				add((Component) df);
				if(field[1] != null) {
					field[1].setValue(null);
					DecoratedField df1 = new DecoratedField(field[1]);
					add((Component) df1);
					fields.put(attribute.getTitle(), new Object[]{df, df1});
				}else {
					add(new JPanel());
					fields.put(attribute.getTitle(), new Object[]{df, null});
				}
			}else {
				IField field = App.INSTANCE.getFieldFactory().createField();
				field.setValue(null);
				add((Component) field);
				fields.put(attribute.getTitle(), field);
				add(new JPanel());				
			}
			
			i++;
		}
		
		if(search) {
			button.setText("search");
			button.addActionListener(new SearchAction(this));
		}else {
			button.setText("create");
			button.addActionListener(new CreateAction(this));
		}

		add(button);
		
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

