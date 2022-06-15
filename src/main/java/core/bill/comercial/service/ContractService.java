package core.bill.comercial.service;

import java.util.List;

import core.bill.comercial.model.ContractDTO;
import core.bill.comercial.model.ContractDashBoardModel;
import core.bill.comercial.model.ContractSearch;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.service.GenericService;

public interface ContractService extends GenericService<ContractDTO, ContractSearch>{

	public Long getMaxContractId() throws SegesaServiceException;
	public List<ContractDTO> getCustomerContract(Long customerId) throws SegesaServiceException;
	public List<ContractDashBoardModel> getNumClientsByLoc(String localId) throws SegesaServiceException;
	public Long getCountOfContract(ContractSearch creteria) throws SegesaServiceException;
}
