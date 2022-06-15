package core.bill.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_SYS_METER_STATUS") //TC Estado
public class MeterStatusDTO extends GenericDTO{

	//[Estado]
	//[Descripcion]
	//[Descripcion_Eng]
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="METER_STATUS_ID")
	private Long meterStatusId;
	
	@Column(name="STATUS_CODE")
	private String statusCode ; 
	
	@Column(name="DESCRIPTION")
	private String description ;
	
	@Column(name="DESCRIPTION_ENG")
	private String descriptionEng ; 
	
	@Column(name="STATUS")
	private String status ;

	

	public Long getMeterStatusId() {
		return meterStatusId;
	}

	public void setMeterStatusId(Long meterStatusId) {
		this.meterStatusId = meterStatusId;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionEng() {
		return descriptionEng;
	}

	public void setDescriptionEng(String descriptionEng) {
		this.descriptionEng = descriptionEng;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	} 
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof MeterStatusDTO) && (this.getMeterStatusId() != null)
		        ? this.meterStatusId.equals(((MeterStatusDTO) obj).meterStatusId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.meterStatusId != null)
		        ? (this.getClass().hashCode() + this.meterStatusId.hashCode())
		        : super.hashCode();
	}
	
}
