package model.resourceFactory;

import controler.handlers.IHandler;
import controler.handlers.JSONHandler;
import controler.parsers.IParser;
import controler.parsers.JSONParser;
import controler.validators.IValidator;
import controler.validators.JSONValidator;

/**
 * 
 * @author jelena
 *
 */

public class JSONFactory implements IResourceFactory {

	@Override
	public IValidator createValidator() {
		return new JSONValidator();
	}

	@Override
	public IParser createParser() {
		return new JSONParser();
	}

	@Override
	public IHandler createHandler() {
		return new JSONHandler();
	}

}
