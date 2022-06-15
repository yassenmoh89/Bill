package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.CircuitsDAO;
import core.bill.setting.model.CircuitsDTO;
import core.bill.setting.model.CommonSearchDTO;

@Service
@Transactional(readOnly = true)

public class CircuitsServiceImpl implements CircuitsService {

	@Autowired
	CircuitsDAO circuitsDAO;

	@Override
	public CircuitsDTO saveModel(CircuitsDTO model) throws SegesaServiceException {
		CircuitsDTO circuitsDTO = null;
		try {
			circuitsDTO = circuitsDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save circuitsDTO Error :" + e);
		}
		return circuitsDTO;
	}

	@Override
	public void deleteModel(CircuitsDTO model) throws SegesaServiceException {

		try {
			circuitsDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete circuitsDTO Error :" + e);
		}

	}

	@Override
	public CircuitsDTO updateModel(CircuitsDTO model) throws SegesaServiceException {

		CircuitsDTO circuitsDTO = null;
		try {
			circuitsDTO = circuitsDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update circuitsDTO Error :" + e);
		}
		return circuitsDTO;
	}

	@Override
	public CircuitsDTO getModelDTO(Long pk) throws SegesaServiceException {
		CircuitsDTO circuitsDTO = null;
		try {
			circuitsDTO = circuitsDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("get circuitsDTO Error :" + e);
		}
		return circuitsDTO;
	}

	@Override
	public List<CircuitsDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException {
		List<CircuitsDTO> list = null;
		try {
			list = circuitsDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search circuitsDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<CircuitsDTO> getDataList(int start, int pageSize) throws SegesaServiceException {

		List<CircuitsDTO> list = null;
		try {
			list = circuitsDAO.getList(null, start, pageSize, "circuitId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search circuitsDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<CircuitsDTO> getDataList() throws SegesaServiceException {

		List<CircuitsDTO> list = null;
		try {
			list = circuitsDAO.getList(null, 0, 0, "circuitId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search circuitsDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<CircuitsDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<CircuitsDTO> list = null;
		try {
			list = circuitsDAO.getList(null, 0, 0, "circuitId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search circuitsDTO Error :" + e);
		}
		return list;

	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = circuitsDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("search circuitsDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(CircuitsDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(CircuitsDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(CircuitsDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CircuitsDTO getMergeMode(CircuitsDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(CircuitsDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	

	
}
