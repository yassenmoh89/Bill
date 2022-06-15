package core.bill.invoice.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.invoice.model.InvoiceSearch;
import core.bill.invoice.model.PaymentDTO;

@Component
public class PaymentDAOImpl extends AbstractDAO<PaymentDTO> implements PaymentDAO{

	@Override
	public Class<PaymentDTO> getClazz() {
		return PaymentDTO.class;
	}
	
	@Override
	public PaymentDTO save(PaymentDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(PaymentDTO model) {
		deleteModel(model);
	}

	@Override
	public PaymentDTO update(PaymentDTO model) {
		return updateModel(model, model.getPaymentId());
	}

	@Override
	public PaymentDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<PaymentDTO> getList(InvoiceSearch certieria, int start, int pageSize, String orderBy, Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		return getDataList(criterion, start, pageSize, orderBy, desc);
	}

	private List<Criterion> getCriteria(InvoiceSearch certieria) {
		List<Criterion> critList = null;
		
		if(certieria !=null)
		{
			critList = new ArrayList<Criterion>();
			
			if(certieria.getInvoiceId() !=null && certieria.getInvoiceId()>0)
			{
				critList.add(Restrictions.eq("referenceId", certieria.getInvoiceId()));
			}
			
			if(certieria.getPaymentDesc() !=null)
			{
				critList.add(Restrictions.eq("paymentStatus", certieria.getPaymentDesc()));
			}
						
		}
		return critList;
	}

	@Override
	public Long getRowNum(InvoiceSearch certieria) {
		return getRowCount(getCriteria(certieria));
	}

	@Override
	public PaymentDTO merge(PaymentDTO model) {
		return mergeModel(model);
	}

	@Override
	public void saveOrUpdate(PaymentDTO model) {
		saveOrUpdateModel(model);
		
	}

}
