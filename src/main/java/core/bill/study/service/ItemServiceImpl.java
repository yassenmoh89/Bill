package core.bill.study.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.common.util.CustomUtil;
import core.bill.study.dao.ItemsDAO;
import core.bill.study.model.ItemSearch;
import core.bill.study.model.ItemsDTO;


@Service("itemService")
public class ItemServiceImpl implements ItemService{

	@Autowired
	ItemsDAO itemsDAO;
	
	public ItemServiceImpl() {
		System.out.println("items DAO"+itemsDAO);
	}
	
	@Override
	@Transactional(readOnly = false)
	public ItemsDTO saveModel(ItemsDTO model) throws SegesaServiceException {
		ItemsDTO itemDTO = null;
		try {
			itemDTO = itemsDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error save itemDTO : " + e);
		}

		return itemDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteModel(ItemsDTO model) throws SegesaServiceException {
		try {
			itemsDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error delete groupDTO : " + e);
		}

	}

	@Override
	@Transactional(readOnly = false)
	public ItemsDTO updateModel(ItemsDTO model) throws SegesaServiceException {
		ItemsDTO itemDTO = null;
		try {
			itemDTO = itemsDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error update itemDTO : " + e);
		}

		return itemDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public ItemsDTO getModelDTO(Long pk) throws SegesaServiceException {
		ItemsDTO itemDTO = null;
		try {
			itemDTO = itemsDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("Error get groupDTO : " + e);
		}
		return itemDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public ItemsDTO getMergeMode(ItemsDTO model) throws SegesaServiceException {
		ItemsDTO itemDTO = null;
		try {
			itemDTO = itemsDAO.merge(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error merge itemDTO : " + e);
		}

		return itemDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void saveOrUpdateMode(ItemsDTO model) throws SegesaServiceException {
		try {
			itemsDAO.saveOrUpdate(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Error saveOrUpdate itemsDTO : " + e);
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<ItemsDTO> getDataList(ItemSearch critiria, int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<ItemsDTO> list = null;
		try {
			
			ProjectionList projectionList = Projections.projectionList(); 
			
			Projection groupIdProj = Projections.property("itemId"); 
			Projection groupNameProj = Projections.property("itemName"); 
			Projection groupCodeProj = Projections.property("itemCode"); 
			Projection statusProj = Projections.property("status"); 
			Projection priceProj = Projections.property("itemPrice"); 
			
			projectionList.add(groupIdProj);
			projectionList.add(groupNameProj);
			projectionList.add(groupCodeProj);
			projectionList.add(statusProj);
			projectionList.add(priceProj);
			
			list = itemsDAO.getList(critiria,projectionList, start, pageSize, orderBy, desc);
			
		} catch (Exception e) {
			throw new SegesaServiceException("Error search itemDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ItemsDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<ItemsDTO> list = null;
		try {
			list = itemsDAO.getList(null, start, pageSize, "itemId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search itemDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ItemsDTO> getDataList() throws SegesaServiceException {
		List<ItemsDTO> list = null;
		try {
			list = itemsDAO.getList(null, 0, 0, "itemId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search itemsDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ItemsDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<ItemsDTO> list = null;
		try {
			list = itemsDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Error search itemsDTO : " + e);
		}

		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getRowCount(ItemSearch critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = itemsDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("Error row count itemsDTO : " + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(ItemsDTO model) {
		List<String> error = new ArrayList<String>();

		if (model.getItemName() == null || model.getItemName().equals("")) {
			String[] params = { CustomUtil.getBundlMSGValue("group.name") };
			error.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		return error;
	}

	@Override
	public List<String> valideUpdate(ItemsDTO model) {
		List<String> error = new ArrayList<String>();
		
		if(model==null)
		{
			error.add(CustomUtil.getBundlMSGValue("se.common.invalid.data.form"));
		}

		if (model.getItemName() == null || model.getItemName().equals("")) {
			String[] params = { CustomUtil.getBundlMSGValue("group.name") };
			error.add(CustomUtil.getBundlMSGValue("value.required", params));
		}
		return error;
	}

	@Override
	public List<String> valideDelete(ItemsDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(ItemSearch critiria) {
		// TODO Auto-generated method stub
		return null;
	}

}
