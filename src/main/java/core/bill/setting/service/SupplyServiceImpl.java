package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.SupplyDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.SupplyDTO;

@Service("supplyService")
@Transactional(readOnly = true)
public class SupplyServiceImpl implements SupplyService {

	@Autowired
	SupplyDAO supplyDAO ;
	
	@Override
	public SupplyDTO saveModel(SupplyDTO model) throws SegesaServiceException {
		SupplyDTO supplyDTO = null;
		try {
			supplyDTO = supplyDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save supplyDTO Error :" + e);
		}
		return supplyDTO;
	}

	@Override
	public void deleteModel(SupplyDTO model) throws SegesaServiceException {
		
		try {
			supplyDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete supplyDTO Error :" + e);
		}
		
		
	}

	@Override
	public SupplyDTO updateModel(SupplyDTO model) throws SegesaServiceException {
		SupplyDTO supplyDTO = null;
		try {
			supplyDTO = supplyDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update supplyDTO Error :" + e);
		}
		return supplyDTO;
	}

	@Override
	public SupplyDTO getModelDTO(Long pk) throws SegesaServiceException {
		SupplyDTO supplyDTO = null;
		try {
			supplyDTO = supplyDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("get supplyDTO Error :" + e);
		}
		return supplyDTO;
	}

	@Override
	public List<SupplyDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<SupplyDTO> list = null;
		try {
			list = supplyDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search supplyDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<SupplyDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<SupplyDTO> list = null;
		try {
			list = supplyDAO.getList(null, start, pageSize, "supplyId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search supplyDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<SupplyDTO> getDataList() throws SegesaServiceException {
		List<SupplyDTO> list = null;
		try {
			list = supplyDAO.getList(null, 0, 0, "supplyId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search supplyDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<SupplyDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<SupplyDTO> list = null;
		try {
			list = supplyDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search supplyDTO Error :" + e);
		}
		return list;
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = supplyDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("search supplyDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(SupplyDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(SupplyDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(SupplyDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SupplyDTO getMergeMode(SupplyDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(SupplyDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	

}
