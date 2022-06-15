package core.bill.user.dao;

import java.util.List;
import org.hibernate.criterion.ProjectionList;
import core.bill.common.dao.GenericDAO;
import core.bill.user.model.GroupDTO;
import core.bill.user.model.GroupSearch;

public interface GroupDAO extends GenericDAO<GroupDTO, GroupSearch> {
	public List<GroupDTO> getList(GroupSearch certieria, final ProjectionList projectionList ,int start, int pageSize, String orderBy, Boolean desc);
}
