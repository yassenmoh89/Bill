package core.bill.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_SYS_APPLICATION_PURPOSE_TYPE") //[TblApplicationsPurpose]
public class ApplicationPurposeTypeDTO extends GenericDTO{

	//[AppPurposeID]
	//[AppPurposeTypeName]
	//[AppPurposeTypeNameSp]
	//
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APPLICATION_PURPOSE_TYPE_ID")
	private Long applicationPurposeTypeId ; 
	
	@Column(name = "APPLICATION_PURPOSE_TYPE")
	private String applicationPurposeType ;
	
	@Column(name = "APPLICATION_PURPOSE_TYPE_ENG")
	private String applicationPurposeTypeNameEng ; 
	
	@Column(name = "STATUS")
	private String status ;

	public Long getApplicationPurposeTypeId() {
		return applicationPurposeTypeId;
	}

	public void setApplicationPurposeTypeId(Long applicationPurposeTypeId) {
		this.applicationPurposeTypeId = applicationPurposeTypeId;
	}

	public String getApplicationPurposeType() {
		return applicationPurposeType;
	}

	public void setApplicationPurposeType(String applicationPurposeType) {
		this.applicationPurposeType = applicationPurposeType;
	}

	public String getApplicationPurposeTypeNameEng() {
		return applicationPurposeTypeNameEng;
	}

	public void setApplicationPurposeTypeNameEng(String applicationPurposeTypeNameEng) {
		this.applicationPurposeTypeNameEng = applicationPurposeTypeNameEng;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	} 
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof ApplicationPurposeTypeDTO) && (this.applicationPurposeTypeId != null)
		        ? this.applicationPurposeTypeId.equals(((ApplicationPurposeTypeDTO) obj).applicationPurposeTypeId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.applicationPurposeTypeId != null)
		        ? (this.getClass().hashCode() + this.applicationPurposeTypeId.hashCode())
		        : super.hashCode();
	}
	
}
