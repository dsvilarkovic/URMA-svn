package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Model paketa u informacionom resursu.
 * Sadrzi njemu dodeljene tabele.
 * @author Boris
 *
 */
public class Package {

	/** @pdOid 510b7405-40d6-4f44-90f3-185f7ef7760f */
	private String title;
	/** @pdOid 201cae65-dbc0-4402-923e-0ad80fcd7c6e */
	private String code;

	private Map<String, Package> childPackages;
	private Map<String, Table> tables;
	
	public Package() {}
	
	public Package(String code, String title) {
		this.code = code;
		this.title = title;
	}

	public Map<String, Package> getChildPackages() {
		if (childPackages == null)
			childPackages = new HashMap<String, Package>();
		return childPackages;
	}

	public void addChildPackages(Package newPackage) {
		if (newPackage == null)
			return;
		if (this.childPackages == null)
			this.childPackages = new HashMap<String, Package>();
		if (!this.childPackages.containsKey(newPackage.getCode()))
			this.childPackages.put(newPackage.getCode(), newPackage);
	}

	public void removeChildPackages(Package oldPackage) {
		if (oldPackage == null)
			return;
		if (this.childPackages != null)
			if (this.childPackages.containsKey(oldPackage.getCode()))
				this.childPackages.remove(oldPackage.getCode());
	}

	public void removeAllChildPackages() {
		if (childPackages != null)
			childPackages.clear();
	}

	public Map<String, Table> getTables() {
		if (tables == null)
			tables = new HashMap<String, Table>();
		return tables;
	}

	public void addTables(Table newTable) {
		if (newTable == null)
			return;
		if (this.tables == null)
			this.tables = new HashMap<String, Table>();
		if (!this.tables.containsKey(newTable.getCode()))
			this.tables.put(newTable.getCode(), newTable);
	}

	public void removeTables(Table oldTable) {
		if (oldTable == null)
			return;
		if (this.tables != null)
			if (this.tables.containsKey(oldTable.getCode()))
				this.tables.remove(oldTable.getCode());
	}

	public void removeAllTables() {
		if (tables != null)
			tables.clear();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * Metoda koja vrši proveru da li je paket isti po vrednostima kao i parametar.
	 * @param p = Paket s kojim se poredi.
	 * @return Da li su paketi isti ili ne.
	 */
	public boolean equals(Package p) {
		if (p == null) {
			return false;
		}
		
		if (!this.title.equals(p.title)) {
			return false;
		}
		
		if (!this.code.equals(p.code)) {
			return false;
		}
		
		if (this.getTables().size() != p.getTables().size()) {
			return false;
		}
		
		for (String tcode : this.getTables().keySet()) {
			if (!p.getTables().containsKey(tcode)) {
				return false;
			}
		}
		
		if (this.getChildPackages().size() != p.getChildPackages().size()) {
			return false;
		}
		
		for (String pcode : this.getChildPackages().keySet()) {
			if (!this.getChildPackages().get(pcode).equals(p.getChildPackages().get(pcode))) {
				return false;
			}
		}
		
		return true;
	}
}
