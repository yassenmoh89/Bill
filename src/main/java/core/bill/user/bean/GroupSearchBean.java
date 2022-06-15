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
import core.bill.user.model.GroupDTO;
import core.bill.user.model.GroupSearch;
import core.bill.user.service.GroupService;
import core.bill.user.service.ResourceService;
import core.bill.user.service.RoleService;

@ManagedBean(name="groupSearchBean")
@ViewScoped
public class GroupSearchBean extends GenericBean<GroupDTO, GroupSearch>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(GroupSearchBean.class);
	
	@ManagedProperty("#{groupService}")
	GroupService groupService ;
	
	@ManagedProperty("#{roleService}")
	RoleService  roleService ;
	
	@ManagedProperty("#{resourceService}")
	ResourceService resourceService ;
	
	private GroupSearch certeria = new GroupSearch();
	private LazyDataModel<GroupDTO> groupLazyDataModel;
	
	
	// =============================== delete Role ================
		public String search() {
			try {
				logger.info("====================- Enter Search role ====================");

				if (getGroupService().valideSearch(certeria) != null && getGroupService().valideSearch(certeria).size() > 0) {
					errorMessages(getGroupService().valideSearch(certeria));
					return null;
				}

				setDataModel(new SearchPagination<GroupDTO, GroupSearch>(getGroupService(), certeria, "groupId", Boolean.TRUE));
				Long totalRows = getGroupService().getRowCount(getCerteria()) ;
				if (totalRows != null) {
					getDataModel().setRowCount(totalRows.intValue());
				} else {
					getDataModel().setRowCount(0);
				}
				setGroupLazyDataModel(getDataModel());
				System.out.println("setGroupLazyDataModel size:"+getGroupLazyDataModel().getPageSize());

			} catch (SegesaServiceException e) {

				System.out.println(e);

				logger.error(" update role Error :" + e);
			}

			return null;
		}

		// =============================================================================
		public String goToGroupUpdate(String groupId) {
			String paramValue="" ;
			System.out.println("Group ID = "+groupId);
			//paramValue= SecureParams.encrypt(groupId);
			paramValue=groupId; 
			return UtilConstant.FROM_SEARCH_TO_UPDATE_GROUP + "?faces-redirect=true&updateKey=" + paramValue;
		}

		public void reset() {
			groupLazyDataModel = null;
			certeria = new GroupSearch();
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
	public GroupSearch getCerteria() {
		return certeria;
	}
	public void setCerteria(GroupSearch certeria) {
		this.certeria = certeria;
	}
	public LazyDataModel<GroupDTO> getGroupLazyDataModel() {
		return groupLazyDataModel;
	}
	public void setGroupLazyDataModel(LazyDataModel<GroupDTO> groupLazyDataModel) {
		this.groupLazyDataModel = groupLazyDataModel;
	}
	
	

}
