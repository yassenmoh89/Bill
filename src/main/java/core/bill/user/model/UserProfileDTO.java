package core.bill.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;



@Entity
@Table(name="SEG_REG_USER_PROFILE")
public class UserProfileDTO extends GenericDTO{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PROFILE_ID" ,nullable = false,columnDefinition = "BIGINT")
	private Long profileId ; 
	
	@Column(name="PHONE")
	private String phone ; 
	
	@Column(name="MOBILE")
	private String mobile ; 
	
	@Column(name="ADDRESS")
	private String address ; 
	
	@Column(name="EMAIL")
	private String email ; 
	
	@Column(name="COMM_MODE")
	private String commMode ; 
	

	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCommMode() {
		return commMode;
	}

	public void setCommMode(String commMode) {
		this.commMode = commMode;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	
	

}
