package core.bill.user.bean;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import core.bill.common.bean.GenericBean;
import core.bill.common.bean.GenericInterface;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.util.CustomUtil;
import core.bill.common.util.SearchPagination;
import core.bill.user.model.SystemConfig;
import core.bill.user.model.SystemConfigSearch;
import core.bill.user.service.SystemConfigService;

@ManagedBean(name="systemConfigBean")
@ViewScoped
public class SystemConfigBean extends GenericBean<SystemConfig, SystemConfigSearch> implements  GenericInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SystemConfigBean.class);
	
	@ManagedProperty("#{systemConfigService}")
	SystemConfigService systemConfigService ;
	
	private SystemConfig model = new SystemConfig(); 
	private SystemConfigSearch certeria = new SystemConfigSearch();
	private LazyDataModel<SystemConfig> configLazyDataModel;
	private String updateKey = null;
	private SystemConfig modelUpdate ; 
	
	public void onload() {
		try {
			if (updateKey != null) {
					Long id = Long.valueOf(updateKey);
					modelUpdate = getSystemConfigService().getModelDTO(id);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public String update() {
		try {
			
			logger.info("====================- Enter Update role ====================");

			modelUpdate.setUpdatedBy(getUser().getUserCode());
			modelUpdate.setUpdatedDate(new Date());
			getSystemConfigService().updateModel(modelUpdate);
			infoMessage(CustomUtil.getBundlMSGValue("se.sys.config.update.success"));
		} catch (SegesaServiceException e) {
			errorMessage(CustomUtil.getBundlMSGValue("se.sys.config.update.fail"));
			System.out.println(e);
			logger.error(" update Sys Config Error :" + e);
		}

		return null;
	}
	
	@Override
	public String search() {
		try {
			logger.info("====================- Enter Search role ====================");

			setDataModel(new SearchPagination<SystemConfig, SystemConfigSearch>(getSystemConfigService(), certeria, "id", Boolean.TRUE));

			Long totalRows = getSystemConfigService().getRowCount(getCerteria()) ;
			if (totalRows != null) {
				getDataModel().setRowCount(totalRows.intValue());
			} else {
				getDataModel().setRowCount(0);
			}
			setConfigLazyDataModel(getDataModel());

		} catch (SegesaServiceException e) {

			System.out.println(e);

			logger.error(" update role Error :" + e);
		}

		return null;
	}
	@Override
	public String save() {
		try {
			logger.info("====================- Enter Save role ====================");

			if (model.getId() != null) {
				warnMessage(CustomUtil.getBundlMSGValue("se.sys.config.save.already"));
				return null;
			}
			model.setCreatedBy(getUser().getUserCode());
			model.setCreatedDate(new Date());
			model = getSystemConfigService().saveModel(model);
			infoMessage(CustomUtil.getBundlMSGValue("se.sys.config.save.success"));

		} catch (SegesaServiceException e) {
			errorMessage(CustomUtil.getBundlMSGValue("se.sys.config.save.fail"));
			System.out.println(e);
			logger.error(" Save Sys Config :" + e);

		}

		return null;
	}
	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String goToConfigUpdate(String id) {
		System.out.println("Role ID = "+id);
		//return UtilConstant.FROM_SEARCH_TO_UPDATE_CONFIG + "?faces-redirect=true&updateKey=" + id;
		return "/bill/segesa/user/updateSysConfig.xhtml?faces-redirect=true&updateKey=" + id;
	}
	
	public void reset() {
		model = new SystemConfig();
		configLazyDataModel = null;
		certeria = new SystemConfigSearch();
	}
	
	public String cancel()
	{
		return "/bill/segesa/user/searchSysConfig.xhtml?faces-redirect=true";
	}
	
	public SystemConfigService getSystemConfigService() {
		return systemConfigService;
	}
	public void setSystemConfigService(SystemConfigService systemConfigService) {
		this.systemConfigService = systemConfigService;
	}
	public SystemConfig getModel() {
		return model;
	}
	public void setModel(SystemConfig model) {
		this.model = model;
	}
	public SystemConfigSearch getCerteria() {
		return certeria;
	}
	public void setCerteria(SystemConfigSearch certeria) {
		this.certeria = certeria;
	}
	public LazyDataModel<SystemConfig> getConfigLazyDataModel() {
		return configLazyDataModel;
	}
	public void setConfigLazyDataModel(LazyDataModel<SystemConfig> configLazyDataModel) {
		this.configLazyDataModel = configLazyDataModel;
	}
	public String getUpdateKey() {
		return updateKey;
	}
	public void setUpdateKey(String updateKey) {
		this.updateKey = updateKey;
	}
	public SystemConfig getModelUpdate() {
		return modelUpdate;
	}
	public void setModelUpdate(SystemConfig modelUpdate) {
		this.modelUpdate = modelUpdate;
	}
	
	

}
