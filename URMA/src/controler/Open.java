package controler;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.App;

/**
 * Klasa za univerzalno otvaranje i ucitavanje objekata
 * @author filip
 */
public class Open {

	/**
	 * Metoda za otvaranje i ucitavanje objekta.
	 * Prvo se nudi dijalog za izbor objekta koji treba da se ucita,
	 * a onda se ucitava izabrani objekat
	 * @author filip
	 * @param extension - ekstenzije vrste objekta za JFileChooser filter <br>
	 * 	moze da se navodi vise ekstenzija npr: json/db/xml
	 * @return {@link Object} - vraca otvoren/ucitan objekat
	 */
	public Object open(String extension) {
		return openThis(getPath(extension));
	}

	/**
	 * Metoda za otvaranje i ucitavanje objekta.
	 * Ucitava objekat kad je poznata putanja do njega
	 * @author filip
	 * @param path - putanja do objekta
	 * @return {@link Object} - vraca otvoren/ucitan objekat
	 */
	public Object openThis(String path) {

		if (path != null) {
			File f = new File(path);
			ObjectInputStream oos;
			Object saved = null;
			try {
				oos = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
				saved = (Object) oos.readObject();
				oos.close();
			} catch (IOException e1) {
				try {
					saved = new String(Files.readAllBytes(f.toPath()));
					System.out.println("File was not java Object class, reading it as string");
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	
			return saved;
		}
		else {
			ResourceBundle resourceBundle = App.INSTANCE.getResourceBundle();
			String validFileErrorText = resourceBundle.getString("valid.file.error");
			JOptionPane.showMessageDialog(null, validFileErrorText);
			return null;
		}
	}

	/**
	 * Metoda za otvaranje dijaloga za izbor fajla.
	 * @author filip
	 * @param extension - ekstenzije vrste objekta za JFileChooser filter <br>
	 * moze da se navodi vise ekstenzija npr: json/db/xml
	 * @return {@link String} - vraca putanju do izabranog fajla
	 */
	public String getPath(String extension) {

		File f = null;
		JFileChooser fc = new JFileChooser();
		String[] extensionParts = extension.split("/");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(null, extensionParts);
		fc.setFileFilter(filter);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result = fc.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			f = fc.getSelectedFile();
			return f.getAbsolutePath();
		}
		return null;
	}

}
