package core.bill.user.model;


import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_REG_GROUP ")
public class GroupDTO extends GenericDTO{
	
	@Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.groupId!= null ? this.groupId.hashCode() : 0);
        return hash;
    }
	
	@Override
	public boolean equals(Object obj) {
	
		if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
		GroupDTO other = (GroupDTO) obj;
		return Objects.equals(groupId, other.groupId);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="GROUP_ID",nullable = false,columnDefinition = "BIGINT")
	private Long groupId ; 
	
	@Column(name="GROUP_NAME")
	private String groupName ;
	
	@Column(name="GROUP_CODE" , unique = true)
	private String groupCode ; 
	
	@Column(name="COMMENT")
	private String comment ; 
	
	@Column(name="STATUS")
	private String status ;
	
	@OneToMany(cascade = { CascadeType.ALL } 
    						, fetch = FetchType.EAGER , mappedBy = "groupDTO")
	
	private Set<GroupResourceDTO>  groupResourceDTOs ;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
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

	@Override
	public String toString() {
		return "GroupDTO [groupId=" + groupId + ", groupName=" + groupName + ", groupCode=" + groupCode + ", comment="
				+ comment + ", status=" + status + "]";
	}
	
	

	public Set<GroupResourceDTO> getGroupResourceDTOs() {
		return groupResourceDTOs;
	}

	public void setGroupResourceDTOs(Set<GroupResourceDTO> groupResourceDTOs) {
		this.groupResourceDTOs = groupResourceDTOs;
	} 
	
	
	


}
