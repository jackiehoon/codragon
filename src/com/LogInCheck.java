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
			
			PersonDTO dto = dao.LogInCheck(input_email, input_pw); // ���̵�� �н����尡 ������ Ȯ��
			
			if(dto != null){ // �α��� ������				
				HttpSession session = request.getSession();   //���� ����
				session.setMaxInactiveInterval(60*60*60);
				session.setAttribute("Person", dto);
				  //�ε��� ���������� ��Ƽ����Ʈ�� ������ֱ� ���� ShowPartyList �������Ϸ� �̵� 				
				
			}
			response.sendRedirect("index");

	}
}
