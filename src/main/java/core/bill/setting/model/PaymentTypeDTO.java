package core.bill.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name = "SEG_SYS_PAYMENT_TYPE") //TC Pagos
public class PaymentTypeDTO extends GenericDTO{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PAYMENT_TYPE_ID")
	private Long paymentTypeId; 
	
	@Column(name = "PAYMENT_CODE")
	private String paymentCode ; 
	
	@Column(name = "DESCRIPTION")
	private String descrption ; 
	
	@Column(name = "DESCRIPTION_ENG")
	private String descrptionEng ; 
	
	@Column(name = "STATUS")
	private String status ;

	public Long getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Long paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

	public String getDescrptionEng() {
		return descrptionEng;
	}

	public void setDescrptionEng(String descrptionEng) {
		this.descrptionEng = descrptionEng;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	} 
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof PaymentTypeDTO) && (this.getPaymentTypeId() != null)
		        ? this.paymentTypeId.equals(((PaymentTypeDTO) obj).paymentTypeId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.paymentTypeId != null)
		        ? (this.getClass().hashCode() + this.paymentTypeId.hashCode())
		        : super.hashCode();
	}
	
}
