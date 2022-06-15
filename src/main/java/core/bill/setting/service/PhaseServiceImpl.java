package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.PhaseDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.PhaseDTO;

@Service("phaseService")
@Transactional(readOnly = true)
public class PhaseServiceImpl implements PhaseService{

	@Autowired
	PhaseDAO phaseDAO ; 
	
	
	@Override
	public PhaseDTO saveModel(PhaseDTO model) throws SegesaServiceException {
		PhaseDTO phaseDTO = null;
		try {
			phaseDTO = phaseDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save phaseDTO Error :" + e);
		}
		return phaseDTO;
	}

	@Override
	public void deleteModel(PhaseDTO model) throws SegesaServiceException {
		
		try {
			phaseDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete phaseDTO Error :" + e);
		}
		
		
	}

	@Override
	public PhaseDTO updateModel(PhaseDTO model) throws SegesaServiceException {
		PhaseDTO phaseDTO = null;
		try {
			phaseDTO = phaseDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update phaseDTO Error :" + e);
		}
		return phaseDTO;
	}

	@Override
	public PhaseDTO getModelDTO(Long pk) throws SegesaServiceException {
		PhaseDTO phaseDTO = null;
		try {
			phaseDTO = phaseDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("get phaseDTO Error :" + e);
		}
		return phaseDTO;
	}

	@Override
	public List<PhaseDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<PhaseDTO> list = null;
		try {
			list = phaseDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search phaseDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<PhaseDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<PhaseDTO> list = null;
		try {
			list = phaseDAO.getList(null, start, pageSize, "phaseId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search phaseDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<PhaseDTO> getDataList() throws SegesaServiceException {
		List<PhaseDTO> list = null;
		try {
			list = phaseDAO.getList(null, 0, 0, "phaseId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("seach phaseDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<PhaseDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<PhaseDTO> list = null;
		try {
			list = phaseDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search phaseDTO Error :" + e);
		}
		return list;
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = phaseDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("get Row Account phaseDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(PhaseDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(PhaseDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(PhaseDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PhaseDTO getMergeMode(PhaseDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(PhaseDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	

}
