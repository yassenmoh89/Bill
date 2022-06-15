package core.bill.setting.dao;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.setting.model.CircuitsDTO;
import core.bill.setting.model.CommonSearchDTO;

@Component
public class CircuitsDAOImpl extends AbstractDAO<CircuitsDTO>
implements CircuitsDAO{

	@Override
	public Class<CircuitsDTO> getClazz() {
		return CircuitsDTO.class;
	}
	
	@Override
	public CircuitsDTO save(CircuitsDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(CircuitsDTO model) {
		deleteModel(model);
	}

	@Override
	public CircuitsDTO update(CircuitsDTO model) {
		return updateModel(model,model.getCircuitId());
	}

	@Override
	public CircuitsDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<CircuitsDTO> getList(CommonSearchDTO certieria, int start, int pageSize, String orderBy, Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		return getDataList(criterion, start, pageSize, orderBy, desc);
	}

	@Override
	public Long getRowNum(CommonSearchDTO certieria) {
		List<Criterion> criterion = getCriteria(certieria);
		return getRowCount(criterion);
	}

	@Override
	public CircuitsDTO merge(CircuitsDTO model) {
		
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
	public void saveOrUpdate(CircuitsDTO model) {
		// TODO Auto-generated method stub
		
	}

}
