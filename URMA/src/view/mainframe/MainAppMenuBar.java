package view.mainframe;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controler.mainframe.OpenCRUDTestPanel;
import controler.mainframe.OpenSchemaEditor;

public class MainAppMenuBar extends JMenuBar{

	public MainAppMenuBar() {
		//JMenu file = new JMenu("File");
		JMenu tools = new JMenu("Tools");
		JMenu edit = new JMenu("Edit");
		JMenu help = new JMenu("Help");
				
		//file.add(newWorkspace);
		//file.addSeparator();
		//file.add(newProject);
		//file.addSeparator();
		//file.add(newDocument);
		
		JMenuItem OpenSchemaEditorItem = new JMenuItem(new OpenSchemaEditor());
		tools.add(OpenSchemaEditorItem);
		JMenuItem CRUDWindow = new JMenuItem(new OpenCRUDTestPanel());
		tools.add(CRUDWindow);
		
		//add(file);
		add(tools);
		add(edit);
		add(help);
		
		validate();
	}

}
