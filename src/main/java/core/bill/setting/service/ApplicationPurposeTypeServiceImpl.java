package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.ApplicationPurposeTypeDAO;
import core.bill.setting.model.ApplicationPurposeTypeDTO;
import core.bill.setting.model.CommonSearchDTO;

@Service
@Transactional(readOnly = true)
public class ApplicationPurposeTypeServiceImpl implements ApplicationPurposeTypeService{

	@Autowired
	ApplicationPurposeTypeDAO applicationPurposeTypeDAO ;

	@Override
	public ApplicationPurposeTypeDTO saveModel(ApplicationPurposeTypeDTO model) throws SegesaServiceException {
		
		return applicationPurposeTypeDAO.save(model);
	}

	@Override
	public void deleteModel(ApplicationPurposeTypeDTO model) throws SegesaServiceException {
		applicationPurposeTypeDAO.delete(model);
		
	}

	@Override
	public ApplicationPurposeTypeDTO updateModel(ApplicationPurposeTypeDTO model) throws SegesaServiceException {
		return applicationPurposeTypeDAO.update(model);
	}

	@Override
	public ApplicationPurposeTypeDTO getModelDTO(Long pk) throws SegesaServiceException {
		
		return applicationPurposeTypeDAO.getModel(pk);
	}

	@Override
	public List<ApplicationPurposeTypeDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize,
			String orderBy, Boolean desc) throws SegesaServiceException {
		
		return applicationPurposeTypeDAO.getList(critiria, start, pageSize, orderBy,desc);
	}

	@Override
	public List<ApplicationPurposeTypeDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		
		return applicationPurposeTypeDAO.getList(null, start, pageSize, "applicationPurposeTypeId",Boolean.TRUE);
	}

	@Override
	public List<ApplicationPurposeTypeDTO> getDataList() throws SegesaServiceException {
		return applicationPurposeTypeDAO.getList(null, 0, 0, "applicationPurposeTypeId",Boolean.TRUE);
	}

	@Override
	public List<ApplicationPurposeTypeDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		return applicationPurposeTypeDAO.getList(null, start, pageSize, orderBy,desc);
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		return applicationPurposeTypeDAO.getRowNum(critiria);
	}

	@Override
	public List<String> valideSave(ApplicationPurposeTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(ApplicationPurposeTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(ApplicationPurposeTypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplicationPurposeTypeDTO getMergeMode(ApplicationPurposeTypeDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(ApplicationPurposeTypeDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
