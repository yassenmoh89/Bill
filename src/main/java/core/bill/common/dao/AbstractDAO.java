package core.bill.common.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public abstract class AbstractDAO<T> {

	@Autowired
	SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private Class<?> clazz;

	public T saveModel(T model) {
		Long pk = (Long) getSession().save(model);
		getSession().flush();
		return getModelDTO(pk);
	}

	public void saveOrUpdateModel(T model) {
		getSession().saveOrUpdate(model);
		getSession().flush();

	}

	public void deleteModel(T model) {
		getSession().delete(model);
	}

	@SuppressWarnings("unchecked")
	public T updateModel(T model, Long pk) {
		getSession().update(model);
		getSession().flush();
		return (T) getSession().get(getClazz(), pk);
	}

	@SuppressWarnings("unchecked")
	public T getModelDTO(Long pk) {
		return (T) getSession().get(getClazz(), pk);

	}

	@SuppressWarnings("unchecked")
	public List<T> getDataList(final List<Criterion> criterion, final int start, final int pageSize,
			final String orderBy, final Boolean desc) {
		Criteria crit = getSession().createCriteria(getClazz());
		// createAlias

		if (criterion != null && criterion.size() > 0) {
			for (Criterion critObj : criterion) {
				if (critObj != null) {
					crit.add(critObj);
				}
			}
		}

		if (start >= 0)
			crit.setFirstResult(start);

		if (pageSize > 0)
			crit.setMaxResults(pageSize);
		if (orderBy != null) {
			if (desc != null) {
				if (desc) {
					crit.addOrder(Order.desc(orderBy));
				} else {
					crit.addOrder(Order.asc(orderBy));
				}
			}
		}

		return crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

	}

	@SuppressWarnings("unchecked")
	public List<T> getDataList(final List<Criterion> criterion, String[] joinTables, final int start,
			final int pageSize, final String orderBy, final Boolean desc) {
		Criteria crit = getSession().createCriteria(getClazz());
		// createAlias
		if (joinTables != null && joinTables.length > 0) {
			for (String table : joinTables) {
				crit.createAlias(table, table);
			}
		}

		if (criterion != null && criterion.size() > 0) {
			for (Criterion critObj : criterion) {
				if (critObj != null) {
					crit.add(critObj);
				}
			}
		}

		if (start >= 0)
			crit.setFirstResult(start);
		if (pageSize > 0)
			crit.setMaxResults(pageSize);
		if (orderBy != null) {
			if (desc != null) {
				if (desc) {
					crit.addOrder(Order.desc(orderBy));
				} else {
					crit.addOrder(Order.asc(orderBy));
				}
			}
		}

		return crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

	}

	// -----------------------------------
	public Long getRowCount(List<Criterion> criterion, String[] joinTables) {
		Long count = null;
		try {
			Criteria crit = getSession().createCriteria(getClazz());

			if (joinTables != null && joinTables.length > 0 && criterion != null) {
				for (String table : joinTables) {
					crit.createAlias(table, table);
				}
			}

			if (criterion != null && criterion.size() > 0) {
				for (Criterion critObj : criterion) {
					if (critObj != null) {
						crit.add(critObj);
					}
				}
			}
			crit.setProjection(Projections.rowCount());
			count = (Long) crit.uniqueResult();
			System.out.println("Abstarct Long :" + count);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;

	}

	public Long getRowCount(List<Criterion> criterion) {
		Criteria crit = getSession().createCriteria(getClazz());

		if (criterion != null && criterion.size() > 0) {
			for (Criterion critObj : criterion) {
				if (critObj != null) {
					crit.add(critObj);
				}
			}
		}
		crit.setProjection(Projections.rowCount());
		Long count = (Long) crit.uniqueResult();
		return count;

	}

	public Class<?> getClazz() {
		return clazz;
	}

	@SuppressWarnings("unchecked")
	public T mergeModel(T model) {
		return (T) getSession().merge(model);

	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getDataList(final List<Criterion> criterion, final ProjectionList projectionList,
			final int start, final int pageSize, final String orderBy, final Boolean desc) {
		Criteria crit = getSession().createCriteria(getClazz());
		// createAlias

		if (criterion != null && criterion.size() > 0) {
			for (Criterion critObj : criterion) {
				if (critObj != null) {
					crit.add(critObj);
				}
			}
		}

		if (projectionList != null) {

			crit.setProjection(projectionList);
		}

		if (start >= 0)
			crit.setFirstResult(start);

		if (pageSize > 0)
			crit.setMaxResults(pageSize);
		if (orderBy != null) {
			if (desc != null) {
				if (desc) {
					crit.addOrder(Order.desc(orderBy));
				} else {
					crit.addOrder(Order.asc(orderBy));
				}
			}
		}

		return (List<Object[]>) crit.list();

	}

	// ------------------------------------------------
	@SuppressWarnings("unchecked")
	public List<Object[]> getDataList(final List<Criterion> criterion, final ProjectionList projectionList,
			final String[] joinTables, final int start, final int pageSize, final String orderBy, final Boolean desc) {

		try {

			Criteria crit = getSession().createCriteria(getClazz());
			// createAlias

			if (criterion != null && criterion.size() > 0) {
				for (Criterion critObj : criterion) {
					if (critObj != null) {
						crit.add(critObj);
					}
				}
			}

			if (joinTables != null && joinTables.length > 0) {
				for (String table : joinTables) {
					crit.createAlias(table, table);
					crit.setFetchMode(table, FetchMode.JOIN);
				}
			}

			if (projectionList != null) {

				crit.setProjection(projectionList);
			}

			if (start >= 0)
				crit.setFirstResult(start);

			if (pageSize > 0)
				crit.setMaxResults(pageSize);
			if (orderBy != null) {
				if (desc != null) {
					if (desc) {
						crit.addOrder(Order.desc(orderBy));
					} else {
						crit.addOrder(Order.asc(orderBy));
					}
				}
			}

			return (List<Object[]>) crit.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Long getRowMax(String fieldName) {
		Criteria crit = getSession().createCriteria(getClazz());
		crit.setProjection(Projections.max(fieldName));
		Long max = (Long) crit.uniqueResult();
		return max;

	}
	
	public Double getSumPendingAmount(List<Criterion> criterion , String fieldName) {
		Criteria crit = getSession().createCriteria(getClazz());

		if (criterion != null && criterion.size() > 0) {
			for (Criterion critObj : criterion) {
				if (critObj != null) {
					crit.add(critObj);
				}
			}
		}
		crit.setProjection(Projections.sum(fieldName));
		Double sum = (Double) crit.uniqueResult();
		return sum;

	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCountByField(String fieldName,List<Criterion> criterion) {
		try {
		Criteria crit = getSession().createCriteria(getClazz());
		if (criterion != null && criterion.size() > 0) {
			for (Criterion critObj : criterion) {
				if (critObj != null) {
					crit.add(critObj);
				}
			}
		}
		
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.count(fieldName));
		projList.add(Projections.groupProperty(fieldName));
		
		crit.setProjection(projList);
		crit.addOrder(Order.asc(fieldName));
		//Long max = (Long) crit.uniqueResult();
		return (List<Object[]>) crit.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSumAndCountByField(String fieldName,String sum1,String sum2,List<Criterion> criterion) {
		try {
		Criteria crit = getSession().createCriteria(getClazz());
		
		if (criterion != null && criterion.size() > 0) {
			for (Criterion critObj : criterion) {
				if (critObj != null) {
					crit.add(critObj);
				}
			}
		}
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.count(fieldName));

		if(sum1!=null) {
			projList.add(Projections.sum(sum1));
		}
		if(sum2!=null) {
			projList.add(Projections.sum(sum2));
		}
		
		if(fieldName!=null) {
		projList.add(Projections.groupProperty(fieldName));
		}
		crit.setProjection(projList);
		
		if(fieldName!=null) {
		crit.addOrder(Order.asc(fieldName));
		}
		//Long max = (Long) crit.uniqueResult();
		return (List<Object[]>) crit.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getListOfObjectArrayWithSQL(final String hqlStatement,
			final Map<String, Object> paramMap, final int startRecord,
			final int numberOfRecord){
		try {
			Query query = getSession().createSQLQuery(hqlStatement);
			
			query = query.setProperties(paramMap);
			if (numberOfRecord != 0) {
				query.setFirstResult(startRecord);
				query.setMaxResults(numberOfRecord);
			}
			return (List<Object[]>) query.list();
		}catch (Exception ex) {}
		
		 return null;
	}
	
	public Long getCount(List<Criterion> criterion ) {
		Criteria crit = getSession().createCriteria(getClazz());

		if (criterion != null && criterion.size() > 0) {
			for (Criterion critObj : criterion) {
				if (critObj != null) {
					crit.add(critObj);
				}
			}
		}
		crit.setProjection(Projections.rowCount());
		Long sum = (Long) crit.uniqueResult();
		return sum;

	}
	
	//---------------------Generic Delte
	public void deleteWithHQL(final String hqlStatement) {

		try {
					Query query = getSession().createSQLQuery(hqlStatement);
					query.executeUpdate();
					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
