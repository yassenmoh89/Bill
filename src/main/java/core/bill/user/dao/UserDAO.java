package core.bill.user.dao;

import java.util.List;

import org.hibernate.criterion.ProjectionList;
import core.bill.common.dao.GenericDAO;

import core.bill.user.model.UserDTO;

import core.bill.user.model.UserSearch;

public interface UserDAO extends GenericDAO<UserDTO, UserSearch> {

	public List<UserDTO> getList(UserSearch critiria,ProjectionList projectionList , int start, int pageSize, String orderBy, Boolean desc);
	public List<UserDTO> getListUsers(UserSearch critiria,ProjectionList projectionList , int start, int pageSize, String orderBy, Boolean desc);
	public void deleteUserRole(Long userRoleId);
}
