package core.bill.study.dao;

import java.util.List;

import org.hibernate.criterion.ProjectionList;

import core.bill.common.dao.GenericDAO;
import core.bill.study.model.ItemSearch;
import core.bill.study.model.ItemsDTO;

public interface ItemsDAO extends GenericDAO<ItemsDTO, ItemSearch>{
	public List<ItemsDTO> getList(ItemSearch certieria, final ProjectionList projectionList ,int start, int pageSize, String orderBy, Boolean desc);

}
