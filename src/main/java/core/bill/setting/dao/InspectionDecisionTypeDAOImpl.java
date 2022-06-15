package core.bill.setting.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.InspectionDecisionTypeDTO;

@Component
public class InspectionDecisionTypeDAOImpl extends AbstractDAO<InspectionDecisionTypeDTO>
implements InspectionDecisionTypeDAO{

	@Override
	public Class<InspectionDecisionTypeDTO> getClazz() {
		return InspectionDecisionTypeDTO.class;
	}
	
	@Override
	public InspectionDecisionTypeDTO save(InspectionDecisionTypeDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(InspectionDecisionTypeDTO model) {
		deleteModel(model);
	}

	@Override
	public InspectionDecisionTypeDTO update(InspectionDecisionTypeDTO model) {
		return updateModel(model,model.getDecisionTypeId());
	}

	@Override
	public InspectionDecisionTypeDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<InspectionDecisionTypeDTO> getList(CommonSearchDTO certieria, int start, int pageSize, String orderBy,
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
	public InspectionDecisionTypeDTO merge(InspectionDecisionTypeDTO model) {
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
	public void saveOrUpdate(InspectionDecisionTypeDTO model) {
		// TODO Auto-generated method stub
		
	}

}
