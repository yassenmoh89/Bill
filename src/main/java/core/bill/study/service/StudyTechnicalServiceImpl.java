package core.bill.study.service;

import java.util.List;

import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.study.dao.StudyTechnicalDAO;
import core.bill.study.model.StudyTechnicalDTO;
import core.bill.study.model.TechnicalSearch;

@Service("studyTechnicalService")
public class StudyTechnicalServiceImpl implements StudyTechnicalService{

	@Autowired
	StudyTechnicalDAO studyTechnicalDAO;
	
	@Override
	@Transactional(readOnly = false)
	public StudyTechnicalDTO saveModel(StudyTechnicalDTO model) throws SegesaServiceException {
		StudyTechnicalDTO technicalDTO = null;
		try {
			technicalDTO = studyTechnicalDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save technicalDTO : " + e);
		}

		return technicalDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteModel(StudyTechnicalDTO model) throws SegesaServiceException {
		try {
			studyTechnicalDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save technicalDTO : " + e);
		}
		
	}

	@Override
	@Transactional(readOnly = false)
	public StudyTechnicalDTO updateModel(StudyTechnicalDTO model) throws SegesaServiceException {
		StudyTechnicalDTO technicalDTO = null;
		try {
			technicalDTO = studyTechnicalDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save technicalDTO : " + e);
		}

		return technicalDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public StudyTechnicalDTO getModelDTO(Long pk) throws SegesaServiceException {
		StudyTechnicalDTO technicalDTO = null;
		try {
			technicalDTO = studyTechnicalDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save technicalDTO : " + e);
		}

		return technicalDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public StudyTechnicalDTO getMergeMode(StudyTechnicalDTO model) throws SegesaServiceException {
		StudyTechnicalDTO technicalDTO = null;
		try {
			technicalDTO = studyTechnicalDAO.merge(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save technicalDTO : " + e);
		}

		return technicalDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void saveOrUpdateMode(StudyTechnicalDTO model) throws SegesaServiceException {
		
		try {
			studyTechnicalDAO.saveOrUpdate(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save technicalDTO : " + e);
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudyTechnicalDTO> getDataList(TechnicalSearch critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException {
		List<StudyTechnicalDTO> list = null;
		try {
			
			ProjectionList projectionList = Projections.projectionList(); 
			
			Projection groupIdProj = Projections.property("technicalId"); 
			Projection groupNameProj = Projections.property("technicalName"); 
			Projection groupCodeProj = Projections.property("technicalCode"); 
			Projection statusProj = Projections.property("status"); 
			Projection phoneProj = Projections.property("phone"); 
			
			projectionList.add(groupIdProj);
			projectionList.add(groupNameProj);
			projectionList.add(groupCodeProj);
			projectionList.add(statusProj);
			projectionList.add(phoneProj);
			
			list = studyTechnicalDAO.getList(critiria,projectionList, start, pageSize, orderBy, desc);
			
		} catch (Exception e) {
			throw new SegesaServiceException("Error search technicalDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudyTechnicalDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<StudyTechnicalDTO> list = null;
		try {
			list = studyTechnicalDAO.getList(null, start, pageSize, "technicalId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search technicalDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudyTechnicalDTO> getDataList() throws SegesaServiceException {
		List<StudyTechnicalDTO> list = null;
		try {
			list = studyTechnicalDAO.getList(null, 0, 0, "technicalId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search itemsDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudyTechnicalDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<StudyTechnicalDTO> list = null;
		try {
			list = studyTechnicalDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search technicalDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getRowCount(TechnicalSearch critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = studyTechnicalDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("Error row count technicalDTO : " + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(StudyTechnicalDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(StudyTechnicalDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(StudyTechnicalDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(TechnicalSearch critiria) {
		// TODO Auto-generated method stub
		return null;
	}

}
