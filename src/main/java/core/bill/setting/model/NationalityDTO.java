package core.bill.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name = "SEG_SYS_NATIONALITY") //[TblNationalities]
public class NationalityDTO extends GenericDTO{

	//[NationalityID]
	//[NationalityName]
	//[NationalityCode]
	//
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="NATIONALITY_ID")
	private Long nationalityId ; 
	
	@Column(name="NATIONALITY_NAME")
	private String nationalityName ; 
	
	@Column(name="NATIONALITY_CODE")
	private String nationalityCode ; 
	
	@Column(name="STATUS")
	private String status ;
	

	public Long getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(Long nationalityId) {
		this.nationalityId = nationalityId;
	}

	public String getNationalityName() {
		return nationalityName;
	}

	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}

	public String getNationalityCode() {
		return nationalityCode;
	}

	public void setNationalityCode(String nationalityCode) {
		this.nationalityCode = nationalityCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	} 
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof NationalityDTO) && (this.getNationalityId() != null)
		        ? this.nationalityId.equals(((NationalityDTO) obj).nationalityId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.nationalityId != null)
		        ? (this.getClass().hashCode() + this.nationalityId.hashCode())
		        : super.hashCode();
	}
	
}
