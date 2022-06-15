package core.bill.comercial.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import core.bill.comercial.model.ContractDTO;
import core.bill.comercial.model.ContractSearch;
import core.bill.comercial.model.CustomerDTO;
import core.bill.comercial.model.CustomerSearch;
import core.bill.comercial.service.ContractService;
import core.bill.comercial.service.CustomerService;
import core.bill.common.bean.GenericBean;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.util.CustomUtil;
import core.bill.common.util.UtilConstant;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.ConnectionTypeDTO;
import core.bill.setting.model.ContractBillingDTO;
import core.bill.setting.model.ContractPaymentDTO;
import core.bill.setting.model.ContractTypeDTO;
import core.bill.setting.model.LocalityDTO;
import core.bill.setting.model.MeterStatusDTO;
import core.bill.setting.model.PhaseDTO;
import core.bill.setting.model.SectorDTO;
import core.bill.setting.model.SupplyDTO;
import core.bill.setting.model.TypeDTO;
import core.bill.setting.serviceProvider.SettingServiceProvider;
import core.bill.upload.model.UploadFileDTO;
import core.bill.upload.serviceProvider.UploadFileSharedService;
import core.bill.user.serviceProvider.UserSharedService;

@ManagedBean(name = "contractBean")
@ViewScoped

public class ContractBean extends GenericBean<ContractDTO, ContractSearch> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(ContractBean.class);

	@ManagedProperty("#{contractService}")
	private ContractService contractService;

	@ManagedProperty("#{customerService}")
	private CustomerService customerService;
	
	@ManagedProperty("#{settingServiceProvider}")
	private SettingServiceProvider  settingServiceProvider ;
	
	@ManagedProperty("#{userSharedService}")
	private UserSharedService userSharedService ;
	
	@ManagedProperty("#{uploadFileSharedService}")
	private UploadFileSharedService uploadFileSharedService ;

	private ContractDTO model;
	private CustomerDTO customerDto;
	private Long customerId;
	private String CustomerCode;

	private List<PhaseDTO> phaseList;
	private List<SectorDTO> sectorList;
	private List<ConnectionTypeDTO> connList;
	private List<SupplyDTO> supplyList;
	private List<TypeDTO> typeDTOList;
	private List<ContractTypeDTO> contractTypeList ;
	private List<MeterStatusDTO> meterStatusDTOList ;
	private List<ContractPaymentDTO> contractPaymentDTOList ;
	private List<ContractBillingDTO> contractBillingList ;
	private List<LocalityDTO> localityDTOList ;

	private PhaseDTO phaseDto;
	private SectorDTO sectorDto;
	private ConnectionTypeDTO connectionTypeDto;
	private SupplyDTO supplyDto;
	private ContractTypeDTO contractTypeDto ;
	private MeterStatusDTO meterStatusDto ;
	
	private TypeDTO typeDto ;
	private ContractPaymentDTO contractPaymentDto ;
	private ContractBillingDTO contractBillingDto ;
	
	private LocalityDTO localityDto ; 
	
	private String updateKey ; 
	private String uploadFilePath ; 
	private List<UploadFileDTO> uploadFileList ;
	private Long uploadFileParamID ; 

	
	public void loadNewContract() {
		try {

			if (updateKey != null) {
				// String decKey = SecureParams.decrypt(updateKey);
				// System.out.println("after updateKey decrpt = "+decKey);

				Long custID = Long.valueOf(updateKey);
				customerDto = getCustomerService().getModelDTO(custID);
			}
			model = new ContractDTO();
			model.setCustomerDTO(customerDto);
			

			loadlists();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("loadNewContract :" + e);
		}
	}
    //=============================================
	public String onload()
	{
		try {

			System.out.println("updateKey = " + updateKey);
			if (updateKey != null) {
				// String decKey = SecureParams.decrypt(updateKey);
				// System.out.println("after updateKey decrpt = "+decKey);

				Long contractId = Long.valueOf(updateKey);
				model = getContractService().getModelDTO(contractId);
				
				loadlists();
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Update Customer Onload :"+e);
		}

		return null;
	}
	
	
	//----------------------------------------------------------------
	public String save()
	{
		logger.info("========== inster Contract save ===============");
		try {
			
			if(model.getContractId()!=null)
			{
				//warnMessage(CustomUtil.getBundlMSGValue("se.comm.contract.save.already"));
				
				//model.setPhaseDTO(phaseDto);
				//model.setSectorDTO(sectorDto);
				
				///model.getContractInfoDTO().setContractBillingDTO(contractBillingDto);
				//model.getContractInfoDTO().setContractPaymentDTO(contractPaymentDto);
				//model.getContractInfoDTO().setContractTypeDTO(contractTypeDto);
				model = getContractService().updateModel(model);
				
				saveUploadFilesList();
				
				infoMessage(CustomUtil.getBundlMSGValue("se.comm.contract.save.success"));
				return null; 
			}
			
			Long max = getContractService().getMaxContractId();
			
			if(model.getLocalityDTO()!=null && model.getLocalityDTO().getLocalityCode()!=null)
			{
				model.setContractCode(model.getLocalityDTO().getLocalityCode()+String.valueOf(max+1L));
			}
			
			
			model.setContractStatus("DRF");  //DRAFT
			model.setCreatedBy(getUser().getUserCode());
			model.setCreatedDate(new Date());
			model.setCustomerDTO(customerDto);
			
			model = getContractService().saveModel(model);
			
			saveUploadFilesList();
			
			infoMessage(CustomUtil.getBundlMSGValue("se.comm.contract.save.success"));
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			errorMessage(CustomUtil.getBundlMSGValue("se.comm.contract.save.fail"));
		}
		return null ; 
	}
	
	
	
	private void saveUploadFilesList() {
		try {
			if(uploadFileList!=null && uploadFileList.size()> 0)
			{
				Long crtID = model.getContractId() ;
				if(crtID !=null)
				for(UploadFileDTO file: uploadFileList)
				{
					if(file.getReferenceId()==null || file.getReferenceId().length()<=0)
					{
						file.setReferenceId(crtID.toString());
					}
				}
				
				uploadFileList =	getUploadFileSharedService().saveFile(uploadFileList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public String submit()
	{
		return null ;
	}
	private void loadlists() {

		try {
			CommonSearchDTO search = new CommonSearchDTO();
			search.setStatus("ACT");
			phaseList = getSettingServiceProvider().getPhaseDTOList(search);
			sectorList = getSettingServiceProvider().getSectorDTOList(search);
			connList = getSettingServiceProvider().getConnectionTypeDTOList(search);
			supplyList = getSettingServiceProvider().getSupplyDTOList(search);
			typeDTOList=getSettingServiceProvider().getTypeDTOList(search); //
			contractTypeList = getSettingServiceProvider().geContractTypeDTOList(search);
			meterStatusDTOList = getSettingServiceProvider().getMeterStatusDTOList(search);
			contractPaymentDTOList =getSettingServiceProvider().getContractPaymentDTOList(search); //
			contractBillingList = getSettingServiceProvider().getContractBillingDTOList(search); //
			
			System.out.println("model id :"+model.getContractId());
			
			if(model!=null && model.getContractId()!=null)
			{
			uploadFileList = getUploadFileSharedService().loadFiles(UtilConstant.UPLOAD_CONTRACT_GATEGORY, model.getContractId().toString());
			System.out.println("uploadFileList size :"+uploadFileList.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String serchCustomer() {
		try {
			if (customerId != null) {
				customerDto = getCustomerService().getModelDTO(customerId);
			} else if (CustomerCode != null) {
				CustomerSearch cuSearch = new CustomerSearch();
				cuSearch.getCustomerCode();
				List<CustomerDTO> custList = getCustomerService().getDataList(cuSearch);
				if (custList != null && custList.size() > 0) {
					customerDto = custList.get(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return null;
	}

	//----------------------------------upload file 
	
	public void handleFileUpload(FileUploadEvent event) {

		try {
			logger.info("file uploaded  :" + event.getFile());
			logger.info("file :" + event.getSource());
			byte[] bytes = event.getFile().getContent();

			UploadFileDTO upload = new UploadFileDTO();
			upload.setCategory(UtilConstant.UPLOAD_CONTRACT_GATEGORY);
			if (getUploadFilePath() == null || getUploadFilePath().length() <= 0) {
				setUploadFilePath(getUserSharedService().getSystemConfig(UtilConstant.UPLOAD_LOCATION).getValue());

			}

			upload.setPath(getUploadFilePath());
			upload.setFolder(CustomUtil.createFolder(getUploadFilePath(), UtilConstant.UPLOAD_CONTRACT_GATEGORY));
			String fileName = event.getFile().getFileName();
			upload.setFileName(fileName);

			CustomUtil.writeFile(
					upload.getPath() + File.separator + upload.getFolder() + File.separator + upload.getFileName(),
					bytes);

			upload.setSize(Long.valueOf(event.getFile().getSize()));
			upload.setCreatedDate(new Date());
			
			if(uploadFileList == null)
			{
				uploadFileList = new ArrayList<>();
			}
			
			if(model.getContractId()!=null)
			{
				upload.setReferenceId((model.getContractId()).toString());
			}
			uploadFileList.add(upload);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//-----------------------------------------------------
	public String downloadDocument()
	{
		List<UploadFileDTO> downloadedList= null; 
		
		if(uploadFileList!=null && uploadFileList.size()>0)
		{
			for(UploadFileDTO file : uploadFileList)
			{
				if(file.getSelected())
				{
					if(downloadedList==null)
					{
						downloadedList = new ArrayList<>();
					}
					downloadedList.add(file);
					
				}
			}
		}
		
		if(downloadedList !=null && downloadedList.size()>1)
		{
			CustomUtil.downloadZip(downloadedList);
		}else if(downloadedList !=null && downloadedList.size()== 1)
		{
			CustomUtil.download(downloadedList.get(0));
		}
		return null; 
	}
	//----------------------------------------------delete document----------------
	public String deleteDocument()
	{
		List<UploadFileDTO> deletedFilesList= null; 
		if(uploadFileList!=null && uploadFileList.size()>0)
		{
			for(UploadFileDTO file : uploadFileList)
			{
				if(file.getSelected())
				{ 
					if(deletedFilesList==null)
					{
						deletedFilesList = new ArrayList<>();
					}
					
					deletedFilesList.add(file);
					
				}
			}
		}
		
		if(deletedFilesList!=null && deletedFilesList.size()>0)
		{
			uploadFileList.removeAll(deletedFilesList);
			try {
				getUploadFileSharedService().deleteFile(deletedFilesList);
			} catch (SegesaServiceException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void printContract() {
		try {
			PrintContract printContract = new PrintContract();
			printContract.printStudProject(model, getUser().getUserName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String cancel() {
		return UtilConstant.FROM_UPDATE_TO_SEARCH_CONTRACT + "?faces-redirect=true";
	}
	//-----------------------
	public void setDocumetShow(Long fileId)
	{
		this.uploadFileParamID =fileId ;
	}
	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	public ContractDTO getModel() {
		return model;
	}

	public void setModel(ContractDTO model) {
		this.model = model;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerCode() {
		return CustomerCode;
	}

	public void setCustomerCode(String customerCode) {
		CustomerCode = customerCode;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public CustomerDTO getCustomerDto() {
		return customerDto;
	}

	public void setCustomerDto(CustomerDTO customerDto) {
		this.customerDto = customerDto;
	}

	public List<PhaseDTO> getPhaseList() {
		return phaseList;
	}

	public void setPhaseList(List<PhaseDTO> phaseList) {
		this.phaseList = phaseList;
	}

	public List<SectorDTO> getSectorList() {
		return sectorList;
	}

	public void setSectorList(List<SectorDTO> sectorList) {
		this.sectorList = sectorList;
	}

	public List<ConnectionTypeDTO> getConnList() {
		return connList;
	}

	public void setConnList(List<ConnectionTypeDTO> connList) {
		this.connList = connList;
	}

	public List<SupplyDTO> getSupplyList() {
		return supplyList;
	}

	public void setSupplyList(List<SupplyDTO> supplyList) {
		this.supplyList = supplyList;
	}

	public PhaseDTO getPhaseDto() {
		return phaseDto;
	}

	public void setPhaseDto(PhaseDTO phaseDto) {
		this.phaseDto = phaseDto;
	}

	public SectorDTO getSectorDto() {
		return sectorDto;
	}

	public void setSectorDto(SectorDTO sectorDto) {
		this.sectorDto = sectorDto;
	}

	public ConnectionTypeDTO getConnectionTypeDto() {
		return connectionTypeDto;
	}

	public void setConnectionTypeDto(ConnectionTypeDTO connectionTypeDto) {
		this.connectionTypeDto = connectionTypeDto;
	}

	public SupplyDTO getSupplyDto() {
		return supplyDto;
	}

	public void setSupplyDto(SupplyDTO supplyDto) {
		this.supplyDto = supplyDto;
	}

	public SettingServiceProvider getSettingServiceProvider() {
		return settingServiceProvider;
	}

	public void setSettingServiceProvider(SettingServiceProvider settingServiceProvider) {
		this.settingServiceProvider = settingServiceProvider;
	}

	public List<TypeDTO> getTypeDTOList() {
		return typeDTOList;
	}

	public void setTypeDTOList(List<TypeDTO> typeDTOList) {
		this.typeDTOList = typeDTOList;
	}

	

	public List<ContractTypeDTO> getContractTypeList() {
		return contractTypeList;
	}

	public void setContractTypeList(List<ContractTypeDTO> contractTypeList) {
		this.contractTypeList = contractTypeList;
	}

	public ContractTypeDTO getContractTypeDto() {
		return contractTypeDto;
	}

	public void setContractTypeDto(ContractTypeDTO contractTypeDto) {
		this.contractTypeDto = contractTypeDto;
	}

	public List<MeterStatusDTO> getMeterStatusDTOList() {
		return meterStatusDTOList;
	}

	public void setMeterStatusDTOList(List<MeterStatusDTO> meterStatusDTOList) {
		this.meterStatusDTOList = meterStatusDTOList;
	}

	public MeterStatusDTO getMeterStatusDto() {
		return meterStatusDto;
	}

	public void setMeterStatusDto(MeterStatusDTO meterStatusDto) {
		this.meterStatusDto = meterStatusDto;
	}

	public List<ContractPaymentDTO> getContractPaymentDTOList() {
		return contractPaymentDTOList;
	}

	public void setContractPaymentDTOList(List<ContractPaymentDTO> contractPaymentDTOList) {
		this.contractPaymentDTOList = contractPaymentDTOList;
	}

	public List<ContractBillingDTO> getContractBillingList() {
		return contractBillingList;
	}

	public void setContractBillingList(List<ContractBillingDTO> contractBillingList) {
		this.contractBillingList = contractBillingList;
	}

	public ContractPaymentDTO getContractPaymentDto() {
		return contractPaymentDto;
	}

	public void setContractPaymentDto(ContractPaymentDTO contractPaymentDto) {
		this.contractPaymentDto = contractPaymentDto;
	}

	public ContractBillingDTO getContractBillingDto() {
		return contractBillingDto;
	}

	public void setContractBillingDto(ContractBillingDTO contractBillingDto) {
		this.contractBillingDto = contractBillingDto;
	}

	public TypeDTO getTypeDto() {
		return typeDto;
	}

	public void setTypeDto(TypeDTO typeDto) {
		this.typeDto = typeDto;
	}

	public String getUpdateKey() {
		return updateKey;
	}

	public void setUpdateKey(String updateKey) {
		this.updateKey = updateKey;
	}
	public List<LocalityDTO> getLocalityDTOList() {
		return localityDTOList;
	}
	public LocalityDTO getLocalityDto() {
		return localityDto;
	}
	public void setLocalityDTOList(List<LocalityDTO> localityDTOList) {
		this.localityDTOList = localityDTOList;
	}
	public void setLocalityDto(LocalityDTO localityDto) {
		this.localityDto = localityDto;
	}
	public UserSharedService getUserSharedService() {
		return userSharedService;
	}
	public void setUserSharedService(UserSharedService userSharedService) {
		this.userSharedService = userSharedService;
	}
	public String getUploadFilePath() {
		return uploadFilePath;
	}
	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}
	public List<UploadFileDTO> getUploadFileList() {
		return uploadFileList;
	}
	public void setUploadFileList(List<UploadFileDTO> uploadFileList) {
		this.uploadFileList = uploadFileList;
	}
	public UploadFileSharedService getUploadFileSharedService() {
		return uploadFileSharedService;
	}
	public void setUploadFileSharedService(UploadFileSharedService uploadFileSharedService) {
		this.uploadFileSharedService = uploadFileSharedService;
	}
	public Long getUploadFileParamID() {
		return uploadFileParamID;
	}
	public void setUploadFileParamID(Long uploadFileParamID) {
		this.uploadFileParamID = uploadFileParamID;
	}

	

	

	

}
