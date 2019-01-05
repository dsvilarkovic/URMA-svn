package controler.parsers;

import model.InformationResource;

/**
 * Interfejs koji nasleđuju svi specijalizovani parseri.
 * @author Boris
 */
public interface IParser {
	/**
	 * Osnovna metoda koju je potrevno implementirati u okviru parsera.
	 * Obavlja celu operaciju parsiranja jednog resursa iz jednog izvora.
	 * @return Isparsirani informacioni resurs.
	 */
	public InformationResource parse();
}
