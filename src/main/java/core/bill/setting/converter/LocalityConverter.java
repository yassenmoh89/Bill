package core.bill.setting.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import core.bill.comercial.bean.CustomerBean;
import core.bill.setting.model.LocalityDTO;

@FacesConverter(value = "localityConverter")
public class LocalityConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
		ValueExpression vex =
				context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(),
                                "#{customerBean}", CustomerBean.class);

		CustomerBean customBean = (CustomerBean)vex.getValue(context.getELContext());
		
		if(customBean !=null && customBean.getLocalityList() !=null && value!=null)
		{
	
			for(LocalityDTO obj :customBean.getLocalityList())
			{
				if(obj.getLocalityId()==(Long.valueOf(value)))
				{
					return obj ;
				}
			}
		}
		} catch (Exception e) {
			return null ;
		}
        return null ;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value !=null)
		{
			return ((LocalityDTO)value).getLocalityId().toString();
		}
		return null ; 
	}

}
