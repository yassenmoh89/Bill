package core.bill.comercial.bean;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.bean.ViewScoped;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import core.bill.comercial.model.ContractDashBoardModel;
import core.bill.comercial.model.ContractSearch;
import core.bill.comercial.service.ContractService;
import core.bill.common.util.CustomUtil;
import core.bill.invoice.model.InvoiceSearch;
import core.bill.invoice.service.InvoiceService;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.LocalityDTO;
import core.bill.setting.model.MeterStatusDTO;
import core.bill.setting.model.PhaseDTO;
import core.bill.setting.model.SectorDTO;
import core.bill.setting.serviceProvider.SettingServiceProvider;



@ManagedBean(name="comDashBorad")
@ViewScoped
public class ContractDashBoardImp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{contractService}")
	private ContractService contractService;
	
	@ManagedProperty("#{settingServiceProvider}")
	private SettingServiceProvider  settingServiceProvider ;
	
	@ManagedProperty("#{invoiceService}")
	InvoiceService invoiceService;

	
	private PieChartModel pieModel;
	private DonutChartModel donutModel;
	private BarChartModel barModel;
	private BarChartModel sectorBarModel;
	private BarChartModel invSumBarModel;
	
	private HorizontalBarChartModel hbarModel;
	List<ContractDashBoardModel> chartLocation;
	List<ContractDashBoardModel> meterStatus;
	List<ContractDashBoardModel> phasesTypes;
	List<ContractDashBoardModel> sectors;
	
	List<ContractDashBoardModel> invoiceSum;
	
	List<LocalityDTO> locationList;
	List<MeterStatusDTO> meterList;
	List<PhaseDTO> phasesList;
	List<SectorDTO> sectorList;
	
	private Long totContracts;
	private Long compContracts;
	private Long bajaContracts;
	private Long bajaCompContracts;
	private Long totInvoices;
	private Long compTotInvoices;
	private Long incomeInvoices;
	private Long compIncomeInvoices;
	
	@PostConstruct
    public void init() {
		load();
        createPieModel();
        createDonutModel();
        createBarModel();
        createSectorBarModel();
        createInvSumBarModel();
	}
	
	void load()  {
		try {
			CommonSearchDTO search = new CommonSearchDTO();
			search.setStatus("ACT");
			
			chartLocation=contractService.getNumClientsByLoc("localityDTO.localityId");
			meterStatus=contractService.getNumClientsByLoc("meterStatusDTO.meterStatusId");
			phasesTypes=contractService.getNumClientsByLoc("phaseDTO.phaseId");
			sectors=contractService.getNumClientsByLoc("sectorDTO.sectorId");
			
			locationList=settingServiceProvider.getLocalityDTOList(search);
			meterList=settingServiceProvider.getMeterStatusDTOList(search);
			phasesList=settingServiceProvider.getPhaseDTOList(search);
			sectorList=settingServiceProvider.getSectorDTOList(search);
			
		    getDashBoardData();
			InvoiceSearch invs= new InvoiceSearch();
			invs.setMonth(4);
			invs.setYear(2014);
			
			invoiceSum=invoiceService.getSumOfPendAndIncomeByField("localityId", "amountPaid", "amountPayable", invs);
			

			for(ContractDashBoardModel l:chartLocation) {
				for(LocalityDTO c:locationList) {
					if(c.getLocalityId()==l.getContId()) {
						l.setName(c.getDescription());
					}
				}
			}
			
			for(ContractDashBoardModel l:meterStatus) {
				for(MeterStatusDTO c:meterList) {
					if(c.getMeterStatusId()==l.getContId()) {
						l.setName(c.getDescription());
					}
				}
			}

			for(ContractDashBoardModel l:phasesTypes) {
				for(PhaseDTO c:phasesList) {
					if(c.getPhaseId()==l.getContId()) {
						l.setName(c.getDescription());
					}
				}
			}
			
			for(ContractDashBoardModel l:sectors) {
				for(SectorDTO c:sectorList) {
					if(c.getSectorId()==l.getContId()) {
						l.setName(c.getDescription());
					}
				}
			}
			
			for(ContractDashBoardModel l:invoiceSum) {
				for(LocalityDTO c:locationList) {
					if(c.getLocalityId()==l.getContId()) {
						l.setName(c.getDescription());
					}
				}
			}
			

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	 private void getDashBoardData() {
		try {
			
			ContractSearch sea = new ContractSearch();
			sea.setMeterStatusId((long) 1);
			totContracts=contractService.getCountOfContract(sea);
			System.out.println("contractService"+totContracts);
			
			ContractSearch sea1 = new ContractSearch();
			String da="01/09/2014";
			Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(da);
			
			String da2="01/08/2014";
			Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(da2);
			
			sea1.setCreatedDate(date1);
			Long curentMonth=contractService.getCountOfContract(sea1);
			sea1.setCreatedDate(date2);
			Long prevmonth=contractService.getCountOfContract(sea1);
			Long x=curentMonth-prevmonth;
			compContracts=x;
			
			sea.setMeterStatusId((long) 2);		
			bajaContracts=contractService.getCountOfContract(sea);
			
			sea.setMeterStatusId((long) 2);
			sea.setCreatedDate(date1);
			Long curent=contractService.getCountOfContract(sea);
			
			sea.setMeterStatusId((long) 2);
			sea.setCreatedDate(date2);
			Long prev=contractService.getCountOfContract(sea);
			
			bajaCompContracts=curent-prev;
			
			InvoiceSearch inv = new InvoiceSearch();
			inv.setCreatedDate(date1);
			totInvoices=invoiceService.getCountOfInvoices(inv);
			
			inv.setCreatedDate(date2);
			Long x2=invoiceService.getCountOfInvoices(inv);
			compTotInvoices=totInvoices-x2;

			
			InvoiceSearch invs2= new InvoiceSearch();
			invs2.setPaymentStatus(Boolean.TRUE);
			invs2.setPaidDate(date1);
			Double xc=invoiceService.getSumAmount("amountPaid", invs2);
			incomeInvoices=xc.longValue();
			
			invs2.setPaidDate(date2);
			Double xc2=invoiceService.getSumAmount("amountPaid", invs2);
			compIncomeInvoices= xc.longValue()-xc2.longValue();
			System.out.println("getSumAmount"+incomeInvoices);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public void createHorizontalBarModel() {
	        hbarModel = new HorizontalBarChartModel();
	        ChartData data = new ChartData();

	        HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
	        hbarDataSet.setLabel(CustomUtil.getBundlMSGValue("dashboard.clients.by.locations"));

	        List<Number> values = new ArrayList<>();
	        for(ContractDashBoardModel l:chartLocation) {
	        	 values.add(l.getContId());
	        }
	       
	        hbarDataSet.setData(values);

	        List<String> bgColor = new ArrayList<>();
	        bgColor.add("rgba(255, 99, 132, 0.2)");
	        bgColor.add("rgba(255, 159, 64, 0.2)");
	        bgColor.add("rgba(255, 205, 86, 0.2)");
	        bgColor.add("rgba(75, 192, 192, 0.2)");
	        bgColor.add("rgba(54, 162, 235, 0.2)");
	        bgColor.add("rgba(153, 102, 255, 0.2)");
	        bgColor.add("rgba(201, 203, 207, 0.2)");
	        hbarDataSet.setBackgroundColor(bgColor);

	        List<String> borderColor = new ArrayList<>();
	        borderColor.add("rgb(255, 99, 132)");
	        borderColor.add("rgb(255, 159, 64)");
	        borderColor.add("rgb(255, 205, 86)");
	        borderColor.add("rgb(75, 192, 192)");
	        borderColor.add("rgb(54, 162, 235)");
	        borderColor.add("rgb(153, 102, 255)");
	        borderColor.add("rgb(201, 203, 207)");
	        hbarDataSet.setBorderColor(borderColor);
	        hbarDataSet.setBorderWidth(1);

	        data.addChartDataSet(hbarDataSet);

	        List<String> labels = new ArrayList<>();
	        for(ContractDashBoardModel l:chartLocation) {
	        	labels.add(l.getName());
	        }
	        

	        data.setLabels(labels);
	        hbarModel.setData(data);

	        //Options
	        BarChartOptions options = new BarChartOptions();
	        CartesianScales cScales = new CartesianScales();
	        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
	        linearAxes.setOffset(true);
	        CartesianLinearTicks ticks = new CartesianLinearTicks();
	        ticks.setBeginAtZero(true);
	        linearAxes.setTicks(ticks);
	        cScales.addXAxesData(linearAxes);
	        options.setScales(cScales);

	        Title title = new Title();
	        title.setDisplay(true);
	        title.setText(CustomUtil.getBundlMSGValue("dashboard.clients.by.locations"));
	        options.setTitle(title);

	        hbarModel.setOptions(options);
	    }
	
    public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel(CustomUtil.getBundlMSGValue("dashboard.clients.by.locations"));

        List<Number> values = new ArrayList<>();
        for(ContractDashBoardModel l:chartLocation) {
        	values.add(l.getCount());
        }
        barDataSet.setData(values);


        List<String> bgColor = new ArrayList<>();

        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
 		
		 borderColor.add("rgb(255, 99, 132)"); borderColor.add("rgb(255, 159, 64)");
		 borderColor.add("rgb(255, 205, 86)"); borderColor.add("rgb(75, 192, 192)");
		 borderColor.add("rgb(54, 162, 235)"); borderColor.add("rgb(153, 102, 255)");
		 borderColor.add("rgb(201, 203, 207)");
		       
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        for(ContractDashBoardModel l:chartLocation) {
        	labels.add(l.getName());
        }

        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel.setOptions(options);
    }
    
    public void createSectorBarModel() {
        sectorBarModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel(CustomUtil.getBundlMSGValue("dashboard.clients.by.sectors"));

        List<Number> values = new ArrayList<>();
        for(ContractDashBoardModel l:sectors) {
        	values.add(l.getCount());
        }
        barDataSet.setData(values);


        List<String> bgColor = new ArrayList<>();

        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
 		
		 borderColor.add("rgb(255, 99, 132)"); borderColor.add("rgb(255, 159, 64)");
		 borderColor.add("rgb(255, 205, 86)"); borderColor.add("rgb(75, 192, 192)");
		 borderColor.add("rgb(54, 162, 235)"); borderColor.add("rgb(153, 102, 255)");
		 borderColor.add("rgb(201, 203, 207)");
		       
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        for(ContractDashBoardModel l:sectors) {
        	labels.add(l.getName());
        }

        data.setLabels(labels);
        sectorBarModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        sectorBarModel.setOptions(options);
    }
	
	private void createPieModel() {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();

        for(ContractDashBoardModel f:meterStatus)
        {
        	 values.add(f.getCount());
        }
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgba(112, 252, 90,0.2)");
        bgColors.add("rgb(241, 235, 36)");
        bgColors.add("rgba(241, 252, 90,0.2)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        
        List<String> borderColor = new ArrayList<>();
 		
		 borderColor.add("rgba(112, 252, 90, 0.2)"); borderColor.add("rgba(75, 192, 192,0.2)");
		 borderColor.add("rgba(241, 252, 90,0.2)"); 
		       
        dataSet.setBorderColor(borderColor);
        
        List<String> labels = new ArrayList<>();

        for(ContractDashBoardModel f:meterStatus)
        {
        	labels.add(f.getName());
        }
        data.setLabels(labels);
        
        pieModel.setData(data);
    }

    public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        for(ContractDashBoardModel f:phasesTypes)
        {
        	values.add(f.getCount());
        }

        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgba(255, 99, 132, 0.2)");
        bgColors.add("rgba(75, 192, 192, 0.2)");
        bgColors.add("rgba(255, 205, 86,0.2)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        
        List<String> borderColor = new ArrayList<>();
 		
		 borderColor.add("rgba(255, 99, 132, 0.2)"); borderColor.add("rgba(75, 192, 192,0.2)");
		 borderColor.add("rgb(255, 205, 86,0.2)"); 
		       
        dataSet.setBorderColor(borderColor);
        
        List<String> labels = new ArrayList<>();
        for(ContractDashBoardModel f:phasesTypes)
        {
        	labels.add(f.getName());
        }

        data.setLabels(labels);

        donutModel.setData(data);
    }

    public void createInvSumBarModel() {
    	invSumBarModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel(CustomUtil.getBundlMSGValue("dashboard.paid.amount"));
        barDataSet.setBackgroundColor("rgba(255, 99, 132, 0.2)");
        barDataSet.setBorderColor("rgb(255, 99, 132)");
        barDataSet.setBorderWidth(1);
        
        List<Number> values = new ArrayList<>();
        
        for(ContractDashBoardModel f:invoiceSum)
        {
        	BigDecimal sum1 = new BigDecimal(f.getFirstSum()).setScale(2, RoundingMode.HALF_UP);
        	   values.add(sum1);
        }
       
        barDataSet.setData(values);
             
        BarChartDataSet barDataSet2 = new BarChartDataSet();
        barDataSet2.setLabel(CustomUtil.getBundlMSGValue("dashboard.pendent.amount"));
        barDataSet2.setBackgroundColor("rgba(255, 159, 64, 0.2)");
        barDataSet2.setBorderColor("rgb(255, 159, 64)");
        barDataSet2.setBorderWidth(1);

        
        
        List<Number> values2 = new ArrayList<>();
        for(ContractDashBoardModel f:invoiceSum)
        {
        	BigDecimal bd = new BigDecimal(f.getSecondsum()).setScale(2, RoundingMode.HALF_UP);
        	values2.add(bd);
        }
        barDataSet2.setData(values2);

        data.addChartDataSet(barDataSet);
        data.addChartDataSet(barDataSet2);

        List<String> labels = new ArrayList<>();
        for(ContractDashBoardModel f:invoiceSum)
        {
        	labels.add(f.getName());
        }
        data.setLabels(labels);
        invSumBarModel.setData(data);
        

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
       
        Title title = new Title();
        title.setDisplay(true);
        title.setText(CustomUtil.getBundlMSGValue("dashboard.clients.by.invoices.by.localitys"));
        options.setTitle(title);
      
        invSumBarModel.setOptions(options);
    }
	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	public DonutChartModel getDonutModel() {
		return donutModel;
	}

	public void setDonutModel(DonutChartModel donutModel) {
		this.donutModel = donutModel;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public HorizontalBarChartModel getHbarModel() {
		return hbarModel;
	}

	public void setHbarModel(HorizontalBarChartModel hbarModel) {
		this.hbarModel = hbarModel;
	}

	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	public SettingServiceProvider getSettingServiceProvider() {
		return settingServiceProvider;
	}

	public void setSettingServiceProvider(SettingServiceProvider settingServiceProvider) {
		this.settingServiceProvider = settingServiceProvider;
	}

	public BarChartModel getSectorBarModel() {
		return sectorBarModel;
	}

	public void setSectorBarModel(BarChartModel sectorBarModel) {
		this.sectorBarModel = sectorBarModel;
	}

	public InvoiceService getInvoiceService() {
		return invoiceService;
	}

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	public BarChartModel getInvSumBarModel() {
		return invSumBarModel;
	}

	public void setInvSumBarModel(BarChartModel invSumBarModel) {
		this.invSumBarModel = invSumBarModel;
	}

	public Long getTotContracts() {
		return totContracts;
	}

	public void setTotContracts(Long totContracts) {
		this.totContracts = totContracts;
	}

	public Long getCompContracts() {
		return compContracts;
	}

	public void setCompContracts(Long compContracts) {
		this.compContracts = compContracts;
	}

	public Long getBajaContracts() {
		return bajaContracts;
	}

	public void setBajaContracts(Long bajaContracts) {
		this.bajaContracts = bajaContracts;
	}

	public Long getBajaCompContracts() {
		return bajaCompContracts;
	}

	public void setBajaCompContracts(Long bajaCompContracts) {
		this.bajaCompContracts = bajaCompContracts;
	}

	public Long getTotInvoices() {
		return totInvoices;
	}

	public void setTotInvoices(Long totInvoices) {
		this.totInvoices = totInvoices;
	}

	public Long getCompTotInvoices() {
		return compTotInvoices;
	}

	public void setCompTotInvoices(Long compTotInvoices) {
		this.compTotInvoices = compTotInvoices;
	}

	public Long getIncomeInvoices() {
		return incomeInvoices;
	}

	public void setIncomeInvoices(Long incomeInvoices) {
		this.incomeInvoices = incomeInvoices;
	}

	public Long getCompIncomeInvoices() {
		return compIncomeInvoices;
	}

	public void setCompIncomeInvoices(Long compIncomeInvoices) {
		this.compIncomeInvoices = compIncomeInvoices;
	}	
	

	
}
