package core.bill.comercial.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.bill.comercial.model.ApplicationDTO;
import core.bill.comercial.model.CustomerDTO;
import core.bill.comercial.model.CustomerSearch;
import core.bill.comercial.service.CustomerService;
import core.bill.common.bean.GenericBean;
import core.bill.common.util.CustomUtil;
import core.bill.common.util.UtilConstant;
import core.bill.setting.model.ApplicationPurposeTypeDTO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.HouseholdsDTO;
import core.bill.setting.model.IdentificationTypeDTO;
import core.bill.setting.model.LocalityDTO;
import core.bill.setting.model.NationalityDTO;
import core.bill.setting.serviceProvider.SettingServiceProvider;

@ManagedBean(name = "customerBean")
@ViewScoped
public class CustomerBean extends GenericBean<CustomerDTO, CustomerSearch> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger log = LoggerFactory.getLogger(CustomerBean.class);

	@ManagedProperty("#{settingServiceProvider}")
	private SettingServiceProvider settingServiceProvider;

	@ManagedProperty("#{customerService}")
	private CustomerService customerService;

	private CustomerDTO model;

	private List<ApplicationPurposeTypeDTO> appPurposeList;
	private List<LocalityDTO> localityList;
	private List<NationalityDTO> nationalityList;
	private List<IdentificationTypeDTO> identificationList;
	private List<HouseholdsDTO> householdsList;

	private ApplicationPurposeTypeDTO applicationPurposeTypeDto;
	private LocalityDTO localityDto;
	private NationalityDTO nationalityDto;
	private IdentificationTypeDTO identificationTypeDto;
	private HouseholdsDTO householdsDto;
	private String contractType;

	@PostConstruct
	private void init() {
		model = new CustomerDTO();
		model.setApplicationDTO(new ApplicationDTO());
		loadlists();
	}

	public String save() {
		log.info("========== inster Customer save ===============");
		try {

			if (model.getCustomerId() != null) {
				warnMessage(CustomUtil.getBundlMSGValue("se.comm.save.already"));
				return null;
			}

			Long max = getCustomerService().getMax("customerId");
			if (max != null) {
				model.setCustomerCode(localityDto.getLocalityCode() + (max + 1));
			}
			model.setLocalityDTO(localityDto);
			model.getApplicationDTO().setLocalityDTO(localityDto);
			model.getApplicationDTO().setCustomerName(model.getCustomerName());
			model.getApplicationDTO().setApplicationPurposeTypeDTO(applicationPurposeTypeDto);
			model.getApplicationDTO().setHouseholdsDTO(householdsDto);
			model.setIdentificationTypeDTO(identificationTypeDto);
			model.setNationalityDTO(nationalityDto);
			model.setStatus(UtilConstant.ACTIVE);
			model.getApplicationDTO().setStatus(UtilConstant.ACTIVE);

			model = getCustomerService().saveModel(model);
			infoMessage(CustomUtil.getBundlMSGValue("se.comm.save.success"));
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage(CustomUtil.getBundlMSGValue("se.comm.save.fail"));
		}

		return null;
	}

	public String reset() {
		return null;
	}

	private void loadlists() {

		try {
			CommonSearchDTO search = new CommonSearchDTO();
			search.setStatus("ACT");
			appPurposeList = getSettingServiceProvider().getApplicationPurposeTypeDTOList(search);
			localityList = getSettingServiceProvider().getLocalityDTOList(search);
			nationalityList = getSettingServiceProvider().getNationalityDTOList(search);
			identificationList = getSettingServiceProvider().getIdentificationTypeDTOList(search);
			householdsList = getSettingServiceProvider().getHouseholdsDTOList(search);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public SettingServiceProvider getSettingServiceProvider() {
		return settingServiceProvider;
	}

	public void setSettingServiceProvider(SettingServiceProvider settingServiceProvider) {
		this.settingServiceProvider = settingServiceProvider;
	}

	public CustomerDTO getModel() {
		return model;
	}

	public void setModel(CustomerDTO model) {
		this.model = model;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
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

	public ApplicationPurposeTypeDTO getApplicationPurposeTypeDto() {
		return applicationPurposeTypeDto;
	}

	public void setApplicationPurposeTypeDto(ApplicationPurposeTypeDTO applicationPurposeTypeDto) {
		this.applicationPurposeTypeDto = applicationPurposeTypeDto;
	}

	public LocalityDTO getLocalityDto() {
		return localityDto;
	}

	public void setLocalityDto(LocalityDTO localityDto) {
		this.localityDto = localityDto;
	}

	public NationalityDTO getNationalityDto() {
		return nationalityDto;
	}

	public void setNationalityDto(NationalityDTO nationalityDto) {
		this.nationalityDto = nationalityDto;
	}

	public IdentificationTypeDTO getIdentificationTypeDto() {
		return identificationTypeDto;
	}

	public void setIdentificationTypeDto(IdentificationTypeDTO identificationTypeDto) {
		this.identificationTypeDto = identificationTypeDto;
	}

	public HouseholdsDTO getHouseholdsDto() {
		return householdsDto;
	}

	public void setHouseholdsDto(HouseholdsDTO householdsDto) {
		this.householdsDto = householdsDto;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

}
