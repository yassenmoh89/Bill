package core.bill.user.service;

import java.util.List;

import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.user.dao.UserSessionDAO;
import core.bill.user.model.UserSearch;
import core.bill.user.model.UserSessionDTO;

@Service("userSessionService")
public class UserSessionServiceImpl implements UserSessionService{

	@Autowired
	UserSessionDAO userSessionDAO;
	
	@Override
	@Transactional(readOnly = false)
	public UserSessionDTO saveModel(UserSessionDTO model) throws SegesaServiceException {
		UserSessionDTO userSessionDTO = null;
		try {
			userSessionDTO = userSessionDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save userSessionDAO : " + e);
		}

		return userSessionDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteModel(UserSessionDTO model) throws SegesaServiceException {
		try {
			userSessionDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save userSessionDAO : " + e);
		}
		
	}

	@Override
	@Transactional(readOnly = false)
	public UserSessionDTO updateModel(UserSessionDTO model) throws SegesaServiceException {
		UserSessionDTO userSessionDTO = null;
		try {
			userSessionDTO = userSessionDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save userSessionDTO : " + e);
		}

		return userSessionDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public UserSessionDTO getModelDTO(Long pk) throws SegesaServiceException {
		UserSessionDTO userSessionDTO = null;
		try {
			userSessionDTO = userSessionDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save userSessionDTO : " + e);
		}

		return userSessionDTO;
	}

	@Override
	public UserSessionDTO getMergeMode(UserSessionDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(UserSessionDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserSessionDTO> getDataList(UserSearch critiria, int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<UserSessionDTO> list = null;
		try {
			list = userSessionDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search userSessionDAO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserSessionDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<UserSessionDTO> list = null;
		try {
			list = userSessionDAO.getList(null, 0, 0, "userId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search userSessionDAO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserSessionDTO> getDataList() throws SegesaServiceException {
		List<UserSessionDTO> list = null;
		try {
			list = userSessionDAO.getList(null, 0, 0, "userId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search userSessionDAO : " + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserSessionDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<UserSessionDTO> list = null;
		try {
			list = userSessionDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search userSessionDAO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getRowCount(UserSearch critiria) throws SegesaServiceException {
		return userSessionDAO.getRowNum(critiria);
	}

	@Override
	public List<String> valideSave(UserSessionDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(UserSessionDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(UserSessionDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(UserSearch critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserSessionDTO> getDataListProjection(UserSearch critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException {
		List<UserSessionDTO> list = null;
		try {
			ProjectionList projectionList = Projections.projectionList(); 
			
			Projection userIdProj = Projections.property("sessionId"); 
			Projection userNameProj = Projections.property("userName"); 
			
			projectionList.add(userIdProj);
			projectionList.add(userNameProj);

			list = userSessionDAO.getList(critiria,projectionList, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search userSessionDAO Error :" + e);
		}
		return list;
	}

}
