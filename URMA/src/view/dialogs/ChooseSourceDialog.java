package view.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

/**
 * Dijalog koji ce sluziti za odabir tipa izvora sa kojeg ce se preuzimati podaci
 * @author Dusan
 *
 */
public class ChooseSourceDialog extends JDialog {
	private JLabel sourceTypeLabel = new JLabel("Source type");
	private String [] sourceTypes = {"JSON", "DB", "XML"};
	private JComboBox<String> combobox = new JComboBox<>(sourceTypes);
	
	private JLabel chooseSourceLocationLabel = new JLabel("Source location: ");
	private JTextField chooseSourceLocatonTextField = new JTextField(40);
	private JButton chooseFileButton = new JButton("...");
	
	private JLabel user = new JLabel("User");
	private JTextField userField = new JTextField(40);
	private JLabel pass = new JLabel("Pass");
	private JPasswordField passwordField = new JPasswordField(40);

	private JLabel port = new JLabel("Port");
	private JTextField portField = new JTextField(4);
	
	/**
	 * Dijalog koji ce sluziti za odabir tipa izvora sa kojeg ce se preuzimati podaci
	 * @author Dusan
	 *
	 */
	public ChooseSourceDialog(){
		setLayout(new BorderLayout());
		
		JPanel gridBagPanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();  
        GridBagConstraints gbc = new GridBagConstraints();  
        
        gridBagPanel.setLayout(layout);
        
        
        //setLayout(layout);
		setTitle("Choose your source");
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/8, (Toolkit.getDefaultToolkit().getScreenSize().height)/4);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		//this.add(sourceTypeLabel, gbc);
		gridBagPanel.add(sourceTypeLabel, gbc); 
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		//this.add(combobox, gbc);
		gridBagPanel.add(combobox, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		//this.add(chooseSourceLocationLabel, gbc);
		gridBagPanel.add(chooseSourceLocationLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		//this.add(chooseSourceLocatonTextField, gbc);
		gridBagPanel.add(chooseSourceLocatonTextField, gbc);
		
		chooseFileButton.setMaximumSize(chooseSourceLocatonTextField.getMaximumSize());
		gbc.gridx = 2;
		gbc.gridy = 1;
		//this.add(chooseFileButton, gbc);	
		gridBagPanel.add(chooseFileButton, gbc);
		
		//DB specific obelezja forme
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		//this.add(user, gbc);
		gridBagPanel.add(user, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		//this.add(userField, gbc);
		gridBagPanel.add(userField, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 3;
		//this.add(pass, gbc);
		gridBagPanel.add(pass, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		//this.add(passwordField, gbc);
		gridBagPanel.add(passwordField, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 4;
		//this.add(port, gbc);
		gridBagPanel.add(port, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		//this.add(portField, gbc);
		gridBagPanel.add(portField, gbc);
		
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//		JButton newButton = new JButton("Confirm");
//		buttonPanel.add(newButton);
//		gbc.gridx = 1;
//		gbc.gridy = 5;
//		//this.add(buttonPanel, gbc);
//		gridBagPanel.add(buttonPanel, gbc);
		
//		gbc.gridx = 1;
//		gbc.gridy = 4;
//		this.add(portField, gbc);
		
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton newButton = new JButton("Confirm");
		buttonPanel.add(newButton);
		
		add(gridBagPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		combobox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				setComponentVisibility();
			}
		});
		
		chooseFileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 JFileChooser fileChooser = new JFileChooser();
				 
				
			 
			     // For File
			     fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			     
			     fileChooser.addChoosableFileFilter(new FileFilter() {
			    	 
			    	    public String getDescription() {
			    	        return "JSON";
			    	    }
			    	 

						@Override
						public boolean accept(File f) {
							if (f.isDirectory()) {
			    	            return true;
			    	        } else {
			    	            return f.getName().toLowerCase().endsWith(".json");
			    	        }
						}
			    	});
			     
			     
			 
			     fileChooser.setAcceptAllFileFilterUsed(false);
			 
			     int rVal = fileChooser.showOpenDialog(null);
			     if (rVal == JFileChooser.APPROVE_OPTION) {
			        chooseSourceLocatonTextField.setText(fileChooser.getSelectedFile().toString());
				
			     }
			}
		});
		
		
		setComponentVisibility();
		setSize(600,300);
		setVisible(true);
		
			
	}
	
	/**
	 * Za podesavanje vidljivosti
	 */
	private void setComponentVisibility() {
		boolean setVisibility = combobox.getSelectedItem() == "DB";
		
		chooseFileButton.setVisible(!setVisibility);
		user.setVisible(setVisibility);
		userField.setVisible(setVisibility);
		pass.setVisible(setVisibility);
		passwordField.setVisible(setVisibility);
		port.setVisible(setVisibility);
		portField.setVisible(setVisibility);
	}
}
