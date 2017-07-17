package com;


import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.MainDAO;

/**
 * Servlet implementation class UploadImage
 */
@WebServlet("/UploadImage")
public class UploadImage extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ServletContext context = getServletContext();
		String path = context.getRealPath("upload");
		int maxSize  = 1024*1024*10;    
		MultipartRequest multi = new MultipartRequest(request, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		String example_id = multi.getParameter("example_id"); 
		String description = multi.getParameter("description"); 
		String url = multi.getParameter("url"); 
		MainDAO dao = new MainDAO();

	        // 파일업로드
	    String uploadFile = multi.getFilesystemName("image");
	       
	        // 실제 저장할 파일명(ex : 20140819151221.zip)
	 

	    String image_id = dao.createImage(example_id, uploadFile,description);
	    
	    response.sendRedirect(url+"&image_id="+image_id);


	}

}
