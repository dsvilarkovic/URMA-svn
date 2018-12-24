package view.dialogs;

import java.util.Vector;

import app.App;
import controler.handlers.IHandler;
import model.Attribute;
import model.Table;
import model.resourceFactory.IResourceFactory;

public class TestChooseRefColumnVal {

	public static void main(String[] args) {
		//Table table = App.INSTANCE.getModel().getAllTables().get("NASELJENO_MESTO");
		Table table = createMockTable();
		App.INSTANCE.setFactory("db");
		IResourceFactory factory = App.INSTANCE.getFactory();
		IHandler handler = factory.createHandler();
		Vector<Vector<Object>> valueList = handler.read(table);
		
		ChooseReferencedCollumnValuesDialog testDialog = new ChooseReferencedCollumnValuesDialog(table);
		
//		new ChooseSourceDialog();
	}
	
	public static Table createMockTable() {
		Table table = new  Table("Naseljeno mesto", "NASELJENO_MESTO");
		Attribute attribute1 = new Attribute("BrojStanov", "NM_STAN",false,false, "", 0, table);
		Attribute attribute2 = new Attribute("Drzava", "DRZ_ID",false,false, "", 0, table);
		Attribute attribute3 = new Attribute("Naziv", "NM_NAZIV",false,false, "", 0, table);
		Attribute attribute4 = new Attribute("Grad", "NM_GRAD",false,false, "", 0, table);
		Attribute attribute5 = new Attribute("Postanski broj", "NM_PB",false,false, "", 0, table);
		
		table.addAttributes(attribute1);
		table.addAttributes(attribute2);
		table.addAttributes(attribute3);
		table.addAttributes(attribute4);
		table.addAttributes(attribute5);
		
		return table;

	}

}
