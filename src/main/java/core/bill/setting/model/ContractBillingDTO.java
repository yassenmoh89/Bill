package core.bill.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="SEG_SYS_CONTRACT_BILLING")
public class ContractBillingDTO {  //TC FacturacioN
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CONTRACT_BILLING_ID")
	private Long contractBillingId ;
	
	@Column(name="DESCRIPTION")
	private String description ; 
	
	@Column(name="DESCRIPTION_ENG")
	private String descriptionEng ; 
	
	@Column(name="STATUS")
	private String status ;

	public Long getContractBillingId() {
		return contractBillingId;
	}

	public void setContractBillingId(Long contractBillingId) {
		this.contractBillingId = contractBillingId;
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
		return (obj instanceof ContractBillingDTO) && (this.getContractBillingId() != null)
		        ? this.contractBillingId.equals(((ContractBillingDTO) obj).contractBillingId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.contractBillingId != null)
		        ? (this.getClass().hashCode() + this.contractBillingId.hashCode())
		        : super.hashCode();
	}

	
}
