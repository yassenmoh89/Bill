package core.bill.invoice.service;

import java.util.List;

import core.bill.comercial.model.ContractDashBoardModel;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.service.GenericService;
import core.bill.invoice.model.InvoiceDTO;
import core.bill.invoice.model.InvoiceSearch;

public interface InvoiceService extends GenericService<InvoiceDTO, InvoiceSearch>{
	
	public InvoiceDTO  getLastInvoice(Long contractId) throws  SegesaServiceException ;
	public Double  getPendingTotal(Long contractId) throws  SegesaServiceException ;
	public List<ContractDashBoardModel> getSumOfPendAndIncomeByField(String fieldName,String sum1,String sum2,InvoiceSearch criterion) throws SegesaServiceException;
	public Long getCountOfInvoices(InvoiceSearch creteria) throws SegesaServiceException;
	public Double  getSumAmount(String fieldName,InvoiceSearch creteria) throws  SegesaServiceException ;
	
}
