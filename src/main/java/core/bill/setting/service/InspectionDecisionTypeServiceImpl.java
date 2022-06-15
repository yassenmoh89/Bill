package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.InspectionDecisionTypeDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.InspectionDecisionTypeDTO;

@Service
@Transactional(readOnly = true)
public class InspectionDecisionTypeServiceImpl implements InspectionDecisionTypeService {

	@Autowired
	InspectionDecisionTypeDAO inspectionDecisionTypeDAO ; 
	
	
	@Override
	public InspectionDecisionTypeDTO saveModel(InspectionDecisionTypeDTO model) throws SegesaServiceException {
		InspectionDecisionTypeDTO inspectionDecisionTypeDTO = null;
		try {
			inspectionDecisionTypeDTO = inspectionDecisionTypeDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save inspectionDecisionTypeDAO Error :" + e);
		}
		return inspectionDecisionTypeDTO;
	}

	@Override
	public void deleteModel(InspectionDecisionTypeDTO model) throws SegesaServiceException {
		
		try {
			inspectionDecisionTypeDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete inspectionDecisionTypeDAO Error :" + e);
		}
		
		
	}

	@Override
	public InspectionDecisionTypeDTO updateModel(InspectionDecisionTypeDTO model) throws SegesaServiceException {
		InspectionDecisionTypeDTO inspectionDecisionTypeDTO = null;
		try {
			inspectionDecisionTypeDTO = inspectionDecisionTypeDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update inspectionDecisionTypeDAO Error :" + e);
		}
		return inspectionDecisionTypeDTO;
	}

	@Override
	public InspectionDecisionTypeDTO getModelDTO(Long pk) throws SegesaServiceException {
		InspectionDecisionTypeDTO inspectionDecisionTypeDTO = null;
		try {
			inspectionDecisionTypeDTO = inspectionDecisionTypeDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("get inspectionDecisionTypeDAO Error :" + e);
		}
		return inspectionDecisionTypeDTO;
	}

	@Override
	public List<InspectionDecisionTypeDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize,
			String orderBy, Boolean desc) throws SegesaServiceException {
		
		List<InspectionDecisionTypeDTO> list = null;
		try {
			list = inspectionDecisionTypeDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search inspectionDecisionTypeDAO Error :" + e);
		}
		return list;
	}

	@Override
	public List<InspectionDecisionTypeDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		
		List<InspectionDecisionTypeDTO> list = null;
		try {
			list = inspectionDecisionTypeDAO.getList(null, start, pageSize, "decisionTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search inspectionDecisionTypeDAO Error :" + e);
		}
		return list;
	}

	@Override
	public List<InspectionDecisionTypeDTO> getDataList() throws SegesaServiceException {
		List<InspectionDecisionTypeDTO> list = null;
		try {
			list = inspectionDecisionTypeDAO.getList(null, 0, 0, "decisionTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search inspectionDecisionTypeDAO Error :" + e);
		}
		return list;
	}

	@Override
	public List<InspectionDecisionTypeDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		
		List<InspectionDecisionTypeDTO> list = null;
		try {
			list = inspectionDecisionTypeDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search inspectionDecisionTypeDAO Error :" + e);
		}
		return list;
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = inspectionDecisionTypeDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("search inspectionDecisionTypeDAO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(InspectionDecisionTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(InspectionDecisionTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(InspectionDecisionTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InspectionDecisionTypeDTO getMergeMode(InspectionDecisionTypeDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(InspectionDecisionTypeDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	
}
