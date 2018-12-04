package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import app.App;
import view.fieldFactory.BooleanField;
import view.fieldFactory.DateField;
import view.fieldFactory.DecoratedField;
import view.fieldFactory.NumberField;
import view.fieldFactory.TextField;

public class CrudWindow extends JDialog{

	private static final long serialVersionUID = 1L;

	public CrudWindow(String[] title, Object[] values, String[] types, Boolean search) {

		CreateWindow();
		
		for(int i = 0; i < title.length; i++) {
			add(new JLabel(title[i]));
			if(types[i].equals("number")) {
				App.INSTANCE.createFieldFactory("number");
				NumberField numberField = (NumberField) App.INSTANCE.getFieldFactory().createField();
				numberField.getJTextField().setText(values[i].toString());
				if(search) {
					DecoratedField df = new DecoratedField(numberField);
					add(df);
				}else {
					add(numberField);
				}
			}else if(types[i].equals("text")) {
				App.INSTANCE.createFieldFactory("text");
				TextField textField = (TextField) App.INSTANCE.getFieldFactory().createField();
				textField.getJTextField().setText(values[i].toString());
				if(search) {
					DecoratedField df = new DecoratedField(textField);
					add(df);
				}else {
					add(textField);
				}
			}else if(types[i].equals("boolean")) {
				App.INSTANCE.createFieldFactory("boolean");
				BooleanField booleanField = (BooleanField) App.INSTANCE.getFieldFactory().createField();
				booleanField.getJCheckBox().setSelected((boolean) values[i]);
				if(search) {
					DecoratedField df = new DecoratedField(booleanField);
					add(df);
				}else {
					add(booleanField);
				}
			}else if(types[i].equals("date")) {
				Integer[] date = (Integer[]) values[i];
				App.INSTANCE.createFieldFactory("date");
				DateField dateField = (DateField) App.INSTANCE.getFieldFactory().createField();
				dateField.getJDatePickerImpl().getModel().setDate(date[2], date[1], date[0]);
				dateField.getJDatePickerImpl().getModel().setSelected(true);
				if(search) {
					DecoratedField df = new DecoratedField(dateField);
					add(df);
				}else {
					add(dateField);
				}
			}else {
				JOptionPane.showMessageDialog(null, "We dont know that type", "Invalid resource type", JOptionPane.ERROR_MESSAGE);
			}
		}

		validate();
	}
	
	public CrudWindow(String[] title, String[] types) {

		CreateWindow();
		
		for(int i = 0; i < title.length; i++) {
			add(new JLabel(title[i]));
			if(types[i].equals("number")) {
				App.INSTANCE.createFieldFactory("number");
				NumberField numberField = (NumberField) App.INSTANCE.getFieldFactory().createField();
				numberField.getJTextField().setText("");
				add(numberField);
			}else if(types[i].equals("text")) {
				App.INSTANCE.createFieldFactory("text");
				TextField textField = (TextField) App.INSTANCE.getFieldFactory().createField();
				textField.getJTextField().setText("");
				add(textField);
			}else if(types[i].equals("boolean")) {
				App.INSTANCE.createFieldFactory("boolean");
				BooleanField booleanField = (BooleanField) App.INSTANCE.getFieldFactory().createField();
				booleanField.getJCheckBox().setSelected(false);
				add(booleanField);
			}else if(types[i].equals("date")) {
				App.INSTANCE.createFieldFactory("date");
				DateField dateField = (DateField) App.INSTANCE.getFieldFactory().createField();
				add(dateField);
			}else {
				JOptionPane.showMessageDialog(null, "We dont know that type", "Invalid resource type", JOptionPane.ERROR_MESSAGE);
			}
		}

		validate();
	}
	
	private void CreateWindow() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setSize((int) (screenSize.getWidth() * 0.71), (int) (screenSize.getHeight() * 0.71));
		
		/**
		 * @author Dusan 
		 * Obrisao sam ti @Jelena da je ovaj prozor JFrame, posto zatvara i prozor ispod njega
		 * tj glavni. Samo jedan JFrame sme postojati, ovo je JDialog
		 * Ti samo setuj sa setModal(true/false) da li zelis da bude modalni ili ne.
		 */
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setTitle("CRUD");
		setLayout(new GridLayout(0,2));
		setIconImage((new ImageIcon("resources/palm-tree.png")).getImage());
	}
}

