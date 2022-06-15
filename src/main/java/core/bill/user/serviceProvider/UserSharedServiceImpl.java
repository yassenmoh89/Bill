package core.bill.user.serviceProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.user.model.SystemConfig;
import core.bill.user.service.SystemConfigService;



@Service("userSharedService")
@Transactional(readOnly = true)
public class UserSharedServiceImpl implements UserSharedService {


	@Autowired
	SystemConfigService systemConfigService ; 
	
	@Override
	public SystemConfig getSystemConfig(String key) throws SegesaServiceException {
		SystemConfig sysConfig = null;
		try {
			sysConfig = systemConfigService.getSystemConfig(key);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SegesaServiceException("Error UserSharedService getSystemConfig: " + e);
		}
		return sysConfig;
	}

	
}
