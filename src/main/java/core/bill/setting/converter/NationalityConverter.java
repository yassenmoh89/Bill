package core.bill.setting.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import core.bill.comercial.bean.CustomerBean;
import core.bill.setting.model.NationalityDTO;

@FacesConverter(value = "nationalityConverter")
public class NationalityConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
		ValueExpression vex =
				context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(),
                                "#{customerBean}", CustomerBean.class);

		CustomerBean customBean = (CustomerBean)vex.getValue(context.getELContext());
		if(customBean !=null && customBean.getNationalityList() !=null && value!=null)
		{
			for(NationalityDTO obj :customBean.getNationalityList())
			{
				if(obj.getNationalityId().equals(Long.valueOf(value)))
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
			return ((NationalityDTO)value).getNationalityId().toString();
		}
		return null ; 
	}

}
