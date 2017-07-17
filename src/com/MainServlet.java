package com;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dto.ExampleDTO;
import dto.ImageDTO;
import dto.LectureDTO;
import dto.MainDAO;
import dto.PartyDTO;
import dto.PersonDTO;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("Person") == null){
			response.sendRedirect("index");			
		}else{
			MainDAO dao = new MainDAO();
        	Gson gson = new Gson();
			
			PartyDTO party = dao.getParty(request.getParameter("party_id"));		
			request.setAttribute("party", party);
			request.setAttribute("studentNum",dao.getStudentNum(party.getId()));
			request.setAttribute("studentList",dao.getStudentList(party.getId()));
			request.setAttribute("doneStudentNum",dao.getCompleteNum(party.getId()));
			request.setAttribute("doneStudentList",dao.getDoneList(party.getId()));
			request.setAttribute("callStudentIdList",dao.getCallList(party.getId()));
			
			
			ArrayList<String[]> list = dao.getCallPerson(party.getId());
        	Type listType = new TypeToken<ArrayList<String[]>>(){}.getType();    		
    		String result2 = gson.toJson(list, listType);
			
			request.setAttribute("callStudentList",result2);
			
			ArrayList<LectureDTO> lectureList = dao.getLectureList(party.getId());
			request.setAttribute("lectureList",lectureList);
			
			LectureDTO lecture = new LectureDTO("","","","");
			if(dao.checkPartyLecture(party.getId(), request.getParameter("lecture_id"))){
				lecture = dao.getLecture(request.getParameter("lecture_id"));
			}		
			request.setAttribute("lecture",lecture);
			
			ArrayList<ExampleDTO> exampleList =dao.getExampleList(lecture.getId());
			request.setAttribute("exampleList",exampleList);	
			
			
			ExampleDTO example = new ExampleDTO("","","","");
			if(request.getParameter("example_id")==null){
				if(exampleList.size() >0){
					example = exampleList.get(0);
				}
			}else{
				example = dao.getExample(request.getParameter("example_id"));
			}		
			request.setAttribute("example",example);
			
			ArrayList<ImageDTO> imageList = dao.getImageList(example.getId());
			request.setAttribute("imageList",imageList);
			
			ImageDTO image = new ImageDTO("","","","");
			if(request.getParameter("image_id")==null){
				if(imageList.size() >0){
					image = imageList.get(0);
				}
			}else{
				image = dao.getImage(request.getParameter("image_id"));
			}
			request.setAttribute("image",image);
		
			
			if(party.getPerson_id().equals(((PersonDTO)session.getAttribute("Person")).getId())){
				request.getRequestDispatcher("/main.jsp").forward(request,response);				
			}else{
				request.getRequestDispatcher("/main_student.jsp").forward(request,response);	
			}
			
		}

	}

}
