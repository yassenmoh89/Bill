package core.bill.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_REG_ROLE_RESOURCE ")
public class ResourceDTO extends GenericDTO{

	@Id
	@Column(name="ROLE_RESOURCE_ID" , nullable = false,columnDefinition = "BIGINT")
	private Long roleResourceId ; 
	
	@Column(name="RESOURCE_NAME")
	private String resourceName ; 
	
	@Column(name="URL")
	private String url ; 
	
	@Column(name="BUNDLE")
	private String bundle ; 
	
	@Column(name="STATUS")
	private String status ; 
	
	@Column(name="IS_SHOW" ,nullable = false, columnDefinition = "TINYINT")
	private Boolean appear;
	
	@Column(name="CATEGORY")
	private String category ; 
	
	@Column(name="SUB_CATEGORY")
	private String subCategory ; 
	
	public Long getRoleResourceId() {
		return roleResourceId;
	}

	public void setRoleResourceId(Long roleResourceId) {
		this.roleResourceId = roleResourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBundle() {
		return bundle;
	}

	public void setBundle(String bundle) {
		this.bundle = bundle;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getAppear() {
		return appear;
	}

	public void setAppear(Boolean appear) {
		this.appear = appear;
	}

	public String getCategory() {
		return category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	
	
	
}
