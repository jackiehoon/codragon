package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MainDAO;

/**
 * Servlet implementation class DeletePartyServlet
 */
@WebServlet("/DeleteParty")
public class DeletePartyServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String person_id = request.getParameter("person_id");
		String party_id = request.getParameter("party_id");
		
		MainDAO dao = new MainDAO();
		dao.deleteParty(party_id,person_id);
		
		response.sendRedirect("index");
	}

}
