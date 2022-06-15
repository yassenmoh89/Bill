package core.bill.invoice.model;

import java.math.BigDecimal;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name = "SEG_INV_CHAREGE_TYPE")
public class ChargeDTO  extends GenericDTO{
//TC Ingresos
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CHARGE_ID")
	private Long chargeId ; 
	
	@Column(name = "CHARGE_CODE")
	private String chargeCodev;
	
	@Column(name = "DESCRIPTION")
	private String description ;
	
	@Column(name = "DESCRIPTION_ENG")
	private String descriptionEng ; 
	
	@Column(name = "AMOUNT")
	private BigDecimal amount ;
	
	@Column(name = "COMMENT")
	private String comment;
	
	@Column(name = "BASIC_TYPE")
	private String basicType ;
	
	@Column(name = "STATUS")
	private String status ;

	public Long getChargeId() {
		return chargeId;
	}

	public String getChargeCodev() {
		return chargeCodev;
	}

	public String getDescription() {
		return description;
	}

	public String getDescriptionEng() {
		return descriptionEng;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getComment() {
		return comment;
	}

	public String getBasicType() {
		return basicType;
	}

	public String getStatus() {
		return status;
	}

	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
	}

	public void setChargeCodev(String chargeCodev) {
		this.chargeCodev = chargeCodev;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDescriptionEng(String descriptionEng) {
		this.descriptionEng = descriptionEng;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setBasicType(String basicType) {
		this.basicType = basicType;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

	
	
	

}
