package core.bill.setting.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.invoice.model.ChargeDTO;
import core.bill.setting.model.CommonSearchDTO;

@Component
public class ChargeDAOImpl extends AbstractDAO<ChargeDTO> implements ChargeDAO{

	@Override
	public Class<ChargeDTO> getClazz(){
		return ChargeDTO.class;
	}
	@Override
	public ChargeDTO save(ChargeDTO model) {
		return save(model);
	}

	@Override
	public void delete(ChargeDTO model) {
		delete(model);
		
	}

	@Override
	public ChargeDTO update(ChargeDTO model) {
		return updateModel(model,model.getChargeId());
	}

	@Override
	public ChargeDTO getModel(Long pk) {
		return getModel(pk);
	}

	@Override
	public List<ChargeDTO> getList(CommonSearchDTO certieria, int start, int pageSize, String orderBy, Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		return getDataList(criterion, start, pageSize, orderBy, desc);
	}

	@Override
	public Long getRowNum(CommonSearchDTO certieria) {
		List<Criterion> criterion = getCriteria(certieria);
		return getRowCount(criterion);
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
	public ChargeDTO merge(ChargeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(ChargeDTO model) {
		// TODO Auto-generated method stub
		
	}

}
