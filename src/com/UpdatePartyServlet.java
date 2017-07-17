package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MainDAO;


@WebServlet("/UpdateParty")
public class UpdatePartyServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String party_id = request.getParameter("party_id");
		String description = request.getParameter("description");
		MainDAO dao =new MainDAO();
		dao.updateParty(title,party_id,description);
		
		String referer = request.getHeader("Referer");
	    response.sendRedirect(referer);
	}

}
