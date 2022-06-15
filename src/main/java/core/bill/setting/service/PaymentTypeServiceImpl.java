package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.PaymentTypeDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.PaymentTypeDTO;

@Service("paymentTypeService")
@Transactional(readOnly = true)
public class PaymentTypeServiceImpl implements PaymentTypeService{

	@Autowired
	PaymentTypeDAO paymentTypeDAO ; 
	
	
	@Override
	
	public PaymentTypeDTO saveModel(PaymentTypeDTO model) throws SegesaServiceException {
		PaymentTypeDTO paymentTypeDTO = null;
		try {
			paymentTypeDTO = paymentTypeDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save paymentTypeDTO Error :" + e);
		}
		return paymentTypeDTO;
	}

	@Override
	public void deleteModel(PaymentTypeDTO model) throws SegesaServiceException {
		
		try {
			paymentTypeDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete paymentTypeDTO Error :" + e);
		}
		
		
	}

	@Override
	public PaymentTypeDTO updateModel(PaymentTypeDTO model) throws SegesaServiceException {
		PaymentTypeDTO paymentTypeDTO = null;
		try {
			paymentTypeDTO = paymentTypeDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update paymentTypeDTO Error :" + e);
		}
		return paymentTypeDTO;
	}

	@Override
	public PaymentTypeDTO getModelDTO(Long pk) throws SegesaServiceException {
		PaymentTypeDTO paymentTypeDTO = null;
		try {
			paymentTypeDTO = paymentTypeDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("update paymentTypeDTO Error :" + e);
		}
		return paymentTypeDTO;
	}

	@Override
	public List<PaymentTypeDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException {
		List<PaymentTypeDTO> list = null;
		try {
			list = paymentTypeDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search paymentTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<PaymentTypeDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<PaymentTypeDTO> list = null;
		try {
			list = paymentTypeDAO.getList(null, start, pageSize, "paymentTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search paymentTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<PaymentTypeDTO> getDataList() throws SegesaServiceException {
		List<PaymentTypeDTO> list = null;
		try {
			list = paymentTypeDAO.getList(null, 0, 0, "paymentTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search paymentTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<PaymentTypeDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<PaymentTypeDTO> list = null;
		try {
			list = paymentTypeDAO.getList(null, start, pageSize, "paymentTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search paymentTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		Long count  = null;
		try {
			count = paymentTypeDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("getRow count paymentTypeDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(PaymentTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(PaymentTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(PaymentTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentTypeDTO getMergeMode(PaymentTypeDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(PaymentTypeDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	

}
