package core.bill.comercial.service.serviceProvider;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.comercial.model.ContractDTO;
import core.bill.comercial.model.CustomerDTO;
import core.bill.comercial.service.ContractService;
import core.bill.comercial.service.CustomerService;
import core.bill.common.exception.SegesaServiceException;
import core.bill.invoice.model.InvoiceDTO;

@Service("commericalServiceProvider")
@Transactional(readOnly = true)
public class CommericalServiceProviderImpl implements CommericalServiceProvider{

	Logger logger = LoggerFactory.getLogger(CommericalServiceProviderImpl.class) ;
	
	@Autowired
	CustomerService customerSerice ; 
	
	@Autowired
	ContractService contractService ;
	
	@Override
	public CustomerDTO getCustomerById(Long customerId) {
		
		try {
			return customerSerice.getModelDTO(customerId);
		} catch (SegesaServiceException e) {
			logger.error("InvoiceServiceProvider :getCustomerById : "+e.toString());
			e.printStackTrace();
		}
		return null ;
	}

	@Override
	public ContractDTO getContratById(Long contractId) {
		
		try {
			return contractService.getModelDTO(contractId) ;
		} catch (SegesaServiceException e) {
			logger.error("InvoiceServiceProvider :getContratById : "+e.toString());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<InvoiceDTO> getInvoicesByContractId(Long contractId) {
		// TODO Auto-generated method stub
		return null;
	}

}
