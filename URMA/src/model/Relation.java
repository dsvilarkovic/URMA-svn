/***********************************************************************
 * Module:  Relation.java
 * Author:  Boris
 * Purpose: Defines the Class Relation
 ***********************************************************************/

package model;

import java.util.HashSet;

/** @pdOid 21a1a755-8af1-411b-9361-e211a1d1a8d1 */
/**
 * Model relacije u informacionom resursu.
 * Sadrži sve informacije o vezi između dve tabele.
 * @author Boris
 *
 */
public class Relation {
	/** @pdOid 0d370978-d9f9-48db-b853-69a6047aadb6 */
	private String title;
	/** @pdOid 24e83570-20e4-4093-9e6f-212c7d0d4992 */
	private String code;

	private Table sourceTable;
	private Table destinationTable;
	private HashSet<Attribute> sourceKeys;
	private HashSet<Attribute> destinationKeys;
	
	public Relation() {}
	
	public Relation(String title, String code) {
		super();
		this.title = title;
		this.code = code;
	}

	public HashSet<Attribute> getSourceKeys() {
		if (sourceKeys == null)
			sourceKeys = new HashSet<Attribute>();
		return sourceKeys;
	}

	public void addSourceKeys(Attribute newAttribute) {
		if (newAttribute == null)
			return;
		if (this.sourceKeys == null)
			this.sourceKeys = new HashSet<Attribute>();
		if (!this.sourceKeys.contains(newAttribute))
			this.sourceKeys.add(newAttribute);
	}

	public void removeSourceKeys(Attribute oldAttribute) {
		if (oldAttribute == null)
			return;
		if (this.sourceKeys != null)
			if (this.sourceKeys.contains(oldAttribute))
				this.sourceKeys.remove(oldAttribute);
	}

	public void removeAllSourceKeys() {
		if (sourceKeys != null)
			sourceKeys.clear();
	}

	public HashSet<Attribute> getDestinationKeys() {
		if (destinationKeys == null)
			destinationKeys = new HashSet<Attribute>();
		return destinationKeys;
	}

	public void addDestinationKeys(Attribute newAttribute) {
		if (newAttribute == null)
			return;
		if (this.destinationKeys == null)
			this.destinationKeys = new HashSet<Attribute>();
		if (!this.destinationKeys.contains(newAttribute))
			this.destinationKeys.add(newAttribute);
	}

	public void removeDestinationKeys(Attribute oldAttribute) {
		if (oldAttribute == null)
			return;
		if (this.destinationKeys != null)
			if (this.destinationKeys.contains(oldAttribute))
				this.destinationKeys.remove(oldAttribute);
	}

	public void removeAllDestinationKeys() {
		if (destinationKeys != null)
			destinationKeys.clear();
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
	
	/**
	 * Metoda koja vrši proveru da li je relacija ista po vrednostima kao i parametar.
	 * @param r = Relacija s kojim se poredi.
	 * @return Da li su relacije iste ili ne.
	 */
	public boolean equals(Relation r) {
		if (r == null) {
			return false;
		}
		
		if (!this.title.equals(r.title)) {
			return false;
		}
		
		if (!this.code.equals(r.code)) {
			return false;
		}
		
		if (!this.sourceTable.equals(r.sourceTable)) {
			return false;
		}
		
		if (!this.destinationTable.equals(r.destinationTable)) {
			return false;
		}
		
		if (this.getSourceKeys().size() != r.getSourceKeys().size()) {
			return false;
		}
		
		if (this.getDestinationKeys().size() != r.getDestinationKeys().size()) {
			return false;
		}
		
		Attribute[] aarray1 = (Attribute[]) this.getSourceKeys().toArray(new Attribute[this.getSourceKeys().size()]);
		Attribute[] aarray2 = (Attribute[]) r.getSourceKeys().toArray(new Attribute[r.getSourceKeys().size()]);
		for (int i = 0; i < aarray1.length; i++) {
			if (!aarray1[i].equals(aarray2[i])) {
				return false;
			}
		}
		
		aarray1 = (Attribute[]) this.getDestinationKeys().toArray(new Attribute[this.getDestinationKeys().size()]);
		aarray2 = (Attribute[]) r.getDestinationKeys().toArray(new Attribute[this.getDestinationKeys().size()]);
		for (int i = 0; i < aarray1.length; i++) {
			if (!aarray1[i].equals(aarray2[i])) {
				return false;
			}
		}
		
		return true;
	}
}
