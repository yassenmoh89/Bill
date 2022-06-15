package core.bill.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.user.dao.GroupRoleDAO;
import core.bill.user.model.GroupRoleDTO;
import core.bill.user.model.GroupSearch;

@Service("groupRoleService")
public class GroupRoleServiceImpl implements GroupRoleService{

	@Autowired
	GroupRoleDAO groupRoleDAO ; 
	
	@Override
	@Transactional(readOnly = false)
	public GroupRoleDTO saveModel(GroupRoleDTO model) throws SegesaServiceException {
		GroupRoleDTO groupRoleDTO = null;
		try {
			groupRoleDTO = groupRoleDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save groupRoleDTO : " + e);
		}

		return groupRoleDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteModel(GroupRoleDTO model) throws SegesaServiceException {
		
		try {
			groupRoleDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error delete groupRoleDTO : " + e);
		}

		
		
	}

	@Override
	@Transactional(readOnly = false)
	public GroupRoleDTO updateModel(GroupRoleDTO model) throws SegesaServiceException {
		GroupRoleDTO groupRoleDTO = null;
		try {
			groupRoleDTO = groupRoleDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error update groupRoleDTO : " + e);
		}

		return groupRoleDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public GroupRoleDTO getModelDTO(Long pk) throws SegesaServiceException {
		GroupRoleDTO groupRoleDTO = null;
		try {
			groupRoleDTO = groupRoleDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("Error get groupRoleDTO : " + e);
		}

		return groupRoleDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public GroupRoleDTO getMergeMode(GroupRoleDTO model) throws SegesaServiceException {
		GroupRoleDTO groupRoleDTO = null;
		try {
			groupRoleDTO = groupRoleDAO.merge(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error merge groupRoleDTO : " + e);
		}

		return groupRoleDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void saveOrUpdateMode(GroupRoleDTO model) throws SegesaServiceException {
		
		try {
			groupRoleDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error saveOrUpdateMode groupRoleDTO : " + e);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public List<GroupRoleDTO> getDataList(GroupSearch critiria, int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<GroupRoleDTO> list = null;
		try {
			list = groupRoleDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search GroupRoleDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<GroupRoleDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<GroupRoleDTO> list = null;
		try {
			list = groupRoleDAO.getList(null, start, pageSize, "groupRoleId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search GroupRoleDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<GroupRoleDTO> getDataList() throws SegesaServiceException {
		List<GroupRoleDTO> list = null;
		try {
			list = groupRoleDAO.getList(null, 0, 0, "groupRoleId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search GroupRoleDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<GroupRoleDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<GroupRoleDTO> list = null;
		try {
			list = groupRoleDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search GroupRoleDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getRowCount(GroupSearch critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = groupRoleDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search GroupRoleDTO : " + e);
		}

		return count;
	}

	@Override
	public List<String> valideSave(GroupRoleDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(GroupRoleDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(GroupRoleDTO model) {
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
	public void deleteModel(List<GroupRoleDTO> models) throws SegesaServiceException {
		try {
		if(models!=null && models.size()>0)
		{
			for(GroupRoleDTO grRoleDTO:models)
			{
				if(grRoleDTO.getGroupRoleId()!=null)
				deleteModel(grRoleDTO);
			}
		}
		} catch (Exception e) {
			throw new SegesaServiceException("Error delete GroupRoleDTO : " + e);
		}
		
	}

}
