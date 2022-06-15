package core.bill.user.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.user.dao.GroupResourceDAO;
import core.bill.user.model.GroupResourceDTO;
import core.bill.user.model.GroupSearch;

@Service("groupResourceService")
public class GroupResourceServiceImpl implements GroupResourceService{

	@Autowired
	GroupResourceDAO groupResourceDAO;

	@Override
	public GroupResourceDTO saveModel(GroupResourceDTO model) throws SegesaServiceException {
	
		GroupResourceDTO groupResourceDTO = null;
			try {
				groupResourceDTO = groupResourceDAO.save(model);
			} catch (Exception e) {
				throw new SegesaServiceException("Error save groupResourceDAO : " + e);
			}

			return groupResourceDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteModel(GroupResourceDTO model) throws SegesaServiceException {
		
		try {
			groupResourceDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save groupResourceDAO : " + e);
		}

		
		
	}

	@Override
	public GroupResourceDTO updateModel(GroupResourceDTO model) throws SegesaServiceException {
		GroupResourceDTO groupResourceDTO = null;
		try {
			groupResourceDTO = groupResourceDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error update groupResourceDAO : " + e);
		}

		return groupResourceDTO;
	}

	@Override
	public GroupResourceDTO getModelDTO(Long pk) throws SegesaServiceException {
		GroupResourceDTO groupResourceDTO = null;
		try {
			groupResourceDTO = groupResourceDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("Error get groupResourceDAO : " + e);
		}

		return groupResourceDTO;
	}

	@Override
	public GroupResourceDTO getMergeMode(GroupResourceDTO model) throws SegesaServiceException {
		GroupResourceDTO groupResourceDTO = null;
		try {
			groupResourceDTO = groupResourceDAO.merge(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error merge groupResourceDAO : " + e);
		}

		return groupResourceDTO;
	}

	@Override
	public void saveOrUpdateMode(GroupResourceDTO model) throws SegesaServiceException {
	
		try {
			groupResourceDAO.saveOrUpdate(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save or update groupResourceDAO : " + e);
		}

		
		
	}

	@Override
	public List<GroupResourceDTO> getDataList(GroupSearch critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException {
		List<GroupResourceDTO> list = null;
		try {
			list = groupResourceDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search groupDTO : " + e);
		}

		return list;
	}

	@Override
	public List<GroupResourceDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<GroupResourceDTO> list = null;
		try {
			list = groupResourceDAO.getList(null, start, pageSize, "groupResourceId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search groupDTO : " + e);
		}

		return list;
	}

	@Override
	public List<GroupResourceDTO> getDataList() throws SegesaServiceException {
		List<GroupResourceDTO> list = null;
		try {
			list = groupResourceDAO.getList(null, 0, 0, "groupResourceId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search groupDTO : " + e);
		}

		return list;
	}

	@Override
	public List<GroupResourceDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<GroupResourceDTO> list = null;
		try {
			list = groupResourceDAO.getList(null, start, pageSize, "groupResourceId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search groupDTO : " + e);
		}

		return list;
	}

	@Override
	public Long getRowCount(GroupSearch critiria) throws SegesaServiceException {
		
		Long count = null;
		try {
			count = groupResourceDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search groupDTO : " + e);
		}

		return count;
	}

	@Override
	public List<String> valideSave(GroupResourceDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(GroupResourceDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(GroupResourceDTO model) {
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
	public void deleteModel(List<GroupResourceDTO> model) throws SegesaServiceException {

		if(model!=null && model.size()>0)
		{
			for(GroupResourceDTO grResDTO:model)
			{
				if(grResDTO.getGroupResourceId()!=null)
				{
					deleteModel(grResDTO);
				}
			}
		}
		
	}
	
}
