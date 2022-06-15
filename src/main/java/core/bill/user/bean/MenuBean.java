package core.bill.user.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import core.bill.common.bean.GenericBean;
import core.bill.common.util.CustomUtil;
import core.bill.user.model.GroupResourceDTO;
import core.bill.user.model.ResourceModel;
import core.bill.user.model.UserDTO;
import core.bill.user.model.UserRoleDTO;
import core.bill.user.model.UserSearch;

@ManagedBean(name = "menuBean")
@SessionScoped
public class MenuBean extends GenericBean<UserDTO, UserSearch> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(MenuBean.class);
	FacesContext context = null;
	private MenuModel model;
	private List<ResourceModel> resourceList;
	private String userName = "tata";
	private String appName;

	@PostConstruct
	public void init() {
		try {
			Set<String> url = new HashSet<String>();
			appName = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			System.out.println("Context Name :" + appName);

			if (getUser() != null && getUser().getUserRoleDTO() != null) {
				this.userName = getUser().getUserName();
				System.err.println("user Name" + userName);
				List<UserRoleDTO> groupList = CustomUtil.convertSetToList(getUser().getUserRoleDTO());

				for (UserRoleDTO roleDto : groupList) {
					if (roleDto.getGroupDTO() != null && roleDto.getGroupDTO().getGroupResourceDTOs() != null) {
						List<GroupResourceDTO> groupResourceDTOList = CustomUtil
								.convertSetToList(roleDto.getGroupDTO().getGroupResourceDTOs());
						for (GroupResourceDTO groupResourceDto : groupResourceDTOList) {
							if (groupResourceDto != null && groupResourceDto.getResourceDTO() != null
									&& groupResourceDto.getResourceDTO().getAppear()) {
								if (!url.contains(groupResourceDto.getResourceDTO().getUrl())) {

									if (resourceList == null) {
										resourceList = new ArrayList<ResourceModel>();
									}

									ResourceModel resourceModel = new ResourceModel();
									resourceModel.setResourceId(groupResourceDto.getResourceDTO().getRoleResourceId());
									resourceModel.setResourceName(groupResourceDto.getResourceDTO().getBundle());
									resourceModel.setUrl(appName + groupResourceDto.getResourceDTO().getUrl());
									resourceModel.setEnglishName(groupResourceDto.getResourceDTO().getResourceName());
									resourceModel.setCategory(groupResourceDto.getResourceDTO().getCategory());
									resourceModel.setSubCategory(groupResourceDto.getResourceDTO().getSubCategory());

									resourceList.add(resourceModel);
									url.add(groupResourceDto.getResourceDTO().getUrl());
								}
							}

						}

					}

				}

			}

			populateMenu(resourceList);

		} catch (Exception e) {
			logger.error("MenuBean :" + e);
			e.printStackTrace();
		}
	}

	private void populateMenu(List<ResourceModel> resList) {

		model = new DefaultMenuModel();
		MenuModel modelTemp = new DefaultMenuModel();
		if (resList != null) {

			DefaultSubMenu submenu = new DefaultSubMenu();
			submenu.setLabel("");

			for (ResourceModel resourceModel : resList) {
				// ----- Profile
				if (resourceModel.getCategory() != null && resourceModel.getCategory().equals("PROFILE")) {
					
					modelTemp= populateSubMenu(modelTemp ,CustomUtil.getBundlMSGValue("menu.profile.title") ,resourceModel ,"10");
				}

				// ----- STUDY
				if (resourceModel.getCategory() != null && resourceModel.getCategory().equals("STUDY")) {

					modelTemp= populateSubMenu(modelTemp ,CustomUtil.getBundlMSGValue("menu.studyProject.title") ,resourceModel ,"20");
				}

				// ----- COMMERCIAL
				if (resourceModel.getCategory() != null && resourceModel.getCategory().equals("COMMERCIAL")) {

					modelTemp= populateSubMenu(modelTemp ,CustomUtil.getBundlMSGValue("menu.commercial.title") ,resourceModel ,"30");
				}

				// ----- INVOICE
				if (resourceModel.getCategory() != null && resourceModel.getCategory().equals("INVOICE")) {
					
					modelTemp= populateSubMenu(modelTemp ,CustomUtil.getBundlMSGValue("menu.invoice.title") ,resourceModel ,"40");
			
				}

				// ----- CASHIER
				if (resourceModel.getCategory() != null && resourceModel.getCategory().equals("CASHIER")) {

					modelTemp= populateSubMenu(modelTemp ,CustomUtil.getBundlMSGValue("menu.cashier.title") ,resourceModel ,"50");
				}

				// ----- INSPECTOR
				if (resourceModel.getCategory() != null && resourceModel.getCategory().equals("INSPECTOR")) {
					
					modelTemp= populateSubMenu(modelTemp ,CustomUtil.getBundlMSGValue("menu.inspect.title") ,resourceModel ,"60");
					
				}

				// ----- SETTING
				if (resourceModel.getCategory() != null && resourceModel.getCategory().equals("SETTING")) {
					
					modelTemp= populateSubMenu(modelTemp ,CustomUtil.getBundlMSGValue("menu.setting.title") ,resourceModel ,"70");

				}

				// ----- REPORT
				if (resourceModel.getCategory() != null && resourceModel.getCategory().equals("REPORT")) {
					
					modelTemp= populateSubMenu(modelTemp ,CustomUtil.getBundlMSGValue("menu.report.title") ,resourceModel ,"80");
				}

				// ----- User
				if (resourceModel.getCategory() != null && resourceModel.getCategory().equals("USER")) {
					modelTemp= populateSubMenu(modelTemp ,CustomUtil.getBundlMSGValue("menu.user.level.ofAccess") ,resourceModel ,"90");
				}

					
			}

			// -------------------
			
			doOrderMenu(modelTemp);

		}

	}

	private void doOrderMenu(MenuModel modelTemp) {
		getOrderMenu(modelTemp, CustomUtil.getBundlMSGValue("menu.user.level.ofAccess"));

		getOrderMenu(modelTemp, CustomUtil.getBundlMSGValue("menu.report.title"));

		getOrderMenu(modelTemp, CustomUtil.getBundlMSGValue("menu.setting.title"));

		getOrderMenu(modelTemp, CustomUtil.getBundlMSGValue("menu.inspect.title"));

		getOrderMenu(modelTemp, CustomUtil.getBundlMSGValue("menu.cashier.title"));

		getOrderMenu(modelTemp, CustomUtil.getBundlMSGValue("menu.invoice.title"));

		getOrderMenu(modelTemp, CustomUtil.getBundlMSGValue("menu.commercial.title"));

		getOrderMenu(modelTemp, CustomUtil.getBundlMSGValue("menu.studyProject.title"));

		getOrderMenu(modelTemp, CustomUtil.getBundlMSGValue("menu.profile.title"));
		
	}

	// ----------------------
	private MenuElement getOrderMenu(MenuModel modelTemp, String menuLable) {
		DefaultSubMenu menuOreder = new DefaultSubMenu();

		Set<String> uniqMenu = new HashSet<String>();
		if (modelTemp != null && menuLable != null)
			for (MenuElement mnu : modelTemp.getElements()) {
				DefaultSubMenu x = (DefaultSubMenu) mnu;

				if (x.getLabel().equals(menuLable) && !uniqMenu.contains(menuLable)) {
					uniqMenu.add(menuLable);
					menuOreder = (DefaultSubMenu) mnu;
					model.getElements().add(menuOreder);
					return menuOreder;
				}
			}
		return menuOreder;
	}
	//-----------------------------------------------------
	private MenuModel populateSubMenu(MenuModel modelTemp , String label, ResourceModel resourceModel, String id)
	{
		
		boolean exsit = false;
		if (modelTemp.getElements() != null) {
			for (MenuElement mnu : modelTemp.getElements()) {
				DefaultSubMenu x = (DefaultSubMenu) mnu;

				if (x.getLabel().equals(label)) {
					System.out.println("DefaultSubMenu :" + x.getLabel());
					exsit = true;
					DefaultMenuItem item = new DefaultMenuItem();
					item.setValue(CustomUtil.getBundlMSGValue(resourceModel.getResourceName()));
					// item.setValue(resourceModel.getResourceName());
					item.setUrl(resourceModel.getUrl());
					x.setId(id);
					x.getElements().add(item);
					mnu = x;
				
				}

			}
			
		}

		if (!exsit || modelTemp.getElements() == null) {
			DefaultSubMenu submenu = new DefaultSubMenu();
			submenu.setLabel(label);
			DefaultMenuItem item = new DefaultMenuItem();
			item.setValue(CustomUtil.getBundlMSGValue(resourceModel.getResourceName()));
			// item.setValue(resourceModel.getResourceName());
			item.setUrl(resourceModel.getUrl());
			submenu.setId(id);
			submenu.getElements().add(item);
			modelTemp.getElements().add(submenu);
		}
		
		return modelTemp ;

	}
	//--------------------------------------------------

	public String callhome() {
		return "/bill/project/home.xhtml" + "?faces-redirect=true";
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public List<ResourceModel> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<ResourceModel> resourceList) {
		this.resourceList = resourceList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

}
