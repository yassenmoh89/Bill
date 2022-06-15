package core.bill.invoice.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import core.bill.common.dto.GenericDTO;

@Entity
@Table(name = "SEG_INV_PAYMENT")
public class PaymentDTO extends GenericDTO{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PAYMENT_ID")
	private Long paymentId ; 
	
	@Column(name = "LOCALITY_ID")
	private Long LocalityId ; 
	
	@Column(name = "REFERENCE_ID")
	private Long referenceId ; 
	
	@Column(name = "CONTRACT_ID")
	private Long contractId ; 
	
	@Column(name = "CUSTOMER_ID")
	private Long customerId ; 
	
	@Column(name = "AMOUNT_PAYABLE")
	private Double amountPayable ;
	
	@Column(name = "AMOUNT_PAID")
	private Double amountPaid ;
	
	@Column(name="PAYMENT_STATUS")
	private String paymentStatus ; // pending // paid
	
	@Column(name = "PAYMENT_MODE")
	private String paymentMode ; 
	
	@Column(name = "MODE")
	private String mode   ; // INV= INVOICE , OTH = OTHER PAYMENT 
	
	@Column(name="PAID_BY")
	private String paidBy ; 
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name = "PAID_DATE")
	private Date paidDate ;
	
	//----------------------- cheque info
	@Column(name="DOCUMENT_NO" , nullable = true)
	private String documentNo ; // cheuqe no , transfer no , compensation ni  
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name="DOCUMENT_ISSUE_DATE" , nullable = true)
	private Date documentIssueDate ;  //
	
	@Column(name="BANK_ID" , nullable = true)
	private Long bankId ; 

	@Column(name="DESCRIPTION" , nullable = true)
	private String description ;
	
	public Long getPaymentId() {
		return paymentId;
	}

	public Long getLocalityId() {
		return LocalityId;
	}

	public Long getReferenceId() {
		return referenceId;
	}

	public Long getContractId() {
		return contractId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public Double getAmountPayable() {
		return amountPayable;
	}

	public Double getAmountPaid() {
		return amountPaid;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public String getMode() {
		return mode;
	}

	public String getPaidBy() {
		return paidBy;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public void setLocalityId(Long localityId) {
		LocalityId = localityId;
	}

	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setAmountPayable(Double amountPayable) {
		this.amountPayable = amountPayable;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public Date getDocumentIssueDate() {
		return documentIssueDate;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public void setDocumentIssueDate(Date documentIssueDate) {
		this.documentIssueDate = documentIssueDate;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	} 
	
	

	
}
