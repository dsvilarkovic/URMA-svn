package controler.mainframe;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import app.App;
import controler.handlers.IHandler;
import model.Table;
import model.resourceFactory.IResourceFactory;
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
		
		Table table = App.INSTANCE.getModel().getAllTables().get("NASELJENO_MESTO");
		App.INSTANCE.setFactory("db");
		IResourceFactory factory = App.INSTANCE.getFactory();
		IHandler handler = factory.createHandler();
		Vector<Vector<Object>> valueList = handler.read(table);
//		CrudWindow crudWindow = new CrudWindow(table, valueList.get(0)); // update
//		CrudWindow crudWindow = new CrudWindow(table, false); //add
		CrudWindow crudWindow = new CrudWindow(table, true); //search
		crudWindow.setVisible(true);
	}

	
}
