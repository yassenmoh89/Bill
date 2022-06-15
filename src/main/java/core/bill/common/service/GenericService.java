package core.bill.common.service;

import java.util.List;

import core.bill.common.exception.SegesaServiceException;

public interface GenericService<T, SEARCH> {

	public T saveModel(T model) throws SegesaServiceException;

	public void deleteModel(T model) throws SegesaServiceException;

	public T updateModel(T model) throws SegesaServiceException;

	public T getModelDTO(Long pk) throws SegesaServiceException;
	
	public T getMergeMode(T model) throws SegesaServiceException;
	
	public void saveOrUpdateMode(T model) throws SegesaServiceException;

	public List<T> getDataList(SEARCH critiria, final int start, final int pageSize, final String orderBy,
			final Boolean desc) throws SegesaServiceException;

	public List<T> getDataList(int start, int pageSize) throws SegesaServiceException;

	public List<T> getDataList() throws SegesaServiceException;

	public List<T> getDataList(int start, final int pageSize, final String orderBy, final Boolean desc)
			throws SegesaServiceException;

	public Long getRowCount(SEARCH critiria) throws SegesaServiceException;

	public List<String> valideSave(T model);

	public List<String> valideUpdate(T model);

	public List<String> valideDelete(T model);

	public List<String> valideSearch(SEARCH critiria);

}
