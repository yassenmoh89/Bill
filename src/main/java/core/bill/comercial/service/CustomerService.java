package core.bill.comercial.service;

import java.util.List;

import core.bill.comercial.model.CustomerDTO;
import core.bill.comercial.model.CustomerSearch;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.service.GenericService;

public interface CustomerService extends GenericService<CustomerDTO, CustomerSearch>{

	public Long getMax(String fieldName) throws SegesaServiceException ;
	public List<CustomerDTO> getDataList(CustomerSearch critiria) throws SegesaServiceException;
}
