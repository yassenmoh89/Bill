package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.ContractPaymentDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.ContractPaymentDTO;


@Service("contractPaymentService")
@Transactional(readOnly = true)
public class ContractPaymentServiceImpl implements ContractPaymentService{

	@Autowired
	ContractPaymentDAO contractPaymentDAO ; 
	
	@Override
	public ContractPaymentDTO saveModel(ContractPaymentDTO model) throws SegesaServiceException {
		ContractPaymentDTO contractPaymentDTO = null;
		try {
			contractPaymentDTO = contractPaymentDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save contractPaymentDTO Error :" + e);
		}
		return contractPaymentDTO;
	}

	@Override
	public void deleteModel(ContractPaymentDTO model) throws SegesaServiceException {
		
		try {
			contractPaymentDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("deleteModel contractPaymentDTO Error :" + e);
		}
		
		
	}

	@Override
	public ContractPaymentDTO updateModel(ContractPaymentDTO model) throws SegesaServiceException {
		ContractPaymentDTO contractPaymentDTO = null;
		try {
			contractPaymentDTO = contractPaymentDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("updateModel contractPaymentDTO Error :" + e);
		}
		return contractPaymentDTO;
	}

	@Override
	public ContractPaymentDTO getModelDTO(Long pk) throws SegesaServiceException {
		ContractPaymentDTO contractPaymentDTO = null;
		try {
			contractPaymentDTO = contractPaymentDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("getModelDTO contractPaymentDTO Error :" + e);
		}
		return contractPaymentDTO;
	}

	@Override
	public ContractPaymentDTO getMergeMode(ContractPaymentDTO model) throws SegesaServiceException {
		ContractPaymentDTO contractPaymentDTO = null;
		try {
			contractPaymentDTO = contractPaymentDAO.merge(model);
		} catch (Exception e) {
			throw new SegesaServiceException("getMergeMode contractPaymentDTO Error :" + e);
		}
		return contractPaymentDTO;
	}

	@Override
	public void saveOrUpdateMode(ContractPaymentDTO model) throws SegesaServiceException {
		
		try {
			contractPaymentDAO.saveOrUpdate(model);
		} catch (Exception e) {
			throw new SegesaServiceException("saveOrUpdateMode contractPaymentDTO Error :" + e);
		}
		
		
	}

	@Override
	public List<ContractPaymentDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException {
		List<ContractPaymentDTO> list = null;
		try {
			list = contractPaymentDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList contractPaymentDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ContractPaymentDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<ContractPaymentDTO> list = null;
		try {
			list = contractPaymentDAO.getList(null, start, pageSize, "contractPaymentId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList contractPaymentDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ContractPaymentDTO> getDataList() throws SegesaServiceException {
		List<ContractPaymentDTO> list = null;
		try {
			list = contractPaymentDAO.getList(null, 0, 0, "contractPaymentId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList contractPaymentDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ContractPaymentDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<ContractPaymentDTO> list = null;
		try {
			list = contractPaymentDAO.getList(null, start, pageSize, orderBy, Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList contractPaymentDTO Error :" + e);
		}
		return list;
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		Long  count = null;
		try {
			count = contractPaymentDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("getRowCount contractPaymentDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(ContractPaymentDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(ContractPaymentDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(ContractPaymentDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

}
