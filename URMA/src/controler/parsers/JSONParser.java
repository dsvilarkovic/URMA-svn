package controler.parsers;

import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import model.Attribute;
import model.InformationResource;
import model.Package;
import model.Relation;
import model.Table;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import app.App;
import controler.Open;

/**
 * Parser šema iz JSON Schema formata, na osnovu unapred specificirane meta-šeme.
 * @author (proud) Boris
 */
public class JSONParser implements IParser {
	
	private InformationResource informationResource;
	
	// Rekurzivna metoda za pakete
	private Package parsePackage(JSONObject packageJSON) {
		// Novi paket
		Package newPackage = new Package();
		newPackage.setCode(packageJSON.getString("code"));
		newPackage.setTitle(packageJSON.getString("title"));
		
		HashMap<String, Table> allTables = (HashMap<String, Table>) informationResource.getAllTables();
		// Sve tabele u paketu
		JSONArray packageTablesJSON = packageJSON.getJSONArray("tables");
		for (Object packageTableObjectJSON : packageTablesJSON) {
			JSONObject currTableJSON = (JSONObject) packageTableObjectJSON;
			String tableCode = currTableJSON.getString("code");
			// Da li je tabela vec obradjena u okviru drugog paketa
			if (!allTables.containsKey(tableCode)) {
				// Nije, super!
				String tableTitle = currTableJSON.getString("title");
				Table newTable = new Table(tableTitle, tableCode);
				
				// Svi atributi u tabeli
				JSONArray tableAttributesJSON = currTableJSON.getJSONArray("attributes");
				for (Object tableAttributeObject : tableAttributesJSON) {
					JSONObject attributeJSON = (JSONObject) tableAttributeObject;
					
					//Novi atribut
					Attribute newAttribute = new Attribute();
					newAttribute.setCode(attributeJSON.getString("code"));
					newAttribute.setTitle(attributeJSON.getString("title"));
					newAttribute.setIsPrimaryKey(attributeJSON.getBoolean("isPK"));
					newAttribute.setIsRequired(attributeJSON.getBoolean("isRequired"));
					newAttribute.setType(attributeJSON.getString("type"));
					if (attributeJSON.keySet().contains("maxLength")) {
						newAttribute.setMaxLength(attributeJSON.getInt("maxLength"));
					}
					if (attributeJSON.keySet().contains("precision")) {
						newAttribute.setPrecision(attributeJSON.getInt("precision"));
					}
					// Dodaj atribut u novu tabelu
					newTable.addAttributes(newAttribute);
				}
				
				allTables.put(newTable.getCode(), newTable);
				newPackage.addTables(newTable);
			}
			else {
				// Jeste, jos bolje!
				newPackage.addTables(allTables.get(tableCode));
			}
		}
		
		// Svi paketi u paketu
		JSONArray packagePackagesJSON = packageJSON.getJSONArray("packages");
		for (Object packageObjectJSON : packagePackagesJSON) {
			JSONObject childPackageJSON = (JSONObject) packageObjectJSON;
			//Jeeej rekurzija
			Package newChildPackage = parsePackage(childPackageJSON);
			newPackage.addChildPackages(newChildPackage);
		}
		
		return newPackage;		
	}

	@Override
	public InformationResource parse() {
		String path = (String) new Open().getPath("sch/json");
		return parseThis(path);
	}	
	
	/**
	 * Metoda kojom se direktno pristupa parsiranju bez grafičkog dijaloga,
	 * kada je unapred poznata putanja.
	 * @param path - Putanja šeme.
	 * @return Isparsirani informacioni resurs. Ukoliko se desi greška, vraća null.
	 */
	public InformationResource parseThis(String path) {
		try {
			JSONTokener tokener = new JSONTokener((String) new Open().openThis(path));
			JSONObject informationResourceJSON = new JSONObject(tokener);
			
			informationResource = new InformationResource();
			
			// Paketi
			JSONArray topPackagesJSON = informationResourceJSON.getJSONArray("packages");
			for (Object packageObjectJSON : topPackagesJSON) {
				JSONObject currPackageJSON = (JSONObject) packageObjectJSON;
				Package newPackage = parsePackage(currPackageJSON);
				informationResource.addPackages(newPackage);
			}
			
			HashMap<String, Table> allTables = (HashMap<String, Table>) informationResource.getAllTables();
			
			// Relacije
			JSONArray relationsJSON = informationResourceJSON.getJSONArray("relations");
			for (Object relationObjectJSON : relationsJSON) {
				JSONObject currRelationJSON = (JSONObject) relationObjectJSON;
				Relation newRelation = new Relation();
				newRelation.setCode(currRelationJSON.getString("code"));
				newRelation.setTitle(currRelationJSON.getString("title"));
				
				Table sourceTable = allTables.get(currRelationJSON.getString("source"));
				Table destinationTable = allTables.get(currRelationJSON.getString("destination"));
				ResourceBundle resourceBundle = App.INSTANCE.getResourceBundle();
				if (sourceTable == null || destinationTable == null) {
					JOptionPane.showMessageDialog(null,
							resourceBundle.getString("parser.relation.error.first") + newRelation.getCode() + resourceBundle.getString("parser.relation.error.second"),
							resourceBundle.getString("parser.error.message"),
							JOptionPane.WARNING_MESSAGE);
					return null;
				}
				newRelation.setSourceTable(sourceTable);
				newRelation.setDestinationTable(destinationTable);
				
				JSONArray sourceKeysJSON = currRelationJSON.getJSONArray("keysSource");
				for (Object sourceKeyObjectJSON : sourceKeysJSON) {
					String  keyCode = sourceKeyObjectJSON.toString();
					Attribute sourceKey = sourceTable.getAttribute(keyCode);
					if (sourceKey == null) {
						JOptionPane.showMessageDialog(null,
								resourceBundle.getString("parser.relation.error.first")  
								+ newRelation.getCode() + resourceBundle.getString("parser.error.source.attribute")
								+ keyCode + resourceBundle.getString("parser.error.notfound"),
								resourceBundle.getString("parser.error.message"),
								JOptionPane.WARNING_MESSAGE);
						return null;
					}
					if (!sourceKey.getIsPrimaryKey()) {
						JOptionPane.showMessageDialog(null,
								resourceBundle.getString("parser.relation.error.first") + newRelation.getCode() + 
								resourceBundle.getString("parser.error.source") + keyCode + resourceBundle.getString("parser.error.notprimarykey"),
								resourceBundle.getString("parser.error.message"),
								JOptionPane.WARNING_MESSAGE);
						return null;
					}
					newRelation.addSourceKeys(sourceKey);
				}
				
				boolean isIdDependency = true;
				JSONArray destinationKeysJSON = currRelationJSON.getJSONArray("keysDestination");
				for (Object destinationKeyObjectJSON : destinationKeysJSON) {
					String keyCode = destinationKeyObjectJSON.toString();
					Attribute destinationKey = destinationTable.getAttribute(keyCode);
					if (destinationKey == null) {
						JOptionPane.showMessageDialog(null,
								resourceBundle.getString("parser.relation.error.first") + newRelation.getCode() + resourceBundle.getString("parser.error.destination.attribute") + keyCode + resourceBundle.getString("parser.error.notfound"),
								resourceBundle.getString("parser.error.message"),
								JOptionPane.WARNING_MESSAGE);
						return null;
					}
					
					// Da li je referencijalni integritet?
					isIdDependency = isIdDependency && destinationKey.getIsPrimaryKey();
					
					newRelation.addDestinationKeys(destinationKey);
				}
				
				if (isIdDependency) {
					sourceTable.addChildTables(destinationTable);
					destinationTable.addParentTables(sourceTable);
				}
				
				informationResource.addRelations(newRelation);
				
				destinationTable.addRelationWhereDestination(newRelation);
			}
			
			return informationResource;
		} catch (Exception e) {
			return null;
		}
		
	}

}
