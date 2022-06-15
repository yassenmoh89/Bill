package core.bill.comercial.dao;

import java.util.List;

import org.hibernate.criterion.ProjectionList;

import core.bill.comercial.model.ContractDTO;
import core.bill.comercial.model.ContractDashBoardModel;
import core.bill.comercial.model.ContractSearch;
import core.bill.common.dao.GenericDAO;

public interface ContractDAO extends GenericDAO<ContractDTO, ContractSearch>{
	public Long getMaxRow(String fieldName);
	public List<ContractDTO> getList(ContractSearch certieria,ProjectionList projectionList , int start, int pageSize, String orderBy, Boolean desc);
	public List<ContractDTO> getCustomerContract(ContractSearch certieria,ProjectionList projectionList , int start, int pageSize, String orderBy, Boolean desc);
	public List<ContractDashBoardModel> getNumOfClientsByLocation(String loaclId);
	public Long getCountOfContracts(ContractSearch certieria);
}
