/***********************************************************************
 * Module:  Relation.java
 * Author:  Boris
 * Purpose: Defines the Class Relation
 ***********************************************************************/

package model;

/**
 * 
 * @author Boris
 *
 */

/** @pdOid 21a1a755-8af1-411b-9361-e211a1d1a8d1 */
public class Relation {
	/** @pdOid 0d370978-d9f9-48db-b853-69a6047aadb6 */
	private java.lang.String title;
	/** @pdOid 24e83570-20e4-4093-9e6f-212c7d0d4992 */
	private java.lang.String code;

	/** @pdRoleInfo migr=no name=Table assc=referencaKaSourceTabeli mult=1..1 */
	private Table sourceTable;
	/**
	 * @pdRoleInfo migr=no name=Table assc=referencaKaDestinationTabeli mult=1..1
	 */
	private Table destinationTable;
	/**
	 * @pdRoleInfo migr=no name=Attribute assc=kolekcijaSourceKljuceva
	 *             coll=java.util.Collection impl=java.util.HashSet mult=1..*
	 *             type=Aggregation
	 */
	private java.util.Collection<Attribute> sourceKeys;
	/**
	 * @pdRoleInfo migr=no name=Attribute assc=kolekcijaDestinationKljuceva
	 *             coll=java.util.Collection impl=java.util.HashSet mult=1..*
	 *             type=Aggregation
	 */
	private java.util.Collection<Attribute> destinationKeys;

	/** @pdGenerated default getter */
	public java.util.Collection<Attribute> getSourceKeys() {
		if (sourceKeys == null)
			sourceKeys = new java.util.HashSet<Attribute>();
		return sourceKeys;
	}

	/** @pdGenerated default iterator getter */
	public java.util.Iterator getIteratorSourceKeys() {
		if (sourceKeys == null)
			sourceKeys = new java.util.HashSet<Attribute>();
		return sourceKeys.iterator();
	}

	/**
	 * @pdGenerated default setter
	 * @param newSourceKeys
	 */
	public void setSourceKeys(java.util.Collection<Attribute> newSourceKeys) {
		removeAllSourceKeys();
		for (java.util.Iterator iter = newSourceKeys.iterator(); iter.hasNext();)
			addSourceKeys((Attribute) iter.next());
	}

	/**
	 * @pdGenerated default add
	 * @param newAttribute
	 */
	public void addSourceKeys(Attribute newAttribute) {
		if (newAttribute == null)
			return;
		if (this.sourceKeys == null)
			this.sourceKeys = new java.util.HashSet<Attribute>();
		if (!this.sourceKeys.contains(newAttribute))
			this.sourceKeys.add(newAttribute);
	}

	/**
	 * @pdGenerated default remove
	 * @param oldAttribute
	 */
	public void removeSourceKeys(Attribute oldAttribute) {
		if (oldAttribute == null)
			return;
		if (this.sourceKeys != null)
			if (this.sourceKeys.contains(oldAttribute))
				this.sourceKeys.remove(oldAttribute);
	}

	/** @pdGenerated default removeAll */
	public void removeAllSourceKeys() {
		if (sourceKeys != null)
			sourceKeys.clear();
	}

	/** @pdGenerated default getter */
	public java.util.Collection<Attribute> getDestinationKeys() {
		if (destinationKeys == null)
			destinationKeys = new java.util.HashSet<Attribute>();
		return destinationKeys;
	}

	/** @pdGenerated default iterator getter */
	public java.util.Iterator getIteratorDestinationKeys() {
		if (destinationKeys == null)
			destinationKeys = new java.util.HashSet<Attribute>();
		return destinationKeys.iterator();
	}

	/**
	 * @pdGenerated default setter
	 * @param newDestinationKeys
	 */
	public void setDestinationKeys(java.util.Collection<Attribute> newDestinationKeys) {
		removeAllDestinationKeys();
		for (java.util.Iterator iter = newDestinationKeys.iterator(); iter.hasNext();)
			addDestinationKeys((Attribute) iter.next());
	}

	/**
	 * @pdGenerated default add
	 * @param newAttribute
	 */
	public void addDestinationKeys(Attribute newAttribute) {
		if (newAttribute == null)
			return;
		if (this.destinationKeys == null)
			this.destinationKeys = new java.util.HashSet<Attribute>();
		if (!this.destinationKeys.contains(newAttribute))
			this.destinationKeys.add(newAttribute);
	}

	/**
	 * @pdGenerated default remove
	 * @param oldAttribute
	 */
	public void removeDestinationKeys(Attribute oldAttribute) {
		if (oldAttribute == null)
			return;
		if (this.destinationKeys != null)
			if (this.destinationKeys.contains(oldAttribute))
				this.destinationKeys.remove(oldAttribute);
	}

	/** @pdGenerated default removeAll */
	public void removeAllDestinationKeys() {
		if (destinationKeys != null)
			destinationKeys.clear();
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

	public Table getSourceTable() {
		return sourceTable;
	}

	public void setSourceTable(Table sourceTable) {
		this.sourceTable = sourceTable;
	}

	public Table getDestinationTable() {
		return destinationTable;
	}

	public void setDestinationTable(Table destinationTable) {
		this.destinationTable = destinationTable;
	}

}