package core.bill.comercial.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import core.bill.comercial.model.CustomerDTO;
import core.bill.comercial.model.CustomerSearch;
import core.bill.comercial.service.CustomerService;
import core.bill.common.bean.GenericBean;
import core.bill.common.util.UtilConstant;
import core.bill.setting.model.ApplicationPurposeTypeDTO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.HouseholdsDTO;
import core.bill.setting.model.IdentificationTypeDTO;
import core.bill.setting.model.LocalityDTO;
import core.bill.setting.model.NationalityDTO;
import core.bill.setting.serviceProvider.SettingServiceProvider;
import core.bill.user.bean.GroupSearchBean;

@ManagedBean(name = "customerUpdateBean")
@ViewScoped
public class CustomerUpdateBean extends GenericBean<CustomerDTO, CustomerSearch>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(GroupSearchBean.class);
	
	@ManagedProperty("#{settingServiceProvider}")
	private SettingServiceProvider  settingServiceProvider ;
	
	@ManagedProperty("#{customerService}")
	private CustomerService customerService ;
	
	private String updateKey;
	
	private List<ApplicationPurposeTypeDTO> appPurposeList ;
	private List<LocalityDTO>  localityList ;
	private List<NationalityDTO>  nationalityList;
	private List<IdentificationTypeDTO> identificationList ; 
	private List<HouseholdsDTO> householdsList ;
	private CustomerDTO model ;
	private String contractType;
	
	public String onload() {
		try {

			System.out.println("updateKey = " + updateKey);
			if (updateKey != null) {
				// String decKey = SecureParams.decrypt(updateKey);
				// System.out.println("after updateKey decrpt = "+decKey);
				Long customerId = Long.valueOf(updateKey);
				model = getCustomerService().getModelDTO(customerId);
				loadlists();
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Update Customer Onload :"+e);
		}

		return null;
	}
	
	public String update()
	{
	
		return null;
	}
	
	public String cancel() {
		return UtilConstant.FROM_UPDATE_TO_SEARCH_CUSTOMER + "?faces-redirect=true";
	}
	private void loadlists() {

		try {
			CommonSearchDTO search = new CommonSearchDTO() ;
			search.setStatus("ACT");
			appPurposeList = getSettingServiceProvider().getApplicationPurposeTypeDTOList(search);
			localityList  = getSettingServiceProvider().getLocalityDTOList(search);
			nationalityList  = getSettingServiceProvider().getNationalityDTOList(search);
			identificationList  = getSettingServiceProvider().getIdentificationTypeDTOList(search);
			householdsList  = getSettingServiceProvider().getHouseholdsDTOList(search);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Update Customer loadlists :"+e);
		}
		
	}

	public SettingServiceProvider getSettingServiceProvider() {
		return settingServiceProvider;
	}
	public void setSettingServiceProvider(SettingServiceProvider settingServiceProvider) {
		this.settingServiceProvider = settingServiceProvider;
	}
	public CustomerService getCustomerService() {
		return customerService;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	public String getUpdateKey() {
		return updateKey;
	}
	public void setUpdateKey(String updateKey) {
		this.updateKey = updateKey;
	}
	public List<ApplicationPurposeTypeDTO> getAppPurposeList() {
		return appPurposeList;
	}
	public void setAppPurposeList(List<ApplicationPurposeTypeDTO> appPurposeList) {
		this.appPurposeList = appPurposeList;
	}
	public List<LocalityDTO> getLocalityList() {
		return localityList;
	}
	public void setLocalityList(List<LocalityDTO> localityList) {
		this.localityList = localityList;
	}
	public List<NationalityDTO> getNationalityList() {
		return nationalityList;
	}
	public void setNationalityList(List<NationalityDTO> nationalityList) {
		this.nationalityList = nationalityList;
	}
	public List<IdentificationTypeDTO> getIdentificationList() {
		return identificationList;
	}
	public void setIdentificationList(List<IdentificationTypeDTO> identificationList) {
		this.identificationList = identificationList;
	}
	public List<HouseholdsDTO> getHouseholdsList() {
		return householdsList;
	}
	public void setHouseholdsList(List<HouseholdsDTO> householdsList) {
		this.householdsList = householdsList;
	}
	public CustomerDTO getModel() {
		return model;
	}
	public void setModel(CustomerDTO model) {
		this.model = model;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	} 
	
	

}
