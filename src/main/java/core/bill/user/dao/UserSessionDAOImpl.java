package core.bill.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.user.model.UserSessionDTO;
import core.bill.user.model.UserSearch;

@Component
public class UserSessionDAOImpl extends AbstractDAO<UserSessionDTO> implements UserSessionDAO{

	@Override
	public Class<UserSessionDTO> getClazz() {
		return UserSessionDTO.class;
	}
	
	@Override
	public UserSessionDTO save(UserSessionDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(UserSessionDTO model) {
		deleteModel(model);
	}

	@Override
	public UserSessionDTO update(UserSessionDTO model) {
		return updateModel(model, model.getSessionId());
	}

	@Override
	public UserSessionDTO getModel(Long pk) {
		return getModel(pk);
	}

	@Override
	public List<UserSessionDTO> getList(UserSearch certieria, int start, int pageSize, String orderBy, Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		return getDataList(criterion, start, pageSize, orderBy, desc);
	}

	private List<Criterion> getCriteria(UserSearch certieria) {
		List<Criterion> critList = null;
		if (certieria != null) {
			critList = new ArrayList<Criterion>();


			if(certieria.getUserName()!=null && certieria.getUserName().length()>0 )
			{
				critList.add(Restrictions.ilike("userName", "%"+certieria.getUserName()+"%"));
			}
		
		}
		return critList;
	}
	@Override
	public Long getRowNum(UserSearch certieria) {
		return getRowCount(getCriteria(certieria));
	}

	@Override
	public UserSessionDTO merge(UserSessionDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(UserSessionDTO model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserSessionDTO> getList(UserSearch critiria, ProjectionList projectionList, int start, int pageSize,
			String orderBy, Boolean desc) {
		List<UserSessionDTO> list =null ;
		try {
		
			List<Criterion> criterion = getCriteria(critiria);
			
			List<Object[]> listObj = getDataList(criterion, projectionList,start, pageSize, orderBy, desc);
			
			System.out.println("listObj size :"+listObj.size());
			for (Object[] obj : listObj) {
				if (list == null) {
					list = new ArrayList<UserSessionDTO>();
				}
				UserSessionDTO sessionDTO = new UserSessionDTO();
				sessionDTO.setSessionId(Long.valueOf(obj[0].toString()));
				sessionDTO.setUserName((String) obj[1]);
				
				list.add(sessionDTO);
				
	}
	} catch (Exception e) {
		e.printStackTrace();
		
	}
	
	return list;
	}

}
