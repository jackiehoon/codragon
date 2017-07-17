
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, user-scalable=no">
<meta charset="UTF-8">
<title>${party.title } - Codragon</title>
<link rel="stylesheet" type="text/css" href="lib/semantic.css">
<style type="text/css">

.progress{
	margin-bottom:0
}
body{
	background:#eeeeee
}
</style>

</head>
<body>

	<jsp:include page="header.jsp" flush="false"></jsp:include>
	<div class="ui segment"  style='width:850px; margin: 20px auto;' id="example3"> 
		<div class="ui left rail" style="padding:0; width:initial">		
			<div class="ui vertical menu">
			  <a class="item" href="/socket/main?party_id=${party.id }" style="background: #002b42; color: white;">
			  	<h3 style="margin-top:0;">${party.title}</h3>
			  </a>  			  
			  <c:forEach var= "item" items="${lectureList}">			  
			  	<c:choose>
					<c:when test="${item.id == lecture.id }">
						<a class="item active" href="/socket/main?party_id=${party.id }&lecture_id=${item.id}">
							${item.title}
							<c:if test="${item.lock == 1}">
								<i class="lock icon" style="float:right"></i>
							</c:if>
						</a>
					</c:when>
					<c:otherwise>
						<a class="item" href="/socket/main?party_id=${party.id }&lecture_id=${item.id}">
							${item.title}
							<c:if test="${item.lock eq 1}">
								<i class="lock icon" style="float:right"></i>
							</c:if>
						</a>
					</c:otherwise>					
				</c:choose>
			  </c:forEach>
			  <a class="item btn-new-lecture">새로운 수업</a>
			</div>
		</div>
		
		<div class="ui grid" >
		<c:if test="${!empty param.lecture_id}">
			<div class="sixteen wide stretched column"  style="background: #003d5d; color: white; "> 
				<h3><span class="btn-edit-lecture" style="cursor:pointer">&nbsp;${lecture.title } <i class="edit icon"></i></span></h3>				
			</div>			
			<div class="fourteen wide stretched column" style=" padding-bottom:0;">				
			  <div class="ui form">
				  <div class="field">
			      <div class="ui selection dropdown">
					  <i class="dropdown icon"></i>
					  <div class="text" style="font-size:1.3em"></div>
					  <div class="menu">
					  	<c:if test="${fn:length(exampleList)>0}">
					  		<c:forEach var= "item" items="${exampleList}" varStatus="state">				
									<div class="item btn-example" data-value="${item.id}">${item.title}</div>	
									<c:if test="${state.last }">
										<c:set var="exampleSize" value="${state.count-1}"/>	      
									</c:if>
							  </c:forEach>
							  <div class="item btn-new-example" data-value="${exampleSize}">새로운 예제 만들기</div>
							</c:if>
						<c:if test="${fn:length(exampleList)==0}">
					  		<div class="item btn-new-example" data-value="default">새로운 예제 만들기</div>				
				      	</c:if>
					  </div>
					</div>
				  </div>
				</div>
	  	</div>
	  	<div class="two wide stretched column" style="padding-bottom:0;">
	  		<button class="ui button btn-edit-example" >수정</button>
	  	</div>	  	
	  	<div class="twelve wide stretched column">
			  <div class="ui segment">
			  	<h5>이미지 <a href="#" class="btn-edit-image" style="float:right">이미지 수정</a></h5>
			  	<c:choose>
				  	<c:when test="${empty imageList}">
				  		<img class="ui centered big image main-image" src="https://vignette3.wikia.nocookie.net/lego/images/a/ac/No-Image-Basic.png/revision/latest?cb=20130819001030">
			  		</c:when>
			  		<c:otherwise>
				  		<img class="ui centered big image main-image" src="upload/${image.image}">
				  	</c:otherwise>
			  	</c:choose>			
			  </div>			
			  <div class="ui segment" >
			  	<h5>설명</h5>
			  	<span style="white-space:pre-wrap">${image.description }</span>						  
		    </div>
		  </div>			  
		  <div class="four wide stretched column">
		    <div class="ui segment small images ex-images">
		    	<h5>&nbsp;<a href="# "class="btn-add-image" style="float:right; cursor:pointer">업로드</a></h5>
		      
		      
		      <c:forEach var= "item" items="${imageList}" varStatus="state">
		      	<c:choose>
		      		<c:when test="${item.id eq image.id}">
		      			<i class="remove icon btn-remove-icon"style="position:absolute; cursor:pointer; right:0" id="img-${image.id}"></i>
		      			<img src="upload/${image.image}" style="border:3px solid #a22b2b">
			      	</c:when>
			      	<c:otherwise>
			     		 	<i class="remove icon btn-remove-icon"style="position:absolute; cursor:pointer; right:0" id="img-${item.id}"></i>
			      		<a href="/socket/main?party_id=${party.id }&lecture_id=${lecture.id}&example_id=${example.id}&image_id=${item.id}">
			      			<img src="upload/${item.image}">
			      		</a>
			      		
			      	</c:otherwise>
		      	</c:choose>
		      </c:forEach>
		    </div>
		  </div>
		  
		</c:if>
		<c:if test="${empty param.lecture_id}">      
         <div class="sixteen wide stretched column"  style="background:#002b42; color: white; text-align:center"> 
            <h3 >&nbsp;${party.title } 
               <i class="setting icon btn-party" style="float:right; cursor:pointer"></i>&nbsp;
               <i class="add user icon btn-invite" style="float:right;cursor:pointer;"></i>&nbsp;
            </h3>   
         </div>   
         
         <div class="sixteen wide stretched column">   
            
            
            
            <div class="ui segment" >
               <h5>선생님 말씀</h5>
               <span style="white-space:pre-wrap">${party.description }</span>
            </div>
            
            <div class="ui segment" >
               <h5>커리큘럼</h5>
               <ol class="ui list">
                  <c:forEach var= "lecture" items="${lectureList}">
                    <li><a href="/socket/main?party_id=${party.id }&lecture_id=${lecture.id}">${lecture.title}</a>                 
                      <ol>
                         <c:forEach var= "example" items="${lecture.exampleList}">
                           <li><a href="/socket/main?party_id=${party.id }&lecture_id=${lecture.id}&example_id=${example.id}">${example.title }</a></li>
                        </c:forEach>
                      </ol>
                    </li>
                  </c:forEach>
               </ol>
            </div>
            
            
            
            
            
         </div>
      </c:if>

		  
		  			  
	</div>	
		
		
		<div class="ui right rail" style="padding:0; width:initial">
         <div class="ui vertical menu "  >
            <div class="item"  style="background: #002b42; color: white;"><h3>Class Info</h3> </div>
            <div class="item">
              <h4>출석률 (<span id="online"></span>/${studentNum})</h4>
              <div class="ui progress teal" id="attend" style="margin-bottom:0;">
                  <div class="bar">
                     <div class="progress"></div>
               </div>
              </div>           
            </div>
            <div class="item" >  
              <h4>예제 진행도 (<span id="progress-num">${doneStudentNum}</span>/${studentNum}) 
              	<i class="undo icon" style="cursor:pointer; float:right;" id ="btn-reset"></i>
              </h4>
              <div class="ui progress orange" id="progress"  style="margin-bottom:0;">
                  <div class="bar">
                    <div class="progress"></div>
                 </div>
               </div>
            </div>         
           <div class="item" >
              <div class="twelve wide stretched column">
                <h4>선생님 호출</h4>                
                <div class="ui relaxed divided list" id="call-list">                    
                    
              	</div>            
              </div>           
           </div>
         </div>           
      </div>  
	</div>

<div id="modals">
	<!-- 클래스 설정 -->
	
	<div class="ui modal" id="setting-party">
		<div class="header">
	   	클래스 설정
	  </div>
	  <div class="content">  
		<div class="ui pointing secondary menu">
		  <a class="item active" data-tab="first">설정</a>
		  <a class="item" data-tab="second">학생 관리</a>
		</div>	  
		<div class="ui tab segment active" data-tab="first">
		  <div class="description" style="width:100%;">
		    <form action="UpdateParty" id="form-edit-party" method="post">  	  
				<div class="ui form">
				  <div class="field">
				    <label>타이틀</label>
				    <input type="text" value="${party.title }" name="title">
				    <label>설명</label>
				    <textarea rows="10" name="description">${party.description }</textarea>	
				    <input type="hidden" value="${party.id }" name="party_id">			    
				  </div>
				</div>
			</form>
	    	</div>	
		</div>
		<div class="ui tab segment" data-tab="second">
		  <div class="description" style="width:100%;">
			 <c:forEach var= "student" items="${studentList}">
			 	<div><span>${student.name}</span> <a href = "#" class= "btn-ban-student" id = "person-${student.id}">강퇴</a></div>
			 </c:forEach>
	    	</div>	
		</div>
	  

	  </div>
	  <div class="actions">
	  	<div class="ui red button btn-delete-party" style="float:left">
	      	삭제
	    </div>
	    <div class="ui black deny button">
	      	취소
	    </div>
	    <button class="ui positive right labeled icon button btn-submit-edit-party">   
	    	수정 <i class="checkmark icon"></i>
	    </button>   
	  </div>
	</div>
	
	<!-- 초대하기 -->
	<div class="ui modal" id="modal-invite">
		<div class="header">
	   초대하기
	  </div>
	  <div class="content">  
	    <div class="description" style="width:100%;">
		    <form action="InviteParty" id="form-invite" method="post">  	  
					<div class="ui form">
					  <div class="field">
					    <label>이메일로 초대</label>
					    <textarea rows="2" name="emails" placeholder="여러명 초대시 ';'로 구분해 주세요."></textarea>
					    <input type="hidden" name="party_id" value="${party.id }">
					    <input type="hidden" name="person_id" value="${person.id }">					    
					  </div>
					</div>
				</form>
	    </div>	    
	  </div>
	  <div class="actions">
	    <div class="ui black deny button">
	      	취소
	    </div>
	    <button class="ui positive right labeled icon button btn-submit-invite">   
	    	초대 <i class="checkmark icon"></i>
	    </button>   
	  </div>
	</div>

	
	<!-- 수업만들기 -->
	
	<div class="ui modal" id="new-lecture">	 		
		  <div class="header">
		   	수업 만들기
		  </div>
		  <div class="content">    
		    <div class="description">
		    	<form action="CreateLecture" method="post" id="form-new-lecture"> 
					<div class="ui form">
					  <div class="field">
					    <label>타이틀</label>
					    
					    	<input type="text" name="title">
					    	<input type="hidden" name="party_id" value="${party.id}">
					    	
					    	
					    	
					   
					  </div>				  
					
							    <div class="field">
							    	<label>잠금</label>
							      <div class="ui toggle checkbox">
							        <input type="checkbox" name="lock" tabindex="0" class="hidden">
							        <label>(학생들에게 수업이 보이지 않습니다)</label>
							      </div>
							    </div>
							  </div>
				  </form>
		    </div>
		  </div>
		  <div class="actions">
		    <div class="ui black deny button">
		      	취소
		    </div>
		    <button class="ui positive right labeled icon button btn-submit-new-lecture">   
		    만들기 <i class="checkmark icon"></i>
		    </button>   
		  </div>
	  
	</div>
		
	
	<!-- 수업 설정 -->
	
		<div class="ui modal" id="modal-edit-lecture">	 
		  <div class="header">
		   	수업 설정
		  </div>
		  <div class="content">    
		    <div class="description">
		    <form action="UpdateLecture" id="form-edit-lecture" method="post"> 
					<div class="ui form">
					  <div class="field">
					    <label>타이틀</label>
					    <input type="text" name="title" value="${lecture.title }">	
					    <input type="hidden" name="lecture_id" value="${lecture.id}">						    
					  </div>			
				    <div class="field">
				    	<label>잠금</label>
				      <div class="ui toggle checkbox">
				      	
				        <input type="checkbox" name="lock" tabindex="0" class="hidden" 
				        <c:if test="${lecture.lock eq 1}">
				        	 checked="checked"
				        </c:if>
				        	>
				        <label>(학생들에게 수업이 보이지 않습니다)</label>
				      </div>
				    </div>
				  </div>
				</form>
	    </div>
	  </div>
	  <div class="actions">
	  	<div class="ui red button btn-delete-lecture" style="float:left">
	      	삭제
	    </div>
	    <div class="ui black deny button">
	      	취소
	    </div>
	    <button class="ui positive right labeled icon button btn-submit-edit-lecture">   
	   		확인<i class="checkmark icon"></i>
	    </button>   
	  </div>
	</div>
	
	<!-- 예제 만들기 -->

	<div class="ui modal" id="modal-new-example">	 
	  <div class="header">
	   	예제 만들기
	  </div>
	  <div class="content">    
	    <div class="description">
				<div class="ui form">
				  <div class="field">
				    <label>타이틀</label>
				    <form action="CreateExample" method="post" id="form-new-example"> 
				    	<input type="text" name="title">
				    	<input type="hidden" name="party_id" value="${party.id}">
				    	<input type="hidden" name="lecture_id" value="${lecture.id}">
				    </form>
				  </div>				  
				</div>
	    </div>
	  </div>
	  <div class="actions">
	    <div class="ui black deny button">
	      	취소
	    </div>
	    <button class="ui positive right labeled icon button btn-submit-new-example">   
	    만들기 <i class="checkmark icon"></i>
	    </button>   
	  </div>
	</div>
	<!-- 예제 수정 -->
	
		<div class="ui modal" id="modal-edit-example">	 
		  <div class="header">
		   	예제 수정
		  </div>
		  <div class="content">    
		    <div class="description">
			    <form action="UpdateExample" id="form-edit-example">
						<div class="ui form">
						  <div class="field">
						    <label>타이틀</label>
						    <input type="text" name="title" value="${example.title }">
						  </div>				  
						</div>
						<input type="hidden" name="example_id" value="${example.id }">
		   	  </form>
				</div>
			    
		  </div>
		  <div class="actions">
		  	<div class="ui red button btn-delete-example" style="float:left">
		      	삭제
		    </div>
		    <div class="ui black deny button">
		      	취소
		    </div>
		    <button class="ui positive right labeled icon button btn-submit-edit-example">   
		    만들기 <i class="checkmark icon"></i>
		    </button>   
		  </div>
		</div>
		
		
	<div class="ui modal" id="modal-new-image">	 
	  <div class="header">
	   	이미지 업로드
	  </div>
	  <div class="content">    
	    <div class="description">
			<form id="form-image" action="UploadImage" method="post" enctype="multipart/form-data">
			    <div class="ui form">
			  		<div class="field">
			    		<label>사진</label>
						<input type="file" id="input-file" name="image" accept="image/*">
					</div>				  
				</div>
				<div class="ui form">
				  <div class="field">
				    <label>설명</label>
				    <textarea rows="10" name="description"></textarea>		
				  </div>				  
				</div>
				<c:choose>	
					<c:when test="${empty example}">
						<input type="hidden" value="${exampleList[0].id }" name="example_id">
					</c:when>
					<c:otherwise>
						<input type="hidden" value="${example.id }" name="example_id">		
					</c:otherwise>	
				</c:choose>
				<input type="hidden" name="url" value='main?party_id=${party.id }&lecture_id=${lecture.id}&example_id=${example.id}'>
			</form>
	    </div>
	  </div>
	  <div class="actions">
	    <div class="ui black deny button">
	      	취소
	    </div>
	    <button class="ui positive right labeled icon button btn-submit-new-image">   
	   		 만들기 <i class="checkmark icon"></i>
	    </button>   
	  </div>
	</div>
	
	
	<div class="ui modal" id="modal-edit-image">	 
	  <div class="header">
	   	이미지 수정
	  </div>
	  <div class="content">    
	    <div class="description">
			<form id="form-edit-image" action="UpdateImage" method="post" enctype="multipart/form-data">
			    <div class="ui form">
			  		<div class="field">
			    		<label>사진</label>
						<input type="file" id="input-file" name="image" accept="image/*">
					</div>				  
				</div>
				<div class="ui form">
				  <div class="field">
				    <label>설명</label>
				    <textarea rows="10" name="description">${image.description}</textarea>		
				  </div>				  
				</div>
				<input type="hidden" name="image_id" value="${image.id}">			
			</form>
	    </div>
	  </div>
	  <div class="actions">
	    <div class="ui black deny button">
	      	취소
	    </div>
	    <button class="ui positive right labeled icon button btn-submit-edit-image">   
	   		 수정 <i class="checkmark icon"></i>
	    </button>   
	  </div>
	</div>
	
	

</div>


<form id="form-delete-example" action="DeleteExample" style="display:none;" method="post">
	<input type="hidden" value="${person.id }" name="person_id">
	<input type="hidden" value="${party.id }" name="party_id">
	<input type="hidden" value="${lecture.id }" name="lecture_id">
	<c:choose>	
		<c:when test="${empty example}">
			<input type="hidden" value="${exampleList[0].id }" name="example_id">
		</c:when>
		<c:otherwise>
			<input type="hidden" value="${example.id }" name="example_id">		
		</c:otherwise>	
	</c:choose>
</form>

<form id="form-delete-lecture" action="DeleteLecture" style="display:none;" method="post">
	<input type="hidden" value="${person.id }" name="person_id">
	<input type="hidden" value="${party.id }" name="party_id">
	<input type="hidden" value="${lecture.id }" name="lecture_id">	
</form>

<form id="form-delete-party" action="DeleteParty" style="display:none;" method="post">
	<input type="hidden" value="${person.id }" name="person_id">
	<input type="hidden" value="${party.id }" name="party_id">
</form>

<form id="form-delete-image" action="DeleteImage" style="display:none;" method="post">
	<input type="hidden" value="${person.id }" name="person_id">
	<input type="hidden" id="value-image" name="image_id">
	<input type="hidden" value="main?party_id=${party.id }&lecture_id=${lecture.id}&example_id=${example.id}" name="url">
</form>

<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous"></script>
<script src="lib/semantic.js"></script>
<script src="lib/time_ago.js"></script>
<script>
$('.ui.checkbox')
.checkbox()
;

$('.menu .item')
.tab()
;

	$('.selection.dropdown')
  .dropdown('set selected','${example.title}')
;
	
	$('.ui.modal')
	  .modal()
	;
	
	$(".btn-party").click(function(){
		$('#setting-party')
		  .modal('show')
		;		
	})
	$(".btn-invite").click(function(){
		$('#modal-invite')
		  .modal('show')
		;		
	})
	$(".btn-new-lecture").click(function(){
		$('#new-lecture')
		  .modal('show')
		;		
	})
	
	$(".btn-edit-lecture").click(function(){
		$('#modal-edit-lecture')
		  .modal('show')
		;		
	})
	$(".btn-new-example").click(function(){
		$('#modal-new-example')
		  .modal('show')
		;		
	})
	$(".btn-edit-example").click(function(){
		$('#modal-edit-example')
		  .modal('show')
		;		
	})
	
	$(".ex-images > img").click(function(){
		$(".main-image").attr("src",$(this).attr("src"));
		$(".ex-images > img").css("border","")
		$(this).css("border","3px solid #828282")
	})
	
	
	$(".btn-add-image").click(function(){
		$('#modal-new-image')
		  .modal('show')
		;		
		
	})
	
	$(".btn-edit-image").click(function(){
		$('#modal-edit-image')
		  .modal('show')
		;		
		
	})
	

	$('.btn-submit-new-lecture').click(function(){
  	$('#form-new-lecture').submit();
  })
  
  $('.btn-submit-new-example').click(function(){
  	$('#form-new-example').submit();
  })
  
  $('.btn-submit-edit-example').click(function(){
  	$('#form-edit-example').submit();
  })
	$('.btn-submit-edit-party').click(function(){
  	$('#form-edit-party').submit();
  })
  $('.btn-submit-edit-lecture').click(function(){
  	$('#form-edit-lecture').submit();
  })
  $('.btn-submit-invite').click(function(){
  	$('#form-invite').submit();
  })
  $('.btn-submit-new-image').click(function(){
  	$('#form-image').submit();
  })
   $('.btn-submit-edit-image').click(function(){
  	$('#form-edit-image').submit();
  })
  
  $('.btn-delete-example').click(function(e){
	  if(confirm("${example.title} 예제를 삭제하시겠습니까?")){
  		$('#form-delete-example').submit();
  	}
  })
  
  $('.btn-delete-lecture').click(function(e){
	  if(confirm("${lecture.title} 수업을 삭제하시겠습니까?")){
  		$('#form-delete-lecture').submit();
  	}
  })
  $('.btn-delete-party').click(function(e){
	  if(confirm("${party.title} 클래스를 삭제하시겠습니까?")){
  		$('#form-delete-party').submit();
  	}
  })
  
  $('.btn-remove-icon').click(function(e){
	if(confirm("이미지를 삭제하시겠습니까?")){
		$('#value-image').val(this.id.substring(4))
  		$('#form-delete-image').submit();
  	}
  })
  
 
  
  	$('.btn-example').click(function(){
  		document.location.href='/socket/main?party_id=${party.id }&lecture_id=${lecture.id}&example_id='+$(this).data('value')	
		
	})
	
	
      if(${fn:length(exampleList)==0}){
   	   $(".btn-new-example").click()
      }


		var personId = ${Person.id}
		var partyId = ${party.id}
	    var studentNum=${studentNum}
	    var doneStudentNum =${doneStudentNum}
		
		$("#progress").progress({
			percent: doneStudentNum*100 / studentNum
		})
		
	    var printCallStudent = function(list){			
			call_list =""
           	var array = JSON.parse(list);     			
           	for(var i=0; i<array.length;i++){
           		call_list +='<div class="item"><i class="large github middle aligned icon"></i><div class="content"><a class="header">'
                   call_list+=array[i][1]
               	call_list+='</a><div class="description">'                	
            		time =array[i][0].toString()               		   	
                	
                	call_list+= jQuery.timeago(new Date("20"+time.substr(0,17)));
               	call_list+='</div></div></div>'     
           	}   
            $("#call-list").html(call_list)			
		}
		
		printCallStudent('${callStudentList}')
		
        //웹소켓 초기화
        var webSocket = new WebSocket("ws://192.168.0.5:8081/socket/broadsocket");
        var messageTextArea = document.getElementById("messageTextArea");
        //메시지가 오면 messageTextArea요소에 메시지를 추가한다.
        webSocket.onmessage = function processMessge(message){
            //Json 풀기
            var jsonData = JSON.parse(message.data);
            if(jsonData.type =="online") {
            	$("#online").html(jsonData.num);
            	$("#attend").progress('update progress', jsonData.num*100 / studentNum )
                
            }else if(jsonData.type =="done") {
            	$("#progress-num").html(jsonData.num);
                $("#progress").progress('update progress', jsonData.num*100 / studentNum ) 
            	
            }else if(jsonData.type =="call") {            	
            	printCallStudent(jsonData.list)
            }
            
        }
        webSocket.onopen = function(message){        	
        	webSocket.send('{"type":"enter","person_id":"0","party_id":"${party.id}"}');
        };
        
   
       
        
        $("#btn-reset").click(function(){
        	
	 		   $.ajax({
	 	           url : "ClearDone",
	 	           data: "party_id=${party.id}",
	 	           success : function(result) {
	 	        	  webSocket.send('{"type":"done","party_id":"${party.id}"}');
	 	           }
	 	        });
        
 	   });
        
        $(".btn-ban-student").click(function(){
        	if(confirm($(this).parent().find("span").first().html()+"님을 강퇴하시겠습니까?")){
        		var this_a = $(this)
        		
	        	var id = this.id.substring(7);
	  		   $.ajax({
	  	           url : "BanStudent",
	  	           data: "party_id=${party.id}&person_id="+id,
	  	           success : function(result) {
	  	        	 this_a.parent().remove();
	  	           }
	  	        });
        	}
  	   });
        
        
        
        
    </script>

</body>
</html>