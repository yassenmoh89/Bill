package core.bill.upload.dao;

import java.util.List;

import core.bill.common.dao.GenericDAO;
import core.bill.upload.model.FileSearch;
import core.bill.upload.model.UploadFileDTO;

public interface UploadFileDAO extends GenericDAO<UploadFileDTO, FileSearch>{
	public List<UploadFileDTO> loadFiles(String category , String referenceId);
}
