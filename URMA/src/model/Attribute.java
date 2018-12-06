/***********************************************************************
 * Module:  Attribute.java
 * Author:  Boris
 * Purpose: Defines the Class Attribute
 ***********************************************************************/

package model;

/** @pdOid f68bbcd5-5fff-40bf-b2e4-b849591951d2 */
public class Attribute {
	/** @pdOid 7040b2e2-2d9a-4727-ba48-9a7d1a7168b8 */
	private String title;
	/** @pdOid 765fbd9a-c400-49d7-b983-93be80b6a6bd */
	private String code;
	/** @pdOid 4d0d48f4-eb55-425b-8c21-51688eaaabfe */
	private boolean isPrimaryKey;
	/** @pdOid ae8d1eff-fc29-4260-a85d-c18001644ae6 */
	private boolean isRequired;
	/** @pdOid 858d66d7-4eb0-4328-b955-df8aa006c0cc */
	private String type;
	/** @pdOid aae8a747-923e-4db4-b7a2-14d9946a9f8c */
	private int maxLength;

	/** @pdRoleInfo migr=no name=Table assc=kolekcijaAtributa mult=1..1 side=A */
	private Table table;

	/** @pdGenerated default parent getter */
	public Table getTable() {
		return table;
	}

	/**
	 * @pdGenerated default parent setter
	 * @param newTable
	 */
	public void setTable(Table newTable) {
		if (this.table == null || !this.table.equals(newTable)) {
			if (this.table != null) {
				Table oldTable = this.table;
				this.table = null;
				oldTable.removeAttributes(this);
			}
			if (newTable != null) {
				this.table = newTable;
				this.table.addAttributes(this);
			}
		}
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

	public boolean getIsPrimaryKey() {
		return isPrimaryKey;
	}

	public void setIsPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public boolean getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	
	public Attribute() {}

	/**
	 * Konstruktor za testiranje. 
	 * @author Dusan 
	 * 
	 * 
	 * @param title
	 * @param code
	 * @param isPrimaryKey
	 * @param isRequired
	 * @param type
	 * @param maxLength
	 * @param table
	 */
	public Attribute(String title, String code, Boolean isPrimaryKey, Boolean isRequired, String type,
			Integer maxLength, Table table) {
		super();
		this.title = title;
		this.code = code;
		this.isPrimaryKey = isPrimaryKey;
		this.isRequired = isRequired;
		this.type = type;
		this.maxLength = maxLength;
		this.table = table;
	}

	/* (non-Javadoc)
	 * Koristi se za poredjenje izmedju atribute po code-u, ili ako je prosledjen string.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Attribute) {
			Attribute compareAttribute = (Attribute) obj;
			if(compareAttribute.getCode().equals(this.getCode())) {
				return true;
			}
		}
		else if(obj instanceof String) {
			String attributeCode = (String) obj;
			if(attributeCode.equals(this.getCode())) {
				return true;
			}
		}
		return false;
	}
}