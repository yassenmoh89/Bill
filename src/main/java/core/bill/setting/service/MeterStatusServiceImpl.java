package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.MeterStatusDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.MeterStatusDTO;

@Service("meterStatusService")
@Transactional(readOnly = true)
public class MeterStatusServiceImpl implements MeterStatusService{

	@Autowired
	MeterStatusDAO meterStatusDAO ; 
	
	@Override
	public MeterStatusDTO saveModel(MeterStatusDTO model) throws SegesaServiceException {
		MeterStatusDTO meterStatusDTO = null;
		try {
			meterStatusDTO = meterStatusDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save meterStatusDTO Error :" + e);
		}
		return meterStatusDTO;
	}

	@Override
	public void deleteModel(MeterStatusDTO model) throws SegesaServiceException {
		
		try {
			    meterStatusDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete meterStatusDTO Error :" + e);
		}
		
		
	}

	@Override
	public MeterStatusDTO updateModel(MeterStatusDTO model) throws SegesaServiceException {
		MeterStatusDTO meterStatusDTO = null;
		try {
			meterStatusDTO = meterStatusDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update meterStatusDTO Error :" + e);
		}
		return meterStatusDTO;
	}

	@Override
	public MeterStatusDTO getModelDTO(Long pk) throws SegesaServiceException {
		MeterStatusDTO meterStatusDTO = null;
		try {
			meterStatusDTO = meterStatusDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("get meterStatusDTO Error :" + e);
		}
		return meterStatusDTO;
	}

	@Override
	public List<MeterStatusDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize, String orderBy,
			Boolean desc) throws SegesaServiceException {
		
		List<MeterStatusDTO> list = null;
		try {
			list = meterStatusDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search meterStatusDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<MeterStatusDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<MeterStatusDTO> list = null;
		try {
			list = meterStatusDAO.getList(null, start, pageSize, "meterStatusId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search meterStatusDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<MeterStatusDTO> getDataList() throws SegesaServiceException {
		
		List<MeterStatusDTO> list = null;
		try {
			list = meterStatusDAO.getList(null, 0, 0, "meterStatusId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search meterStatusDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<MeterStatusDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		
		List<MeterStatusDTO> list = null;
		try {
			list = meterStatusDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search meterStatusDTO Error :" + e);
		}
		return list;
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		
		Long count = null;
		try {
			count = meterStatusDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("search meterStatusDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(MeterStatusDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(MeterStatusDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(MeterStatusDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MeterStatusDTO getMergeMode(MeterStatusDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(MeterStatusDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	
}
