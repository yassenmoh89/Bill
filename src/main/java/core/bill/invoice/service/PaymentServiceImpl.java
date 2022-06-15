package core.bill.invoice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.invoice.dao.PaymentDAO;
import core.bill.invoice.model.InvoiceSearch;
import core.bill.invoice.model.PaymentDTO;


@Service("paymentService")
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	PaymentDAO paymentDAO ; 
	
	@Override
	@Transactional(readOnly = false)
	public PaymentDTO saveModel(PaymentDTO model) throws SegesaServiceException {
		PaymentDTO paymentDTO = null;
		try {
			paymentDTO = paymentDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save paymentDTO Error :" + e);
		}
		return paymentDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteModel(PaymentDTO model) throws SegesaServiceException {
		
		try {
			 paymentDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("deleteModel paymentDTO Error :" + e);
		}
		
		
	}

	@Override
	@Transactional(readOnly = false)
	public PaymentDTO updateModel(PaymentDTO model) throws SegesaServiceException {
		
		PaymentDTO paymentDTO = null;
		
		try {
			paymentDTO = paymentDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("updateModel paymentDTO Error :" + e);
		}
		
		return paymentDTO ;
	}

	@Override
	@Transactional(readOnly = false)
	public PaymentDTO getModelDTO(Long pk) throws SegesaServiceException {
			
		PaymentDTO paymentDTO = null;
		try {
			paymentDTO = paymentDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("getModel paymentDTO Error :" + e);
		}
		
		return paymentDTO ;
	}

	@Override
	@Transactional(readOnly = false)
	public PaymentDTO getMergeMode(PaymentDTO model) throws SegesaServiceException {
		
		PaymentDTO paymentDTO = null;
		try {
			paymentDTO = paymentDAO.merge(model);
		} catch (Exception e) {
			throw new SegesaServiceException("getMergeModel paymentDTO Error :" + e);
		}
		
		return paymentDTO ;
	}

	@Override
	@Transactional(readOnly = false)
	public void saveOrUpdateMode(PaymentDTO model) throws SegesaServiceException {
		
		try {
			paymentDAO.merge(model);
		} catch (Exception e) {
			throw new SegesaServiceException("saveOrUpdateMode paymentDTO Error :" + e);
		}
		
		
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<PaymentDTO> getDataList(InvoiceSearch critiria, int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<PaymentDTO> list = null;
		try {
			list = paymentDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList paymentDTO Error :" + e);
		}
		
		return list ;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PaymentDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		
		List<PaymentDTO> list = null;
		try {
			list = paymentDAO.getList(null, start, pageSize, "paymentId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList paymentDTO Error :" + e);
		}
		
		return list ;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PaymentDTO> getDataList() throws SegesaServiceException {
		List<PaymentDTO> list = null;
		try {
			list = paymentDAO.getList(null, 0, 0, "paymentId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList paymentDTO Error :" + e);
		}
		
		return list ;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PaymentDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<PaymentDTO> list = null;
		try {
			list = paymentDAO.getList(null, 0, 0, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList paymentDTO Error :" + e);
		}
		
		return list ;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getRowCount(InvoiceSearch critiria) throws SegesaServiceException {
		
		Long count = null;
		try {
			count = paymentDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("getRowCount paymentDTO Error :" + e);
		}
		
		return count ;
	}

	@Override
	public List<String> valideSave(PaymentDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(PaymentDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(PaymentDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(InvoiceSearch critiria) {
		// TODO Auto-generated method stub
		return null;
	}

}
