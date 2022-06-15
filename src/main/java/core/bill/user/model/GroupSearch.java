package core.bill.user.model;

public class GroupSearch {
	private String groupName ;
	private String groupCode ;
	private String status ;
	private String comment ;
	private Long groupId ;
	
	private Long groupResourceId ;
	
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getGroupResourceId() {
		return groupResourceId;
	}
	public void setGroupResourceId(Long groupResourceId) {
		this.groupResourceId = groupResourceId;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	

}
