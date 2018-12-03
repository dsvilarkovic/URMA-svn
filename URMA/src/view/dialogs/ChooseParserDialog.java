package view.dialogs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class ChooseParserDialog extends JDialog {
	private JList<String> list;
	private DefaultListModel<String> listModel;

	private JButton selectButton;
	private JButton cancelButton;
	private JScrollPane scrollPane;

	private String retVal;

	public ChooseParserDialog() {
		super();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int width = (int) screenSize.getWidth() / 5;
		int height = (int) screenSize.getHeight() / 3;
		setSize(width, height);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setResizable(true);
		setTitle("Schema type selection");
		setModal(true);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		listModel = new DefaultListModel<>();
		list = new JList<>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// TODO: Ovde ako zatreba napraviti da nije hardcode-ovano)
		listModel.addElement("JSON");
		listModel.addElement("XML");
		listModel.addElement("DB");

		scrollPane = new JScrollPane(list);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.gridheight = 3;
		add(scrollPane);

		selectButton = new JButton("Select");
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

		cancelButton = new JButton("Cancel");
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