package controler;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Klasa za univerzlno snimanje objekta
 * @author filip
 */
public class Save implements Serializable {

	private static final long serialVersionUID = 2283855445039462791L;

	/**
	 * Metoda za snimanje objekta neke klase
	 * @author filip
	 * @param object - objekat koji treba da se snimi
	 * @param extension - ekstenzije pod kojom zeli da se snimi objekat
	 */
	public void saveAs(Object object, String extension) {

		JFileChooser fc = new JFileChooser();
		fc.setApproveButtonText("Save");
		fc.setDialogTitle("Save file");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(null, extension);
		fc.setFileFilter(filter);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result = fc.showOpenDialog(null);
		if (result != JFileChooser.APPROVE_OPTION)
			return;

		String path = fc.getSelectedFile().getAbsolutePath().replace("." + extension, "");

		File f = new File(path + "." + extension);
		System.out.println(f.getAbsolutePath());
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
			oos.writeObject(object);
			oos.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
