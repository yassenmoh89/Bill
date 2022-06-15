package core.bill.study.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.study.model.ItemSearch;
import core.bill.study.model.ItemsDTO;

@Component
public class ItemsDAOImpl extends AbstractDAO<ItemsDTO> implements ItemsDAO{

	@Override
	public Class<ItemsDTO> getClazz() {
		return ItemsDTO.class;
	}

	@Override
	public ItemsDTO save(ItemsDTO model) {
		// TODO Auto-generated method stub
		return saveModel(model);
	}

	@Override
	public void delete(ItemsDTO model) {
		deleteModel(model);	
	}

	@Override
	public ItemsDTO update(ItemsDTO model) {
		return updateModel(model,model.getItemId());
	}

	@Override
	public ItemsDTO getModel(Long pk) {
		
		return getModel(pk);
	}

	@Override
	public List<ItemsDTO> getList(ItemSearch certieria, int start, int pageSize, String orderBy, Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		return getDataList(criterion, start, pageSize, orderBy, desc);
	}
	
	private List<Criterion> getCriteria(ItemSearch certieria) {
		List<Criterion> critList = null;
		if (certieria != null) {
			critList = new ArrayList<Criterion>();

			if (certieria.getStatus() != null) {
				if (certieria.getStatus().equals("ACT")) {
					critList.add(Restrictions.eq("status", certieria.getStatus()));
				} else if (certieria.getStatus().equals("INACT")) {
					critList.add(Restrictions.eq("status", certieria.getStatus()));
				}
			}
			if (certieria.getItemName() != null && certieria.getItemName().length() > 0) {
				critList.add(Restrictions.ilike("itemName", "%" + certieria.getItemName() + "%"));
			}
			if (certieria.getItemCode() != null && certieria.getItemCode().length() > 0) {
				critList.add(Restrictions.ilike("ItemCode", "%" + certieria.getItemCode() + "%"));
			}
			if (certieria.getItemId()>0 ) {
				critList.add(Restrictions.eq("itemId", certieria.getItemId() ));
			}
		}
		return critList;
	}

	@Override
	public Long getRowNum(ItemSearch certieria) {
		return getRowCount(getCriteria(certieria));
	}

	@Override
	public ItemsDTO merge(ItemsDTO model) {
		return mergeModel(model);
	}

	@Override
	public void saveOrUpdate(ItemsDTO model) {
		saveOrUpdate(model);
		
	}

	@Override
	public List<ItemsDTO> getList(ItemSearch certieria, ProjectionList projectionList, int start, int pageSize,
			String orderBy, Boolean desc) {
		List<ItemsDTO> list = null;
		try {

			List<Criterion> criterion = getCriteria(certieria);
			List<Object[]> listObj = getDataList(criterion, projectionList, start, pageSize, orderBy, desc);

			for (Object[] obj : listObj) {
				if (list == null) {
					list = new ArrayList<ItemsDTO>();
				}
				ItemsDTO item = new ItemsDTO();
				item.setItemId(Long.valueOf(obj[0].toString()));
				item.setItemName((String) obj[1]);
				item.setItemCode((String) obj[2]);
				item.setStatus((String) obj[3]);
				item.setItemPrice((float) obj[4]);

				list.add(item);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
