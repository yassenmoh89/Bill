package core.bill.comercial.model;

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
import core.bill.setting.model.ContractBillingDTO;
import core.bill.setting.model.ContractPaymentDTO;
import core.bill.setting.model.ContractTypeDTO;
import core.bill.setting.model.LocalityDTO;
import core.bill.setting.model.MeterStatusDTO;
import core.bill.setting.model.PhaseDTO;
import core.bill.setting.model.SectorDTO;
import core.bill.setting.model.SupplyDTO;
import core.bill.setting.model.TypeDTO;

@Entity
@Table(name = "SEG_COMM_CONTRACT")
public class ContractDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONTRACT_ID")
	private Long contractId ; 
	
	@Column(name = "CONTRACT_CODE")
	private String contractCode; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CUSTOMER_ID")
	private CustomerDTO  customerDTO ;
	
	@Column(name="FAX" , nullable = true)
	private String fax ; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PHASE_ID")
	private PhaseDTO phaseDTO ; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SECTOR_ID")
	private SectorDTO sectorDTO ;
	
	@Column(name = "INSPECTION_ID" , nullable = true)
	private String inspectorId ;
	
	@Column(name="MARCA" , nullable = true)
	private Boolean marca ;
	
	@Column(name ="OPERATOR_ID" , nullable = true)
	private String  OperatorId ;
	
	@Column(name = "CAJA_ID", nullable = true)
	private String cajaId ; 
	
	@Column(name="RUTA" , nullable = true)
	private String ruta ; 
	
	@Column(name = "FOLIO" , nullable = true)
	private String folio ; 
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SUPPLY_ID")
	private SupplyDTO supplyDTO ;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name="CONTRACT_DATE")
	private Date contractDate;
	
	@Column(name = "STUDY_ID" , nullable = true)
	private String studyId ;
	
	@Column(name="HOME_CODE" , nullable = true)
	private String homeCode ; 
	
	@Column(name = "METER_NUMBER" , nullable = true)
	private String meterNumber ;
	
	@Column(name = "IS_IQUALA")
	private Boolean iguala ;
	
	@Column(name = "CONSUMPTION" , nullable = true)
	private Integer consumption ;
	
	@Column(name = "COMMENT")
	private String comment ;
	
	@Column(name = "APPROVAL_STATUS")
	private String  approvalStatus ;
	
	@Column(name = "SUBMITTED_DATE")
	private Date submittedDate; //[ContractDate]
	
	@Column(name = "SUBMITTED_BY")
	private String submittedBy ; 
	
	@Column(name="APPROVED_BY")
	private String apporovedBy;
	
    @Temporal(value=TemporalType.TIMESTAMP)
    @Column(name="APPROVED_DATE")
    private Date approvedDate;
    
    
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
    
    @Column(name="CONTRACT_STATUS")
	private String contractStatus;

    //----------------------------------ContractInfo
    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LOCALITY_ID")
	private LocalityDTO localityDTO ; 
	
	@Column(name = "TELEPHONE" , nullable = true)
	private String telephone ; 
	
	@Column(name = "BENEFICIARY")
	private String beneficiary ;
	
	@Column(name = "CIRCUITO_NUM")
	private String circuitoNum	; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TYPE_ID")
	private TypeDTO typeDTO ; ////tipo
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CONTRACT_TYPE_ID")
	private ContractTypeDTO contractTypeDTO; ////TContrato
	
	@Column(name = "COEFFICIENT_A")
	private Integer coefficientA ;  //Factor coefficient A
	
	@Column(name ="COEFFICIENT_R")
	private Integer coefficient_R ;  //RFactor coefficient R
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CONTRACT_PAYMENT_ID")
	private ContractPaymentDTO contractPaymentDTO;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CONTRACT_BILLING_ID")
	private ContractBillingDTO contractBillingDTO;
	
	@Column(name="A_FACTOR")
	private Boolean afactor ;
	
	@Column(name="POWER") //PRIMA_FIJA
	private Integer power ;
	
	@Column(name = "DIRECTION")
	private String direction ; 

	
	@Column(name = "CONTADOR_NUM")
	private String contadorNum ;
	
	@Column(name = "DIGIT_A")
	private Integer digitA ;  //DIGITOS
	
	@Column(name="DIGIT_R")
	private Integer digitR ;  //RDigitos
	
	@Column(name="FIXED_PRICE_OP")
	private Boolean fixedPriceOp ;  //OP_PRECIO
	
	@Column(name="FIXED_PRICE_VALUE")
	private Integer fixedPriceValue ; // PRECIO
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "METER_STATUS_ID")
	private MeterStatusDTO meterStatusDTO ;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name = "FECHA_ALTA")
	private Date fechaAlta ;
		
	@Column(name="OP_IGUALA")
	private Boolean opIguala ;
	
	@Column(name="IQUALA_ANTERIOR")
	private Boolean IgualaAnterior ;
	//---------------------------------------------- CONTRACT MORE INFO
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
	
	@Column(name="FACTOR")
	private Integer factor ;
	
	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	

	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}

	public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public PhaseDTO getPhaseDTO() {
		return phaseDTO;
	}

	public void setPhaseDTO(PhaseDTO phaseDTO) {
		this.phaseDTO = phaseDTO;
	}

	public SectorDTO getSectorDTO() {
		return sectorDTO;
	}

	public String getMeterNumber() {
		return meterNumber;
	}

	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
	}

	public void setSectorDTO(SectorDTO sectorDTO) {
		this.sectorDTO = sectorDTO;
	}

	
	public Boolean getMarca() {
		return marca;
	}

	public void setMarca(Boolean marca) {
		this.marca = marca;
	}

	public SupplyDTO getSupplyDTO() {
		return supplyDTO;
	}

	public void setSupplyDTO(SupplyDTO supplyDTO) {
		this.supplyDTO = supplyDTO;
	}

	public String getStudyId() {
		return studyId;
	}

	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public String getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}

	public String getApporovedBy() {
		return apporovedBy;
	}

	public void setApporovedBy(String apporovedBy) {
		this.apporovedBy = apporovedBy;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
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

	public String getHomeCode() {
		return homeCode;
	}

	public void setHomeCode(String homeCode) {
		this.homeCode = homeCode;
	}

	public Integer getConsumption() {
		return consumption;
	}

	public void setConsumption(Integer consumption) {
		this.consumption = consumption;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public Boolean getIguala() {
		return iguala;
	}

	public void setIguala(Boolean iguala) {
		this.iguala = iguala;
	}

	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public String getInspectorId() {
		return inspectorId;
	}

	public void setInspectorId(String inspectorId) {
		this.inspectorId = inspectorId;
	}

	public String getOperatorId() {
		return OperatorId;
	}

	public String getCajaId() {
		return cajaId;
	}

	public String getRuta() {
		return ruta;
	}

	public String getFolio() {
		return folio;
	}

	public void setOperatorId(String operatorId) {
		OperatorId = operatorId;
	}

	public void setCajaId(String cajaId) {
		this.cajaId = cajaId;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public LocalityDTO getLocalityDTO() {
		return localityDTO;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getBeneficiary() {
		return beneficiary;
	}

	public String getCircuitoNum() {
		return circuitoNum;
	}

	public TypeDTO getTypeDTO() {
		return typeDTO;
	}

	public ContractTypeDTO getContractTypeDTO() {
		return contractTypeDTO;
	}

	public Integer getCoefficientA() {
		return coefficientA;
	}

	public Integer getCoefficient_R() {
		return coefficient_R;
	}

	public ContractPaymentDTO getContractPaymentDTO() {
		return contractPaymentDTO;
	}

	public ContractBillingDTO getContractBillingDTO() {
		return contractBillingDTO;
	}

	public Boolean getAfactor() {
		return afactor;
	}

	public Integer getPower() {
		return power;
	}

	public String getDirection() {
		return direction;
	}

	
	public String getContadorNum() {
		return contadorNum;
	}

	public Integer getDigitA() {
		return digitA;
	}

	public Integer getDigitR() {
		return digitR;
	}

	public Boolean getFixedPriceOp() {
		return fixedPriceOp;
	}

	public Integer getFixedPriceValue() {
		return fixedPriceValue;
	}

	public MeterStatusDTO getMeterStatusDTO() {
		return meterStatusDTO;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public Boolean getOpIguala() {
		return opIguala;
	}

	public Boolean getIgualaAnterior() {
		return IgualaAnterior;
	}

	public Boolean getTenant() {
		return tenant;
	}

	public Boolean getInterlConsumption() {
		return interlConsumption;
	}

	public Boolean getAuthority() {
		return authority;
	}

	public Boolean getExemptPenality() {
		return exemptPenality;
	}

	public Boolean getExonerado() {
		return exonerado;
	}

	public Boolean getInvoiceBillingOrder() {
		return invoiceBillingOrder;
	}

	public Boolean getInvoicePaymentOrder() {
		return invoicePaymentOrder;
	}

	public Boolean getSubjectTocut() {
		return subjectTocut;
	}

	public void setLocalityDTO(LocalityDTO localityDTO) {
		this.localityDTO = localityDTO;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

	public void setCircuitoNum(String circuitoNum) {
		this.circuitoNum = circuitoNum;
	}

	public void setTypeDTO(TypeDTO typeDTO) {
		this.typeDTO = typeDTO;
	}

	public void setContractTypeDTO(ContractTypeDTO contractTypeDTO) {
		this.contractTypeDTO = contractTypeDTO;
	}

	public void setCoefficientA(Integer coefficientA) {
		this.coefficientA = coefficientA;
	}

	public void setCoefficient_R(Integer coefficient_R) {
		this.coefficient_R = coefficient_R;
	}

	public void setContractPaymentDTO(ContractPaymentDTO contractPaymentDTO) {
		this.contractPaymentDTO = contractPaymentDTO;
	}

	public void setContractBillingDTO(ContractBillingDTO contractBillingDTO) {
		this.contractBillingDTO = contractBillingDTO;
	}

	public void setAfactor(Boolean afactor) {
		this.afactor = afactor;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	

	public void setContadorNum(String contadorNum) {
		this.contadorNum = contadorNum;
	}

	public void setDigitA(Integer digitA) {
		this.digitA = digitA;
	}

	public void setDigitR(Integer digitR) {
		this.digitR = digitR;
	}

	public void setFixedPriceOp(Boolean fixedPriceOp) {
		this.fixedPriceOp = fixedPriceOp;
	}

	public void setFixedPriceValue(Integer fixedPriceValue) {
		this.fixedPriceValue = fixedPriceValue;
	}

	public void setMeterStatusDTO(MeterStatusDTO meterStatusDTO) {
		this.meterStatusDTO = meterStatusDTO;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public void setOpIguala(Boolean opIguala) {
		this.opIguala = opIguala;
	}

	public void setIgualaAnterior(Boolean igualaAnterior) {
		IgualaAnterior = igualaAnterior;
	}

	public void setTenant(Boolean tenant) {
		this.tenant = tenant;
	}

	public void setInterlConsumption(Boolean interlConsumption) {
		this.interlConsumption = interlConsumption;
	}

	public void setAuthority(Boolean authority) {
		this.authority = authority;
	}

	public void setExemptPenality(Boolean exemptPenality) {
		this.exemptPenality = exemptPenality;
	}

	public void setExonerado(Boolean exonerado) {
		this.exonerado = exonerado;
	}

	public void setInvoiceBillingOrder(Boolean invoiceBillingOrder) {
		this.invoiceBillingOrder = invoiceBillingOrder;
	}

	public void setInvoicePaymentOrder(Boolean invoicePaymentOrder) {
		this.invoicePaymentOrder = invoicePaymentOrder;
	}

	public void setSubjectTocut(Boolean subjectTocut) {
		this.subjectTocut = subjectTocut;
	}

	public Integer getFactor() {
		return factor;
	}

	public void setFactor(Integer factor) {
		this.factor = factor;
	}

	
}
