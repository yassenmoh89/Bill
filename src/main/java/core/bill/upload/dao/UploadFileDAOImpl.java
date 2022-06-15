package core.bill.upload.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.upload.model.FileSearch;
import core.bill.upload.model.UploadFileDTO;

@Component
public class UploadFileDAOImpl extends AbstractDAO<UploadFileDTO> implements UploadFileDAO{

	@Override
	public Class<UploadFileDTO> getClazz() {
		
		return UploadFileDTO.class;
	}
	
	@Override
	public UploadFileDTO save(UploadFileDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(UploadFileDTO model) {
		deleteModel(model);
	}

	@Override
	public UploadFileDTO update(UploadFileDTO model) {
		return updateModel(model, model.getUpoladFileId());
	}

	@Override
	public UploadFileDTO getModel(Long pk) {
		
		return getModelDTO(pk);
	}

	@Override
	public List<UploadFileDTO> getList(FileSearch certieria, int start, int pageSize, String orderBy, Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		return getDataList(criterion, start, pageSize, orderBy, desc);
		
	}

	private List<Criterion> getCriteria(FileSearch certieria) {
		List<Criterion> critList = null;

		if (certieria != null) {
			critList = new ArrayList<Criterion>();

			if (certieria.getCategory() != null && certieria.getCategory().length()> 0) {
				
					critList.add(Restrictions.ilike("category", certieria.getCategory(),MatchMode.EXACT));
				}
			
			if (certieria.getReferenceId() != null && certieria.getReferenceId().length()> 0) {
				
				critList.add(Restrictions.ilike("referenceId", certieria.getReferenceId(),MatchMode.EXACT));
			}
			
		}
		return critList;
	}

	@Override
	public Long getRowNum(FileSearch certieria) {
		return getRowCount(getCriteria(certieria));
	}

	@Override
	public UploadFileDTO merge(UploadFileDTO model) {
		return null;
	}

	@Override
	public void saveOrUpdate(UploadFileDTO model) {
		saveOrUpdateModel(model);
	
	}

	@Override
	public List<UploadFileDTO> loadFiles(String category, String referenceId) {
		List<Criterion> criterion = getCriteriaLoad(category ,referenceId);
		return getDataList(criterion, 0, 0, "upoladFileId", Boolean.TRUE);
	}

	private List<Criterion> getCriteriaLoad(String category, String referenceId) {
		List<Criterion> critList = new ArrayList<Criterion>();


			if (category != null && category.length()> 0) {
				
					critList.add(Restrictions.ilike("category", category,MatchMode.EXACT));
				}
			
			if (referenceId != null && referenceId.length()> 0) {
				
				critList.add(Restrictions.ilike("referenceId", referenceId,MatchMode.EXACT));
			}
			
		return critList;
	}

}
