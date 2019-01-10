package model.treeAdapter;

import java.util.ArrayList;
import java.util.List;

import model.InformationResource;
import model.Package;
import model.Table;
import app.App;

/**
 * Klasa adapter za paket. Adaptira paket za stablo i za dalju upotrebu u programu.
 * @author filip
 */
public class AdapterPackage implements TreeParts{

	private Package paket;
	
	/**
	 * Konstruktor za kreiranje adaptera paketa
	 * @author filip
	 * @param pkt - paket koji treba da se adaptira
	 */
	public AdapterPackage(Package pkt) {
		paket = pkt;
	}

	@Override
	public String getImgPath() {
		return "resources/box.png";
	}

	@Override
	public String getName() {
		
		//return paket.getTitle();
		return App.INSTANCE.getTitleLanguagePack().getPackageTitle(paket.getCode());
	}

	@Override
	public void action() {
		System.out.println("Akcija u stablu na tabeli: " + paket.getTitle() + "   " + paket);
	}

	@Override
	public List<TreeParts> getContent(InformationResource infRes) {

		List<TreeParts> list = new ArrayList<TreeParts>();
		for (Package value : paket.getChildPackages().values()) {
			list.add(new AdapterPackage(value));
		}
		
		for (Table value : paket.getTables().values()) {
			if(value.getParentTables().size()==0)
				list.add(new AdapterTable(value));
		}
		
		return list.size()==0?null:list;
	}
}
