package core.bill.study.dao;

import java.util.List;

import org.hibernate.criterion.ProjectionList;

import core.bill.common.dao.GenericDAO;
import core.bill.study.model.StudyApplicationDetailsDTO;
import core.bill.study.model.StudyApplicationSearch;

public interface StudyApplicationDetailsDAO extends GenericDAO<StudyApplicationDetailsDTO, StudyApplicationSearch>{
	public List<StudyApplicationDetailsDTO> getList(StudyApplicationSearch certieria, final ProjectionList projectionList ,int start, int pageSize, String orderBy, Boolean desc);
	
}
