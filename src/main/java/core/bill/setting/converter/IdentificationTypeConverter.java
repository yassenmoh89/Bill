package core.bill.setting.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import core.bill.comercial.bean.CustomerBean;
import core.bill.setting.model.IdentificationTypeDTO;

@FacesConverter(value = "identificationTypeConverter")
public class IdentificationTypeConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
		ValueExpression vex =
				context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(),
                                "#{customerBean}", CustomerBean.class);

		CustomerBean customBean = (CustomerBean)vex.getValue(context.getELContext());
		if(customBean !=null && customBean.getIdentificationList() !=null && value!=null)
		{
			for(IdentificationTypeDTO obj :customBean.getIdentificationList())
			{
				if(obj.getIdentificationTypeId().equals(Long.valueOf(value)))
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
			return ((IdentificationTypeDTO)value).getIdentificationTypeId().toString();
		}
		return null ; 
	}

}
