package core.bill.setting.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.ContractBillingDTO;

@Component
public class ContractBillingDAOImpl extends AbstractDAO<ContractBillingDTO> implements ContractBillingDAO{

	@Override
	public Class<ContractBillingDTO> getClazz() {
		return ContractBillingDTO.class;
	}
	
	@Override
	public ContractBillingDTO save(ContractBillingDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(ContractBillingDTO model) {
		deleteModel(model);
		
	}

	@Override
	public ContractBillingDTO update(ContractBillingDTO model) {
		return updateModel(model, model.getContractBillingId());
	}

	@Override
	public ContractBillingDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<ContractBillingDTO> getList(CommonSearchDTO certieria, int start, int pageSize, String orderBy,
			Boolean desc) {
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
	public ContractBillingDTO merge(ContractBillingDTO model) {
		return mergeModel(model);
	}

	@Override
	public void saveOrUpdate(ContractBillingDTO model) {
		saveOrUpdateModel(model);
		
	}

}
