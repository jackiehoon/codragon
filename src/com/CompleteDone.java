package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.LogInDAO;

@WebServlet("/CompleteDone")
public class CompleteDone extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String person_id = request.getParameter("person_id");
		String party_id = request.getParameter("party_id");
		
		LogInDAO dao = new LogInDAO();
		int cnt = dao.completeDone(party_id, person_id);    //party_id, person_id 를 받아와서 해당하는 사람의 done 을 1로 변경
		
		if(cnt == -1) System.out.println("실패");
		else System.out.println("성공");
	}

}
