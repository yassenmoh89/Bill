package core.bill.user.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.util.CustomUtil;
import core.bill.user.dao.UserDAO;
import core.bill.user.model.UserDTO;
import core.bill.user.model.UserRoleDTO;
import core.bill.user.model.UserSearch;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO ;
	
	@Autowired 
	UserRoleGroupService userRoleGroupService ;

	@Override
	@Transactional(readOnly = false)
	public UserDTO saveModel(UserDTO model) throws SegesaServiceException {
		UserDTO userDTO = null;
		try {
			userDTO = userDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save userDTO : " + e);
		}

		return userDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteModel(UserDTO model) throws SegesaServiceException {
	
		try {
		 userDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error delete userDTO : " + e);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public UserDTO updateModel(UserDTO model) throws SegesaServiceException {
		UserDTO userDTO = null;
		try {
			userDTO = userDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error update userDTO : " + e);
		}

		return userDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO getModelDTO(Long pk) throws SegesaServiceException {
		UserDTO userDTO = null;
		try {
			userDTO = userDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("Error get userDTO : " + e);
		}

		return userDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDTO> getDataList(UserSearch critiria, int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<UserDTO> list = null;
		try {
			
			ProjectionList projectionList = Projections.projectionList(); 
			Projection userIdProj = Projections.property("userId"); 
			Projection userNameProj = Projections.property("userName"); 
			Projection userCodeProj = Projections.property("userCode"); 
			Projection mobileProj = Projections.property("userProfileDTO.mobile"); 
			Projection emailProj = Projections.property("userProfileDTO.email"); 
			Projection activeProj = Projections.property("active");
			//Projection localityNameProj = Projections.property("localityDTO.description");
			
			projectionList.add(userIdProj);
			projectionList.add(userNameProj);
			projectionList.add(userCodeProj);
			projectionList.add(mobileProj);
			projectionList.add(emailProj);
			projectionList.add(activeProj);
			
			list = userDAO.getListUsers(critiria, projectionList, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search userDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<UserDTO> list = null;
		try {
			list = userDAO.getList(null, 0, 0, "userId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search userDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDTO> getDataList() throws SegesaServiceException {
		List<UserDTO> list = null;
		try {
			list = userDAO.getList(null, 0, 0, "userId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search userDTO : " + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<UserDTO> list = null;
		try {
			list = userDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search userDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getRowCount(UserSearch critiria) throws SegesaServiceException {
		return userDAO.getRowNum(critiria);
	}

	@Override
	public List<String> valideSave(UserDTO model) {
		
		List<String> errorList = new ArrayList<String>();
		if(model.getUserName()==null || model.getUserName().equals(""))
		{
			String params[]  = {CustomUtil.getBundlMSGValue("user.name")} ;
			errorList.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		
		if(model.getUserCode()==null || model.getUserCode().equals(""))
		{
			String params[]  = {CustomUtil.getBundlMSGValue("user.code")} ;
			errorList.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		
		
		if(model.getActive()==null)
		{
			String params[]  = {CustomUtil.getBundlMSGValue("user.status.active")} ;
			errorList.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		
		if(model.getUserProfileDTO().getMobile()==null || model.getUserProfileDTO().getMobile().equals(""))
		{
			String params[]  = {CustomUtil.getBundlMSGValue("user.mobile")} ;
			errorList.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		return errorList;
	}

	@Override
	public List<String> valideUpdate(UserDTO model) {
		List<String> errorList = new ArrayList<String>();
		if(model.getUserName()==null || model.getUserName().equals(""))
		{
			String params[]  = {CustomUtil.getBundlMSGValue("user.name")} ;
			errorList.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		
		if(model.getUserCode()==null || model.getUserCode().equals(""))
		{
			String params[]  = {CustomUtil.getBundlMSGValue("user.code")} ;
			errorList.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		
		if(model.getPassword()==null || model.getPassword().equals(""))
		{
			String params[]  = {CustomUtil.getBundlMSGValue("user.passowrd")} ;
			errorList.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		
		if(model.getActive()==null)
		{
			String params[]  = {CustomUtil.getBundlMSGValue("user.status.active")} ;
			errorList.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		
		if(model.getUserProfileDTO().getMobile()==null || model.getUserProfileDTO().getMobile().equals(""))
		{
			String params[]  = {CustomUtil.getBundlMSGValue("user.mobile")} ;
			errorList.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		return errorList;
	}

	@Override
	public List<String> valideDelete(UserDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(UserSearch critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getMergeMode(UserDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(UserDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDTO> getDataListProjection(UserSearch critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException {
		List<UserDTO> list = null;
		try {
			ProjectionList projectionList = Projections.projectionList(); 
			
			Projection userIdProj = Projections.property("userId"); 
			Projection userNameProj = Projections.property("userName"); 
			Projection userCodeProj = Projections.property("userCode"); 
			
			
			projectionList.add(userIdProj);
			projectionList.add(userNameProj);
			projectionList.add(userCodeProj);
			
			
			list = userDAO.getList(critiria,projectionList, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search customerDTO Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteUserRole(List<Long> userRoleIds) throws SegesaServiceException {
		try {
			
			if(userRoleIds !=null && userRoleIds.size()>0)
			{
				for(Long userRoleId : userRoleIds)
				{
					UserRoleDTO roleDTO = new UserRoleDTO();
					roleDTO.setUserRoleId(userRoleId);
					userRoleGroupService.deleteModel(roleDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SegesaServiceException("deleteUserRole Error :" + e);
		
		}
		
	}

	
	
	
}
