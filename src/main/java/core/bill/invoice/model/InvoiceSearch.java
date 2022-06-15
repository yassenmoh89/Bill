package core.bill.invoice.model;

import java.util.Date;

public class InvoiceSearch {

	private Long contractId ; 
	private Long customerId ; 
	private Long invoiceId ;
	private Boolean eliminable ; 
	private Boolean invoiceCreated ;
	private Boolean paymentStatus ;
	private Date eliminableDate  ;
	private String paymentDesc;
	private Long localityId ; 
	private Date createdDate  ;
	private Date paidDate  ;
		
	private Integer year ; 
	private Integer month ; 
	
	private Date fromDate  ;
	private Date toDate  ;
	
	public Long getContractId() {
		return contractId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public Long getInvoiceId() {
		return invoiceId;
	}
	
	
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Integer getYear() {
		return year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Boolean getEliminable() {
		return eliminable;
	}
	public Boolean getInvoiceCreated() {
		return invoiceCreated;
	}
	public Date getEliminableDate() {
		return eliminableDate;
	}
	public void setEliminable(Boolean eliminable) {
		this.eliminable = eliminable;
	}
	public void setInvoiceCreated(Boolean invoiceCreated) {
		this.invoiceCreated = invoiceCreated;
	}
	public void setEliminableDate(Date eliminableDate) {
		this.eliminableDate = eliminableDate;
	}
	public Boolean getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(Boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPaymentDesc() {
		return paymentDesc;
	}
	public void setPaymentDesc(String paymentDesc) {
		this.paymentDesc = paymentDesc;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Long getLocalityId() {
		return localityId;
	}
	public void setLocalityId(Long localityId) {
		this.localityId = localityId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	} 
	
	
}
