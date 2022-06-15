package core.model.setting;

public class Location {
	private int localID=0;
	private String code="";
	private String locationName="";
	private boolean initial =false ; 
	private boolean penZ=false;
	public int getLocalID() {
		return localID;
	}
	public void setLocalID(int localID) {
		this.localID = localID;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public boolean isInitial() {
		return initial;
	}
	public void setInitial(boolean initial) {
		this.initial = initial;
	}
	public boolean getPenZ() {
		return penZ;
	}
	public void setPenZ(boolean penZ) {
		this.penZ = penZ;
	}
	
	

}
