package model.resourceFactory;

import controler.handlers.IHandler;
import controler.parsers.IParser;
import controler.validators.IValidator;

/**
 * Interfejs sluzi za kreiranje validatora, parsera i handlera. Implementiraju ga konkretne fabrike
 * @author Jelena
 *
 */

public interface IResourceFactory {
	
	/**
		Metoda koja kreira validator, implemeniraju je metode pojedinačnih tipova fabrika	
		@author - Jelena
		@return - validator
	**/
	public IValidator createValidator();

	/**
		Metoda koja kreira parser, implemeniraju je metode pojedinačnih tipova fabrika	
		@author - Jelena
		@return - parser
	**/
	public IParser createParser();

	/**
		Metoda koja kreira handler, implemeniraju je metode pojedinačnih tipova fabrika	
		@author - Jelena
		@return - handler
	**/
	public IHandler createHandler();
}
