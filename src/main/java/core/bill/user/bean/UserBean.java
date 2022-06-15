package core.bill.user.bean;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.bill.common.bean.GenericBean;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.security.PasswordEncoder;
import core.bill.common.util.CustomUtil;
import core.bill.user.model.GroupDTO;
import core.bill.user.model.GroupSearch;
import core.bill.user.model.UserDTO;
import core.bill.user.model.UserProfileDTO;
import core.bill.user.model.UserRoleDTO;
import core.bill.user.model.UserSearch;
import core.bill.user.service.GroupService;
import core.bill.user.service.UserService;

@ManagedBean(name="userBean")
@ViewScoped
public class UserBean extends GenericBean<UserDTO, UserSearch>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UserBean.class);
	
	@ManagedProperty("#{userService}")
	 UserService userService ;
	
	@ManagedProperty("#{groupService}")
	GroupService groupService ;
	
	private UserDTO model ;
	
	private String currentPassword;
	private String newPassword;
	private String confirmPassword;
	
	
	@PostConstruct
	public void  init()
	{
		model= new UserDTO() ;
		model.setUserProfileDTO(new UserProfileDTO());
	}
	public String save()
	{
		try {
				
			logger.info("=============  inside user save =========== ");
		
			
			if(getUserService().valideSave(model)!=null && getUserService().valideSave(model).size()>0)
			{
				errorMessages(getUserService().valideSave(model));
				return null ;
			}
			
			if(model.getUserId() !=null )
			{
				warnMessage(CustomUtil.getBundlMSGValue("user.save.already"));
				return null ; 
			}
			
			if (PasswordEncoder.checkPassword(model.getPassword(), PasswordEncoder.BCryptEncoder(model.getPassword())))
				model.setPassword(PasswordEncoder.BCryptEncoder(model.getPassword()));
				GroupSearch crit = new GroupSearch();
				crit.setGroupCode("USR");
				
				Set<GroupDTO> groupSet = CustomUtil.convertListToSet(getGroupService().getDataList(crit, 0, 0, "groupId", true)) ;
				Set<UserRoleDTO> userRoleDTOs = new HashSet<UserRoleDTO>();
				if(groupSet!=null)
				{
					for(GroupDTO grp:groupSet)
					{
						UserRoleDTO userRoleDTO = new UserRoleDTO();
						userRoleDTO.setGroupDTO(grp);
						userRoleDTO.setUserDTO(model);
						userRoleDTOs.add(userRoleDTO);
						
					}
					model.setUserRoleDTO(userRoleDTOs);
				}
				model.setUserType(1);
				model.setCreatedBy(getUser().getUserId().toString());
				model.setCreatedDate(new Date());
				model.setNeedPassChange(true);
				model.setLoginAttempt(0);
				model = getUserService().saveModel(model);
				infoMessage(CustomUtil.getBundlMSGValue("user.save.success"));
				
		} catch (SegesaServiceException e) {
			errorMessage(CustomUtil.getBundlMSGValue("user.save.fail"));
			
			logger.error("error save user "+e);
			e.printStackTrace();
		}
		
		return null; 
	}

	public String onload() {
		try {
			if (getUser().getUserId().toString() != null) {
					Long groupId = Long.valueOf(getUser().getUserId().toString());
					model = getUserService().getModelDTO(groupId);
					System.out.println("model : "+model.getUserName());

				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	public void changePassword() throws SegesaServiceException {

		if (PasswordEncoder.checkPassword(currentPassword, model.getPassword()))
		 {
			if(newPassword.equals(confirmPassword)) {
				model.setPassword(PasswordEncoder.BCryptEncoder(newPassword));
				model.setNeedPassChange(Boolean.FALSE);
				getUserService().updateModel(model);
				infoMessage(CustomUtil.getBundlMSGValue("login.change.password.success"));
				
			}else {
				warnMessage(CustomUtil.getBundlMSGValue("login.newpassword.not.match.confirmpassword"));
			}
			
		}else {
			warnMessage(CustomUtil.getBundlMSGValue("login.current.password.not.currect"));
		}
		
	}
	
	public String cancel()
	{
		return "/faces/bill/project/home.xhtml" + "?faces-redirect=true";
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public UserDTO getModel() {
		return model;
	}

	public void setModel(UserDTO model) {
		this.model = model;
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


}
