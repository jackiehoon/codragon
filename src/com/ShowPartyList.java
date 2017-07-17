
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
		
		String person_id = person.getId();                                 // 세션에서 유저 아이디 가져오기		
		
		ArrayList<PartyDTO> myList = dao.getMyParty(person_id);           //해당 유저가 생성한 파티가 있는지 확인 		
		ArrayList<PartyDTO> otherList= dao.getOtherParty(person_id);    //내가 소속되어있는 다른 사람의 파티리스트 확인
		
	 
		request.setAttribute("myPartyList", myList);                          // 리퀘스트 영역에 두 파티리스트를 저장
		request.setAttribute("otherPartyList", otherList);
		
		
	    request.getRequestDispatcher("IndexPage.jsp").forward(request, response); 
	
	}

}
