package core.bill.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_REG_ROLE ")
public class RoleDTO extends GenericDTO{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ROLE_ID", nullable = false,columnDefinition = "BIGINT")
	private Long roleId ; 
	
	@Column(name="ROLE_NAME")
	private String roleName ;
	
	@Column(name="ROLE_CODE")
	private String roleCode ;
	
	@Column(name="COMMENT")
	private String comment ; 
	
	@Column(name="STATUS")
	private String status ;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	} 
	
	@Override
	public boolean equals(Object obj) {
		Long _current = new Long(this.getRoleId());
		Long _coming = new Long(((RoleDTO) obj).getRoleId());
		
		int i = _current.compareTo(_coming);
		if(i==0)
		{
			return true;
		}else
		{
			return false ;
		}
	}

}
