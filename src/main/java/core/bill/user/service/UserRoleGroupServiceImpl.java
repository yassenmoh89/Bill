package core.bill.user.service;

import java.util.List;

import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.user.dao.UserRoleGroupDAO;
import core.bill.user.model.GroupSearch;
import core.bill.user.model.UserDTO;
import core.bill.user.model.UserRoleDTO;


@Service("userRoleGroupService")
public class UserRoleGroupServiceImpl implements UserRoleGroupService{

	@Autowired
	UserRoleGroupDAO userRoleGroupDAO;
	
	@Override
	public UserRoleDTO saveModel(UserRoleDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteModel(UserRoleDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserRoleDTO updateModel(UserRoleDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRoleDTO getModelDTO(Long pk) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRoleDTO getMergeMode(UserRoleDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(UserRoleDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = false)
	public List<UserRoleDTO> getDataList(GroupSearch critiria, int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<UserRoleDTO> list=null;
		try {
			
			list=userRoleGroupDAO.getList(critiria, start, pageSize, "userRoleId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search UserRoleDTO : " + e);
		}
		return list;
	}

	@Override
	public List<UserRoleDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserRoleDTO> getDataList() throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserRoleDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getRowCount(GroupSearch critiria) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSave(UserRoleDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(UserRoleDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(UserRoleDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(GroupSearch critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getUserListProjection(GroupSearch critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException {
		List<UserDTO> list=null;
		try {
			ProjectionList proList = Projections.projectionList();
			Projection userIdPro= Projections.property("userDTO.userId");
			Projection userNamePro= Projections.property("userDTO.userName");
			Projection userCodePro= Projections.property("userDTO.userCode");
			Projection userTelPro= Projections.property("userDTO.active");
			
			proList.add(userIdPro);
			proList.add(userNamePro);
			proList.add(userCodePro);
			proList.add(userTelPro);
			
			list=userRoleGroupDAO.getUserList(critiria,proList, start, pageSize, "userRoleId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search UserRoleDTO : " + e);
		}
		return list;
	}

}
