package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.LogInDAO;

@WebServlet("/AddPerson")
public class AddPartyPerson extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String email = request.getParameter("email");
		 String party_id = request.getParameter("party_id");
		 
		LogInDAO dao = new LogInDAO();
		int cnt = dao.addPartyPerson(email, party_id);   //파티원 추가
		
		System.out.println(cnt);
	}

}
