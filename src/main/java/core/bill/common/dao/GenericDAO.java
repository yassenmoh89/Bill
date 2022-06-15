package core.bill.common.dao;

import java.util.List;

public interface GenericDAO<T,SEARCH> {
	
	public T save(T model);

	public void delete(T model);

	public T update(T model);

	public T getModel(Long pk);

	public List<T> getList(SEARCH certieria ,final int start, final int pageSize
			,String orderBy ,Boolean desc);
	
	public Long getRowNum(SEARCH certieria);
		
	public T merge(T model) ;
	
	public void saveOrUpdate(T model) ;
}
