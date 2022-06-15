package core.model.setting;

public class Receptor {
	private Integer receptorID;
	private String receptorName;
	private Integer receptorPower;
	private Integer receptorConsumption;
	private String receptorComment;
	private double qunt;
	
	public double getQunt() {
		return qunt;
	}
	public void setQunt(double qunt) {
		this.qunt = qunt;
	}
	public Integer getReceptorID() {
		return receptorID;
	}
	public void setReceptorID(Integer receptorID) {
		this.receptorID = receptorID;
	}
	public String getReceptorName() {
		return receptorName;
	}
	public void setReceptorName(String receptorName) {
		this.receptorName = receptorName;
	}
	public Integer getReceptorPower() {
		return receptorPower;
	}
	public void setReceptorPower(Integer receptorPower) {
		this.receptorPower = receptorPower;
	}
	public Integer getReceptorConsumption() {
		return receptorConsumption;
	}
	public void setReceptorConsumption(Integer receptorConsumption) {
		this.receptorConsumption = receptorConsumption;
	}
	public String getReceptorComment() {
		return receptorComment;
	}
	public void setReceptorComment(String receptorComment) {
		this.receptorComment = receptorComment;
	}
	
	
}
