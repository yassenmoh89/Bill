package core.bill.upload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.upload.dao.UploadFileDAO;
import core.bill.upload.model.FileSearch;
import core.bill.upload.model.UploadFileDTO;
@Service("uploadFileService")
public class UploadFileServiceImpl implements UploadFileService{

	@Autowired
	UploadFileDAO uploadFileDAO ; 
	
	@Override
	@Transactional(readOnly = false)
	public UploadFileDTO saveModel(UploadFileDTO model) throws SegesaServiceException {
		UploadFileDTO uploadFileDTO = null;
		try {
			uploadFileDTO = uploadFileDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save uploadFileDTO Error :" + e);
		}
		return uploadFileDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteModel(UploadFileDTO model) throws SegesaServiceException {
		
		try {
			   uploadFileDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete uploadFileDTO Error :" + e);
		}
		
		
	}

	@Override
	@Transactional(readOnly = false)
	public UploadFileDTO updateModel(UploadFileDTO model) throws SegesaServiceException {
		UploadFileDTO uploadFileDTO = null;
		try {
			uploadFileDTO = uploadFileDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update uploadFileDTO Error :" + e);
		}
		return uploadFileDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public UploadFileDTO getModelDTO(Long pk) throws SegesaServiceException {
		UploadFileDTO uploadFileDTO = null;
		try {
			uploadFileDTO = uploadFileDAO.getModel(pk) ;
		} catch (Exception e) {
			throw new SegesaServiceException("get uploadFileDTO Error :" + e);
		}
		return uploadFileDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public UploadFileDTO getMergeMode(UploadFileDTO model) throws SegesaServiceException {
		UploadFileDTO uploadFileDTO = null;
		try {
			uploadFileDTO = uploadFileDAO.merge(model);
		} catch (Exception e) {
			throw new SegesaServiceException("merge uploadFileDTO Error :" + e);
		}
		return uploadFileDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void saveOrUpdateMode(UploadFileDTO model) throws SegesaServiceException {
		
		try {
			 uploadFileDAO.saveOrUpdate(model);
		} catch (Exception e) {
			throw new SegesaServiceException("saveOrUpdate uploadFileDTO Error :" + e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<UploadFileDTO> getDataList(FileSearch critiria, int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<UploadFileDTO> list = null;
		try {
			list = uploadFileDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search uploadFileDTO Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UploadFileDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<UploadFileDTO> list = null;
		try {
			list = uploadFileDAO.getList(null, start, pageSize, "upoladFileId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search uploadFileDTO Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UploadFileDTO> getDataList() throws SegesaServiceException {
		List<UploadFileDTO> list = null;
		try {
			list = uploadFileDAO.getList(null, 0, 0, "upoladFileId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search uploadFileDTO Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UploadFileDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<UploadFileDTO> list = null;
		try {
			list = uploadFileDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search uploadFileDTO Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getRowCount(FileSearch critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = uploadFileDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("save uploadFileDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(UploadFileDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(UploadFileDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(UploadFileDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(FileSearch critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UploadFileDTO> loadFiles(String category, String referenceId) {
		List<UploadFileDTO> list = null;
		try {
			list = uploadFileDAO.loadFiles(category, referenceId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
