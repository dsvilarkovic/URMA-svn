package model.treeAdapter;

import java.util.List;

import model.InformationResource;

/**
 * Interfejs koji moraju da naslede svi cvorovi u stablu.
 * Definise metode potrebne za iscrtavanje i dobijanje podataka o deci cvora
 * @author filip
 */
public interface TreeParts {
	
	/**
	 * Metoda koja vraca putanju do slike koja ce se prikazivati kao cvor na stablu
	 * @author filip
	 * @param none
	 * @return {@link String} - putanja do slike cvora na
	 */
	public String getImgPath();
	
	/**
	 * Metoda koja vraca ime cvora koje treba da se prikaze na stablu
	 * @author filip
	 * @param none
	 * @return {@link String} - ime cvora
	 */
	public String getName();
	
	/**
	 * Metoda koja definise akciju klika na cvor stabla
	 * @author filip
	 * @param none
	 * @return {@link Void} 
	 */
	public void action();
	
	/**
	 * Metoda koja vraca decu cvora u stablu
	 * @author filip
	 * @param infRes - informacioni resurs
	 * @return {@link List}&lt;{@link TreeParts}> - listu dece koji su takodje cvorovi stabla
	 */
	public List<TreeParts> getContent(InformationResource infRes);
}
