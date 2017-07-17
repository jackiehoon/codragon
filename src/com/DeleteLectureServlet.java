package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MainDAO;

/**
 * Servlet implementation class DeleteLectureServlet
 */
@WebServlet("/DeleteLecture")
public class DeleteLectureServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String example_id = request.getParameter("example_id");
		String person_id = request.getParameter("person_id");
		String party_id = request.getParameter("party_id");
		String lecture_id = request.getParameter("lecture_id");
		
		MainDAO dao = new MainDAO();
		dao.deleteLecture(lecture_id,person_id);
		
		response.sendRedirect("main?party_id="+party_id);
	}

}
