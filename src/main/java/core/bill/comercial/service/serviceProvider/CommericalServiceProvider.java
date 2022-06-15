package core.bill.comercial.service.serviceProvider;

import java.util.List;

import core.bill.comercial.model.ContractDTO;
import core.bill.comercial.model.CustomerDTO;
import core.bill.invoice.model.InvoiceDTO;

public interface CommericalServiceProvider {
	
	public CustomerDTO getCustomerById(Long customerId);
	
	public ContractDTO getContratById(Long contractId);
	
	public List<InvoiceDTO>  getInvoicesByContractId(Long contractId);

}
