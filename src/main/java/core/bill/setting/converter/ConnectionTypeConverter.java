package core.bill.setting.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import core.bill.comercial.bean.ContractBean;
import core.bill.setting.model.ConnectionTypeDTO;


@FacesConverter(value = "connectionTypeConverter")
public class ConnectionTypeConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			ValueExpression vex =
					context.getApplication().getExpressionFactory()
	                        .createValueExpression(context.getELContext(),
	                                "#{contractBean}", ContractBean.class);

			ContractBean contractBean = (ContractBean)vex.getValue(context.getELContext());
			if(contractBean !=null && contractBean.getConnList() !=null && value!=null)
			{
				for(ConnectionTypeDTO obj : contractBean.getConnList())
				{
					if(obj.getConnectionTypeId().equals(Long.valueOf(value)))
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
			return ((ConnectionTypeDTO)value).getConnectionTypeId().toString();
		}
		return null ;
	}

}
