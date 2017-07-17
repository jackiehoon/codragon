
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<meta charset="EUC-KR">
<title>Codragon</title>
<link rel="stylesheet" type="text/css" href="lib/semantic.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
   <script src="lib/semantic.js"></script>

</head>
<body style="background-color: #eeeeee;">

<jsp:include page="header.jsp" flush="false"></jsp:include>
<style type="text/css">

#header {
	height: 85px;
	width: 100%;
	background-color: #378ab5;
}

#login {
	height: 632px;
	width: 100%;
}

#footer {
	height: 85px;
	background-color: #ffffff;
	z-index: 99;
	position: absolute;

	
}

.section1 {
	width: 100vw;
	/* height: 100vh;
 */
}

.sub-section {
	display: inline-block;
	height: 100%;
	width: 50%;
	height: 100%;
}

.wrapper {
	width: 870px;
	height: 100%;
	margin: 0 auto;
}

#section2_1 {
	background-color: gray;
}

.main_text {
	float: left;
	padding-top: 160px;
	text-align: center;
	font-size: 20px;
	width: 380px;
}

.input {
	background-color: silver;
	width: 400px;
	height: 50px;
	border-radius: 12px;
}

#join {
	background-color: #55acee;
	border-radius: 12px;
	width: 400px;
	height: 50px;
}

#footer {
	width: 100%;
	padding-left: 300px;
}

#logo {
	height: 85px;
	width: 160px;
}

#sign {
	margin-right: 20px;
	margin-top: 11px;
	float: right;
}

#sign_1 {
	padding-left: 100px;
	padding-top: 0px;
}

#signup {
	width: 250px;
	height: 40px;
}
.party-card{
	width:200px !important;
	display: inline-block;
}
</style>

	<div class="section1"
		style=" padding-top: 30px; margin-bottom: 50px;">
		<div class="wrapper" style="display:none">
			<div class="ui form">
			  <div class="field">
			    <input type="text" name="first-name" placeholder="오픈 강좌 검색" id="search-open">
			  </div>
		  	</div>
		
		</div>
		
		<div class="wrapper" id="open-class">
		</div>
		<div class="wrapper" id="my-class">
			<div class="ui grid" >
				<div class="sixteen wide stretched column"  style="background: #003d5d; color: white; "> 
					<h3>내가 만든 클래스</h3>				
				</div>
				<div class="box_01"	style="border: 10px solid #dddddd; border-radius:10px; width: 900px; background-color: #dddddd; ">
					
					<div class="ui link cards">					
						<c:forEach var = "showMyParty" items = "${myPartyList}">
							<div class="card party-card" style="border-radius:10px;" id="party-${showMyParty.id}">
								<div class="image" >
									<img src="img/test.jpg">
								</div>
								<div class="content">
									<div class="header_">${showMyParty.title}</div>
									<!-- <div class="description">내용</div> -->
								</div>
								<div class="extra content">
									<span class="right floated"> ${showMyParty.person_num}명 공부중 </span> <span>
										<i class="user icon"></i>${showMyParty.person_name}
									</span>
								</div>
							</div>
						</c:forEach>
						<div class="card" id="create"style=" border-radius:10px; width:200px; display: inline-block; height :250px; text-align: center;">						
							<div class="content" style="display:inline-block; padding-top: 70px;" >							
								<i class="huge add circle icon" ></i>
								<h1>CREATE</h1>							
							</div>						
						</div>
					</div>
				</div>
			</div>
			<div class="ui grid" style="margin-top:50px">
			<div class="sixteen wide stretched column"  style="background: #003d5d; color: white; "> 
				<h3>내가 속한 클래스</h3>				
			</div>
			<div class="box_01"
				style="border: 10px solid #dddddd; border-radius:10px; width: 900px; background-color: #dddddd">
				
				<!-- <div class="ui link cards"> -->
				
				
				<div class="ui link cards" style="width: 870px; height: 250px; display: inline-block;  " >
					 <c:if test="${fn:length(otherPartyList) eq 0}">	
						 <div style="width:100%; height:100%; text-align:center;padding-top:75px;">					
					      <i class="huge warning sign icon"></i>
						    <h1>클래스 목록이 없습니다.</h1>	
					   </div>
				   </c:if>
						<c:forEach var = "showOtherParty" items = "${otherPartyList}">
							<div class="card party-card" id="party-${showOtherParty.id}">
								<div class="image">
									<img src="img/test.jpg">
								</div>
								<div class="content">
									<div class="header_">${showOtherParty.title}</div>
									<!-- <div class="description">내용</div> -->
								</div>
								<div class="extra content">
									<span class="right floated"> ${showOtherParty.person_num}명 공부중 </span> 
									<span>
										<i class="user icon"></i>${showOtherParty.person_name}
									</span>
								</div>
							</div>						
						</c:forEach>
				</div>
			</div>
			
		</div>
		
		
	</div>
	
	<!-- 
	<div id="footer" >
		<div class="wrapper">
			<br>
			<span>CoDragon&emsp;|&emsp;(주)코드래곤&emsp;&emsp;|&emsp;&emsp;TEL:123-1234-1234&emsp;&emsp;|&emsp;&emsp;TEST:123-45-789</span>
		</div>
	</div>
	 -->
	

	<div class="ui modal">
		<i class="close icon"></i>

		<div class="image content">
			<div class="ui medium image">
				<img
					src="http://www.crystalfallswater.com/wp-content/uploads/2016/05/signup.png">
			</div>
			<div class="description">
				<div class="ui header" align="center">클래스 만들기</div>

				<div class="ui form">
					<div class="inline field">
					
						<label><h4>클래스 이름</h4></label> 
						
						<form action="CreateParty" id = "createParty" >
						<input type="text" placeholder="Name" id="signup" name ="title">
						</form>
					</div>

				</div>
			</div>
		</div>
		<div class="actions">
			<div class="ui black deny button">No</div>
			<div class="ui positive right labeled icon button btn-createParty">
				CREATE <i class="checkmark icon"></i>
			</div>
		</div>
	</div>


	<script>
		$("#create").click(function() {
			$('.ui.modal').modal('show');

		})
		
		
$(".btn-createParty").click(function(){
	$("#createParty").submit();
})

$(".party-card").click(function(){
	document.location.href='/socket/main?party_id='+this.id.substring(6)
	
	
})



$("#search-open").on('input', function() {
	if($(this).val().length>0){
		$("#my-class").css("display","none")
		$("#open-class").css("display","block")
		$.ajax({
		  url: "SearchOpenParty",
		  data:"title="+$(this).val(),

	        dataType: "html",
		  success: function( result ) {
			  $("#open-class").html(result)
		  }
		});
		
	}else{
		$("#my-class").css("display","block")
		$("#open-class").css("display","none")
		
	}
	
	
})

	</script>
</body>
</html>