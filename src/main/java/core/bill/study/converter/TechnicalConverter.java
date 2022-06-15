package core.bill.study.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import core.bill.study.bean.StudyApplicationBean;

import core.bill.study.model.StudyTechnicalDTO;

@FacesConverter(value = "technicalConverter")
public class TechnicalConverter implements Converter{
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
		ValueExpression vex =
				context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(),
                                "#{studyApplicationBean}", StudyApplicationBean.class);

		StudyApplicationBean studyAppBean = (StudyApplicationBean)vex.getValue(context.getELContext());
		if(studyAppBean !=null && studyAppBean.getTechnicalList() !=null && value!=null)
		{
			for(StudyTechnicalDTO obj :studyAppBean.getTechnicalList())
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
