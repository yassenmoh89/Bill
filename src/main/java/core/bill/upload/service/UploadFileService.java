package core.bill.upload.service;

import java.util.List;

import core.bill.common.exception.SegesaServiceException;
import core.bill.common.service.GenericService;
import core.bill.upload.model.FileSearch;
import core.bill.upload.model.UploadFileDTO;

public interface UploadFileService extends GenericService<UploadFileDTO, FileSearch> {

	public List<UploadFileDTO> loadFiles(String category , String referenceId)  throws SegesaServiceException;
	
}
