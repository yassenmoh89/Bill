package core.bill.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name = "SEG_SYS_TYPE")
public class TypeDTO extends GenericDTO { //TC Tipo
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TYPE_ID")
	private Long typeId; 
	
	@Column(name = "TYPE")
	private String typeCode ; 
	
	@Column(name = "DESCRIPTION")
	private String description ; 
	
	@Column(name = "DESCRIPTION_ENG")
	private String descriptionEng ; 
	
	@Column(name = "STATUS")
	private String status ;

	
	
	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
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
		return (obj instanceof TypeDTO) && (this.typeId != null)
		        ? this.typeId.equals(((TypeDTO) obj).typeId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.typeId != null)
		        ? (this.getClass().hashCode() + this.typeId.hashCode())
		        : super.hashCode();
	}

}
