package core.bill.setting.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import core.bill.invoice.bean.OtherPaymentBean;
import core.bill.invoice.model.ChargeDTO;


@FacesConverter(value = "chargeConverter")
public class ChargeTypeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			ValueExpression vex =
					context.getApplication().getExpressionFactory()
	                        .createValueExpression(context.getELContext(),
	                                "#{othPayment}", OtherPaymentBean.class);

			OtherPaymentBean otherPayBean = (OtherPaymentBean)vex.getValue(context.getELContext());
			if(otherPayBean !=null && otherPayBean.getChargeList() !=null && value!=null)
			{
				for(ChargeDTO obj :otherPayBean.getChargeList() )
				{
					if(obj.getChargeId().equals(Long.valueOf(value)))
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
			return ((ChargeDTO)value).getChargeId().toString();
		}
		return null ;
	}

}
