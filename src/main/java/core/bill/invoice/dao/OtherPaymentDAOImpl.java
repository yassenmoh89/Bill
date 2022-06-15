package core.bill.invoice.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.invoice.model.InvoiceSearch;
import core.bill.invoice.model.OtherPaymentDTO;

@Component
public class OtherPaymentDAOImpl extends AbstractDAO<OtherPaymentDTO> implements OtherPaymentDAO{

	@Override
	public Class<OtherPaymentDTO> getClazz() {
	
		return OtherPaymentDTO.class;
	}
	@Override
	public OtherPaymentDTO save(OtherPaymentDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(OtherPaymentDTO model) {
		deleteModel(model);
		
	}

	@Override
	public OtherPaymentDTO update(OtherPaymentDTO model) {
		return updateModel(model, model.getOtherPaymentId());
	}

	@Override
	public OtherPaymentDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<OtherPaymentDTO> getList(InvoiceSearch certieria, int start, int pageSize, String orderBy,
			Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		return getDataList(criterion, start, pageSize, orderBy, desc);
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
		}
		return critList;
	}
	@Override
	public Long getRowNum(InvoiceSearch certieria) {
		return getRowCount(getCriteria(certieria));
	}

	@Override
	public OtherPaymentDTO merge(OtherPaymentDTO model) {
		return mergeModel(model);
	}

	@Override
	public void saveOrUpdate(OtherPaymentDTO model) {
		saveOrUpdateModel(model);
		
	}

	

	
}
