
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


<div style="margin:20px 0">
   <div class="ui segment"  style='width:850px; margin: 0 auto; ' id="example3"> 
      <div class="ui left rail" style="padding:0; width:initial">      
         <div class="ui vertical menu">
           
           <a class="item" href="/socket/main?party_id=${party.id }" style="background: #1679ad; color: white;">              
              <h3 style="margin-top:0;">${party.title}</h3>
           </a>
           
           <c:forEach var= "item" items="${lectureList}">
              <c:choose>
                  <c:when test="${item.id == lecture.id }">
                     <a class="item active" href="/socket/main?party_id=${party.id }&lecture_id=${item.id}">
                     	${item.title}
                     	<c:if test="${item.lock eq 1}">
							<i class="lock icon" style="float:right"></i>
						</c:if>
                     </a>
                  </c:when>
                  <c:otherwise>
                  	<c:if test="${item.lock eq 1}">
	                     <div class="item"">
	                     	${item.title}<i class="lock icon" style="float:right"></i>							
	                     </div>
                     </c:if>
                     <c:if test="${item.lock eq 0}">
	                     <a class="item" href="/socket/main?party_id=${party.id }&lecture_id=${item.id}">
	                     	${item.title}						
	                     </a>
                     </c:if>
                  </c:otherwise>               
               </c:choose>      
           </c:forEach>
         </div>
      </div>
      
      <div class="ui grid" >
      	<c:if test="${!empty param.lecture_id}">
         <div class="sixteen wide stretched column"  style="background: #003d5d; color: white; "> 
            <h3><span style="cursor:pointer">&nbsp;${lecture.title }</span></h3>            
         </div>         
         <div class="sixteen wide stretched column" style=" padding-bottom:0;">            
           <div class="ui form">
              <div class="field">
               <div class="ui selection dropdown">
                    <i class="dropdown icon"></i>
                    <div class="text" style="font-size:1.3em"></div>
                    <div class="menu">
                       <c:if test="${fn:length(exampleList)>0}">
                          <c:forEach var= "item" items="${exampleList}" varStatus="state">            
                              <div class="item btn-example" data-value="${item.id}">${item.title}</div> 
                          </c:forEach>
                       </c:if>
                    </div>
                  </div>
              </div>
            </div>
        </div>      
        <div class="twelve wide stretched column">
           <div class="ui segment">
              <h5>이미지</h5>
              <c:choose>
                 <c:when test="${empty imageList}">
                    <img class="ui centered big image main-image" src="https://vignette3.wikia.nocookie.net/lego/images/a/ac/No-Image-Basic.png/revision/latest?cb=20130819001030">
                 </c:when>
                 <c:otherwise>
                    <img class="ui centered big image main-image" src="upload/${imageList[0].image}">
                 </c:otherwise>
              </c:choose>         
           </div>         
           <div class="ui segment" >
              <h5>설명</h5>
              <span style="white-space:pre-wrap">${example.content }</span>                    
          </div>
        </div>           
        <div class="four wide stretched column">
          <div class="ui segment small images ex-images">
             <h5>이미지</h5>
            
            
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
         <div class="sixteen wide stretched column"  style="background:#1679ad; color: white; "> 
            <h3 >&nbsp;${party.title }</h3>   
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
      
      
      <c:set var="is_done" value="false"></c:set>
      <c:forEach var="id" items="${doneStudentList}">
      	<c:if test="${id eq Person.id}">
      		<c:set var="is_done" value="true"></c:set>
      	</c:if>
      </c:forEach>
      
      <c:set var="is_call" value="false"></c:set>
      <c:forEach var="id" items="${callStudentIdList}">
      	<c:if test="${id eq Person.id}">
      		<c:set var="is_call" value="true"></c:set>
      	</c:if>
      </c:forEach>
      
               
      <div class="ui right rail" style="padding:0; width:initial">
         <div class="ui vertical menu "  >
            <div class="item"  style="background: #378ab5; color: white;"><h3>Class Info</h3> </div>
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
              	<a href="#" style="position:absolute; right:15px" id="btn-checkdone"
              		><c:if test="${is_done}">취소</c:if><c:if test="${!is_done}">완료</c:if></a>
              </h4>
              <div class="ui progress orange" id="progress"  style="margin-bottom:0;">
                  <div class="bar">
                    <div class="progress"></div>
                 </div>
               </div>
            </div>         
            <div class="item">
              <div class="twelve wide stretched column">
                <h4>선생님 호출 <a href="#" style="text-align: right;margin-left: 35px;" id="btn-call"
                ><c:if test="${is_call}">호출취소</c:if><c:if test="${!is_call}">호출하기</c:if></a></h4>                
                <div class="ui relaxed divided list" id="call-list">  
              	</div>            
              </div>           
           </div>
         </div>           
      </div>   
   </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="lib/semantic.js"></script>
<script src="lib/time_ago.js"></script>

<script>

   $('.selection.dropdown').dropdown('set selected','${example.title}');
   
   $('.ui.modal').modal();
   
  
   $(".ex-images > img").click(function(){
      $(".main-image").attr("src",$(this).attr("src"));
      $(".ex-images > img").css("border","")
      $(this).css("border","3px solid #828282")
   })
   
   
   
   $('.btn-example').click(function(){
        document.location.href='/socket/main?party_id=${party.id }&lecture_id=${lecture.id}&example_id='+$(this).data('value')   
      
   })



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
      
        //메시지가 오면 messageTextArea요소에 메시지를 추가한다.
        webSocket.onmessage = function processMessge(message){
            //Json 풀기
            var jsonData = JSON.parse(message.data);
            
            if(jsonData.type =="online"){
               $("#online").html(jsonData.num);
               $("#attend").progress('update progress', jsonData.num*100 / studentNum )                
            }else if(jsonData.type =="done"){
            	$("#progress-num").html(jsonData.num);
                $("#progress").progress('update progress', jsonData.num*100 / studentNum ) 
            	
            }else if(jsonData.type =="call"){            	
            	printCallStudent(jsonData.list)
            }
            
        }
        webSocket.onopen = function(message){           
            webSocket.send('{"type":"enter","person_id":"${Person.id}","party_id":"${party.id}"}');
        };

        webSocket.onclose = function(message){        	
        	webSocket.send('{"type":"exit","person_id":"${Person.id}","party_id":"${party.id}"}');
        };

        $("#btn-call").click(function(){           
           if($(this).html()=="호출하기"){
              $.ajax({
                 url : "Call",
                 data: "person_id=${Person.id}&party_id=${party.id}",           
                 success : function(result) {
                    $("#btn-call").html("호출취소")                    
                    webSocket.send('{"type":"call","party_id":"${party.id}"}');
                 }
              });
           }else{
              $.ajax({
                 url : "CallCancel",
                 data: "person_id=${Person.id}&party_id=${party.id}",           
                 success : function(result) {
                	 $("#btn-call").html("호출하기")
                	 webSocket.send('{"type":"call","party_id":"${party.id}"}');
                 }
              });
           }
           
        })
        
        
        $("#btn-checkdone").click(function(){           
           if($(this).html()=="완료"){
              $.ajax({
                 url : "CompleteDone",
                 data: "person_id=${Person.id}&party_id=${party.id}",
           
                 success : function(result) {
                    $("#btn-checkdone").html("취소")
                    
                    webSocket.send('{"type":"done","party_id":"${party.id}"}');
                 }
              });
              
           }else{
              $.ajax({
                 url : "CancelDone",
                 data: "person_id=${Person.id}&party_id=${party.id}",
           
                 success : function(result) {
                	 $("#btn-checkdone").html("완료")
                	 webSocket.send('{"type":"done","party_id":"${party.id}"}');
                 }
              });
           }
           
        })
        
        
     
    </script>

</body>
</html>