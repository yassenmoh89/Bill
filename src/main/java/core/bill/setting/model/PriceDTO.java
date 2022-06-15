package core.bill.setting.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import core.bill.common.dto.GenericDTO;

@Entity
@Table(name="SEG_SYS_PRICE") // [TS Precios]
public class PriceDTO extends GenericDTO{

	//[Rango]
	//[Minimo]
	//[Maximo]
	//[Precio]
	//[Descripcion]
	//[Descripcion_Eng]
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PRICE_ID")
	private Long priceId; 
	
	@Column(name="RANK")
	private String rank ;
	
	@Column(name="MINIMUM")
	private String minimum ;
	
	@Column(name="MAXIMUM")
	private String maximum ;
	
	@Column(name="PRICE")
	private BigDecimal Price ;
	
	@Column(name="DESCRIPTION")
	private String description ;
	
	@Column(name="DESCRIPTION_ENG")
	private String descriptionEng ; 
	
	@Column(name="STATUS")
	private String status ; 

	public Long getPriceId() {
		return priceId;
	}

	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}

	public String getMinimum() {
		return minimum;
	}

	public void setMinimum(String minimum) {
		this.minimum = minimum;
	}

	public String getMaximum() {
		return maximum;
	}

	public void setMaximum(String maximum) {
		this.maximum = maximum;
	}

	public BigDecimal getPrice() {
		return Price;
	}

	public void setPrice(BigDecimal price) {
		Price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionEng() {
		return descriptionEng;
	}

	public void setDescriptionEng(String descriptionEng) {
		this.descriptionEng = descriptionEng;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof PriceDTO) && (this.priceId != null)
		        ? this.priceId.equals(((PriceDTO) obj).priceId)
		        : (obj == this);
	}
	
	@Override
	public int hashCode() {
		return (this.priceId != null)
		        ? (this.getClass().hashCode() + this.priceId.hashCode())
		        : super.hashCode();
	}
	
	
}
