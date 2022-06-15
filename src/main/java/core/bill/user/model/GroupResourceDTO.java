package core.bill.user.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_REG_GROUP_RESOURCE")
public class GroupResourceDTO extends GenericDTO{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="GROUP_RESOURCE_ID" , nullable = false,columnDefinition = "BIGINT")
	private Long groupResourceId ; 
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GROUP_ID")
    private GroupDTO groupDTO;
	
	@ManyToOne(fetch = FetchType.EAGER  )
	@JoinColumn(name = "ROLE_RESOURCE_ID")
	ResourceDTO resourceDTO ;
	
	
	@OneToMany(fetch = FetchType.EAGER 
			,cascade = { CascadeType.ALL} , mappedBy = "groupResourceDTO" )
	private Set<GroupRoleDTO> groupRoleDTOs ;
	
	public Long getGroupResourceId() {
		return groupResourceId;
	}
	public void setGroupResourceId(Long groupResourceId) {
		this.groupResourceId = groupResourceId;
	}
	
	
	public Set<GroupRoleDTO> getGroupRoleDTOs() {
		return groupRoleDTOs;
	}
	public void setGroupRoleDTOs(Set<GroupRoleDTO> groupRoleDTOs) {
		this.groupRoleDTOs = groupRoleDTOs;
	}
	
	public ResourceDTO getResourceDTO() {
		return resourceDTO;
	}
	public void setResourceDTO(ResourceDTO resourceDTO) {
		this.resourceDTO = resourceDTO;
	}
	public GroupDTO getGroupDTO() {
		return groupDTO;
	}
	public void setGroupDTO(GroupDTO groupDTO) {
		this.groupDTO = groupDTO;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		Long _current = new Long(this.resourceDTO.getRoleResourceId());
		Long _coming = new Long(((GroupResourceDTO) obj).getResourceDTO().getRoleResourceId());
		
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
