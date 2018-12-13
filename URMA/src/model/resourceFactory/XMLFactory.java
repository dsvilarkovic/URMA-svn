package model.resourceFactory;

import controler.handlers.IHandler;
import controler.handlers.XMLHandler;
import controler.parsers.IParser;
import controler.parsers.XMLParser;
import controler.validators.IValidator;
import controler.validators.XMLValidator;

/**
 * 
 * @author jelena
 *
 */

public class XMLFactory implements IResourceFactory {

	@Override
	public IValidator createValidator() {
		return new XMLValidator();
	}

	@Override
	public IParser createParser() {
		return new XMLParser();
	}

	@Override
	public IHandler createHandler() {
		return new XMLHandler();
	}
}
