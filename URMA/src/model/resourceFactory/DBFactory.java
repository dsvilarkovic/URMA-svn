package model.resourceFactory;

import controler.handlers.DBHandler;
import controler.handlers.IHandler;
import controler.parsers.DBParser;
import controler.parsers.IParser;
import controler.validators.DBValidator;
import controler.validators.IValidator;

public class DBFactory implements IResourceFactory{

	@Override
	public IValidator createValidator() {
		return new DBValidator();
	}

	@Override
	public IParser createParser() {
		return new DBParser();
	}

	@Override
	public IHandler createHandler() {
		return new DBHandler();
	}
}
