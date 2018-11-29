/***********************************************************************
 * Module:  InformationResource.java
 * Author:  Boris
 * Purpose: Defines the Class InformationResource
 ***********************************************************************/

package model;

/**
 * 
 * @author Boris
 *
 */

/** @pdOid 66940cd2-31c6-40c8-ab87-73f875f9b8d3 */
public class InformationResource {
   /** @pdRoleInfo migr=no name=Table assc=kolekcijaTabela coll=java.util.Collection impl=java.util.HashSet mult=1..* type=Composition */
   private java.util.Collection<Table> tables;
   /** @pdRoleInfo migr=no name=Relation assc=kolekcijaVeza coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   private java.util.Collection<Relation> relations;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Table> getTables() {
      if (tables == null)
         tables = new java.util.HashSet<Table>();
      return tables;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorTables() {
      if (tables == null)
         tables = new java.util.HashSet<Table>();
      return tables.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newTables */
   public void setTables(java.util.Collection<Table> newTables) {
      removeAllTables();
      for (java.util.Iterator iter = newTables.iterator(); iter.hasNext();)
         addTables((Table)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newTable */
   public void addTables(Table newTable) {
      if (newTable == null)
         return;
      if (this.tables == null)
         this.tables = new java.util.HashSet<Table>();
      if (!this.tables.contains(newTable))
         this.tables.add(newTable);
   }
   
   /** @pdGenerated default remove
     * @param oldTable */
   public void removeTables(Table oldTable) {
      if (oldTable == null)
         return;
      if (this.tables != null)
         if (this.tables.contains(oldTable))
            this.tables.remove(oldTable);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllTables() {
      if (tables != null)
         tables.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Relation> getRelations() {
      if (relations == null)
         relations = new java.util.HashSet<Relation>();
      return relations;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorRelations() {
      if (relations == null)
         relations = new java.util.HashSet<Relation>();
      return relations.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newRelations */
   public void setRelations(java.util.Collection<Relation> newRelations) {
      removeAllRelations();
      for (java.util.Iterator iter = newRelations.iterator(); iter.hasNext();)
         addRelations((Relation)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newRelation */
   public void addRelations(Relation newRelation) {
      if (newRelation == null)
         return;
      if (this.relations == null)
         this.relations = new java.util.HashSet<Relation>();
      if (!this.relations.contains(newRelation))
         this.relations.add(newRelation);
   }
   
   /** @pdGenerated default remove
     * @param oldRelation */
   public void removeRelations(Relation oldRelation) {
      if (oldRelation == null)
         return;
      if (this.relations != null)
         if (this.relations.contains(oldRelation))
            this.relations.remove(oldRelation);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllRelations() {
      if (relations != null)
         relations.clear();
   }

}