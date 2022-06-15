package core.bill.user.dao;

import core.bill.common.dao.GenericDAO;
import core.bill.user.model.SystemConfig;
import core.bill.user.model.SystemConfigSearch;

public interface SystemConfigDAO extends GenericDAO<SystemConfig, SystemConfigSearch> {

	public SystemConfig getSystemConfig(String key); 
}
