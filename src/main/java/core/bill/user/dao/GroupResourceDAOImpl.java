package core.bill.user.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.user.model.GroupResourceDTO;
import core.bill.user.model.GroupSearch;


@Component
public class GroupResourceDAOImpl extends AbstractDAO<GroupResourceDTO> implements GroupResourceDAO{

	@Override
	public Class<GroupResourceDTO> getClazz() {
		
		return GroupResourceDTO.class;
	}
	
	@Override
	public GroupResourceDTO save(GroupResourceDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(GroupResourceDTO model) {
		deleteModel(model);
		
	}

	@Override
	public GroupResourceDTO update(GroupResourceDTO model) {
		return updateModel(model, model.getGroupResourceId());
	}

	@Override
	public GroupResourceDTO getModel(Long pk) {

		return getModelDTO(pk);
	}

	@Override
	public List<GroupResourceDTO> getList(GroupSearch certieria, int start, int pageSize, String orderBy,
			Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		return getDataList(criterion, start, pageSize, orderBy, desc);
	}

	private List<Criterion> getCriteria(GroupSearch certieria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getRowNum(GroupSearch certieria) {
		
		return getRowCount(getCriteria(certieria));
	}

	@Override
	public GroupResourceDTO merge(GroupResourceDTO model) {
		// TODO Auto-generated method stub
		return mergeModel(model);
	}

	@Override
	public void saveOrUpdate(GroupResourceDTO model) {
		saveOrUpdateModel(model);
		
	}

}
