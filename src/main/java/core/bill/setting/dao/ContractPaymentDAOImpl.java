package core.bill.setting.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.ContractPaymentDTO;

@Component
public class ContractPaymentDAOImpl extends AbstractDAO<ContractPaymentDTO> implements ContractPaymentDAO{

	@Override
	public Class<ContractPaymentDTO> getClazz() {
		return ContractPaymentDTO.class ;
	}
	
	@Override
	public ContractPaymentDTO save(ContractPaymentDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(ContractPaymentDTO model) {
		deleteModel(model);
	}

	@Override
	public ContractPaymentDTO update(ContractPaymentDTO model) {
		return updateModel(model, model.getContractPaymentId());
	}

	@Override
	public ContractPaymentDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<ContractPaymentDTO> getList(CommonSearchDTO certieria, int start, int pageSize, String orderBy,
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
	public ContractPaymentDTO merge(ContractPaymentDTO model) {
		return mergeModel(model);
	}

	@Override
	public void saveOrUpdate(ContractPaymentDTO model) {
		saveOrUpdateModel(model);
	}

	
	

}
