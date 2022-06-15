package core.bill.invoice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import core.bill.common.exception.SegesaServiceException;
import core.bill.invoice.dao.ReadingDAO;
import core.bill.invoice.model.InvoiceSearch;
import core.bill.invoice.model.ReadingDTO;


@Service("readingService")
public class ReadingServiceImpl implements ReadingService{

	@Autowired
	ReadingDAO readingDAO ; 
	
	@Override
	@Transactional(readOnly = false)
	public ReadingDTO saveModel(ReadingDTO model) throws SegesaServiceException {
		
		ReadingDTO readingDTO = null;
		try {
			readingDTO = readingDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save ReadingDTO Error :" + e);
		}
		return readingDTO;
	}

	
	@Override
	@Transactional(readOnly = false)
	public void deleteModel(ReadingDTO model) throws SegesaServiceException {
		
		try {
			readingDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("deleteModel ReadingDTO Error :" + e);
		}
	
		
	}

	@Override
	@Transactional(readOnly = false)
	public ReadingDTO updateModel(ReadingDTO model) throws SegesaServiceException {
		ReadingDTO readingDTO = null;
		try {
			readingDTO =	readingDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("updateModel ReadingDTO Error :" + e);
		}
		return readingDTO ;
	}

	@Override
	@Transactional(readOnly = false)
	public ReadingDTO getModelDTO(Long pk) throws SegesaServiceException {
		ReadingDTO readingDTO = null;
		try {
			readingDTO =	readingDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("getModelDTO ReadingDTO Error :" + e);
		}
		return readingDTO ;
	}

	@Override
	@Transactional(readOnly = false)
	public ReadingDTO getMergeMode(ReadingDTO model) throws SegesaServiceException {
		ReadingDTO readingDTO = null;
		try {
			readingDTO =	readingDAO.merge(model);
		} catch (Exception e) {
			throw new SegesaServiceException("getMergeMode ReadingDTO Error :" + e);
		}
		return readingDTO ;
	}

	@Override
	@Transactional(readOnly = false)
	public void saveOrUpdateMode(ReadingDTO model) throws SegesaServiceException {
		
		try {
			readingDAO.saveOrUpdate(model);
		} catch (Exception e) {
			throw new SegesaServiceException("saveOrUpdateMode ReadingDTO Error :" + e);
		}
		
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReadingDTO> getDataList(InvoiceSearch critiria, int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		
		List<ReadingDTO> list = null;
		try {
			list =	readingDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList ReadingDTO Error :" + e);
		}
		return list ;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReadingDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<ReadingDTO> list = null;
		try {
			list =	readingDAO.getList(null, start, pageSize, "readingId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList ReadingDTO Error :" + e);
		}
		return list ;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReadingDTO> getDataList() throws SegesaServiceException {
		List<ReadingDTO> list = null;
		try {
			list =	readingDAO.getList(null, 0, 0, "readingId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList ReadingDTO Error :" + e);
		}
		return list ;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReadingDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<ReadingDTO> list = null;
		try {
			list =	readingDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList ReadingDTO Error :" + e);
		}
		return list ;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getRowCount(InvoiceSearch critiria) throws SegesaServiceException {
		
		Long count = null;
		try {
			count =	readingDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("getRowCount ReadingDTO Error :" + e);
		}
		return count ;
	}

	@Override
	public List<String> valideSave(ReadingDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(ReadingDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(ReadingDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(InvoiceSearch critiria) {
		// TODO Auto-generated method stub
		return null;
	}

}
