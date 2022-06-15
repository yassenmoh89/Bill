package core.bill.comercial.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import core.bill.comercial.model.CustomerDTO;
import core.bill.comercial.model.CustomerSearch;
import core.bill.common.dao.AbstractDAO;

@Component
public class CustomerDAOImpl extends AbstractDAO<CustomerDTO> implements CustomerDAO{

	@Override
	public Class<CustomerDTO> getClazz() {
		return CustomerDTO.class;
	}
	String[] table= null ;
	
	@Override
	public CustomerDTO save(CustomerDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(CustomerDTO model) {
		deleteModel(model);
		
	}

	@Override
	public CustomerDTO update(CustomerDTO model) {
		return updateModel(model, model.getCustomerId());
	}

	@Override
	public CustomerDTO getModel(Long pk) {

		return getModelDTO(pk);
	}

	@Override
	public List<CustomerDTO> getList(CustomerSearch certieria, int start, int pageSize, String orderBy, Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		
		return getDataList(criterion,table ,start, pageSize, orderBy, desc);
	}
	


	private List<Criterion> getCriteria(CustomerSearch criteria) {
		List<Criterion> critList = null;
		if (criteria != null) {
			critList = new ArrayList<Criterion>();
			
			if(criteria.getCustomerId()!=null)
			{
				critList.add(Restrictions.eq("customerId", criteria.getCustomerId()));
			}
			
			if(criteria.getCustomerCode()!=null && criteria.getCustomerCode().length()>0 )
			{
				critList.add(Restrictions.like("customerCode", criteria.getCustomerCode(),MatchMode.ANYWHERE));
			}
			
			if(criteria.getCustomerName()!=null && criteria.getCustomerName().length()>0 )
			{
				critList.add(Restrictions.like("customerName",criteria.getCustomerName() , MatchMode.ANYWHERE));
			}
			
			if(criteria.getDirection()!=null && criteria.getDirection().length()>0 )
			{
				critList.add(Restrictions.like("calle", criteria.getDirection(), MatchMode.ANYWHERE));
			}
			
			if(criteria.getPhone()!=null && criteria.getPhone().length()>0 )
			{
				critList.add(Restrictions.like("phone", criteria.getPhone(), MatchMode.EXACT));
			}
			
			if(criteria.getCreatedFrom()!=null)
			{
				critList.add(Restrictions.ge("createdDate", criteria.getCreatedFrom()));
			}
			
			if(criteria.getCreatedTo()!=null)
			{
				critList.add(Restrictions.le("createdDate", criteria.getCreatedTo()));
			}
			if(criteria.getLocalityId()!=null)
			{
				critList.add(Restrictions.eq("localityDTO.localityId", criteria.getLocalityId())) ;
				
			}
			
		}
		return critList;
	}

	@Override
	public Long getRowNum(CustomerSearch certieria) {
		String[] table1 =null ;
		
		if(certieria!=null)
		{
			if(certieria.getLocalityId()!=null)
			{
				table1 =new String[0] ;
				table1[0]= "localityDTO" ;
			}
			
		}

		return getRowCount(getCriteria(certieria),table1);
	}
	
	

	@Override
	public CustomerDTO merge(CustomerDTO model) {
		return mergeModel(model);
	}

	@Override
	public void saveOrUpdate(CustomerDTO model) {
		saveOrUpdateModel(model);
		
	}

	@Override
	public Long getMaxRow(String fieldName) {
		
		return getRowMax(fieldName);
	}

	@Override
	public List<CustomerDTO> getList(CustomerSearch certieria, ProjectionList projectionList, 
			int start, int pageSize, String orderBy, Boolean desc) {
		
			List<CustomerDTO> list =null ;
			String[] table1 = null ;
		try {
			
				List<Criterion> criterion = getCriteria(certieria);
				
				if(certieria!=null)
				{
					if(certieria.getLocalityId()!=null)
					{
						table1 =new String[0] ;
						table1[0]= "localityDTO" ;
					}
					
				}


			
				List<Object[]> listObj = getDataList(criterion, projectionList,table1,start, pageSize, orderBy, desc);
				
				System.out.println("listObj size :"+listObj.size());
				for (Object[] obj : listObj) {
					if (list == null) {
						list = new ArrayList<CustomerDTO>();
					}
					CustomerDTO customer = new CustomerDTO();
					customer.setCustomerId(Long.valueOf(obj[0].toString()));
					customer.setCustomerName((String) obj[1]);
					customer.setCustomerCode((String) obj[2]);
					
					if(obj[3]!=null)
					{
						customer.setPhone((String) obj[3]);
					}
					
					if(obj[4]!=null)
					{
						customer.setCalle((String) obj[4]);
					}
					
					/*if(obj[5]!=null)
					{
						customer.setLocalityDTO(new LocalityDTO());
						customer.getLocalityDTO().setDescription((String) obj[5]);
					}*/
		
					list.add(customer);
					
		}
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
		
		return list;
	}

}
