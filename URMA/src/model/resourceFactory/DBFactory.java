package model.resourceFactory;

import controler.handlers.DBHandler;
import controler.handlers.IHandler;
import controler.parsers.DBParser;
import controler.parsers.IParser;
import controler.validators.DBValidator;
import controler.validators.IValidator;

/**
 * Klasa sluzi za kreiranje validatora, parsera i handlera za bazu podataka
 * @author Jelena
 *
 */

public class DBFactory implements IResourceFactory{

	/**
		Metoda koja kreira validator za bazu	
		@author - Jelena
		@return - validator baze
	**/
	@Override
	public IValidator createValidator() {
		return new DBValidator();
	}

	/**
		Metoda koja kreira parser za bazu podataka	
		@author - Jelena
		@return - parser za bazu podataka
	**/
	@Override
	public IParser createParser() {
		return new DBParser();
	}

	/**
		Metoda koja kreira handler za bazu podataka	
		@author - Jelena
		@return - handler za bazu podataka
	**/
	@Override
	public IHandler createHandler() {
		return new DBHandler();
	}
}
