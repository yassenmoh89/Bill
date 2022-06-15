package core.bill.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import core.bill.common.dao.AbstractDAO;
import core.bill.user.model.SystemConfig;
import core.bill.user.model.SystemConfigSearch;

@Component

public class SystemConfigDAOImpl extends AbstractDAO<SystemConfig> implements SystemConfigDAO {

	@Override
	public Class<SystemConfig> getClazz() {
		return SystemConfig.class;
	}
	@Override
	public SystemConfig save(SystemConfig model) {

		return saveModel(model);
	}

	@Override
	public void delete(SystemConfig model) {
		deleteModel(model);
	}

	@Override
	public SystemConfig update(SystemConfig model) {
		return updateModel(model, model.getId());
	}

	@Override
	public SystemConfig getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<SystemConfig> getList(SystemConfigSearch certieria, int start, int pageSize, String orderBy,
			Boolean desc) {
		List<Criterion> critList = getCriteria(certieria);

		return getDataList(critList, start, pageSize, orderBy, desc);
	}

	@Override
	public Long getRowNum(SystemConfigSearch certieria) {
		return getRowCount(getCriteria(certieria));
	}

	@Override
	public SystemConfig merge(SystemConfig model) {

		return mergeModel(model);
	}

	private List<Criterion> getCriteria(SystemConfigSearch certieria) {
		List<Criterion> critList = null;
		if (certieria != null) {
			critList = new ArrayList<Criterion>();

			if (certieria.getConfigName() != null && certieria.getConfigName().length() > 0) {

				critList.add(Restrictions.ilike("configName", certieria.getConfigName() ,MatchMode.EXACT));
			}

			if (certieria.getValue() != null && certieria.getValue().length() > 0) {
				critList.add(Restrictions.ilike("value", "%" + certieria.getValue() + "%"));
			}
		}
		return critList;
	}
	//-------------------------------------
	private List<Criterion> getCriteriaConfigName(String key) {
		List<Criterion> critList = null;
		if (key != null) {
			critList = new ArrayList<Criterion>();

			if (key != null && key.length() > 0) {

				critList.add(Restrictions.ilike("configName", key ,MatchMode.EXACT));
			}

			
		}
		return critList;
	}
	@Override
	public void saveOrUpdate(SystemConfig model) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public SystemConfig getSystemConfig(String key) {
		SystemConfig systemConfig =null ;
		List<SystemConfig> list = getDataList(getCriteriaConfigName(key), 0, 0, "configName", Boolean.TRUE);
		if(list!=null && list.size()>0)
		{
			systemConfig = list.get(0);
		}
		return systemConfig;
	}

}
