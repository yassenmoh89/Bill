package core.bill.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import core.bill.common.exception.SegesaServiceException;
import core.bill.user.dao.SystemConfigDAO;
import core.bill.user.model.SystemConfig;
import core.bill.user.model.SystemConfigSearch;

@Service("systemConfigService")
public class SystemConfigServiceImpl implements SystemConfigService {

	@Autowired
	SystemConfigDAO systemConfigDAO ; 
	
	@Override
	@Transactional(readOnly = false)
	public SystemConfig saveModel(SystemConfig model) throws SegesaServiceException {
		
		SystemConfig sysConfig = null;
		try {
			sysConfig = systemConfigDAO.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SegesaServiceException("Error save sysConfig : " + e);
		}
		return sysConfig;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteModel(SystemConfig model) throws SegesaServiceException {
		
		
		try {
			systemConfigDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error delete sysConfig : " + e);
		}
		
		
	}

	@Override
	@Transactional(readOnly = false)
	public SystemConfig updateModel(SystemConfig model) throws SegesaServiceException {
		
		SystemConfig sysConfig = null;
		try {
			sysConfig = systemConfigDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error Update sysConfig : " + e);
		}
		return sysConfig;
	}

	@Override
	@Transactional(readOnly = true)
	public SystemConfig getModelDTO(Long pk) throws SegesaServiceException {
		SystemConfig sysConfig = null;
		try {
			sysConfig = systemConfigDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("Error get sysConfig : " + e);
		}
		return sysConfig;
	}

	@Override
	@Transactional(readOnly = true)
	public List<SystemConfig> getDataList(SystemConfigSearch critiria, int start, int pageSize,
			String orderBy, Boolean desc) throws SegesaServiceException {
		
		List<SystemConfig> list = null;
		try {
			list = systemConfigDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search sysConfig : " + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<SystemConfig> getDataList(int start, int pageSize) throws SegesaServiceException {

		List<SystemConfig> list = null;
		try {
			list = systemConfigDAO.getList(null, start, pageSize, "id", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search sysConfig : " + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<SystemConfig> getDataList() throws SegesaServiceException {

		List<SystemConfig> list = null;
		try {
			list = systemConfigDAO.getList(null, 0, 0, "id", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search sysConfig : " + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<SystemConfig> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {

		List<SystemConfig> list = null;
		try {
			list = systemConfigDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search sysConfig : " + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getRowCount(SystemConfigSearch critiria) throws SegesaServiceException {

		Long count = null;
		try {
			count = systemConfigDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save sysConfig : " + e);
		}
		return count;
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> valideSave(SystemConfig model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> valideUpdate(SystemConfig model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> valideDelete(SystemConfig model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> valideSearch(SystemConfigSearch critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemConfig getMergeMode(SystemConfig model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(SystemConfig model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = true)
	public SystemConfig getSystemConfig(String key) throws SegesaServiceException {
		
			SystemConfig sysConfig = null;
			try {
				sysConfig = systemConfigDAO.getSystemConfig(key);
			} catch (Exception e) {
				e.printStackTrace();
				throw new SegesaServiceException("Error save sysConfig : " + e);
			}
			return sysConfig;
	}

}
