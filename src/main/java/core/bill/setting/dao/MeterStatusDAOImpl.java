package core.bill.setting.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.MeterStatusDTO;

@Component
public class MeterStatusDAOImpl extends AbstractDAO<MeterStatusDTO> implements MeterStatusDAO{

	@Override
	public Class<MeterStatusDTO> getClazz() {
		return MeterStatusDTO.class;
	}
	
	@Override
	public MeterStatusDTO save(MeterStatusDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(MeterStatusDTO model) {
		deleteModel(model);
	}

	@Override
	public MeterStatusDTO update(MeterStatusDTO model) {
		return updateModel(model, model.getMeterStatusId());
	}

	@Override
	public MeterStatusDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<MeterStatusDTO> getList(CommonSearchDTO certieria, int start, int pageSize, String orderBy,
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
	public MeterStatusDTO merge(MeterStatusDTO model) {
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
	public void saveOrUpdate(MeterStatusDTO model) {
		// TODO Auto-generated method stub
		
	}

}
