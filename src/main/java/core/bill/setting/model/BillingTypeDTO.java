package core.bill.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_SYS_BILLING_TYPE")  // TC Facturacion
public class BillingTypeDTO extends GenericDTO{
	
	//[PFacturacion]
	//[PFDescripcion]
	//[PFDescripcion_Eng]
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BILLING_TYPE_ID")
	private Long billingTypeId;
	
	@Column(name="BILLING_TYPE")
	private String billingType ;
	
	@Column(name="BILLING_TYPE_ENG")
	private String billingTypeEng ;
	
	@Column(name="STATUS")
	private String status ;
	

	public Long getBillingTypeId() {
		return billingTypeId;
	}

	public void setBillingTypeId(Long billingTypeId) {
		this.billingTypeId = billingTypeId;
	}

	public String getBillingType() {
		return billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	public String getBillingTypeEng() {
		return billingTypeEng;
	}

	public void setBillingTypeEng(String billingTypeEng) {
		this.billingTypeEng = billingTypeEng;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof BillingTypeDTO) && (this.billingTypeId != null)
		        ? this.billingTypeId.equals(((BillingTypeDTO) obj).billingTypeId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.billingTypeId != null)
		        ? (this.getClass().hashCode() + this.billingTypeId.hashCode())
		        : super.hashCode();
	}

}
