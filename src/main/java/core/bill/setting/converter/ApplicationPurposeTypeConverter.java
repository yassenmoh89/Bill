package core.bill.setting.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import core.bill.comercial.bean.CustomerBean;
import core.bill.setting.model.ApplicationPurposeTypeDTO;


@FacesConverter(value = "applicationPurposeTypeConverter")
public class ApplicationPurposeTypeConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		try {
			
				ValueExpression vex =
							context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(),
                                "#{customerBean}", CustomerBean.class);

				CustomerBean customBean = (CustomerBean)vex.getValue(context.getELContext());
				
				if(customBean !=null && customBean.getAppPurposeList() !=null && value!=null)
				{
					for(ApplicationPurposeTypeDTO obj :customBean.getAppPurposeList())
					{
						if(obj.getApplicationPurposeTypeId()==(Long.valueOf(value)))
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
			return ((ApplicationPurposeTypeDTO)value).getApplicationPurposeTypeId().toString();
		}
		return null ; 
	}

}
