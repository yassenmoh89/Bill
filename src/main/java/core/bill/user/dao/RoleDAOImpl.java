package core.bill.user.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import core.bill.common.dao.AbstractDAO;
import core.bill.user.model.RoleDTO;
import core.bill.user.model.RoleSearch;

@Component
public class RoleDAOImpl extends AbstractDAO<RoleDTO> implements RoleDAO{

	@Override
	public Class<RoleDTO> getClazz() {
		return RoleDTO.class;
	}
	
	@Override
	public RoleDTO save(RoleDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(RoleDTO model) {
		deleteModel(model);
		
	}

	@Override
	public RoleDTO update(RoleDTO model) {
		
		return updateModel(model, model.getRoleId());
	}

	@Override
	public RoleDTO getModel(Long pk) {
		
		return getModelDTO(pk);
	}

	@Override
	public List<RoleDTO> getList(RoleSearch certieria, int start, int pageSize, String orderBy, Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		return getDataList(criterion, start, pageSize, orderBy, desc);
	}

	private List<Criterion> getCriteria(RoleSearch certieria) {
		List<Criterion> critList = null;
		if (certieria != null) {
			critList = new ArrayList<Criterion>();

			if (certieria.getStatus() != null) {
				if (certieria.getStatus().equals("ACT")) {
					critList.add(Restrictions.eq("status", certieria.getStatus()));
				} else if (certieria.getStatus().equals("INACT")) {
					critList.add(Restrictions.eq("status", certieria.getStatus()));
				} 
			} if(certieria.getRoleName()!=null && certieria.getRoleName().length()>0 )
			{
				critList.add(Restrictions.ilike("roleName", "%"+certieria.getRoleName()+"%"));
			}if(certieria.getRoleCode()!=null && certieria.getRoleCode().length()>0 )
			{
				critList.add(Restrictions.ilike("roleCode", "%"+certieria.getRoleName()+"%"));
			}
		}
		return critList;
	}

	@Override
	public Long getRowNum(RoleSearch certieria) {
		return getRowCount(getCriteria(certieria));
	}

	@Override
	public RoleDTO merge(RoleDTO model) {
		return mergeModel(model);
	}

	@Override
	public void saveOrUpdate(RoleDTO model) {
		// TODO Auto-generated method stub
		
	}

}
