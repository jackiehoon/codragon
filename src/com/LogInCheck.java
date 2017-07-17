package com;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.LogInDAO;
import dto.PersonDTO;
@WebServlet("/LogInCheck")
public class LogInCheck extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String input_email = request.getParameter("input_email");
			String input_pw = request.getParameter("input_password");
			
			
			LogInDAO dao = new LogInDAO();
			
			PersonDTO dto = dao.LogInCheck(input_email, input_pw); // 아이디와 패스워드가 같은지 확인
			
			if(dto != null){ // 로그인 성공시				
				HttpSession session = request.getSession();   //세션 생성
				session.setMaxInactiveInterval(60*60*60);
				session.setAttribute("Person", dto);
				  //인덱스 페이지에서 파티리스트를 출력해주기 위해 ShowPartyList 서블릿파일로 이동 				
				
			}
			response.sendRedirect("index");

	}
}
