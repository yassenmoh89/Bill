package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.HouseholdsDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.HouseholdsDTO;

@Service
@Transactional(readOnly = true)
public class HouseholdsServiceImpl implements HouseholdsService{

	@Autowired
	HouseholdsDAO  householdsDAO;
	
	@Override
	public HouseholdsDTO saveModel(HouseholdsDTO model) throws SegesaServiceException {
		HouseholdsDTO householdsDTO = null;
		try {
			householdsDTO = householdsDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save householdsDAO Error :" + e);
		}
		return householdsDTO;
	}

	@Override
	public void deleteModel(HouseholdsDTO model) throws SegesaServiceException {
		
		try {
			householdsDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete householdsDAO Error :" + e);
		}
	}

	@Override
	public HouseholdsDTO updateModel(HouseholdsDTO model) throws SegesaServiceException {
		HouseholdsDTO householdsDTO = null;
		try {
			householdsDTO = householdsDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update householdsDAO Error :" + e);
		}
		return householdsDTO;
	}

	@Override
	public HouseholdsDTO getModelDTO(Long pk) throws SegesaServiceException {
		HouseholdsDTO householdsDTO = null;
		try {
			householdsDTO = householdsDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("delete householdsDAO Error :" + e);
		}
		return householdsDTO;
	}

	@Override
	public List<HouseholdsDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException {
		List<HouseholdsDTO> list = null;
		try {
			list = householdsDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search householdsDAO Error :" + e);
		}
		return list;
	}

	@Override
	public List<HouseholdsDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<HouseholdsDTO> list = null;
		try {
			list = householdsDAO.getList(null, start, pageSize, "houseHoldingTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search householdsDAO Error :" + e);
		}
		return list;
	}

	@Override
	public List<HouseholdsDTO> getDataList() throws SegesaServiceException {
		List<HouseholdsDTO> list = null;
		try {
			list = householdsDAO.getList(null, 0, 0, "houseHoldingTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search householdsDAO Error :" + e);
		}
		return list;
	}

	@Override
	public List<HouseholdsDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<HouseholdsDTO> list = null;
		try {
			list = householdsDAO.getList(null, 0, 0, "houseHoldingTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search householdsDAO Error :" + e);
		}
		return list;
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = householdsDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("search householdsDAO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(HouseholdsDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(HouseholdsDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(HouseholdsDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HouseholdsDTO getMergeMode(HouseholdsDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(HouseholdsDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	
	
}
