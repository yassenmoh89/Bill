package core.bill.study.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import core.bill.comercial.bean.CustomerSearchBean;
import core.bill.comercial.model.CustomerDTO;
import core.bill.comercial.service.CustomerService;
import core.bill.common.bean.GenericBean;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.util.SearchPagination;
import core.bill.common.util.UtilConstant;
import core.bill.study.model.StudyApplicationDTO;
import core.bill.study.model.StudyApplicationSearch;
import core.bill.study.service.StudyApplicationService;

@ManagedBean(name = "studySearch")
@ViewScoped
public class StudyApplicationSearchBean  extends GenericBean<StudyApplicationDTO, StudyApplicationSearch>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Logger logger = LoggerFactory.getLogger(CustomerSearchBean.class);
	

	@ManagedProperty("#{studyApplicationService}")
	StudyApplicationService studyApplicationService;
	
	@ManagedProperty("#{customerService}")
	private CustomerService customerService ;
	
	private StudyApplicationSearch criteria ;
	
	private CustomerDTO customerDTO;
	
	private LazyDataModel<StudyApplicationDTO> studyAppLazyDataModel;
		
	@PostConstruct
	private void init()
	{
		customerDTO= new CustomerDTO();
		criteria = new StudyApplicationSearch();
	}

	public String search()
	{
		try {
			logger.info("====================- Enter Search role ====================");

			if (getStudyApplicationService().valideSearch(criteria) != null && getStudyApplicationService().valideSearch(criteria).size() > 0) {
				errorMessages(getStudyApplicationService().valideSearch(criteria));
				return null;
			}
			
			if(criteria.getCustomerId()!=null ) {
			setCustomerDTO(getCustomerService().getModelDTO(criteria.getCustomerId()));
			}
			
			setDataModel(new SearchPagination<StudyApplicationDTO, StudyApplicationSearch>(getStudyApplicationService(), criteria, "studyId", Boolean.FALSE));
			
			Long totalRows = getStudyApplicationService().getRowCount(getCriteria()) ;
			if (totalRows != null) {
				getDataModel().setRowCount(totalRows.intValue());
			} else {
				getDataModel().setRowCount(0);
			}
			setStudyAppLazyDataModel(getDataModel());

		} catch (SegesaServiceException e) {

			System.out.println(e);

			logger.error(" update role Error :" + e);
		}
		
		return null ;

	}

	public String goToStudyAppUpdate(String studyId) {
		String paramValue="" ;
		System.out.println("Study search ID = "+studyId);
		//paramValue= SecureParams.encrypt(groupId);
		paramValue=studyId; 
		return UtilConstant.FROM_SEARCH_TO_UPDATE_STUDY + "?faces-redirect=true&updateKey=" + paramValue;
	}

	public LazyDataModel<StudyApplicationDTO> getStudyAppLazyDataModel() {
		return studyAppLazyDataModel;
	}

	public void setStudyAppLazyDataModel(LazyDataModel<StudyApplicationDTO> studyAppLazyDataModel) {
		this.studyAppLazyDataModel = studyAppLazyDataModel;
	}

	public StudyApplicationService getStudyApplicationService() {
		return studyApplicationService;
	}

	public void setStudyApplicationService(StudyApplicationService studyApplicationService) {
		this.studyApplicationService = studyApplicationService;
	}

	public StudyApplicationSearch getCriteria() {
		return criteria;
	}

	public void setCriteria(StudyApplicationSearch criteria) {
		this.criteria = criteria;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}

	public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}


}
