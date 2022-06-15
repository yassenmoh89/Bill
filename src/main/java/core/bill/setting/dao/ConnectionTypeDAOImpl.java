package core.bill.setting.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.ConnectionTypeDTO;

@Component
public class ConnectionTypeDAOImpl extends AbstractDAO<ConnectionTypeDTO>
implements ConnectionTypeDAO
{

	@Override
	public Class<ConnectionTypeDTO> getClazz() {
		return ConnectionTypeDTO.class ;
	}
	
	@Override
	public ConnectionTypeDTO save(ConnectionTypeDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(ConnectionTypeDTO model) {
		delete(model);
	}

	@Override
	public ConnectionTypeDTO update(ConnectionTypeDTO model) {
		return updateModel(model,model.getConnectionTypeId());
	}

	@Override
	public ConnectionTypeDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<ConnectionTypeDTO> getList(CommonSearchDTO certieria, int start, int pageSize, String orderBy,
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
	public ConnectionTypeDTO merge(ConnectionTypeDTO model) {
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
	public void saveOrUpdate(ConnectionTypeDTO model) {
		// TODO Auto-generated method stub
		
	}
}
