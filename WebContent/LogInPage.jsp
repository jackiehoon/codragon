<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="lib/semantic.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
   integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
   crossorigin="anonymous"></script>


<script src="lib/semantic.js"></script>
<style type="text/css">
@import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);
body{
font-family:
}
#header {
   height: 85px;
   width: 100%;

}

#login {
   height: 632px;
   width: 100%;
}

#main_1 {
   height: 400px;
}

#main_2 {
   height: 400px;
   
}

#main_3 {
   height: 400px;
}

#footer {
   height: 85px;
     width: 100%;
     text-align:Center;
}

.section1 {
   background-image: url('img/LoginBackground.png');
   width: 100vw;
   height: 500px;
}

.sub-section {
   display: inline-block;
   height: 100%;
   width: 50%;
   height: 100%;
}

.section2 {
   
   width: 100vw;
   height: 500px;
}
.wrapper{
   width:900px;
   height:100%;
   margin:0 auto;
}
#section2_1{
background-color: gray;
}
.main_text{
float: left;
padding-top: 160px;
text-align: center;
font-size: 20px;
width:380px;
}
.input{
  background-color: silver;
  width: 400px;
  height: 50px;
  border-radius: 12px;
  }
   #join{
 background-color: #55acee;
 border-radius: 12px;
 width: 400px;
 height: 50px;
  }

  #logo{
  height: 85px;
   width: 160px;
  }

 #sign_1{
 padding-left: 100px;
 padding-top: 0px;
 } 
 #signup{
 width: 250px;
 height: 40px;
 
 }



</style>
</head>
<body>

<jsp:include page="header.jsp" flush="false"></jsp:include>


<div class="section1">
   <div class="wrapper">
      <div class="sub-section">
<div class="main_text">
         <h1>프로그래밍 수업?</h1>
         <h1>보다 스마트하게!</h1>
         <p>클래스를 개설하여</p>
         <p>보다 원활하게 IT수업을진행하세요.</p>

      </div></div><div class="sub-section">
      <div class="main_text">
         <h1>LOG IN</h1>
         <form action="LogInCheck">
         <div class="ui form">
           <div class="field">
             <input type="email" placeholder="이메일"  name = "input_email">
           </div>
           <div class="field">
             <input type="password" placeholder="비밀번호"  name = "input_password">
           </div>
           <div class="field">
             <input type="submit" value="LOG IN" class="fluid ui large blue button " >
           </div>
         </div>
		</form>
         
</div>
      </div>
      </div>
</div>



<div class="section2">
   <div class="wrapper">
      <div class="sub-section">
         <img alt="" src="img/Loginimg1.png" style="margin:67px auto">
      </div><div class="sub-section">
      <div class="main_text">
      <h1>여러가지 수업을</h1>
         <h1>진행해보세요</h1>
         <p>자신만의 수업을 진행하여 실력을 키워보세요.</p>
      </div>
         
               

      </div>
      </div>
</div>




<div class="section2" id="section2_1">
   <div class="wrapper">
      <div class="sub-section">
      <div class="main_text">
         <h1>누구나 자유롭게</h1>
         
         <p>어느 누구나 자유롭게 사용해 보세요.</p>
      </div>
         

      </div><div class="sub-section">
      <img alt="" src= "img/Loginimg2.png"style="margin:67px auto">

      </div>
      </div>
</div>




<div class="section2">
   <div class="wrapper">
      <div class="sub-section">
      <img alt="" src="img/Loginimg3.png" style="margin:67px auto">
         
      </div><div class="sub-section">
      <div class="main_text">
         <h1>보다 쉽게 예제를</h1>
         <h1>사용해보세요</h1>
         <p>예제 이미지 및 코드를 사용하세요.</p>
   </div>
      </div>
      </div>
</div>









   <div id="footer"><div><br><span>CoDragon&emsp;|&emsp;(주)코드래곤&emsp;&emsp;|&emsp;&emsp;TEL:123-1234-1234&emsp;&emsp;|&emsp;&emsp;TEST:123-45-789</span></div></div>

<div class="ui modal">
  <i class="close icon"></i>
  
  <div class="image content">
    <div class="ui medium image">
      <img src="http://www.crystalfallswater.com/wp-content/uploads/2016/05/signup.png">
    </div>
    <div class="description">
      <div class="ui header" align="center">회원가입</div>
      <form action = "Join"  id="form-join" method = "post">
      <div class="ui form">
          <div class="inline field">
            <label><h4>이름&emsp;&emsp;</h4></label>
            <input type="text" placeholder="Name" id="signup" name = "name">
          </div>
          <div class="inline field">
            <label><h4>이메일&emsp;</h4></label>
            <input type="email" placeholder="Email" id="signup" name = "email">
          </div>
          <div class="inline field">
            <label><h4>비밀번호&nbsp;</h4></label>
            <input type="password" placeholder="Password" id="signup" name ="password">
          </div>
       
      </div>
      </form>
    
      

    </div>
  </div>
  <div class="actions">
    <div class="ui black deny button">
      No
    </div>
    <div class="ui positive right labeled icon button btn-signup">
     SIGN UP
      <i class="checkmark icon"></i>
    </div>
  </div>
</div>

<script>

$("#sign").click(function(){
   $('.ui.modal')
   .modal('show')
   ;

})

$(".btn-signup").click(function(){
	
	$("#form-join").submit();
})


</script>


</body>
</html>