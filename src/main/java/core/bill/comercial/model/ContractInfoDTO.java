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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import core.bill.setting.model.ContractBillingDTO;
import core.bill.setting.model.ContractPaymentDTO;
import core.bill.setting.model.ContractTypeDTO;
import core.bill.setting.model.LocalityDTO;
import core.bill.setting.model.MeterStatusDTO;
import core.bill.setting.model.TypeDTO;

@Entity
@Table(name="SEG_COMM_CONTRACT_INFO")
public class ContractInfoDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONTRACT_INFO_ID")
	private Long contractInfoId ; 
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CONTRACT_ID" )
	private ContractDTO contractDTO ;
	
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
	
	@Column(name="IGUALA")
	private Integer iguala ; 
	
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

	
	public Long getContractInfoId() {
		return contractInfoId;
	}

	public void setContractInfoId(Long contractInfoId) {
		this.contractInfoId = contractInfoId;
	}

	public String getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

	public String getCircuitoNum() {
		return circuitoNum;
	}

	public void setCircuitoNum(String circuitoNum) {
		this.circuitoNum = circuitoNum;
	}

	public TypeDTO getTypeDTO() {
		return typeDTO;
	}

	public void setTypeDTO(TypeDTO typeDTO) {
		this.typeDTO = typeDTO;
	}

	public ContractTypeDTO getContractTypeDTO() {
		return contractTypeDTO;
	}

	public void setContractTypeDTO(ContractTypeDTO contractTypeDTO) {
		this.contractTypeDTO = contractTypeDTO;
	}

	public Integer getCoefficientA() {
		return coefficientA;
	}

	public void setCoefficientA(Integer coefficientA) {
		this.coefficientA = coefficientA;
	}

	public Integer getCoefficient_R() {
		return coefficient_R;
	}

	public void setCoefficient_R(Integer coefficient_R) {
		this.coefficient_R = coefficient_R;
	}

	public ContractPaymentDTO getContractPaymentDTO() {
		return contractPaymentDTO;
	}

	public void setContractPaymentDTO(ContractPaymentDTO contractPaymentDTO) {
		this.contractPaymentDTO = contractPaymentDTO;
	}

	public ContractBillingDTO getContractBillingDTO() {
		return contractBillingDTO;
	}

	public void setContractBillingDTO(ContractBillingDTO contractBillingDTO) {
		this.contractBillingDTO = contractBillingDTO;
	}

	public Boolean getAfactor() {
		return afactor;
	}

	public void setAfactor(Boolean afactor) {
		this.afactor = afactor;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Integer getIguala() {
		return iguala;
	}

	public void setIguala(Integer iguala) {
		this.iguala = iguala;
	}

	public String getContadorNum() {
		return contadorNum;
	}

	public void setContadorNum(String contadorNum) {
		this.contadorNum = contadorNum;
	}

	public Integer getDigitA() {
		return digitA;
	}

	public void setDigitA(Integer digitA) {
		this.digitA = digitA;
	}

	public Integer getDigitR() {
		return digitR;
	}

	public void setDigitR(Integer digitR) {
		this.digitR = digitR;
	}

	public Boolean getFixedPriceOp() {
		return fixedPriceOp;
	}

	public void setFixedPriceOp(Boolean fixedPriceOp) {
		this.fixedPriceOp = fixedPriceOp;
	}

	public Integer getFixedPriceValue() {
		return fixedPriceValue;
	}

	public void setFixedPriceValue(Integer fixedPriceValue) {
		this.fixedPriceValue = fixedPriceValue;
	}

	public MeterStatusDTO getMeterStatusDTO() {
		return meterStatusDTO;
	}

	public void setMeterStatusDTO(MeterStatusDTO meterStatusDTO) {
		this.meterStatusDTO = meterStatusDTO;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Boolean getOpIguala() {
		return opIguala;
	}

	public void setOpIguala(Boolean opIguala) {
		this.opIguala = opIguala;
	}

	public Boolean getIgualaAnterior() {
		return IgualaAnterior;
	}

	public void setIgualaAnterior(Boolean igualaAnterior) {
		IgualaAnterior = igualaAnterior;
	}

	public ContractDTO getContractDTO() {
		return contractDTO;
	}

	public void setContractDTO(ContractDTO contractDTO) {
		this.contractDTO = contractDTO;
	}

	public LocalityDTO getLocalityDTO() {
		return localityDTO;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setLocalityDTO(LocalityDTO localityDTO) {
		this.localityDTO = localityDTO;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	

}
