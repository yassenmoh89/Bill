package core.bill.study.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import core.bill.comercial.model.CustomerDTO;
import core.bill.common.dto.GenericDTO;


@Entity
@Table(name="SEG_STUDY_APPLICATION")
public class StudyApplicationDTO extends GenericDTO{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="STUDY_ID",nullable = false,columnDefinition = "BIGINT")
	private Long studyId ; 
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name = "STUDY_DATE")
	private Date studyDate;
	
	@Column(name="APPLICATION_ID")
	private Long applicationId;
	
	@Column(name="ACCESS_TYPE",columnDefinition = "TINYINT")
	private Long accessType;
	
	@Column(name="INTERNAL_INSTALLATION_TYPE",columnDefinition = "TINYINT")
	private Long internalInstallationType;
	
	@Column(name="POSSIBILITY_TYPE",columnDefinition = "TINYINT")
	private Long possibilityType;
	
	
	@Column(name="ITEMS_TOTAL")
	private BigDecimal itemsTotal;
	
	@Column(name="CERTIFICATION_OF_STUDY")
	private Long certificationOfStudyProject;
	
	@Column(name="PREVICIONES_PRECENT")
	private BigDecimal previcionesPrecent;
	
	@Column(name="PREVICIONES_VALUE")
	private BigDecimal previousValue;
	
	@Column(name="MANO_PRECENT")
	private BigDecimal manoPrecent;
	
	@Column(name="MANO_VALUE")
	private BigDecimal manoValue;
	
	@Column(name="CNI_PRECENT")
	private BigDecimal cniPrecent;
	
	@Column(name="CNI_VALUE")
	private BigDecimal cniValue;
	
	@Column(name="DESCUENTO_VALUE")
	private BigDecimal descuentoValue;
	
	@Column(name="VIVIENDA_FEE")
	private BigDecimal viviendaFee;
	
	@Column(name="COMERCIAL_FEE")
	private BigDecimal comercialFee;	
	
	@Column(name="MEDIATENSION_FEE")
	private BigDecimal mediatensionFee;
	
	@Column(name="TRASLADO_FEE")
	private BigDecimal trasladoFee;
	
	@Column(name="CAMBIO_FEE")
	private BigDecimal cambioFee;
	
	@Column(name="FINAL_TOTAL")
	private BigDecimal finalTotal;
	
	@Column(name="USER_ID")
	private Long userId;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TECHNICAL_ID")
	StudyTechnicalDTO studyTechnicalDTO;
	
	@Column(name="CHECK_NEW",columnDefinition = "TINYINT")
	private Boolean chkNew;
	
	@Column(name="CHECK_OLD",columnDefinition = "TINYINT")
	private Boolean chkOld;
	
	@Column(name="CURRENTLY_HAS_ENERGY",columnDefinition = "TINYINT")
	private Boolean currentlyHasEnergy;
	
	@Column(name="NEVER_HAD_LIGHT",columnDefinition = "TINYINT")
	private Boolean neverHadLight;
	
	@Column(name="WORK_CONNECTION",columnDefinition = "TINYINT")
	private Boolean workConnection;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID")
	private CustomerDTO customerDTO;
	
	@Column(name="STATUS")
	private String status ;
	
	@OneToMany(fetch = FetchType.EAGER 
			,cascade = { CascadeType.ALL} , mappedBy = "studyApplicationDTO" )
	private Set<StudyApplicationDetailsDTO> studyApplicationDetailsDTOs ;


	public Long getStudyId() {
		return studyId;
	}


	public void setStudyId(Long studyId) {
		this.studyId = studyId;
	}


	public Date getStudyDate() {
		return studyDate;
	}


	public void setStudyDate(Date studyDate) {
		this.studyDate = studyDate;
	}


	public Long getApplicationId() {
		return applicationId;
	}


	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}


	public Long getAccessType() {
		return accessType;
	}


	public void setAccessType(Long accessType) {
		this.accessType = accessType;
	}


	public Long getInternalInstallationType() {
		return internalInstallationType;
	}


	public void setInternalInstallationType(Long internalInstallationType) {
		this.internalInstallationType = internalInstallationType;
	}


	public Long getPossibilityType() {
		return possibilityType;
	}


	public void setPossibilityType(Long possibilityType) {
		this.possibilityType = possibilityType;
	}


	public BigDecimal getItemsTotal() {
		return itemsTotal;
	}


	public void setItemsTotal(BigDecimal itemsTotal) {
		this.itemsTotal = itemsTotal;
	}


	public Long getCertificationOfStudyProject() {
		return certificationOfStudyProject;
	}


	public void setCertificationOfStudyProject(Long certificationOfStudyProject) {
		this.certificationOfStudyProject = certificationOfStudyProject;
	}


	public BigDecimal getPrevicionesPrecent() {
		return previcionesPrecent;
	}


	public void setPrevicionesPrecent(BigDecimal previcionesPrecent) {
		this.previcionesPrecent = previcionesPrecent;
	}


	

	public BigDecimal getManoPrecent() {
		return manoPrecent;
	}


	public void setManoPrecent(BigDecimal manoPrecent) {
		this.manoPrecent = manoPrecent;
	}


	public BigDecimal getManoValue() {
		return manoValue;
	}


	public void setManoValue(BigDecimal manoValue) {
		this.manoValue = manoValue;
	}


	public BigDecimal getCniPrecent() {
		return cniPrecent;
	}


	public void setCniPrecent(BigDecimal cniPrecent) {
		this.cniPrecent = cniPrecent;
	}


	public BigDecimal getCniValue() {
		return cniValue;
	}


	public void setCniValue(BigDecimal cniValue) {
		this.cniValue = cniValue;
	}


	public BigDecimal getDescuentoValue() {
		return descuentoValue;
	}


	public void setDescuentoValue(BigDecimal descuentoValue) {
		this.descuentoValue = descuentoValue;
	}


	public BigDecimal getViviendaFee() {
		return viviendaFee;
	}


	public void setViviendaFee(BigDecimal viviendaFee) {
		this.viviendaFee = viviendaFee;
	}


	public BigDecimal getComercialFee() {
		return comercialFee;
	}


	public void setComercialFee(BigDecimal comercialFee) {
		this.comercialFee = comercialFee;
	}


	public BigDecimal getMediatensionFee() {
		return mediatensionFee;
	}


	public void setMediatensionFee(BigDecimal mediatensionFee) {
		this.mediatensionFee = mediatensionFee;
	}


	public BigDecimal getTrasladoFee() {
		return trasladoFee;
	}


	public void setTrasladoFee(BigDecimal trasladoFee) {
		this.trasladoFee = trasladoFee;
	}


	public BigDecimal getCambioFee() {
		return cambioFee;
	}


	public void setCambioFee(BigDecimal cambioFee) {
		this.cambioFee = cambioFee;
	}


	public BigDecimal getFinalTotal() {
		return finalTotal;
	}


	public void setFinalTotal(BigDecimal finalTotal) {
		this.finalTotal = finalTotal;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public StudyTechnicalDTO getStudyTechnicalDTO() {
		return studyTechnicalDTO;
	}


	public void setStudyTechnicalDTO(StudyTechnicalDTO studyTechnicalDTO) {
		this.studyTechnicalDTO = studyTechnicalDTO;
	}


	public Boolean getChkNew() {
		return chkNew;
	}


	public void setChkNew(Boolean chkNew) {
		this.chkNew = chkNew;
	}


	public Boolean getChkOld() {
		return chkOld;
	}


	public void setChkOld(Boolean chkOld) {
		this.chkOld = chkOld;
	}


	public Boolean getCurrentlyHasEnergy() {
		return currentlyHasEnergy;
	}


	public void setCurrentlyHasEnergy(Boolean currentlyHasEnergy) {
		this.currentlyHasEnergy = currentlyHasEnergy;
	}


	public Boolean getNeverHadLight() {
		return neverHadLight;
	}


	public void setNeverHadLight(Boolean neverHadLight) {
		this.neverHadLight = neverHadLight;
	}


	public Boolean getWorkConnection() {
		return workConnection;
	}


	public void setWorkConnection(Boolean workConnection) {
		this.workConnection = workConnection;
	}

	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}


	public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}


	public Set<StudyApplicationDetailsDTO> getStudyApplicationDetailsDTOs() {
		return studyApplicationDetailsDTOs;
	}


	public void setStudyApplicationDetailsDTOs(Set<StudyApplicationDetailsDTO> studyApplicationDetailsDTOs) {
		this.studyApplicationDetailsDTOs = studyApplicationDetailsDTOs;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public BigDecimal getPreviousValue() {
		return previousValue;
	}


	public void setPreviousValue(BigDecimal previousValue) {
		this.previousValue = previousValue;
	}
	
	

	

	
	
}
