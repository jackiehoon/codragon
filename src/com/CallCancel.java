package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.LogInDAO;
import dto.MainDAO;

@WebServlet("/CallCancel")
public class CallCancel extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String person_id = request.getParameter("person_id");
	    String party_id = request.getParameter("party_id");
	    
	    MainDAO dao = new MainDAO();
	    dao.callCancel(person_id, party_id);
	}

}
