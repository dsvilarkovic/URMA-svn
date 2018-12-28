/**
 * 
 */
package model.treeAdapter;

import java.util.ArrayList;
import java.util.List;

import app.App;
import model.InformationResource;
import model.Table;
import model.TitleLanguagePack;

/**
 * Klasa adapter za tabelu. Adaptira tabelu za stablo i za dalju upotrebu u programu.
 * @author filip
 */
public class AdapterTable implements TreeParts {

	private Table tabela;
	
	/**
	 * Konstruktor za kreiranje adaptera tabele
	 * @author filip
	 * @param tbl - tabela koja treba da se adaptira
	 */
	public AdapterTable(Table tbl) {
		tabela = tbl;
	}
	
	@Override
	public String getImgPath() {return "resources/spreadsheet.png";}

	@Override
	public String getName() {
		String localizedTitle = tabela.getTitle();
		TitleLanguagePack titleLanguagePack = App.INSTANCE.getTitleLanguagePack();
		if(titleLanguagePack.isLanguagePackLoaded()) {
			localizedTitle = titleLanguagePack.getTableTitle(tabela.getCode());
		}
		//return tabela.getTitle();
		return localizedTitle;
	}

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
