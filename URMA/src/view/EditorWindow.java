package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.io.FilenameUtils;

import app.App;
import controler.EditorWindowClosingAction;
import controler.validators.IValidator;
import model.fieldFactory.DecoratedField;
import model.fieldFactory.IField;
import model.fieldFactory.TextField;

/**
 * 
 * @author filip
 *
 */
public class EditorWindow extends JDialog {

	private static final long serialVersionUID = -8130256364738099887L;
	private MainPanel mainPanel = new MainPanel();
	private Toolbar toolbar = new Toolbar();
	private ConsolePanel console = new ConsolePanel();

	public EditorWindow() {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setSize((int) (screenSize.getWidth() * 0.71), (int) (screenSize.getHeight() * 0.71));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new EditorWindowClosingAction());
		setLocationRelativeTo(null);
		setResizable(true);
		setTitle("Editor");
		setModal(true);
		setLayout(new BorderLayout());
		setIconImage((new ImageIcon("resources/palm-tree.png")).getImage());

		add(toolbar, BorderLayout.NORTH);
		add(mainPanel);
		add(console, BorderLayout.SOUTH);

		validate();
		
//		System.out.println(App.INSTANCE);
//
//		App.INSTANCE.createFieldFactory("text");
//		TextField textField = (TextField) App.INSTANCE.getFieldFactory().createField();
//		textField.getJTextField().setText("jelena");
//		System.out.println(textField.getJTextField().getText());
//		
//		DecoratedField df = new DecoratedField(textField);
//		System.out.println(((JTextField)df.getField()).getText());
//		System.out.println(df.getCheckbox().isSelected());
	}

	public Toolbar getToolbar() {
		return toolbar;
	}

	public MainPanel getMainPanel() {
		return mainPanel;
	}

	public ConsolePanel getConsolePanel() {
		return console;
	}
}
