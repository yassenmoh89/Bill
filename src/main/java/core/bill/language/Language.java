package core.bill.language;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;


import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;


public class Language implements Serializable{
	
	private String locale;
	private Map<String,Object> countryMap;
	
	
	public 	Language()
	{
		countryMap = new LinkedHashMap<String,Object>();
		countryMap.put("English", new Locale("en"));
		countryMap.put("Espaniol", new Locale("es","Es"));
	}


	public String getLocale() {
		return locale;
	}


	public void setLocale(String locale) {
		this.locale = locale;
	}


	public Map<String, Object> getCountryMap() {
		return countryMap;
	}


	public void setCountryMap(Map<String, Object> countryMap) {
		this.countryMap = countryMap;
	}
	
	public void localeChanged(ValueChangeEvent e){

		String localeStr = e.getNewValue().toString();

		for (Map.Entry<String, Object> entry : countryMap.entrySet()) {
		if(entry.getValue().toString().equals(localeStr)){
		Locale locale = (Locale)entry.getValue();
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		}
		}
		}
	

}
