package core.bill.comercial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import core.bill.setting.model.ApplicationPurposeTypeDTO;
import core.bill.setting.model.ContractTypeDTO;
import core.bill.setting.model.HouseholdsDTO;
import core.bill.setting.model.LocalityDTO;
import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_COMM_APPLICATIONS")
public class ApplicationDTO extends GenericDTO{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="APPLICATION_ID")   //ApplicationID
	private Long applicationId;
	

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CONTRACT_TYPE" , nullable = true)
	private ContractTypeDTO ContractTypeDTO;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="HOUSE_HOLDING_TYPE_ID")
	private HouseholdsDTO householdsDTO;
	
	@Column(name="OWNER_NAME")
	private String ownername;
	
	@Column(name="OWNER_ADDRESS")
	private String ownerAddress;
	
	@Column(name="OWNER_TELEPHONE")
	private String ownerTelphone;
	
	@Column(name="OWNER_ID")
	private String ownerID;
	
	@Column(name="CUSTOMER_NAME")
	private String customerName;
	
	
	@Column(name="DIP")
	private String dip;
	
	@Column(name="ADDRESS" , nullable = true)
	private String address;
	
	@Column(name="TELEPHONE1")
	private String telephone1;
	
	@Column(name="TELEPHONE2")
	private String telephone2;
	
	@Column(name="MOBILE1")
	private String mobile1;
	
	@Column(name="MOBILE2")
	private String mobile2;
	
	@Column(name="NEIGHBORHOOD") //Neighborhood
	private String neighbors;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="APPLICATION_PURPOSE_TYPE_ID")
	private ApplicationPurposeTypeDTO applicationPurposeTypeDTO;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LOCALITY_ID")
	private LocalityDTO localityDTO ; //Localidad
	
	@Column(name="STATUS")
	private String status ; 

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	
	public ContractTypeDTO getContractTypeDTO() {
		return ContractTypeDTO;
	}

	public void setContractTypeDTO(ContractTypeDTO contractTypeDTO) {
		ContractTypeDTO = contractTypeDTO;
	}

	public HouseholdsDTO getHouseholdsDTO() {
		return householdsDTO;
	}

	public void setHouseholdsDTO(HouseholdsDTO householdsDTO) {
		this.householdsDTO = householdsDTO;
	}

	public String getOwnername() {
		return ownername;
	}

	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}

	public String getOwnerAddress() {
		return ownerAddress;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	public String getOwnerTelphone() {
		return ownerTelphone;
	}

	public void setOwnerTelphone(String ownerTelphone) {
		this.ownerTelphone = ownerTelphone;
	}

	public String getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDip() {
		return dip;
	}

	public void setDip(String dip) {
		this.dip = dip;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getMobile1() {
		return mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public String getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(String neighbors) {
		this.neighbors = neighbors;
	}

	public ApplicationPurposeTypeDTO getApplicationPurposeTypeDTO() {
		return applicationPurposeTypeDTO;
	}

	public void setApplicationPurposeTypeDTO(ApplicationPurposeTypeDTO applicationPurposeTypeDTO) {
		this.applicationPurposeTypeDTO = applicationPurposeTypeDTO;
	}

	public LocalityDTO getLocalityDTO() {
		return localityDTO;
	}

	public void setLocalityDTO(LocalityDTO localityDTO) {
		this.localityDTO = localityDTO;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
