package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import app.App;
import view.fieldFactory.DecoratedField;
import view.fieldFactory.IField;

public class CrudWindow extends JDialog{

	private static final long serialVersionUID = 1L;

	public CrudWindow(String[] title, Object[] values, String[] types, Boolean search) {

		CreateWindow();
		
		for(int i = 0; i < title.length; i++) {
			try {
				add(new JLabel(title[i]));
				App.INSTANCE.createFieldFactory(types[i]);
				IField field = App.INSTANCE.getFieldFactory().createField();
				field.setValue(values[i]);
				if(search) {
					DecoratedField df = new DecoratedField(field);
					add(df);
				}else {
					add((Component) field);
				}
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "We dont know that type", "Invalid resource type", JOptionPane.ERROR_MESSAGE);
			}
		}

		validate();
	}
	
	public CrudWindow(String[] title, String[] types) {

		CreateWindow();
		
		for(int i = 0; i < title.length; i++) {
			try {
				add(new JLabel(title[i]));
				App.INSTANCE.createFieldFactory(types[i]);
				IField field = App.INSTANCE.getFieldFactory().createField();
				field.setValue(null);
				add((Component) field);
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "We dont know that type", "Invalid resource type", JOptionPane.ERROR_MESSAGE);
			}
		}

		validate();
	}
	
	private void CreateWindow() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setSize((int) (screenSize.getWidth() * 0.71), (int) (screenSize.getHeight() * 0.71));
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(true);
		setTitle("CRUD");
		setLayout(new GridLayout(0,2));
		setIconImage((new ImageIcon("resources/palm-tree.png")).getImage());
	}
}

