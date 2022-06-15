package core.bill.user.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import core.bill.common.dao.AbstractDAO;
import core.bill.user.model.UserDTO;
import core.bill.user.model.UserProfileDTO;
import core.bill.user.model.UserRoleDTO;
import core.bill.user.model.UserSearch;

@Component
public class UserDAOImpl extends AbstractDAO<UserDTO> implements UserDAO {

	@Override
	public Class<UserDTO> getClazz() {
		return UserDTO.class;
	}

	@Override
	public UserDTO save(UserDTO model) {
		return saveModel(model);
	}

	@Override
	public void delete(UserDTO model) {
		deleteModel(model);
	}

	@Override
	public UserDTO update(UserDTO model) {
		return updateModel(model, model.getUserId());
	}

	@Override
	public UserDTO getModel(Long pk) {
		return getModelDTO(pk);
	}

	@Override
	public List<UserDTO> getList(UserSearch certieria, int start, int pageSize, String orderBy, Boolean desc) {
		List<Criterion> criterion = getCriteria(certieria);
		String[] table = { "userProfileDTO" };
		return getDataList(criterion, table, start, pageSize, orderBy, desc);
	}

	private List<Criterion> getCriteria(UserSearch certieria) {
		List<Criterion> critList = null;
		if (certieria != null) {
			critList = new ArrayList<Criterion>();

			if (certieria.getActive() != null) {
				if (certieria.getActive().equals("ACT")) {
					critList.add(Restrictions.eq("active", true));
				} else if (certieria.getActive().equals("INACT")) {
					critList.add(Restrictions.eq("active", false));
				}
			}
			if (certieria.getUserName() != null && certieria.getUserName().length() > 0) {
				critList.add(Restrictions.ilike("userName", "%" + certieria.getUserName() + "%"));
			}
			if (certieria.getUserCode() != null && certieria.getUserCode().length() > 0) {
				critList.add(Restrictions.ilike("userCode", "%" + certieria.getUserCode() + "%"));
			}
			if (certieria.getMobile() != null && certieria.getMobile().length() > 0) {
				critList.add(Restrictions.ilike("userProfileDTO.mobile", "%" + certieria.getMobile() + "%"));
			}
			if (certieria.getEmail() != null && certieria.getEmail().length() > 0) {
				critList.add(Restrictions.ilike("userProfileDTO.email", "%" + certieria.getEmail() + "%"));
			}
			if (certieria.getAddress() != null && certieria.getAddress().length() > 0) {
				critList.add(Restrictions.ilike("userProfileDTO.address", "%" + certieria.getAddress() + "%"));
			}
		}
		return critList;
	}

	@Override
	public Long getRowNum(UserSearch certieria) {
		String[] table = { "userProfileDTO" };
		return getRowCount(getCriteria(certieria), table);
	}

	@Override
	public UserDTO merge(UserDTO model) {
		return mergeModel(model);
	}

	@Override
	public void saveOrUpdate(UserDTO model) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserDTO> getList(UserSearch certieria, ProjectionList projectionList, int start, int pageSize,
			String orderBy, Boolean desc) {

		List<UserDTO> list = null;
		String[] table1 = null;
		try {

			List<Criterion> criterion = getCriteria(certieria);

			List<Object[]> listObj = getDataList(criterion, projectionList, table1, start, pageSize, orderBy, desc);

			System.out.println("listObj size :" + listObj.size());
			for (Object[] obj : listObj) {
				if (list == null) {
					list = new ArrayList<UserDTO>();
				}
				UserDTO userDTO = new UserDTO();
				userDTO.setUserId(Long.valueOf(obj[0].toString()));
				userDTO.setUserName((String) obj[1]);
				userDTO.setUserCode((String) obj[2]);

				list.add(userDTO);

			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return list;
	}

	@Override
	public List<UserDTO> getListUsers(UserSearch critiria, ProjectionList projectionList, int start, int pageSize,
			String orderBy, Boolean desc) {
		List<UserDTO> list = null;
		String[] table1 = { "userProfileDTO" };
		try {

			List<Criterion> criterion = getCriteria(critiria);

			List<Object[]> listObj = getDataList(criterion, projectionList, table1, start, pageSize, orderBy, desc);

			System.out.println("listObj size :" + listObj.size());
			for (Object[] obj : listObj) {
				if (list == null) {
					list = new ArrayList<UserDTO>();
				}
				UserDTO user = new UserDTO();
				user.setUserId(Long.valueOf(obj[0].toString()));

				if (obj[1] != null) {
					user.setUserName((String) obj[1]);
				}

				if (obj[2] != null) {
					user.setUserCode((String) obj[2]);
				}

				if (obj[3] != null) {
					
					user.setUserProfileDTO( new UserProfileDTO());
					user.getUserProfileDTO().setMobile((String) obj[3]);
				}

				if (obj[4] != null) {
					user.getUserProfileDTO().setEmail((String) obj[4]);
				}

				if (obj[1] != null) {
					user.setActive((Boolean) obj[5]);
				}

				list.add(user);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}

		return list;
	}

	@Override
	public void deleteUserRole(Long userRoleId) {
		try {
			
			StringBuilder delQuery = new StringBuilder();
			delQuery.append(" DELETE ") ;
			delQuery.append(" FROM ") ;
			delQuery.append(UserRoleDTO.class.getSimpleName());
			delQuery.append(" WHERE ") ;
			delQuery.append(" userRoleId = ") ;
			delQuery.append(userRoleId);
			
			deleteWithHQL(delQuery.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
		
		}

		
	}

}
