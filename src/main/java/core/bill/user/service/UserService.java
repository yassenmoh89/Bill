package core.bill.user.service;

import java.util.List;

import core.bill.common.exception.SegesaServiceException;
import core.bill.common.service.GenericService;
import core.bill.user.model.UserDTO;
import core.bill.user.model.UserSearch;

public interface UserService extends GenericService<UserDTO, UserSearch> {
	
	public List<UserDTO> getDataListProjection(UserSearch critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException;
	
	public void deleteUserRole(List<Long> userRoleIds) throws SegesaServiceException;;
	
}
