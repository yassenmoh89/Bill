package core.bill.study.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_STUDY_TECHNICALS")
public class StudyTechnicalDTO extends GenericDTO{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TECHNICAL_ID", nullable = false,columnDefinition = "BIGINT")
	private Long technicalId ; 
	
	@Column(name="TECHNICAL_NAME")
	private String technicalName ;
	
	@Column(name="TECHNICAL_CODE")
	private String technicalCode ;
	
	@Column(name="PHONE")
	private String phone ;

	@Column(name="STATUS")
	private String status ;


	public Long getTechnicalId() {
		return technicalId;
	}


	public void setTechnicalId(Long technicalId) {
		this.technicalId = technicalId;
	}


	public String getTechnicalName() {
		return technicalName;
	}


	public void setTechnicalName(String technicalName) {
		this.technicalName = technicalName;
	}


	public String getTechnicalCode() {
		return technicalCode;
	}


	public void setTechnicalCode(String technicalCode) {
		this.technicalCode = technicalCode;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}
