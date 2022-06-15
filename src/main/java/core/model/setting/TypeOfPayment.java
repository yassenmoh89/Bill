package core.model.setting;

public class TypeOfPayment {
	
	private int paymentTypeID ; 
	private String payment; 
	private String paymentUS ;
	
	public int getPaymentTypeID() {
		return paymentTypeID;
	}
	public String getPayment() {
		return payment;
	}
	public String getPaymentUS() {
		return paymentUS;
	}
	public void setPaymentTypeID(int paymentTypeID) {
		this.paymentTypeID = paymentTypeID;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public void setPaymentUS(String paymentUS) {
		this.paymentUS = paymentUS;
	} 
	
	

}
