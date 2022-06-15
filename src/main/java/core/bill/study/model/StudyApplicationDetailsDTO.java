package core.bill.study.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_STUDY_APPLICATION_DETAILS")
public class StudyApplicationDetailsDTO extends GenericDTO{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="STUDY_DETAILS_ID",nullable = false,columnDefinition = "BIGINT")
	private Long studyDetailsId ; 
	
	@Column(name="QUANTITY")
	private double quantity ;
	
	@Column(name="PRICE")
	private double price ;
	

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEM_ID")
	private ItemsDTO itemDTO ;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STUDY_ID")
	private StudyApplicationDTO studyApplicationDTO ;
	
	@Column(name="HAVE",columnDefinition = "TINYINT")
	private Boolean have ;

	public Long getStudyDetailsId() {
		return studyDetailsId;
	}

	public void setStudyDetailsId(Long studyDetailsId) {
		this.studyDetailsId = studyDetailsId;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ItemsDTO getItemDTO() {
		return itemDTO;
	}

	public void setItemDTO(ItemsDTO itemDTO) {
		this.itemDTO = itemDTO;
	}

	public StudyApplicationDTO getStudyApplicationDTO() {
		return studyApplicationDTO;
	}

	public void setStudyApplicationDTO(StudyApplicationDTO studyApplicationDTO) {
		this.studyApplicationDTO = studyApplicationDTO;
	}

	public Boolean getHave() {
		return have;
	}

	public void setHave(Boolean have) {
		this.have = have;
	}
	
	


}
