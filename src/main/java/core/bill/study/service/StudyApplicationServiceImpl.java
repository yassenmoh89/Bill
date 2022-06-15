package core.bill.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.study.dao.StudyApplicationDAO;
import core.bill.study.model.StudyApplicationDTO;
import core.bill.study.model.StudyApplicationSearch;

@Service("studyApplicationService")
public class StudyApplicationServiceImpl implements StudyApplicationService {

	@Autowired
	StudyApplicationDAO studyApplicationDAO;
	
	
	@Override
	@Transactional(readOnly = false)
	public StudyApplicationDTO saveModel(StudyApplicationDTO model) throws SegesaServiceException {
		
		StudyApplicationDTO studyAppDTO = null;
		try {
			studyAppDTO = studyApplicationDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save studyAppDTO : " + e);
		}

		return studyAppDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteModel(StudyApplicationDTO model) throws SegesaServiceException {
		try {
			studyApplicationDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error delete deleteDTO : " + e);
		}
		
	}

	@Override
	@Transactional(readOnly = false)
	public StudyApplicationDTO updateModel(StudyApplicationDTO model) throws SegesaServiceException {
		StudyApplicationDTO studyAppDTO = null;
		try {
			studyAppDTO = studyApplicationDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error update studyAppDTO : " + e);
		}

		return studyAppDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public StudyApplicationDTO getModelDTO(Long pk) throws SegesaServiceException {
	
		StudyApplicationDTO studyAppDTO = null;
		System.out.println("service "+pk);
		try {
			studyAppDTO = studyApplicationDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("Error get studyAppDTO : " + e);
		}

		return studyAppDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public StudyApplicationDTO getMergeMode(StudyApplicationDTO model) throws SegesaServiceException {
		StudyApplicationDTO studyAppDTO = null;
		try {
			studyAppDTO = studyApplicationDAO.merge(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error merge studyAppDTO : " + e);
		}

		return studyAppDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void saveOrUpdateMode(StudyApplicationDTO model) throws SegesaServiceException {
	
		try {
			 studyApplicationDAO.saveOrUpdate(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error update studyAppDTO : " + e);
		}

		
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudyApplicationDTO> getDataList(StudyApplicationSearch critiria, int start, int pageSize,
			String orderBy, Boolean desc) throws SegesaServiceException {
		List<StudyApplicationDTO> list = null;
		try {
			
			list = studyApplicationDAO.getList(critiria, start, pageSize, orderBy, desc);
			
		} catch (Exception e) {
			throw new SegesaServiceException("Error search StudyApplicationDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudyApplicationDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<StudyApplicationDTO> list = null;
		try {
			list = studyApplicationDAO.getList(null, start, pageSize, "studyId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search itemDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudyApplicationDTO> getDataList() throws SegesaServiceException {
		List<StudyApplicationDTO> list = null;
		try {
			list = studyApplicationDAO.getList(null, 0, 0, "studyId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search studyApplicationDAO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudyApplicationDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<StudyApplicationDTO> list = null;
		try {
			list = studyApplicationDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search StudyApplicationDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getRowCount(StudyApplicationSearch critiria) throws SegesaServiceException {
		
		Long count = null;
		try {
			count = studyApplicationDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("Error row count studyApplicationDAO : " + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(StudyApplicationDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(StudyApplicationDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(StudyApplicationDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(StudyApplicationSearch critiria) {
		// TODO Auto-generated method stub
		return null;
	}

}
