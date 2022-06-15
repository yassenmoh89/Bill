package core.bill.user.bean;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import core.bill.common.bean.GenericBean;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.security.SecureParams;
import core.bill.common.util.CustomUtil;
import core.bill.common.util.SearchPagination;
import core.bill.common.util.UtilConstant;
import core.bill.user.model.RoleDTO;
import core.bill.user.model.RoleSearch;
import core.bill.user.service.RoleService;

@ManagedBean(name = "roleBean")
@ViewScoped
public class RoleBean extends GenericBean<RoleDTO, RoleSearch> {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(RoleBean.class);

	@ManagedProperty("#{roleService}")
	RoleService roleService;

	private RoleDTO model = new RoleDTO();
	private RoleSearch certeria = new RoleSearch();
	private LazyDataModel<RoleDTO> roleLazyDataModel;
	private String updateKey = null;
	private RoleDTO modelUpdate ; 
	// ==============================Save ====================================

	public String onload() {
		try {
			System.out.println("updateKey = "+updateKey);
			if (updateKey != null) {
				String decKey = SecureParams.decrypt(updateKey);
				System.out.println("after updateKey  decrpt = "+decKey);
				if (decKey != null) {
					Long roleId = Long.valueOf(decKey);
					modelUpdate = getRoleService().getModelDTO(roleId);
				}
			}else
			{
				errorMessage(CustomUtil.getBundlMSGValue("se.common.invalid.data.form"));
				return UtilConstant.FROM_UPDATE_TO_SEARCH_ROLE + "?faces-redirect=true" ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public RoleBean() {

	}

	public String saveRole() {

		try {
			logger.info("====================- Enter Save role ====================");

			if (roleService.valideSave(model) != null && roleService.valideSave(model).size() > 0) {
				errorMessages(roleService.valideSave(model));
				return null;
			}

			if (model.getRoleId() != null) {
				warnMessage(CustomUtil.getBundlMSGValue("role.save.already.save"));
				return null;
			}

			model.setCreatedBy(getUser().getUserCode());
			model.setCreatedDate(new Date());
			model.setStatus(UtilConstant.ACTIVE);

			model = roleService.saveModel(model);
			infoMessage(CustomUtil.getBundlMSGValue("role.save.success"));

		} catch (SegesaServiceException e) {
			errorMessage(CustomUtil.getBundlMSGValue("role.save.fail"));
			System.out.println(e);
			logger.error(" Save role :" + e);

		}

		return null;
	}

	// =============================== update Role ================
	public String updateRole() {
		try {
		
			logger.info("====================- Enter Update role ====================");

			if (roleService.valideUpdate(modelUpdate) != null && roleService.valideUpdate(modelUpdate).size()>0) {
			
				errorMessages(roleService.valideUpdate(modelUpdate));
				return null;
			}
			modelUpdate.setUpdatedBy(getUser().getUserCode());
			modelUpdate.setUpdatedDate(new Date());
			roleService.updateModel(modelUpdate);
			infoMessage(CustomUtil.getBundlMSGValue("role.update.sucess"));
		} catch (SegesaServiceException e) {
			errorMessage(CustomUtil.getBundlMSGValue("role.update.fail"));
			System.out.println(e);

			logger.error(" update role Error :" + e);
		}

		return null;
	}

	// =============================== delete Role ================
	public String disableRole() {
		try {
			logger.info("====================- Enter disable role ====================");

			if (roleService.valideUpdate(model) != null) {
				errorMessages(null);
				return null;
			}
			roleService.updateModel(model);
			infoMessage(null);
		} catch (SegesaServiceException e) {

			System.out.println(e);

			logger.error(" update role Error :" + e);
		}

		return null;
	}

	// =============================== delete Role ================
	public String search() {
		try {
			logger.info("====================- Enter Search role ====================");

			if (roleService.valideSearch(certeria) != null && roleService.valideSearch(certeria).size() > 0) {
				errorMessages(roleService.valideSearch(certeria));
				return null;
			}

			setDataModel(new SearchPagination<RoleDTO, RoleSearch>(roleService, certeria, "roleId", Boolean.TRUE));

			Long totalRows = getRoleService().getRowCount(getCerteria()) ;
			if (totalRows != null) {
				getDataModel().setRowCount(totalRows.intValue());
			} else {
				getDataModel().setRowCount(0);
			}
			setRoleLazyDataModel(getDataModel());

		} catch (SegesaServiceException e) {

			System.out.println(e);

			logger.error(" update role Error :" + e);
		}

		return null;
	}

	// =============================================================================
	public String goToRoleUpdate(String roleId) {
		System.out.println("Role ID = "+roleId);
		String encryp = SecureParams.encrypt(roleId);
		System.out.println("genterated time updateKey = "+encryp);
		return UtilConstant.FROM_SEARCH_TO_UPDATE_ROLE + "?faces-redirect=true&updateKey=" + encryp;
	}

	public void reset() {
		model = new RoleDTO();
		roleLazyDataModel = null;
		certeria = new RoleSearch();
	}
	
	public String cancel()
	{
		return UtilConstant.FROM_UPDATE_TO_SEARCH_ROLE + "?faces-redirect=true";
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public RoleDTO getModel() {
		return model;
	}

	public void setModel(RoleDTO model) {
		this.model = model;
	}

	public RoleSearch getCerteria() {
		return certeria;
	}

	public void setCerteria(RoleSearch certeria) {
		this.certeria = certeria;
	}

	public LazyDataModel<RoleDTO> getRoleLazyDataModel() {
		return roleLazyDataModel;
	}

	public void setRoleLazyDataModel(LazyDataModel<RoleDTO> roleLazyDataModel) {
		this.roleLazyDataModel = roleLazyDataModel;
	}

	public String getUpdateKey() {

		return updateKey;
	}

	public void setUpdateKey(String updateKey) {
		this.updateKey = updateKey;
	}

	public RoleDTO getModelUpdate() {
		return modelUpdate;
	}

	public void setModelUpdate(RoleDTO modelUpdate) {
		this.modelUpdate = modelUpdate;
	}

}
