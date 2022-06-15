package core.bill.setting.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.IdentificationTypeDTO;

@Component
public class IdentificationTypeDAOImpl extends AbstractDAO<IdentificationTypeDTO>
implements IdentificationTypeDAO{

	@Override
	public Class<IdentificationTypeDTO> getClazz() {
		return IdentificationTypeDTO.class;
	}
	
	@Override
	public IdentificationTypeDTO save(IdentificationTypeDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(IdentificationTypeDTO model) {
		deleteModel(model);
	}

	@Override
	public IdentificationTypeDTO update(IdentificationTypeDTO model) {
		return updateModel(model,model.getIdentificationTypeId());
	}

	@Override
	public IdentificationTypeDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<IdentificationTypeDTO> getList(CommonSearchDTO certieria, int start, int pageSize, String orderBy,
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
	public IdentificationTypeDTO merge(IdentificationTypeDTO model) {
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
	public void saveOrUpdate(IdentificationTypeDTO model) {
		// TODO Auto-generated method stub
		
	}

}
