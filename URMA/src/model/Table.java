/***********************************************************************
 * Module:  Table.java
 * Author:  Boris
 * Purpose: Defines the Class Table
 ***********************************************************************/

package model;

import java.util.*;

/** @pdOid 964e13bd-7bb2-48fd-8a10-ad5bb18e49aa */
public class Table {
	/** @pdOid e4b2fa55-2b4b-4511-af71-90a0306db4b2 */
	private String title;
	/** @pdOid d5cc1d3a-c489-414b-b1ab-93a62ec3ed04 */
	private String code;

	
	private Map<String, Attribute> attributes;
	private Map<String, Table> parentTables;
	private Map<String, Table> childTables;
	
	public Table() {}
	
	public Table(String title, String code) {
		super();
		this.title = title;
		this.code = code;
	}

	public Map<String, Attribute> getAttributes() {
		if (attributes == null)
			attributes = new HashMap<String, Attribute>();
		return attributes;
	}

	public Attribute getAttribute(String attributeCode) {
		if (attributeCode == null) {
			return null;
		}
		if (this.attributes.containsKey(attributeCode)) {
			return this.attributes.get(attributeCode);
		}
		return null;
	}
	
	public void addAttributes(Attribute newAttribute) {
		if (newAttribute == null)
			return;
		if (this.attributes == null)
			this.attributes = new HashMap<String, Attribute>();
		if (!this.attributes.containsKey(newAttribute.getCode())) {
			this.attributes.put(newAttribute.getCode(), newAttribute);
			newAttribute.setTable(this);
		}
	}
	
	public void removeAttributes(Attribute oldAttribute) {
		if (oldAttribute == null)
			return;
		if (this.attributes != null)
			if (this.attributes.containsKey(oldAttribute.getCode())) {
				this.attributes.remove(oldAttribute.getCode());
				oldAttribute.setTable((Table) null);
			}
	}

	public Map<String, Table> getParentTables() {
		if (parentTables == null)
			parentTables = new HashMap<String, Table>();
		return parentTables;
	}

	public void addParentTables(Table newTable) {
		if (newTable == null)
			return;
		if (this.parentTables == null)
			this.parentTables = new HashMap<String, Table>();
		if (!this.parentTables.containsKey(newTable.getCode()))
			this.parentTables.put(newTable.getCode(), newTable);
	}

	public void removeParentTables(Table oldTable) {
		if (oldTable == null)
			return;
		if (this.parentTables != null)
			if (this.parentTables.containsKey(oldTable.getCode()))
				this.parentTables.remove(oldTable.getCode());
	}

	public void removeAllParentTables() {
		if (parentTables != null)
			parentTables.clear();
	}

	public Map<String, Table> getChildTables() {
		if (childTables == null)
			childTables = new HashMap<String, Table>();
		return childTables;
	}

	public void addChildTables(Table newTable) {
		if (newTable == null)
			return;
		if (this.childTables == null)
			this.childTables = new HashMap<String, Table>();
		if (!this.childTables.containsKey(newTable.getCode()))
			this.childTables.put(newTable.getCode(), newTable);
	}

	public void removeChildTables(Table oldTable) {
		if (oldTable == null)
			return;
		if (this.childTables != null)
			if (this.childTables.containsKey(oldTable.getCode()))
				this.childTables.remove(oldTable.getCode());
	}

	public void removeAllChildTables() {
		if (childTables != null)
			childTables.clear();
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
	 * Sluzi da proveri da li tabela identifikaciono zavisi od parametra tabele.
	 * </br>
	 * Identifikaciona zavisnost - da li se deo primarnih atributa tabele nalazi kao
	 * primarni atribut u drugoj tabeli
	 * 
	 * @param table
	 * @return ako se pojavi bilo koji primarni atribut koji postoji kao primarni
	 *         atribut </br>
	 *         u drugoj tabeli, zaustavi pretragu i vrati <code>true </code>
	 */

	public boolean isIdDependentFrom(Table compareTable) {

		for (String attributeKey : this.attributes.keySet()) {
			//prvo proveri da li je odabrani atribut iz tabele uopste primarni
			Attribute attribute = this.attributes.get(attributeKey);
			
			if(attribute.getIsPrimaryKey() == true) {			
				Attribute compareAttribute = compareTable.getAttributes().get(attributeKey);
				
				//ako je nadjen atribut u drugoj tabeli i taj attribut je primarni u tabeli, onda vrati true
				if(compareAttribute != null && compareAttribute.getIsPrimaryKey() == true) {
					return true;
				}
			}
		}
		
		//sve ok proslo moze dalje
		return false;


//		if (title.equals("paretnNode") && compareTable.getTitle().equals("elderNode"))
//			return true;
//		else if (title.equals("childNode") && compareTable.getTitle().equals("paretnNode"))
//			return true;
//		else 
//			return false;
	}

}