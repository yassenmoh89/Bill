package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.ContractTypeDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.ContractTypeDTO;

@Service
@Transactional(readOnly = true)
public class ContractTypeServiceImpl implements ContractTypeService {

	@Autowired
	ContractTypeDAO contractTypeDAO;

	@Override
	public ContractTypeDTO saveModel(ContractTypeDTO model) throws SegesaServiceException {
		ContractTypeDTO contractTypeDTO = null;
		try {
			contractTypeDTO = contractTypeDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save contractOwnerTypeDTO Error :" + e);
		}
		return contractTypeDTO;
	}

	@Override
	public void deleteModel(ContractTypeDTO model) throws SegesaServiceException {

		try {
			contractTypeDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete contractOwnerTypeDTO Error :" + e);
		}

	}

	@Override
	public ContractTypeDTO updateModel(ContractTypeDTO model) throws SegesaServiceException {
		ContractTypeDTO contractTypeDTO = null;
		try {
			contractTypeDTO = contractTypeDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update contractOwnerTypeDTO Error :" + e);
		}
		return contractTypeDTO;
	}

	@Override
	public ContractTypeDTO getModelDTO(Long pk) throws SegesaServiceException {
		ContractTypeDTO contractTypeDTO = null;
		try {
			contractTypeDTO = contractTypeDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("get contractOwnerTypeDTO Error :" + e);
		}
		return contractTypeDTO;
	}

	@Override
	public List<ContractTypeDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException {
		List<ContractTypeDTO> list = null;
		try {
			list = contractTypeDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search contractOwnerTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ContractTypeDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		
		List<ContractTypeDTO> list = null;
		
		try {
				list = contractTypeDAO.getList(null, start, pageSize, "contractTypeId", Boolean.TRUE);
			} catch (Exception e) {
				throw new SegesaServiceException("search contractOwnerTypeDTO Error :" + e);
			}
		
		return list;
	}

	@Override
	public List<ContractTypeDTO> getDataList() throws SegesaServiceException {
		List<ContractTypeDTO> list = null;
		try {
			list = contractTypeDAO.getList(null, 0, 0, "contractTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search contractOwnerTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ContractTypeDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<ContractTypeDTO> list = null;
		try {
			list = contractTypeDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search contractOwnerTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = contractTypeDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("count Row contractOwnerTypeDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(ContractTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(ContractTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(ContractTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContractTypeDTO getMergeMode(ContractTypeDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(ContractTypeDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	
}
