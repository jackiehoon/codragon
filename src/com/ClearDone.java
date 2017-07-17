package com;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.LogInDAO;


@WebServlet("/ClearDone")
public class ClearDone extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String party_id = request.getParameter("party_id");
			
			LogInDAO dao = new LogInDAO();
			int cnt = dao.clearDone(party_id);    //party_id �� ���� ������� done�� 0���� ����
			
			if(cnt == -1) System.out.println("����");
			else System.out.println("����");
	}

}
