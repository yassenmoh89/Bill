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
import core.bill.user.dao.GroupDAO;
import core.bill.user.model.GroupDTO;
import core.bill.user.model.GroupSearch;


@Service("groupService")
public class GroupServiceImpl implements GroupService{

	@Autowired
	GroupDAO groupDAO ; 
	
	@Override
	@Transactional(readOnly = false)
	public GroupDTO saveModel(GroupDTO model) throws SegesaServiceException {
		GroupDTO groupDTO = null;
		try {
			groupDTO = groupDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save groupDTO : " + e);
		}

		return groupDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteModel(GroupDTO model) throws SegesaServiceException {
		
		try {
			groupDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error delete groupDTO : " + e);
		}

		
		
	}

	@Override
	@Transactional(readOnly = false)
	public GroupDTO updateModel(GroupDTO model) throws SegesaServiceException {
		GroupDTO groupDTO = null;
		try {
			groupDTO = groupDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error update groupDTO : " + e);
		}

		return groupDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public GroupDTO getModelDTO(Long pk) throws SegesaServiceException {
		GroupDTO groupDTO = null;
		try {
			groupDTO = groupDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("Error get groupDTO : " + e);
		}
		return groupDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<GroupDTO> getDataList(GroupSearch critiria, int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<GroupDTO> list = null;
		try {
			
			ProjectionList projectionList = Projections.projectionList(); 
			
			Projection groupIdProj = Projections.property("groupId"); 
			Projection groupNameProj = Projections.property("groupName"); 
			Projection groupCodeProj = Projections.property("groupCode"); 
			Projection statusProj = Projections.property("status"); 
			
			projectionList.add(groupIdProj);
			projectionList.add(groupNameProj);
			projectionList.add(groupCodeProj);
			projectionList.add(statusProj);
			
			list = groupDAO.getList(critiria,projectionList, start, pageSize, orderBy, desc);
			
		} catch (Exception e) {
			throw new SegesaServiceException("Error search groupDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<GroupDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<GroupDTO> list = null;
		try {
			list = groupDAO.getList(null, start, pageSize, "groupId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search groupDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<GroupDTO> getDataList() throws SegesaServiceException {
		List<GroupDTO> list = null;
		try {
			list = groupDAO.getList(null, 0, 0, "groupId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search groupDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<GroupDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<GroupDTO> list = null;
		try {
			list = groupDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search groupDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getRowCount(GroupSearch critiria) throws SegesaServiceException {
		
		Long count = null;
		try {
			count = groupDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("Error row count groupDTO : " + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(GroupDTO model) {
		List<String> error = new ArrayList<String>();

		if (model.getGroupName() == null || model.getGroupName().equals("")) {
			String[] params = { CustomUtil.getBundlMSGValue("group.name") };
			error.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		
		if (model.getGroupCode() == null || model.getGroupCode().equals("")) {
			String[] params = { CustomUtil.getBundlMSGValue("group.code") };

			error.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		
		if (model.getStatus() == null || model.getStatus().equals("")) {
			String[] params = { CustomUtil.getBundlMSGValue("role.status") };

			error.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		
		return error;
	}

	@Override
	public List<String> valideUpdate(GroupDTO model) {
		List<String> error = new ArrayList<String>();
		
		if(model==null)
		{
			error.add(CustomUtil.getBundlMSGValue("se.common.invalid.data.form"));
		}

		if (model.getGroupName() == null || model.getGroupName().equals("")) {
			String[] params = { CustomUtil.getBundlMSGValue("group.name") };
			error.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		
		if (model.getGroupCode() == null || model.getGroupCode().equals("")) {
			String[] params = { CustomUtil.getBundlMSGValue("group.code") };

			error.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		
		if (model.getStatus() == null || model.getStatus().equals("")) {
			String[] params = { CustomUtil.getBundlMSGValue("role.status") };

			error.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		
		return error;
	}

	@Override
	public List<String> valideDelete(GroupDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(GroupSearch critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public GroupDTO getMergeMode(GroupDTO model) throws SegesaServiceException {
		GroupDTO groupDTO = null;
		try {
			groupDTO = groupDAO.merge(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error merge groupDTO : " + e);
		}

		return groupDTO;

	}

	@Override
	@Transactional(readOnly = false)
	public void saveOrUpdateMode(GroupDTO model) throws SegesaServiceException {

		try {
			groupDAO.saveOrUpdate(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error saveOrUpdate groupDTO : " + e);
		}

		
	}

	

}
