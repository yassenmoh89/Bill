package core.bill.upload.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name = "SEG_UPLOAD_FILE")

public class UploadFileDTO extends GenericDTO{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long upoladFileId ; 
	
	@Column(name="PATH")
	private String path ; 
	
	@Column(name = "FOLDER")
	private String folder ; 
	
	@Column(name = "FILE_NAME")
	private String fileName ; 
	
	@Column(name = "CATEGORY")
	private String category; 
	
	@Column(name = "REFERENCE_ID")
	private String referenceId ; 
	
	@Column(name = "SIZE")
	private long 	size ;

	

	public String getPath() {
		return path;
	}

	public String getFolder() {
		return folder;
	}

	public String getFileName() {
		return fileName;
	}

	public String getCategory() {
		return category;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public long getSize() {
		return size;
	}

	public Long getUpoladFileId() {
		return upoladFileId;
	}

	public void setUpoladFileId(Long upoladFileId) {
		this.upoladFileId = upoladFileId;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public void setSize(long size) {
		this.size = size;
	}
	
}
