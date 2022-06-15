package core.bill.user.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import core.bill.common.dao.AbstractDAO;
import core.bill.user.model.GroupDTO;
import core.bill.user.model.GroupSearch;

@Component
public class GroupDAOImpl extends AbstractDAO<GroupDTO> implements GroupDAO {

	@Override
	public Class<GroupDTO> getClazz() {
		return GroupDTO.class;
	}

	@Override
	public GroupDTO save(GroupDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(GroupDTO model) {
		deleteModel(model);
	}

	@Override
	public GroupDTO update(GroupDTO model) {
		return updateModel(model, model.getGroupId());
	}

	@Override
	public GroupDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<GroupDTO> getList(GroupSearch certieria, int start, int pageSize, String orderBy, Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		return getDataList(criterion, start, pageSize, orderBy, desc);
	}

	private List<Criterion> getCriteria(GroupSearch certieria) {
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
			if (certieria.getGroupName() != null && certieria.getGroupName().length() > 0) {
				critList.add(Restrictions.ilike("groupName", "%" + certieria.getGroupName() + "%"));
			}
			if (certieria.getGroupCode() != null && certieria.getGroupCode().length() > 0) {
				critList.add(Restrictions.ilike("groupCode", "%" + certieria.getGroupCode() + "%"));
			}
			if (certieria.getComment() != null && certieria.getComment().length() > 0) {
				critList.add(Restrictions.ilike("COMMENT", "%" + certieria.getComment() + "%"));
			}
		}
		return critList;
	}

	@Override
	public Long getRowNum(GroupSearch certieria) {
		return getRowCount(getCriteria(certieria));
	}

	@Override
	public GroupDTO merge(GroupDTO model) {
		return mergeModel(model);
	}

	@Override
	public void saveOrUpdate(GroupDTO model) {
		saveOrUpdateModel(model);

	}

	@Override
	public List<GroupDTO> getList(GroupSearch certieria, ProjectionList projectionList, int start, int pageSize,
			String orderBy, Boolean desc) {
		List<GroupDTO> list = null;
		try {

			List<Criterion> criterion = getCriteria(certieria);
			List<Object[]> listObj = getDataList(criterion, projectionList, start, pageSize, orderBy, desc);

			for (Object[] obj : listObj) {
				if (list == null) {
					list = new ArrayList<GroupDTO>();
				}
				GroupDTO group = new GroupDTO();
				group.setGroupId(Long.valueOf(obj[0].toString()));
				group.setGroupName((String) obj[1]);
				group.setGroupCode((String) obj[2]);
				group.setStatus((String) obj[3]);

				list.add(group);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
