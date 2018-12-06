package model;

import java.util.HashMap;
import java.util.Map;

public class Package {

	/** @pdOid 510b7405-40d6-4f44-90f3-185f7ef7760f */
	private String title;
	/** @pdOid 201cae65-dbc0-4402-923e-0ad80fcd7c6e */
	private String code;

	private Map<String, Package> childPackages;
	private Map<String, Table> tables;

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
}