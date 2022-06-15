package core.bill.setting.serviceProvider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.ApplicationPurposeTypeDAO;
import core.bill.setting.dao.BillingTypeDAO;
import core.bill.setting.dao.CircuitsDAO;
import core.bill.setting.dao.ConnectionTypeDAO;
import core.bill.setting.dao.ContractBillingDAO;
import core.bill.setting.dao.ContractOwnerTypeDAO;
import core.bill.setting.dao.ContractPaymentDAO;
import core.bill.setting.dao.ContractTypeDAO;
import core.bill.setting.dao.HouseholdsDAO;
import core.bill.setting.dao.IdentificationTypeDAO;
import core.bill.setting.dao.InspectionDecisionTypeDAO;
import core.bill.setting.dao.LocalityDAO;
import core.bill.setting.dao.MeterStatusDAO;
import core.bill.setting.dao.NationalityDAO;
import core.bill.setting.dao.PaymentTypeDAO;
import core.bill.setting.dao.PhaseDAO;
import core.bill.setting.dao.PriceDAO;
import core.bill.setting.dao.ReceiverDAO;
import core.bill.setting.dao.SectorDAO;
import core.bill.setting.dao.SupplyDAO;
import core.bill.setting.dao.TypeDAO;
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
import core.bill.setting.model.PriceDTO; //---
import core.bill.setting.model.ReceiverDTO;
import core.bill.setting.model.SectorDTO;
import core.bill.setting.model.SupplyDTO;
import core.bill.setting.model.TypeDTO;

@Service("settingServiceProvider")
@Transactional(readOnly = true)
public class SettingServiceProviderImpl implements SettingServiceProvider {

	@Autowired
	ApplicationPurposeTypeDAO applicationPurposeTypeDAO;

	@Autowired
	BillingTypeDAO billingTypeDAO;

	@Autowired
	CircuitsDAO circuitsDAO;

	@Autowired
	ConnectionTypeDAO connectionTypeDAO;

	@Autowired
	ContractOwnerTypeDAO contractOwnerTypeDAO;

	@Autowired
	ContractTypeDAO contractTypeDAO;

	@Autowired
	HouseholdsDAO householdsDAO;

	@Autowired
	IdentificationTypeDAO identificationTypeDAO;

	@Autowired
	InspectionDecisionTypeDAO inspectionDecisionTypeDAO;

	@Autowired
	LocalityDAO localityDAO;

	@Autowired
	MeterStatusDAO meterStatusDAO;

	@Autowired
	NationalityDAO nationalityDAO;

	@Autowired
	PaymentTypeDAO paymentTypeDAO;

	@Autowired
	PhaseDAO phaseDAO;

	@Autowired
	ReceiverDAO receiverDAO;

	@Autowired
	SectorDAO sectorDAO;

	@Autowired
	SupplyDAO supplyDAO;

	@Autowired
	PriceDAO priceDAO;
	
	@Autowired
	TypeDAO typeDAO ;
	
	@Autowired
	ContractPaymentDAO contractPaymentDAO ;
	
	@Autowired
	ContractBillingDAO contractBillingDAO ;

	@Override
	public List<ApplicationPurposeTypeDTO> getApplicationPurposeTypeDTOList(CommonSearchDTO search)
			throws SegesaServiceException {

		List<ApplicationPurposeTypeDTO> list = null;
		try {
			list = applicationPurposeTypeDAO.getList(search, 0, 0, "applicationPurposeTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search ApplicationPurposeTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<BillingTypeDTO> getBillingTypeDTOList(CommonSearchDTO search) throws SegesaServiceException {
		List<BillingTypeDTO> list = null;
		try {
			list = billingTypeDAO.getList(search, 0, 0, "billingTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search BillingTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<CircuitsDTO> getCircuitsDTOList(CommonSearchDTO search) throws SegesaServiceException {
		List<CircuitsDTO> list = null;
		try {
			list = circuitsDAO.getList(search, 0, 0, "circuitId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search CircuitsDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ConnectionTypeDTO> getConnectionTypeDTOList(CommonSearchDTO search) throws SegesaServiceException {
		List<ConnectionTypeDTO> list = null;
		try {
			list = connectionTypeDAO.getList(search, 0, 0, "connectionTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search ConnectionTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ContractOwnerTypeDTO> getContractOwnerTypeDTOList(CommonSearchDTO search)
			throws SegesaServiceException {
		List<ContractOwnerTypeDTO> list = null;
		try {
			list = contractOwnerTypeDAO.getList(search, 0, 0, "contractOwnerTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search ConnectionTypeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ContractTypeDTO> geContractTypeDTOList(CommonSearchDTO search) throws SegesaServiceException {
		List<ContractTypeDTO> list = null;
		
		try {
			list = contractTypeDAO.getList(search, 0, 0, "contractTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search ConnectionTypeDTO Error :" + e);
		}
		
		return list;
	}

	@Override
	public List<HouseholdsDTO> getHouseholdsDTOList(CommonSearchDTO search) throws SegesaServiceException {
		
		List<HouseholdsDTO> list = null;
		try {
			list = householdsDAO.getList(search, 0, 0, "houseHoldingTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search HouseholdsDTO Error :" + e);
		}
		
		return list;
	}

	@Override
	public List<IdentificationTypeDTO> getIdentificationTypeDTOList(CommonSearchDTO search)
			throws SegesaServiceException {
		
		List<IdentificationTypeDTO> list = null;
		try {
			list = identificationTypeDAO.getList(search, 0, 0, "identificationTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search IdentificationTypeDTO Error :" + e);
		}
		
		return list;
	}

	@Override
	public List<InspectionDecisionTypeDTO> getInspectionDecisionTypeDTOList(CommonSearchDTO search)
			throws SegesaServiceException {
		
		List<InspectionDecisionTypeDTO> list = null;
		try {
			list = inspectionDecisionTypeDAO.getList(search, 0, 0, "decisionTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search InspectionDecisionTypeDTO Error :" + e);
		}
		
		return list;
	}

	@Override
	public List<LocalityDTO> getLocalityDTOList(CommonSearchDTO search) throws SegesaServiceException {
		
		List<LocalityDTO> list = null;
		try {
			list = localityDAO.getList(search, 0, 0, "localityId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search LocalityDTO Error :" + e);
		}
		
		return list;
	}

	@Override
	public List<MeterStatusDTO> getMeterStatusDTOList(CommonSearchDTO search) throws SegesaServiceException {
		
		List<MeterStatusDTO> list = null;
		try {
			list = meterStatusDAO.getList(search, 0, 0, "meterStatusId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search MeterStatusDTO Error :" + e);
		}
		
		return list;
	}

	@Override
	public List<NationalityDTO> getNationalityDTOList(CommonSearchDTO search) throws SegesaServiceException {
		
		List<NationalityDTO> list = null;
		try {
			list = nationalityDAO.getList(search, 0, 0, "nationalityId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search NationalityDTO Error :" + e);
		}
		
		return list;
	}

	@Override
	public List<PaymentTypeDTO> getPaymentTypeDTOList(CommonSearchDTO search) throws SegesaServiceException {
		
		List<PaymentTypeDTO> list = null;
		try {
			list = paymentTypeDAO.getList(search, 0, 0, "paymentTypeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search PaymentTypeDTO Error :" + e);
		}
		
		return list;
	}

	@Override
	public List<PhaseDTO> getPhaseDTOList(CommonSearchDTO search) throws SegesaServiceException {
		
		List<PhaseDTO> list = null;
		try {
			list = phaseDAO.getList(search, 0, 0, "phaseId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search PhaseDTO Error :" + e);
		}
		
		return list;
	}

	@Override
	public List<PriceDTO> getPriceDTOList(CommonSearchDTO search) throws SegesaServiceException {
		
		List<PriceDTO> list = null;
		try {
			list = priceDAO.getList(search, 0, 0, "priceId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search PriceDTO Error :" + e);
		}
		
		return list;
	}

	@Override
	public List<ReceiverDTO> getReceiverDTOList(CommonSearchDTO search) throws SegesaServiceException {
		
		List<ReceiverDTO> list = null;
		try {
			list = receiverDAO.getList(search, 0, 0, "receiverId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search ReceiverDTO Error :" + e);
		}
		
		return list;
	}

	@Override
	public List<SectorDTO> getSectorDTOList(CommonSearchDTO search) throws SegesaServiceException {
		
		List<SectorDTO> list = null;
		try {
			list = sectorDAO.getList(search, 0, 0, "sectorId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search SectorDTO Error :" + e);
		}
		
		return list;
	}

	@Override
	public List<SupplyDTO> getSupplyDTOList(CommonSearchDTO search) throws SegesaServiceException {
		
		List<SupplyDTO> list = null;
		try {
			list = supplyDAO.getList(search, 0, 0, "supplyId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search SectorDTO Error :" + e);
		}
		
		return list;
	}

	@Override
	public List<TypeDTO> getTypeDTOList(CommonSearchDTO search) throws SegesaServiceException {
		List<TypeDTO> list = null;
		try {
			list = typeDAO.getList(search, 0, 0, "typeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search getTypeDTOList Error :" + e);
		}
		return list;
	}

	@Override
	public List<ContractBillingDTO> getContractBillingDTOList(CommonSearchDTO search) throws SegesaServiceException {
		List<ContractBillingDTO> list = null;
		try {
			list = contractBillingDAO.getList(search, 0, 0, "contractBillingId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search ContractBillingDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ContractPaymentDTO> getContractPaymentDTOList(CommonSearchDTO search) throws SegesaServiceException {
		List<ContractPaymentDTO> list = null;
		try {
			list = contractPaymentDAO.getList(search, 0, 0, "contractPaymentId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search ContractPaymentDTO Error :" + e);
		}
		return list;
	}

}
