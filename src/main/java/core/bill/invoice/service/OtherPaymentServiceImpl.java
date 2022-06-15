package core.bill.invoice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.invoice.dao.OtherPaymentDAO;
import core.bill.invoice.model.InvoiceSearch;
import core.bill.invoice.model.OtherPaymentDTO;


@Service("otherPaymentService")
public class OtherPaymentServiceImpl implements OtherPaymentService{

	@Autowired
	OtherPaymentDAO otherPaymentDAO ;
	
	@Override
	@Transactional(readOnly = false)
	public OtherPaymentDTO saveModel(OtherPaymentDTO model) throws SegesaServiceException {
		OtherPaymentDTO otherPaymentDTO = null;
		try {
			otherPaymentDTO = otherPaymentDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save otherPaymentDTO Error :" + e);
		}
		return otherPaymentDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteModel(OtherPaymentDTO model) throws SegesaServiceException {
		
		try {
			otherPaymentDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete otherPaymentDTO Error :" + e);
		}
		
		
	}

	@Override
	@Transactional(readOnly = false)
	public OtherPaymentDTO updateModel(OtherPaymentDTO model) throws SegesaServiceException {
		OtherPaymentDTO otherPaymentDTO = null;
		try {
			otherPaymentDTO = otherPaymentDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("upda otherPaymentDTO Error :" + e);
		}
		return otherPaymentDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public OtherPaymentDTO getModelDTO(Long pk) throws SegesaServiceException {
		OtherPaymentDTO otherPaymentDTO = null;
		try {
			otherPaymentDTO = otherPaymentDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("model otherPaymentDTO Error :" + e);
		}
		return otherPaymentDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public OtherPaymentDTO getMergeMode(OtherPaymentDTO model) throws SegesaServiceException {
		OtherPaymentDTO otherPaymentDTO = null;
		try {
			otherPaymentDTO = otherPaymentDAO.merge(model);
		} catch (Exception e) {
			throw new SegesaServiceException("model otherPaymentDTO Error :" + e);
		}
		return otherPaymentDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void saveOrUpdateMode(OtherPaymentDTO model) throws SegesaServiceException {
		
		try {
			 otherPaymentDAO.saveOrUpdate(model);
		} catch (Exception e) {
			throw new SegesaServiceException("saveOrUpdateMode otherPaymentDTO Error :" + e);
		}
		
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<OtherPaymentDTO> getDataList(InvoiceSearch critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException {
		List<OtherPaymentDTO>  list = null;
		try {
			list = otherPaymentDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("list otherPaymentDTO Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<OtherPaymentDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		
		List<OtherPaymentDTO>  list = null;
		try {
			list = otherPaymentDAO.getList(null, start, pageSize, "otherPaymentId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("list otherPaymentDTO Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<OtherPaymentDTO> getDataList() throws SegesaServiceException {
		
		List<OtherPaymentDTO>  list = null;
		try {
			list = otherPaymentDAO.getList(null, 0, 0, "otherPaymentId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("list otherPaymentDTO Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<OtherPaymentDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<OtherPaymentDTO>  list = null;
		try {
			list = otherPaymentDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("list otherPaymentDTO Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getRowCount(InvoiceSearch critiria) throws SegesaServiceException {
		
		Long   count = null;
		try {
			count = otherPaymentDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("list otherPaymentDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(OtherPaymentDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(OtherPaymentDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(OtherPaymentDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(InvoiceSearch critiria) {
		// TODO Auto-generated method stub
		return null;
	}

}
