package core.bill.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_SYS_CONTRACT_OWNER_TYPE") //TC Tipo
public class ContractOwnerTypeDTO extends GenericDTO{
	
	//[TipoID]
	//[Tipo]
	//[Descripcion]
	//[Descripcion_Eng]
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CONTRACT_OWNER_TYPE_ID")
	private Long contractOwnerTypeId ; 
	
	@Column(name = "CONTRACT_OWNER_TYPE_CODE")
	private String contractOwnerTypeCode ; 
	
	@Column(name = "CONTRACT_OWNER_TYPE")
	private String contractOwnerType ; 
	
	@Column(name = "CONTRACT_OWNER_TYPE_ENG")
	private String contractOwnerTypeEng ; 
	
	@Column(name = "STATUS")
	private String status ;

	public Long getContractOwnerTypeId() {
		return contractOwnerTypeId;
	}

	public void setContractOwnerTypeId(Long contractOwnerTypeId) {
		this.contractOwnerTypeId = contractOwnerTypeId;
	}

	public String getContractOwnerTypeCode() {
		return contractOwnerTypeCode;
	}

	public void setContractOwnerTypeCode(String contractOwnerTypeCode) {
		this.contractOwnerTypeCode = contractOwnerTypeCode;
	}

	public String getContractOwnerType() {
		return contractOwnerType;
	}

	public void setContractOwnerType(String contractOwnerType) {
		this.contractOwnerType = contractOwnerType;
	}

	public String getContractOwnerTypeEng() {
		return contractOwnerTypeEng;
	}

	public void setContractOwnerTypeEng(String contractOwnerTypeEng) {
		this.contractOwnerTypeEng = contractOwnerTypeEng;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	} 
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof ContractOwnerTypeDTO) && (this.getContractOwnerTypeId() != null)
		        ? this.contractOwnerTypeId.equals(((ContractOwnerTypeDTO) obj).contractOwnerTypeId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.contractOwnerTypeId != null)
		        ? (this.getClass().hashCode() + this.contractOwnerTypeId.hashCode())
		        : super.hashCode();
	}
	
	

}
