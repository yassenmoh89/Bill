package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.IdentificationTypeDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.IdentificationTypeDTO;
@Service
@Transactional(readOnly = true)
public class IdentificationTypeServiceImpl implements IdentificationTypeService{

	@Autowired
	IdentificationTypeDAO identificationTypeDAO;
	
	@Override
	public IdentificationTypeDTO saveModel(IdentificationTypeDTO model) throws SegesaServiceException {
		
		IdentificationTypeDTO IdentificationTypeDTO = null;
		try {
			IdentificationTypeDTO = identificationTypeDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save IdentificationTypeDTO Error :" + e);
		}
		return IdentificationTypeDTO;
	}

	@Override
	public void deleteModel(IdentificationTypeDTO model) throws SegesaServiceException {
		
		try {
			 identificationTypeDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete IdentificationTypeDTO Error :" + e);
		}
		
		}

	@Override
	public IdentificationTypeDTO updateModel(IdentificationTypeDTO model) throws SegesaServiceException {
		IdentificationTypeDTO IdentificationTypeDTO = null;
		try {
			IdentificationTypeDTO = identificationTypeDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update IdentificationTypeDTO Error :" + e);
		}
		return IdentificationTypeDTO;
	}

	@Override
	public IdentificationTypeDTO getModelDTO(Long pk) throws SegesaServiceException {
		IdentificationTypeDTO IdentificationTypeDTO = null;
		try {
			IdentificationTypeDTO = identificationTypeDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("get IdentificationTypeDTO Error :" + e);
		}
		return IdentificationTypeDTO;
	}

	@Override
	public List<IdentificationTypeDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException {
		List<IdentificationTypeDTO> list = null;
		try {
			list = identificationTypeDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search IdentificationTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<IdentificationTypeDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<IdentificationTypeDTO> list = null;
		try {
			list = identificationTypeDAO.getList(null, start, pageSize, "identificationTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search IdentificationTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<IdentificationTypeDTO> getDataList() throws SegesaServiceException {
		List<IdentificationTypeDTO> list = null;
		try {
			list = identificationTypeDAO.getList(null, 0, 0, "identificationTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search IdentificationTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<IdentificationTypeDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<IdentificationTypeDTO> list = null;
		try {
			list = identificationTypeDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search IdentificationTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		
		Long count = null;
		try {
			count = identificationTypeDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("search IdentificationTypeDTO Error :" + e);
		}
		return count;
	}

	
	
	@Override
	public List<String> valideSave(IdentificationTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(IdentificationTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(IdentificationTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IdentificationTypeDTO getMergeMode(IdentificationTypeDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(IdentificationTypeDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

}
