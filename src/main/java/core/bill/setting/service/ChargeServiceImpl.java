package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.invoice.model.ChargeDTO;
import core.bill.setting.dao.ChargeDAO;
import core.bill.setting.model.CommonSearchDTO;

@Service("chargeService")
@Transactional(readOnly = true)
public class ChargeServiceImpl implements ChargeService {

	@Autowired
	ChargeDAO chargeDAO;

	@Override
	public ChargeDTO saveModel(ChargeDTO model) throws SegesaServiceException {
		ChargeDTO chargeDTO = null;
		try {
			chargeDTO = chargeDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save ChargeDTO Error :" + e);
		}
		return chargeDTO;
	}

	@Override
	public void deleteModel(ChargeDTO model) throws SegesaServiceException {
		try {
			chargeDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete ChargeDTO Error :" + e);
		}
		
	}

	@Override
	public ChargeDTO updateModel(ChargeDTO model) throws SegesaServiceException {
		ChargeDTO chargeDTO = null;
		try {
			chargeDTO = chargeDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update ChargeDTO Error :" + e);
		}
		return chargeDTO;
	}

	@Override
	public ChargeDTO getModelDTO(Long pk) throws SegesaServiceException {
		ChargeDTO chargeDTO = null;
		try {
			chargeDTO = chargeDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("get model ChargeDTO Error :" + e);
		}
		return chargeDTO;
	}

	@Override
	public ChargeDTO getMergeMode(ChargeDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateMode(ChargeDTO model) throws SegesaServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ChargeDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<ChargeDTO> list=null;
		try {
			list = chargeDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("search chargeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ChargeDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<ChargeDTO> list=null;
		try {
			list = chargeDAO.getList(null,start, pageSize,"chargeId",Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search chargeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ChargeDTO> getDataList() throws SegesaServiceException {
		List<ChargeDTO> list=null;
		try {
			list = chargeDAO.getList(null,0, 0,"chargeId",Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search chargeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<ChargeDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<ChargeDTO> list=null;
		try {
			list = chargeDAO.getList(null,start, pageSize,"chargeId",Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("search chargeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		// TODO Auto-generated method stub
		Long count = null;
		try {
			count = chargeDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("search chargeDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(ChargeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(ChargeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(ChargeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

}
