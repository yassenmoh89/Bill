package core.bill.study.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.study.model.StudyApplicationDetailsDTO;
import core.bill.study.model.StudyApplicationSearch;

@Component
public class StudyApplicationDetailsDAOImpl extends AbstractDAO<StudyApplicationDetailsDTO> implements StudyApplicationDetailsDAO {

	@Override
	public Class<StudyApplicationDetailsDTO> getClazz() {
		return StudyApplicationDetailsDTO.class;
	}

	@Override
	public StudyApplicationDetailsDTO save(StudyApplicationDetailsDTO model) {
		
		return saveModel(model);
	}

	@Override
	public void delete(StudyApplicationDetailsDTO model) {
		deleteModel(model);
		
	}
	
	@Override
	public StudyApplicationDetailsDTO update(StudyApplicationDetailsDTO model) {
		
		return updateModel(model, model.getStudyDetailsId());
	}

	@Override
	public StudyApplicationDetailsDTO getModel(Long pk) {
		
		return getModel(pk);
	}

	@Override
	public List<StudyApplicationDetailsDTO> getList(StudyApplicationSearch certieria, int start, int pageSize,
			String orderBy, Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		return getDataList(criterion, start, pageSize, orderBy, desc);
	}

	private List<Criterion> getCriteria(StudyApplicationSearch certieria) {
		List<Criterion> critList = null;
		if (certieria != null) {
			critList = new ArrayList<Criterion>();

			if (certieria.getStatus() != null) {
				if (certieria.getStatus().equals("ACT")) {
					critList.add(Restrictions.eq("status", certieria.getStatus()));
				} else if (certieria.getStatus().equals("INACT")) {
					critList.add(Restrictions.eq("status", certieria.getStatus()));
				}
			}
			if (certieria.getStudyID() > 0) {
				critList.add(Restrictions.eq("studyApplicationDTO.studyId",  certieria.getStudyID() ));
			}
			
			if (certieria.getStudyDetailsId() > 0) {
				critList.add(Restrictions.eq("studyDetailsId",  certieria.getStudyDetailsId() ));
			}
			
//			if (certieria.getTechnicalCode() != null && certieria.getTechnicalCode().length() > 0) {
//				critList.add(Restrictions.ilike("technicalCode", "%" + certieria.getTechnicalCode() + "%"));
//			}
//			if (certieria.getTechnicalId()>0 ) {
//				critList.add(Restrictions.eq("technicalId", certieria.getTechnicalId() ));
//			}
		}
		return critList;
		}
	@Override
	public Long getRowNum(StudyApplicationSearch certieria) {
		
		return getRowNum(certieria);
	}

	@Override
	public StudyApplicationDetailsDTO merge(StudyApplicationDetailsDTO model) {
		
		return mergeModel(model);
	}

	@Override
	public void saveOrUpdate(StudyApplicationDetailsDTO model) {
		saveOrUpdateModel(model);
		
	}

	@Override
	public List<StudyApplicationDetailsDTO> getList(StudyApplicationSearch certieria, ProjectionList projectionList,
			int start, int pageSize, String orderBy, Boolean desc) {
		List<StudyApplicationDetailsDTO> list = null;
		try {

			List<Criterion> criterion = getCriteria(certieria);
			List<Object[]> listObj = getDataList(criterion, projectionList, start, pageSize, orderBy, desc);

			for (Object[] obj : listObj) {
				if (list == null) {
					list = new ArrayList<StudyApplicationDetailsDTO>();
				}
				StudyApplicationDetailsDTO item = new StudyApplicationDetailsDTO();
				item.getStudyApplicationDTO().setStudyId(Long.valueOf(obj[0].toString()));;
				item.setStudyDetailsId((Long) obj[1]);

				list.add(item);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
