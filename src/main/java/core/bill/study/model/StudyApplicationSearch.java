package core.bill.study.model;

import java.util.Date;

public class StudyApplicationSearch {
	
	private int studyID;
	private Long customerId;
	private String status;
	private int studyDetailsId;
	private int itemId;
	private Date fromDate;
	
	
	public int getStudyID() {
		return studyID;
	}

	public void setStudyID(int studyID) {
		this.studyID = studyID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStudyDetailsId() {
		return studyDetailsId;
	}

	public void setStudyDetailsId(int studyDetailsId) {
		this.studyDetailsId = studyDetailsId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	

}
