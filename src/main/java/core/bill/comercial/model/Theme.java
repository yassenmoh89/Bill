package core.bill.comercial.model;

public class Theme {
	private int id;  
    private String displayName; 
    private String name;
    
    
    public Theme() {}
    
   
    
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
    
	@Override
    public String toString() {
        return displayName;
    }
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	

}
