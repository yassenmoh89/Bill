package core.bill.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_SYS_CONNECTION_TYPE") //[Tbl_TC_ConnectionsTypes]
public class ConnectionTypeDTO extends GenericDTO{

	//[ConnectionTypeID]
	//[ConnectionTypeNameEng]
	//[ConnectionTypeNameSp]
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CONNECTION_TYPE_ID")
	private Long connectionTypeId ; 
	
	@Column(name="CONNECTION_TYPE")
	private String connectionType ; 
	
	@Column(name="CONNECTION_TYPE_ENG")
	private String connectionTypeEng ; 
	
	@Column(name="STATUS")
	private String status ;
	

	public Long getConnectionTypeId() {
		return connectionTypeId;
	}

	public void setConnectionTypeId(Long connectionTypeId) {
		this.connectionTypeId = connectionTypeId;
	}

	public String getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	public String getConnectionTypeEng() {
		return connectionTypeEng;
	}

	public void setConnectionTypeEng(String connectionTypeEng) {
		this.connectionTypeEng = connectionTypeEng;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof ConnectionTypeDTO) && (this.connectionTypeId != null)
		        ? this.connectionTypeId.equals(((ConnectionTypeDTO) obj).connectionTypeId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.connectionTypeId != null)
		        ? (this.getClass().hashCode() + this.connectionTypeId.hashCode())
		        : super.hashCode();
	}
	
}
