package core.bill.study.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import core.bill.common.dao.AbstractDAO;
import core.bill.study.model.StudyTechnicalDTO;
import core.bill.study.model.TechnicalSearch;

@Component
public class StudyTechnicalDAOImpl extends AbstractDAO<StudyTechnicalDTO> implements StudyTechnicalDAO{

	@Override
	public Class<StudyTechnicalDTO> getClazz() {
		return StudyTechnicalDTO.class;
	}
	
	@Override
	public StudyTechnicalDTO save(StudyTechnicalDTO model) {
		
		return saveModel(model);
	}

	@Override
	public void delete(StudyTechnicalDTO model) {
		deleteModel(model);
		
	}

	@Override
	public StudyTechnicalDTO update(StudyTechnicalDTO model) {
		
		return updateModel(model, model.getTechnicalId());
	}

	@Override
	public StudyTechnicalDTO getModel(Long pk) {
		
		return getModelDTO(pk);
	}

	@Override
	public List<StudyTechnicalDTO> getList(TechnicalSearch certieria, int start, int pageSize, String orderBy,
			Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		return getDataList(criterion, start, pageSize, orderBy, desc);
	}

	private List<Criterion> getCriteria(TechnicalSearch certieria) {
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
			if (certieria.getTechnicalName() != null && certieria.getTechnicalName().length() > 0) {
				critList.add(Restrictions.ilike("technicalName", "%" + certieria.getTechnicalName() + "%"));
			}
			if (certieria.getTechnicalCode() != null && certieria.getTechnicalCode().length() > 0) {
				critList.add(Restrictions.ilike("technicalCode", "%" + certieria.getTechnicalCode() + "%"));
			}
			if (certieria.getTechnicalId()>0 ) {
				critList.add(Restrictions.eq("technicalId", certieria.getTechnicalId() ));
			}
		}
		return critList;
	}
	@Override
	public Long getRowNum(TechnicalSearch certieria) {
		return getRowCount(getCriteria(certieria));
	}

	@Override
	public StudyTechnicalDTO merge(StudyTechnicalDTO model) {
		return mergeModel(model);
	}

	@Override
	public void saveOrUpdate(StudyTechnicalDTO model) {
		saveOrUpdate(model);
		
	}

	@Override
	public List<StudyTechnicalDTO> getList(TechnicalSearch certieria, ProjectionList projectionList, int start,
			int pageSize, String orderBy, Boolean desc) {
		List<StudyTechnicalDTO> list = null;
		try {

			List<Criterion> criterion = getCriteria(certieria);
			List<Object[]> listObj = getDataList(criterion, projectionList, start, pageSize, orderBy, desc);

			for (Object[] obj : listObj) {
				if (list == null) {
					list = new ArrayList<StudyTechnicalDTO>();
				}
				StudyTechnicalDTO item = new StudyTechnicalDTO();
				item.setTechnicalId(Long.valueOf(obj[0].toString()));
				item.setTechnicalName((String) obj[1]);
				item.setTechnicalCode((String) obj[2]);
				item.setStatus((String) obj[3]);
				item.setPhone((String) obj[4]);

				list.add(item);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
