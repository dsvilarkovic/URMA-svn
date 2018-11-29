/***********************************************************************
 * Module:  Table.java
 * Author:  Boris
 * Purpose: Defines the Class Table
 ***********************************************************************/

package model;

/**
 * 
 * @author Boris
 *
 */

/** @pdOid 964e13bd-7bb2-48fd-8a10-ad5bb18e49aa */
public class Table {
	/** @pdOid e4b2fa55-2b4b-4511-af71-90a0306db4b2 */
	private java.lang.String title;
	/** @pdOid d5cc1d3a-c489-414b-b1ab-93a62ec3ed04 */
	private java.lang.String code;

	/**
	 * @pdRoleInfo migr=no name=Attribute assc=kolekcijaAtributa
	 *             coll=java.util.Collection impl=java.util.HashSet mult=1..*
	 *             type=Composition
	 */
	private java.util.Collection<Attribute> attribute;

	/** @pdGenerated default getter */
	public java.util.Collection<Attribute> getAttribute() {
		if (attribute == null)
			attribute = new java.util.HashSet<Attribute>();
		return attribute;
	}

	/** @pdGenerated default iterator getter */
	public java.util.Iterator getIteratorAttribute() {
		if (attribute == null)
			attribute = new java.util.HashSet<Attribute>();
		return attribute.iterator();
	}

	/**
	 * @pdGenerated default setter
	 * @param newAttribute
	 */
	public void setAttribute(java.util.Collection<Attribute> newAttribute) {
		removeAllAttribute();
		for (java.util.Iterator iter = newAttribute.iterator(); iter.hasNext();)
			addAttribute((Attribute) iter.next());
	}

	/**
	 * @pdGenerated default add
	 * @param newAttribute
	 */
	public void addAttribute(Attribute newAttribute) {
		if (newAttribute == null)
			return;
		if (this.attribute == null)
			this.attribute = new java.util.HashSet<Attribute>();
		if (!this.attribute.contains(newAttribute)) {
			this.attribute.add(newAttribute);
			newAttribute.setTable(this);
		}
	}

	/**
	 * @pdGenerated default remove
	 * @param oldAttribute
	 */
	public void removeAttribute(Attribute oldAttribute) {
		if (oldAttribute == null)
			return;
		if (this.attribute != null)
			if (this.attribute.contains(oldAttribute)) {
				this.attribute.remove(oldAttribute);
				oldAttribute.setTable((Table) null);
			}
	}

	/** @pdGenerated default removeAll */
	public void removeAllAttribute() {
		if (attribute != null) {
			Attribute oldAttribute;
			for (java.util.Iterator iter = getIteratorAttribute(); iter.hasNext();) {
				oldAttribute = (Attribute) iter.next();
				iter.remove();
				oldAttribute.setTable((Table) null);
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

}