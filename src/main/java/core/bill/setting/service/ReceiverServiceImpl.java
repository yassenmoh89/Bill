package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.ReceiverDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.ReceiverDTO;

@Service("receiverService")
@Transactional(readOnly = true)
public class ReceiverServiceImpl implements ReceiverService{

	@Autowired
	ReceiverDAO receiverDAO ; 
	
	@Override
	public ReceiverDTO saveModel(ReceiverDTO model) throws SegesaServiceException {
		ReceiverDTO receiverDTO = null;
		try {
			receiverDTO = receiverDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save receiverDTO Error :" + e);
		}
		return receiverDTO;
	}

	@Override
	public void deleteModel(ReceiverDTO model) throws SegesaServiceException {
		
		try {
			 receiverDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete receiverDTO Error :" + e);
		}
		
		
	}

	@Override
	public ReceiverDTO updateModel(ReceiverDTO model) throws SegesaServiceException {
		ReceiverDTO receiverDTO = null;
		try {
			receiverDTO = receiverDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update receiverDTO Error :" + e);
		}
		return receiverDTO;
	}

	@Override
	public ReceiverDTO getModelDTO(Long pk) throws SegesaServiceException {
		ReceiverDTO receiverDTO = null;
		try {
			receiverDTO = receiverDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("get receiverDTO Error :" + e);
		}
		return receiverDTO;
	}

	@Override
	public List<ReceiverDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException {
		
		List<ReceiverDTO> list = null;
		try {
			list = receiverDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Search receiverDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ReceiverDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<ReceiverDTO> list = null;
		try {
			list = receiverDAO.getList(null, start, pageSize, "receiverId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Search receiverDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ReceiverDTO> getDataList() throws SegesaServiceException {
		
		List<ReceiverDTO> list = null;
		try {
			list = receiverDAO.getList(null, 0, 0, "receiverId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Search receiverDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ReceiverDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		
		List<ReceiverDTO> list = null;
		try {
			list = receiverDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Search receiverDTO Error :" + e);
		}
		return list;
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = receiverDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("get Row account receiverDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(ReceiverDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(ReceiverDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(ReceiverDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReceiverDTO getMergeMode(ReceiverDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(ReceiverDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	

}
