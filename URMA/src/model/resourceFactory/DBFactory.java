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
		DBHandler dbHandler = new DBHandler();
		dbHandler.setUpDBHandlerParameters(dbHandlerIp, dbHandlerUser, dbHandlerPass);
		return new DBHandler();
	}
	
	private String dbHandlerUser = "147.91.175.155";
	private String dbHandlerPass = "psw-2018-tim7-1";
	private String dbHandlerIp = "tim7-19940718";
	
	/**
	 * Sluzi za podesavanje potrebnih parametara za handler za dalji rad
	 * @param ip - adresa na kojoj se pogadja baza
	 * @param user - username za tu bazu
	 * @param pass - password za tu bazu
	 * @author Dusan 
	 * @return true ako vraca dobru vrednost, false ako ne.
	 */
	public void setUpDBHandlerParameters(String ip, String user, String pass) {
		this.dbHandlerIp = ip;
		this.dbHandlerUser = user;
		this.dbHandlerPass = pass;
	}
}
