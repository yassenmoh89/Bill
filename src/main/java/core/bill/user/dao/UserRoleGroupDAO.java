package core.bill.user.dao;

import java.util.List;

import org.hibernate.criterion.ProjectionList;

import core.bill.common.dao.GenericDAO;
import core.bill.user.model.GroupSearch;
import core.bill.user.model.UserDTO;
import core.bill.user.model.UserRoleDTO;


public interface UserRoleGroupDAO extends GenericDAO<UserRoleDTO, GroupSearch>{
	public List<UserRoleDTO> getList(GroupSearch critiria,ProjectionList projectionList , int start, int pageSize, String orderBy, Boolean desc);
	public List<UserDTO> getUserList(GroupSearch critiria,ProjectionList projectionList , int start, int pageSize, String orderBy, Boolean desc);
	
}
