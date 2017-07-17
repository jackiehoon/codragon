package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MainDAO;


@WebServlet("/UpdateExample")
public class UpdateExampleServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String example_id = request.getParameter("example_id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		MainDAO dao = new MainDAO();
		dao.updateExample(example_id,title,content);
		
		String referer = request.getHeader("Referer");
	    response.sendRedirect(referer);

	}

}
