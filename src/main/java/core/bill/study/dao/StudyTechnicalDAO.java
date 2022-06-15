package core.bill.study.dao;

import java.util.List;

import org.hibernate.criterion.ProjectionList;

import core.bill.common.dao.GenericDAO;

import core.bill.study.model.StudyTechnicalDTO;
import core.bill.study.model.TechnicalSearch;

public interface StudyTechnicalDAO extends GenericDAO<StudyTechnicalDTO, TechnicalSearch>{
	public List<StudyTechnicalDTO> getList(TechnicalSearch certieria, final ProjectionList projectionList ,int start, int pageSize, String orderBy, Boolean desc);


}
