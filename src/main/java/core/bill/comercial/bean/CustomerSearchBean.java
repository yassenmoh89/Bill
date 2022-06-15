package core.bill.comercial.bean;


import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.bill.comercial.model.CustomerDTO;
import core.bill.comercial.model.CustomerSearch;
import core.bill.comercial.service.CustomerService;
import core.bill.common.bean.GenericBean;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.util.SearchPagination;
import core.bill.common.util.UtilConstant;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.LocalityDTO;
import core.bill.setting.serviceProvider.SettingServiceProvider;


@ManagedBean(name = "customerSearchBean")
@ViewScoped
public class CustomerSearchBean extends GenericBean<CustomerDTO, CustomerSearch>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(CustomerSearchBean.class);
	
	
	@ManagedProperty("#{settingServiceProvider}")
	private SettingServiceProvider  settingServiceProvider ;
	
	@ManagedProperty("#{customerService}")
	private CustomerService customerService ;
	
	
	private CustomerSearch criteria ;
	private List<LocalityDTO>  localityList ;
	private LazyDataModel<CustomerDTO> customerLazyDataModel;
	private CustomerDTO customerDTO;
	

	@PostConstruct
	private void init()
	{
		criteria = new CustomerSearch();
		loadlists();
	}
	
	
	public String search()
	{
		try {
			System.out.println("search customer");
			logger.info("====================- Enter Search role ====================");

			if (getCustomerService().valideSearch(criteria) != null && getCustomerService().valideSearch(criteria).size() > 0) {
				errorMessages(getCustomerService().valideSearch(criteria));
				return null;
			}

			setDataModel(new SearchPagination<CustomerDTO, CustomerSearch>(getCustomerService(), criteria, "customerName", Boolean.FALSE));
			
			Long totalRows = getCustomerService().getRowCount(getCriteria()) ;
			if (totalRows != null) {
				getDataModel().setRowCount(totalRows.intValue());
			} else {
				getDataModel().setRowCount(0);
			}
			setCustomerLazyDataModel(getDataModel());

		} catch (SegesaServiceException e) {

			System.out.println(e);

			logger.error(" update role Error :" + e);
		}
		
		return null ;

	}
	
	public String reset()
	{
		criteria= new CustomerSearch();
		customerLazyDataModel= null;
		
		return null ; 
	}
	
	String paramValue="" ;
	public String goToCustomerUpdate() {	
		System.out.println("Group ID = "+paramValue);
		//paramValue= SecureParams.encrypt(groupId);
		return UtilConstant.FROM_SEARCH_TO_UPDATE_CUSTOMER + "?faces-redirect=true&updateKey=" + paramValue;
	}
	
	public String getCustomerInfo(String customerId) {
		
		System.out.println("Group ID = "+customerId);
		//paramValue= SecureParams.encrypt(groupId);
		paramValue=customerId; 
		try {
			customerDTO=getCustomerService().getModelDTO(Long.valueOf(paramValue));
			System.out.println("customerDTO :"+customerDTO.getApplicationDTO().getAddress());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	public String goToCreateNewContract() {

		return "/bill/segesa/commerical/createContract.xhtml" + "?faces-redirect=true&updateKey=" + paramValue;
	}

	public String goToOtherPayment() {
	
		//paramValue= SecureParams.encrypt(groupId);
		return "/bill/segesa/invoice/otherPayment.xhtml" + "?faces-redirect=true&updateKey=" + paramValue;
	}
	
	private void loadlists() {

		try {
			CommonSearchDTO search = new CommonSearchDTO() ;
			search.setStatus("ACT");
			localityList  = getSettingServiceProvider().getLocalityDTOList(search);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public CustomerSearch getCriteria() {
		return criteria;
	}

	public void setCriteria(CustomerSearch criteria) {
		this.criteria = criteria;
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

	public List<LocalityDTO> getLocalityList() {
		return localityList;
	}

	public void setLocalityList(List<LocalityDTO> localityList) {
		this.localityList = localityList;
	}


	public LazyDataModel<CustomerDTO> getCustomerLazyDataModel() {
		return customerLazyDataModel;
	}


	public void setCustomerLazyDataModel(LazyDataModel<CustomerDTO> customerLazyDataModel) {
		this.customerLazyDataModel = customerLazyDataModel;
	}


	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}


	public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}

	
}
