package core.bill.user.service;

import java.util.List;

import core.bill.common.exception.SegesaServiceException;
import core.bill.common.service.GenericService;
import core.bill.user.model.UserSearch;
import core.bill.user.model.UserSessionDTO;

public interface UserSessionService extends GenericService<UserSessionDTO, UserSearch>{
	public List<UserSessionDTO> getDataListProjection(UserSearch critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException;

}
