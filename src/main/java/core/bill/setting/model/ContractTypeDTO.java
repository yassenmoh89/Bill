package core.bill.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name = "SEG_SYS_CONTRACT_TYPE") // TC Contrato
public class ContractTypeDTO extends GenericDTO{

	//TContrato
	//TCDescripcion
	//TCDescripcion_Eng
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CONTRACT_TYPE_ID")
	private Long contractTypeId ; 
	
	@Column(name = "DESCRIPTION")
	private String description ; 
	
	@Column(name = "DESCRIPTION_ENG")
	private String descriptionEng ; 
	
	@Column(name = "STATUS")
	private String status ;

	
	public Long getContractTypeId() {
		return contractTypeId;
	}

	public void setContractTypeId(Long contractTypeId) {
		this.contractTypeId = contractTypeId;
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
		return (obj instanceof ContractTypeDTO) && (this.getContractTypeId() != null)
		        ? this.contractTypeId.equals(((ContractTypeDTO) obj).contractTypeId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.contractTypeId != null)
		        ? (this.getClass().hashCode() + this.contractTypeId.hashCode())
		        : super.hashCode();
	}
	
	
	
}
