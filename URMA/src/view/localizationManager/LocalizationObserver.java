package view.localizationManager;

/**
 * Observer po kojem se menja jezik u komponentama. <br>
 * Obavezan za svaku: 
 * 
    Nazive tabela, kolona, paketa i child tabela.
    Statički deo korisničkog interfejsa: meni, toolbar, status bar, naslovna linija prozora...
    Brojčane i datumske vrednosti (format u skladu sa odabranim lokalom).

 * @author Dusan
 *
 */
public interface LocalizationObserver {
	
	/**
	 * Nametnuta metoda po kojoj se menja jezik na jezicki zavisnim delovima graficke komponente.
	 * language - jezik koji se bira iz combobox-a u <code>MainAppStatusBar</code>
	 */
	public void updateLanguage();
}
