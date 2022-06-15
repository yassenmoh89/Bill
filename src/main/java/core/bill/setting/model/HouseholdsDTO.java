package core.bill.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name = "SEG_SYS_HOUSEHOUSE_TYPE") // TC Viviendas
public class HouseholdsDTO extends GenericDTO{

	//[TVivienda]
	//[TVDescripcion]
	//[TVDescripcion_Eng]
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "HOUSE_HOLDING_TYPE_ID")
	private Long houseHoldingTypeId ; 
	
	@Column(name = "DESCRIPTION")
	private String description ; 
	
	@Column(name = "DESCRIPTION_ENG")
	private String descriptionEng ;
	
	@Column(name = "STATUS")
	private String status ;

	public Long getHouseHoldingTypeId() {
		return houseHoldingTypeId;
	}

	public void setHouseHoldingTypeId(Long houseHoldingTypeId) {
		this.houseHoldingTypeId = houseHoldingTypeId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof HouseholdsDTO) && (this.getHouseHoldingTypeId() != null)
		        ? this.houseHoldingTypeId.equals(((HouseholdsDTO) obj).houseHoldingTypeId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.houseHoldingTypeId != null)
		        ? (this.getClass().hashCode() + this.houseHoldingTypeId.hashCode())
		        : super.hashCode();
	}
	
	
	
}
