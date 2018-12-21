package model.resourceFactory;

import controler.handlers.IHandler;
import controler.handlers.XMLHandler;
import controler.parsers.IParser;
import controler.parsers.XMLParser;
import controler.validators.IValidator;
import controler.validators.XMLValidator;

/**
 * Klasa sluzi za kreiranje validatora, parsera i handlera za xml
 * @author Jelena
 *
 */

public class XMLFactory implements IResourceFactory {

	/**
		Metoda koja kreira validator za xml	
		@author - Jelena
		@return - xml validator
	**/
	@Override
	public IValidator createValidator() {
		return new XMLValidator();
	}

	/**
		Metoda koja kreira parser za xml	
		@author - Jelena
		@return - xml parser
	**/
	@Override
	public IParser createParser() {
		return new XMLParser();
	}

	/**
		Metoda koja kreira handler za xml	
		@author - Jelena
		@return - xml handler
	**/
	@Override
	public IHandler createHandler() {
		return new XMLHandler();
	}
}
