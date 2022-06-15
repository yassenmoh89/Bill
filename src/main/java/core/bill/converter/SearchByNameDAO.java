package core.bill.converter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import core.bill.comercial.model.Theme;


public class SearchByNameDAO {
	private Connection conn=null; 
	private PreparedStatement ps=null;
	private DataSource ds;
	private ResultSet rs;
	
	
	public SearchByNameDAO()
	{
		try{
			Context ctx = new InitialContext();
	        ds=(DataSource)ctx.lookup("java:comp/env/jdbc/bill");
	       
	        }	catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			try {
				conn.close();
			} catch (Exception e2) {}
				// TODO: handle exception
		}
		
	}
			
	public Theme seacrhName(String code) {
	 	Theme client = new Theme();
	 	
	 	try {
			
	         conn= ds.getConnection();
	         String sql = " SELECT NumContrib,Nombre  FROM  CONTRIBUYENTES  where NumContrib = ?";
	         ps = conn.prepareStatement(sql);
	         ps.setString(1,code);
		         rs=ps.executeQuery();
	      
	        if(rs.next())
	        {
	        	client.setName(rs.getString(1));
	        	client.setDisplayName(rs.getString(2).toString());
	        	
	        }
	        
	           
	    	 } catch (Exception e) {
	 			// TODO: handle exception
	 		}   finally
			{
				try {
					conn.close();
				} catch (Exception e2) {
					System.out.println(e2);
				}} 
	 	
	 	return client;
	}
	
	//**************************************************//
	public ArrayList<Theme> findAll(String key) {
		 ArrayList<Theme> clientList = new ArrayList<Theme>();
		 
		 try {
			 conn= ds.getConnection();
	         ps = conn.prepareStatement("SELECT  NumContrib,Nombre  FROM  CONTRIBUYENTES where  Nombre  LIKE ?");
	         ps.setString(1,key+"%");
		         rs=ps.executeQuery();
	      
	        while(rs.next())
	        {
	        	Theme skin = new Theme();
	        	//skin.setId(i);
	        	skin.setName(rs.getString(1));
	        	skin.setDisplayName(rs.getString(2).toString());
	        	clientList.add(skin);
	        	//i++;
	        	
	        }
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 
		 
		 return clientList;
	}
		 


}
