package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.ContractBillingDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.ContractBillingDTO;

@Service("contractBillingService")
@Transactional(readOnly = true)
public class ContractBillingServiceImpl implements ContractBillingService{

	@Autowired
	ContractBillingDAO contractBillingDAO ;
	
	@Override
	public ContractBillingDTO saveModel(ContractBillingDTO model) throws SegesaServiceException {
		ContractBillingDTO contractBillingDTO = null;
		try {
			contractBillingDTO = contractBillingDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save contractBillingDTO Error :" + e);
		}
		return contractBillingDTO;
	}

	@Override
	public void deleteModel(ContractBillingDTO model) throws SegesaServiceException {
		
		try {
			 contractBillingDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete contractBillingDTO Error :" + e);
		}
		
		
	}

	@Override
	public ContractBillingDTO updateModel(ContractBillingDTO model) throws SegesaServiceException {
		ContractBillingDTO contractBillingDTO = null;
		try {
			contractBillingDTO = contractBillingDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update contractBillingDTO Error :" + e);
		}
		return contractBillingDTO;
	}

	@Override
	public ContractBillingDTO getModelDTO(Long pk) throws SegesaServiceException {
		ContractBillingDTO contractBillingDTO = null;
		try {
			contractBillingDTO = contractBillingDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("get contractBillingDTO Error :" + e);
		}
		return contractBillingDTO;
	}

	@Override
	public ContractBillingDTO getMergeMode(ContractBillingDTO model) throws SegesaServiceException {
		ContractBillingDTO contractBillingDTO = null;
		try {
			contractBillingDTO = contractBillingDAO.merge(model);
		} catch (Exception e) {
			throw new SegesaServiceException("merge contractBillingDTO Error :" + e);
		}
		return contractBillingDTO;
	}

	@Override
	public void saveOrUpdateMode(ContractBillingDTO model) throws SegesaServiceException {
		
		try {
			contractBillingDAO.saveOrUpdate(model);
		} catch (Exception e) {
			throw new SegesaServiceException("saveOrUpdate contractBillingDTO Error :" + e);
		}
		
		
	}

	@Override
	public List<ContractBillingDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException {
		List<ContractBillingDTO> list =null ;
		try {
			list = contractBillingDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("list contractBillingDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ContractBillingDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<ContractBillingDTO> list =null ;
		try {
			list = contractBillingDAO.getList(null, start, pageSize, "contractBillingId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("list contractBillingDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ContractBillingDTO> getDataList() throws SegesaServiceException {
		List<ContractBillingDTO> list =null ;
		try {
			list = contractBillingDAO.getList(null, 0, 0, "contractBillingId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("merge contractBillingDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ContractBillingDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<ContractBillingDTO> list =null ;
		try {
			list = contractBillingDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("merge contractBillingDTO Error :" + e);
		}
		return list;
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		Long  count =null ;
		try {
			count = contractBillingDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("merge contractBillingDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(ContractBillingDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(ContractBillingDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(ContractBillingDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

}
