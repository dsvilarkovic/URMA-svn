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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Attribute;
import model.Relation;
import model.Table;
import model.TitleLanguagePack;
import view.fieldFactory.DecoratedField;
import view.fieldFactory.IField;
import app.App;
import controler.crud.CreateAction;
import controler.crud.ForeignKeyAction;
import controler.crud.SearchAction;
import controler.crud.UpdateAction;

/**
 * Prozor koji služi za popunjavanje polja za create, update i search akcije nad podacima
 * @author Jelena
 *
 */

public class CrudWindow extends JDialog{

	private static final long serialVersionUID = 1L;
	private Table table;
	private Vector<Object> values;
	private Map<String, Object> fields;

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
		
		//Dusan radio lokalizaciju
		TitleLanguagePack titleLanguagePack = App.INSTANCE.getTitleLanguagePack();
		String localizedString = titleLanguagePack.getTableTitle(table.getCode());
		jp.add(new JLabel(localizedString));
		//Jelena
		//jp.add(new JLabel(table.getTitle()));
		add(jp);
		add(new JPanel());
		Map<String, Attribute> attributes = table.getAttributes();
		int i = 0;
		for (String attributeKey : attributes.keySet()) {
			Attribute attribute = attributes.get(attributeKey);
			App.INSTANCE.createFieldFactory(attribute.getType());
			jp = new JPanel();
			//Dusan radio lokalizaciju
			localizedString = titleLanguagePack.getAttributeTitle(attribute.getCode(), table.getCode());
			if(attribute.getIsPrimaryKey() || attribute.getIsRequired()) {
				localizedString += "*";
			}
			jp.add(new JLabel(localizedString));
			//Jelena
			//jp.add(new JLabel(attribute.getTitle()));
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
		add(new JPanel());

		for (String key : t.getParentTables().keySet()) {
			Table table = t.getParentTables().get(key);
			JButton b = new JButton();
			JPanel j = new JPanel();
			b.addActionListener(new ForeignKeyAction(this, table, key));
			b.setText(key);
			j.add(b);
			add(j);
		}
		
		validate();
	}
	
	/**
	 * Sluzi za trazenje relacije po kojoj su <code>childTableModel</code> i <br>
	 *  <code>parentTableModel</code> povezani
	 * @author Dusan
	 * @param childTable - tabela koja se nalazi u donjem TablePanelu
	 * @param parentTable- tabela koja se nalazi u gornjem TablePanel-u
	 */
	public Relation findRelation(Table childTable, Table parentTable) {
		
		//nadji tu relaciju
		Map<String, Relation> relations = App.INSTANCE.getModel().getRelations();
		System.out.println(relations);
		//nadji relaciju gde se ove dve tabele nalaze
		for (String relationKey : relations.keySet()) {
			Relation relationValue = relations.get(relationKey);
			if(relationValue.getDestinationTable().getCode().equals(childTable.getCode())
					&& relationValue.getSourceTable().getCode().equals(parentTable.getCode())) {
				return relationValue;
			}
				
		}
		
		return null;
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
		//Dusan radio lokalizaciju
		TitleLanguagePack titleLanguagePack = App.INSTANCE.getTitleLanguagePack();
		String localizedString = titleLanguagePack.getTableTitle(table.getCode());
		jp.add(new JLabel(localizedString));
		//Jelena
		//jp.add(new JLabel(table.getTitle()));
		
		add(jp);
		add(new JPanel());
		Map<String, Attribute> attributes = table.getAttributes();
		for (String attributeKey : attributes.keySet()) {
			Attribute attribute = attributes.get(attributeKey);
			App.INSTANCE.createFieldFactory(attribute.getType());
			jp = new JPanel();
			//Dusan radio lokalizaciju
			localizedString = titleLanguagePack.getAttributeTitle(attribute.getCode(), table.getCode());
			if((attribute.getIsPrimaryKey() || attribute.getIsRequired()) && !search) {
				localizedString += "*";
			}
			jp.add(new JLabel(localizedString));
			//Jelena
			//jp.add(new JLabel(attribute.getTitle()));
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
		add(new JPanel());
		
		for (String key : t.getParentTables().keySet()) {
			Table table = t.getParentTables().get(key);
			JButton b = new JButton();
			JPanel j = new JPanel();
			b.addActionListener(new ForeignKeyAction(this, table, key));
			b.setText(key);
			j.add(b);
			add(j);
		}
		
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


	/**
		Metoda koja popunjava polja na osnovu stranog kljuca
		@author - Jelena
		@param selectedRow - mapa&lt;atribut, vrednost&gt; kojima treba popuniti polja
	**/
	public void fillFields(Map<String, Object> selectedRow) {
		Map<String, Attribute> attributes = this.table.getAttributes();
		IField field = null;
		for (String attributeKey : attributes.keySet()) {
			Attribute attribute = attributes.get(attributeKey);
			if(fields.get(attribute.getTitle()) instanceof IField) {
				field = (IField) fields.get(attribute.getTitle());
			}else if(fields.get(attribute.getTitle()) instanceof DecoratedField[]) {
				DecoratedField decField = ((DecoratedField) ((DecoratedField[])fields.get(attribute.getTitle()))[0]);
				field = (IField) decField.getField();
			}
			
			if(selectedRow.get(attribute.getTitle()) != null){
				System.out.println("VALUE: " + field.getValue());
				if(!attribute.getIsPrimaryKey() || field.getValue() == null) {
					field.setValue(selectedRow.get(attribute.getTitle()));
				}else {
					ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
					JOptionPane.showMessageDialog(null, resourceBundle.getString("table.constraints.fk_change"));
					return;
				}
			}
			
		}
	}
}

