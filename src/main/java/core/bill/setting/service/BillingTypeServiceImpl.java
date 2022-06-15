package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.BillingTypeDAO;
import core.bill.setting.model.BillingTypeDTO;
import core.bill.setting.model.CommonSearchDTO;

@Service
@Transactional(readOnly = true)
public class BillingTypeServiceImpl implements BillingTypeService{

	@Autowired
	BillingTypeDAO billingTypeDAO ; 
	
	@Override
	public BillingTypeDTO saveModel(BillingTypeDTO model) throws SegesaServiceException {
		return billingTypeDAO.save(model);
	}

	@Override
	public void deleteModel(BillingTypeDTO model) throws SegesaServiceException {
		billingTypeDAO.delete(model);
	}

	@Override
	public BillingTypeDTO updateModel(BillingTypeDTO model) throws SegesaServiceException {
		BillingTypeDTO  billingTypeDTO =null ; 
		try {
			billingTypeDTO = billingTypeDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update Billing Type Error :"+e) ;
		}
		return billingTypeDTO;
	}

	@Override
	public BillingTypeDTO getModelDTO(Long pk) throws SegesaServiceException {

		BillingTypeDTO  billingTypeDTO =null ; 
		try {
			billingTypeDTO = billingTypeDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("get Billing Type DTO Error :"+e) ;
		}
		return billingTypeDTO;
	}

	@Override
	public List<BillingTypeDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException {
		List<BillingTypeDTO> list = null;
		try {
			list = billingTypeDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("update Billing Type Error :" + e);
		}
		return list;
	}

	@Override
	public List<BillingTypeDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		
		List<BillingTypeDTO> list = null;
		try {
			list = billingTypeDAO.getList(null, start, pageSize, "billingTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("update Billing Type Error :" + e);
		}
		return list;
	}

	@Override
	public List<BillingTypeDTO> getDataList() throws SegesaServiceException {
		List<BillingTypeDTO> list = null;
		try {
			list = billingTypeDAO.getList(null, 0, 0, "billingTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("update Billing Type Error :" + e);
		}
		return list;
	}

	@Override
	public List<BillingTypeDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<BillingTypeDTO> list = null;
		try {
			list = billingTypeDAO.getList(null, start, pageSize, "billingTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("update Billing Type Error :" + e);
		}
		return list;
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = billingTypeDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("update Billing Type Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(BillingTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(BillingTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(BillingTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BillingTypeDTO getMergeMode(BillingTypeDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(BillingTypeDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	
}
