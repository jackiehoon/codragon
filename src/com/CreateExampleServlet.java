package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MainDAO;


@WebServlet("/CreateExample")
public class CreateExampleServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String title= request.getParameter("title");
		String content = request.getParameter("content");
		String party_id = request.getParameter("party_id");
		
		int lecture_id = Integer.parseInt(request.getParameter("lecture_id"));
		MainDAO dao = new MainDAO();
		String example_id = String.valueOf(dao.createExample(title,content,lecture_id));
		response.sendRedirect("main?party_id="+party_id+"&lecture_id="+lecture_id+"&example_id="+example_id);
	}

}
