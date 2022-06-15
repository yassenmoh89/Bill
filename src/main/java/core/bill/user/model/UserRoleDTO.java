package core.bill.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_REG_USER_ROLE")
public class UserRoleDTO extends GenericDTO{
	
	//USER_ROLE_ID
	//USER_ID
	//GROUP_ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ROLE_ID" ,nullable = false,columnDefinition = "BIGINT")
	private Long userRoleId ; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private UserDTO userDTO ; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="GROUP_ID")
	private GroupDTO  groupDTO;

	
	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public GroupDTO getGroupDTO() {
		return groupDTO;
	}

	public void setGroupDTO(GroupDTO groupDTO) {
		this.groupDTO = groupDTO;
	}

	
	@Override
	public boolean equals(Object obj) {
	 
		int i =0;
		if(userRoleId!=null)
		{
			i = userRoleId.compareTo(((UserRoleDTO) obj).getUserRoleId());
		}else
		{
			i = groupDTO.getGroupId().compareTo(((UserRoleDTO) obj).getGroupDTO().getGroupId());
		}
		 
		 boolean flag = false ;
		 if(i==0)
		 {
			 flag=true ;
		 }
		return flag ;
	}

}
