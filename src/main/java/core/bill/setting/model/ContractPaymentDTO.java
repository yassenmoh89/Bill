package core.bill.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SEG_SYS_CONTRACT_PAYMENT")
public class ContractPaymentDTO { 
	//[TC FPago]
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONTRACT_PAYMENT_ID")
	private Long contractPaymentId ; 
	
	@Column(name = "DESCRIPTION")
	private String description ; 
	
	@Column(name = "DESCRIPTION_ENG")
	private String descriptionEng ; 
	
	@Column(name = "STATUS")
	private String status ;

	public Long getContractPaymentId() {
		return contractPaymentId;
	}

	public void setContractPaymentId(Long contractPaymentId) {
		this.contractPaymentId = contractPaymentId;
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
		return (obj instanceof ContractPaymentDTO) && (this.getContractPaymentId() != null)
		        ? this.contractPaymentId.equals(((ContractPaymentDTO) obj).contractPaymentId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.contractPaymentId != null)
		        ? (this.getClass().hashCode() + this.contractPaymentId.hashCode())
		        : super.hashCode();
	}
	
	

}
