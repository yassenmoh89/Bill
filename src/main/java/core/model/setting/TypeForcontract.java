package core.model.setting;

public class TypeForcontract {

	private int typeID;
	private String typeCode;
	private String typeName ; 
	private String typeNameUS;
	
	public int getTypeID() {
		return typeID;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public String getTypeNameUS() {
		return typeNameUS;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public void setTypeNameUS(String typeNameUS) {
		this.typeNameUS = typeNameUS;
	}
	
	
}
