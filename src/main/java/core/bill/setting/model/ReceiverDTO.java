package core.bill.setting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_SYS_RECEIVER") //[Tbl_RECEPTORS]
public class ReceiverDTO extends GenericDTO{

	//[RECEPTORS_ID]
	//[RECEPTORS_Name]
	//[Potencia]
	//[ConsumoMensual]
	//[Comment]
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="RECEIVER_ID")
	private Long receiverId ;
	
	@Column(name="RECEIVER_NAME")
	private String  receiverName ; 
	
	@Column(name="POWER")
	private Integer power ; 
	
	@Column(name="MONTHLY_CONSUMPTION")
	private Integer monthlyConsumption ;
	
	@Column(name="COMMENT")
	private String comment ;
	
	@Column(name="STATUS")
	private String status ;

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public Integer getMonthlyConsumption() {
		return monthlyConsumption;
	}

	public void setMonthlyConsumption(Integer monthlyConsumption) {
		this.monthlyConsumption = monthlyConsumption;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof ReceiverDTO) && (this.receiverId != null)
		        ? this.receiverId.equals(((ReceiverDTO) obj).receiverId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.receiverId != null)
		        ? (this.getClass().hashCode() + this.receiverId.hashCode())
		        : super.hashCode();
	}
	
	
	
}
