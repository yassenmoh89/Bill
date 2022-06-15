package core.bill.invoice.bean;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import core.bill.comercial.model.ContractDTO;
import core.bill.comercial.model.ContractSearch;
import core.bill.comercial.service.ContractService;

import core.bill.comercial.service.serviceProvider.CommericalServiceProvider;
import core.bill.common.bean.GenericBean;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.util.SearchPagination;
import core.bill.common.util.UtilConstant;
import core.bill.invoice.model.InvoiceDTO;
import core.bill.invoice.model.InvoiceSearch;
import core.bill.invoice.model.ReadingDTO;
import core.bill.invoice.service.InvoiceService;
import core.bill.invoice.service.ReadingService;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.LocalityDTO;
import core.bill.setting.serviceProvider.SettingServiceProvider;



@ManagedBean(name = "invoiceReport")
@ViewScoped
public class InvoiceReportBean extends GenericBean<InvoiceDTO, InvoiceSearch> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(InvoiceReportBean.class);
	
	@ManagedProperty("#{commericalServiceProvider}")
	CommericalServiceProvider commericalServiceProvider;

	@ManagedProperty("#{invoiceService}")
	InvoiceService invoiceService;

	@ManagedProperty("#{readingService}")
	ReadingService readingService;
	
	@ManagedProperty("#{contractService}")
	ContractService contractService;

	@ManagedProperty("#{settingServiceProvider}")
	private SettingServiceProvider settingServiceProvider;
	

	ContractDTO model;

	private LazyDataModel<InvoiceDTO> invoiceLazyDataModel;
	
	private List<LocalityDTO> locationList;
	
	private static PrintInvoiceBean printInvoiceBean;

	private ContractDTO contractDto = new ContractDTO();
	private Long contractId;
	private InvoiceSearch criterion;
	private double requiredAmount;
	private double penaliz=0;
	private int year_reading;
	private int month_reading;
	private boolean newMeter_reading;
	private int reading_reading;
	private int readChange_reading;
	private InvoiceDTO invoiceDto;
	private ReadingDTO readingDto;
	private Double equation;
	private double price;
	private double discount = 0.0;

	private boolean flag;
	
	private Date fromDate = new Date();
	private Date toDate = new Date();
	List<InvoiceDTO> invoiceList;
	private String repTemplete="ALL";
	
	private Long localityId;
	private Boolean showReportDate=true;
	
	
	@PostConstruct
	private void init() {
		printInvoiceBean=new PrintInvoiceBean();
		loadlists();
	}
	
	private void  loadlists() {
		try {
			CommonSearchDTO search = new CommonSearchDTO();
			search.setStatus("ACT");
			locationList=getSettingServiceProvider().getLocalityDTOList(search);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String searchcontract() {
		try {


				if (contractId != null && contractId > 0) {
					contractDto = getCommericalServiceProvider().getContratById(contractId);

					if (contractDto.getIguala() && contractDto.getTypeDTO() != null
							&& contractDto.getTypeDTO().getTypeCode().equals("PER")) {
						equation = 1.15;
						reading_reading = contractDto.getFixedPriceValue(); // need to be change to real iqual
					} else if (contractDto.getIguala() && contractDto.getTypeDTO() != null
							&& contractDto.getTypeDTO().getTypeCode().equals("SOC")) {
						equation = 1.30;
						reading_reading = contractDto.getFixedPriceValue(); //// need to be change to real iqual
					} else {
						equation = Double.valueOf(contractDto.getFactor());
					}
					search();

					printInvoiceBean = new PrintInvoiceBean();
				}
				return null;
			

		} catch (Exception e) {
			logger.error("InvoiceBean : searchcontract " + e);
			e.printStackTrace();
		}
		return null;
	}


	// -------------------------------------------------------------
	public String search() {
		try {
			logger.info("====================- Enter Search role ====================");
			
			if (getInvoiceService().valideSearch(criterion) != null
					&& getInvoiceService().valideSearch(criterion).size() > 0) {
				errorMessages(getInvoiceService().valideSearch(criterion));
				return null;
			}

			criterion = new InvoiceSearch();
			criterion.setContractId(getContractId());
			
			
			setDataModel(new SearchPagination<InvoiceDTO, InvoiceSearch>(getInvoiceService(), getCriterion(),
					"invoiceId", Boolean.TRUE));

			Long totalRows = getInvoiceService().getRowCount(getCriterion());

			if (totalRows != null) {
				getDataModel().setRowCount(totalRows.intValue());
			} else {
				getDataModel().setRowCount(0);
			}
			setInvoiceLazyDataModel(getDataModel());

		} catch (SegesaServiceException e) {

			System.out.println(e);

			logger.error(" update role Error :" + e);
		}

		return null;
	}


	public void clientBalance() throws SegesaServiceException {
		criterion = new InvoiceSearch();
		criterion.setContractId(getContractId());
		criterion.setFromDate(fromDate);
		criterion.setToDate(toDate);
		//criterion.setInvoiceCreated(Boolean.TRUE);
		penaliz=0.0;
		
		if(repTemplete.equals("UNPAID")) {
			criterion.setPaymentStatus(Boolean.FALSE);
			criterion.setFromDate(null);
			criterion.setToDate(null);
		}
		else if(repTemplete.equals("PAID")) {
			criterion.setPaymentStatus(Boolean.TRUE);
		}
		
		Long totalRows = getInvoiceService().getRowCount(getCriterion());
		Long x=totalRows;
		
		invoiceList=invoiceService.getDataList(criterion, 0, x.intValue(), "invoiceId", Boolean.TRUE);
		if(repTemplete.equals("UNPAID"))
		{
			calculatePenalty();
		}
		
		printInvoiceBean.clientBalance(contractDto, invoiceList, fromDate, toDate, getUser().getUserName(),repTemplete,penaliz,null);
	}
	
	public void companyBalance() throws SegesaServiceException {
		
		criterion = new InvoiceSearch();
		criterion.setCustomerId(contractDto.getCustomerDTO().getCustomerId());
		criterion.setPaymentStatus(Boolean.FALSE);
		//criterion.setInvoiceCreated(Boolean.TRUE);
		List<ContractDTO> listContract=contractService.getCustomerContract(contractDto.getCustomerDTO().getCustomerId());
		System.out.println(" Multi contract"+listContract.size());

		Long totalRows = getInvoiceService().getRowCount(getCriterion());
		Long x=totalRows;
		
		invoiceList=invoiceService.getDataList(criterion, 0, x.intValue(), "contractId", Boolean.TRUE);
		for(InvoiceDTO i:invoiceList) {
			
				for(ContractDTO c:listContract) {
					System.out.println("c.getContractId() :"+c.getContractId());
					System.out.println("i.getContractId() :"+i.getContractId());
					if(c.getContractId().equals(i.getContractId())) {
						i.setContractDirecion(c.getDirection());
						i.setContractbenefeciario(c.getBeneficiary());
						System.out.println("getContractDirecion :"+i.getContractDirecion());
					}						
				}
				
		}
		calculatePenalty();

		System.out.println(" we :"+contractDto.getCustomerDTO().getCustomerId()+"totalRows"+totalRows);
		printInvoiceBean.clientBalance(contractDto, invoiceList, fromDate, toDate, getUser().getUserName(),"company",penaliz,null);
	}

	public void invoicesByLocality() throws SegesaServiceException {

		criterion = new InvoiceSearch();
		criterion.setPaymentStatus(Boolean.FALSE);
		criterion.setLocalityId(this.localityId);
		// criterion.setInvoiceCreated(Boolean.TRUE);

		ContractSearch contractCriteria = new ContractSearch();
		contractCriteria.setLocalityId(localityId);
		List<ContractDTO> listContract = contractService.getDataList(contractCriteria, 0,
				contractService.getRowCount(contractCriteria).intValue(), "contractId", Boolean.TRUE);
		System.out.println(" listContract :" + listContract.size() + "totalRows"
				+ contractService.getRowCount(contractCriteria).intValue());

		Long totalRows = getInvoiceService().getRowCount(getCriterion());
		Long x = totalRows;

		invoiceList = invoiceService.getDataList(criterion, 0, x.intValue(), "contractId", Boolean.TRUE);

		for (ContractDTO c : listContract) {

			for (InvoiceDTO i : invoiceList) {
				if (i.getContractId().equals(c.getContractId())) {
					i.setCustomerName(c.getCustomerDTO().getCustomerName());
					i.setContractDirecion(c.getDirection());
					i.setContractbenefeciario(c.getBeneficiary());

				}
			}
		}
		// calculatePenalty();
		penaliz = 0.0;

		String locationName = "";
		for (LocalityDTO l : locationList) {
			if (l.getLocalityId().equals(localityId)) {
				locationName = l.getDescription();
			}
		}

		printInvoiceBean.clientBalance(null, invoiceList, fromDate, toDate, getUser().getUserName(), "loc", penaliz,
				locationName);
	}
	
	public void onReportOptionSelected() {
		
		if(repTemplete.equals("UNPAID")) {
			this.showReportDate=false;
			System.out.println("onReportOptionSelected"+repTemplete+"showReportDate"+showReportDate);
		}else {
			this.showReportDate=true;
		}
	}
	
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

			InvoiceSearch PendingInvociecriteria = new InvoiceSearch();
			PendingInvociecriteria.setContractId(contractDto.getContractId());
			PendingInvociecriteria.setInvoiceCreated(Boolean.TRUE);
			PendingInvociecriteria.setPaymentStatus(Boolean.FALSE);
			Long pendingPaymentInvoices = invoiceService.getRowCount(PendingInvociecriteria);
			
			System.out.println("pendingPaymentInvoices " +pendingPaymentInvoices);

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
					if (lastInvoiceDto != null 
							&& lastInvoiceDto.getYear() == Integer.parseInt(getYear.format(toDay))
							&& lastInvoiceDto.getMonth() == Integer.parseInt(getMonth.format(toDay)) ){
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

    
	public void printBill() throws SegesaServiceException {
		double previousReading=getPreviousReading();
		printInvoiceBean.printBill(contractDto, invoiceDto, getUser().getUserName(),equation,previousReading,false,"",false);

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
		List<ReadingDTO> prevouislist = getReadingService().getDataList(prevouisReadingCriteria, 0, 0,
				"readingId", Boolean.TRUE);

		if (prevouislist != null && prevouislist.size() > 0) {
			prevouisReadingDto = prevouislist.get(0);
			return prevouisReadingDto.getReading();
		}else {
			return 0.0;
		}
		
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

	public LazyDataModel<InvoiceDTO> getInvoiceLazyDataModel() {
		return invoiceLazyDataModel;
	}

	public void setInvoiceLazyDataModel(LazyDataModel<InvoiceDTO> invoiceLazyDataModel) {
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

	public int getYear_reading() {
		return year_reading;
	}

	public int getMonth_reading() {
		return month_reading;
	}

	public boolean isNewMeter_reading() {
		return newMeter_reading;
	}

	public int getReading_reading() {
		return reading_reading;
	}

	public int getReadChange_reading() {
		return readChange_reading;
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

	public void setYear_reading(int year_reading) {
		this.year_reading = year_reading;
	}

	public void setMonth_reading(int month_reading) {
		this.month_reading = month_reading;
	}

	public void setNewMeter_reading(boolean newMeter_reading) {
		this.newMeter_reading = newMeter_reading;
	}

	public void setReading_reading(int reading_reading) {
		this.reading_reading = reading_reading;
	}

	public void setReadChange_reading(int readChange_reading) {
		this.readChange_reading = readChange_reading;
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

	public Double getEquation() {
		return equation;
	}

	public void setEquation(Double equation) {
		this.equation = equation;
	}

	public ReadingDTO getReadingDto() {
		return readingDto;
	}

	public void setReadingDto(ReadingDTO readingDto) {
		this.readingDto = readingDto;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public SettingServiceProvider getSettingServiceProvider() {
		return settingServiceProvider;
	}

	public void setSettingServiceProvider(SettingServiceProvider settingServiceProvider) {
		this.settingServiceProvider = settingServiceProvider;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}


	public String getRepTemplete() {
		return repTemplete;
	}


	public void setRepTemplete(String repTemplete) {
		this.repTemplete = repTemplete;
	}


	public ContractService getContractService() {
		return contractService;
	}


	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}


	public List<LocalityDTO> getLocationList() {
		return locationList;
	}


	public void setLocationList(List<LocalityDTO> locationList) {
		this.locationList = locationList;
	}

	public Long getLocalityId() {
		return localityId;
	}

	public void setLocalityId(Long localityId) {
		this.localityId = localityId;
	}

	public Boolean getShowReportDate() {
		return showReportDate;
	}

	public void setShowReportDate(Boolean showReportDate) {
		this.showReportDate = showReportDate;
	}


}
