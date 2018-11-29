package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import app.App;
import model.fieldFactory.DecoratedField;
import model.fieldFactory.TextField;

public class CrudTestWindow extends JFrame{

	private static final long serialVersionUID = 1L;

	public CrudTestWindow() {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setSize((int) (screenSize.getWidth() * 0.71), (int) (screenSize.getHeight() * 0.71));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setTitle("CRUD");
		setLayout(new GridLayout(0,2));
		setIconImage((new ImageIcon("resources/palm-tree.png")).getImage());
		
		add(new JLabel("name:"));
		App.INSTANCE.createFieldFactory("text");
		TextField textField = (TextField) App.INSTANCE.getFieldFactory().createField();
		textField.getJTextField().setText("jelena");
		add(textField);
		
		add(new JLabel("text:"));
		App.INSTANCE.createFieldFactory("text");
		TextField textField0 = (TextField) App.INSTANCE.getFieldFactory().createField();
		textField0.getJTextField().setText("asdf");
		add(textField0);
		
		add(new JLabel("surname:"));
		TextField textField1 = (TextField) App.INSTANCE.getFieldFactory().createField();
		textField1.getJTextField().setText("dokic");
		DecoratedField df = new DecoratedField(textField1);
		add(df);

		validate();
	}
}
