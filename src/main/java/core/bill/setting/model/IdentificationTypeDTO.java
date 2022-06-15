package core.bill.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_SYS_IDENTIFICATION_TYPE") //TblIdentificationTypes
public class IdentificationTypeDTO extends GenericDTO{

	//[IdentificationTypeID]
	//[IdentificationTypeName]
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENTIFICATION_TYPE_ID")
	private Long identificationTypeId ; 
	
	@Column(name = "IDENTIFICATION_TYPE")
	private String identificationType ; 
	
	@Column(name = "IDENTIFICATION_TYPE_ENG")
	private String identificationTypeEng ; 
	
	@Column(name = "STATUS")
	private String status ;
	

	public Long getIdentificationTypeId() {
		return identificationTypeId;
	}

	public void setIdentificationTypeId(Long identificationTypeId) {
		this.identificationTypeId = identificationTypeId;
	}

	public String getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(String identificationType) {
		this.identificationType = identificationType;
	}

	public String getIdentificationTypeEng() {
		return identificationTypeEng;
	}

	public void setIdentificationTypeEng(String identificationTypeEng) {
		this.identificationTypeEng = identificationTypeEng;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	} 
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof IdentificationTypeDTO) && (this.getIdentificationTypeId() != null)
		        ? this.identificationTypeId.equals(((IdentificationTypeDTO) obj).identificationTypeId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.identificationTypeId != null)
		        ? (this.getClass().hashCode() + this.identificationTypeId.hashCode())
		        : super.hashCode();
	}
	
	
}
