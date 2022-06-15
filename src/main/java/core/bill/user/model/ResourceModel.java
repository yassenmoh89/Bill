package core.bill.user.model;

public class ResourceModel {
	
	private Long resourceId ; 
	private String resourceName ; 
	private String icon ; 
	private String url ; 
	private String englishName ;
	private String category ;  // commerical , study , cashier , invoice , report 
	private String subCategory ; 
	
	public String getResourceName() {
		return resourceName;
	}
	public String getIcon() {
		return icon;
	}
	public String getUrl() {
		return url;
	}
	public String getEnglishName() {
		return englishName;
	}
	
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
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
