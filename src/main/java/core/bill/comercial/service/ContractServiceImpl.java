package core.bill.comercial.service;

import java.util.List;

import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.comercial.dao.ContractDAO;
import core.bill.comercial.model.ContractDTO;
import core.bill.comercial.model.ContractDashBoardModel;
import core.bill.comercial.model.ContractSearch;
import core.bill.common.exception.SegesaServiceException;

@Service("contractService")
public class ContractServiceImpl implements ContractService{

	@Autowired
	ContractDAO contractDAO ; 
	
	@Override
	@Transactional(readOnly = false)
	public ContractDTO saveModel(ContractDTO model) throws SegesaServiceException {
		ContractDTO contractDTO = null;
		try {
			contractDTO = contractDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save contractDTO Error :" + e);
		}
		return contractDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteModel(ContractDTO model) throws SegesaServiceException {
		
		try {
			contractDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete contractDTO Error :" + e);
		}
		
	}

	@Override
	@Transactional(readOnly = false)
	public ContractDTO updateModel(ContractDTO model) throws SegesaServiceException {
		
		ContractDTO contractDTO = null;
		try {
			contractDTO = contractDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("Update contractDTO Error :" + e);
		}
		return contractDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public ContractDTO getModelDTO(Long pk) throws SegesaServiceException {
		ContractDTO contractDTO = null;
		try {
			contractDTO = contractDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("getModelDTO contractDTO Error :" + e);
		}
		return contractDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public ContractDTO getMergeMode(ContractDTO model) throws SegesaServiceException {
		ContractDTO contractDTO = null;
		try {
			contractDTO = contractDAO.merge(model);
		} catch (Exception e) {
			throw new SegesaServiceException("getMergeMode contractDTO Error :" + e);
		}
		return contractDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void saveOrUpdateMode(ContractDTO model) throws SegesaServiceException {
		
		try {
			contractDAO.saveOrUpdate(model);
		} catch (Exception e) {
			throw new SegesaServiceException("saveOrUpdateMode contractDTO Error :" + e);
		}
		
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<ContractDTO> getDataList(ContractSearch critiria, int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		
		List<ContractDTO> list = null;
		try {
			
			ProjectionList projectionList = Projections.projectionList(); 
			
			Projection contractIdProj = Projections.property("contractId"); 
			Projection contractCodeProj = Projections.property("contractCode"); 
			Projection contractDirectorProj = Projections.property("direction"); 
			Projection createdDateProj = Projections.property("createdDate"); 
			Projection contractProj = Projections.property("contractStatus"); 
			Projection customerCodeProj = Projections.property("customerDTO.customerCode"); 
			Projection customerNameProj = Projections.property("customerDTO.customerName"); 
			Projection contractBenefProj = Projections.property("beneficiary"); 
			
			projectionList.add(contractIdProj);
			projectionList.add(contractCodeProj);
			projectionList.add(contractDirectorProj);
			projectionList.add(createdDateProj);
			projectionList.add(contractProj);
			projectionList.add(customerCodeProj);
			projectionList.add(customerNameProj);
			projectionList.add(contractBenefProj);
			
			list = contractDAO.getList(critiria,projectionList, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SegesaServiceException("getDataList contractDTO Error :" + e);
			
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ContractDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<ContractDTO> list = null;
		try {
			list = contractDAO.getList(null, start, pageSize, "contractId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList contractDTO Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ContractDTO> getDataList() throws SegesaServiceException {
		List<ContractDTO> list = null;
		try {
			list = contractDAO.getList(null, 0, 0, "contractId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList contractDTO Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ContractDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<ContractDTO> list = null;
		try {
			list = contractDAO.getList(null, start, pageSize, "contractId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList contractDTO Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getRowCount(ContractSearch critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = contractDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("getRowCount contractDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(ContractDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(ContractDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(ContractDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(ContractSearch critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getMaxContractId() throws SegesaServiceException {
		Long max = null;
		try {
			max = contractDAO.getMaxRow("contractId");
		} catch (Exception e) {
			throw new SegesaServiceException("getRowCount contractDTO Error :" + e);
		}
		return max;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ContractDTO> getCustomerContract(Long customerId) throws SegesaServiceException {
		List<ContractDTO> list = null;
		try {
			ContractSearch critiria=new ContractSearch();
			critiria.setCutomerId(customerId);
			
			ProjectionList projectionList = Projections.projectionList(); 
			
			Projection contractIdProj = Projections.property("contractId"); 
			Projection contractdirectionProj = Projections.property("direction"); 
			Projection contractbeneficiaryProj = Projections.property("beneficiary"); 

			projectionList.add(contractIdProj);
			projectionList.add(contractdirectionProj);
			projectionList.add(contractbeneficiaryProj);

			
			list = contractDAO.getCustomerContract(critiria, projectionList, 0, 0, "contractId", Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SegesaServiceException("getDataList contractDTO Error :" + e);
			
		}
		return list;

	}

	@Override
	@Transactional(readOnly = true)
	public List<ContractDashBoardModel> getNumClientsByLoc(String localId) throws SegesaServiceException {
		List<ContractDashBoardModel> list = null;
		try {
	
			list = contractDAO.getNumOfClientsByLocation(localId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SegesaServiceException("getDataList getNumClientsByLoc Error :" + e);
			
		}
		return list;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Long getCountOfContract(ContractSearch creteria) throws SegesaServiceException {
		Long list = null;
		try {
	
			list = contractDAO.getCountOfContracts(creteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SegesaServiceException("getDataList getCountOfContract Error :" + e);
			
		}
		return list;
	}

}
