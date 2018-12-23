package view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

/**
 * Klasa glavnog dela Editora. Deo gde se vrsi unos seme.
 * @author filip
 */
public class MainPanel extends JPanel {

	private static final long serialVersionUID = -8668618688820184387L;
	RSyntaxTextArea textArea = new RSyntaxTextArea();

	public MainPanel() {
		setLayout(new BorderLayout());
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
		textArea.setCodeFoldingEnabled(true);
		Font font = textArea.getFont();
		float size = font.getSize() + 3.0f;
		textArea.setFont( font.deriveFont(size) );
		RTextScrollPane sp = new RTextScrollPane(textArea);
		add(sp);
	}
	
	public RSyntaxTextArea getTextArea() {return textArea;}
}
