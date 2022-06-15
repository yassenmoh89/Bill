package core.model.setting;

public class Income {
	private String code;
	private String incomeNameEn;
	private String incomeNameEs;
	private Float value;
	private String comment;
	private Boolean state=false;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIncomeNameEn() {
		return incomeNameEn;
	}
	public void setIncomeNameEn(String incomeNameEn) {
		this.incomeNameEn = incomeNameEn;
	}
	public String getIncomeNameEs() {
		return incomeNameEs;
	}
	public void setIncomeNameEs(String incomeNameEs) {
		this.incomeNameEs = incomeNameEs;
	}
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}

}
