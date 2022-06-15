package core.bill.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_SYS_SUPPLY")
public class SupplyDTO extends GenericDTO {

	//[TSuministros]
	//[TSDescripcion]
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="SUPPLY_ID")
	private Long supplyId ;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="DESCRIPTION_ENG")
	private String descriptionEng ; 
	
	@Column(name="STATUS")
	private String status ;

	public Long getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(Long supplyId) {
		this.supplyId = supplyId;
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
		return (obj instanceof SupplyDTO) && (this.supplyId != null)
		        ? this.supplyId.equals(((SupplyDTO) obj).supplyId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.supplyId != null)
		        ? (this.getClass().hashCode() + this.supplyId.hashCode())
		        : super.hashCode();
	}
	
	
}
