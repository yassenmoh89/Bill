package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.ContractOwnerTypeDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.ContractOwnerTypeDTO;

@Service
@Transactional(readOnly = true)
public class ContractOwnerTypeServiceImpl  implements ContractOwnerTypeService{

	@Autowired
	ContractOwnerTypeDAO contractOwnerTypeDAO ;
	
	@Override
	public ContractOwnerTypeDTO saveModel(ContractOwnerTypeDTO model) throws SegesaServiceException {
		ContractOwnerTypeDTO contractOwnerTypeDTO = null;
		try {
			contractOwnerTypeDTO = contractOwnerTypeDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save contractOwnerTypeDTO Error :" + e);
		}
		return contractOwnerTypeDTO;
	}

	@Override
	public void deleteModel(ContractOwnerTypeDTO model) throws SegesaServiceException {
	
		try {
			contractOwnerTypeDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete contractOwnerTypeDTO Error :" + e);
		}
		
		
	}

	@Override
	public ContractOwnerTypeDTO updateModel(ContractOwnerTypeDTO model) throws SegesaServiceException {
		ContractOwnerTypeDTO contractOwnerTypeDTO = null;
		try {
			contractOwnerTypeDTO = contractOwnerTypeDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update contractOwnerTypeDTO Error :" + e);
		}
		return contractOwnerTypeDTO;
	}

	@Override
	public ContractOwnerTypeDTO getModelDTO(Long pk) throws SegesaServiceException {
		ContractOwnerTypeDTO contractOwnerTypeDTO = null;
		try {
			contractOwnerTypeDTO = contractOwnerTypeDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("get contractOwnerTypeDTO Error :" + e);
		}
		return contractOwnerTypeDTO;
	}

	@Override
	public List<ContractOwnerTypeDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException {
		List<ContractOwnerTypeDTO> list =null;
		try {
			list = contractOwnerTypeDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("update contractOwnerTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ContractOwnerTypeDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<ContractOwnerTypeDTO> list =null;
		try {
			list = contractOwnerTypeDAO.getList(null, start, pageSize, "contractOwnerTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Search contractOwnerTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ContractOwnerTypeDTO> getDataList() throws SegesaServiceException {
		List<ContractOwnerTypeDTO> list =null;
		try {
			list = contractOwnerTypeDAO.getList(null, 0, 0, "contractOwnerTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Search contractOwnerTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ContractOwnerTypeDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<ContractOwnerTypeDTO> list =null;
		try {
			list = contractOwnerTypeDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Search contractOwnerTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		Long count =null;
		try {
			count = contractOwnerTypeDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("Search contractOwnerTypeDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(ContractOwnerTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(ContractOwnerTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(ContractOwnerTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContractOwnerTypeDTO getMergeMode(ContractOwnerTypeDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(ContractOwnerTypeDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	

	
	

}
