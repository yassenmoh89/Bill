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
@Table(name="SEG_REG_GROUP_ROLE")
public class GroupRoleDTO extends GenericDTO implements Comparable<GroupRoleDTO>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="GROUP_ROLE_ID" , nullable = false,columnDefinition = "BIGINT")
	private Long groupRoleId ; 
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GROUP_RESOURCE_ID")
	GroupResourceDTO groupResourceDTO ;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID" )
	private RoleDTO roleDTO ;
	
	public Long getGroupRoleId() {
		return groupRoleId;
	}
	public void setGroupRoleId(Long groupRoleId) {
		this.groupRoleId = groupRoleId;
	}
	
	public RoleDTO getRoleDTO() {
		return roleDTO;
	}
	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}
	public GroupResourceDTO getGroupResourceDTO() {
		return groupResourceDTO;
	}
	public void setGroupResourceDTO(GroupResourceDTO groupResourceDTO) {
		this.groupResourceDTO = groupResourceDTO;
	}
	
	@Override
	public int compareTo(GroupRoleDTO o) {
	int i = this.roleDTO.getRoleId().compareTo(o.getRoleDTO().getRoleId());
	System.out.println("compareTo :"+i);
		return this.roleDTO.getRoleId().compareTo(o.getRoleDTO().getRoleId());
	}
	
	@Override
	public boolean equals(Object obj) {
		int j =0 ;
		Long _CURRENT_GROUP_RESOURCE_ID = this.groupResourceDTO.getGroupResourceId() ;
		Long _COMING_GROUP_RESOURCE_ID = ((GroupRoleDTO) obj).getGroupResourceDTO().getGroupResourceId();
		
		Long _current = new Long(this.roleDTO.getRoleId());
		Long _coming = new Long(((GroupRoleDTO) obj).getRoleDTO().getRoleId());
		
		int i = _current.compareTo(_coming);
		
		if(_CURRENT_GROUP_RESOURCE_ID!=null && _COMING_GROUP_RESOURCE_ID!=null)
		{
			j = _CURRENT_GROUP_RESOURCE_ID.compareTo(_COMING_GROUP_RESOURCE_ID);
		}
		
		if(i==0 && j==0)
		{
			return true;
		}else
		{
			return false ;
		}
	}
	
	

	
	
	
	
	
}
