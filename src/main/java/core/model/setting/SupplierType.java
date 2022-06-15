package core.model.setting;

public class SupplierType {
	
	/// original table in Database is [TC Suministros]
	private int supplierTypeID;
	private String suppliertypeName;
	
	
	public int getSupplierTypeID() {
		return supplierTypeID;
	}
	public void setSupplierTypeID(int supplierTypeID) {
		this.supplierTypeID = supplierTypeID;
	}
	public String getSuppliertypeName() {
		return suppliertypeName;
	}
	public void setSuppliertypeName(String suppliertypeName) {
		this.suppliertypeName = suppliertypeName;
	} 
	
	
}
