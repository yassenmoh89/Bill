package core.bill.user.bean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import core.bill.common.bean.GenericBean;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.security.PasswordEncoder;
import core.bill.common.util.CustomUtil;
import core.bill.user.model.UserDTO;
import core.bill.user.model.UserSearch;
import core.bill.user.model.UserSessionDTO;
import core.bill.user.service.UserService;
import core.bill.user.service.UserSessionService;

@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean extends GenericBean<UserDTO, UserSearch> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger logger = LoggerFactory.getLogger(LoginBean.class);

	@ManagedProperty("#{userService}")
	private UserService userService;
	
	@ManagedProperty("#{userSessionService}")
	private UserSessionService userSessionService;
	
	
	private List<UserDTO> userList;
	private Long userId;
	private String password;
	private Boolean needChangePwd=false;
	private String currentPassword;
	private String newPassword;
	private String confirmPassword;
	

	FacesContext context = null;
	HttpServletRequest request = null;
	HttpSession session = null;
	UserDTO userDto;
	UserSessionDTO userSessionDTO;

	@PostConstruct
	public void init() {
		try {
			userList = userService.getDataListProjection(null, 0, 0, "userName", Boolean.TRUE);
			userDto= new UserDTO();
			userSessionDTO= new UserSessionDTO();
		} catch (SegesaServiceException e) {
			logger.error("LoginBean :" + e);
			e.printStackTrace();
		}
	}

	// -------------------------------------------------
	public String doLogin() {
		try {

			if (userId == null || (password == null || password.equals(""))) {			
				errorMessage(CustomUtil.getBundlMSGValue("login.fail.wrong.password"));
				return null;
			}

			userDto = userService.getModelDTO(userId);

			if (userDto == null) {
				errorMessage(CustomUtil.getBundlMSGValue("login.fail"));
				return null;
			}

			if (userDto.getNeedPassChange()) {
				this.needChangePwd=true;
				warnMessage(CustomUtil.getBundlMSGValue("login.need.change.pwd"));
			}

			if ((!userDto.getActive()) || (userDto.getLoginAttempt() > 3)) {
				warnMessage(CustomUtil.getBundlMSGValue("login.attempt"));
				return null;
			}

			if(needChangePwd==false && userDto.getLoginAttempt()<=3)
			{
				if (PasswordEncoder.checkPassword(password, userDto.getPassword())) {
					context = FacesContext.getCurrentInstance();
					request = (HttpServletRequest) context.getExternalContext().getRequest();
					session = request.getSession(false);
					if (session != null) {
						session.invalidate();
					}
					session = request.getSession();
					session.setAttribute("usr", userDto);
	
					if (userDto.getLoginAttempt() > 1) {
						userDto.setLoginAttempt(0);
						userService.updateModel(userDto);
					}
					saveUserSession("loging");	
					return "/faces/bill/project/home.xhtml?faces-redirect=true";
	
				} else {
					int attemp = userDto.getLoginAttempt();
					if (attemp == 3) {
						userDto.setActive(Boolean.FALSE);
					}
					userDto.setLoginAttempt(attemp + 1);
					userService.updateModel(userDto);
					errorMessage(CustomUtil.getBundlMSGValue("login.fail")+"yassin");
					saveUserSession("Try login 3 attempts");
					return null;
				}
			}

		} catch (Exception e) {
			logger.error("doLogin :" + e);
			e.printStackTrace();
		}
		return null;
	}

	public String logout() {
		context = FacesContext.getCurrentInstance();
		request = (HttpServletRequest) context.getExternalContext().getRequest();
		session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		return "/faces/login.xhtml?faces-redirect=true";
	}
	
	public String changePassword() throws SegesaServiceException {
		
		System.out.println("WE are"+PasswordEncoder.checkPassword(currentPassword, userDto.getPassword()));
		
		try {	
		
		if (PasswordEncoder.checkPassword(currentPassword, userDto.getPassword()))
		 {
			System.out.println("WE are currentPassword");
			if(newPassword.equals(confirmPassword)) {
				userDto.setPassword(PasswordEncoder.BCryptEncoder(newPassword));
				userDto.setNeedPassChange(Boolean.FALSE);
				getUserService().updateModel(userDto);
				
				System.out.println("WE are currentPassword");
				
				needChangePwd=false;
				infoMessage(CustomUtil.getBundlMSGValue("login.change.password.success"));
				
				context = FacesContext.getCurrentInstance();
				request = (HttpServletRequest) context.getExternalContext().getRequest();
				session = request.getSession(false);
				if (session != null) {
					session.invalidate();
				}
				session = request.getSession();
				session.setAttribute("usr", userDto);

				if (userDto.getLoginAttempt() > 1) {
					userDto.setLoginAttempt(0);
					userService.updateModel(userDto);
				}
				saveUserSession("change password and Login");
				
				return "/faces/bill/project/home.xhtml?faces-redirect=true";
				
			}else {
				warnMessage(CustomUtil.getBundlMSGValue("login.newpassword.not.match.confirmpassword"));
			}
			
		}else {
			warnMessage(CustomUtil.getBundlMSGValue("login.current.password.not.currect"));
			return null;
		}
		}catch (Exception e) {
			logger.error("do Change Password and Login :" + e);
			e.printStackTrace();
		}
		return null;
	}

	void saveUserSession(String formName) {
		try {
		userSessionDTO.setHostName(getRemoteHost());
		userSessionDTO.setIpAddress(getRemoteAddress());
		userSessionDTO.setUserName(userDto.getUserName());
		userSessionDTO.setSessionDate(new Date());
		userSessionDTO.setFormName(formName);
		getUserSessionService().saveModel(userSessionDTO);
		}catch (Exception e) {
			logger.error("saveUserSession and Login :" + e);
			e.printStackTrace();
		}
	}
	public UserService getUserService() {
		return userService;
	}

	public List<UserDTO> getUserList() {
		return userList;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setUserList(List<UserDTO> userList) {
		this.userList = userList;
	}

	public Long getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getNeedChangePwd() {
		return needChangePwd;
	}

	public void setNeedChangePwd(Boolean needChangePwd) {
		this.needChangePwd = needChangePwd;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public UserSessionService getUserSessionService() {
		return userSessionService;
	}

	public void setUserSessionService(UserSessionService userSessionService) {
		this.userSessionService = userSessionService;
	}



}
