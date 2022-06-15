package core.bill.comercial.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import core.bill.comercial.model.ContractDTO;

import core.bill.common.util.CustomUtil;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PrintContract implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JasperPrint jasperPrint;
	HttpServletResponse httpServletResponse;
	ServletOutputStream servletOutputStream;
	
	String reportTitle = CustomUtil.getBundlMSGValue("app.contract.cust.reportTitle"); 
	String customerInfoLabel= CustomUtil.getBundlMSGValue("app.fieldset.clientInformation");
	String nameLabel = CustomUtil.getBundlMSGValue("app.name"); 
	String  locationNameLable= CustomUtil.getBundlMSGValue("app.localID");
	String addressLabel= CustomUtil.getBundlMSGValue("app.address");
	String mobilLabel= CustomUtil.getBundlMSGValue("app.mobile1");
    
	String telLabel= CustomUtil.getBundlMSGValue("app.mobile1");
	String  nationalityLabel= CustomUtil.getBundlMSGValue("comm.customer.nationality");
    
	String inspecInfoLabel= CustomUtil.getBundlMSGValue("app.contract.ins.fieldset");
	String  phaseTypeLabel = CustomUtil.getBundlMSGValue("app.insp.phaseType");
	String supplyTypeLabel = CustomUtil.getBundlMSGValue("app.insp.supplyType");
	String  sectorLabel= CustomUtil.getBundlMSGValue("app.insp.sector"); 
	String  conntypeLabel= CustomUtil.getBundlMSGValue("app.insp.connectType");
	String meterNoLabel = CustomUtil.getBundlMSGValue("app.contract.meter.consump"); 
	String homeCodeLabel = CustomUtil.getBundlMSGValue("app.insp.homeCode");
    
    
	String contractInfoLabel= CustomUtil.getBundlMSGValue("app.contract.info.fieldset");
	String  contractDateLabel= CustomUtil.getBundlMSGValue("app.contract.contractDate"); 
	String TypeOfContractLabel= CustomUtil.getBundlMSGValue("app.contract.typeOfContract");
	String  powerLabel= CustomUtil.getBundlMSGValue("app.contract.power");
	String  benefLabel= CustomUtil.getBundlMSGValue("app.contract.beneficiary");
	String directorLabel = CustomUtil.getBundlMSGValue("app.contract.director");
	String circuitLabel= CustomUtil.getBundlMSGValue("app.contract.noCircuit"); 
	String typeLabel= CustomUtil.getBundlMSGValue("app.contract.type"); 
	String digitLabel= CustomUtil.getBundlMSGValue("app.contract.digit");
    
	String coeffLabel= CustomUtil.getBundlMSGValue("app.contract.coefficient");
	String fixedLabel= CustomUtil.getBundlMSGValue("app.contract.fixedPrice");
	String  typePaymentLabel= CustomUtil.getBundlMSGValue("app.contract.typeOfPayment");
	String payPeriodLabel = CustomUtil.getBundlMSGValue("app.contract.payPeriod");
	String statusLabel = CustomUtil.getBundlMSGValue("app.contract.status");  
	String dateAltLabel= CustomUtil.getBundlMSGValue("app.contract.alta");
	String dateCorteLabel= CustomUtil.getBundlMSGValue("app.contract.date.corte"); 
	String dateBajaLabel= CustomUtil.getBundlMSGValue("app.contract.date.baja");
    
	String  moreOptionLabel= CustomUtil.getBundlMSGValue("app.contract.moreInfo.fieldset");
	String tenanLabel= CustomUtil.getBundlMSGValue("app.contract.tenant"); 
	String  authLabel= CustomUtil.getBundlMSGValue("app.contract.authority"); 
	String internalLabel= CustomUtil.getBundlMSGValue("app.contract.interalConsumption"); 
	String  exradoLabel= CustomUtil.getBundlMSGValue("app.contract.exonerado"); 
	String freePenLable= CustomUtil.getBundlMSGValue("app.contract.freePenalization");
	String freePayOrderLabel= CustomUtil.getBundlMSGValue("app.contract.freePayment"); 
	String freeBilOrderLabel= CustomUtil.getBundlMSGValue("app.contract.freeBillOrder"); 
	String cutEneryLabel= CustomUtil.getBundlMSGValue("app.contract.cutEnergy");
	String studyIdLabel= CustomUtil.getBundlMSGValue("app.contract.study.ID");
	String  inspIdLabel= CustomUtil.getBundlMSGValue("app.contract.insp.ID");
    
	String customerCodeLabel= CustomUtil.getBundlMSGValue("app.contract.cust.number");
	String contractCodeLabel= CustomUtil.getBundlMSGValue("app.contract.contractCode");
	String  contractNoLabel= CustomUtil.getBundlMSGValue("app.contract.ContractNo"); 
    
	String commentLabel= CustomUtil.getBundlMSGValue("app.contract.comment"); 
    
	public String printStudProject(ContractDTO contractDTO,String UserName)
	{
		

		try{
			//FacesContext context = FacesContext.getCurrentInstance();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			HashMap<String,Object> map = new HashMap<String,Object> ();
			
			map.put("reportTitle",reportTitle);
			map.put("customerInfoLabel",customerInfoLabel);
			map.put("nameLabel",nameLabel);
			map.put("locationNameLable",locationNameLable);
			map.put("addressLabel",addressLabel);
			map.put("mobilLabel",mobilLabel);
		    
			map.put("telLabel",telLabel);
			map.put("nationalityLabel",nationalityLabel);
		    
			map.put("inspecInfoLabel",inspecInfoLabel);
			map.put("phaseTypeLabel",phaseTypeLabel);
			map.put("supplyTypeLabel",supplyTypeLabel);
		    map.put("sectorLabel",sectorLabel);
		    map.put("conntypeLabel",conntypeLabel);
		    map.put("meterNoLabel",meterNoLabel);
		    map.put("homeCodeLabel",homeCodeLabel);
		    
		    
		    map.put("contractInfoLabel",contractInfoLabel);
		    map.put("contractDateLabel",contractDateLabel);
		    map.put("TypeOfContractLabel",TypeOfContractLabel);
		    map.put("powerLabel",powerLabel);
		    map.put("benefLabel",benefLabel);
		    map.put("directorLabel",directorLabel);
		    map.put("circuitLabel",circuitLabel);
		    map.put("typeLabel",typeLabel);
		    map.put("digitLabel",digitLabel);
		    
		    map.put("coeffLabel",coeffLabel);
		    map.put("fixedLabel",fixedLabel);
		    map.put("typePaymentLabel",typePaymentLabel);
		    map.put("payPeriodLabel",payPeriodLabel);
		    map.put("statusLabel",statusLabel);
		    map.put("dateAltLabel",dateAltLabel);
		    map.put("dateCorteLabel",dateCorteLabel); 
		   map.put("dateBajaLabel",dateBajaLabel);
		    
		   map.put("moreOptionLabel",moreOptionLabel);
		   map.put("tenanLabel",tenanLabel);
		   map.put("authLabel",authLabel);
		    map.put("internalLabel",internalLabel);
		   map.put("exradoLabel",exradoLabel); 
		   map.put("freePenLable",freePenLable);
		   map.put("freePayOrderLabel",freePayOrderLabel); 
		   map.put("freeBilOrderLabel",freeBilOrderLabel );
		    map.put("cutEneryLabel",cutEneryLabel);
		    map.put("studyIdLabel",studyIdLabel);
		    map.put("inspIdLabel",inspIdLabel);
			
		    //************************************************
		    map.put("customerName",contractDTO.getCustomerDTO().getCustomerName());
		    map.put("locationName",contractDTO.getCustomerDTO().getLocalityDTO().getDescription());
		    if(contractDTO.getCustomerDTO().getApplicationDTO()!=null)
		    {
			    map.put("address",contractDTO.getCustomerDTO().getApplicationDTO().getAddress());
			    map.put("nationality",contractDTO.getCustomerDTO().getNationalityDTO().getNationalityName());
			    map.put("telphone",contractDTO.getCustomerDTO().getApplicationDTO().getTelephone1());
			    map.put("mobile",contractDTO.getCustomerDTO().getApplicationDTO().getMobile1());
		    }
		    //****************************************************
		    map.put("phaseTypeName",contractDTO.getPhaseDTO().getDescription());
		    map.put("supplyTypeName",contractDTO.getSupplyDTO().getDescription());
		    map.put("sectorName",contractDTO.getSectorDTO().getDescription());
		    map.put("connectTypeName",contractDTO.getTypeDTO().getDescription());

		    if(contractDTO.getOpIguala()!=null)
		    {
		    if(contractDTO.getOpIguala())
		    {
		    map.put("meterNumber",contractDTO.getConsumption()+"  KW/H");
		    }else
		    {
		    	map.put("meterNumber",contractDTO.getMeterNumber());
		    }
		    }
		    map.put("homeCode",contractDTO.getHomeCode());
		    
		    //**************************************************
		    map.put("power",contractDTO.getPower());
		    map.put("benf",contractDTO.getBeneficiary());
		    map.put("director",contractDTO.getDirection());
		    map.put("circut",contractDTO.getCircuitoNum());
		    map.put("typeName",contractDTO.getContractTypeDTO().getDescription());
		    map.put("typeofContractName",contractDTO.getContractTypeDTO().getDescription());
		    map.put("contractDate",sdf.format(contractDTO.getCreatedDate()));
		    
		    map.put("fixedPriceOp",contractDTO.getFixedPriceOp());
		    map.put("fixedPriceValue",contractDTO.getFixedPriceValue());
		    map.put("digitA",contractDTO.getDigitA());
		    map.put("digitR",contractDTO.getDigitR());
		    map.put("coeA",contractDTO.getCoefficientA());
		    map.put("coeR",contractDTO.getCoefficient_R());
		    map.put("altaDate",sdf.format(contractDTO.getFechaAlta()));
		    map.put("corteDate","");
		    map.put("bajaDate","");
		    
			map.put("statusName",contractDTO.getMeterStatusDTO().getDescription());
			map.put("typePaymentName",contractDTO.getContractPaymentDTO().getDescription());
			map.put("payPeriodName","");
		    //*********************************************
		    
		    //************************************************
		    map.put("tenant",contractDTO.getTenant());
		    map.put("auth",contractDTO.getAuthority());
		    map.put("interlConsum",contractDTO.getInterlConsumption());
		    map.put("exonerado",contractDTO.getExonerado());
		    map.put("freePenaliz",contractDTO.getExemptPenality());
		    map.put("freePaymentOrder",contractDTO.getInvoicePaymentOrder());
		    map.put("freeBillOrder",contractDTO.getInvoiceBillingOrder());
		    map.put("cutEngery",contractDTO.getContractStatus());
		    map.put("commentLabel", commentLabel);
		    //************************************
		    
		    
		    
		    map.put("customerCodeLabel",this.customerCodeLabel);
		    map.put("contractCodeLabel",this.contractCodeLabel);
		    map.put("contractNoLabel",this.contractNoLabel);
		    
		    map.put("contractID",contractDTO.getContractId());
		    map.put("contractNum",contractDTO.getContractCode());
		    map.put("customerID",contractDTO.getCustomerDTO().getCustomerName());
		    map.put("usrName",UserName);
		    
		    
		    ArrayList<ContractDTO> appList = new ArrayList<ContractDTO>();
		    ContractDTO a = new ContractDTO();
		    System.out.println("contractDTO.getStudyId().toString()"+contractDTO.getStudyId().toString());
		    a.setStudyId(contractDTO.getStudyId().toString());
		    a.setInspectorId(contractDTO.getInspectorId());
		    a.setComment(contractDTO.getComment());

		    appList.add(a);
 	     JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(appList);  
	     String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath("/bill/segesa/report/contractReport.jasper");        
	     jasperPrint=JasperFillManager.fillReport(reportPath, map,beanCollectionDataSource);  
	     httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();  
	     httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");  
	     // ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();  
	      byte[] pdfByteArray=JasperExportManager.exportReportToPdf(jasperPrint);
	      
	      httpServletResponse.setContentType("application/pdf;charset=windows-1252");
	      httpServletResponse.setHeader("Content-disposition","inline; filename=Prova.pdf");
	      httpServletResponse.setContentLength(pdfByteArray.length);
	      ServletOutputStream out = httpServletResponse.getOutputStream();
	      out.write(pdfByteArray, 0, pdfByteArray.length);
	      out.flush();
	      out.close(); 
	      FacesContext.getCurrentInstance().responseComplete(); 

	
      
	}catch(Exception exp)
	{
		exp.printStackTrace();
	}finally
	{
	try{
		 
	//st.close();
	 }catch(Exception cn)
	{
		System.out.println(cn);	
	}

			}   
		return null;
	}

}
