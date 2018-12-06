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
		
		for (Table value : tabela.getChildTables().values()) {
			list.add(new AdapterTable(value));
		}
		
		return list.size()==0?null:list;		
	}
}
