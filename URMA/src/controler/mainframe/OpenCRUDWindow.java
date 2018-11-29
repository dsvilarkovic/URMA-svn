package controler.mainframe;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import view.CrudWindow;

public class OpenCRUDWindow extends AbstractAction{

	private static final long serialVersionUID = 1L;

	public OpenCRUDWindow() {

		this(23);
		//TODO lokalizacija
		//putValue(NAME, MyApp.getInstance().getResourceBundle().getString("page.new"));
	}
	
	public OpenCRUDWindow(int prefferedSize) {
		putValue(NAME, "Open CRUD window");
		
		putValue(MNEMONIC_KEY, KeyEvent.VK_C);
		putValue(ACCELERATOR_KEY, 
				KeyStroke.getKeyStroke(
						KeyEvent.VK_C, 
						KeyEvent.CTRL_MASK));
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		CrudWindow crudWindow = new CrudWindow();
		crudWindow.setVisible(true);
	}

	
}
