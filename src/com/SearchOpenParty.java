package com;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.LogInDAO;
import dto.PartyDTO;

/**
 * Servlet implementation class SearchOpenParty
 */
@WebServlet("/SearchOpenParty")
public class SearchOpenParty extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		
		LogInDAO dao = new LogInDAO();
		
		ArrayList<PartyDTO> list = dao.getPartyByTitle(title);
		System.out.println(list.size());
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/searchPartyList.jsp").forward(request,response);
		
	}

}
