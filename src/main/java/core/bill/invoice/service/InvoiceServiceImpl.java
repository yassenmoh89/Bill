package core.bill.invoice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.comercial.model.ContractDashBoardModel;
import core.bill.common.exception.SegesaServiceException;
import core.bill.invoice.dao.InvoiceDAO;
import core.bill.invoice.model.InvoiceDTO;
import core.bill.invoice.model.InvoiceSearch;


@Service("invoiceService")
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired
	InvoiceDAO invoiceDAO ; 
	
	@Override
	@Transactional(readOnly = false)
	public InvoiceDTO saveModel(InvoiceDTO model) throws SegesaServiceException {
		
		InvoiceDTO invoiceDTO = null;
		try {
			invoiceDTO = invoiceDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save InvoiceDTO Error :" + e);
		}
		return invoiceDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteModel(InvoiceDTO model) throws SegesaServiceException {
	
		try {
			  invoiceDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("deleteModel InvoiceDTO Error :" + e);
		}
		
		
	}

	@Override
	@Transactional(readOnly = false)
	public InvoiceDTO updateModel(InvoiceDTO model) throws SegesaServiceException {
		
		InvoiceDTO invoiceDTO = null;
		try {
			invoiceDTO = invoiceDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("updateModel InvoiceDTO Error :" + e);
		}
		return invoiceDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public InvoiceDTO getModelDTO(Long pk) throws SegesaServiceException {
		
		InvoiceDTO invoiceDTO = null;
		try {
			invoiceDTO = invoiceDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("getModelDTO InvoiceDTO Error :" + e);
		}
		return invoiceDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public InvoiceDTO getMergeMode(InvoiceDTO model) throws SegesaServiceException {

		InvoiceDTO invoiceDTO = null;
		try {
			invoiceDTO = invoiceDAO.merge(model);
		} catch (Exception e) {
			throw new SegesaServiceException("getModelDTO InvoiceDTO Error :" + e);
		}
		return invoiceDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void saveOrUpdateMode(InvoiceDTO model) throws SegesaServiceException {
		
		try {
			  invoiceDAO.saveOrUpdate(model);
		} catch (Exception e) {
			throw new SegesaServiceException("getModelDTO InvoiceDTO Error :" + e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<InvoiceDTO> getDataList(InvoiceSearch critiria, int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<InvoiceDTO> list = null;
		try {
			list = invoiceDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("getModelDTO InvoiceDTO Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<InvoiceDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<InvoiceDTO> list = null;
		try {
			list = invoiceDAO.getList(null, start, pageSize, "invoiceId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("getModelDTO InvoiceDTO Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<InvoiceDTO> getDataList() throws SegesaServiceException {
		List<InvoiceDTO> list = null;
		try {
			list = invoiceDAO.getList(null, 0, 0, "invoiceId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("getModelDTO InvoiceDTO Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<InvoiceDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		
		List<InvoiceDTO> list = null;
		try {
			list = invoiceDAO.getList(null, start, pageSize, orderBy,desc);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList InvoiceDTO Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getRowCount(InvoiceSearch critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = invoiceDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList InvoiceDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(InvoiceDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(InvoiceDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(InvoiceDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(InvoiceSearch critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public InvoiceDTO getLastInvoice(Long contractId) throws SegesaServiceException {
		InvoiceDTO invoiceDto = null;
		try {
			InvoiceSearch search = new InvoiceSearch();
			search.setContractId(contractId);
			List<InvoiceDTO>  list = invoiceDAO.getList(search, 0, 2, "invoiceId", Boolean.TRUE);
			System.out.println("List<InvoiceDTO> : "+list.size());
			
			if(list!=null && list.size()>0)
			{
				for(InvoiceDTO inv:list)
				{
					//System.out.println("==== invoice ID : "+inv.getInvoiceId() +"  creatd invoice :"+inv.getCreatedInvoice() );
					if(inv.getCreatedInvoice()!=null && inv.getCreatedInvoice())
					{
						//System.out.println("inv.getInvoice ID"+inv.getInvoiceId());
						invoiceDto =inv ;
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SegesaServiceException("getModelDTO InvoiceDTO Error :" + e);
		}
		return invoiceDto;
	}

	@Override
	@Transactional(readOnly = true)
	public Double getPendingTotal(Long contractId) throws SegesaServiceException {
		Double total = null;
		try {
			InvoiceSearch certieria = new InvoiceSearch();
			certieria.setContractId(contractId);
			certieria.setInvoiceCreated(Boolean.TRUE);
			certieria.setPaymentStatus(Boolean.FALSE);
			total = invoiceDAO.getInvoicePendingAmount(certieria, "amountPayable") ;
		} catch (Exception e) {
			throw new SegesaServiceException("getModelDTO InvoiceDTO Error :" + e);
		}
		return total;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ContractDashBoardModel> getSumOfPendAndIncomeByField(String fieldName, String sum1, String sum2,
			InvoiceSearch criterion) throws SegesaServiceException {
		
		List<ContractDashBoardModel> list = null;
		try {
			list = invoiceDAO.getSumOfPendAndIncomeByField(fieldName, sum1, sum2, criterion);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList getSumOfPendAndIncomeByField Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getCountOfInvoices(InvoiceSearch creteria) throws SegesaServiceException {
		Long list = null;
		try {	
			list = invoiceDAO.getCountOfInvoices(creteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SegesaServiceException("getDataList getCountOfInvoices Error :" + e);
			
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Double getSumAmount(String fieldName, InvoiceSearch creteria) throws SegesaServiceException {
		Double total = null;
		try {

			total = invoiceDAO.getInvoiceSumAmount(creteria, fieldName) ;
		} catch (Exception e) {
			throw new SegesaServiceException("getModelDTO InvoiceDTO Error :" + e);
		}
		return total;
	}

}
