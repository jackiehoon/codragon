package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MainDAO;

/**
 * Servlet implementation class DeleteImageServlet
 */
@WebServlet("/DeleteImage")
public class DeleteImageServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String image_id = request.getParameter("image_id");
		String person_id = request.getParameter("person_id");
		String url = request.getParameter("url");
		MainDAO dao = new MainDAO();
		dao.deleteImage(image_id,person_id);
		
		
	    response.sendRedirect(url);
	}

}
