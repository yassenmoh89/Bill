package core.bill.setting.serviceProvider;

import java.util.List;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.model.ApplicationPurposeTypeDTO;
import core.bill.setting.model.BillingTypeDTO;
import core.bill.setting.model.CircuitsDTO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.ConnectionTypeDTO;
import core.bill.setting.model.ContractBillingDTO;
import core.bill.setting.model.ContractOwnerTypeDTO;
import core.bill.setting.model.ContractPaymentDTO;
import core.bill.setting.model.ContractTypeDTO;
import core.bill.setting.model.HouseholdsDTO;
import core.bill.setting.model.IdentificationTypeDTO;
import core.bill.setting.model.InspectionDecisionTypeDTO;
import core.bill.setting.model.LocalityDTO;
import core.bill.setting.model.MeterStatusDTO;
import core.bill.setting.model.NationalityDTO;
import core.bill.setting.model.PaymentTypeDTO;
import core.bill.setting.model.PhaseDTO;
import core.bill.setting.model.PriceDTO;
import core.bill.setting.model.ReceiverDTO;
import core.bill.setting.model.SectorDTO;
import core.bill.setting.model.SupplyDTO;
import core.bill.setting.model.TypeDTO;

public interface SettingServiceProvider {

	public List<ApplicationPurposeTypeDTO>  getApplicationPurposeTypeDTOList(CommonSearchDTO search) throws SegesaServiceException ;
	public List<BillingTypeDTO>  getBillingTypeDTOList(CommonSearchDTO search) throws SegesaServiceException ;
	public List<CircuitsDTO>  getCircuitsDTOList(CommonSearchDTO search)  throws SegesaServiceException ;
	public List<ConnectionTypeDTO>  getConnectionTypeDTOList(CommonSearchDTO search)  throws SegesaServiceException ;
	public List<ContractOwnerTypeDTO>  getContractOwnerTypeDTOList(CommonSearchDTO search)  throws SegesaServiceException ;
	public List<ContractTypeDTO>  geContractTypeDTOList(CommonSearchDTO search)  throws SegesaServiceException ;
	public List<HouseholdsDTO>  getHouseholdsDTOList(CommonSearchDTO search)  throws SegesaServiceException ;
	public List<IdentificationTypeDTO>  getIdentificationTypeDTOList(CommonSearchDTO search)  throws SegesaServiceException ;
	public List<InspectionDecisionTypeDTO>  getInspectionDecisionTypeDTOList(CommonSearchDTO search)  throws SegesaServiceException ;
	public List<LocalityDTO>  getLocalityDTOList(CommonSearchDTO search)  throws SegesaServiceException ;
	
	public List<MeterStatusDTO>  getMeterStatusDTOList(CommonSearchDTO search)  throws SegesaServiceException ;
	public List<NationalityDTO>  getNationalityDTOList(CommonSearchDTO search)  throws SegesaServiceException ;
	public List<PaymentTypeDTO>  getPaymentTypeDTOList(CommonSearchDTO search)  throws SegesaServiceException ;
	public List<PhaseDTO>  getPhaseDTOList(CommonSearchDTO search)  throws SegesaServiceException ;
	public List<PriceDTO>  getPriceDTOList(CommonSearchDTO search)  throws SegesaServiceException ;
	public List<ReceiverDTO>  getReceiverDTOList(CommonSearchDTO search)  throws SegesaServiceException ;
	public List<SectorDTO>  getSectorDTOList(CommonSearchDTO search)  throws SegesaServiceException ;
	public List<SupplyDTO>  getSupplyDTOList(CommonSearchDTO search)  throws SegesaServiceException ;
	
	public List<TypeDTO>  getTypeDTOList(CommonSearchDTO search)  throws SegesaServiceException ;
	public List<ContractBillingDTO>  getContractBillingDTOList(CommonSearchDTO search)  throws SegesaServiceException ;
	public List<ContractPaymentDTO>  getContractPaymentDTOList(CommonSearchDTO search)  throws SegesaServiceException ;
}
