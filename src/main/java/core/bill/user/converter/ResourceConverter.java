package core.bill.user.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import core.bill.user.model.ResourceDTO;

@FacesConverter(value = "resourceConverter")
public class ResourceConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value!=null)
		{
				@SuppressWarnings("unchecked")
				List<ResourceDTO> model = (List<ResourceDTO>) ((UIInput) component).getValue();
				
				 for (ResourceDTO res : model) {
			          if (res.getRoleResourceId().equals(Long.valueOf(value))) {
			              return res;
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
			return String.valueOf(((ResourceDTO) value).getRoleResourceId()) ;
		}
	}

}
