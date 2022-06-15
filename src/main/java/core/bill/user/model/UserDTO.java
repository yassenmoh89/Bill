package core.bill.user.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_REG_USER")
public class UserDTO extends GenericDTO implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="USER_ID" , nullable = false,columnDefinition = "BIGINT")
	public Long userId ; 
	
	@Column(name="USER_NAME")
	private String userName ; 
	
	@Column(name="PASSWORD")
	private String password ; 
	
	@Column(name="USER_TYPE")
	private Integer userType ; 
	
	@Column(name="IS_ACTIVE" ,nullable = false, columnDefinition = "TINYINT")
	private Boolean active ; 
	
	@Column(name="USER_CODE")
	private String userCode ; 
	
	@Column(name="NEED_PASS_CHANGE"  ,nullable = false, columnDefinition = "TINYINT")
	private Boolean needPassChange ; 
	
	@Column(name="LOGIN_ATTEMPT")
	private Integer loginAttempt ;

	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "PROFILE_ID")
	private UserProfileDTO userProfileDTO ;
	
	@OneToMany(cascade = { CascadeType.ALL } , fetch = FetchType.EAGER , mappedBy = "userDTO")
	private Set<UserRoleDTO> UserRoleDTO ;
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Boolean getNeedPassChange() {
		return needPassChange;
	}

	public void setNeedPassChange(Boolean needPassChange) {
		this.needPassChange = needPassChange;
	}

	public Integer getLoginAttempt() {
		return loginAttempt;
	}

	public void setLoginAttempt(Integer loginAttempt) {
		this.loginAttempt = loginAttempt;
	}

	public UserProfileDTO getUserProfileDTO() {
		return userProfileDTO;
	}

	public void setUserProfileDTO(UserProfileDTO userProfileDTO) {
		this.userProfileDTO = userProfileDTO;
	}

	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Set<UserRoleDTO> getUserRoleDTO() {
		return UserRoleDTO;
	}

	public void setUserRoleDTO(Set<UserRoleDTO> userRoleDTO) {
		UserRoleDTO = userRoleDTO;
	}

	
	

}
