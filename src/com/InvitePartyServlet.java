package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MainDAO;

/**
 * Servlet implementation class InvitePartyServlet
 */
@WebServlet("/InviteParty")
public class InvitePartyServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String party_id = request.getParameter("party_id");
		String emails = request.getParameter("emails");
		String person_id = request.getParameter("person_id");
		
		MainDAO dao = new MainDAO();
		dao.inviteParty(party_id,emails,person_id);
		
		String referer = request.getHeader("Referer");
	    response.sendRedirect(referer);
	}

}
