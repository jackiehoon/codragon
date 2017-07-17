package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MainDAO;

@WebServlet("/BanStudent")
public class BanStudent extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String person_id = request.getParameter("person_id");
		String party_id = request.getParameter("party_id");
		
		
		MainDAO dao = new MainDAO();
		dao.banStudent(person_id, party_id);
		
		
	}

}
