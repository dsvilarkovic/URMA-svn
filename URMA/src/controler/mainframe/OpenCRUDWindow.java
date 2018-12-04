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
		String[] titles = new String[4];
		titles[0] = "title1";
		titles[1] = "title2";
		titles[2] = "title22";
		titles[3] = "title3";
		
		Object[] values = new Object[4];
		
		values[0] = "jelena";
		
		values[1] = 2.2;
		
		Integer[] date= new Integer[3];
		date[0] = 2;//dan
		date[1] = 2; // mesec, broji od nula, tako da ce ovo staviti mart.. znam da je glupavo ali tako radi..
		date[2] = 1212; //godina
		
		values[2] = date;
		values[3] = false;
		
		String[] types = new String[4];
		types[0] = "text";
		types[1] = "number";
		types[2] = "date";
		types[3] = "boolean";
		CrudWindow crudWindow = new CrudWindow(titles, values, types, false); //titles su nazivi kolona, values su vrednosti torke, types su tipovi vrednosti
		//tipove mi saljes u obliku stringa: "text", "number", "boolean", "date"
		//vazno je da se na mestu [0] nalazi title[0], value[0] i type[0], tj hocu da kazem da je bitan redosled
		// poslednji parametar govori da li treba dekorisati field, i true je ako je akcija search, inace je false
		//date vidi kako dobijas iz baze pa cu prilagoditi da primam tu vrednost(za sada mi saljes niz od 3 int-a(day, month, year))
//		CrudWindow crudWindow = new CrudWindow(titles, types); //za new akciju(posto mi tada ne trebaju vrednosti i nikad nije dekorisano)
		crudWindow.setVisible(true);
	}

	
}
