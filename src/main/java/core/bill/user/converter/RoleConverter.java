package core.bill.user.converter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import core.bill.user.model.RoleDTO;


@FacesConverter(value = "roleConverter")
public class RoleConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value!=null)
		{
				@SuppressWarnings("unchecked")
				DualListModel<RoleDTO> model = (DualListModel<RoleDTO>) ((PickList) component).getValue();
				
				 for (RoleDTO role : model.getSource()) {
			          if (role.getRoleId().equals(Long.valueOf(value))) {
			              return role;
			          }
			      }
				 
				 
				 for (RoleDTO role : model.getTarget()) {
			          if (role.getRoleId().equals(Long.valueOf(value))) {
			              return role;
			          }
			      }
				 
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value==null) {
			return null;
		}else
		{
			return String.valueOf(((RoleDTO) value).getRoleId()) ;
		}
	}


}
