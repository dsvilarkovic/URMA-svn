package view.mainFrame;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import app.App;
import view.localizationManager.LocalizationObserver;

/**
 * Status bar u kojem ce se menjati jezik i ispisivati sta je odabrano od hendlera, parsera i validatora 
 * @author Dusan
 *
 */
@SuppressWarnings("serial")
public class MainAppStatusBar extends JPanel implements LocalizationObserver{

	private String[] languageString = {"Srpski - RS", "English - UK"};
	private JComboBox<String> languageComboBox = new JComboBox<>(languageString);
	
	private JLabel handlerLabel = new JLabel("Handler: ");
	private JLabel handlerLabelType = new JLabel("JSON");
	private JLabel parserLabel = new JLabel("Parser: ");
	private JLabel parserLabelType = new JLabel("JSON");
	
	private JLabel validatorLabel = new JLabel("Validator: ");
	private JLabel validatorLabelType = new JLabel("JSON");
	/**
	 * Status bar u kojem ce se menjati jezik i ispisivati sta je odabrano od hendlera, parsera i validatora 
	 * @author Dusan
	 *
	 */
	public MainAppStatusBar() {
		setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY), new EmptyBorder(1, 1, 1, 1)));
		//setLayout(new FlowLayout(FlowLayout.LEFT));
		setLayout(new BoxLayout(this,  BoxLayout.LINE_AXIS));
		
		App.INSTANCE.getLocalizationManager().addLocalizationObserver(this);
		
		languageComboBox.addItemListener(new ItemListener() {
				
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {					
					String language = (String) languageComboBox.getSelectedItem();
					App.INSTANCE.getLocalizationManager().updateLanguage(language);							
				}
			}
		});
		
		setLabels();
		add(languageComboBox);
		add(new JSeparator(SwingConstants.VERTICAL));
		add(handlerLabel);
		add(handlerLabelType);
		add(new JSeparator(SwingConstants.VERTICAL));
		add(parserLabel);
		add(parserLabelType);
		add(new JSeparator(SwingConstants.VERTICAL));
		add(validatorLabel);
		add(validatorLabelType);
	}
	
	/**
	 * Sluzi za podesavanje vrednosti labela po jeziku.
	 */
	private void setLabels() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		handlerLabel.setText(resourceBundle.getString("statusbar.handler"));
		parserLabel.setText(resourceBundle.getString("statusbar.parser"));
		validatorLabel.setText(resourceBundle.getString("statusbar.validator"));
	}


	/**
	 * @return the handlerLabelType
	 */
	public String getHandlerLabelType() {
		return handlerLabelType.getText();
	}
	/**
	 * @param handlerLabelType the handlerLabelType to set
	 */
	public void setHandlerLabelType(String handlerLabelType) {
		this.handlerLabelType.setText(handlerLabelType);
	}
	/**
	 * @return the parserLabelType
	 */
	public String getParserLabelType() {
		return parserLabelType.getText();
	}
	/**
	 * @param parserLabelType the parserLabelType to set
	 */
	public void setParserLabelType(String parserLabelType) {
		this.parserLabelType.setText(parserLabelType);
	}
	/**
	 * @return the validatorLabelType
	 */
	public String getValidatorLabelType() {
		return validatorLabelType.getText();
	}
	/**
	 * @param validatorLabelType the validatorLabelType to set
	 */
	public void setValidatorLabelType(String validatorLabelType) {
		this.validatorLabelType.setText(validatorLabelType);
	}


	@Override
	public void updateLanguage() {
		setLabels();
	}

}
