package core.bill.setting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.exception.SegesaServiceException;
import core.bill.setting.dao.TypeDAO;
import core.bill.setting.model.CommonSearchDTO;
import core.bill.setting.model.TypeDTO;

@Service("typeService")
@Transactional(readOnly = true)
public class TypeServiceImpl implements TypeService{

	@Autowired
	TypeDAO typeDAO ;

	@Override
	public TypeDTO saveModel(TypeDTO model) throws SegesaServiceException {
		TypeDTO typeDTO = null;
		try {
			typeDTO = typeDAO.save(model);
		} catch (Exception e) {
			throw new SegesaServiceException("save typeDTO Error :" + e);
		}
		return typeDTO;
	}

	@Override
	public void deleteModel(TypeDTO model) throws SegesaServiceException {
		
		try {
			typeDAO.delete(model);
		} catch (Exception e) {
			throw new SegesaServiceException("delete typeDTO Error :" + e);
		}
		
		
	}

	@Override
	public TypeDTO updateModel(TypeDTO model) throws SegesaServiceException {
		TypeDTO typeDTO = null;
		try {
			typeDTO = typeDAO.update(model);
		} catch (Exception e) {
			throw new SegesaServiceException("update typeDTO Error :" + e);
		}
		return typeDTO;
	}

	@Override
	public TypeDTO getModelDTO(Long pk) throws SegesaServiceException {
		TypeDTO typeDTO = null;
		try {
			typeDTO = typeDAO.getModel(pk);
		} catch (Exception e) {
			throw new SegesaServiceException("save typeDTO Error :" + e);
		}
		return typeDTO;
	}

	@Override
	public TypeDTO getMergeMode(TypeDTO model) throws SegesaServiceException {
		TypeDTO typeDTO = null;
		try {
			typeDTO = typeDAO.merge(model);
		} catch (Exception e) {
			throw new SegesaServiceException("merge typeDTO Error :" + e);
		}
		return typeDTO;
	}

	@Override
	public void saveOrUpdateMode(TypeDTO model) throws SegesaServiceException {
		
		try {
			typeDAO.saveOrUpdate(model);
		} catch (Exception e) {
			throw new SegesaServiceException("saveOrUpdate typeDTO Error :" + e);
		}
		
		
	}

	@Override
	public List<TypeDTO> getDataList(CommonSearchDTO critiria, int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<TypeDTO> list = null;
		try {
			list = typeDAO.getList(critiria, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("merge typeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<TypeDTO> getDataList(int start, int pageSize) throws SegesaServiceException {
		List<TypeDTO> list = null;
		try {
			list = typeDAO.getList(null, start, pageSize, "typeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList typeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<TypeDTO> getDataList() throws SegesaServiceException {
		List<TypeDTO> list = null;
		try {
			list = typeDAO.getList(null, 0, 0, "typeId", Boolean.TRUE);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList typeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public List<TypeDTO> getDataList(int start, int pageSize, String orderBy, Boolean desc)
			throws SegesaServiceException {
		List<TypeDTO> list = null;
		try {
			list = typeDAO.getList(null, start, pageSize, orderBy, desc);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList typeDTO Error :" + e);
		}
		return list;
	}

	@Override
	public Long getRowCount(CommonSearchDTO critiria) throws SegesaServiceException {
		Long  count = null;
		try {
			count = typeDAO.getRowNum(critiria);
		} catch (Exception e) {
			throw new SegesaServiceException("getDataList typeDTO Error :" + e);
		}
		return count;
	}

	@Override
	public List<String> valideSave(TypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideUpdate(TypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideDelete(TypeDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> valideSearch(CommonSearchDTO critiria) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
