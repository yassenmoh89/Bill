package core.bill.common.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import core.bill.upload.model.UploadFileDTO;

public class CustomUtil {

	
	
	// Generic function to convert list to set
    public static <T> Set<T> convertListToSet(List<T> list)
    {
        // create an empty set
    	
        Set<T> set = new HashSet<>();
  
        // Add each element of list into the set
        if(list!=null) {
        for (T t : list)
            set.add(t);
        }
  
        // return the set
        return set;
    }
    
    public static <T> List<T> convertSetToList(Set<T> set)
    {
        // create an empty list
        List<T> list = new ArrayList<>();
  
        // push each element in the set into the list
        if(set!=null)
        {
        for (T t : set)
            list.add(t);
        }
  
        // return the list
        return list;
    }
    
    public static String  getBundlMSGValue(String key)
    {
    	try{
    			ResourceBundle bundle =  ResourceBundle.getBundle(UtilConstant.BUNDLE_PACKAGE);
    			return bundle.getString(key) ;
    			
    	}catch(MissingResourceException ex)
    	{
    		System.out.println("Error : "+ex);
    	}
    	return "" ;
    }
    
    public static String  getBundlMSGValue(String key, String[] param)
    {
    	try{
    			ResourceBundle bundle =  ResourceBundle.getBundle(UtilConstant.BUNDLE_PACKAGE);
    			MessageFormat   messageFormat = new MessageFormat(bundle.getString(key));
    		    String[] args = param;
    			return messageFormat.format(args).toString();
    			
    	}catch(MissingResourceException ex)
    	{
    		System.out.println("Error : "+ex);
    	}
    	return "" ;
    }
    
    //---------------------------upload file
    public static String writeFile(String pathFile ,byte[] bytes)
	{
		try {

            Path path = Paths.get(pathFile);
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
            return "Fail";
        }
		return "success";
	}
    
    //---------------------------create folder 
    public static String createFolder(String path, String categoryType) throws Exception {
		// TODO Auto-generated method stub
		 String dir = "";

	        
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	        	dir = categoryType + sdf.format(new Date()) ;
	            File file = new File(path+File.separatorChar+dir);
	            
	            if(!file.exists())
	            {
	            	if (file.mkdirs()) {
	                    System.out.println("Directory is created!");
	                } else {
	                	dir =null ;
	                    System.out.println("Failed to create directory!");
	                }
	            }
	        
		return dir;
	}
    
    public static void downloadZip(List<UploadFileDTO> fileList) {
		 
		 
	 	try {
	//..code to add URLs to the list
	 	FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse _response = (HttpServletResponse) context.getExternalContext().getResponse();
		_response.setCharacterEncoding("UTF-8");
	    byte[] buf = new byte[2048];
	    // Create the ZIP file
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ZipOutputStream out = new ZipOutputStream(baos);
	    BufferedInputStream bis=null;
	    File file =null ;
	    BufferedInputStream fis=null;
	    // Compress the files
	    for (UploadFileDTO f:fileList) {
	    
	    file = new File(f.getPath() + File.separatorChar + f.getFolder() + File.separatorChar + f.getFileName());
		fis = new BufferedInputStream(new FileInputStream(file));
		bis = new BufferedInputStream(fis);
	    // Add ZIP entry to output stream.
	    //File file = new File(filenames.get(i).toString());
	    String entryname = file.getName();
	    out.putNextEntry(new ZipEntry(entryname));
	    int bytesRead;
	    while ((bytesRead = bis.read(buf)) != -1) {
	    out.write(buf, 0, bytesRead);
	    }
	    out.closeEntry();
	    bis.close();
	    fis.close();
	    context.responseComplete();
	    }
	    out.flush();
	    baos.flush();
	    out.close();
	    baos.close();
	    context.responseComplete();
	    ServletOutputStream sos = _response.getOutputStream();
	    _response.setContentType("application/zip");
	    _response.setHeader("Content-Disposition", "attachment; filename=\"segesaContract.ZIP\"");
	    sos.write(baos.toByteArray());
	    out.flush();
	    out.close();
	    sos.flush();
	 			} catch (Exception e) {
	 				// TODO Auto-generated catch block
	 				e.printStackTrace();
	 			}
		} 
    //---------------------------------------------------------
    
    public  static void download(UploadFileDTO fileDto) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		
		String fileName = encodeValue(fileDto.getFileName()) ;
		//System.out.println("Select file before Decode >>>>"+ systemFile);	
		String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);		
		//System.out.println("Select file name is >>>>"+ fileName);	
		
		response.setCharacterEncoding("UTF-8");
		if (fileExtension.equalsIgnoreCase("pdf")) {
			response.setContentType("application/pdf");
		} else if (fileExtension.equalsIgnoreCase("doc") || fileExtension.equalsIgnoreCase("docx")) {
			response.setContentType("application/msword");
		}else if (fileExtension.equalsIgnoreCase("jpg")) {
			response.setContentType("image/jpeg");
		}else if (fileExtension.equalsIgnoreCase("jpeg")) {
			response.setContentType("image/jpeg");
		}else if (fileExtension.equalsIgnoreCase("png")) {
			response.setContentType("image/png");
		}else if(fileExtension.equalsIgnoreCase("xml"))
		{
			response.setContentType("text/xml");
		}else if(fileExtension.equalsIgnoreCase("xls"))
		{
			response.setContentType("application/vnd.ms-excel");
		}else if(fileExtension.equalsIgnoreCase("xlsx"))
		{
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		}
		
		response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		//System.out.println("Select file name is >>>>"+ url+fileName);	
		
		byte[] buf = new byte[1024];
		try {
			
			File file = null;
			file = new File(fileDto.getPath() + File.pathSeparatorChar + fileDto.getFolder() + File.pathSeparatorChar + fileDto.getFileName());
			long length = file.length();
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
			ServletOutputStream out = response.getOutputStream();
			response.setContentLength((int) length);
			while ((in != null) && ((length = in.read(buf)) != -1)) {
				out.write(buf, 0, (int) length);
			}
			in.close();
			out.close();
			context.responseComplete();
			
		} catch (Exception exc) {
			exc.printStackTrace();
		} 
		
	}

    public static  String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }
 //--------------------
 private static  String decodeValue(String value) {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }
    
}
