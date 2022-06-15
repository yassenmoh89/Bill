package core.bill.setting.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import core.bill.common.dao.AbstractDAO;
import core.bill.setting.model.BillingTypeDTO;
import core.bill.setting.model.CommonSearchDTO;

@Component
public class BillingTypeDAOImpl extends AbstractDAO<BillingTypeDTO> 
implements BillingTypeDAO{

	
	@Override
	public Class<BillingTypeDTO> getClazz() {
		
		return BillingTypeDTO.class;
	}
	
	@Override
	public BillingTypeDTO save(BillingTypeDTO model) {
		
		return saveModel(model);
	}

	@Override
	public void delete(BillingTypeDTO model) {
		deleteModel(model);
	}

	@Override
	public BillingTypeDTO update(BillingTypeDTO model) {
		return updateModel(model,model.getBillingTypeId());
	}

	@Override
	public BillingTypeDTO getModel(Long pk) {
	
		return getModelDTO(pk);
	}

	@Override
	public List<BillingTypeDTO> getList(CommonSearchDTO certieria, int start, int pageSize, String orderBy,
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
	public BillingTypeDTO merge(BillingTypeDTO model) {
		// TODO Auto-generated method stub
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
	public void saveOrUpdate(BillingTypeDTO model) {
		// TODO Auto-generated method stub
		
	}
	

}
