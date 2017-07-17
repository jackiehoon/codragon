<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1 style="color: #ffffff">오픈 클래스</h1>
	<div class="box_01"	style="border: 10px solid #dddddd; border-radius:10px; width: 870px; background-color: #dddddd; ">
		
		<div class="ui link cards">					
			<c:forEach var = "party" items = "${list}">
				<div class="card party-card" style="width:170px; border-radius:10px; display: inline-block;" id="party-${party.id}">
					<div class="image" style="width: 170px;">
						<img src="img/test.jpg">
					</div>
					<div class="content">
						<div class="header_">${party.title}</div>
						<!-- <div class="description">내용</div> -->
					</div>
					<div class="extra content">
						<span class="right floated"> ${party.person_num}명 공부중 </span> <span>
							<i class="user icon"></i>${party.person_name}
						</span>
					</div>
				</div>
			</c:forEach>
			
		</div>
	</div>