package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.SectorDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.SectorDTO;

@Service("sectorService")
@Transactional(readOnly = true)
public class SectorServiceImpl implements SectorService{

	@Autowired
	SectorDAO sectorDAO ; 
	
	
	@Override
	public SectorDTO saveModel(SectorDTO model) throws SegesaServiceException {
		SectorDTO sectorDTO = null;
		try {
			sectorDTO = sectorDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save sectorDTO Error :" + e);
		}
		return sectorDTO;
	}

	@Override
	public void deleteModel(SectorDTO model) throws SegesaServiceException {
		
		try {
			sectorDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete sectorDTO Error :" + e);
		}
	}

	@Override
	public SectorDTO updateModel(SectorDTO model) throws SegesaServiceException {
		SectorDTO sectorDTO = null;
		try {
			sectorDTO = sectorDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update sectorDTO Error :" + e);
		}
		return sectorDTO;
	}

	@Override
	public SectorDTO getModelDTO(Long pk) throws SegesaServiceException {
		SectorDTO sectorDTO = null;
		try {
			sectorDTO = sectorDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("get sectorDTO Error :" + e);
		}
		return sectorDTO;
	}

	@Override
	public List<SectorDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<SectorDTO> list = null;
		try {
			list = sectorDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Search sectorDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<SectorDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<SectorDTO> list = null;
		try {
			list = sectorDAO.getList(null, start, pageSize, "sectorId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Search sectorDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<SectorDTO> getDataList() throws SegesaServiceException {
		List<SectorDTO> list = null;
		try {
			list = sectorDAO.getList(null, 0, 0, "sectorId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("Search sectorDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<SectorDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<SectorDTO> list = null;
		try {
			list = sectorDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("Search sectorDTO Error :" + e);
		}
		return list;
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		Long count = null;
		try {
			count = sectorDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("get Row count sectorDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(SectorDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(SectorDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(SectorDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SectorDTO getMergeMode(SectorDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(SectorDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	
}
