package core.bill.invoice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import core.bill.common.dto.GenericDTO;


@Entity
@Table(name = "SEG_INV_OTHER_PAYMENT")
public class OtherPaymentDTO extends GenericDTO{
// .[dbo].[INGRESOS] 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OTHER_PAYMENT_ID")
	private Long otherPaymentId ; 
	
	@Column(name = "LOCALITY_ID")
	private Long localityId ; 
	
	@Column(name = "CONTRACT_ID")
	private Long contractId ;
	
	@Column(name = "CUSTOMER_ID")
	private Long customerId ;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CHARGE_ID")
	private ChargeDTO chargeDTO ; 
	
	@Column(name = "TOTAL")
	private Double total ; 
	
	@Column(name = "AMOUNT_PAYABLE")
	private Double amountPayable ; 
	
	@Column(name = "AMOUNT_PAID")
	private Double amountPaid;
	
	@Column(name = "METER_NUMBER")
	private String meterNumber ; 
	
	@Column(name = "CIRCUIT_NUMBER")
	private String circuitNumber ; 
	
	@Column(name="PAID_BY")
	private String paidBy;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name = "PAID_DATE")
	private Date paidDate ;

	@Column(name = "IS_PAID")
	private Boolean paymentStatus ;   
	
	@Column(name = "PAYMENT_MODE")
	private String paymentMode ; 
	
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

    
	public Long getOtherPaymentId() {
		return otherPaymentId;
	}

	public Long getLocalityId() {
		return localityId;
	}

	public Long getContractId() {
		return contractId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public ChargeDTO getChargeDTO() {
		return chargeDTO;
	}

	public Double getTotal() {
		return total;
	}

	public Double getAmountPayable() {
		return amountPayable;
	}

	public Double getAmountPaid() {
		return amountPaid;
	}

	public String getMeterNumber() {
		return meterNumber;
	}

	public String getCircuitNumber() {
		return circuitNumber;
	}

	public String getPaidBy() {
		return paidBy;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setOtherPaymentId(Long otherPaymentId) {
		this.otherPaymentId = otherPaymentId;
	}

	public void setLocalityId(Long localityId) {
		this.localityId = localityId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setChargeDTO(ChargeDTO chargeDTO) {
		this.chargeDTO = chargeDTO;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setAmountPayable(Double amountPayable) {
		this.amountPayable = amountPayable;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
	}

	public void setCircuitNumber(String circuitNumber) {
		this.circuitNumber = circuitNumber;
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
	
}
