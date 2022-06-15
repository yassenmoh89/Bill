package core.bill.comercial.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;
import core.bill.setting.model.IdentificationTypeDTO;
import core.bill.setting.model.LocalityDTO;
import core.bill.setting.model.NationalityDTO;

@Entity
@Table(name="SEG_COMM_CUSTOMER")
public class CustomerDTO extends GenericDTO{

	//TableID
	//[CONTRIBUYENTES]  ==table name
	@Id    //[NumContrib]
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="CUSTOMER_ID")  //NumContrib
	private Long customerId ;
	
	@Column(name="CUSTOMER_CODE")
	private String customerCode ;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LOCALITY_ID")
	 private LocalityDTO localityDTO ; //Localidad
	
	@Column(name="IDENTIFICATION")    //Identification
	private String identification ; 
	
	@Column(name="CUSTOMER_NAME")        //Nombre
	private String customerName ;
	
	@Column(name="NULOS")
	private Boolean nulos ;
	
	@Column(name="STREET" , nullable = true)
	private String calle ;
	
	@Column(name="PHONE" , nullable = true)   //Telefono
	private String phone ; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NATIONALITY_ID")
	private NationalityDTO nationalityDTO ;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDENTIFICATION_TYPE_ID")
	private IdentificationTypeDTO identificationTypeDTO; //IdentificationTypeID
	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "APPLICATION_ID")
	private ApplicationDTO ApplicationDTO ;
	
	@Column(name="STATUS") 
	private String status ;


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public LocalityDTO getLocalityDTO() {
		return localityDTO;
	}


	public void setLocalityDTO(LocalityDTO localityDTO) {
		this.localityDTO = localityDTO;
	}


	public String getIdentification() {
		return identification;
	}


	public void setIdentification(String identification) {
		this.identification = identification;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public Boolean getNulos() {
		return nulos;
	}


	public void setNulos(Boolean nulos) {
		this.nulos = nulos;
	}


	public String getCalle() {
		return calle;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public NationalityDTO getNationalityDTO() {
		return nationalityDTO;
	}


	public void setNationalityDTO(NationalityDTO nationalityDTO) {
		this.nationalityDTO = nationalityDTO;
	}

	public ApplicationDTO getApplicationDTO() {
		return ApplicationDTO;
	}


	public void setApplicationDTO(ApplicationDTO applicationDTO) {
		ApplicationDTO = applicationDTO;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getCustomerCode() {
		return customerCode;
	}


	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}


	public IdentificationTypeDTO getIdentificationTypeDTO() {
		return identificationTypeDTO;
	}


	public void setIdentificationTypeDTO(IdentificationTypeDTO identificationTypeDTO) {
		this.identificationTypeDTO = identificationTypeDTO;
	}

	
	}
