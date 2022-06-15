package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.NationalityDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.NationalityDTO;

@Service("nationalityService")
@Transactional(readOnly = true)
public class NationalityServiceImpl implements NationalityService{

	@Autowired
	NationalityDAO nationalityDAO ;
	
	@Override
	public NationalityDTO saveModel(NationalityDTO model) throws SegesaServiceException {
		NationalityDTO nationalityDTO = null;
		try {
			nationalityDTO = nationalityDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save nationalityDTO Error :" + e);
		}
		return nationalityDTO;
	}

	@Override
	public void deleteModel(NationalityDTO model) throws SegesaServiceException {
		
		try {
			nationalityDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete nationalityDTO Error :" + e);
		}
		
		
	}

	@Override
	public NationalityDTO updateModel(NationalityDTO model) throws SegesaServiceException {
		NationalityDTO nationalityDTO = null;
		try {
			nationalityDTO = nationalityDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update nationalityDTO Error :" + e);
		}
		return nationalityDTO;
	}

	@Override
	public NationalityDTO getModelDTO(Long pk) throws SegesaServiceException {
		NationalityDTO nationalityDTO = null;
		try {
			nationalityDTO = nationalityDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("get nationalityDTO Error :" + e);
		}
		return nationalityDTO;
	}

	@Override
	public List<NationalityDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException {
		List<NationalityDTO> list = null;
		try {
			list = nationalityDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search nationalityDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<NationalityDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<NationalityDTO> list = null;
		try {
			list = nationalityDAO.getList(null, start, pageSize, "nationalityId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search nationalityDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<NationalityDTO> getDataList() throws SegesaServiceException {
		List<NationalityDTO> list = null;
		try {
			list = nationalityDAO.getList(null, 0, 0, "nationalityId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search nationalityDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<NationalityDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		
		List<NationalityDTO> list = null;
		try {
			list = nationalityDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search nationalityDTO Error :" + e);
		}
		return list;
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = nationalityDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("search nationalityDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(NationalityDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(NationalityDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(NationalityDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NationalityDTO getMergeMode(NationalityDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(NationalityDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	

}
