package core.bill.invoice.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.invoice.model.InvoiceSearch;
import core.bill.invoice.model.ReadingDTO;

@Component
public class ReadingDAOImpl extends AbstractDAO<ReadingDTO> implements ReadingDAO{

	@Override
	public Class<ReadingDTO> getClazz() {
		return ReadingDTO.class;
	}
	
	@Override
	public ReadingDTO save(ReadingDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(ReadingDTO model) {
		deleteModel(model);
	}

	@Override
	public ReadingDTO update(ReadingDTO model) {
		
		return updateModel(model, model.getReadingId());
	}

	@Override
	public ReadingDTO getModel(Long pk) {
	
		return getModelDTO(pk);
	}

	@Override
	public List<ReadingDTO> getList(InvoiceSearch certieria, int start, int pageSize, String orderBy, Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		return getDataList(criterion, start, pageSize, orderBy, desc);
	}

	//---------------------------------------------
	private List<Criterion> getCriteria(InvoiceSearch certieria) {
		List<Criterion> critList = null;

		if (certieria != null) {
			critList = new ArrayList<Criterion>();

			if (certieria.getContractId() != null && certieria.getContractId() > 0) {
				critList.add(Restrictions.eq("contractId", certieria.getContractId()));
			}

			if (certieria.getCustomerId() != null && certieria.getCustomerId() > 0) {
				critList.add(Restrictions.eq("customerId", certieria.getCustomerId()));
			}
			
			if(certieria.getMonth()!=null && certieria.getMonth()>0)
			{
				critList.add(Restrictions.eq("month", certieria.getMonth()));
			}
			
			if(certieria.getYear()!=null && certieria.getYear()>0)
			{
				critList.add(Restrictions.eq("year", certieria.getYear()));
			}

		}
		return critList;
	}

	@Override
	public Long getRowNum(InvoiceSearch certieria) {
	
		return getRowCount(getCriteria(certieria));
	}

	@Override
	public ReadingDTO merge(ReadingDTO model) {
	
		return mergeModel(model);
	}

	@Override
	public void saveOrUpdate(ReadingDTO model) {
		saveOrUpdateModel(model);
	}

}
