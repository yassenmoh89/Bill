package core.bill.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import core.bill.common.dao.AbstractDAO;
import core.bill.user.model.GroupSearch;
import core.bill.user.model.UserDTO;
import core.bill.user.model.UserRoleDTO;


@Component
public class UserRoleGroupDAOImp extends AbstractDAO<UserRoleDTO> implements UserRoleGroupDAO{

	@Override
	public Class<UserRoleDTO> getClazz() {
		return UserRoleDTO.class;
	}
	@Override
	public UserRoleDTO save(UserRoleDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UserRoleDTO model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserRoleDTO update(UserRoleDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRoleDTO getModel(Long pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public List<UserRoleDTO> getList(GroupSearch certieria, int start, int pageSize, String orderBy, Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		return getDataList(criterion, start, pageSize, orderBy, desc);
	}

	private List<Criterion> getCriteria(GroupSearch certieria) {
		List<Criterion> critList = null;
		if (certieria != null) {
			critList = new ArrayList<Criterion>();
			if(certieria.getGroupId()!=null) {
				critList.add(Restrictions.eq("groupDTO.groupId", certieria.getGroupId()));
			}
		}		
		return critList;
		}
	@Override
	public Long getRowNum(GroupSearch certieria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRoleDTO merge(UserRoleDTO model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(UserRoleDTO model) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<UserRoleDTO> getList(GroupSearch critiria, ProjectionList projectionList, int start, int pageSize,
			String orderBy, Boolean desc) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	@Transactional(readOnly = false)
	public List<UserDTO> getUserList(GroupSearch critiria, ProjectionList projectionList, int start, int pageSize,
			String orderBy, Boolean desc) {
		List<UserDTO> list=null;
		try {
			
			
		    List<Criterion> criterion = getCriteria(critiria);
		    String [] tables= {"userDTO","userDTO.userProfileDTO"};
			List<Object[]> listObj = getDataList(criterion, projectionList,tables,start, pageSize, orderBy, desc);
			
			if(listObj !=null)
			{
	
			for (Object[] obj : listObj) {
				if (list == null) {
					list = new ArrayList<UserDTO>();
				}
				UserDTO usr= new UserDTO();
				usr.setUserId((Long)obj[0]);
				usr.setUserName(obj[1].toString());
				if(obj[2]!=null) {
					usr.setUserCode(obj[2].toString());
				}
				
				if(obj[3]!=null) {
					usr.setActive((Boolean) obj[3]);
				}
			

				list.add(usr);
			}
			}
		}
			catch (Exception e) {
				e.printStackTrace();
				return list;
			}
		return list;
	}



}
