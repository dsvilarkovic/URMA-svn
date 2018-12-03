package model.treeAdapter;

import java.util.List;

import model.InformationResource;

public interface TreeParts {

	public String getImgPath();
	public String getName();
	public void action();
	public List<TreeParts> getContent(InformationResource infRes);
	
}
