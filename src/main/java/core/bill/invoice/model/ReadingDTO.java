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
@Table(name="SEG_INV_READING")
public class ReadingDTO extends GenericDTO{
//LECTURAS
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "READING_ID")
	private Long readingId ;
	
	@Column(name = "LOCALITY_ID")
	private Long localityId;
	
	@Column(name = "CONTRACT_ID")
	private Long contractId ;
	
	@Column(name = "YEAR")
	private Integer year ;
	
	@Column(name = "MONTH")
	private Integer month;
	
	@Column(name = "CUSTOMER_ID")
	private Long customerId;
	
	@Column(name = "READING")
	private Integer reading ;
	
	@Column(name = "CONSUMPTION")
	private Double consumption ;
	
	@Column(name = "R_READING")
	private Double rreading; 
	
	@Column(name="R_CONSUMPTION")
	private Double rconsumption ; 
	
	@Column(name = "INVOICE_CREATED")
	private Boolean invoiceCreated ;
	
	@Column(name = "L_CHANGE")
	private Integer lchange ;
	
	@Column(name = "R_CHANGE")
	private Integer rchange;
	
	@Column(name = "SECTOR_ID")
	private Long sectorId ; 
	
	@Column(name = "IS_IQUALA")
	private Boolean iquala ;
	
	@Column(name="CODE")
	private String code ;
	
	@Column(name = "BRAND")
	private String brand ;
	
	@Column(name="A_READING")
	private Double areading ;
	
	@Column(name="FACTOR")
	private Integer factor ;
	
	@Column(name = "EDITING_TYPE")
	private Integer editingType ; 
	
	@Column(name = "ELIMINABLE")
	private Boolean eliminable ; 
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name = "ELIMINABLE_DATE")
	private Date eliminableDate ;

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

	public Long getReadingId() {
		return readingId;
	}

	public Long getLocalityId() {
		return localityId;
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

	

	public Double getConsumption() {
		return consumption;
	}

	public Double getRreading() {
		return rreading;
	}

	public Double getRconsumption() {
		return rconsumption;
	}

	public Boolean getInvoiceCreated() {
		return invoiceCreated;
	}

	
	public Long getSectorId() {
		return sectorId;
	}

	public Boolean getIquala() {
		return iquala;
	}

	public String getCode() {
		return code;
	}

	public String getBrand() {
		return brand;
	}

	public Double getAreading() {
		return areading;
	}

	public Integer getFactor() {
		return factor;
	}

	public Integer getEditingType() {
		return editingType;
	}

	public Boolean getEliminable() {
		return eliminable;
	}

	public Date getEliminableDate() {
		return eliminableDate;
	}

	public void setReadingId(Long readingId) {
		this.readingId = readingId;
	}

	public void setLocalityId(Long localityId) {
		this.localityId = localityId;
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

	public void setConsumption(Double consumption) {
		this.consumption = consumption;
	}

	public void setRreading(Double rreading) {
		this.rreading = rreading;
	}

	public void setRconsumption(Double rconsumption) {
		this.rconsumption = rconsumption;
	}

	public void setInvoiceCreated(Boolean invoiceCreated) {
		this.invoiceCreated = invoiceCreated;
	}

	public void setSectorId(Long sectorId) {
		this.sectorId = sectorId;
	}

	public void setIquala(Boolean iquala) {
		this.iquala = iquala;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setAreading(Double areading) {
		this.areading = areading;
	}

	public void setFactor(Integer factor) {
		this.factor = factor;
	}

	public void setEditingType(Integer editingType) {
		this.editingType = editingType;
	}

	public void setEliminable(Boolean eliminable) {
		this.eliminable = eliminable;
	}

	public void setEliminableDate(Date eliminableDate) {
		this.eliminableDate = eliminableDate;
	}

	public Integer getReading() {
		return reading;
	}

	public void setReading(Integer reading) {
		this.reading = reading;
	}

	public Integer getLchange() {
		return lchange;
	}

	public Integer getRchange() {
		return rchange;
	}

	public void setLchange(Integer lchange) {
		this.lchange = lchange;
	}

	public void setRchange(Integer rchange) {
		this.rchange = rchange;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	
	

}
