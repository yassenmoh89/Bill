package core.bill.user.service;

import java.util.List;

import core.bill.common.exception.SegesaServiceException;
import core.bill.common.service.GenericService;
import core.bill.user.model.GroupSearch;
import core.bill.user.model.UserDTO;
import core.bill.user.model.UserRoleDTO;


public interface UserRoleGroupService extends GenericService<UserRoleDTO, GroupSearch> {
	public List<UserDTO> getUserListProjection(GroupSearch critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException;

}
