package model.treeAdapter;

import java.util.ArrayList;
import java.util.List;

import model.InformationResource;
import model.Package;
import model.Table;

public class AdapterPackage implements TreeParts{

	private Package paket;

	public AdapterPackage(Package pkt) {
		paket = pkt;
	}

	@Override
	public String getImgPath() {
		return "resources/box.png";
	}

	@Override
	public String getName() {
		return paket.getTitle();
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
			list.add(new AdapterTable(value));
		}
		
		if (list.size() == 0)
			return null;

		return list;
	}
	
	
}
