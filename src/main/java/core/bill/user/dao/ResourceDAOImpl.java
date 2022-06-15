package core.bill.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import core.bill.common.dao.AbstractDAO;
import core.bill.user.model.ResourceDTO;
import core.bill.user.model.ResourceSearch;

@Component
public class ResourceDAOImpl extends AbstractDAO<ResourceDTO> implements ResourceDAO {

	@Override
	public Class<ResourceDTO> getClazz() {
		return ResourceDTO.class;
	}
	
	@Override
	public ResourceDTO save(ResourceDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(ResourceDTO model) {
		deleteModel(model);
	}

	@Override
	public ResourceDTO update(ResourceDTO model) {
		return updateModel(model, model.getRoleResourceId());
	}

	@Override
	public ResourceDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<ResourceDTO> getList(ResourceSearch certieria, int start, int pageSize, String orderBy, Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		return getDataList(criterion, start, pageSize, orderBy, desc);
	}

	private List<Criterion> getCriteria(ResourceSearch certieria) {
		List<Criterion> critList = null;
		if (certieria != null) {
			critList = new ArrayList<Criterion>();

			if (certieria.getStatus() != null) {
				if (certieria.getStatus().equals("ACT")) {
					critList.add(Restrictions.eq("status", certieria.getStatus()));
				} else if (certieria.getStatus().equals("INACT")) {
					critList.add(Restrictions.eq("status", certieria.getStatus()));
				} 
			} if(certieria.getResourceName()!=null && certieria.getResourceName().length()>0 )
			{
				critList.add(Restrictions.ilike("resourceName", "%"+certieria.getResourceName()+"%"));
			}
		}
		return critList;
	}

	@Override
	public Long getRowNum(ResourceSearch certieria) {
		return getRowCount(getCriteria(certieria));
	}

	@Override
	public ResourceDTO merge(ResourceDTO model) {
		// TODO Auto-generated method stub
		return mergeModel(model);
	}

	@Override
	public void saveOrUpdate(ResourceDTO model) {
		// TODO Auto-generated method stub
		
	}

	

}
