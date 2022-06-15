package core.bill.study.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import core.bill.study.bean.StudyApplicationUpdateBean;
import core.bill.study.model.StudyTechnicalDTO;

@FacesConverter(value = "updateTechnicalConverter")
public class UpdateTechnicalConverter implements Converter{
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
		ValueExpression vex =
				context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(),
                                "#{studyUpdateBean}", StudyApplicationUpdateBean.class);

		StudyApplicationUpdateBean studyUpdateAppBean = (StudyApplicationUpdateBean)vex.getValue(context.getELContext());
		if(studyUpdateAppBean !=null && studyUpdateAppBean.getTechnicalList() !=null && value!=null)
		{
			for(StudyTechnicalDTO obj :studyUpdateAppBean.getTechnicalList())
			{
				if(obj.getTechnicalId().equals(Long.valueOf(value)))
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
			return ((StudyTechnicalDTO)value).getTechnicalId().toString();
		}
		return null ; 
	}

}
