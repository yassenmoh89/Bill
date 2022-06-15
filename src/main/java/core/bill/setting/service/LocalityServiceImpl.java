package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.LocalityDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.LocalityDTO;

@Service("localityService")
@Transactional(readOnly = true)
public class LocalityServiceImpl implements LocalityService{

	@Autowired
	LocalityDAO localityDAO ;
	
	@Override
	public LocalityDTO saveModel(LocalityDTO model) throws SegesaServiceException {
		LocalityDTO localityDTO = null;
		try {
			localityDTO = localityDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save localityDAO Error :" + e);
		}
		return localityDTO;
	}

	@Override
	public void deleteModel(LocalityDTO model) throws SegesaServiceException {
		
		try {
			localityDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete localityDAO Error :" + e);
		}
		
		
	}

	@Override
	public LocalityDTO updateModel(LocalityDTO model) throws SegesaServiceException {
		LocalityDTO localityDTO = null;
		try {
			localityDTO = localityDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update localityDAO Error :" + e);
		}
		return localityDTO;
	}

	@Override
	public LocalityDTO getModelDTO(Long pk) throws SegesaServiceException {
		LocalityDTO localityDTO = null;
		try {
			localityDTO = localityDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("get localityDAO Error :" + e);
		}
		return localityDTO;
	}

	@Override
	public List<LocalityDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException {
		List<LocalityDTO> list = null;
		try {
			list = localityDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search localityDAO Error :" + e);
		}
		return list;
	}

	@Override
	public List<LocalityDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<LocalityDTO> list = null;
		try {
			list = localityDAO.getList(null, start, pageSize, "localId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search localityDAO Error :" + e);
		}
		return list;
	}

	@Override
	public List<LocalityDTO> getDataList() throws SegesaServiceException {
		List<LocalityDTO> list = null;
		try {
			list = localityDAO.getList(null, 0, 0, "localId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search localityDAO Error :" + e);
		}
		return list;
	}

	@Override
	public List<LocalityDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<LocalityDTO> list = null;
		try {
			list = localityDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search localityDAO Error :" + e);
		}
		return list;
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = localityDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("search localityDAO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(LocalityDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(LocalityDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(LocalityDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalityDTO getMergeMode(LocalityDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(LocalityDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	

}
