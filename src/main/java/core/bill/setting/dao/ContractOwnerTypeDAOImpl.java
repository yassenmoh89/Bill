package core.bill.setting.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.ContractOwnerTypeDTO;

@Component
public class ContractOwnerTypeDAOImpl extends AbstractDAO<ContractOwnerTypeDTO>
implements ContractOwnerTypeDAO{

	@Override
	public Class<ContractOwnerTypeDTO> getClazz() {
		return ContractOwnerTypeDTO.class;
	}
	
	@Override
	public ContractOwnerTypeDTO save(ContractOwnerTypeDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(ContractOwnerTypeDTO model) {
		deleteModel(model);
	}

	@Override
	public ContractOwnerTypeDTO update(ContractOwnerTypeDTO model) {
		return updateModel(model,model.getContractOwnerTypeId());
	}

	@Override
	public ContractOwnerTypeDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<ContractOwnerTypeDTO> getList(CommonSearchDTO certieria, int start, int pageSize, String orderBy,
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
	public ContractOwnerTypeDTO merge(ContractOwnerTypeDTO model) {
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
	public void saveOrUpdate(ContractOwnerTypeDTO model) {
		// TODO Auto-generated method stub
		
	}

}
