package core.bill.user.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DualListModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.bill.common.bean.GenericBean;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.util.CustomUtil;

import core.bill.common.util.UtilConstant;
import core.bill.user.model.GroupDTO;
import core.bill.user.model.GroupResourceDTO;
import core.bill.user.model.GroupRoleDTO;
import core.bill.user.model.GroupSearch;
import core.bill.user.model.ResourceDTO;
import core.bill.user.model.RoleDTO;
import core.bill.user.model.UserDTO;
import core.bill.user.service.GroupResourceService;
import core.bill.user.service.GroupRoleService;
import core.bill.user.service.GroupService;
import core.bill.user.service.ResourceService;
import core.bill.user.service.RoleService;

import core.bill.user.service.UserRoleGroupService;


@ManagedBean(name = "groupUpdateBean")
@ViewScoped
public class GroupUpdateBean extends GenericBean<GroupDTO, GroupSearch> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(GroupSearchBean.class);

	@ManagedProperty("#{groupService}")
	GroupService groupService;

	@ManagedProperty("#{roleService}")
	RoleService roleService;

	@ManagedProperty("#{resourceService}")
	ResourceService resourceService;

	@ManagedProperty("#{groupResourceService}")
	GroupResourceService groupResourceService;

	@ManagedProperty("#{groupRoleService}")
	GroupRoleService groupRoleService;

	private String updateKey;
	private GroupDTO model;
	private Long resourceID;
	private List<GroupResourceDTO> groupResourceDTOs;
	private DualListModel<RoleDTO> roles;
	private List<RoleDTO> rolesListSource = new ArrayList<RoleDTO>();
	private List<RoleDTO> rolesList = new ArrayList<RoleDTO>();
	private List<ResourceDTO> resourceList;
	private List<GroupResourceDTO> deleteGroupResourceList;
	private List<GroupRoleDTO> deleteGroupRoleList;
	private List<GroupRoleDTO> deleteGroupRoleListOfUpdateRole;
	private String groupResourseName;

	private List<RoleDTO> updateRoleTarget = new ArrayList<RoleDTO>();
	private DualListModel<RoleDTO> updateRole = new DualListModel<>(rolesListSource, updateRoleTarget);
	private GroupResourceDTO updateGroupResourceDTO;
	
	List<GroupRoleDTO> finalGrpList ;

	private List<UserDTO> userDataModel;
	@ManagedProperty("#{userRoleGroupService}")
	 UserRoleGroupService userRoleGoupService;
	
	private List<UserDTO> userRole;
	
	public String onload() {
		try {
			userRole=null;
			System.out.println("updateKey = " + updateKey);
			if (updateKey != null) {
				// String decKey = SecureParams.decrypt(updateKey);
				// System.out.println("after updateKey decrpt = "+decKey);

				Long groupId = Long.valueOf(updateKey);
				model = getGroupService().getModelDTO(groupId);
				System.out.println("onload model.getGroupResourceDTOs() size :" + model.getGroupResourceDTOs().size());

				groupResourceDTOs = CustomUtil.convertSetToList(model.getGroupResourceDTOs());

				for (GroupResourceDTO obj : groupResourceDTOs) {
					System.out.println("onload GroupResourceDTO :" + obj.getGroupResourceId());
				}
				System.out.println("onload groupResourceDTOs size :" + groupResourceDTOs.size());
				rolesListSource = getRoleService().getDataList();
				roles = new DualListModel<>(rolesListSource, rolesList);
				resourceList = getResourceService().getDataList();
			
			GroupSearch certiria = new GroupSearch();
			certiria.setGroupId(groupId);

			userRole=userRoleGoupService.getUserListProjection(certiria, 0, 100, "userRoleId", Boolean.TRUE);
			
			System.out.println("www"+userRole.size());
					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String addResource() {
		System.out.println("========================Add Resource =======================");
		System.out.println("resourceDTO  from converter : " + resourceID);

		try {

			if (groupResourceDTOs == null) {
				groupResourceDTOs = new ArrayList<GroupResourceDTO>();
			}

			GroupResourceDTO groupResourceDTO = new GroupResourceDTO();
			Set<GroupRoleDTO> groupRoleDTOs = new HashSet<GroupRoleDTO>();

			if (resourceID == null) {
				String[] params = { CustomUtil.getBundlMSGValue("role.resource.name") };
				errorMessage(CustomUtil.getBundlMSGValue("value.required", params));

				return null;
			}
			ResourceDTO resourceDTO = getResourceService().getModelDTO(resourceID);

			for (RoleDTO roleDTO : roles.getTarget()) {
				GroupRoleDTO gr = new GroupRoleDTO();
				gr.setRoleDTO(roleDTO);
				gr.setGroupResourceDTO(groupResourceDTO);
				groupRoleDTOs.add(gr);

			}

			groupResourceDTO.setGroupDTO(model);
			groupResourceDTO.setGroupRoleDTOs(groupRoleDTOs);
			groupResourceDTO.setResourceDTO(resourceDTO);
			groupResourceDTO.setCreatedDate(new Date());
			//groupResourceDTO.setCreatedBy(getUser().getUserId().toString());
			groupResourceDTOs.add(groupResourceDTO);

			System.out.println("groupResourceDTOs inside add resource :" + groupResourceDTOs.size());
			// model.setGroupResourceDTOs(CustomUtil.convertListToSet(groupResourceDTOs));
			rolesListSource = getRoleService().getDataList();
			roles = new DualListModel<>(rolesListSource, rolesList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// =======================================
	public void showResourse(GroupResourceDTO gr) {
		try {

			rolesListSource = getRoleService().getDataList();
			updateRoleTarget.clear();
			setUpdateGroupResourceDTO(gr);
			getUpdateGroupResourceDTO().setGroupRoleDTOs(gr.getGroupRoleDTOs());
			groupResourseName = gr.getResourceDTO().getBundle();
			List<RoleDTO> tempRole = new ArrayList<RoleDTO>();
			deleteGroupRoleList = CustomUtil.convertSetToList(gr.getGroupRoleDTOs());
			
			if (gr.getGroupRoleDTOs() != null) {
				for (GroupRoleDTO group : gr.getGroupRoleDTOs()) {
					System.out.println("Role From Show Resource : " + group.getRoleDTO().getRoleName());
					updateRoleTarget.add(group.getRoleDTO());

					for (RoleDTO res : rolesListSource) {
						if (res.getRoleId() == group.getRoleDTO().getRoleId()) {
							tempRole.add(res);
						}
					}

				}

				rolesListSource.removeAll(tempRole);
			}

			updateRole = new DualListModel<>(rolesListSource, updateRoleTarget);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ===========
	public void updateResource() {
		Set<GroupRoleDTO> setUpdatGroup = new HashSet<GroupRoleDTO>();
	
		for (GroupResourceDTO gr : groupResourceDTOs) {
			if (updateGroupResourceDTO != null
					&& (updateGroupResourceDTO.getResourceDTO().getBundle() == gr.getResourceDTO().getBundle())) {
				deleteGroupRoleListOfUpdateRole= CustomUtil.convertSetToList(gr.getGroupRoleDTOs()) ;
				gr.getGroupRoleDTOs().clear();
				
				for (RoleDTO r : updateRole.getTarget()) {
					//System.out.println("Target Role : " + r.getRoleName());
					GroupRoleDTO temp = new GroupRoleDTO();
					//System.out.println("deleteGroupRoleList :"+deleteGroupRoleList.size());
					for(GroupRoleDTO grRoleDTO :deleteGroupRoleList)
					{
						//System.out.println("matching grRoleDTO.getRoleDTO().getRoleId : " + grRoleDTO.getRoleDTO().getRoleId());
						//System.out.println("matching r.getRoleId(): " + r.getRoleId());
						if(grRoleDTO.getRoleDTO().getRoleId()==r.getRoleId())
						{
							System.out.println("matching getGroupRoleId() : " + grRoleDTO.getGroupRoleId());
							if(grRoleDTO.getGroupRoleId()!=null)
							{
								temp.setGroupRoleId(grRoleDTO.getGroupRoleId());
								
							}
						}
					}
					temp.setRoleDTO(r);
					temp.setGroupResourceDTO(gr);
					setUpdatGroup.add(temp);
				}

				gr.setGroupRoleDTOs(setUpdatGroup);

				//// --------------------checking deleted Role
				if (deleteGroupRoleList != null && deleteGroupRoleList.size() > 0) {
					for (GroupRoleDTO orgGrpRole : deleteGroupRoleList) {
						if (!gr.getGroupRoleDTOs().contains(orgGrpRole) && orgGrpRole.getGroupRoleId() != null) {
							
					
							if (finalGrpList == null)
								finalGrpList = new ArrayList<GroupRoleDTO>();

							finalGrpList.add(orgGrpRole);
						}
					}
				}
			}
			
			
		}
		
		//=================
	}
	// =================================
	public String update() {
		try {

			logger.info("====================- Enter Update role ====================");

			if (getGroupService().valideUpdate(model) != null && getGroupService().valideUpdate(model).size() > 0) {

				errorMessages(getGroupService().valideUpdate(model));
				return null;
			}
			
			
			//GroupSearch search = new GroupSearch();
			//search.setGroupResourceId(updateGroupResourceDTO.getGroupResourceId());
			//deleteGroupRoleListOfUpdateRole =getGroupRoleService().getDataList(search, 0, 0, "groupResourceId", Boolean.TRUE);
			
			//if(deleteGroupRoleListOfUpdateRole!=null && deleteGroupRoleListOfUpdateRole.size()>0)
			//{
			//	getGroupRoleService().deleteModel(deleteGroupRoleListOfUpdateRole);
				//deleteGroupRoleListOfUpdateRole=null ;
			//}
			
			Set<GroupResourceDTO> tempList = new HashSet<GroupResourceDTO>();

			//System.out.println("groupResourceDTOs Size :" + groupResourceDTOs.size());
			for (GroupResourceDTO groupResourceDTO : groupResourceDTOs) {
				tempList.add(groupResourceDTO);
			}
			model.getGroupResourceDTOs().clear();
			model.setGroupResourceDTOs(tempList);
			//model.setUpdatedBy(getUser().getUserId().toString());
			model.setUpdatedDate(new Date());
			getGroupService().updateModel(model);
			
			if(deleteGroupResourceList!=null && deleteGroupResourceList.size()>0)
			{
				getGroupResourceService().deleteModel(deleteGroupResourceList);
				deleteGroupResourceList=null;
			}
			
			if(finalGrpList!=null && finalGrpList.size()>0)
			{
				getGroupRoleService().deleteModel(finalGrpList);
				finalGrpList=null;
			}
			
			infoMessage(CustomUtil.getBundlMSGValue("group.update.success"));
		} catch (SegesaServiceException e) {
			errorMessage(CustomUtil.getBundlMSGValue("group.update.fail"));
			System.out.println(e);

			logger.error(" update role Error :" + e);
		}

		return null;
	}

	// =======================
	public void removeResource(GroupResourceDTO grpResDTO) {
		if (grpResDTO != null) {
			groupResourceDTOs.remove(grpResDTO);
			model.getGroupResourceDTOs().remove(grpResDTO);
			if(grpResDTO.getGroupResourceId()!=null)
			{
				if(deleteGroupResourceList==null)
				{
					deleteGroupResourceList = new ArrayList<GroupResourceDTO>();
				}
				deleteGroupResourceList.add(grpResDTO);
			}
			
		}
	}

	public List<UserDTO> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserDTO> userRole) {
		this.userRole = userRole;
	}

	// ========================
	public String cancel() {
		return UtilConstant.FROM_UPDATE_TO_SEARCH_GROUP + "?faces-redirect=true";
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public String getUpdateKey() {
		return updateKey;
	}

	public void setUpdateKey(String updateKey) {
		this.updateKey = updateKey;
	}

	public GroupDTO getModel() {
		return model;
	}

	public void setModel(GroupDTO model) {
		this.model = model;
	}

	public Long getResourceID() {
		return resourceID;
	}

	public void setResourceID(Long resourceID) {
		this.resourceID = resourceID;
	}

	public List<GroupResourceDTO> getGroupResourceDTOs() {
		return groupResourceDTOs;
	}

	public void setGroupResourceDTOs(List<GroupResourceDTO> groupResourceDTOs) {
		this.groupResourceDTOs = groupResourceDTOs;
	}

	public DualListModel<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(DualListModel<RoleDTO> roles) {
		this.roles = roles;
	}

	public List<RoleDTO> getRolesListSource() {
		return rolesListSource;
	}

	public void setRolesListSource(List<RoleDTO> rolesListSource) {
		this.rolesListSource = rolesListSource;
	}

	public List<RoleDTO> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<RoleDTO> rolesList) {
		this.rolesList = rolesList;
	}

	public List<ResourceDTO> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<ResourceDTO> resourceList) {
		this.resourceList = resourceList;
	}

	public String getGroupResourseName() {
		return groupResourseName;
	}

	public void setGroupResourseName(String groupResourseName) {
		this.groupResourseName = groupResourseName;
	}

	public DualListModel<RoleDTO> getUpdateRole() {
		return updateRole;
	}

	public void setUpdateRole(DualListModel<RoleDTO> updateRole) {
		this.updateRole = updateRole;
	}

	public List<RoleDTO> getUpdateRoleTarget() {
		return updateRoleTarget;
	}

	public void setUpdateRoleTarget(List<RoleDTO> updateRoleTarget) {
		this.updateRoleTarget = updateRoleTarget;
	}

	public GroupResourceDTO getUpdateGroupResourceDTO() {
		return updateGroupResourceDTO;
	}

	public void setUpdateGroupResourceDTO(GroupResourceDTO updateGroupResourceDTO) {
		this.updateGroupResourceDTO = updateGroupResourceDTO;
	}

	public GroupResourceService getGroupResourceService() {
		return groupResourceService;
	}

	public void setGroupResourceService(GroupResourceService groupResourceService) {
		this.groupResourceService = groupResourceService;
	}

	public GroupRoleService getGroupRoleService() {
		return groupRoleService;
	}

	public void setGroupRoleService(GroupRoleService groupRoleService) {
		this.groupRoleService = groupRoleService;
	}

	public List<GroupResourceDTO> getDeleteGroupResourceList() {
		return deleteGroupResourceList;
	}

	public void setDeleteGroupResourceList(List<GroupResourceDTO> deleteGroupResourceList) {
		this.deleteGroupResourceList = deleteGroupResourceList;
	}

	public List<GroupRoleDTO> getDeleteGroupRoleList() {
		return deleteGroupRoleList;
	}

	public void setDeleteGroupRoleList(List<GroupRoleDTO> deleteGroupRoleList) {
		this.deleteGroupRoleList = deleteGroupRoleList;
	}

	public List<GroupRoleDTO> getFinalGrpList() {
		return finalGrpList;
	}

	public UserRoleGroupService getUserRoleGoupService() {
		return userRoleGoupService;
	}

	public void setUserRoleGoupService(UserRoleGroupService userRoleGoupService) {
		this.userRoleGoupService = userRoleGoupService;
	}

	public void setFinalGrpList(List<GroupRoleDTO> finalGrpList) {
		this.finalGrpList = finalGrpList;
	}

	public List<GroupRoleDTO> getDeleteGroupRoleListOfUpdateRole() {
		return deleteGroupRoleListOfUpdateRole;
	}

	public void setDeleteGroupRoleListOfUpdateRole(List<GroupRoleDTO> deleteGroupRoleListOfUpdateRole) {
		this.deleteGroupRoleListOfUpdateRole = deleteGroupRoleListOfUpdateRole;
	}

	public List<UserDTO> getUserDataModel() {
		return userDataModel;
	}

	public void setUserDataModel(List<UserDTO> userDataModel) {
		this.userDataModel = userDataModel;
	}



}
