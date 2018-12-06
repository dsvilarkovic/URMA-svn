package app;

import java.text.ParseException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel;

/**
 * 
 * @author filip
 *
 */
public class ApplicationStart {

	public static void main(String[] args) throws UnsupportedLookAndFeelException {

		try {
			UIManager.setLookAndFeel(new SyntheticaBlackStarLookAndFeel());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		App.INSTANCE.start();
	}
}
