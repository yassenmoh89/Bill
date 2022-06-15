package core.bill.setting.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.PaymentTypeDTO;

@Component
public class PaymentTypeDAOImpl extends AbstractDAO<PaymentTypeDTO> implements PaymentTypeDAO{

	@Override
	public Class<PaymentTypeDTO> getClazz() {
		return PaymentTypeDTO.class;
	}
	
	
	@Override
	public PaymentTypeDTO save(PaymentTypeDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(PaymentTypeDTO model) {
		deleteModel(model);
	}

	@Override
	public PaymentTypeDTO update(PaymentTypeDTO model) {
		return updateModel(model, model.getPaymentTypeId());
	}

	@Override
	public PaymentTypeDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<PaymentTypeDTO> getList(CommonSearchDTO certieria, int start, int pageSize, String orderBy,
			Boolean desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getRowNum(CommonSearchDTO certieria) {
		List<Criterion> criterion = getCriteria(certieria);
		return getRowCount(criterion);
	}

	@Override
	public PaymentTypeDTO merge(PaymentTypeDTO model) {
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
	public void saveOrUpdate(PaymentTypeDTO model) {
		// TODO Auto-generated method stub
		
	}

}
