package core.bill.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_SYS_CIRCUITS") //[Tbl_Circuits]
public class CircuitsDTO extends GenericDTO{

	//[CircuitTableID]
	//[Localidad]
	//[CircuitNumber]
	//[Date_Added]
	//[CurrentInspectorID]
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CIRCUIT_ID")
	private Long circuitId ; 
	
	@Column(name="LOCAL_CODE")
	private String localCode ;
	
	@Column(name="CIRCUIT_NUMBER")
	private String circuitNumber ; 
	
	@Column(name="INSPECTOR_ID")
	private String inspectorId; 
	
	@Column(name="STATUS")
	private String status ;

	public Long getCircuitId() {
		return circuitId;
	}

	public void setCircuitId(Long circuitId) {
		this.circuitId = circuitId;
	}

	public String getLocalCode() {
		return localCode;
	}

	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}

	public String getCircuitNumber() {
		return circuitNumber;
	}

	public void setCircuitNumber(String circuitNumber) {
		this.circuitNumber = circuitNumber;
	}

	public String getInspectorId() {
		return inspectorId;
	}

	public void setInspectorId(String inspectorId) {
		this.inspectorId = inspectorId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	} 
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof CircuitsDTO) && (this.circuitId != null)
		        ? this.circuitId.equals(((CircuitsDTO) obj).circuitId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.circuitId != null)
		        ? (this.getClass().hashCode() + this.circuitId.hashCode())
		        : super.hashCode();
	}
	
	
}
