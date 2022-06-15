package core.model.setting;

public class Fase {
	private int faseID ; 
	private String fase;
	private String descrip ;
	private String descripEng ;
	private double cost ;
	
	
	public int getFaseID() {
		return faseID;
	}
	public void setFaseID(int faseID) {
		this.faseID = faseID;
	}
	public String getFase() {
		return fase;
	}
	public void setFase(String fase) {
		this.fase = fase;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public String getDescripEng() {
		return descripEng;
	}
	public void setDescripEng(String descripEng) {
		this.descripEng = descripEng;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	} 
	
	

}
