package core.model.setting;

public class PowerCost {
	private String id ;
	private int min;
	private int max;
	private int price;
	public String getId() {
		return id;
	}
	public int getMin() {
		return min;
	}
	public int getMax() {
		return max;
	}
	public int getPrice() {
		return price;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	

}
