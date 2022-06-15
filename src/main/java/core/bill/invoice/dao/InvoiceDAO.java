package core.bill.invoice.dao;
import java.util.List;
import core.bill.comercial.model.ContractDashBoardModel;
import core.bill.common.dao.GenericDAO;
import core.bill.invoice.model.InvoiceDTO;
import core.bill.invoice.model.InvoiceSearch;

public interface InvoiceDAO extends GenericDAO<InvoiceDTO, InvoiceSearch>{
	
	public Double getInvoicePendingAmount(InvoiceSearch certieria , String fieldName);
	public List<ContractDashBoardModel> getSumOfPendAndIncomeByField(String fieldName,String sum1,String sum2,InvoiceSearch criterion);
	public Long getCountOfInvoices(InvoiceSearch certieria);
	public Double getInvoiceSumAmount(InvoiceSearch certieria , String fieldName);
	
}
