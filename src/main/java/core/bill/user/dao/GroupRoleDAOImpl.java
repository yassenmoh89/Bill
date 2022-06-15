package core.bill.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.dao.AbstractDAO;
import core.bill.user.model.GroupRoleDTO;
import core.bill.user.model.GroupSearch;

@Component
public class GroupRoleDAOImpl extends AbstractDAO<GroupRoleDTO> implements GroupRoleDAO {

	@Override
	public Class<GroupRoleDTO> getClazz() {
		
		return GroupRoleDTO.class;
	}
	
	@Override
	public GroupRoleDTO save(GroupRoleDTO model) {
	
		return saveModel(model);
	}

	@Override
	public void delete(GroupRoleDTO model) {
		deleteModel(model);
		
	}

	@Override
	public GroupRoleDTO update(GroupRoleDTO model) {
		return updateModel(model, model.getGroupRoleId());
	}

	@Override
	public GroupRoleDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<GroupRoleDTO> getList(GroupSearch certieria, int start, int pageSize, String orderBy, Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		String[] table= {"groupResourceDTO"} ;
		return getDataList(criterion,table,start, pageSize, orderBy, desc);
	}

	private List<Criterion> getCriteria(GroupSearch certieria) {
		List<Criterion> critList = null;
		if (certieria != null) {
			critList = new ArrayList<Criterion>();

			if (certieria.getGroupResourceId()!=null) {

				critList.add(Restrictions.eq("groupResourceDTO.groupResourceId", certieria.getGroupResourceId()));
			}

			
		}
		return critList;
	}

	@Override
	public Long getRowNum(GroupSearch certieria) {
		return getRowCount(getCriteria(certieria));
	}

	@Override
	public GroupRoleDTO merge(GroupRoleDTO model) {
		// TODO Auto-generated method stub
		return mergeModel(model);
	}

	@Override
	public void saveOrUpdate(GroupRoleDTO model) {
		saveOrUpdateModel(model);
		
	}

}
