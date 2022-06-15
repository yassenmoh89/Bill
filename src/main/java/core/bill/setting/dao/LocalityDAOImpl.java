package core.bill.setting.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.LocalityDTO;

@Component
public class LocalityDAOImpl extends AbstractDAO<LocalityDTO> implements LocalityDAO{

	@Override
	public Class<LocalityDTO> getClazz() {
		return LocalityDTO.class;
	}
	
	@Override
	public LocalityDTO save(LocalityDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(LocalityDTO model) {
		deleteModel(model);
	}

	@Override
	public LocalityDTO update(LocalityDTO model) {
		return updateModel(model,model.getLocalityId());
	}

	@Override
	public LocalityDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<LocalityDTO> getList(CommonSearchDTO certieria, int start, int pageSize, String orderBy, Boolean desc) {
		
		List<Criterion> criterion = getCriteria(certieria);
		return getDataList(criterion, start, pageSize, orderBy, desc);
	}

	@Override
	public Long getRowNum(CommonSearchDTO certieria) {
		
		List<Criterion> criterion = getCriteria(certieria);
		return getRowCount(criterion);
	}

	@Override
	public LocalityDTO merge(LocalityDTO model) {
		return mergeModel(model);
	}
	
	private List<Criterion> getCriteria(CommonSearchDTO certieria) {

		List<Criterion> critList = null;

		if (certieria != null) {
			critList = new ArrayList<Criterion>();

			if (certieria.getStatus() != null) {
				if (certieria.getStatus().equals("ACT")) {
					critList.add(Restrictions.eq("status", certieria.getStatus()));
				} else if (certieria.getStatus().equals("INACT")) {
					critList.add(Restrictions.eq("status", certieria.getStatus()));
				} else {

				}
			}
		}
		return critList;
	}

	@Override
	public void saveOrUpdate(LocalityDTO model) {
		// TODO Auto-generated method stub
		
	}


}
