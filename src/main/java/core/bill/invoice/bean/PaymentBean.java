package core.bill.invoice.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import core.bill.comercial.model.ContractDTO;
import core.bill.comercial.service.serviceProvider.CommericalServiceProvider;
import core.bill.common.bean.GenericBean;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.util.CustomUtil;
import core.bill.common.util.UtilConstant;
import core.bill.invoice.model.InvoiceDTO;
import core.bill.invoice.model.InvoiceSearch;
import core.bill.invoice.model.ReadingDTO;
import core.bill.invoice.service.InvoiceService;
import core.bill.invoice.service.PaymentService;
import core.bill.invoice.service.ReadingService;
import core.bill.setting.serviceProvider.SettingServiceProvider;
import core.bill.user.model.UserDTO;
import core.bill.user.service.UserService;
import core.bill.invoice.model.PaymentDTO;

@ManagedBean(name = "paymentBean")
@ViewScoped
public class PaymentBean extends GenericBean<InvoiceDTO, InvoiceSearch> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(PaymentBean.class);

	@ManagedProperty("#{commericalServiceProvider}")
	CommericalServiceProvider commericalServiceProvider;

	@ManagedProperty("#{invoiceService}")
	InvoiceService invoiceService;

	@ManagedProperty("#{readingService}")
	ReadingService readingService;

	@ManagedProperty("#{settingServiceProvider}")
	private SettingServiceProvider settingServiceProvider;

	@ManagedProperty("#{paymentService}")
	private PaymentService paymentService;

	@ManagedProperty("#{userService}")
	private UserService userService;

	UserDTO userDto;

	private String chequeNo;
	private Date chequeIssueDate;
	private Long bankId;
	private String compensationNo;
	private Long tranferBankId;
	private String transferNo;
	private List<InvoiceDTO> invoiceLazyDataModel;
	private ContractDTO contractDto = new ContractDTO();
	private Long contractId;
	private InvoiceSearch criterion;
	private double requiredAmount;
	private double penaliz;
	private InvoiceDTO invoiceDto;
	private ReadingDTO readingDto;
	private boolean flag;
	private String tabID = "cash";
	private InvoiceDTO selectInv;
	
	private String description = "";


	
	public void searchcontract() {
		try {
			if (contractId != null && contractId > 0) {
				contractDto = getCommericalServiceProvider().getContratById(contractId);

				search();
				// ------------------call Calculate Penalty

			}

		} catch (Exception e) {
			logger.error("InvoiceBean : searchcontract " + e);
			e.printStackTrace();
		}
	}

	// --------------------------
	 public void onRowSelect(SelectEvent<InvoiceDTO> event) {
		 invoiceDto=event.getObject();
	 }
	 
	 
	public void selectRow(InvoiceDTO invoiceSelected) throws SegesaServiceException {

		System.out.println("====== invoiceSelected ==== :" + invoiceSelected.getInvoiceId());
		invoiceDto = invoiceSelected;
	}

	// ----------------------------------------------------------
	public void onTabChange(TabChangeEvent<String> event) {

		tabID = (String) event.getTab().getId().toString().toLowerCase();

	}

	// -------------------------------------------------------------
	public String search() {
		try {
			logger.info("====================- Enter Search role ====================");
			requiredAmount = 0.0;
			penaliz = 0.0;

			calculatePenalty();
			// -------------------------get total
			// getPendingTotal()
			Double tempTotal = invoiceService.getPendingTotal(contractDto.getContractId());

			if (tempTotal != null) {
				requiredAmount = tempTotal;
			}
			if (getInvoiceService().valideSearch(criterion) != null
					&& getInvoiceService().valideSearch(criterion).size() > 0) {
				errorMessages(getInvoiceService().valideSearch(criterion));
				return null;
			}
			criterion = new InvoiceSearch();
			criterion.setContractId(getContractId());

//			setDataModel(new SearchPagination<InvoiceDTO, InvoiceSearch>(getInvoiceService(), getCriterion(),
//					"invoiceId", Boolean.TRUE));
//
//			Long totalRows = getInvoiceService().getRowCount(getCriterion());
//
//			System.out.println("total Rows : " + totalRows);
//
//			if (totalRows != null) {
//				getDataModel().setRowCount(totalRows.intValue());
//			} else {
//				getDataModel().setRowCount(0);
//			}
			setInvoiceLazyDataModel(getInvoiceService().getDataList(getCriterion(), 0, 50, "invoiceId", Boolean.TRUE));

		} catch (SegesaServiceException e) {

			System.out.println(e);

			logger.error(" update role Error :" + e);
		}

		return null;
	}

	// =======================
	public String payInvoice() {
		// 01 cash
		// 02 cheque
		// 03 Compensation
		// 04 bank transfer

		try {

			if (invoiceDto != null) {
				PaymentDTO paymentDTO = new PaymentDTO();

				invoiceDto.setPenality(penaliz);
				invoiceDto.setAmountPaid(invoiceDto.getAmountPaid() + invoiceDto.getAmountPayable() + penaliz);
				invoiceDto.setAmountPayable(0.0);
				invoiceDto.setElimiableDate(new Date());
				invoiceDto.setEliminable(Boolean.TRUE);
				invoiceDto.setPaidBy(getUser().getUserId().toString());
				invoiceDto.setPaidDate(new Date());
				invoiceDto.setPaymentStatus(Boolean.TRUE);
				invoiceDto.setCategory("01"); // Electrical Consumption

				if (tabID != null && tabID.equals("cash")) // 01
				{
					invoiceDto.setPaymentMode(UtilConstant.PAYMENT_MODE_CASH); // 01
				}

				if (tabID != null && tabID.equals("chequeTab")) // 02
				{
					invoiceDto.setPaymentMode(UtilConstant.PAYMENT_MODE_CHEQUE); // 02
					paymentDTO.setDocumentNo(chequeNo);
					paymentDTO.setDocumentIssueDate(chequeIssueDate);
					paymentDTO.setBankId(bankId);

				}

				if (tabID != null && tabID.equals("comTab")) // 03
				{
					invoiceDto.setPaymentMode(UtilConstant.PAYMENT_MODE_COMPENSATION); // 03
					paymentDTO.setDocumentNo(compensationNo);
				}

				if (tabID != null && tabID.equals("bankTransTab")) // 04
				{
					invoiceDto.setPaymentMode(UtilConstant.PAYMENT_MODE_BANK_TRANSFER); // 04
					paymentDTO.setDocumentNo(transferNo);
					paymentDTO.setBankId(tranferBankId);
				}

				invoiceService.updateModel(invoiceDto);
				paymentDTO.setAmountPaid(invoiceDto.getAmountPaid());
				paymentDTO.setAmountPayable(invoiceDto.getAmountPayable() + invoiceDto.getPenality());
				paymentDTO.setContractId(contractDto.getContractId());
				paymentDTO.setReferenceId(invoiceDto.getInvoiceId());
				paymentDTO.setCustomerId(contractDto.getCustomerDTO().getCustomerId());
				paymentDTO.setLocalityId(invoiceDto.getLocalityId());
				paymentDTO.setPaidDate(new Date());
				paymentDTO.setPaymentMode(invoiceDto.getPaymentMode());
				paymentDTO.setMode(UtilConstant.MODE_INVOICE);
				paymentDTO.setCreatedBy(getUser().getUserId().toString());
				paymentDTO.setCreatedDate(new Date());
				paymentDTO.setPaymentStatus("Paid");
				paymentDTO.setPaidBy(getUser().getUserId().toString());

				paymentService.saveModel(paymentDTO);
				//search();
				copyInv=false;
				printBill();

			}
		} catch (Exception e) {
			logger.error("payInvoice :" + e);
			e.printStackTrace();
		}
		return null;
	}

	public String cancelPaidInvoice() {


		try {
			if(description==null) {
				errorMessage(CustomUtil.getBundlMSGValue("menu.cashier.cancel.pay.invoice.description"));
			}else{
			if (invoiceDto != null) {
				PaymentDTO paymentDTO = new PaymentDTO();
				invoiceDto.setAmountPayable(invoiceDto.getAmountPaid()-invoiceDto.getPenality());
				invoiceDto.setPenality(0.0);
				invoiceDto.setAmountPaid(0.0);
				
				invoiceDto.setElimiableDate(null);
				invoiceDto.setEliminable(Boolean.FALSE);
				invoiceDto.setPaidBy(null);
				invoiceDto.setPaidDate(null);
				invoiceDto.setPaymentStatus(Boolean.FALSE);
				invoiceDto.setCategory(null); // Electrical Consumption

				invoiceDto.setPaymentMode(null); // 01

				invoiceService.updateModel(invoiceDto);
				Long paimentId = null;
				
				InvoiceSearch creteria = new InvoiceSearch();	
				creteria.setInvoiceId(invoiceDto.getInvoiceId());
				creteria.setPaymentDesc("Paid");
				
				List<PaymentDTO> pendingList = paymentService.getDataList(creteria, 0, 1, "paymentId",
						Boolean.TRUE);
				for(PaymentDTO p:pendingList) {
					paimentId=p.getPaymentId();
				}
				
				paymentDTO=paymentService.getModelDTO(paimentId);
				
				paymentDTO.setPaymentStatus("Canceled");
				paymentDTO.setUpdatedBy(getUser().getUserId().toString());
				paymentDTO.setUpdatedDate(new Date());
				paymentDTO.setDescription(description);

				paymentService.updateModel(paymentDTO);
			}
			}
		} catch (Exception e) {
			logger.error("payInvoice :" + e);
			e.printStackTrace();
		}
		return null;
	}
	// ------------------------------------
	private void calculatePenalty() {

		try {
			boolean exmptPenalty = false;
			InvoiceDTO lastInvoiceDto = null;
			Date toDay = new Date();
			SimpleDateFormat getYear = new SimpleDateFormat("yy");
			SimpleDateFormat getMonth = new SimpleDateFormat("MM");
			SimpleDateFormat getDay = new SimpleDateFormat("dd");

			if ((contractDto.getExemptPenality() != null && contractDto.getExemptPenality())
					|| !contractDto.getLocalityDTO().getPenZ()) {
				exmptPenalty = true;
			}

			InvoiceSearch criteria = new InvoiceSearch();
			criteria.setContractId(contractDto.getContractId());
			criteria.setEliminable(Boolean.TRUE);
			criteria.setEliminableDate(new Date());
			Long count = invoiceService.getRowCount(criteria);

			System.out.println("count : " + count);

			InvoiceSearch PendingInvociecriteria = new InvoiceSearch();
			PendingInvociecriteria.setContractId(contractDto.getContractId());
			PendingInvociecriteria.setInvoiceCreated(Boolean.TRUE);
			PendingInvociecriteria.setPaymentStatus(Boolean.FALSE);
			Long pendingPaymentInvoices = invoiceService.getRowCount(PendingInvociecriteria);

			System.out.println("pendingPaymentInvoices " + pendingPaymentInvoices);

			List<InvoiceDTO> pendingList = invoiceService.getDataList(PendingInvociecriteria, 0, 1, "invoiceId",
					Boolean.TRUE);
			if (pendingList != null && pendingList.size() > 0) {
				lastInvoiceDto = pendingList.get(0);
			}

			if ((count != null && count > 0)) {
				exmptPenalty = true;
			}

			if (exmptPenalty) {
				penaliz = 0.0;
			} else {
				if (pendingPaymentInvoices != null && pendingPaymentInvoices == 1) {
					if (lastInvoiceDto != null && lastInvoiceDto.getYear() == Integer.parseInt(getYear.format(toDay))
							&& lastInvoiceDto.getMonth() == Integer.parseInt(getMonth.format(toDay))) {
						penaliz = 0.0;
					} else if (lastInvoiceDto != null
							&& lastInvoiceDto.getYear() == Integer.parseInt(getYear.format(toDay))
							&& lastInvoiceDto.getMonth() == (Integer.parseInt(getMonth.format(toDay)) - 1)) {
						if (Integer.parseInt(getDay.format(toDay)) <= 15) {
							penaliz = 0.0;
						}

						if (Integer.parseInt(getDay.format(toDay)) > 15) {
							penaliz = getPenaltyValue();
						}
					}

				}

				// ---------------------------------------------
				if (pendingPaymentInvoices != null && pendingPaymentInvoices == 2) {
					penaliz = getPenaltyValue();
				}

				if (pendingPaymentInvoices != null && pendingPaymentInvoices == 3) {
					if (Integer.parseInt(getDay.format(toDay)) <= 15) {
						penaliz = getPenaltyValue();
					}

					if (Integer.parseInt(getDay.format(toDay)) > 15) {
						if (getPenaltyValue() == UtilConstant.PENALTY_MEDIA_TENSION_CHARGE) {
							penaliz = getPenaltyValue();
						} else {
							penaliz = getPenaltyValue() * 2;
						}

					}
				}

				if (pendingPaymentInvoices != null && pendingPaymentInvoices > 3) {
					if (getPenaltyValue() == UtilConstant.PENALTY_MEDIA_TENSION_CHARGE) {
						penaliz = getPenaltyValue();
					} else {
						penaliz = getPenaltyValue() * 2;
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private double getPenaltyValue() {
		double penaltyVal = 0.0;

		if (contractDto.getPhaseDTO().getPhaseCode().equals(UtilConstant.PENALTY_MON)) {
			penaltyVal = UtilConstant.PENALTY_MON_CHARGE;
		}

		if (contractDto.getPhaseDTO().getPhaseCode().equals(UtilConstant.PENALTY_TRIFASICA)) {
			penaltyVal = UtilConstant.PENALTY_TRIFASICA_CHARGE;
		}

		if (contractDto.getPhaseDTO().getPhaseCode().equals(UtilConstant.PENALTY_MEDIA_TENSION)) {
			penaltyVal = UtilConstant.PENALTY_MEDIA_TENSION_CHARGE;
		}

		return penaltyVal;
	}
	
	Boolean copyInv=true;
	
	public void printBill() throws SegesaServiceException {
		double previousReading = getPreviousReading();
		PrintInvoiceBean printInvoiceBean = new PrintInvoiceBean();
		userDto = new UserDTO();
		String cashierName = "";

		if (invoiceDto.getPaidBy() != null) {
			if (userService.getModelDTO(Long.valueOf(invoiceDto.getPaidBy())) != null) {
				cashierName = userService.getModelDTO(Long.valueOf(invoiceDto.getPaidBy())).getUserName();
			}
		}

		printInvoiceBean.printBill(contractDto, invoiceDto, getUser().getUserName(), 1, previousReading, true,
				cashierName,copyInv);

	}

	public double getPreviousReading() throws SegesaServiceException {

		ReadingDTO prevouisReadingDto = null;

		int prevouisYear = 0;
		int prevouisMonth = 0;

		if (invoiceDto.getMonth() == 1) {
			prevouisMonth = 12;
			prevouisYear = invoiceDto.getYear() - 1;
		} else {
			prevouisMonth = invoiceDto.getMonth() - 1;
			prevouisYear = invoiceDto.getYear();

		}

		InvoiceSearch prevouisReadingCriteria = new InvoiceSearch();
		prevouisReadingCriteria.setContractId(contractDto.getContractId());
		prevouisReadingCriteria.setMonth(prevouisMonth);
		prevouisReadingCriteria.setYear(prevouisYear);
		List<ReadingDTO> prevouislist = getReadingService().getDataList(prevouisReadingCriteria, 0, 0, "readingId",
				Boolean.TRUE);

		if (prevouislist != null && prevouislist.size() > 0) {
			prevouisReadingDto = prevouislist.get(0);
			return prevouisReadingDto.getReading();
		} else {
			return 0.0;
		}

	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	public ContractDTO getContractDto() {
		return contractDto;
	}

	public void setContractDto(ContractDTO contractDto) {
		this.contractDto = contractDto;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public CommericalServiceProvider getCommericalServiceProvider() {
		return commericalServiceProvider;
	}

	public InvoiceService getInvoiceService() {
		return invoiceService;
	}

	public void setCommericalServiceProvider(CommericalServiceProvider commericalServiceProvider) {
		this.commericalServiceProvider = commericalServiceProvider;
	}

	public List<InvoiceDTO> getInvoiceLazyDataModel() {
		return invoiceLazyDataModel;
	}

	public void setInvoiceLazyDataModel(List<InvoiceDTO> invoiceLazyDataModel) {
		this.invoiceLazyDataModel = invoiceLazyDataModel;
	}

	public InvoiceSearch getCriterion() {
		return criterion;
	}

	public void setCriterion(InvoiceSearch criterion) {
		this.criterion = criterion;
	}

	public Logger getLogger() {
		return logger;
	}

	public double getRequiredAmount() {
		return requiredAmount;
	}

	public double getPenaliz() {
		return penaliz;
	}

	public InvoiceDTO getInvoiceDto() {
		return invoiceDto;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void setRequiredAmount(double requiredAmount) {
		this.requiredAmount = requiredAmount;
	}

	public void setPenaliz(double penaliz) {
		this.penaliz = penaliz;
	}

	public void setInvoiceDto(InvoiceDTO invoiceDto) {
		this.invoiceDto = invoiceDto;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public ReadingService getReadingService() {
		return readingService;
	}

	public void setReadingService(ReadingService readingService) {
		this.readingService = readingService;
	}

	public ReadingDTO getReadingDto() {
		return readingDto;
	}

	public void setReadingDto(ReadingDTO readingDto) {
		this.readingDto = readingDto;
	}

	public SettingServiceProvider getSettingServiceProvider() {
		return settingServiceProvider;
	}

	public void setSettingServiceProvider(SettingServiceProvider settingServiceProvider) {
		this.settingServiceProvider = settingServiceProvider;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public Date getChequeIssueDate() {
		return chequeIssueDate;
	}

	public Long getBankId() {
		return bankId;
	}

	public String getCompensationNo() {
		return compensationNo;
	}

	public Long getTranferBankId() {
		return tranferBankId;
	}

	public String getTabID() {
		return tabID;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public void setChequeIssueDate(Date chequeIssueDate) {
		this.chequeIssueDate = chequeIssueDate;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public void setCompensationNo(String compensationNo) {
		this.compensationNo = compensationNo;
	}

	public void setTranferBankId(Long tranferBankId) {
		this.tranferBankId = tranferBankId;
	}

	public void setTabID(String tabID) {
		this.tabID = tabID;
	}

	public String getTransferNo() {
		return transferNo;
	}

	public void setTransferNo(String transferNo) {
		this.transferNo = transferNo;
	}

	public PaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public InvoiceDTO getSelectInv() {
		return selectInv;
	}

	public void setSelectInv(InvoiceDTO selectInv) {
		this.selectInv = selectInv;
	}

}
