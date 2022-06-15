package core.bill.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_SYS_SECTOR") //TC Sector
public class SectorDTO extends GenericDTO{

	//[SectorID]
	//[Sector]
	//[Descripcion]
	//[Reactiva]
	//[Fijo]
	//[SInicial]
	//[Descripcion_Eng]
	//[PenZ]
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SECTOR_ID")
	private Long sectorId ; 
	
	@Column(name="SECTOR_CODE")
	private String sectorCode; 
	
	@Column(name="DESCRIPTION")
	private String description ; 
	
	@Column(name="REACTIVA" , nullable = false, columnDefinition = "TINYINT")
	private Boolean reactiva ; 
	
	@Column(name="FIJO",nullable = false, columnDefinition = "TINYINT")
	private Boolean fijo ;
	
	@Column(name="INICIAL")
	private String inicial ;
	
	@Column(name="DESCRIPTION_ENG")
	private String descriptionEng ;
	
	@Column(name="PEN_Z", nullable = false, columnDefinition = "TINYINT")
	private Boolean penZ ;
	
	@Column(name="STATUS")
	private String status ;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSectorCode() {
		return sectorCode;
	}

	public void setSectorCode(String sectorCode) {
		this.sectorCode = sectorCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getReactiva() {
		return reactiva;
	}

	public void setReactiva(Boolean reactiva) {
		this.reactiva = reactiva;
	}

	public Boolean getFijo() {
		return fijo;
	}

	public void setFijo(Boolean fijo) {
		this.fijo = fijo;
	}

	public String getInicial() {
		return inicial;
	}

	public void setInicial(String inicial) {
		this.inicial = inicial;
	}

	public String getDescriptionEng() {
		return descriptionEng;
	}

	public void setDescriptionEng(String descriptionEng) {
		this.descriptionEng = descriptionEng;
	}

	public Boolean getPenZ() {
		return penZ;
	}

	public void setPenZ(Boolean penZ) {
		this.penZ = penZ;
	}

	public Long getSectorId() {
		return sectorId;
	}

	public void setSectorId(Long sectorId) {
		this.sectorId = sectorId;
	} 
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof SectorDTO) && (this.sectorId != null)
		        ? this.sectorId.equals(((SectorDTO) obj).sectorId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.sectorId != null)
		        ? (this.getClass().hashCode() + this.sectorId.hashCode())
		        : super.hashCode();
	}
	
}
