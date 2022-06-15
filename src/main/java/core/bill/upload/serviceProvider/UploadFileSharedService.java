package core.bill.upload.serviceProvider;

import java.util.List;

import core.bill.common.exception.SegesaServiceException;
import core.bill.upload.model.UploadFileDTO;

public interface UploadFileSharedService {
	
	public List<UploadFileDTO> loadFiles(String category , String referenceId)  throws SegesaServiceException;
	public List<UploadFileDTO> saveFile(List<UploadFileDTO> files)  throws SegesaServiceException;
	public List<UploadFileDTO> updateFile(List<UploadFileDTO> files)  throws SegesaServiceException;
	public void deleteFile(List<UploadFileDTO> files)  throws SegesaServiceException;
	
	public UploadFileDTO getFileDTO(Long key)  throws SegesaServiceException;

}
