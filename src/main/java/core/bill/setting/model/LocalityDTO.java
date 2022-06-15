package core.bill.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name = "SEG_SYS_LOCALITY") //[TC Localidad
public class LocalityDTO extends GenericDTO{
	
	//LocalID
	//Localidad
	//Descripcion
	//LInicial
	//PenZ
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LOCALITY_ID")
	private Long localityId ;
	
	@Column(name = "LOCAL_CODE")
	private String localityCode ; 
	
	@Column(name = "DESCRIPTION")
	private String description ; 
	
	@Column(name = "LINICIAL" ,nullable = false, columnDefinition = "TINYINT")
	private Boolean lInicial ;
	
	@Column(name = "PEN_Z" ,nullable = false, columnDefinition = "TINYINT")
	private Boolean penZ ;
	
	@Column(name = "STATUS" )
	private String status ; 

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getlInicial() {
		return lInicial;
	}

	public void setlInicial(Boolean lInicial) {
		this.lInicial = lInicial;
	}

	public Boolean getPenZ() {
		return penZ;
	}

	public void setPenZ(Boolean penZ) {
		this.penZ = penZ;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getLocalityId() {
		return localityId;
	}

	public void setLocalityId(Long localityId) {
		this.localityId = localityId;
	}

	public String getLocalityCode() {
		return localityCode;
	}

	public void setLocalityCode(String localityCode) {
		this.localityCode = localityCode;
	} 
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof LocalityDTO) && (this.getLocalityId() != null)
		        ? this.localityId.equals(((LocalityDTO) obj).localityId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.localityId != null)
		        ? (this.getClass().hashCode() + this.localityId.hashCode())
		        : super.hashCode();
	}
	

}
