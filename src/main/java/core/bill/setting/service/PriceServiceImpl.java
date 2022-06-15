package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.PriceDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.PriceDTO;

@Service("priceService")
@Transactional(readOnly = true)
public class PriceServiceImpl implements PriceService{

	@Autowired
	PriceDAO priceDAO ; 
	
	@Override
	public PriceDTO saveModel(PriceDTO model) throws SegesaServiceException {
		PriceDTO priceDTO = null;
		try {
			priceDTO = priceDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save priceDTO Error :" + e);
		}
		return priceDTO;
	}

	@Override
	public void deleteModel(PriceDTO model) throws SegesaServiceException {
		
		try {
			priceDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete priceDTO Error :" + e);
		}
		
		
	}

	@Override
	public PriceDTO updateModel(PriceDTO model) throws SegesaServiceException {
		PriceDTO priceDTO = null;
		try {
			priceDTO = priceDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update priceDTO Error :" + e);
		}
		return priceDTO;
	}

	@Override
	public PriceDTO getModelDTO(Long pk) throws SegesaServiceException {
		PriceDTO priceDTO = null;
		try {
			priceDTO = priceDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("update priceDTO Error :" + e);
		}
		return priceDTO;
	}

	@Override
	public List<PriceDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		
		List<PriceDTO> list = null;
		try {
			list = priceDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search priceDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<PriceDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<PriceDTO> list = null;
		try {
			list = priceDAO.getList(null, start, pageSize, "priceId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search priceDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<PriceDTO> getDataList() throws SegesaServiceException {
		List<PriceDTO> list = null;
		try {
			list = priceDAO.getList(null, 0, 0, "priceId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search priceDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<PriceDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<PriceDTO> list = null;
		try {
			list = priceDAO.getList(null, 0, 0, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search priceDTO Error :" + e);
		}
		return list;
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = priceDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("search priceDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(PriceDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(PriceDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(PriceDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PriceDTO getMergeMode(PriceDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(PriceDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	

}
