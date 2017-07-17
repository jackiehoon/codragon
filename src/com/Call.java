package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MainDAO;


@WebServlet("/Call")
public class Call extends HttpServlet {
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String person_id = request.getParameter("person_id");
      String party_id = request.getParameter("party_id");
      System.out.println(person_id);
      MainDAO dao = new MainDAO();
      dao.call(person_id, party_id);
   }
}