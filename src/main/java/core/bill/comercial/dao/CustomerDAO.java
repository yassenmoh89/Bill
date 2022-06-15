package core.bill.comercial.dao;

import java.util.List;

import org.hibernate.criterion.ProjectionList;

import core.bill.comercial.model.CustomerDTO;
import core.bill.comercial.model.CustomerSearch;
import core.bill.common.dao.GenericDAO;

public interface CustomerDAO extends GenericDAO<CustomerDTO, CustomerSearch>{

	public Long getMaxRow(String fieldName);
	public List<CustomerDTO> getList(CustomerSearch certieria,ProjectionList projectionList , int start, int pageSize, String orderBy, Boolean desc);
}
