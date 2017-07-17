package com;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.LogInDAO;


@WebServlet("/Join")
public class Join extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				request.setCharacterEncoding("utf-8");		
		
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String name = request.getParameter("name");
			
			
				LogInDAO dao = new LogInDAO();
				
				int cnt  = dao.join(email, password, name);
				
				if(cnt != -1){
					response.sendRedirect("LogInPage.jsp");
					System.out.println("¼º°ø");
				}
			
	}

}
