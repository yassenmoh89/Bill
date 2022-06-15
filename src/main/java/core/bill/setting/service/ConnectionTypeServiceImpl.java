package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.ConnectionTypeDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.ConnectionTypeDTO;

@Service
@Transactional(readOnly = true)
public class ConnectionTypeServiceImpl implements ConnectionTypeService{

	@Autowired
	ConnectionTypeDAO  connectionTypeDAO ;
	
	@Override
	public ConnectionTypeDTO saveModel(ConnectionTypeDTO model) throws SegesaServiceException {
		ConnectionTypeDTO connectionTypeDTO = null;
		try {
			connectionTypeDTO = connectionTypeDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save connectionTypeDTO Error :" + e);
		}
		return connectionTypeDTO;
	}

	@Override
	public void deleteModel(ConnectionTypeDTO model) throws SegesaServiceException {
		
		try {
			 connectionTypeDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete connectionTypeDTO Error :" + e);
		}
		
		
	}

	@Override
	public ConnectionTypeDTO updateModel(ConnectionTypeDTO model) throws SegesaServiceException {
		ConnectionTypeDTO connectionTypeDTO = null;
		try {
			connectionTypeDTO = connectionTypeDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update connectionTypeDTO Error :" + e);
		}
		return connectionTypeDTO;
	}

	@Override
	public ConnectionTypeDTO getModelDTO(Long pk) throws SegesaServiceException {
		ConnectionTypeDTO connectionTypeDTO = null;
		try {
			connectionTypeDTO = connectionTypeDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("get connectionTypeDTO Error :" + e);
		}
		return connectionTypeDTO;
	}

	@Override
	public List<ConnectionTypeDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException {
		
		List<ConnectionTypeDTO> list = null;
		try {
			list = connectionTypeDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search connectionTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ConnectionTypeDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<ConnectionTypeDTO> list = null;
		try {
			list = connectionTypeDAO.getList(null, start, pageSize, "connectionTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search connectionTypeDTO Error :" + e);
		}
		return list;

	}

	@Override
	public List<ConnectionTypeDTO> getDataList() throws SegesaServiceException {
		
		List<ConnectionTypeDTO> list = null;
		try {
			list = connectionTypeDAO.getList(null, 0, 0, "connectionTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search connectionTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ConnectionTypeDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<ConnectionTypeDTO> list = null;
		try {
			list = connectionTypeDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search connectionTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		
		Long  count = null;
		try {
			count = connectionTypeDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("get row number connectionTypeDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(ConnectionTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(ConnectionTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(ConnectionTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConnectionTypeDTO getMergeMode(ConnectionTypeDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(ConnectionTypeDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	

}
