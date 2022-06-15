package core.bill.invoice.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.bill.comercial.bean.ContractBean;

import core.bill.comercial.model.CustomerDTO;
import core.bill.comercial.service.CustomerService;
import core.bill.common.bean.GenericBean;
import core.bill.common.util.CustomUtil;
import core.bill.invoice.model.ChargeDTO;
import core.bill.invoice.model.InvoiceSearch;
import core.bill.invoice.model.OtherPaymentDTO;
import core.bill.invoice.service.OtherPaymentService;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.service.ChargeService;
import core.bill.setting.serviceProvider.SettingServiceProvider;

@ManagedBean(name = "othPayment")
@ViewScoped
public class OtherPaymentBean extends GenericBean<OtherPaymentDTO, InvoiceSearch>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(ContractBean.class);
	
	@ManagedProperty("#{customerService}")
	private CustomerService customerService;
	
	@ManagedProperty("#{settingServiceProvider}")
	private SettingServiceProvider  settingServiceProvider ;
	
	@ManagedProperty("#{chargeService}")
	private ChargeService chargeService;
	
	@ManagedProperty("#{otherPaymentService}")
	private OtherPaymentService otherPaymentService;
	
	private List<OtherPaymentDTO> otherPaymentList;
	private OtherPaymentDTO selectedInvoice;
	
	
	private CustomerDTO customerDto;
	private String updateKey ; 
	private List<ChargeDTO> chargeList;
	
	private ChargeDTO chargeDto;
	private Boolean printFlag=false;
	
	private static PrintInvoiceBean printInvoiceBean;
	
	
	public void loadNewContract() {
		try {

			if (updateKey != null) {
				// String decKey = SecureParams.decrypt(updateKey);
				// System.out.println("after updateKey decrpt = "+decKey);

				Long custID = Long.valueOf(updateKey);
				customerDto = getCustomerService().getModelDTO(custID);
				loadList();
			}

			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("loadNewContract :" + e);
		}
	}



	private void loadList() {
		try {
			CommonSearchDTO search = new CommonSearchDTO();
			search.setStatus("1");
			Long x= getChargeService().getRowCount(search);
			chargeList=getChargeService().getDataList(search, 0, x.intValue(), "chargeId", Boolean.TRUE);
			InvoiceSearch inv = new InvoiceSearch();
			inv.setCustomerId(customerDto.getCustomerId());
			otherPaymentList=getOtherPaymentService().getDataList(inv, 0, 0, "otherPaymentId", Boolean.TRUE);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Load List :" + e);
		}
		
	}

	public void addInvoice() {
		try {
			OtherPaymentDTO oth= new OtherPaymentDTO();
			oth.setChargeDTO(chargeDto);
			oth.setCustomerId(customerDto.getCustomerId());
			oth.setAmountPayable(chargeDto.getAmount().doubleValue());
			oth.setCreatedBy(getUser().getUserId().toString());
			oth.setCreatedDate(new Date());
			oth.setTotal(chargeDto.getAmount().doubleValue());
			oth.setLocalityId(customerDto.getLocalityDTO().getLocalityId());
			oth.setPaymentStatus(Boolean.FALSE);
			getOtherPaymentService().saveModel(oth);
			infoMessage(CustomUtil.getBundlMSGValue("save.succ"));
			loadList();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addInvoice List :" + e);
		}
	}

	public void onSelected(SelectEvent<OtherPaymentDTO> event) {
		selectedInvoice=event.getObject();
		printFlag=false;
		if(selectedInvoice.getPaymentStatus()!=null && selectedInvoice.getPaymentStatus()==false) {
			printFlag=true;	
		}
		
		
		System.out.println("select inv"+printFlag);
		
	}
	
	public void printOtherPayBill() {
		
		printInvoiceBean= new PrintInvoiceBean();
		List<OtherPaymentDTO> list= new ArrayList<OtherPaymentDTO>();
		list.add(selectedInvoice);
		printInvoiceBean.printBillContract(customerDto, selectedInvoice,list);
	}

	public CustomerService getCustomerService() {
		return customerService;
	}


	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}


	public SettingServiceProvider getSettingServiceProvider() {
		return settingServiceProvider;
	}


	public void setSettingServiceProvider(SettingServiceProvider settingServiceProvider) {
		this.settingServiceProvider = settingServiceProvider;
	}


	public CustomerDTO getCustomerDto() {
		return customerDto;
	}


	public void setCustomerDto(CustomerDTO customerDto) {
		this.customerDto = customerDto;
	}


	public String getUpdateKey() {
		return updateKey;
	}


	public void setUpdateKey(String updateKey) {
		this.updateKey = updateKey;
	}


	public ChargeService getChargeService() {
		return chargeService;
	}


	public void setChargeService(ChargeService chargeService) {
		this.chargeService = chargeService;
	}



	public List<ChargeDTO> getChargeList() {
		return chargeList;
	}



	public void setChargeList(List<ChargeDTO> chargeList) {
		this.chargeList = chargeList;
	}



	public ChargeDTO getChargeDto() {
		return chargeDto;
	}



	public void setChargeDto(ChargeDTO chargeDto) {
		this.chargeDto = chargeDto;
	}



	public OtherPaymentService getOtherPaymentService() {
		return otherPaymentService;
	}



	public void setOtherPaymentService(OtherPaymentService otherPaymentService) {
		this.otherPaymentService = otherPaymentService;
	}



	public List<OtherPaymentDTO> getOtherPaymentList() {
		return otherPaymentList;
	}



	public void setOtherPaymentList(List<OtherPaymentDTO> otherPaymentList) {
		this.otherPaymentList = otherPaymentList;
	}



	public OtherPaymentDTO getSelectedInvoice() {
		return selectedInvoice;
	}



	public void setSelectedInvoice(OtherPaymentDTO selectedInvoice) {
		this.selectedInvoice = selectedInvoice;
	}



	public Boolean getPrintFlag() {
		return printFlag;
	}



	public void setPrintFlag(Boolean printFlag) {
		this.printFlag = printFlag;
	}
}
