
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
import dto.PartyDTO;
import dto.PersonDTO;


@WebServlet("/ShowPartyList")
public class ShowPartyList extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session = request.getSession();		
		LogInDAO dao = new LogInDAO();				
		PersonDTO person = (PersonDTO)session.getAttribute("Person"); 
		
		String person_id = person.getId();                                 // ���ǿ��� ���� ���̵� ��������		
		
		ArrayList<PartyDTO> myList = dao.getMyParty(person_id);           //�ش� ������ ������ ��Ƽ�� �ִ��� Ȯ�� 		
		ArrayList<PartyDTO> otherList= dao.getOtherParty(person_id);    //���� �ҼӵǾ��ִ� �ٸ� ����� ��Ƽ����Ʈ Ȯ��
		
	 
		request.setAttribute("myPartyList", myList);                          // ������Ʈ ������ �� ��Ƽ����Ʈ�� ����
		request.setAttribute("otherPartyList", otherList);
		
		
	    request.getRequestDispatcher("IndexPage.jsp").forward(request, response); 
	
	}

}
