package view.table;

import java.awt.Dimension;

import javax.swing.JFrame;

public class TableMainProba {

	public TableMainProba() {
		
		
	}

	public static void main(String[] args) {
		JFrame jframe = new JFrame();
		TablePanel tpp = new ParentTablePanel("proba");
		
		jframe.setSize(new Dimension(400, 400));
		
		
		jframe.add(tpp);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
		jframe.setVisible(true);
	}

}
