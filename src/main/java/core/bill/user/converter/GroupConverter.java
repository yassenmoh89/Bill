package core.bill.user.converter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import core.bill.user.model.GroupDTO;

@FacesConverter(forClass = GroupDTO.class,  value = "groupConverter")
public class GroupConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent arg1, String arg2) {
		
		 Object ret = null;
		    if (arg1 instanceof PickList) {
		        Object dualList = ((PickList) arg1).getValue();
		        DualListModel dl = (DualListModel) dualList;
		        for (Object o : dl.getSource()) {
		            String id = (((GroupDTO) o).getGroupId()).toString();
		            if (arg2.equals(id)) {
		                ret = o;
		                break;
		            }
		        }
		        if (ret == null)
		            for (Object o : dl.getTarget()) {
		                String id = (((GroupDTO) o).getGroupId()).toString();
		                if (arg2.equals(id)) {
		                    ret = o;
		                    break;
		                }
		            }
		    }
		    return ret;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent arg1, Object arg2) {
		String str = "";
	    if (arg2 instanceof GroupDTO) {
	        str = (((GroupDTO) arg2).getGroupId()).toString();
	    }
	    return str;
	}

}
