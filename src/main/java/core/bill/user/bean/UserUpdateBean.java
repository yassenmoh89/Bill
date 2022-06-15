package core.bill.user.bean;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import core.bill.common.bean.GenericBean;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.util.CustomUtil;
import core.bill.common.util.UtilConstant;
import core.bill.user.model.GroupDTO;
import core.bill.user.model.UserDTO;
import core.bill.user.model.UserRoleDTO;
import core.bill.user.model.UserSearch;
import core.bill.user.service.GroupService;
import core.bill.user.service.UserService;

@ManagedBean(name="userUpdateBean")
@ViewScoped

public class UserUpdateBean extends GenericBean<UserDTO, UserSearch>{

	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UserUpdateBean.class);
	@ManagedProperty("#{userService}")
	UserService userService ; 
	
	@ManagedProperty("#{groupService}")
	GroupService groupService ; 
	
	private UserDTO model ; 
	private String updateKey ;
	
	private List<GroupDTO> groupListSource = new ArrayList<GroupDTO>();
	private List<GroupDTO> groupListTarget = new ArrayList<GroupDTO>();
	private Set<GroupDTO> groupSet = new HashSet<GroupDTO>();
	private Set<UserRoleDTO> userRoleDTO ;
	private DualListModel<GroupDTO> groupPicList;
	
	private List<Long> deletedUserRoleIds = new ArrayList<>();
	
	public String onload() {
		try {
			logger.info("----------------- Enter onload() method ----------------");
			System.out.println("updateKey = "+updateKey);
			if (updateKey != null) {
			
				groupListTarget.clear();
				groupListSource.clear();
				
					Long userId = Long.valueOf(updateKey);
					model = getUserService().getModelDTO(userId);
					groupListSource = getGroupService().getDataList();
					
				
					groupListTarget = loadTraget(model.getUserRoleDTO());
					groupListSource.removeAll(groupListTarget);
					groupPicList = new DualListModel<>(groupListSource,groupListTarget) ;
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	private List<GroupDTO> loadTraget(Set<UserRoleDTO> userRoleDTO2) {
		List<GroupDTO> list =new ArrayList<GroupDTO>(); 
		for(UserRoleDTO usrRoleDTO:userRoleDTO2)
		{
			if(list==null) {list= new ArrayList<GroupDTO>();}
			list.add(usrRoleDTO.getGroupDTO());
		}
		return list;
	}
	//---------------------------------------
	public void onTransfer(TransferEvent event) {
		
		
	}
	public String update()
	{
		try {
			
			logger.info("====================- Enter Update role ====================");

			if (getUserService().valideUpdate(model) != null && getUserService().valideUpdate(model).size()>0) {
			
				errorMessages(getUserService().valideUpdate(model));
				return null;
			}
			//model.setGroupResourceDTOs(CustomUtil.convertListToSet(groupResourceDTOs));
			System.out.println("groupSet size in update :"+groupSet.size());
			
			userRoleDTO = loadUserRoleSet(groupPicList,model.getUserRoleDTO(),model);
			loadDeletedRoles() ;
			model.getUserRoleDTO().clear();
			model.setUserRoleDTO(getUserRoleDTO());
			model.setUpdatedBy(getUser().getUserId().toString());
			model.setUpdatedDate(new Date());
			
			getUserService().updateModel(model);
			
			getUserService().deleteUserRole(getDeletedUserRoleIds());
			
			infoMessage(CustomUtil.getBundlMSGValue("user.update.success"));
		} catch (SegesaServiceException e) {
			errorMessage(CustomUtil.getBundlMSGValue("user.update.fail"));
			System.out.println(e);

			logger.error(" update role Error :" + e);
		}

		return null;
	}
	//----------------------------------------------
	private void loadDeletedRoles() {
		deletedUserRoleIds.clear();
		
		System.out.println("model.getUserRoleDTO() :"+model.getUserRoleDTO());
		if(model.getUserRoleDTO() != null)
		{
			for(UserRoleDTO srcRole : CustomUtil.convertSetToList(model.getUserRoleDTO()))
			{
				System.out.println("############################");
				for(GroupDTO group :groupPicList.getSource())
				{
					System.out.println(" Source Name : "+group.getGroupName());
					if(group.getGroupId()==(srcRole.getGroupDTO().getGroupId()))
					{
						deletedUserRoleIds.add(srcRole.getUserRoleId());
					}
				}
			}
		}
		
	/*	if(deletedUserRoleIds.size()>0)
		{
			Set<UserRoleDTO> usrRoleDTOsDelSet = new HashSet<UserRoleDTO>() ;
			for(Long deletedUserRoleId:deletedUserRoleIds)
			{
				for(UserRoleDTO tempRoleDTO: userRoleDTO)
				{
					if(tempRoleDTO.getUserRoleId()==deletedUserRoleId)
					{
						usrRoleDTOsDelSet.add(tempRoleDTO);
					}
				}
			}
			userRoleDTO.removeAll(usrRoleDTOsDelSet);
		}*/
		System.out.println("deleted size : "+ deletedUserRoleIds.size());
	}
	//------------------------------------------
	private Set<UserRoleDTO> loadUserRoleSet(DualListModel<GroupDTO> groupPicList2, Set<UserRoleDTO> userRoleDTO2,UserDTO uerDTO) {
		Set<UserRoleDTO> usrRoleDTOsSet = new HashSet<UserRoleDTO>() ;
		 if(groupPicList2 !=null && userRoleDTO2!=null && groupPicList2.getTarget()!=null)
		 {
			 for(GroupDTO group:groupPicList2.getTarget())
			 {
				 UserRoleDTO userRoleDTO = new UserRoleDTO();
				 userRoleDTO.setGroupDTO(group);
				 userRoleDTO.setUserDTO(uerDTO);
				 for(UserRoleDTO usrRoleObj:userRoleDTO2)
				 {
					 if(usrRoleObj.getGroupDTO().getGroupId()== group.getGroupId())
					 {
						 userRoleDTO.setUserRoleId(usrRoleObj.getUserRoleId());
						 
					 }
				 }
				 
				 usrRoleDTOsSet.add(userRoleDTO);
				 
			 }
		 }

		return usrRoleDTOsSet;
	}
	public String cancel()
	{
		return UtilConstant.FROM_UPDATE_TO_SEARCH_USER + "?faces-redirect=true";
	}
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public UserDTO getModel() {
		return model;
	}
	public void setModel(UserDTO model) {
		this.model = model;
	}
	public String getUpdateKey() {
		return updateKey;
	}
	public void setUpdateKey(String updateKey) {
		this.updateKey = updateKey;
	}

	public List<GroupDTO> getGroupListSource() {
		return groupListSource;
	}

	public void setGroupListSource(List<GroupDTO> groupListSource) {
		this.groupListSource = groupListSource;
	}

	public List<GroupDTO> getGroupListTarget() {
		return groupListTarget;
	}

	public void setGroupListTarget(List<GroupDTO> groupListTarget) {
		this.groupListTarget = groupListTarget;
	}

	public DualListModel<GroupDTO> getGroupPicList() {
		return groupPicList;
	}

	public void setGroupPicList(DualListModel<GroupDTO> groupPicList) {
		this.groupPicList = groupPicList;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	public Set<GroupDTO> getGroupSet() {
		return groupSet;
	}
	public void setGroupSet(Set<GroupDTO> groupSet) {
		this.groupSet = groupSet;
	}
	public Set<UserRoleDTO> getUserRoleDTO() {
		return userRoleDTO;
	}
	public void setUserRoleDTO(Set<UserRoleDTO> userRoleDTO) {
		this.userRoleDTO = userRoleDTO;
	}
	
	
	public List<Long> getDeletedUserRoleIds() {
		return deletedUserRoleIds;
	}
	public void setDeletedUserRoleIds(List<Long> deletedUserRoleIds) {
		this.deletedUserRoleIds = deletedUserRoleIds;
	}
	
	
	
	

}
