package core.bill.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_SYS_INSPECTION_DECISION") //[Tbl_TC_InspectionDecision]
public class InspectionDecisionTypeDTO extends GenericDTO{

	//[DicisionTypeID]
	//[DicisionTypeNameEng]
	//[DicisionTypeNameSp]
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="DECISION_TYPE_ID")
	private Long decisionTypeId ;
	
	@Column(name="DECISION_TYPE")
	private String decisionType;
	
	@Column(name="DECISION_TYPE_ENG")
	private String decisionTypeEng; 
	
	@Column(name="STATUS")
	private String status ;

	public Long getDecisionTypeId() {
		return decisionTypeId;
	}

	public void setDecisionTypeId(Long decisionTypeId) {
		this.decisionTypeId = decisionTypeId;
	}

	public String getDecisionType() {
		return decisionType;
	}

	public void setDecisionType(String decisionType) {
		this.decisionType = decisionType;
	}

	public String getDecisionTypeEng() {
		return decisionTypeEng;
	}

	public void setDecisionTypeEng(String decisionTypeEng) {
		this.decisionTypeEng = decisionTypeEng;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	} 
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof InspectionDecisionTypeDTO) && (this.getDecisionTypeId() != null)
		        ? this.decisionTypeId.equals(((InspectionDecisionTypeDTO) obj).decisionTypeId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.decisionTypeId != null)
		        ? (this.getClass().hashCode() + this.decisionTypeId.hashCode())
		        : super.hashCode();
	}
	
	
}
