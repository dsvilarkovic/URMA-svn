package controler;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author filip
 *
 */
public class Open {

	public Object open(String extension) {
		return openThis(getPath(extension));
	}

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
			JOptionPane.showMessageDialog(null, "Please select a valid file.");
			return null;
		}
	}

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
