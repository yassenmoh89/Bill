package core.bill.setting.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.PriceDTO;

@Component
public class PriceDAOImpl extends AbstractDAO<PriceDTO> implements PriceDAO{

	@Override
	public Class<PriceDTO> getClazz() {
		return PriceDTO.class;
	}
	
	@Override
	public PriceDTO save(PriceDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(PriceDTO model) {
		deleteModel(model);
		
	}

	@Override
	public PriceDTO update(PriceDTO model) {
		return updateModel(model, model.getPriceId());
	}

	@Override
	public PriceDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<PriceDTO> getList(CommonSearchDTO certieria, int start, int pageSize, String orderBy, Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		return getDataList(criterion, start, pageSize, orderBy, desc);
	}

	@Override
	public Long getRowNum(CommonSearchDTO certieria) {
		List<Criterion> criterion = getCriteria(certieria);
		return getRowCount(criterion);
	}

	@Override
	public PriceDTO merge(PriceDTO model) {
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
			
			if(certieria.getRank()!=null && certieria.getRank().equals(""))
			{
				critList.add(Restrictions.eq("rank", certieria.getStatus()));
			}
		}
		return critList;
	}

	@Override
	public void saveOrUpdate(PriceDTO model) {
		// TODO Auto-generated method stub
		
	}

}
