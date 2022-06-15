package core.bill.user.serviceProvider;

import core.bill.common.exception.SegesaServiceException;
import core.bill.user.model.SystemConfig;

public interface UserSharedService {
	
	public SystemConfig getSystemConfig(String key) throws SegesaServiceException;

}
