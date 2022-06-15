package core.bill.comercial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="SEG_COMM_CONTRACT_OPTION")
public class ContractOptionDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONTRACT_OPTION_ID")
	private Long contractOptionId ; 
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CONTRACT_ID" )
	private ContractDTO contractDTO ;
	
	@Column(name="TENANT") // OPINGULINO
	private Boolean tenant;
	
	@Column(name = "INTERNAL_CONSUMPTION") // OP_INTIRNO 
	private Boolean interlConsumption ;
	
	@Column(name = "AUTHORITY")  //AUTHORIDAD
	private Boolean authority ;
	
	@Column(name="EXEMPT_PENALITY")
	private Boolean exemptPenality ; //multa
	
	@Column(name="EXONERADO")
	private Boolean exonerado ;
	
	@Column(name="INVOICE_BILLING_ORDER")
	private Boolean invoiceBillingOrder ;
	
	@Column(name="INVOICE_PAYMENT_ORDER")
	private Boolean invoicePaymentOrder ;
	
	@Column(name="SUBJECT_TO_CUT_ENERGY")
	private Boolean subjectTocut ; //OpBloqueado

	public Long getContractOptionId() {
		return contractOptionId;
	}

	public void setContractOptionId(Long contractOptionId) {
		this.contractOptionId = contractOptionId;
	}

	public Boolean getTenant() {
		return tenant;
	}

	public void setTenant(Boolean tenant) {
		this.tenant = tenant;
	}

	public Boolean getInterlConsumption() {
		return interlConsumption;
	}

	public void setInterlConsumption(Boolean interlConsumption) {
		this.interlConsumption = interlConsumption;
	}

	public Boolean getAuthority() {
		return authority;
	}

	public void setAuthority(Boolean authority) {
		this.authority = authority;
	}

	public Boolean getExemptPenality() {
		return exemptPenality;
	}

	public void setExemptPenality(Boolean exemptPenality) {
		this.exemptPenality = exemptPenality;
	}

	public Boolean getExonerado() {
		return exonerado;
	}

	public void setExonerado(Boolean exonerado) {
		this.exonerado = exonerado;
	}

	public Boolean getInvoiceBillingOrder() {
		return invoiceBillingOrder;
	}

	public void setInvoiceBillingOrder(Boolean invoiceBillingOrder) {
		this.invoiceBillingOrder = invoiceBillingOrder;
	}

	public Boolean getInvoicePaymentOrder() {
		return invoicePaymentOrder;
	}

	public void setInvoicePaymentOrder(Boolean invoicePaymentOrder) {
		this.invoicePaymentOrder = invoicePaymentOrder;
	}

	public Boolean getSubjectTocut() {
		return subjectTocut;
	}

	public void setSubjectTocut(Boolean subjectTocut) {
		this.subjectTocut = subjectTocut;
	}

	public ContractDTO getContractDTO() {
		return contractDTO;
	}

	public void setContractDTO(ContractDTO contractDTO) {
		this.contractDTO = contractDTO;
	}
	
	

}
