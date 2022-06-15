package core.bill.invoice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name = "SEG_INV_INVOICE")
public class InvoiceDTO extends GenericDTO{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INVOICE_ID")
	private Long invoiceId ;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "READING_ID")
	private ReadingDTO readingDTO ;
	
	@Column(name = "LOCALITY_ID")
	private Long localityId ;
	
	@Column(name = "CONTRACT_ID")
	private Long contractId;
	
	@Column(name = "YEAR")
	private Integer year ;
	
	@Column(name="MONTH")
	private Integer month ;
	
	@Column(name="CUSTOMER_ID")
	private Long customerId ;
	
	@Column(name = "PAYMENT_CODE")
	private String paymentCode ;
	
	@Column(name = "PAYMENT_MODE")
	private String paymentMode ;
	
	@Column(name ="CONSUMPTION")
	private Double consumption ; 
	
	@Column(name="PRICE")
	private Double price ;
	
	
	@Column(name = "GROSS_AMOUNT")
	private Double grossAmount ; 
	
	@Column(name="DISCOUNT_RATE")
	private Double discountRate ; 
	
	@Column(name = "DISCOUNT")
	private Double discount ;
	
	@Column(name = "AMOUNT")
	private Double amount ; 
	
	@Column(name ="R_CONSUMPTION")
	private Double rconsumption ; 
	
	@Column(name = "R_GROSS_AMOUT")
	private Double rgrossAmount ;
	
	@Column(name = "PENALITY")
	private Double penality ;
	
	@Column(name = "RI_AMOUNT")
	private Double riamount ;
	
	@Column(name = "M_COUNTER")
	private Double mcounter ; 
	
	@Column(name = "POWER")
	private Double power ; 
	
	@Column(name = "TAX")
	private Double tax ;
	
	@Column(name = "AMOUNT_PAID")
	private Double amountPaid ; 
	
	@Column(name="AMOUNT_PAYABLE")
	private Double amountPayable ;
	
	@Column(name = "VENTANILLA")
	private String ventanilla ;
	
	@Column(name="CREATED_INVOICE")
	private Boolean createdInvoice ;
	
	@Column(name= "IS_PAID")
	private Boolean paymentStatus ;
	
	@Column(name = "MODIFIABLE")
	private Boolean modifiable ; 
	
	
	@Column(name = "GATEGORY")
	private String category ;
	
	@Column(name = "OPERATOR_ID")
	private String operatorId ;
	
	@Column(name="COMMENT")
	private String comment ; 
	
	@Column(name = "COLLECTED_BY_SYSTEM")
	private Boolean collectedBySystem ;
	
	@Column(name="ELIMINABLE")
	private Boolean eliminable ; 
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name = "ELIMINABLE_DATE")
	private Date elimiableDate ; 
	
	@Column(name = "PAID_BY")
	private String paidBy ;
	
	@Column(name = "PAID_DATE")
	private Date paidDate ;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
    @Temporal(value=TemporalType.TIMESTAMP)
    @Column(name="CREATED_DATE")
    private Date createdDate;
    
    @Column(name="UPDATED_BY")
	private String updatedBy;
	
    @Temporal(value=TemporalType.TIMESTAMP)
    @Column(name="UPDATED_DATE")
    private Date updatedDate;
    
    private String contractDirecion;
    
    private String contractbenefeciario;
    
    private String customerName;
    
    
	public Long getInvoiceId() {
		return invoiceId;
	}

	public Long getLocalityId() {
		return localityId;
	}

	public Long getContractId() {
		return contractId;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getMonth() {
		return month;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	

	public Double getConsumption() {
		return consumption;
	}

	public Double getPrice() {
		return price;
	}

	public Double getGrossAmount() {
		return grossAmount;
	}

	public Double getDiscountRate() {
		return discountRate;
	}

	public Double getDiscount() {
		return discount;
	}

	public Double getAmount() {
		return amount;
	}

	public Double getRconsumption() {
		return rconsumption;
	}

	public Double getRgrossAmount() {
		return rgrossAmount;
	}

	public Double getPenality() {
		return penality;
	}

	public Double getRiamount() {
		return riamount;
	}

	public Double getMcounter() {
		return mcounter;
	}

	public Double getPower() {
		return power;
	}

	

	public Double getAmountPaid() {
		return amountPaid;
	}

	public Double getAmountPayable() {
		return amountPayable;
	}

	public String getVentanilla() {
		return ventanilla;
	}

	public Boolean getCreatedInvoice() {
		return createdInvoice;
	}

	

	public Boolean getModifiable() {
		return modifiable;
	}

	

	public String getCategory() {
		return category;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public String getComment() {
		return comment;
	}

	public Boolean getCollectedBySystem() {
		return collectedBySystem;
	}

	public Boolean getEliminable() {
		return eliminable;
	}

	public Date getElimiableDate() {
		return elimiableDate;
	}

	public String getPaidBy() {
		return paidBy;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public void setLocalityId(Long localityId) {
		this.localityId = localityId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public void setConsumption(Double consumption) {
		this.consumption = consumption;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setGrossAmount(Double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public void setDiscountRate(Double discountRate) {
		this.discountRate = discountRate;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setRconsumption(Double rconsumption) {
		this.rconsumption = rconsumption;
	}

	public void setRgrossAmount(Double rgrossAmount) {
		this.rgrossAmount = rgrossAmount;
	}

	public void setPenality(Double penality) {
		this.penality = penality;
	}

	public void setRiamount(Double riamount) {
		this.riamount = riamount;
	}

	public void setMcounter(Double mcounter) {
		this.mcounter = mcounter;
	}

	public void setPower(Double power) {
		this.power = power;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public void setAmountPayable(Double amountPayable) {
		this.amountPayable = amountPayable;
	}

	public void setVentanilla(String ventanilla) {
		this.ventanilla = ventanilla;
	}

	public void setCreatedInvoice(Boolean createdInvoice) {
		this.createdInvoice = createdInvoice;
	}

	

	public void setModifiable(Boolean modifiable) {
		this.modifiable = modifiable;
	}

	

	public void setCategory(String category) {
		this.category = category;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setCollectedBySystem(Boolean collectedBySystem) {
		this.collectedBySystem = collectedBySystem;
	}

	public void setEliminable(Boolean eliminable) {
		this.eliminable = eliminable;
	}

	public void setElimiableDate(Date elimiableDate) {
		this.elimiableDate = elimiableDate;
	}

	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public Boolean getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public ReadingDTO getReadingDTO() {
		return readingDTO;
	}

	public void setReadingDTO(ReadingDTO readingDTO) {
		this.readingDTO = readingDTO;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getContractDirecion() {
		return contractDirecion;
	}

	public void setContractDirecion(String contractDirecion) {
		this.contractDirecion = contractDirecion;
	}

	public String getContractbenefeciario() {
		return contractbenefeciario;
	}

	public void setContractbenefeciario(String contractbenefeciario) {
		this.contractbenefeciario = contractbenefeciario;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	
}
