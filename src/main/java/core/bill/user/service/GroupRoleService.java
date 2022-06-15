package core.bill.user.service;

import java.util.List;

import core.bill.common.exception.SegesaServiceException;
import core.bill.common.service.GenericService;
import core.bill.user.model.GroupRoleDTO;
import core.bill.user.model.GroupSearch;

public interface GroupRoleService extends GenericService<GroupRoleDTO, GroupSearch>{
	public void deleteModel(List<GroupRoleDTO> models) throws SegesaServiceException;
}
