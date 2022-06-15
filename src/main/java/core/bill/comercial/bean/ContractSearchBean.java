package core.bill.comercial.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import core.bill.comercial.model.ContractDTO;
import core.bill.comercial.model.ContractSearch;
import core.bill.comercial.service.ContractService;
import core.bill.common.bean.GenericBean;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.util.SearchPagination;
import core.bill.common.util.UtilConstant;

@ManagedBean(name="contractSearchBean")
@ViewScoped
public class ContractSearchBean extends GenericBean<ContractDTO, ContractSearch>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(ContractSearchBean.class);
	
	@ManagedProperty("#{contractService}")
	private ContractService contractService ; 
	
	private ContractSearch criteria;
	private LazyDataModel<ContractDTO> contractLazyDataModel;
	
	@PostConstruct
	private void init()
	{
		criteria = new ContractSearch();
		
	}
	
	public String search()
	{
		try {
			logger.info("====================- Enter Search role ====================");

			if (getContractService().valideSearch(criteria) != null && getContractService().valideSearch(criteria).size() > 0) {
				errorMessages(getContractService().valideSearch(criteria));
				return null;
			}

			setDataModel(new SearchPagination<ContractDTO, ContractSearch>(getContractService(), criteria, "contractId", Boolean.TRUE));
			
			Long totalRows = getContractService().getRowCount(getCriteria()) ;
			if (totalRows != null) {
				getDataModel().setRowCount(totalRows.intValue());
			} else {
				getDataModel().setRowCount(0);
			}
			setContractLazyDataModel(getDataModel());

		} catch (SegesaServiceException e) {

			System.out.println(e);

			logger.error(" update role Error :" + e);
		}
		
		return null ;
	}
	
	//---------------------------------reset
	public String reset()
	{
		
		criteria= new ContractSearch();
		contractLazyDataModel= null;
		
		return null ; 
	}
	//-----------------------------------------

	public String goToContactUpdate(String contractId) {
		String paramValue="" ;
		System.out.println("Group ID = "+contractId);
		//paramValue= SecureParams.encrypt(groupId);
		paramValue=contractId; 
		return UtilConstant.FROM_SEARCH_TO_UPDATE_CONTRACT + "?faces-redirect=true&updateKey=" + paramValue;
	}
	
	//----------------------------------------------------------
	public String goToApprovalContact(String contractId) {
		String paramValue="" ;
		System.out.println("Group ID = "+contractId);
		//paramValue= SecureParams.encrypt(groupId);
		paramValue=contractId; 
		return "/bill/segesa/commerical/approveContract.xhtml" + "?faces-redirect=true&updateKey=" + paramValue;
	}
	//-----------------------------------------------------------

	public ContractSearch getCriteria() {
		return criteria;
	}

	public void setCriteria(ContractSearch criteria) {
		this.criteria = criteria;
	}

	public LazyDataModel<ContractDTO> getContractLazyDataModel() {
		return contractLazyDataModel;
	}

	public void setContractLazyDataModel(LazyDataModel<ContractDTO> contractLazyDataModel) {
		this.contractLazyDataModel = contractLazyDataModel;
	}

	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

}
