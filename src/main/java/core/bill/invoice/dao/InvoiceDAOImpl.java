package core.bill.invoice.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import core.bill.comercial.model.ContractDashBoardModel;
import core.bill.common.dao.AbstractDAO;
import core.bill.invoice.model.InvoiceDTO;
import core.bill.invoice.model.InvoiceSearch;

@Component
public class InvoiceDAOImpl extends AbstractDAO<InvoiceDTO> implements InvoiceDAO{

	@Override
	public Class<InvoiceDTO> getClazz() {
		return InvoiceDTO.class;
	}
	
	@Override
	public InvoiceDTO save(InvoiceDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(InvoiceDTO model) {
		deleteModel(model);
	}

	@Override
	public InvoiceDTO update(InvoiceDTO model) {
		return updateModel(model, model.getInvoiceId());
	}

	@Override
	public InvoiceDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<InvoiceDTO> getList(InvoiceSearch certieria, int start, int pageSize, String orderBy, Boolean desc) {
		List<InvoiceDTO> list=null; 
		try {
			List<Criterion> criterion = getCriteria(certieria);
			list = getDataList(criterion, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list ;
	}

	private List<Criterion> getCriteria(InvoiceSearch certieria) {
		List<Criterion> critList = null;
		
		if(certieria !=null)
		{
			critList = new ArrayList<Criterion>();
			
			if(certieria.getContractId()!=null && certieria.getContractId()>0)
			{
				critList.add(Restrictions.eq("contractId", certieria.getContractId()));
			}
			
			if(certieria.getCustomerId()!=null && certieria.getCustomerId()>0)
			{
				critList.add(Restrictions.eq("customerId", certieria.getCustomerId()));
			}
			
			if(certieria.getEliminable()!=null)
			{
				critList.add(Restrictions.eq("eliminable", certieria.getEliminable()));
			}
			
			if(certieria.getEliminableDate()!=null)
			{
				try {

					SimpleDateFormat getYear = new SimpleDateFormat("yyyy");
					SimpleDateFormat getMonth = new SimpleDateFormat("MM");
					
					

					int year = Integer.parseInt(getYear.format(certieria.getEliminableDate()));
					int month = Integer.parseInt(getMonth.format(certieria.getEliminableDate()));
					
					//IntegerType.INSTANCE
					critList.add(Restrictions.sqlRestriction("YEAR(ELIMINABLE_DATE) = "+year));
					critList.add(Restrictions.sqlRestriction("MONTH(ELIMINABLE_DATE) = "+ month));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(certieria.getPaymentStatus()!=null)
			{
				critList.add(Restrictions.eq("paymentStatus", certieria.getPaymentStatus()));
			}
			
			if(certieria.getInvoiceCreated()!=null)
			{
				critList.add(Restrictions.eq("createdInvoice", certieria.getInvoiceCreated()));
			}
			
			if(certieria.getFromDate()!=null)
			{
				critList.add(Restrictions.ge("createdDate", certieria.getFromDate()));
			}
			
			if(certieria.getToDate()!=null)
			{
				critList.add(Restrictions.le("createdDate", certieria.getToDate()));
			}
			
			if(certieria.getLocalityId()!=null)
			{
				critList.add(Restrictions.eq("localityId", certieria.getLocalityId()));
			}
			
			if(certieria.getCreatedDate()!=null)
			{
				try {

					SimpleDateFormat getYear = new SimpleDateFormat("yyyy");
					SimpleDateFormat getMonth = new SimpleDateFormat("MM");
					
					

					int year = Integer.parseInt(getYear.format(certieria.getCreatedDate()));
					int month = Integer.parseInt(getMonth.format(certieria.getCreatedDate()));
					
					//IntegerType.INSTANCE
					critList.add(Restrictions.sqlRestriction("YEAR(CREATED_DATE) = "+year));
					critList.add(Restrictions.sqlRestriction("MONTH(CREATED_DATE) = "+ month));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if(certieria.getPaidDate()!=null)
			{
				try {

					SimpleDateFormat getYear = new SimpleDateFormat("yyyy");
					SimpleDateFormat getMonth = new SimpleDateFormat("MM");
					
					

					int year = Integer.parseInt(getYear.format(certieria.getPaidDate()));
					int month = Integer.parseInt(getMonth.format(certieria.getPaidDate()));
					
					//IntegerType.INSTANCE
					critList.add(Restrictions.sqlRestriction("YEAR(PAID_DATE) = "+year));
					critList.add(Restrictions.sqlRestriction("MONTH(PAID_DATE) = "+ month));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		return critList;
	}

	@Override
	public Long getRowNum(InvoiceSearch certieria) {
		return getRowCount(getCriteria(certieria));
	}

	@Override
	public InvoiceDTO merge(InvoiceDTO model) {
		return mergeModel(model);
	}

	@Override
	public void saveOrUpdate(InvoiceDTO model) {
		saveOrUpdateModel(model);
		
	}

	@Override
	public Double getInvoicePendingAmount(InvoiceSearch certieria, String fieldName) {
		return getSumPendingAmount(getCriteria(certieria)	, fieldName);
	}

	@Override
	public List<ContractDashBoardModel> getSumOfPendAndIncomeByField(String fieldName, String sum1, String sum2,
			InvoiceSearch certieria) {
		List<ContractDashBoardModel> list = null;

		try {

			List<Criterion> critList = null;

			if (certieria != null) {
				critList = new ArrayList<Criterion>();
				if (certieria.getMonth() != null) {
					critList.add(Restrictions.eq("month", certieria.getMonth()));
				}

				if (certieria.getYear() != null) {
					critList.add(Restrictions.eq("year", certieria.getYear()));
				}
				
				if(certieria.getLocalityId()!=null)
				{
					critList.add(Restrictions.ne("localityId", certieria.getLocalityId()));
				}
				
				if(certieria.getPaymentStatus()!=null)
				{
					critList.add(Restrictions.eq("paymentStatus", certieria.getPaymentStatus()));
				}
			}

			List<Object[]> listObj = getSumAndCountByField(fieldName, sum1, sum2, critList);

			if (listObj != null) {
				System.out.println("getSumOfPendAndIncomeByField size :" + listObj.size());

				for (Object[] obj : listObj) {

					if (list == null) {
						list = new ArrayList<ContractDashBoardModel>();
					}

					ContractDashBoardModel inv = new ContractDashBoardModel();
					if (obj[0] != null) {
						inv.setCount(Long.valueOf(obj[0].toString()));
					}

					if (obj[1] != null) {
						inv.setFirstSum((Double)(obj[1]));
					}

					if (obj[2] != null) {
						inv.setSecondsum((Double) (obj[2]));
					}

					if (obj[3] != null) {
						inv.setContId((Long) (obj[3]));
					}

					list.add(inv);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}

		return list;

	}

	@Override
	public Long getCountOfInvoices(InvoiceSearch certieria) {
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

	@Override
	public Double getInvoiceSumAmount(InvoiceSearch certieria, String fieldName) {
		// TODO Auto-generated method stub
		return getSumPendingAmount(getCriteria(certieria)	, fieldName);
	}

}
