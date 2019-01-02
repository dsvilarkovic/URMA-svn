package view.dialogs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import app.App;

public class DBParserConnectionDialog extends JDialog {
	private JLabel labIP;
	private JLabel labUser;
	private JLabel labPassword;
	private JLabel labPort;
	private JTextField txtIP;
	private JTextField txtUser;
	private JTextField txtPassword;
	private JTextField txtPort;
	private JButton butOK;
	private JButton butCancel;
	
	private boolean dataValid = false;
	
	
	public DBParserConnectionDialog() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int width = (int) screenSize.getWidth() / 4;
		int height = (int) screenSize.getHeight() / 4;
		setSize(width, height);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setResizable(true);
		setModal(true);
		ResourceBundle rb = App.INSTANCE.getResourceBundle();
		setTitle(rb.getString("database.connection"));
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.0;
		
		labIP = new JLabel("IP:");
		add(labIP, gbc);
		
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		gbc.weightx = 0.5;
		
		txtIP = new JTextField();
		add(txtIP, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.0;
		
		labPort = new JLabel("Port:");
		add(labPort, gbc);
		
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		gbc.weightx = 0.5;
		
		txtPort = new JTextField();
		add(txtPort, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.0;
		
		labUser = new JLabel(rb.getString("database.user"));
		add(labUser, gbc);
		
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		gbc.weightx = 0.5;
		
		txtUser = new JTextField();
		add(txtUser, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0.0;
		
		labPassword = new JLabel(rb.getString("database.password"));
		add(labPassword, gbc);
		
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		gbc.weightx = 0.5;
		
		txtPassword = new JTextField();
		add(txtPassword, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		
		butCancel = new JButton(rb.getString("button.Cancel"));
		butCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dataValid = false;
				setVisible(false);				
			}
		});
		add(butCancel, gbc);
		
		gbc.gridx = 3;
		
		butOK = new JButton(rb.getString("button.OK"));
		butOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dataValid = dataValidation();
				if (dataValid) {
					setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null, rb.getString("message.completeform"), rb.getString("database.connection"), JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		add(butOK, gbc);
		
		// prefilled podaci
		prefill();
		
		validate();		
	}
	
	private boolean dataValidation() {
		if (txtIP.getText().equals("") || txtUser.getText().equals("") || txtPassword.getText().equals("")) {
			return false;
		}
		return true;
	}
	
	public String getIP() {
		return this.txtIP.getText();
	}
	
	public String getPort() {
		return this.txtPort.getText();
	}
	
	public String getUser() {
		return this.txtUser.getText();
	}
	
	public String getPassword() {
		return this.txtPassword.getText();
	}
	
	public boolean isDataValid() {
		return this.dataValid;
	}
	
	private void prefill() {
		this.txtIP.setText("147.91.175.155");
		this.txtUser.setText("psw-2018-tim7-1");
		this.txtPassword.setText("tim7-19940718");
	}
}
