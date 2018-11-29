/***********************************************************************
 * Module:  Attribute.java
 * Author:  Boris
 * Purpose: Defines the Class Attribute
 ***********************************************************************/

package model;

/**
 * 
 * @author Boris
 *
 */

/** @pdOid f68bbcd5-5fff-40bf-b2e4-b849591951d2 */
public class Attribute {
	/** @pdOid 7040b2e2-2d9a-4727-ba48-9a7d1a7168b8 */
	private java.lang.String title;
	/** @pdOid 765fbd9a-c400-49d7-b983-93be80b6a6bd */
	private java.lang.String code;
	/** @pdOid 4d0d48f4-eb55-425b-8c21-51688eaaabfe */
	private java.lang.Boolean isPrimaryKey;
	/** @pdOid ae8d1eff-fc29-4260-a85d-c18001644ae6 */
	private java.lang.Boolean isRequired;
	/** @pdOid 858d66d7-4eb0-4328-b955-df8aa006c0cc */
	private java.lang.String type;
	/** @pdOid aae8a747-923e-4db4-b7a2-14d9946a9f8c */
	private java.lang.Integer maxLength;

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
				oldTable.removeAttribute(this);
			}
			if (newTable != null) {
				this.table = newTable;
				this.table.addAttribute(this);
			}
		}
	}

	public java.lang.String getTitle() {
		return title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.Boolean getIsPrimaryKey() {
		return isPrimaryKey;
	}

	public void setIsPrimaryKey(java.lang.Boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public java.lang.Boolean getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(java.lang.Boolean isRequired) {
		this.isRequired = isRequired;
	}

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(java.lang.Integer maxLength) {
		this.maxLength = maxLength;
	}

}