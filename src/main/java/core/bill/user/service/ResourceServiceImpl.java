package core.bill.user.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import core.bill.common.exception.SegesaServiceException;
import core.bill.user.dao.ResourceDAO;
import core.bill.user.model.ResourceDTO;
import core.bill.user.model.ResourceSearch;


@Service("resourceService")
public class ResourceServiceImpl implements ResourceService{

	@Autowired
	ResourceDAO resourceDAO ; 
	
	@Override
	@Transactional(readOnly = false)
	public ResourceDTO saveModel(ResourceDTO model) throws SegesaServiceException {
		ResourceDTO resourceDTO = null;
		try {
			resourceDTO = resourceDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save resourceDTO : " + e);
		}
		return resourceDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteModel(ResourceDTO model) throws SegesaServiceException {
	
		try {
			resourceDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error delete resourceDTO : " + e);
		}
		
		
	}

	@Override
	@Transactional(readOnly = false)
	public ResourceDTO updateModel(ResourceDTO model) throws SegesaServiceException {
		ResourceDTO resourceDTO = null;
		try {
			resourceDTO = resourceDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error update resourceDTO : " + e);
		}
		return resourceDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public ResourceDTO getModelDTO(Long pk) throws SegesaServiceException {
		ResourceDTO resourceDTO = null;
		try {
			resourceDTO = resourceDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("Error get resourceDTO : " + e);
		}
		return resourceDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ResourceDTO> getDataList(ResourceSearch critiria, int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<ResourceDTO> list = null;
		try {
			list = resourceDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search resourceDTO : " + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ResourceDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<ResourceDTO> list = null;
		try {
			list = resourceDAO.getList(null, start, pageSize, "resourceName", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search resourceDTO : " + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ResourceDTO> getDataList() throws SegesaServiceException {
		List<ResourceDTO> list = null;
		try {
			list = resourceDAO.getList(null, 0, 0, "resourceName", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search resourceDTO : " + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ResourceDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<ResourceDTO> list = null;
		try {
			list = resourceDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search resourceDTO : " + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getRowCount(ResourceSearch critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = resourceDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("Error row count resourceDTO : " + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(ResourceDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(ResourceDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(ResourceDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(ResourceSearch critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourceDTO getMergeMode(ResourceDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(ResourceDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	

}
