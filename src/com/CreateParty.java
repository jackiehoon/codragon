package com;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.LogInDAO;
import dto.PersonDTO;

@WebServlet("/CreateParty")
public class CreateParty extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 String title = request.getParameter("title");
		 System.out.println(title);
		 LogInDAO dao = new LogInDAO();
		 
		 HttpSession session = request.getSession();
		 
		 PersonDTO person = (PersonDTO)session.getAttribute("Person"); 
			
		 String person_id = person.getId(); 
		
		 
		 String party_id = dao.createParty(title, person_id);         // �Է�â���� ��Ƽ �̸��� ��������  ���ǿ��� ���̵� �����ͼ� ��Ƽ ����.. 
		 
		 response.sendRedirect("main?party_id="+party_id);
		 
		 
	}

}
