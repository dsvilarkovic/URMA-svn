package model.resourceFactory;

import controler.handlers.IHandler;
import controler.parsers.IParser;
import controler.validators.IValidator;

/**
 * 
 * @author jelena
 *
 */

public interface IResourceFactory {
	public IValidator createValidator();

	public IParser createParser();

	public IHandler createHandler();
}
