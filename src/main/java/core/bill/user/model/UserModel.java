package core.bill.user.model;

public class UserModel {

	private Long userId;
	private String userName;
	private String userCode;

	public Long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
