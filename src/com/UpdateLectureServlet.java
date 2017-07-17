package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MainDAO;


@WebServlet("/UpdateLecture")
public class UpdateLectureServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String lecture_id = request.getParameter("lecture_id");
		String lock = request.getParameter("lock");
		
		MainDAO dao = new MainDAO();
		
		dao.updateLecture(title,lecture_id, lock);
		
		
		
		
		String referer = request.getHeader("Referer");
	    response.sendRedirect(referer);
		
	}

}
