package core.bill.user.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.bill.common.bean.GenericBean;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.util.CustomUtil;
import core.bill.user.model.GroupDTO;
import core.bill.user.model.GroupResourceDTO;
import core.bill.user.model.GroupRoleDTO;
import core.bill.user.model.GroupSearch;
import core.bill.user.model.ResourceDTO;
import core.bill.user.model.RoleDTO;
import core.bill.user.service.GroupService;
import core.bill.user.service.ResourceService;
import core.bill.user.service.RoleService;

@ManagedBean(name = "groupBean")
@ViewScoped
public class GroupBean extends GenericBean<GroupDTO, GroupSearch>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(GroupBean.class);
	
	@ManagedProperty("#{groupService}")
	GroupService groupService ;
	
	@ManagedProperty("#{roleService}")
	RoleService  roleService ;
	
	@ManagedProperty("#{resourceService}")
	ResourceService resourceService ;
	
	private Boolean showPanel=false ;
	private List<ResourceDTO> resourceList ;
	private List<RoleDTO> rolesListSource = new ArrayList<RoleDTO>();
	private List<RoleDTO> rolesList = new ArrayList<RoleDTO>();
	private GroupDTO model = new GroupDTO();
	private DualListModel<RoleDTO> roles;
	private Long resourceID ;
	private List<GroupResourceDTO> groupResourceDTOs ;
	
	public String addResource() {
		System.out.println("========================Add Resource =======================");
		
		try {
			
			if(resourceID==null)
			{
				String[] params= {CustomUtil.getBundlMSGValue("role.resource.name")};
				errorMessage(CustomUtil.getBundlMSGValue("value.required", params));
			
				return null ;
			}
			
			if(groupResourceDTOs==null)
			{
				groupResourceDTOs = new ArrayList<GroupResourceDTO>();
			}
			
			GroupResourceDTO groupResourceDTO = new GroupResourceDTO();
			Set<GroupRoleDTO>  groupRoleDTOs = new HashSet<GroupRoleDTO>();
			ResourceDTO resourceDTO = getResourceService().getModelDTO(resourceID);
			groupResourceDTO.setGroupDTO(model);
			
			for(RoleDTO roleDTO: roles.getTarget())
			{
				System.out.println("====roles== :"+roleDTO.getRoleName());
				GroupRoleDTO gr = new GroupRoleDTO();
				gr.setRoleDTO(roleDTO);
				gr.setGroupResourceDTO(groupResourceDTO);
				gr.setCreatedDate(new Date());
				groupRoleDTOs.add(gr);
				
			}
			
			
			System.out.println("====groupRoleDTOs size == :"+groupRoleDTOs.size());
			groupResourceDTO.setGroupRoleDTOs(groupRoleDTOs);
			groupResourceDTO.setResourceDTO(resourceDTO);
			groupResourceDTOs.add(groupResourceDTO);
			model.setGroupResourceDTOs(CustomUtil.convertListToSet(groupResourceDTOs));
			rolesListSource = getRoleService().getDataList();
			roles = new DualListModel<>(rolesListSource, rolesList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null ;
	}
	
	//=======================
	public void removeResource(GroupResourceDTO grpResDTO)
	{
		if(grpResDTO !=null)
		{
			groupResourceDTOs.remove(grpResDTO);
			model.getGroupResourceDTOs().remove(grpResDTO);
		}
	}
	
	@PostConstruct
	public void init()
	{
		try {
			resourceList = getResourceService().getDataList();
			rolesListSource = getRoleService().getDataList();
			roles = new DualListModel<>(rolesListSource, rolesList);
			} catch (SegesaServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	public String save()
	{
		try {
			
			if (getGroupService().valideSave(model) != null && getGroupService().valideSave(model).size() > 0) {
				errorMessages(getGroupService().valideSave(model));
				return null;
			}

			if (model.getGroupId() != null) {
				warnMessage(CustomUtil.getBundlMSGValue("group.save.already"));
				return null;
			}
			//model.setCreatedBy(getUser().getUserId().toString());
			model.setCreatedDate(new Date());
			model =getGroupService().saveModel(model);
			infoMessage(CustomUtil.getBundlMSGValue("group.save.sucess"));
		} catch (Exception e) {
			errorMessage(CustomUtil.getBundlMSGValue("group.save.fail"));
			e.printStackTrace();
			logger.error(" Save role :" + e);
		}
		return null ;
	}
	//-------------------------------
	public String reset()
	{
		try {
			rolesListSource = getRoleService().getDataList();
				roles = new DualListModel<>(rolesListSource, rolesList);
			 model = new GroupDTO();
				resourceID =null ;
				 groupResourceDTOs =null ;
		} catch (SegesaServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
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


	public Boolean getShowPanel() {
		return showPanel;
	}


	public void setShowPanel(Boolean showPanel) {
		this.showPanel = showPanel;
	}


	public List<ResourceDTO> getResourceList() {
		return resourceList;
	}


	public void setResourceList(List<ResourceDTO> resourceList) {
		this.resourceList = resourceList;
	}


	public List<RoleDTO> getRolesList() {
		return rolesList;
	}


	public void setRolesList(List<RoleDTO> rolesList) {
		this.rolesList = rolesList;
	}


	public GroupDTO getModel() {
		return model;
	}


	public void setModel(GroupDTO model) {
		this.model = model;
	}


	public List<RoleDTO> getRolesListSource() {
		return rolesListSource;
	}


	public void setRolesListSource(List<RoleDTO> rolesListSource) {
		this.rolesListSource = rolesListSource;
	}


	public DualListModel<RoleDTO> getRoles() {
		return roles;
	}


	public void setRoles(DualListModel<RoleDTO> roles) {
		this.roles = roles;
	}

	

	public List<GroupResourceDTO> getGroupResourceDTOs() {
		return groupResourceDTOs;
	}

	public Long getResourceID() {
		return resourceID;
	}

	public void setResourceID(Long resourceID) {
		this.resourceID = resourceID;
	}

	public void setGroupResourceDTOs(List<GroupResourceDTO> groupResourceDTOs) {
		this.groupResourceDTOs = groupResourceDTOs;
	}


}
