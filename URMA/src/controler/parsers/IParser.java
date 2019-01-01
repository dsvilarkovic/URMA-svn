package controler.parsers;

import model.InformationResource;

/**
 * Interfejs koji sadrzi operaciju parse
 * da ga naslede zasebni parseri
 * @author filip
 */
public interface IParser {
	public InformationResource parse();
}
