package model.resourceFactory;

import controler.handlers.IHandler;
import controler.handlers.JSONHandler;
import controler.parsers.IParser;
import controler.parsers.JSONParser;
import controler.validators.IValidator;
import controler.validators.JSONValidator;

/**
 * Klasa sluzi za kreiranje validatora, parsera i handlera za json
 * @author Jelena
 *
 */

public class JSONFactory implements IResourceFactory {

	/**
		Metoda koja kreira validator za json	
		@author - Jelena
		@return - json validator
	**/
	@Override
	public IValidator createValidator() {
		return new JSONValidator();
	}

	/**
		Metoda koja kreira parser za json	
		@author - Jelena
		@return - json parser
	**/
	@Override
	public IParser createParser() {
		return new JSONParser();
	}

	/**
		Metoda koja kreira handler za json	
		@author - Jelena
		@return - json handler
	**/
	@Override
	public IHandler createHandler() {
		return new JSONHandler();
	}

}
