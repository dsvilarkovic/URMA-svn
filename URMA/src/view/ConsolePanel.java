package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Klasa u kojoj se ispisuju informacije o uspesnosti izvrsavanja akcija u Editoru
 * @author filip
 */
public class ConsolePanel  extends JPanel{

	private static final long serialVersionUID = 1L;
	JTextArea textArea = new JTextArea();
	
	public ConsolePanel() {
		setLayout(new BorderLayout());
		textArea.setEditable(false);
		textArea.setFocusable(false);
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setForeground(Color.WHITE);
		JScrollPane sp = new JScrollPane(textArea);
		Dimension d = sp.getPreferredSize();
		d.height = 100;
		sp.setPreferredSize(d);
		add(sp);
	}
	
	public JTextArea getTextArea() {return textArea;}
}
