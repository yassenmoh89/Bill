package core.bill.common.util;

import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import core.bill.common.service.GenericService;


public class SearchPagination<T, R>  extends LazyDataModel<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private List<T> datasource;
	private R criteria ; 
	private Boolean order ;
	private GenericService<T, R> genericService ;
	private String sortByField ; 
	private Object idField;

	//-----------------------------------------------------------
		public SearchPagination(GenericService<T,R> service ,R criteria, String sortByField , Boolean order)
		{
			this.genericService =service;
			this.criteria = criteria ;
			this.order =order ;
			this.sortByField=sortByField ;
			this.idField=sortByField;
		}
	//--------------------------------------------------------------
		        
		@Override
	    public T getRowData(String rowKey) {
	
	        for (T object : datasource) {
	            if (object !=null) {
	                return object;
	            }
	        }

	        return null;
	    }
		
		@Override
		public String getRowKey(T arg0) {
			// TODO Auto-generated method stub
			System.out.println("washinton"+arg0);
			return arg0.toString();
		}
		 //--------------------------------------------------------------
		 
		 @Override
		    public List<T> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
			 
			 //listData(criteria, offset, pageSize, null, false) ;
			 List<T> dataList =	 null;
			 try {
				 		if(getGenericService()!=null)
				 			{
				 			
				 				dataList = getGenericService().getDataList(criteria, offset, pageSize, sortByField, order);
				 				
				 					}
		        
			 } catch (Exception e) {
					
				 e.printStackTrace();
				}
			 
			 
		        return dataList;
		    }
		 //===================================
		
		public List<T> getDatasource() {
			return datasource;
		}

		public void setDatasource(List<T> datasource) {
			this.datasource = datasource;
		}

		public GenericService<T, R> getGenericService() {
			return genericService;
		}

		public void setGenericService(GenericService<T, R> genericService) {
			this.genericService = genericService;
		}

		public R getCriteria() {
			return criteria;
		}

		public void setCriteria(R criteria) {
			this.criteria = criteria;
		}

		public Boolean getOrder() {
			return order;
		}

		public void setOrder(Boolean order) {
			this.order = order;
		}

		public String getSortByField() {
			return sortByField;
		}

		public void setSortByField(String sortByField) {
			this.sortByField = sortByField;
		}

		public Object getIdField() {
			return idField;
		}

		public void setIdField(Object idField) {
			this.idField = idField;
		}

		@Override
		public int count(Map<String, FilterMeta> arg0) {
			// TODO Auto-generated method stub
			return getRowCount();
		}

	

}
