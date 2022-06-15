package core.bill.setting.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.NationalityDTO;

@Component
public class NationalityDAOImpl extends AbstractDAO<NationalityDTO> implements NationalityDAO{

	@Override
	public Class<NationalityDTO> getClazz() {
		return NationalityDTO.class;
	}
	
	
	@Override
	public NationalityDTO save(NationalityDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(NationalityDTO model) {
		deleteModel(model);
	}

	@Override
	public NationalityDTO update(NationalityDTO model) {
		return updateModel(model, model.getNationalityId());
	}

	@Override
	public NationalityDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<NationalityDTO> getList(CommonSearchDTO certieria, int start, int pageSize, String orderBy,
			Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		return getDataList(criterion, start, pageSize, orderBy, desc);
	}

	@Override
	public Long getRowNum(CommonSearchDTO certieria) {
		List<Criterion> criterion = getCriteria(certieria);
		return getRowCount(criterion);
	}

	@Override
	public NationalityDTO merge(NationalityDTO model) {
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
	public void saveOrUpdate(NationalityDTO model) {
		// TODO Auto-generated method stub
		
	}

}
