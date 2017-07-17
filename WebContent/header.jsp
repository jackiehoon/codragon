<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
@import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);
body{
font-family:'Jeju Gothic' !important;
}

</style>
<div style="width:100vw; height:60px; background:white; border-bottom:1px solid #e2e2e2">
   <div style='width:850px; margin: 0 auto; padding:10px'> 
      <a class="item" href="index">
        <img class="ui image" style="display:inline-block; height:35px;" src="https://www.codecan.org/sites/default/files/code-logo-only-styleguide.png">              
      </a>  
       
       
      <c:choose>   
	      <c:when test="${empty Person}">
	      	<button id="sign" class="ui blue button" style="float:right;">SIGN UP</button>
	      </c:when> 
	      <c:otherwise>
		      <div style="float:right; margin-top:10px; font-size:14px">
	          	${Person.name}님 환영합니다. <a href = "SessionInvalidate" >로그아웃</a>
          	</div>
         </c:otherwise>
		</c:choose>
      </div>
   </div>
</div>