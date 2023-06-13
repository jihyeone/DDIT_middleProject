<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="../js/jquery-3.6.3.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
h1{
   margin-top : 3%;
}

h1,h4{

   text-align : center;
}
.form-control{
   width : 30%;
   margin-left: 45%;
}
#btn,#btn2{
   width : 20%;
   margin-left : 30%;
   
}

h5{
	text-aling : center;
	margin-left : 40%
}
a{
	text-aling : center;
	margin-left : 50%
}

</style>

<script type="text/javascript">
$(function(){

	
   $("#btn2").on("click", function(){
	   location.href="<%=request.getContextPath()%>/LoginMain.do";
   })
   
   
   $('#btn').on("click", function(){
      vform = $('#form').serialize();
      
      console.log(vform);
      
      $.ajax({
         url : "<%=request.getContextPath()%>/find/findPw.do" ,
         type : "post",
         data : vform,
         success : function(data){
        	 if(data.flag != "실패"){
        		 alert('비밀번호가 초기화되었습니다.'+
        			   '(초기화된 비밀번호는 주민등록번호 6자리 숫자입니다.)')
        		 
        	 }else{
        		 alert('입력하신 정보를 찾을 수 없습니다.')
        	 }
         },
         error: function(xhr){
            alert('에러정보 :' + xhr.status )
         },
         
         dataType: 'json'
      })
   })
})



</script>


<body>
<h1>비밀번호 찾기</h1>
<h4>비밀번호는 아이디, 이름, 주민등록번호를 통해 찾을 수 있습니다.</h4>
<form class="form-horizontal" id="form">
  <div class="form-group">
    <div class="col-sm-10">
      <input type="text" class="form-control"  name ="id" id="id" placeholder="아이디를 입력하세요">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-10">
      <input type="text" class="form-control" name ="name" id="nm" placeholder="이름을 입력하세요">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-10">
      <input type="text" class="form-control"  name ="reg" id="reg" placeholder="주민등록번호 ( '-'을 포함한 14자리 모두 입력)">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <h5 style="text-align:cencer">아이디가 기억나지 않는다면?</h5>
      <a href="<%=request.getContextPath()%>/Login/findId.jsp">아이디찾기</a>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="button" id="btn" class="btn btn-warning">비밀번호찾기</button>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="button" id="btn2" class="btn btn-warning">로그인화면으로 돌아가기</button>
    </div>
  </div>
</form>


</body>
</html>