package core.bill.setting.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import core.bill.comercial.bean.CustomerBean;
import core.bill.setting.model.HouseholdsDTO;


@FacesConverter(value = "householdsConverter")
public class HouseholdsConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
		ValueExpression vex =
				context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(),
                                "#{customerBean}", CustomerBean.class);

		CustomerBean customBean = (CustomerBean)vex.getValue(context.getELContext());
		if(customBean !=null && customBean.getHouseholdsList() !=null && value!=null)
		{
			for(HouseholdsDTO obj :customBean.getHouseholdsList())
			{
				if(obj.getHouseHoldingTypeId().equals(Long.valueOf(value)))
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
			return ((HouseholdsDTO)value).getHouseHoldingTypeId().toString();
		}
		return null ;
	}

}
