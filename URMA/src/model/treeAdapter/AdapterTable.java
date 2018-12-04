/**
 * 
 */
package model.treeAdapter;

import java.util.ArrayList;
import java.util.List;

import app.App;
import model.InformationResource;
import model.Table;

/**
 * @author filip
 *
 */
public class AdapterTable implements TreeParts {

	private Table tabela;
	
	public AdapterTable(Table tbl) {
		tabela = tbl;
	}
	
	@Override
	public String getImgPath() {return "resources/spreadsheet.png";}

	@Override
	public String getName() {return tabela.getTitle();}

	@Override
	public void action() {
		App.INSTANCE.getTableMediator().showTable(tabela);
	}

	@Override
	public List<TreeParts> getContent(InformationResource infRes) {
		
		List<TreeParts> list = new ArrayList<TreeParts>();
		//List<Table> tabele = infRes.getChildTables(tabela);
		//System.out.println("- " + tabele + "   " + tabele.size());
		//for (int i = 0; i < tabela.getChildTables().size(); i++) {
			//list.add(new AdapterTable(tabela.getChildTables().get(i)));			
		//}
		
		for (Table value : tabela.getChildTables().values()) {
			list.add(new AdapterTable(value));
		}
		
		if(list.size() == 0)
			return null;
			
		return list;		
	}


	
	
}
