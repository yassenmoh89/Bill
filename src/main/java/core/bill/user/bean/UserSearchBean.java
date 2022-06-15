package core.bill.user.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.bill.common.bean.GenericBean;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.util.SearchPagination;
import core.bill.common.util.UtilConstant;
import core.bill.user.model.UserDTO;
import core.bill.user.model.UserSearch;
import core.bill.user.service.UserService;

@ManagedBean(name="userSearchBean")
@ViewScoped
public class UserSearchBean extends GenericBean<UserDTO, UserSearch> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserSearch certiria = new UserSearch();
	private LazyDataModel<UserDTO> userDataModel;
	private static final Logger logger = LoggerFactory.getLogger(RoleBean.class);
	
	@ManagedProperty("#{userService}")
	 UserService userService ;
	
	public String search()
	{
		try {
			logger.info("====================- Enter Search role ====================");

			if (getUserService().valideSearch(certiria) != null && getUserService().valideSearch(certiria).size() > 0) {
				errorMessages(getUserService().valideSearch(certiria));
				return null;
			}

			setDataModel(new SearchPagination<UserDTO, UserSearch>(getUserService(), certiria, "userId", Boolean.TRUE));

			Long totalRows = getUserService().getRowCount(getCertiria()) ;
			if (totalRows != null) {
				getDataModel().setRowCount(totalRows.intValue());
			} else {
				getDataModel().setRowCount(0);
			}
			setUserDataModel(getDataModel());

		} catch (SegesaServiceException e) {

			System.out.println(e);

			logger.error(" update role Error :" + e);
		}

		return null;
	}


	public String goToUserUpdate(String userId) {
		String paramValue="" ;
		System.out.println("Group ID = "+userId);
		//paramValue= SecureParams.encrypt(groupId);
		paramValue=userId; 
		return UtilConstant.FROM_SEARCH_TO_UPDATE_USER + "?faces-redirect=true&updateKey=" + paramValue;
	}
	
	public String reset()
	{
		userDataModel= null; 
		certiria = new UserSearch();
		return null ;
	}
	
	public UserSearch getCertiria() {
		return certiria;
	}


	public void setCertiria(UserSearch certiria) {
		this.certiria = certiria;
	}


	
	public LazyDataModel<UserDTO> getUserDataModel() {
		return userDataModel;
	}





	public void setUserDataModel(LazyDataModel<UserDTO> userDataModel) {
		this.userDataModel = userDataModel;
	}





	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	

}
