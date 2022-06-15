package core.bill.study.service;

import java.util.List;

import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.study.dao.StudyApplicationDetailsDAO;
import core.bill.study.model.StudyApplicationDetailsDTO;
import core.bill.study.model.StudyApplicationSearch;

@Service("studyApplicationDetailsService")
public class StudyApplicationDetailsServiceImpl implements StudyApplicationDetailsService{

	@Autowired
	StudyApplicationDetailsDAO studyDetailsDAO;
	
	@Override
	@Transactional(readOnly = false)
	public StudyApplicationDetailsDTO saveModel(StudyApplicationDetailsDTO model) throws SegesaServiceException {
		StudyApplicationDetailsDTO studyDetailsDTO = null;
		try {
			studyDetailsDTO = studyDetailsDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save studyDetailsDTO : " + e);
		}

		return studyDetailsDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteModel(StudyApplicationDetailsDTO model) throws SegesaServiceException {

		try {
			studyDetailsDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error delete studyDetailsDTO : " + e);
		}

		
	}
	
	@Override
	@Transactional(readOnly = false)
	public void deleteList(List<StudyApplicationDetailsDTO> model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		if(model!=null) {
			
				for(StudyApplicationDetailsDTO mod:model) {
					deleteModel(mod);
				}
			
		}
		
	}

	@Override
	@Transactional(readOnly = false)
	public StudyApplicationDetailsDTO updateModel(StudyApplicationDetailsDTO model) throws SegesaServiceException {
		
		StudyApplicationDetailsDTO studyDetailsDTO = null;
		try {
			studyDetailsDTO = studyDetailsDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error update studyDetailsDTO : " + e);
		}

		return studyDetailsDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public StudyApplicationDetailsDTO getModelDTO(Long pk) throws SegesaServiceException {
		StudyApplicationDetailsDTO studyDetailsDTO = null;
		try {
			studyDetailsDTO = studyDetailsDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("Error get studyDetailsDTO : " + e);
		}

		return studyDetailsDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public StudyApplicationDetailsDTO getMergeMode(StudyApplicationDetailsDTO model) throws SegesaServiceException {
		
		StudyApplicationDetailsDTO studyDetailsDTO = null;
		try {
			studyDetailsDTO = studyDetailsDAO.merge(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error merge studyDetailsDTO : " + e);
		}

		return studyDetailsDTO;
		
	}

	@Override
	@Transactional(readOnly = false)
	public void saveOrUpdateMode(StudyApplicationDetailsDTO model) throws SegesaServiceException {
		

		try {
			 studyDetailsDAO.saveOrUpdate(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error merge studyDetailsDTO : " + e);
		}

		
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudyApplicationDetailsDTO> getDataList(StudyApplicationSearch critiria, int start, int pageSize,
			String orderBy, Boolean desc) throws SegesaServiceException {
		List<StudyApplicationDetailsDTO> list = null;
		try {
			
			ProjectionList projectionList = Projections.projectionList(); 
			
			Projection groupIdProj = Projections.property("studyId"); 
			Projection groupNameProj = Projections.property("studyId"); 
//			Projection groupCodeProj = Projections.property("itemCode"); 
//			Projection statusProj = Projections.property("status"); 
//			Projection priceProj = Projections.property("itemPrice"); 
			
			projectionList.add(groupIdProj);
			projectionList.add(groupNameProj);
//			projectionList.add(groupCodeProj);
//			projectionList.add(statusProj);
//			projectionList.add(priceProj);
			
			list = studyDetailsDAO.getList(critiria,projectionList, start, pageSize, orderBy, desc);
			
		} catch (Exception e) {
			throw new SegesaServiceException("Error search studyDetailsDAO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudyApplicationDetailsDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		
		List<StudyApplicationDetailsDTO> list = null;
		try {
			list = studyDetailsDAO.getList(null, start, pageSize, "studyId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search studyDetailsDAO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudyApplicationDetailsDTO> getDataList() throws SegesaServiceException {
		
		List<StudyApplicationDetailsDTO> list = null;
		try {
			list = studyDetailsDAO.getList(null, 0, 0, "studyId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search studyDetailsDAO : " + e);
		}

		return list;
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudyApplicationDetailsDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<StudyApplicationDetailsDTO> list = null;
		try {
			list = studyDetailsDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search studyDetailsDAO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getRowCount(StudyApplicationSearch critiria) throws SegesaServiceException {
		
		Long count = null;
		try {
			count = studyDetailsDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("Error row count studyDetailsDAO : " + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(StudyApplicationDetailsDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(StudyApplicationDetailsDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(StudyApplicationDetailsDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(StudyApplicationSearch critiria) {
		// TODO Auto-generated method stub
		return null;
	}

}
