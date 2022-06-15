package core.bill.user.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SEG_REG_SESSION")
public class UserSessionDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="SESSION_ID" , nullable = false,columnDefinition = "BIGINT")
	public Long sessionId ; 
	
	@Column(name="USER_NAME")
	private String userName ;
	
	@Column(name="HOST_NAME")
	private String hostName ;
	
	@Column(name="IP_ADDRESS")
	private String ipAddress ;
	
	@Column(name="FORM_NAME")
	private String formName ;
	
    @Temporal(value=TemporalType.TIMESTAMP)
    @Column(name="SESSION_DATE")
    private Date sessionDate;

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public Date getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(Date sessionDate) {
		this.sessionDate = sessionDate;
	}
    
    
}
