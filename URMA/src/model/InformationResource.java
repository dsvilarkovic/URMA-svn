/***********************************************************************
 * Module:  InformationResource.java
 * Author:  Boris
 * Purpose: Defines the Class InformationResource
 ***********************************************************************/

package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** @pdOid 66940cd2-31c6-40c8-ab87-73f875f9b8d3 */
public class InformationResource {
	   /** @pdRoleInfo migr=no name=Relation assc=kolekcijaVeza coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
	   private Map<String,Relation> relations;
	   /** @pdRoleInfo migr=no name=Package assc=kolekcijaPaketa coll=java.util.Collection impl=java.util.HashSet mult=1..* type=Composition */
	   private Map<String,Package> packages;
	   /** @pdRoleInfo migr=no name=Table assc=kolekcijaSvihTabela coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
	   private Map<String,Table> allTables;
	   
	   public InformationResource() {}
	   
	   
	   public Map<String,Relation> getRelations() {
	      if (relations == null)
	         relations = new HashMap<String,Relation>();
	      return relations;
	   }
	   
	   public void addRelations(Relation newRelation) {
	      if (newRelation == null)
	         return;
	      if (this.relations == null)
	         this.relations = new HashMap<String,Relation>();
	      if (!this.relations.containsKey(newRelation.getCode()))
	         this.relations.put(newRelation.getCode(), newRelation);
	   }
	   
	   public void removeRelations(Relation oldRelation) {
	      if (oldRelation == null)
	         return;
	      if (this.relations != null)
	         if (this.relations.containsKey(oldRelation.getCode()))
	            this.relations.remove(oldRelation.getCode());
	   }
	   
	   public void removeAllRelations() {
	      if (relations != null)
	         relations.clear();
	   }
	   
	   public Map<String,Package> getPackages() {
	      if (packages == null)
	         packages = new HashMap<String,Package>();
	      return packages;
	   }
	   
	   public void addPackages(Package newPackage) {
	      if (newPackage == null)
	         return;
	      if (this.packages == null)
	         this.packages = new HashMap<String,Package>();
	      if (!this.packages.containsKey(newPackage.getCode()))
	         this.packages.put(newPackage.getCode(), newPackage);
	   }
	   
	   public void removePackages(Package oldPackage) {
	      if (oldPackage == null)
	         return;
	      if (this.packages != null)
	         if (this.packages.containsKey(oldPackage.getCode()))
	            this.packages.remove(oldPackage.getCode());
	   }
	   
	   public void removeAllPackages() {
	      if (packages != null)
	         packages.clear();
	   }
	   
	   public Map<String,Table> getAllTables() {
	      if (allTables == null)
	         allTables = new HashMap<String,Table>();
	      return allTables;
	   }
	   
	   public void addAllTables(Table newTable) {
	      if (newTable == null)
	         return;
	      if (this.allTables == null)
	         this.allTables = new HashMap<String,Table>();
	      if (!this.allTables.containsKey(newTable.getCode()))
	         this.allTables.put(newTable.getCode(), newTable);
	   }
	   
	   public void removeAllTables(Table oldTable) {
	      if (oldTable == null)
	         return;
	      if (this.allTables != null)
	         if (this.allTables.containsKey(oldTable.getCode()))
	            this.allTables.remove(oldTable.getCode());
	   }
	   
	   public void removeAllAllTables() {
	      if (allTables != null)
	         allTables.clear();
	   }

   
   /**
    * Vraca tabelu svih identifikaciono zavisnih tabela od <code> table </code> tabele
    * @param table
    * @return
    */
   public List<Table> getChildTables(Table table) {
	   ArrayList<Table> tableList = new ArrayList<>();
	   
	   for (Relation relation : relations.values()) {
		   Table potentialChildTable = relation.getDestinationTable();
//		   System.out.println(relations);
//		   System.out.println(table);
//		   System.out.println(potentialChildTable);
		   if(potentialChildTable.isIdDependentFrom(table)) {
			   tableList.add(potentialChildTable);
		   }
	   }
	   
	   
	   return tableList;
   }
   
   /**
    * Vraca tabelu svih tabela od kojih je tabela <code>  table </code> identifikaciono zavisna
    * @param table
    * @return
    */
   public List<Table> getParentTables(Table table) {
	   ArrayList<Table> parentList = new ArrayList<>();
	   
	   
	   for (Relation relation : relations.values()) {
		   Table potentialParentTable = relation.getSourceTable();
		   if(table.isIdDependentFrom(potentialParentTable)) {
			   parentList.add(potentialParentTable);
		   }
	   }
	   
	   
	   return parentList;
   }
}