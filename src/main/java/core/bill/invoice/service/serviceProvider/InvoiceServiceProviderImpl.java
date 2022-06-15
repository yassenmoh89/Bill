package core.bill.invoice.service.serviceProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("invoiceServiceProvider")
@Transactional(readOnly = true)
public class InvoiceServiceProviderImpl implements InvoiceServiceProvider{

	Logger logger = LoggerFactory.getLogger(InvoiceServiceProviderImpl.class) ;
	
	

}
