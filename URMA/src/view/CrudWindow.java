package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.NumberFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import app.App;
import view.fieldFactory.BooleanField;
import view.fieldFactory.DateField;
import view.fieldFactory.DateLabelFormatter;
import view.fieldFactory.DecoratedField;
import view.fieldFactory.NumberField;
import view.fieldFactory.TextField;

public class CrudWindow extends JFrame{

	private static final long serialVersionUID = 1L;

	public CrudWindow() {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setSize((int) (screenSize.getWidth() * 0.71), (int) (screenSize.getHeight() * 0.71));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setTitle("CRUD");
		setLayout(new GridLayout(0,2));
		setIconImage((new ImageIcon("resources/palm-tree.png")).getImage());
		
		add(new JLabel("text:"));
		App.INSTANCE.createFieldFactory("text");
		TextField textField = (TextField) App.INSTANCE.getFieldFactory().createField();
		textField.getJTextField().setText("12.12.1234");
		add(textField);
		
		add(new JLabel("surname:"));
		TextField textField1 = (TextField) App.INSTANCE.getFieldFactory().createField();
		textField1.getJTextField().setText("dokic");
		DecoratedField df = new DecoratedField(textField1);
		add(df);
		
		add(new JLabel("date:"));
		App.INSTANCE.createFieldFactory("date");
		DateField dateField = (DateField) App.INSTANCE.getFieldFactory().createField();
		dateField.getJDatePickerImpl().getModel().setDate(1990, 8, 24);
		dateField.getJDatePickerImpl().getModel().setSelected(true);
		add(dateField);
		
		add(new JLabel("number:"));
		App.INSTANCE.createFieldFactory("number");
		NumberField numberField = (NumberField) App.INSTANCE.getFieldFactory().createField();
		numberField.getJTextField().setText("12345");
		add(numberField);
		
		add(new JLabel("number:"));
		NumberField numberField1 = (NumberField) App.INSTANCE.getFieldFactory().createField();
		numberField1.getJTextField().setText("1234.5");
		DecoratedField df1 = new DecoratedField(numberField1);
		add(df1);
		
		add(new JLabel("bool:"));
		App.INSTANCE.createFieldFactory("boolean");
		BooleanField booleanField = (BooleanField) App.INSTANCE.getFieldFactory().createField();
		booleanField.getJCheckBox().setSelected(true);
		DecoratedField df2 = new DecoratedField(booleanField);
		add(df2);

		validate();
	}
}
