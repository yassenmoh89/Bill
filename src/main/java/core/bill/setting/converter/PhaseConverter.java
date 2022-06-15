package core.bill.setting.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import core.bill.comercial.bean.ContractBean;
import core.bill.setting.model.PhaseDTO;

@FacesConverter(value = "phaseConverter")
public class PhaseConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			ValueExpression vex =
					context.getApplication().getExpressionFactory()
	                        .createValueExpression(context.getELContext(),
	                                "#{contractBean}", ContractBean.class);

			ContractBean contractBean = (ContractBean)vex.getValue(context.getELContext());
			if(contractBean !=null && contractBean.getPhaseList() !=null && value!=null)
			{
				for(PhaseDTO obj :contractBean.getPhaseList() )
				{
					if(obj.getPhaseId().equals(Long.valueOf(value)))
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
			return ((PhaseDTO)value).getPhaseId().toString();
		}
		return null ; 
	}

}
