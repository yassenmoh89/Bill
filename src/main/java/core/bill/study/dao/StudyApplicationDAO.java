package core.bill.study.dao;

import java.util.List;

import org.hibernate.criterion.ProjectionList;

import core.bill.common.dao.GenericDAO;
import core.bill.study.model.StudyApplicationDTO;
import core.bill.study.model.StudyApplicationSearch;

public interface StudyApplicationDAO extends GenericDAO<StudyApplicationDTO, StudyApplicationSearch>{
	public List<StudyApplicationDTO> getList(StudyApplicationSearch certieria, final ProjectionList projectionList ,int start, int pageSize, String orderBy, Boolean desc);


}
