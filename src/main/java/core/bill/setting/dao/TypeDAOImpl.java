package core.bill.setting.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.TypeDTO;

@Component
public class TypeDAOImpl extends AbstractDAO<TypeDTO> implements TypeDAO{

	@Override
	public Class<TypeDTO> getClazz() {
	
		return TypeDTO.class;
	}
	
	@Override
	public TypeDTO save(TypeDTO model) {
		
		return saveModel(model);
	}

	@Override
	public void delete(TypeDTO model) {
		
		deleteModel(model);
	}

	@Override
	public TypeDTO update(TypeDTO model) {
		
		return updateModel(model, model.getTypeId());
	}

	@Override
	public TypeDTO getModel(Long pk) {
		
		return getModelDTO(pk);
	}

	@Override
	public List<TypeDTO> getList(CommonSearchDTO certieria, int start, int pageSize, String orderBy, Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		return getDataList(criterion, start, pageSize, orderBy, desc);
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
	public Long getRowNum(CommonSearchDTO certieria) {
		
		return getRowCount(getCriteria(certieria));
	}

	@Override
	public TypeDTO merge(TypeDTO model) {
		return mergeModel(model);
	}

	@Override
	public void saveOrUpdate(TypeDTO model) {
		saveOrUpdateModel(model);
		
	}

}
