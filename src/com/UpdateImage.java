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
 * Servlet implementation class UpdateImage
 */
@WebServlet("/UpdateImage")
public class UpdateImage extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ServletContext context = getServletContext();
		String path = context.getRealPath("upload");
		int maxSize  = 1024*1024*10;    
		MultipartRequest multi = new MultipartRequest(request, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		String image_id = multi.getParameter("image_id"); 
		String description = multi.getParameter("description"); 
		
		MainDAO dao = new MainDAO();

	    String uploadFile = multi.getFilesystemName("image");
	 
	    System.out.println(image_id);
	    dao.updateImage(image_id, uploadFile,description);
	    
	    String referer = request.getHeader("Referer");
	    response.sendRedirect(referer);
	}

}
