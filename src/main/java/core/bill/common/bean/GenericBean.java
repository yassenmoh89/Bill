package core.bill.common.bean;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import core.bill.common.util.SearchPagination;
import core.bill.user.model.UserDTO;
import core.bill.user.service.UserSessionServiceImpl;

public class GenericBean<T,R> extends GenericRole  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SearchPagination<T, R>  dataModel ;
	private UserDTO user = new UserDTO();
	
	FacesContext context = null;
	HttpServletRequest request = null;
	HttpSession session = null;
	
	private String remoteHost ; 
	private String remoteAddress ; 
	
	@Autowired
	UserSessionServiceImpl userSessionServiceImpl;
	
	
	public GenericBean()
	{
		context = FacesContext.getCurrentInstance();
		request = (HttpServletRequest) context.getExternalContext().getRequest();
		
		remoteHost = request.getRemoteHost();
		remoteAddress = request.getRemoteAddr();
		
		session = request.getSession(false);
		if (session != null) {
			user =(UserDTO) session.getAttribute("usr");
		}
		//session = request.getSession();
	}

	
	
	public SearchPagination<T, R> getDataModel() {
		return dataModel;
	}

	public void setDataModel(SearchPagination<T, R> dataModel) {
		this.dataModel = dataModel;
	}

	
	public UserDTO getUser() {
		
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}
	
	
	
	
	

}
