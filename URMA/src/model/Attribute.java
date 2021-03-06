/***********************************************************************
 * Module:  Attribute.java
 * Author:  Boris
 * Purpose: Defines the Class Attribute
 ***********************************************************************/

package model;

/** @pdOid f68bbcd5-5fff-40bf-b2e4-b849591951d2 */
/**
 * Model atributa (kolone) jedne tabele šeme informacionog resursa.
 * Identifikaciono je zavisan od matične tabele.
 * @author Boris
 *
 */
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
	private int precision;

	/** @pdRoleInfo migr=no name=Table assc=kolekcijaAtributa mult=1..1 side=A */
	private Table table;

	public Attribute() {
	}

	public Attribute(String title, String code, boolean isPrimaryKey, boolean isRequired, String type, int maxLength, int precision) {
		super();
		this.title = title;
		this.code = code;
		this.isPrimaryKey = isPrimaryKey;
		this.isRequired = isRequired;
		this.type = type;
		this.maxLength = maxLength;
		this.precision = precision;
	}

	/**
	 * Konstruktor za testiranje.
	 * 
	 * @author Dusan
	 * 
	 * 
	 * @param title - naslov atributa
	 * @param code - kod atributa
	 * @param isPrimaryKey - da li je primarni kljic
	 * @param isRequired - da li je obavezan
	 * @param type - tip
	 * @param maxLength - maksimalna duzina
	 * @param table - tabela
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

	/** */
	public Table getTable() {
		return table;
	}

	/**
	 * 
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

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		System.out.println("IKad");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attribute other = (Attribute) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	/**
	 * Metoda koja vrši proveru da li je atribut isti po vrednostima kao i parametar.
	 * @param a = Atribut s kojim se poredi.
	 * @return Da li su atributi isti ili ne.
	 */
	public boolean equals(Attribute a) {
		if (a == null) {
			return false;
		}
		
		if (!this.title.equals(a.title)) {
			return false;
		}
		
		if (!this.code.equals(a.code)) {
			return false;
		}
		
		if (!this.type.equals(a.type)) {
			return false;
		}
		
		if (this.maxLength != a.maxLength) {
			return false;
		}
		
		if (this.precision != a.precision) {
			return false;
		}
		
		if (this.isRequired != a.isRequired) {
			return false;
		}
		
		if (this.isPrimaryKey != a.isPrimaryKey) {
			return false;
		}
		
		return true;
	}

}