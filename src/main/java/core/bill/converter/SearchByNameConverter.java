package core.bill.converter;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import core.bill.comercial.model.Theme;

@FacesConverter(value="searchEng")
public class SearchByNameConverter implements Converter{
	
	private DataSource ds;
	private Connection conn=null; 
	private PreparedStatement ps=null;
	private ResultSet rs;
	
	
	public SearchByNameConverter() {
		
	}
	
	
	public Object getAsObject(FacesContext facesContext, UIComponent component, String key) {  
		if(key==null || key=="")
		{
			return "";
		}else
		{
			//System.out.println("Converter :"+key);
			SearchByNameDAO clientDAO = new SearchByNameDAO();
			Theme client = (Theme)clientDAO.seacrhName(key.trim());
			//System.out.println("fucking client :"+client.getDisplayName());
			return client;
		}
		
		
    }  
  
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
        	 
        	Theme client = new Theme();
        	 client = (Theme) value;
        	 return client.getName();

        }  
    }
	

	

	
	

}
