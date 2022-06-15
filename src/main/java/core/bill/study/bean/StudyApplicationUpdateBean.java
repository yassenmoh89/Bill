package core.bill.study.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.bill.comercial.model.CustomerDTO;
import core.bill.comercial.service.CustomerService;
import core.bill.common.bean.GenericBean;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.util.CustomUtil;
import core.bill.common.util.UtilConstant;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.study.model.ItemsDTO;
import core.bill.study.model.StudyApplicationDTO;
import core.bill.study.model.StudyApplicationDetailsDTO;
import core.bill.study.model.StudyApplicationSearch;
import core.bill.study.model.StudyTechnicalDTO;
import core.bill.study.service.ItemService;
import core.bill.study.service.StudyApplicationDetailsService;
import core.bill.study.service.StudyApplicationService;
import core.bill.study.service.StudyTechnicalService;

@ManagedBean(name = "studyUpdateBean")
@ViewScoped
public class StudyApplicationUpdateBean extends GenericBean<StudyApplicationDTO, StudyApplicationSearch>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(StudyApplicationBean.class);
	
	@ManagedProperty("#{studyTechnicalService}")
	StudyTechnicalService studyTechnicalService;
	
	@ManagedProperty("#{studyApplicationService}")
	StudyApplicationService studyApplicationService;
	
	@ManagedProperty("#{studyApplicationDetailsService}")
	StudyApplicationDetailsService studyApplicationDetailsService;
	
	@ManagedProperty("#{itemService}")
	ItemService itemService;
	
	@ManagedProperty("#{customerService}")
	private CustomerService customerService ;
	private String updateKey;
	
	private CustomerDTO customerDTO = new CustomerDTO();
	
	private StudyApplicationDTO model = new StudyApplicationDTO();
	private List<StudyApplicationDetailsDTO> studyApplicationDetailsDTOs;
	private List<ItemsDTO> itemsList = new ArrayList<ItemsDTO>();
	private List<StudyTechnicalDTO> technicalList= new ArrayList<StudyTechnicalDTO>();
	private List<StudyApplicationDetailsDTO> transList= new ArrayList<StudyApplicationDetailsDTO>();
	List<StudyApplicationDetailsDTO> oldItemList= new ArrayList<StudyApplicationDetailsDTO>();
	
	public String onload() {
		try {

			
			if (updateKey != null) {
				// String decKey = SecureParams.decrypt(updateKey);
				
				Long studyId = Long.valueOf(updateKey);
				model=getStudyApplicationService().getModelDTO(studyId);
								
				studyDate=model.getStudyDate();
				customerDTO=model.getCustomerDTO();
				this.total=model.getItemsTotal().doubleValue();
				this.pervisonPercent=model.getPrevicionesPrecent().doubleValue();
				this.pervisonVarias=model.getPreviousValue().doubleValue();
				this.studChargePercent=model.getManoPrecent().doubleValue();
				this.studCharge=model.getManoValue().doubleValue();
				this.icniPercent=model.getCniPrecent().doubleValue();
				this.icni=model.getCniValue().doubleValue();
				this.discount=model.getDescuentoValue().doubleValue();
				this.accommdateTax=model.getViviendaFee().doubleValue();
				this.commerical=model.getComercialFee().doubleValue();
				this.mediaTension=model.getMediatensionFee().doubleValue();
				this.changeCharge=model.getTrasladoFee().doubleValue();
				this.companyCharge=model.getCambioFee().doubleValue();
				this.totalbudget=model.getFinalTotal().doubleValue();
				
				if(model.getStudyTechnicalDTO()!=null) {
				this.technicalRow=getStudyTechnicalService().getModelDTO(model.getStudyTechnicalDTO().getTechnicalId());
				}
				transList=CustomUtil.convertSetToList(model.getStudyApplicationDetailsDTOs());
				
								
				loadlists();
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Update study Onload :"+e);
		}

		return null;
	}
	
	public String update()
	{
		logger.info("========== inster Customer save ===============");
		try {
			System.out.println("save app :"+customerDTO.getCustomerId());
			
			
			if(model.getStudyId()==null)
			{
				warnMessage(CustomUtil.getBundlMSGValue("se.comm.save.already"));
				return null; 
			}
			if(oldItemList.equals(transList)) {
			System.out.println("oldItemList no change = "+transList.size());
			}
			if(customerDTO.getApplicationDTO().getApplicationId()>0) {
				model.setApplicationId(customerDTO.getApplicationDTO().getApplicationId());
			}
			if(technicalIsChanged) {
			model.setStudyTechnicalDTO(technicalRow);
			}
			model.setCustomerDTO(customerDTO);
			model.setStudyDate(studyDate);
			model.setItemsTotal(new BigDecimal(totParcial));
			model.setPrevicionesPrecent(new BigDecimal(this.pervisonPercent));
			model.setPreviousValue(new BigDecimal(this.pervisonVarias));
			model.setManoPrecent(new BigDecimal(this.studChargePercent));
			model.setManoValue(new BigDecimal(this.studCharge));
			model.setCniPrecent(new BigDecimal(this.icniPercent));
			model.setCniValue(new BigDecimal(this.icni));
			model.setDescuentoValue(new BigDecimal(this.discount));
			model.setViviendaFee(new BigDecimal(this.accommdateTax));
			model.setComercialFee(new BigDecimal(this.commerical));
			model.setMediatensionFee(new BigDecimal(this.mediaTension));
			model.setTrasladoFee(new BigDecimal(this.changeCharge));
			model.setCambioFee(new BigDecimal(this.companyCharge));
			model.setFinalTotal(new BigDecimal(this.totalbudget));
			
			if(this.itemIsChanged) {
				if(oldItemList.size()>0) {
						getStudyApplicationDetailsService().deleteList(oldItemList);
				}
			model.setStudyApplicationDetailsDTOs(CustomUtil.convertListToSet(transList));
			}
			
			
			model = getStudyApplicationService().updateModel(model);
			if(model.getStudyTechnicalDTO()!=null) {
			this.technicalRow=getStudyTechnicalService().getModelDTO(model.getStudyTechnicalDTO().getTechnicalId());
			}
			infoMessage(CustomUtil.getBundlMSGValue("study.save.success"));
			this.itemIsChanged=false;
			oldItemList.clear();
			
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage(CustomUtil.getBundlMSGValue("study.fail.success"));
		}
		
		return null ;
	}
	
	public String cancel() {
		return UtilConstant.FROM_UPDATE_TO_SEARCH_STUDY + "?faces-redirect=true";
	}
	private void loadlists() {

		try {
			CommonSearchDTO search = new CommonSearchDTO() ;
			search.setStatus("ACT");
			itemsList=getItemService().getDataList();
			technicalList=getStudyTechnicalService().getDataList();	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Update Customer loadlists :"+e);
		}
		
	}
	
	public void addRow() {
		boolean notDoubleRow = true;
		if (itemsRow.getItemPrice() > 0 || quantity <= 0.0) {
			StudyApplicationDetailsDTO i = new StudyApplicationDetailsDTO();

			try {
				for (StudyApplicationDetailsDTO o : transList) {
					if (o.getItemDTO().getItemId() == itemsRow.getItemId()) {
						notDoubleRow = false;
						break;
					}
				}

				if (notDoubleRow) {
					
					i.setItemDTO(itemsRow);
					i.setPrice(itemsRow.getItemPrice());
					i.setQuantity(this.quantity);
					this.total += (itemsRow.getItemPrice() * this.quantity);
					i.setHave(false);
					i.setStudyApplicationDTO(model);
					transList.add(i);
					this.quantity = 0.0;
					this.itemIsChanged=true;
					
				} else {

				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

	}
	
	public String delRow(StudyApplicationDetailsDTO item) throws SegesaServiceException
	{
		oldItemList.add(item);
		this.itemIsChanged=true;
		this.total-=(item.getQuantity()*item.getPrice());
		transList.remove(item);
		return null;
	}
	
	public void discountAjax(StudyApplicationDetailsDTO i)
	{
		if(i.getHave())
		{
		this.discount+=(i.getPrice()*i.getQuantity());
		}else 
		{
			this.discount-=(i.getPrice()*i.getQuantity());
		}
		
	}
	
	Boolean technicalIsChanged=false;
	Boolean itemIsChanged=false;
	
	public void changeTechnical() {
		this.technicalIsChanged=!technicalIsChanged;
		
	}
	
	
	private Date studyDate = new Date();
	
	private ItemsDTO itemsRow;
	private StudyTechnicalDTO technicalRow;
	
	private double quantity=0.0 ; 
	private double total;
	
	private double companyCharge = 0.0;
	private double totalbudget = 0.0;
	
	private double totParcial =0.0;
	private double pervisonVarias =0.0;
	private double pervisonPercent =8.5;
	
	private double studCharge = 0.0;
	private double studChargePercent =30;
	
	private double discount =0.0;
	private double totalInFCFA =0.0;
	
	private double icni =0.0; 
	private double icniPercent =15;
	
	private double subTotal = 0.0; 
	private double accommdateTax = 21250; 
	private double commerical =0.0; 
	private double mediaTension =0.0;
	private double changeCharge =0.0;

	public StudyTechnicalService getStudyTechnicalService() {
		return studyTechnicalService;
	}
	public void setStudyTechnicalService(StudyTechnicalService studyTechnicalService) {
		this.studyTechnicalService = studyTechnicalService;
	}
	public StudyApplicationService getStudyApplicationService() {
		return studyApplicationService;
	}
	public void setStudyApplicationService(StudyApplicationService studyApplicationService) {
		this.studyApplicationService = studyApplicationService;
	}
	public StudyApplicationDetailsService getStudyApplicationDetailsService() {
		return studyApplicationDetailsService;
	}
	public void setStudyApplicationDetailsService(StudyApplicationDetailsService studyApplicationDetailsService) {
		this.studyApplicationDetailsService = studyApplicationDetailsService;
	}
	public ItemService getItemService() {
		return itemService;
	}
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	public CustomerService getCustomerService() {
		return customerService;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	public StudyApplicationDTO getModel() {
		return model;
	}
	public void setModel(StudyApplicationDTO model) {
		this.model = model;
	}
	public List<StudyApplicationDetailsDTO> getStudyApplicationDetailsDTOs() {
		return studyApplicationDetailsDTOs;
	}
	public void setStudyApplicationDetailsDTOs(List<StudyApplicationDetailsDTO> studyApplicationDetailsDTOs) {
		this.studyApplicationDetailsDTOs = studyApplicationDetailsDTOs;
	}
	public List<ItemsDTO> getItemsList() {
		return itemsList;
	}
	public void setItemsList(List<ItemsDTO> itemsList) {
		this.itemsList = itemsList;
	}
	public List<StudyTechnicalDTO> getTechnicalList() {
		return technicalList;
	}
	public void setTechnicalList(List<StudyTechnicalDTO> technicalList) {
		this.technicalList = technicalList;
	}
	public List<StudyApplicationDetailsDTO> getTransList() {
		return transList;
	}
	public void setTransList(List<StudyApplicationDetailsDTO> transList) {
		this.transList = transList;
	}

	public String getUpdateKey() {
		return updateKey;
	}

	public void setUpdateKey(String updateKey) {
		this.updateKey = updateKey;
	}
	
	public double getTotParcial() {
		totParcial=this.total;
	return totParcial;
}

public void setTotParcial(double totParcial) {
	this.totParcial = totParcial;
}

public double getPervisonVarias() {
	if(this.total>0)
	{
		this.pervisonVarias=(this.total*this.pervisonPercent)/100;
	}
	return pervisonVarias;
}

public void setPervisonVarias(double pervisonVarias) {
	this.pervisonVarias = pervisonVarias;
}

public double getStudCharge() {
	if(this.total>0)
	{
		this.studCharge=(this.total*this.studChargePercent)/100;
	}
	return studCharge;
}

public void setStudCharge(double studCharge) {
	this.studCharge = studCharge;
}

public double getDiscount() {
	return discount;
}

public void setDiscount(double discount) {
	this.discount = discount;
}

public double getTotalInFCFA() {
	this.totalInFCFA = ((this.totParcial+this.pervisonVarias+this.studCharge)-this.discount);
	return totalInFCFA;
}

public void setTotalInFCFA(double totalInFCFA) {
	this.totalInFCFA = totalInFCFA;
}

public double getIcni() {
	if(this.totalInFCFA>0)
	{
		this.icni =(this.totalInFCFA*icniPercent)/100;
	}
	return icni;
}

public void setIcni(double icni) {
	this.icni = icni;
}

public double getAccommdateTax() {
	return accommdateTax;
}

public void setAccommdateTax(double accommdateTax) {
	this.accommdateTax = accommdateTax;
}

public double getCommerical() {
	return commerical;
}

public void setCommerical(double commerical) {
	this.commerical = commerical;
}

public double getMediaTension() {
	return mediaTension;
}

public void setMediaTension(double mediaTension) {
	this.mediaTension = mediaTension;
}

public double getChangeCharge() {
	return changeCharge;
}

public void setChangeCharge(double changeCharge) {
	this.changeCharge = changeCharge;
}

public double getCompanyCharge() {
	return companyCharge;
}

public void setCompanyCharge(double companyCharge) {
	this.companyCharge = companyCharge;
}

public double getTotalbudget() {
	this.totalbudget=(this.subTotal+accommdateTax);
	return totalbudget;
}

public void setTotalbudget(double totalbudget) {
	this.totalbudget = totalbudget;
}

public double getSubTotal() {
	this.subTotal=(this.totalInFCFA+this.icni);
	return subTotal;
}

public double getPervisonPercent() {
	return pervisonPercent;
}

public double getStudChargePercent() {
	return studChargePercent;
}

public double getIcniPercent() {
	return icniPercent;
}

public void setPervisonPercent(double pervisonPercent) {
	this.pervisonPercent = pervisonPercent;
}

public void setStudChargePercent(double studChargePercent) {
	this.studChargePercent = studChargePercent;
}

public void setIcniPercent(double icniPercent) {
	this.icniPercent = icniPercent;
}

public void setSubTotal(double subTotal) {
	this.subTotal = subTotal;
}


public Date getStudyDate() {
	return studyDate;
}

public void setStudyDate(Date studyDate) {
	this.studyDate = studyDate;
}

public StudyTechnicalDTO getTechnicalRow() {
	return technicalRow;
}

public void setTechnicalRow(StudyTechnicalDTO technicalRow) {
	this.technicalRow = technicalRow;
}

public ItemsDTO getItemsRow() {
	return itemsRow;
}

public void setItemsRow(ItemsDTO itemsRow) {
	this.itemsRow = itemsRow;
}

public double getQuantity() {
	return quantity;
}

public void setQuantity(double quantity) {
	this.quantity = quantity;
}

public double getTotal() {
	return total;
}

public void setTotal(double total) {
	this.total = total;
}
	
	
}
