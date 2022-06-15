package core.bill.user.dao;

import java.util.List;

import org.hibernate.criterion.ProjectionList;

import core.bill.common.dao.GenericDAO;
import core.bill.user.model.UserSessionDTO;
import core.bill.user.model.UserSearch;

public interface UserSessionDAO extends GenericDAO<UserSessionDTO, UserSearch>{
	public List<UserSessionDTO> getList(UserSearch critiria,ProjectionList projectionList , int start, int pageSize, String orderBy, Boolean desc);
	

}
