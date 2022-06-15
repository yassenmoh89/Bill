package core.bill.comercial.service;

import java.util.List;

import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.comercial.dao.CustomerDAO;
import core.bill.comercial.model.CustomerDTO;
import core.bill.comercial.model.CustomerSearch;
import core.bill.common.exception.SegesaServiceException;


@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerDAO customerDAO ; 
	
	@Override
	@Transactional(readOnly = false)
	public CustomerDTO saveModel(CustomerDTO model) throws SegesaServiceException {
		CustomerDTO customerDTO = null;
		try {
			customerDTO = customerDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save customerDTO Error :" + e);
		}
		return customerDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteModel(CustomerDTO model) throws SegesaServiceException {
		
		try {
			customerDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete customerDTO Error :" + e);
		}
		
	}

	@Override
	@Transactional(readOnly = false)
	public CustomerDTO updateModel(CustomerDTO model) throws SegesaServiceException {
		CustomerDTO customerDTO = null;
		try {
			customerDTO = customerDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update customerDTO Error :" + e);
		}
		return customerDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public CustomerDTO getModelDTO(Long pk) throws SegesaServiceException {
		CustomerDTO customerDTO = null;
		try {
			customerDTO = customerDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("get customerDTO Error :" + e);
		}
		return customerDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public CustomerDTO getMergeMode(CustomerDTO model) throws SegesaServiceException {
		CustomerDTO customerDTO = null;
		try {
			customerDTO = customerDAO.merge(model);
		} catch (Exception e) {
			throw new SegesaServiceException("merge customerDTO Error :" + e);
		}
		return customerDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void saveOrUpdateMode(CustomerDTO model) throws SegesaServiceException {
		
		try {
			customerDAO.saveOrUpdate(model);
		} catch (Exception e) {
			throw new SegesaServiceException("saveOrUpdate customerDTO Error :" + e);
		}
		
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<CustomerDTO> getDataList(CustomerSearch critiria, int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<CustomerDTO> list = null;
		try {
			ProjectionList projectionList = Projections.projectionList(); 
			
			Projection customerIdProj = Projections.property("customerId"); 
			Projection customerNameProj = Projections.property("customerName"); 
			Projection customerCodeProj = Projections.property("customerCode"); 
			Projection phoneProj = Projections.property("phone"); 
			Projection calleNameProj = Projections.property("calle"); 
			//Projection localityNameProj = Projections.property("localityDTO.description");
			
			projectionList.add(customerIdProj);
			projectionList.add(customerNameProj);
			projectionList.add(customerCodeProj);
			projectionList.add(phoneProj);
			projectionList.add(calleNameProj);
			//projectionList.add(localityNameProj);
			
			list = customerDAO.getList(critiria,projectionList, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search customerDTO Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CustomerDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<CustomerDTO> list = null;
		try {
			list = customerDAO.getList(null, start, pageSize, "customerId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search customerDTO Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CustomerDTO> getDataList() throws SegesaServiceException {
		List<CustomerDTO> list = null;
		try {
			list = customerDAO.getList(null, 0, 0, "customerId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search customerDTO Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CustomerDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<CustomerDTO> list = null;
		try {
			list = customerDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search customerDTO Error :" + e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getRowCount(CustomerSearch critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = customerDAO.getRowNum(critiria);
			System.out.println("Count :"+count);
		} catch (Exception e) {
			throw new SegesaServiceException("count customerDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(CustomerDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(CustomerDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(CustomerDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CustomerSearch critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getMax(String fieldName) throws SegesaServiceException{
		Long max = null;
		try {
			max = customerDAO.getMaxRow(fieldName);
		} catch (Exception e) {
			throw new SegesaServiceException("count getMax Error :" + e);
		}
		return max;
	}

	@Override
	public List<CustomerDTO> getDataList(CustomerSearch critiria) throws SegesaServiceException{
		List<CustomerDTO> list = null;
		try {
			list = customerDAO.getList(critiria, 0, 0, "customerId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search customerDTO Error :" + e);
		}
		return list;
	}

}
