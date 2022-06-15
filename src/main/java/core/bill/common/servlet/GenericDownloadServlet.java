package core.bill.common.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import core.bill.upload.model.UploadFileDTO;
import core.bill.upload.serviceProvider.UploadFileSharedService;


public class GenericDownloadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(GenericDownloadServlet.class);

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("================= GenericDownloadServlet =================");
		 ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		UploadFileSharedService uploadFileSharedService = (UploadFileSharedService)context.getBean("uploadFileSharedService");
		
		System.out.println("request.getPathInfo().substring(1) "+request.getPathInfo().substring(1) );
		String downloadName = "";
		if (request.getPathInfo().substring(1) != null &&  !request.getPathInfo().substring(1).equals("")) {
			String fileName = request.getPathInfo().substring(1);
			String fileURI = "";
			byte[] buf = new byte[1024];
			long length = 0;

			response.setHeader("Cache-Control", "no-cache"); // Forces caches to obtain a new copy of the page from the
																// origin server
			response.setHeader("Cache-Control", "no-store"); // Directs caches not to store the page under any
																// circumstance
			response.setDateHeader("Expires", 0); // Causes the proxy cache to see the page as "stale"
			response.setHeader("Pragma", "no-cache"); // HTTP 1.0 backward compatibility

			Long fileID = Long.valueOf(fileName);
			logger.info("Document fileID :" + fileID);

			try {
				UploadFileDTO fileDto = uploadFileSharedService.getFileDTO(fileID);
				if (fileDto != null) {
					logger.info("getFolder :" + fileDto.getFolder());
					logger.info("getFileName :" + fileDto.getFileName());
					downloadName = fileDto.getFileName();
					fileURI = fileDto.getPath() + File.separator + fileDto.getFolder() + File.separatorChar
							+ fileDto.getFileName();

					logger.info("fileURI :" + fileURI);
				}

				File file = new File(fileURI);
				length = file.length();
				logger.info(" file length :" + length);
				response.setHeader("Content-Length", String.valueOf(file.length()));
				response.setHeader("Content-Type", getServletContext().getMimeType(fileURI));
				response.setHeader("Content-Disposition", "inline; filename=\"" + downloadName + "\"");

				BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
				ServletOutputStream out = response.getOutputStream();
				response.setContentLength((int) length);

				while ((in != null) && ((length = in.read(buf)) != -1)) {
					out.write(buf, 0, (int) length);
				}

				in.close();
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// -------------------------------------file System ----------------
	public String getDocumentType(String fileName) {
		String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
		String back = "";
		if (fileExtension.equalsIgnoreCase("pdf")) {
			back = "application/pdf";
		} else if (fileExtension.equalsIgnoreCase("doc") || fileExtension.equalsIgnoreCase("docx")) {
			back = "application/msword";
		} else if (fileExtension.equalsIgnoreCase("jpg")) {
			back = "image/jpeg";
		} else if (fileExtension.equalsIgnoreCase("jpeg")) {
			back = "image/jpeg";
		} else if (fileExtension.equalsIgnoreCase("png")) {
			back = "image/png";
		}
		return back;
	}
}
