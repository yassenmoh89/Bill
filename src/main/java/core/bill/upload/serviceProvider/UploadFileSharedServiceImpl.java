package core.bill.upload.serviceProvider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.upload.model.UploadFileDTO;
import core.bill.upload.service.UploadFileService;
@Service("uploadFileSharedService")
public class UploadFileSharedServiceImpl implements UploadFileSharedService{

	@Autowired
	UploadFileService uploadFileService ; 
	
	@Override
	@Transactional(readOnly = true)
	public List<UploadFileDTO> loadFiles(String category, String referenceId) throws SegesaServiceException {
		List<UploadFileDTO> list = null;
		try {
			list = uploadFileService.loadFiles(category, referenceId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	@Transactional(readOnly = false)
	public List<UploadFileDTO> saveFile(List<UploadFileDTO> files) throws SegesaServiceException {
		List<UploadFileDTO> list = null;
		try {
			if(files!=null && files.size()>0)
			{
				list = new ArrayList<>() ;
				for(UploadFileDTO file:files)
				{
					if(file.getUpoladFileId()==null)
					{
					UploadFileDTO obj = uploadFileService.saveModel(file);
					list.add(obj);
					}else
					{
						list.add(file);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	@Transactional(readOnly = false)
	public List<UploadFileDTO> updateFile(List<UploadFileDTO> files) throws SegesaServiceException {
		List<UploadFileDTO> list = null;
		try {
			if(files!=null && files.size()>0)
			{
				list = new ArrayList<>() ;
				for(UploadFileDTO file:files)
				{
					UploadFileDTO obj = uploadFileService.updateModel(file);
					list.add(obj);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteFile(List<UploadFileDTO> files) throws SegesaServiceException {
		
		try {
			if(files!=null && files.size()>0)
			{
				
				for(UploadFileDTO file:files)
				{
					if(file.getUpoladFileId()!=null)
					{
					 uploadFileService.deleteModel(file);
					}
				
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	@Transactional(readOnly = true)
	public UploadFileDTO getFileDTO(Long key) throws SegesaServiceException {
		UploadFileDTO fileDto = null;
		try {
			
			fileDto = uploadFileService.getModelDTO(key);
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileDto;
	}

}
