package core.bill.user.service;

import core.bill.common.exception.SegesaServiceException;
import core.bill.common.service.GenericService;
import core.bill.user.model.SystemConfig;
import core.bill.user.model.SystemConfigSearch;

public interface SystemConfigService extends GenericService<SystemConfig, SystemConfigSearch> {
	public SystemConfig getSystemConfig(String key) throws SegesaServiceException;
}
