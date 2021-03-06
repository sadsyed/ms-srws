package ms.sr.pocketsphinx;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadHandler extends HttpServlet {
	 //private final String UPLOAD_DIRECTORY = "/home/parallels/tomcat7/apache-tomcat-7.0.54/data";
	 private final String UPLOAD_DIRECTORY = "/home/ubuntu/apache-tomcat-7.0.54/data";
	 
	 @Override
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	  	throws ServletException, IOException {
		 /*String url = ((HttpServletRequest)request).getRequestURL().toString();
		 System.out.println("URL: " + url);*/
		 
		 System.out.println("Upload Directory: " + UPLOAD_DIRECTORY);
		 
		 String fileNameValue = null;
		 String fileUploadDirectory = null;
		 
		  //process only if its multipart content
		  if(ServletFileUpload.isMultipartContent(request)) {
			  try {
				  /*DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
				  ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
				  
				  List<FileItem> multiparts = servletFileUpload.parseRequest(request);*/
				  List<FileItem> multiparts = new ServletFileUpload(
						  												new DiskFileItemFactory()).parseRequest(request);
				  
				  for(FileItem item : multiparts){
					  if(!item.isFormField()){
						  fileNameValue = new File(item.getName()).getName();
						  System.out.println("File Name :" + fileNameValue);
						  fileUploadDirectory = UPLOAD_DIRECTORY + File.separator + fileNameValue;
						  System.out.println("File Upload Path :" + fileUploadDirectory );
						  item.write(new File(fileUploadDirectory));
					  }
				  }
				  
				  //File uploaded successfuly
				  request.setAttribute("message", "File Uploaded Successfully");
			  } catch(Exception ex) {
				  request.setAttribute("message", "File Upload Failed due to " + ex);
			  }
		  } else {
			  request.setAttribute("message", "Sorry this servlet  only handles file upload request");
		  }
		  
		  HttpSession session = request.getSession();
		  
		  if(fileNameValue !=null && fileNameValue.endsWith("wav")){
			  System.out.println("File Extension is wav");
			  
			  session.setAttribute("uploadedFilePath", fileUploadDirectory);
			  response.sendRedirect("recognizer.jsp");
			  //request.getRequestDispatcher("/recognizer.jsp").forward(request,  response);
		  }
		  else{
			  request.getRequestDispatcher("/languageModel.jsp").forward(request, response);
		  }

	  }
}
