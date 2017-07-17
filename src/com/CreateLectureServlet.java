package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MainDAO;

/**
 * Servlet implementation class NewLectureServlet
 */
@WebServlet("/CreateLecture")
public class CreateLectureServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String party_id = request.getParameter("party_id");
		String title = request.getParameter("title");
		String lock = request.getParameter("lock");
		
		
		MainDAO dao = new MainDAO();
		
		String lecture_id = dao.createLecture(party_id,title,lock);
		
		response.sendRedirect("main?party_id="+party_id+"&lecture_id="+lecture_id);
	}

}
