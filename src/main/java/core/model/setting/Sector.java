package core.model.setting;

public class Sector {
	private int sectorID=0; 
	private String sectorName=""; 
	private String descrip=""; 
	private boolean reactive= false;
	private boolean fijo=false;
	public int getSectorID() {
		return sectorID;
	}
	public void setSectorID(int sectorID) {
		this.sectorID = sectorID;
	}
	public String getSectorName() {
		return sectorName;
	}
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public boolean isReactive() {
		return reactive;
	}
	public void setReactive(boolean reactive) {
		this.reactive = reactive;
	}
	public boolean isFijo() {
		return fijo;
	}
	public void setFijo(boolean fijo) {
		this.fijo = fijo;
	}
	
	
	

}
