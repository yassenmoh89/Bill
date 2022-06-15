package core.bill.setting.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import core.bill.comercial.bean.ContractBean;
import core.bill.setting.model.SupplyDTO;

@FacesConverter(value = "supplyConverter")
public class SupplyConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			ValueExpression vex =
					context.getApplication().getExpressionFactory()
	                        .createValueExpression(context.getELContext(),
	                                "#{contractBean}", ContractBean.class);

			ContractBean contractBean = (ContractBean)vex.getValue(context.getELContext());
			if(contractBean !=null && contractBean.getSupplyList() !=null && value!=null)
			{
				for(SupplyDTO obj : contractBean.getSupplyList()  )
				{
					if(obj.getSupplyId().equals(Long.valueOf(value)))
					{
						return obj ;
					}
				}
			}
			} catch (Exception e) {
				e.printStackTrace();
				return null ;
			}
	        return null ;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value !=null)
		{
			return ((SupplyDTO)value).getSupplyId().toString();
		}
		return null ;
	}

}
