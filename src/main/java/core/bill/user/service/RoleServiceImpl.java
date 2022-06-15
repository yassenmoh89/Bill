package core.bill.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.util.CustomUtil;
import core.bill.user.dao.RoleDAO;
import core.bill.user.model.RoleDTO;
import core.bill.user.model.RoleSearch;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDAO roleDAO;

	@Override
	@Transactional(readOnly = false)
	public RoleDTO saveModel(RoleDTO model) throws SegesaServiceException {
		RoleDTO roleDTO = null;
		try {
			roleDTO = roleDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save roleDTO : " + e);
		}

		return roleDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteModel(RoleDTO model) throws SegesaServiceException {

		try {
			roleDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error delete roleDTO : " + e);
		}

	}

	@Override
	@Transactional(readOnly = false)
	public RoleDTO updateModel(RoleDTO model) throws SegesaServiceException {
		RoleDTO roleDTO = null;
		try {
			roleDTO = roleDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error update roleDTO : " + e);
		}

		return roleDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public RoleDTO getModelDTO(Long pk) throws SegesaServiceException {
		RoleDTO roleDTO = null;
		try {
			roleDTO = roleDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("Error get roleDTO : " + e);
		}

		return roleDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<RoleDTO> getDataList(RoleSearch critiria, int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<RoleDTO> list = null;
		try {
			list = roleDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search roleDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<RoleDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<RoleDTO> list = null;
		try {
			list = roleDAO.getList(null, start, pageSize, "roleId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search roleDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<RoleDTO> getDataList() throws SegesaServiceException {
		List<RoleDTO> list = null;
		try {
			list = roleDAO.getList(null, 0, 0, "roleId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search roleDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<RoleDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<RoleDTO> list = null;
		try {
			list = roleDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search roleDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getRowCount(RoleSearch critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = roleDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("Error row count roleDTO : " + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(RoleDTO model) {
		List<String> error = new ArrayList<String>();

		if (model.getRoleName() == null || model.getRoleName().equals("")) {
			String[] params = { CustomUtil.getBundlMSGValue("role.name") };
			error.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		
		if (model.getRoleCode() == null || model.getRoleCode().equals("")) {
			String[] params = { CustomUtil.getBundlMSGValue("role.code") };

			error.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		return error;
	}

	@Override
	public List<String> valideUpdate(RoleDTO model) {
		List<String> error = new ArrayList<String>();
		if(model==null)
		{
			error.add(CustomUtil.getBundlMSGValue("se.common.invalid.data.form"));
			return error; 
		}
		
		if (model.getRoleName() == null || model.getRoleName().equals("")) {
			String[] params = { CustomUtil.getBundlMSGValue("role.name") };
			error.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		
		if (model.getStatus() == null || model.getStatus().equals("")) {
			String[] params = { CustomUtil.getBundlMSGValue("role.status") };

			error.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		return error;
	}

	@Override
	public List<String> valideDelete(RoleDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(RoleSearch critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleDTO getMergeMode(RoleDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(RoleDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	

}
