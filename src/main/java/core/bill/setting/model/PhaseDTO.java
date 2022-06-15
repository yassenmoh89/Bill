package core.bill.setting.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_SYS_PHASE") //TC Fase
public class PhaseDTO extends GenericDTO{

	//[FaseID]
	//[Fase]
	//[Descripcion]
	//[Coste]
	//[Descripcion_Eng]
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PHASE_ID")
	private Long phaseId ; 
	
	@Column(name="PHASE_CODE")
	private String phaseCode ;
	
	@Column(name="COST")
	private BigDecimal cost ; 
	
	@Column(name="DESCRIPTION")
	private String description ;
	
	@Column(name="DESCRIPTION_ENG")
	private String descriptionEng ;
	
	@Column(name="STATUS")
	private String status ;

	public Long getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(Long phaseId) {
		this.phaseId = phaseId;
	}

	public String getPhaseCode() {
		return phaseCode;
	}

	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionEng() {
		return descriptionEng;
	}

	public void setDescriptionEng(String descriptionEng) {
		this.descriptionEng = descriptionEng;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	} 
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof PhaseDTO) && (this.getPhaseId() != null)
		        ? this.phaseId.equals(((PhaseDTO) obj).phaseId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.phaseId != null)
		        ? (this.getClass().hashCode() + this.phaseId.hashCode())
		        : super.hashCode();
	}
	
	
}
