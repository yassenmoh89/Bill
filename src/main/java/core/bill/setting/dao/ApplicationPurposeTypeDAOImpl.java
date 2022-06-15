package core.bill.setting.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import core.bill.common.dao.AbstractDAO;
import core.bill.setting.model.ApplicationPurposeTypeDTO;
import core.bill.setting.model.CommonSearchDTO;

@Component
public class ApplicationPurposeTypeDAOImpl extends AbstractDAO<ApplicationPurposeTypeDTO> implements ApplicationPurposeTypeDAO {
 
	@Override
	public Class<ApplicationPurposeTypeDTO> getClazz() {
		return ApplicationPurposeTypeDTO.class;
	}

	@Override
	public ApplicationPurposeTypeDTO save(ApplicationPurposeTypeDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(ApplicationPurposeTypeDTO model) {
		 deleteModel(model);
	}

	@Override
	public ApplicationPurposeTypeDTO update(ApplicationPurposeTypeDTO model) {
		return updateModel(model, model.getApplicationPurposeTypeId());
	}

	@Override
	public ApplicationPurposeTypeDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<ApplicationPurposeTypeDTO> getList(CommonSearchDTO certieria, int start, int pageSize, String orderBy,
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
	public ApplicationPurposeTypeDTO merge(ApplicationPurposeTypeDTO model) {
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
	public void saveOrUpdate(ApplicationPurposeTypeDTO model) {
		// TODO Auto-generated method stub
		
	}
	
}
