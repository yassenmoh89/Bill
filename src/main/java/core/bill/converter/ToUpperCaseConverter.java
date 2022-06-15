package core.bill.converter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="toUpperCase")
public class ToUpperCaseConverter implements Converter{

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String submittedValue) {
		if (submittedValue == null) { 
            return null;
        }

        return submittedValue.toUpperCase();
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object modelValue) {
		if (!(modelValue instanceof String)) { 
            return null; // Or throw ConverterException.
        }
		
        return modelValue.toString().toUpperCase();
	}
	

	
}
