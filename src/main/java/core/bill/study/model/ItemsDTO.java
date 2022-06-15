package core.bill.study.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name = "SEG_SYS_ITEMS")
public class ItemsDTO extends GenericDTO{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ITEM_ID", nullable = false,columnDefinition = "BIGINT")
	private Long itemId ; 
	
	@Column(name="ITEM_NAME")
	private String itemName ;
	
	@Column(name="ITEM_CODE")
	private String itemCode ;
	
	@Column(name="ITEM_PRICE")
	private double itemPrice ;
	
	@Column(name="ITEM_GROUP_ID")
	private Long itemGroupId ;

	@Column(name="ITEM_TYPE")
	private Long itemType ;

	@Column(name="STATUS")
	private String status ;
	
	
	
	
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}


	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Long getItemGroupId() {
		return itemGroupId;
	}

	public void setItemGroupId(Long itemGroupId) {
		this.itemGroupId = itemGroupId;
	}

	public Long getItemType() {
		return itemType;
	}

	public void setItemType(Long itemType) {
		this.itemType = itemType;
	}


	@Override
	public boolean equals(Object obj) {
		Long _current = new Long(this.getItemId());
		Long _coming = new Long(((ItemsDTO) obj).getItemId());
		
		int i = _current.compareTo(_coming);
		if(i==0)
		{
			return true;
		}else
		{
			return false ;
		}
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
