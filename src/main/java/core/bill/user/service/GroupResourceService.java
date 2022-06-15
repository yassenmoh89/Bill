package core.bill.user.service;

import java.util.List;

import core.bill.common.exception.SegesaServiceException;
import core.bill.common.service.GenericService;
import core.bill.user.model.GroupResourceDTO;
import core.bill.user.model.GroupSearch;

public interface GroupResourceService extends GenericService<GroupResourceDTO, GroupSearch>{
	public void deleteModel(List<GroupResourceDTO> model) throws SegesaServiceException ;
}
