package core.bill.comercial.model;

import java.util.Date;
import java.util.List;

public class ContractSearch {
	
	private Long cutomerId ; 
	private String customerCode ; 
	private Long localityId ; 
	private String contractStatus ; 
	private String contractCode ;
	private String direction ; 
	private Date createdFrom ; 
	private Date createdTo ;
	private List<String> sectorCode ;
	private List<String> localidadCode ;
	private Long meterStatusId ; 
	private Date createdDate ; 
	
	public Long getLocalityId() {
		return localityId;
	}
	public void setLocalityId(Long localityId) {
		this.localityId = localityId;
	}
	public String getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	public Date getCreatedFrom() {
		return createdFrom;
	}
	public void setCreatedFrom(Date createdFrom) {
		this.createdFrom = createdFrom;
	}
	public Date getCreatedTo() {
		return createdTo;
	}
	public void setCreatedTo(Date createdTo) {
		this.createdTo = createdTo;
	}
	public Long getCutomerId() {
		return cutomerId;
	}
	public void setCutomerId(Long cutomerId) {
		this.cutomerId = cutomerId;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public List<String> getSectorCode() {
		return sectorCode;
	}
	public void setSectorCode(List<String> sectorCode) {
		this.sectorCode = sectorCode;
	}
	public List<String> getLocalidadCode() {
		return localidadCode;
	}
	public void setLocalidadCode(List<String> localidadCode) {
		this.localidadCode = localidadCode;
	}
	public Long getMeterStatusId() {
		return meterStatusId;
	}
	public void setMeterStatusId(Long meterStatusId) {
		this.meterStatusId = meterStatusId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


}
