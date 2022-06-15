package core.bill.setting.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import core.bill.comercial.bean.ContractBean;
import core.bill.setting.model.SectorDTO;

@FacesConverter(value = "sectorConverter")
public class SectorConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			ValueExpression vex =
					context.getApplication().getExpressionFactory()
	                        .createValueExpression(context.getELContext(),
	                                "#{contractBean}", ContractBean.class);

			ContractBean contractBean = (ContractBean)vex.getValue(context.getELContext());
			if(contractBean !=null && contractBean.getSectorList() !=null && value!=null)
			{
				for(SectorDTO obj :contractBean.getSectorList() )
				{
					if(obj.getSectorId().equals(Long.valueOf(value)))
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
			return ((SectorDTO)value).getSectorId().toString();
		}
		return null ; 
	}

}
