package controler.validators;

/**
 * Interfejs koji sadrzi operaciju validate
 * da ga naslede zasebni validatori
 * @author filip
 */
public interface IValidator {
	/**
	 * Sluzi da validira odgovarajucu semu.
	 * @author Dusan
	 * @return true ako je validacija uspesna, i false ako nije uspesna
	 */
	public boolean validate();
}
