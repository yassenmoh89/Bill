package core.bill.invoice.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import core.bill.comercial.model.ContractDTO;
import core.bill.comercial.service.serviceProvider.CommericalServiceProvider;
import core.bill.common.bean.GenericBean;
import core.bill.common.exception.SegesaServiceException;
import core.bill.common.util.CustomUtil;
import core.bill.common.util.SearchPagination;
import core.bill.common.util.UtilConstant;
import core.bill.invoice.model.InvoiceDTO;
import core.bill.invoice.model.InvoiceSearch;
import core.bill.invoice.model.ReadingDTO;
import core.bill.invoice.service.InvoiceService;
import core.bill.invoice.service.ReadingService;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.PriceDTO;
import core.bill.setting.serviceProvider.SettingServiceProvider;



@ManagedBean(name = "invoiceBean")
@ViewScoped
public class InvoiceBean extends GenericBean<InvoiceDTO, InvoiceSearch> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(InvoiceBean.class);
	
	@ManagedProperty("#{commericalServiceProvider}")
	CommericalServiceProvider commericalServiceProvider;

	@ManagedProperty("#{invoiceService}")
	InvoiceService invoiceService;

	@ManagedProperty("#{readingService}")
	ReadingService readingService;

	@ManagedProperty("#{settingServiceProvider}")
	private SettingServiceProvider settingServiceProvider;

	private LazyDataModel<InvoiceDTO> invoiceLazyDataModel;
	
	private static PrintInvoiceBean printInvoiceBean;

	private ContractDTO contractDto = new ContractDTO();
	private Long contractId;
	private InvoiceSearch criterion;
	private double requiredAmount;
	private double penaliz;
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
	
	private Boolean flagPenalityBtn=false;
	private Boolean flagTaxBtn=false;
	
	
	

	public String searchcontract() {
		try {

			if (getUser() == null) {

			} else {
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
					// ------------------call Calculate Penalty
					calculatePenalty();
					printInvoiceBean = new PrintInvoiceBean();
				}
				return null;
			}

		} catch (Exception e) {
			logger.error("InvoiceBean : searchcontract " + e);
			e.printStackTrace();
		}
		return null;
	}

	// ----------------------------------------------------------------
	public String addReading() {

		try {

			if (this.reading_reading == 0) {
				errorMessage(CustomUtil.getBundlMSGValue("inv.invoice.noteReading"));
				return null;
			}
			ReadingDTO prevouisReadingDto = null;
			InvoiceSearch readingCriteria = new InvoiceSearch();
			readingCriteria.setContractId(contractDto.getContractId());
			readingCriteria.setMonth(month_reading);
			readingCriteria.setYear(year_reading);
			List<ReadingDTO> list = getReadingService().getDataList(readingCriteria, 0, 0, "readingId", Boolean.TRUE);
			if (list != null && list.size() > 0) {
				errorMessage(CustomUtil.getBundlMSGValue("exist.records"));
				return null;
			}

			if (contractDto.getIguala()) {

				readingDto = new ReadingDTO();

				readingDto.setContractId(contractDto.getContractId());
				readingDto.setLocalityId(contractDto.getLocalityDTO().getLocalityId());
				readingDto.setCustomerId(contractDto.getCustomerDTO().getCustomerId());
				readingDto.setYear(year_reading);
				readingDto.setMonth(month_reading);
				readingDto.setReading(reading_reading);
				readingDto.setLchange(readChange_reading);
				readingDto.setCreatedDate(new Date());
				readingDto.setIquala(contractDto.getIguala());
				readingDto.setConsumption((reading_reading - readChange_reading) * equation);
				readingDto.setCreatedBy((getUser().getUserId()).toString());

				readingDto = getReadingService().saveModel(readingDto);

				invoiceDto = new InvoiceDTO();
				invoiceDto.setCreatedDate(new Date());
				invoiceDto.setCreatedBy((getUser().getUserId()).toString());
				invoiceDto.setReadingDTO(readingDto);

				invoiceDto.setYear(readingDto.getYear());
				invoiceDto.setMonth(readingDto.getMonth());

				invoiceDto.setContractId(contractDto.getContractId());
				invoiceDto.setCustomerId(contractDto.getCustomerDTO().getCustomerId());
				invoiceDto.setLocalityId(contractDto.getLocalityDTO().getLocalityId());
				invoiceDto.setPaymentStatus(Boolean.FALSE);
				invoiceDto.setConsumption(readingDto.getConsumption());
				invoiceDto.setCreatedInvoice(Boolean.FALSE);
				
				getInvoiceService().saveModel(invoiceDto);

				invoiceDto = null;
				readingDto = null;
				readChange_reading = 0;
				search();
				infoMessage(CustomUtil.getBundlMSGValue("save.succ"));

				return null;

			}

			if (this.newMeter_reading) {

				if ((this.reading_reading < this.readChange_reading)) {

					infoMessage(CustomUtil.getBundlMSGValue("inv.invoice.readingLessChg"));
					return null;
				}

				readingDto = new ReadingDTO();
				readingDto.setContractId(contractDto.getContractId());
				readingDto.setLocalityId(contractDto.getLocalityDTO().getLocalityId());
				readingDto.setCustomerId(contractDto.getCustomerDTO().getCustomerId());
				readingDto.setYear(year_reading);
				readingDto.setMonth(month_reading);
				readingDto.setReading(reading_reading);
				readingDto.setLchange(readChange_reading);
				readingDto.setCreatedDate(new Date());
				readingDto.setIquala(contractDto.getIguala());
				readingDto.setConsumption((reading_reading - readChange_reading) * equation);
				readingDto.setCreatedBy((getUser().getUserId()).toString());

				readingDto = getReadingService().saveModel(readingDto);

				invoiceDto = new InvoiceDTO();
				invoiceDto.setCreatedDate(new Date());
				invoiceDto.setCreatedBy((getUser().getUserId()).toString());
				invoiceDto.setReadingDTO(readingDto);

				invoiceDto.setYear(readingDto.getYear());
				invoiceDto.setMonth(readingDto.getMonth());

				invoiceDto.setContractId(contractDto.getContractId());
				invoiceDto.setCustomerId(contractDto.getCustomerDTO().getCustomerId());
				invoiceDto.setLocalityId(contractDto.getLocalityDTO().getLocalityId());
				invoiceDto.setPaymentStatus(Boolean.FALSE);
				invoiceDto.setConsumption(readingDto.getConsumption());
				invoiceDto.setCreatedInvoice(Boolean.FALSE);

				getInvoiceService().saveModel(invoiceDto);

				invoiceDto = null;
				readingDto = null;
				readChange_reading = 0;
				search();
				infoMessage(CustomUtil.getBundlMSGValue("save.succ"));

				return null;

			} else {

				int prevouisYear = 0;
				int prevouisMonth = 0;

				if (month_reading == 1) {
					prevouisMonth = 12;
					prevouisYear = year_reading - 1;
				} else {
					prevouisMonth = month_reading - 1;

				}

				InvoiceSearch prevouisReadingCriteria = new InvoiceSearch();
				prevouisReadingCriteria.setContractId(contractDto.getContractId());
				prevouisReadingCriteria.setMonth(prevouisMonth);
				prevouisReadingCriteria.setYear(prevouisYear);
				List<ReadingDTO> prevouislist = getReadingService().getDataList(prevouisReadingCriteria, 0, 0,
						"readingId", Boolean.TRUE);

				if (prevouislist != null && prevouislist.size() > 0) {
					prevouisReadingDto = prevouislist.get(0);
				}

				if (prevouisReadingDto.getReading() > this.reading_reading
						|| (this.reading_reading < this.readChange_reading)) {

					errorMessage(CustomUtil.getBundlMSGValue("inv.invoice.readingLess"));
					return null;

				}

				readingDto = new ReadingDTO();
				readingDto.setContractId(contractDto.getContractId());
				readingDto.setLocalityId(contractDto.getLocalityDTO().getLocalityId());
				readingDto.setCustomerId(contractDto.getCustomerDTO().getCustomerId());
				readingDto.setYear(year_reading);
				readingDto.setMonth(month_reading);
				readingDto.setReading(reading_reading);
				readingDto.setLchange(readChange_reading);
				readingDto.setCreatedDate(new Date());
				readingDto.setIquala(contractDto.getIguala());

				if (prevouisReadingDto.getReading() > this.reading_reading) {
					readingDto.setConsumption((reading_reading - readChange_reading) * equation);
				} else {
					readingDto.setConsumption(
							((this.reading_reading - this.readChange_reading) - prevouisReadingDto.getReading())
									* equation);
				}

				readingDto.setCreatedBy((getUser().getUserId()).toString());

				readingDto = getReadingService().saveModel(readingDto);

				invoiceDto = new InvoiceDTO();
				invoiceDto.setCreatedDate(new Date());
				invoiceDto.setCreatedBy((getUser().getUserId()).toString());
				invoiceDto.setReadingDTO(readingDto);

				invoiceDto.setYear(readingDto.getYear());
				invoiceDto.setMonth(readingDto.getMonth());

				invoiceDto.setContractId(contractDto.getContractId());
				invoiceDto.setCustomerId(contractDto.getCustomerDTO().getCustomerId());
				invoiceDto.setLocalityId(contractDto.getLocalityDTO().getLocalityId());
				invoiceDto.setPaymentStatus(Boolean.FALSE);
				invoiceDto.setConsumption(readingDto.getConsumption());
				invoiceDto.setCreatedInvoice(Boolean.FALSE);

				getInvoiceService().saveModel(invoiceDto);

				invoiceDto = null;
				readingDto = null;
				readChange_reading = 0;
				search();
				infoMessage(CustomUtil.getBundlMSGValue("save.succ"));

				return null;

			}
		} catch (Exception e) {

			e.printStackTrace();

			errorMessage(CustomUtil.getBundlMSGValue("save.fail"));
		}

		return null;
	}

	// -------------------------------------------------------------
	public String addFactura() {

		double balance = 0.0; // balance
		int mContador = 0;
		int tax = 0;
		int power = 0;
		//int penalty = 0;

		try {

			if (invoiceDto == null || (invoiceDto.getYear() == 0) || (invoiceDto.getMonth() == 0)) {
				errorMessage(CustomUtil.getBundlMSGValue("exist.records"));
				return null;

			}

			CommonSearchDTO priceSearch = new CommonSearchDTO();
			List<PriceDTO> listprice = settingServiceProvider.getPriceDTOList(priceSearch);

			if (contractDto.getFixedPriceOp()) {

				price = contractDto.getFixedPriceValue();
			} else {
				for (PriceDTO priceDto : listprice) {
					// **************** Tax price**********************
					if ((Double.valueOf(priceDto.getMinimum()) <= invoiceDto.getConsumption())
							&& (Double.valueOf(priceDto.getMaximum()) >= invoiceDto.getConsumption())) {
						price = priceDto.getPrice().intValue();
					}
				}

			}

			// ************** fase condition *****************
			mContador = contractDto.getPhaseDTO().getCost().intValue();

			for (PriceDTO priceDto : listprice) {
				// **************** Tax price**********************
				if (priceDto.getRank().equals("IVA")) {
					tax = priceDto.getPrice().intValue();
				}
				// **************** Power price**********************
				if (priceDto.getRank().equals("101")) {
					power = priceDto.getPrice().intValue();
				}
			}

			InvoiceDTO lastInvoice = invoiceService.getLastInvoice(contractDto.getContractId());

			if (lastInvoice != null) {
				System.out.println("Bean ==== balance = : "+lastInvoice.getRiamount());
				balance = lastInvoice.getRiamount(); // balance
			}

			//penalty = 0;
			//////////////////////////////////////////////////////////////////
			invoiceDto.setCreatedInvoice(Boolean.TRUE);
			invoiceDto.setCreatedDate(new Date());
			invoiceDto.setPaymentStatus(Boolean.FALSE);
			invoiceDto.setPenality(0.0);
			invoiceDto.setPrice(price);
			invoiceDto.setGrossAmount(price * invoiceDto.getConsumption());
			invoiceDto.setDiscountRate(getDiscount());
			invoiceDto.setDiscount((invoiceDto.getGrossAmount() * getDiscount()) / 100);
			invoiceDto.setAmount(invoiceDto.getGrossAmount() - invoiceDto.getDiscount());
			invoiceDto.setMcounter(Double.valueOf(mContador));
			invoiceDto.setPower(Double.valueOf(power));

			if (contractDto.getExonerado()) {
				invoiceDto.setTax(0.0);
			} else {
				invoiceDto.setTax(
						((invoiceDto.getAmount() + invoiceDto.getMcounter() + invoiceDto.getPower()) * (tax)) / 100);

			}

			invoiceDto.setAmountPaid(0.0);
			invoiceDto.setAmountPayable(
					invoiceDto.getAmount() + invoiceDto.getMcounter() + invoiceDto.getPower() + invoiceDto.getTax()); //// pendient
			invoiceDto.setRgrossAmount(0.0);
			invoiceDto.setRiamount(0.0);

			if (balance > 0) {
				if ((balance) > (invoiceDto.getAmountPayable())) {

					invoiceDto.setPaymentStatus(Boolean.TRUE);
					invoiceDto.setRgrossAmount(balance);
					invoiceDto.setRiamount(balance - invoiceDto.getAmountPayable());
					invoiceDto.setAmountPaid(invoiceDto.getAmountPayable());
					invoiceDto.setAmountPayable(0.0);
					invoiceDto.setPaidDate(new Date());
					invoiceDto.setPaidBy("SYS");

				} else {

					invoiceDto.setPaymentStatus(Boolean.FALSE);
					invoiceDto.setRgrossAmount(balance);
					invoiceDto.setRiamount(0.0);
					invoiceDto.setAmountPayable(invoiceDto.getAmountPayable() - balance);
				}

			}

			invoiceDto.setCreatedBy(getUser().getUserId().toString());
			invoiceDto.getReadingDTO().setInvoiceCreated(Boolean.TRUE);
			invoiceDto.setUpdatedDate(new Date());
			invoiceDto.setUpdatedBy(getUser().getUserId().toString());

			invoiceService.updateModel(invoiceDto);
			readingService.updateModel(invoiceDto.getReadingDTO());
			////////////////////////////////////////////
			infoMessage(CustomUtil.getBundlMSGValue("se.invoice.create"));
			return null;

		} catch (Exception e) {
			logger.error("invoice Bean : " + e);
			e.printStackTrace();
		}

		return null;
	}

	// --------------------------
	public void selectRow(InvoiceDTO invoiceSelected) {
		invoiceDto = invoiceSelected ;
		this.flagPenalityBtn=false;
		this.flagTaxBtn=false;
		
			if(!invoiceDto.getPaymentStatus() && penaliz>0) {
				this.flagPenalityBtn=true;
			}
			
			if(invoiceDto.getTax()==null) {
				
			}else if(!invoiceDto.getPaymentStatus() && invoiceDto.getCreatedInvoice()) {
				this.flagTaxBtn=true;
				System.out.println("wwwwww :"+flagTaxBtn);
			}	
		
	}

	// -------------------------------------------------------------
	public String search() {
		try {
			logger.info("====================- Enter Search role ====================");
			
			//-------------------------get total 
			//getPendingTotal()
			Double tempTotal = invoiceService.getPendingTotal(contractDto.getContractId());
			
			if(tempTotal!=null)
			{
				requiredAmount = tempTotal ;
			}
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

			System.out.println("total Rows : " + totalRows);

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
	
	public void cancelTax() throws SegesaServiceException {
		if(invoiceDto!=null) {
			if(invoiceDto.getTax()!=null) 
			{
			invoiceDto.setAmountPayable(invoiceDto.getAmountPayable()-invoiceDto.getTax());
			invoiceDto.setTax(null);
			invoiceService.updateModel(invoiceDto);
			infoMessage(CustomUtil.getBundlMSGValue("delete.succ"));		
			search();
			}
			else {
				infoMessage(CustomUtil.getBundlMSGValue("invoice.edit.cancel.tax.fail"));				
			}
		}
		
	}
	
	public void cancelInvoice() throws SegesaServiceException {
		if(invoiceDto!=null) {
			
			invoiceDto.setCreatedInvoice(Boolean.FALSE);
			invoiceDto.setCreatedDate(null);
			invoiceDto.setPaymentStatus(Boolean.FALSE);
			invoiceDto.setPenality(null);
			invoiceDto.setPrice(null);
			invoiceDto.setGrossAmount(null);
			invoiceDto.setDiscountRate(null);
			invoiceDto.setDiscount(null);
			invoiceDto.setAmount(null);
			invoiceDto.setMcounter(null);
			invoiceDto.setPower(null);
			invoiceDto.setTax(null);
			invoiceDto.setAmountPaid(null);
			invoiceDto.setAmountPayable(null); //// pendient
			invoiceDto.setRgrossAmount(null);
			invoiceDto.setRiamount(null);
			invoiceDto.getReadingDTO().setInvoiceCreated(Boolean.FALSE);
			invoiceDto.getReadingDTO().setCreatedDate(null);
			invoiceDto.setUpdatedBy(getUser().getUserId().toString());
			invoiceDto.setUpdatedDate(new Date());
			
			invoiceService.updateModel(invoiceDto);
			infoMessage(CustomUtil.getBundlMSGValue("delete.succ"));		
			search();
		}
		
	}
	
	public void cancelReading() throws SegesaServiceException {
		if(invoiceDto!=null) {	
			ReadingDTO readingDTO=invoiceDto.getReadingDTO();
			invoiceService.deleteModel(invoiceDto);
			readingService.deleteModel(readingDTO);
			infoMessage(CustomUtil.getBundlMSGValue("delete.succ"));		
			search();
		}
		
	}
	
	public void cancelPenality() throws SegesaServiceException {
		if(invoiceDto!=null) {	
			if(!invoiceDto.getPaymentStatus() && penaliz>0) {
				
				invoiceDto.setEliminable(Boolean.TRUE);
				invoiceDto.setElimiableDate(new Date());
				invoiceDto.setUpdatedBy(getUser().getUserId().toString());
				invoiceDto.setUpdatedDate(new Date());
				invoiceService.updateModel(invoiceDto);
			search();
			infoMessage(CustomUtil.getBundlMSGValue("delete.succ"));		
			search();
			}
			else {
				infoMessage(CustomUtil.getBundlMSGValue("delete.succ"));
			}
			
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

	public Boolean getFlagPenalityBtn() {
		return flagPenalityBtn;
	}

	public void setFlagPenalityBtn(Boolean flagPenalityBtn) {
		this.flagPenalityBtn = flagPenalityBtn;
	}

	public Boolean getFlagTaxBtn() {
		return flagTaxBtn;
	}

	public void setFlagTaxBtn(Boolean flagTaxBtn) {
		this.flagTaxBtn = flagTaxBtn;
	}

}
