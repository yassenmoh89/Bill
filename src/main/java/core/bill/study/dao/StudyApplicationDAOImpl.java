package core.bill.study.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.study.model.StudyApplicationDTO;
import core.bill.study.model.StudyApplicationSearch;


@Component
public class StudyApplicationDAOImpl extends AbstractDAO<StudyApplicationDTO> implements StudyApplicationDAO{

	@Override
	public Class<StudyApplicationDTO> getClazz() {
		return StudyApplicationDTO.class;
	}
	
	@Override
	public StudyApplicationDTO save(StudyApplicationDTO model) {
		
		return saveModel(model);
	}

	@Override
	public void delete(StudyApplicationDTO model) {
		deleteModel(model);
		
	}

	@Override
	public StudyApplicationDTO update(StudyApplicationDTO model) {
		
		return updateModel(model, model.getStudyId());
	}

	@Override
	public StudyApplicationDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<StudyApplicationDTO> getList(StudyApplicationSearch certieria, int start, int pageSize, String orderBy,Boolean desc) {
			List<Criterion> criterion = getCriteria(certieria);
			String [] table = {"customerDTO"};
			return getDataList(criterion,table, start, pageSize, orderBy, desc);
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
		
		if (certieria.getCustomerId() > 0) {
			critList.add(Restrictions.eq("customerDTO.customerId",  certieria.getCustomerId() ));
		}
		
		if (certieria.getStudyID() > 0) {
			critList.add(Restrictions.eq("studyId",  certieria.getStudyID() ));
		}
		if (certieria.getFromDate() != null) {
			critList.add(Restrictions.gt("studyDate",  certieria.getFromDate() ));
		}

	}
	return critList;
}

	@Override
	public Long getRowNum(StudyApplicationSearch certieria) {
		String [] table = {"customerDTO"};
		return getRowCount(getCriteria(certieria),table);
	}

	@Override
	public StudyApplicationDTO merge(StudyApplicationDTO model) {
		
		return mergeModel(model);
	}

	@Override
	public void saveOrUpdate(StudyApplicationDTO model) {
		saveOrUpdate(model);
		
	}

	@Override
	public List<StudyApplicationDTO> getList(StudyApplicationSearch certieria, ProjectionList projectionList, int start,
			int pageSize, String orderBy, Boolean desc) {
		List<StudyApplicationDTO> list = null;
		try {

			List<Criterion> criterion = getCriteria(certieria);
			List<Object[]> listObj = getDataList(criterion, projectionList, start, pageSize, orderBy, desc);

			for (Object[] obj : listObj) {
				if (list == null) {
					list = new ArrayList<StudyApplicationDTO>();
				}
				StudyApplicationDTO item = new StudyApplicationDTO();
				item.setStudyId(Long.valueOf(obj[0].toString()));
				item.setStatus((String) obj[1]);
				list.add(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
