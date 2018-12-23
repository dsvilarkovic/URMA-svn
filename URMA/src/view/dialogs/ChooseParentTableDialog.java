package view.dialogs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.Table;

public class ChooseParentTableDialog extends JDialog{

	private static final long serialVersionUID = -2861236282515127819L;
	private DefaultListModel<String> listModel;
	private JList<String> list;
	private Map<String, Table> parentTableMap;
	private JScrollPane scrollPane;
	private JButton selectButton;
	protected String retVal;
	private JButton cancelButton;

	public ChooseParentTableDialog(Map<String, Table> parentTableMap) {
		super();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int width = (int) screenSize.getWidth() / 5;
		int height = (int) screenSize.getHeight() / 3;
		setSize(width, height);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setResizable(true);
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localisationresources.localisationresources",Locale.getDefault());
		setTitle(resourceBundle.getString("table.parent.dialog.choose"));
		
		setModal(true);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		listModel = new DefaultListModel<>();
		list = new JList<>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
		this.parentTableMap = parentTableMap;
		for (String tableKey : this.parentTableMap.keySet()) {
			
			listModel.addElement(tableKey);
		}

		scrollPane = new JScrollPane(list);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.gridheight = 3;
		add(scrollPane);

		selectButton = new JButton(resourceBundle.getString("select"));
		selectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				retVal = list.getSelectedValue();
				setVisible(false);
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		add(selectButton);

		cancelButton = new JButton(resourceBundle.getString("cancel"));
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		gbc.gridx = 3;
		add(cancelButton);

		setVisible(true);
	}

	public String getSelected() {
		return retVal;
	}

}
