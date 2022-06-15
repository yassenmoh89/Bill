package core.bill.invoice.bean;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import core.bill.comercial.model.ContractDTO;
import core.bill.comercial.model.CustomerDTO;
import core.bill.common.util.CustomUtil;

import core.bill.invoice.model.InvoiceDTO;
import core.bill.invoice.model.OtherPaymentDTO;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PrintInvoiceBean implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	JasperPrint jasperPrint;
	HttpServletResponse httpServletResponse;
	ServletOutputStream servletOutputStream;
	
	String customerNameLabel = CustomUtil.getBundlMSGValue("app.contract.cust.name");
	String customerIDLabel = CustomUtil.getBundlMSGValue("app.contract.cust.number");
	String addressLabel = CustomUtil.getBundlMSGValue("app.address");
	String sectorLabel = CustomUtil.getBundlMSGValue("se.sector.description");
	String meterNoLabel = CustomUtil.getBundlMSGValue("app.insp.meterNum");
	String contractNoLabel = CustomUtil.getBundlMSGValue("rep.contractNo");
	String localIDLabel = CustomUtil.getBundlMSGValue("app.insp.localID");
	String invoiceLabel = CustomUtil.getBundlMSGValue("rep.invoice.reForma");
	String invoiceNoLabel = CustomUtil.getBundlMSGValue("cash.comp.invoice");
	String oldReadLabel = CustomUtil.getBundlMSGValue("inv.invoice.oldReading");
	String newReadLabel = CustomUtil.getBundlMSGValue("rep.newReading");
	String consumptionLabel = CustomUtil.getBundlMSGValue("inv.invoice.consumption");
	String priceLabel = CustomUtil.getBundlMSGValue("inv.invoice.price");
	String discountPrecLabel = CustomUtil.getBundlMSGValue("inv.invoice.discount%");
	String discountLabel = CustomUtil.getBundlMSGValue("inv.invoice.discount");
	String subToalLabel = CustomUtil.getBundlMSGValue("rep.subToal");
	String totalLabel = CustomUtil.getBundlMSGValue("rep.total");
	String lineTotal = CustomUtil.getBundlMSGValue("rep.lineTotal");
	String generalTotalLabel = CustomUtil.getBundlMSGValue("rep.invoiceTotal");
	String powerLabel = CustomUtil.getBundlMSGValue("inv.invoice.power");
	String taxLabel = CustomUtil.getBundlMSGValue("rep.tax");
	String penaltyLabel = CustomUtil.getBundlMSGValue("cash.panality");
	String paidAmountLabel = CustomUtil.getBundlMSGValue("rep.paidAmount");
	String remainLable = CustomUtil.getBundlMSGValue("rep.remainingAmount");
	String paymentDateLabel = CustomUtil.getBundlMSGValue("rep.paymentDate");
	String cashierNameLabel = CustomUtil.getBundlMSGValue("rep.cashierName");
	String descriptionLabel = CustomUtil.getBundlMSGValue("rep.descrip");
	String amountOfActiveEnergyLabel = CustomUtil.getBundlMSGValue("rep.amountOfActiveEnergy");
	String maintenanceLabel = CustomUtil.getBundlMSGValue("app.insp.maintenance");
	String creditLabel = CustomUtil.getBundlMSGValue("rep.invoice.credit");
	String payLabel = CustomUtil.getBundlMSGValue("rep.requiredPay");
	String coeLabel = CustomUtil.getBundlMSGValue("inv.invoice.confeient");
	String balanceLable= CustomUtil.getBundlMSGValue("rep.invoice.balance");
	String fromDateLabel= CustomUtil.getBundlMSGValue("rep.dateFrom");
	String toDateLabel= CustomUtil.getBundlMSGValue("rep.dateTo");
	String yearLabel=CustomUtil.getBundlMSGValue("inv.invoice.year");
	String monthLabel=CustomUtil.getBundlMSGValue("inv.invoice.month");
	String incomLabel = CustomUtil.getBundlMSGValue("inv.invoice.income");
	String reportTitleCustomerBalance=CustomUtil.getBundlMSGValue("rep.customerBalanceUpaied");//paid
	String reportTitleCustomerHistory=CustomUtil.getBundlMSGValue("rep.customerBalanceUn");
	String dateLabel=CustomUtil.getBundlMSGValue("inv.invoice.pre.date");
	
	/*Other Payment Invoice*/
	String invoiceTitleRep=CustomUtil.getBundlMSGValue("rep.invoice");
	String paymentIDRep=CustomUtil.getBundlMSGValue("rep.paymentID");
	String paymentDateRep=CustomUtil.getBundlMSGValue("rep.paymentDate");
	String customerRep=CustomUtil.getBundlMSGValue("rep.customerID");
	String cashierNameRep=CustomUtil.getBundlMSGValue("rep.cashierName");
	String categoryRep=CustomUtil.getBundlMSGValue("rep.categoryName");
	String incomeAmountRep=CustomUtil.getBundlMSGValue("rep.ingross.amount");
	String totalRep=CustomUtil.getBundlMSGValue("rep.total");
	String subTotalRep=CustomUtil.getBundlMSGValue("rep.subToal");
	String taxRep=CustomUtil.getBundlMSGValue("rep.tax");

	public void printBill(ContractDTO contractDto, InvoiceDTO invoiceDto, String userName, double equation,
			double previousReading, Boolean isFinalFactura,String cashierName,Boolean printCopy) {
		try {
			

			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("invoiceID", contractDto.getContractId());
			map.put("customerName", contractDto.getCustomerDTO().getCustomerName());
			map.put("locationID", contractDto.getCustomerDTO().getLocalityDTO().getDescription());
			map.put("address", contractDto.getDirection());// contractDto.getCustomerDTO().getCustomerName()
			map.put("customerID", contractDto.getCustomerDTO().getCustomerId());
			map.put("meterNo", contractDto.getContadorNum());
			map.put("sectorName", contractDto.getSectorDTO().getDescription());
			map.put("contractNo", contractDto.getContractId());
			map.put("cashierName", cashierName);

			map.put("usrName", userName);

			// ***************
			map.put("customerNameLabel", customerNameLabel);
			map.put("addressLabel", addressLabel);
			map.put("sectorLabel", sectorLabel);
			map.put("meterNoLabel", meterNoLabel);
			map.put("contractNoLabel", contractNoLabel);
			map.put("localIDLabel", localIDLabel);
			if(isFinalFactura && printCopy) {
				map.put("invoiceLabel", CustomUtil.getBundlMSGValue("copy"));
				map.put("invoiceNoLabel", invoiceNoLabel);
			}
			else if(isFinalFactura && !printCopy) {
				map.put("invoiceLabel", CustomUtil.getBundlMSGValue("original"));
				map.put("invoiceNoLabel", invoiceNoLabel);
			}else {
				map.put("invoiceLabel", invoiceLabel);
				map.put("invoiceNoLabel", invoiceNoLabel);
			}

			map.put("oldReadLabel", oldReadLabel);
			map.put("newReadLabel", newReadLabel);
			map.put("consumptionLabel", consumptionLabel);
			map.put("priceLabel", priceLabel);
			map.put("discountPrecLabel", discountPrecLabel);
			map.put("discountLabel", discountLabel);
			map.put("subToalLabel", subToalLabel);
			map.put("totalLabel", totalLabel);
			map.put("lineTotal", lineTotal);
			map.put("generalTotalLabel", generalTotalLabel);
			map.put("powerLabel", powerLabel);
			map.put("taxLabel", taxLabel);
			map.put("penaltyLabel", penaltyLabel);
			map.put("paidAmountLabel", paidAmountLabel);
			map.put("remainLable", remainLable);
			map.put("customerIDLabel", customerIDLabel);
			map.put("paymentDateLabel", paymentDateLabel);
			map.put("cashierNameLabel", cashierNameLabel);
			map.put("descriptionLabel", descriptionLabel);
			map.put("amountOfActiveEnergyLabel", amountOfActiveEnergyLabel);
			map.put("maintenanceLabel", maintenanceLabel);
			map.put("creditLabel", creditLabel);
			map.put("payLabel", payLabel);
			map.put("coeLabel", coeLabel);
			map.put("balanceLable", balanceLable);
			map.put("equation", equation);
			map.put("previousReading", previousReading);
			map.put("monthES", convertMonth(invoiceDto.getMonth()));
			

			ArrayList<InvoiceDTO> repList = new ArrayList<InvoiceDTO>();
			repList.add(invoiceDto);

			String url = getUrlReport(isFinalFactura);

			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(repList);
			String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(url);
			jasperPrint = JasperFillManager.fillReport(reportPath, map, beanCollectionDataSource);
			httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
					.getResponse();
			httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
			// ServletOutputStream
			// servletOutputStream=httpServletResponse.getOutputStream();
			byte[] pdfByteArray = JasperExportManager.exportReportToPdf(jasperPrint);

			httpServletResponse.setContentType("application/pdf;charset=windows-1252");
			httpServletResponse.setHeader("Content-disposition", "inline; filename=Prova.pdf");
			httpServletResponse.setContentLength(pdfByteArray.length);
			ServletOutputStream out = httpServletResponse.getOutputStream();
			out.write(pdfByteArray, 0, pdfByteArray.length);
			out.flush();
			out.close();
			FacesContext.getCurrentInstance().responseComplete();


		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			try {

				// st.close();
			} catch (Exception cn) {

			}

		}
	}
	
	SimpleDateFormat dd = new SimpleDateFormat("MM/dd/yyyy");
	
	public void clientBalance(ContractDTO contractDto,List<InvoiceDTO> invoiceDTOs ,Date fromDate,Date toDate,
			String uesrName,String reportName,double penality,String localityName)
	{
		try {

			HashMap<String,Object> map = new HashMap<String,Object>();
			
			if(contractDto!=null) {
			map.put("customerName", contractDto.getCustomerDTO().getCustomerName());
			map.put("locationID", contractDto.getCustomerDTO().getLocalityDTO().getDescription());
			map.put("address", contractDto.getDirection());// contractDto.getCustomerDTO().getCustomerName()
			map.put("customerID", contractDto.getCustomerDTO().getCustomerId());
			map.put("meterNo", contractDto.getContadorNum());
			map.put("sectorName", contractDto.getSectorDTO().getDescription());
			map.put("contractNo", contractDto.getContractId());
			map.put("benef", contractDto.getBeneficiary());
			}
			map.put("panality", penality);
			map.put("locationID", localityName);

			map.put("reportTitle", "");
			map.put("dateFrom", dd.format(fromDate));
			map.put("dateTo", dd.format(toDate));
			
			map.put("usrName", uesrName);
			
			//***************
			map.put("customerNameLabel", customerNameLabel); 
			map.put("addressLabel", addressLabel); 
			map.put("sectorLabel", sectorLabel); 
			map.put("meterNoLabel", meterNoLabel); 
			map.put("contractNoLabel", contractNoLabel); 
			map.put("localIDLabel", localIDLabel);
			map.put("customerIDLabel", customerIDLabel);
			map.put("from",fromDateLabel);
			map.put("to",toDateLabel);
			
			map.put("maintenanceLabel", maintenanceLabel);
			map.put("powerLabel", powerLabel);
			map.put("taxLabel", taxLabel) ;
			map.put("penaltyLabel", penaltyLabel);
			
			map.put("remainLable", remainLable);
			map.put("customerIDLabel", customerIDLabel);
			
			map.put("amountOfActiveEnergyLabel", amountOfActiveEnergyLabel);
			map.put("totalLabel", totalLabel);
			map.put("subToalLabel", subToalLabel);
			map.put("discountLabel", discountLabel);
			
			map.put("yearLabel",yearLabel);
			map.put("monthLabel",monthLabel);
			map.put("incomLabel", incomLabel);
			map.put("creditLabel", creditLabel);
			
			map.put("dateLabel", dateLabel);
			
			String url="";
			
			if(reportName.equals("ALL")) {
				url="/bill/segesa/report/customerBalanceAll.jasper";
				map.put("reportTitleCustomerBalance", "Saldo cliente");
			}else if(reportName.equals("UNPAID")) {
				url="/bill/segesa/report/customerBalance.jasper";
				map.put("reportTitleCustomerBalance", "Saldo del cliente pendiente de pago");
			}else if(reportName.equals("PAID")) {
				url="/bill/segesa/report/customerPaidInvoice.jasper";
				map.put("reportTitleCustomerBalance", "Historia Cliente (Pagado) ");
			}else if(reportName.equals("company")) {
				url="/bill/segesa/report/multiContractInvoice.jasper";
				map.put("reportTitleCustomerBalance", "Saldo del cliente pendiente de pago");
			}else if(reportName.equals("loc")) {
				url="/bill/segesa/report/locationFacturas.jasper";
				map.put("reportTitleCustomerBalance", "Saldo del cliente pendiente de pago");
			}
			
			//String custcashier="/bill/segesa/report/customerBalance.jasper";
			
     JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(invoiceDTOs);  
     String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(url);        
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
		
	}	

			  
	}
	
	
	
	
	String getUrlReport(Boolean inv) {
		String url="";
		if (inv == false) { //****factura proforma
			url = "/bill/segesa/report/invoiceForma.jasper";
		} 
		else if (inv == true) {//factura final
			url = "/bill/segesa/report/invoiceFinal.jasper";
		} 
		return url;
	}
	
	public String convertMonth(int m){
		
		if(m==1){
			
			return "Enero";
		}else if(m==2){
			return "Febrero";
			
		}else if(m==3){
			return "Marzo";
			
		}else if(m==4){
			return "Abril";
			
		}else if(m==5){
			return "Mayo";
			
		} else if(m==6){
			return "Junio";
			
		} else if(m==7){
			return "Julio";
			
		}else if(m==8){
			return "Agesto";
			
		}else if(m==9){
			return "Septiembre";
			
		}else if(m==10){
			return  "Octubre";
			
		}else if(m==11){
			return "Noviembre";
			
		}else if(m==12){
			return "Diciembre";
			
		}
		
		return null;
	}

	public void printBillContract(CustomerDTO customerDTO,OtherPaymentDTO otherPaymentDTO,List<OtherPaymentDTO> otherPaymentDTOs)
	{
		try{

	
			
			System.out.println("printed");
			//FacesContext context = FacesContext.getCurrentInstance();
		
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("pamentID", otherPaymentDTO.getOtherPaymentId());
			map.put("customerID", otherPaymentDTO.getCustomerId());
			map.put("paymentDate", otherPaymentDTO.getCreatedDate());
			map.put("cashir","");
			map.put("usrName","");
			
			map.put("customerName",customerDTO.getCustomerName());
			map.put("locationName",customerDTO.getLocalityDTO().getDescription());
			map.put("address",customerDTO.getApplicationDTO().getAddress());
			map.put("vivendaType","");
			map.put("sectName","");
			map.put("suministorType","");
			
			map.put("invoiceLabel",this.invoiceTitleRep);
			map.put("paymentIDLabel",this.paymentIDRep);
			map.put("paymentDateLabel", this.paymentDateRep);
			map.put("customerIDLabel",this.customerRep);
			map.put("cashierNameLabel",this.cashierNameRep);
			map.put("categoryLabel",this.categoryRep);
			map.put("incomeAmountLabel",this.incomeAmountRep);
			map.put("totalLabel",this.totalRep);
			map.put("subTotalLabel",this.subTotalRep);
			map.put("taxLabel",this.taxRep);
			map.put("categoryNam",otherPaymentDTO.getChargeDTO().getDescription());
			
	//ArrayList<Invoice> repList = new ArrayList<Invoice>();
	//repList.add(this.invoice);
     JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource( otherPaymentDTOs);  
     String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath("/bill/segesa/report/invoiceContract.jasper");        
     jasperPrint=JasperFillManager.fillReport(reportPath,map,beanCollectionDataSource);  
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
	}	
	
}
