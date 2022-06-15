package core.bill.comercial.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import core.bill.comercial.model.ContractDTO;
import core.bill.comercial.model.ContractDashBoardModel;
import core.bill.comercial.model.ContractSearch;
import core.bill.comercial.model.CustomerDTO;
import core.bill.common.dao.AbstractDAO;

@Component
public class ContractDAOImpl extends AbstractDAO<ContractDTO> implements ContractDAO{

	@Override
	public Class<ContractDTO> getClazz() {
		
		return ContractDTO.class;
	}
	
	@Override
	public ContractDTO save(ContractDTO model) {
	
		return saveModel(model);
	}

	@Override
	public void delete(ContractDTO model) {
		deleteModel(model);
	}

	@Override
	public ContractDTO update(ContractDTO model) {
		return updateModel(model, model.getContractId());
	}

	@Override
	public ContractDTO getModel(Long pk) {
	
		return getModelDTO(pk);
	}

	@Override
	public List<ContractDTO> getList(ContractSearch certieria, int start, int pageSize, String orderBy, Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);

		return getDataList(criterion, start, pageSize, orderBy, desc);
	}

	private List<Criterion> getCriteria(ContractSearch criteria) {
		List<Criterion> critList = null;
		
		if (criteria != null) {
			critList = new ArrayList<Criterion>();
			
			if(criteria.getContractCode()!=null && criteria.getContractCode().length()>0)
			{
				critList.add(Restrictions.eq("contractCode", criteria.getContractCode()));
			}
			
			if(criteria.getMeterStatusId()!=null  )
			{
				critList.add(Restrictions.eq("meterStatusDTO.meterStatusId", criteria.getMeterStatusId()));
			}
			
			if(criteria.getDirection() !=null && criteria.getDirection().length()>0 )
			{
				critList.add(Restrictions.like("direction",criteria.getDirection() , MatchMode.ANYWHERE));
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
			
			if(criteria.getCustomerCode() !=null && criteria.getCustomerCode().length()>0)
			{
				critList.add(Restrictions.like("customerDTO.customerCode", criteria.getCustomerCode(), MatchMode.EXACT));
			}
			
			if(criteria.getCutomerId() !=null )
			{
				critList.add(Restrictions.eq("customerDTO.customerId", criteria.getCutomerId()));
			}
			
			if(criteria.getLocalidadCode() !=null )
			{
				critList.add(Restrictions.in("localityDTO.localityCode", criteria.getLocalidadCode()));
			}
			
			if(criteria.getSectorCode() !=null )
			{
				critList.add(Restrictions.in("sectorDTO.sectorCode", criteria.getSectorCode()));
			}
			
			if(criteria.getCreatedDate()!=null)
			{
				try {

					SimpleDateFormat getYear = new SimpleDateFormat("yyyy");
					SimpleDateFormat getMonth = new SimpleDateFormat("MM");
					
					int year = Integer.parseInt(getYear.format(criteria.getCreatedDate()));
					int month = Integer.parseInt(getMonth.format(criteria.getCreatedDate()));
					
					//IntegerType.INSTANCE
					critList.add(Restrictions.sqlRestriction("YEAR(CREATED_DATE) = "+year));
					critList.add(Restrictions.sqlRestriction("MONTH(CREATED_DATE) = "+ month));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		return critList;
	}

	@Override
	public Long getRowNum(ContractSearch certieria) {
		String[] table1 = {"customerDTO","localityDTO"} ;
		return getRowCount(getCriteria(certieria),table1);
	}

	@Override
	public ContractDTO merge(ContractDTO model) {
		
		return mergeModel(model);
	}

	@Override
	public void saveOrUpdate(ContractDTO model) {
		saveModel(model);
	}

	@Override
	public Long getMaxRow(String fieldName) {
		// TODO Auto-generated method stub
		return getRowMax(fieldName);
	}

	@Override
	public List<ContractDTO> getList(ContractSearch certieria, ProjectionList projectionList, int start, int pageSize,
			String orderBy, Boolean desc) {

		List<ContractDTO> list =null ;
		String[] table1 = {"customerDTO","localityDTO"} ;
	
		try {
			
			
		    List<Criterion> criterion = getCriteria(certieria);
		
			List<Object[]> listObj = getDataList(criterion, projectionList,table1,start, pageSize, orderBy, desc);
			
			if(listObj !=null)
			{
			System.out.println("listObj size :"+listObj.size());
			
			
			for (Object[] obj : listObj) {
				if (list == null) {
					list = new ArrayList<ContractDTO>();
				}
				ContractDTO contract = new ContractDTO();
				contract.setContractId(Long.valueOf(obj[0].toString()));
				contract.setContractCode((String) obj[1]);
				
				if(obj[2] !=null)
				{
					
					contract.setDirection((String) obj[2]);
					
				}
				
				
				if(obj[3]!=null)
				{
					contract.setCreatedDate((Date) obj[3]);
				}
				
				if(obj[4]!=null)
				{
					contract.setContractStatus((String) obj[4]);
				}
				
				
				if(obj[5]!=null)
				{
					CustomerDTO cus = new CustomerDTO();
					cus.setCustomerCode((String) obj[5]);
					contract.setCustomerDTO(cus);
				}
				
				if(obj[6]!=null)
				{
					if(contract.getCustomerDTO()!=null)
					{
						contract.getCustomerDTO().setCustomerName((String) obj[6]);
					}
				}
				
				if(obj[7]!=null)
				{
					contract.setBeneficiary((String) obj[7]);
				}
	
				list.add(contract);
			}
	}
	} catch (Exception e) {
		e.printStackTrace();
		return list;
	}
	
	return list;
}

	@Override
	public List<ContractDTO> getCustomerContract(ContractSearch certieria, ProjectionList projectionList, int start,
			int pageSize, String orderBy, Boolean desc) {
		List<ContractDTO> list =null ;
	
	
		try {
						
		    List<Criterion> criterion = getCriteria(certieria);
		
			List<Object[]> listObj = getDataList(criterion, projectionList,null,start, pageSize, orderBy, desc);
			
			if(listObj !=null)
			{
			System.out.println("listObj size :"+listObj.size());
			
			
			for (Object[] obj : listObj) {
				if (list == null) {
					list = new ArrayList<ContractDTO>();
				}
				ContractDTO contract = new ContractDTO();
				contract.setContractId(Long.valueOf(obj[0].toString()));
				if(obj[1]!=null) {
				contract.setDirection((String) obj[1]);
				}
				if(obj[2]!=null) {
				contract.setBeneficiary((String) obj[2]);
				}
				list.add(contract);
			}
	}
	} catch (Exception e) {
		e.printStackTrace();
		return list;
	}
	
	return list;
}

	@Override
	public List<ContractDashBoardModel> getNumOfClientsByLocation(String loaclId) {
		List<ContractDashBoardModel> list =null ;
		
		
		try {
			
			List<Object[]> listObj = getCountByField(loaclId,null);
			
			
			if(listObj !=null)
			{
			System.out.println("listObj size :"+listObj.size());
			
			for (Object[] obj : listObj) {
				

				if (list == null) {
					list = new ArrayList<ContractDashBoardModel>();
				}
				
				ContractDashBoardModel contract = new ContractDashBoardModel();
				if(obj[0]!=null) {
				 contract.setCount(Long.valueOf(obj[0].toString()));
				}
								
				if(obj[1]!=null) {
					contract.setContId( (Long) (obj[1]));
				}
			

				list.add(contract);
			}
	}
	} catch (Exception e) {
		e.printStackTrace();
		return list;
	}
	
	return list;
	}
	
@Override
public Long getCountOfContracts(ContractSearch certieria) {
	Long list =null ;
	
	
	try {
		List<Criterion> criterion = getCriteria(certieria);
		 list = getCount(criterion);		
	} catch (Exception e) {
	e.printStackTrace();
	return list;
}

return list;
}


}
