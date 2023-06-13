<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.3.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
h1{
text-align : center; 

}
	#pwdForm{
		width: 250px;
		height: 200px;
		
		margin: 50px auto;
	}
	.b-block w-100{
		text-align : center;
		width : 200px;
	}
	
	
</style>

<script type="text/javascript">
$(function(){


   
   
   $('#changeBtn').on("click", function(){
      vform = $('#form').serialize();

      console.log(vform);
      vpwd = $('#new_pwd').val().trim();
      vpwd_re = $('#new_pwd_re').val().trim();
      
      if(vpwd == vpwd_re){
      	$.ajax({
    	  
        	 url : "<%=request.getContextPath()%>/Info/ProPwChange.do" ,
        	 type : "post",
         	data : vform,
         	success : function(data){
        		 if(data.flag == "성공"){
            		 alert('비밀번호 변경에 성공했습니다.');
            		 window.opener.document.getElementById("pro_pw").value = vpwd;
            		 window.opener.location.reload();
            		 window.close();
            		 } 
        	 },
        	 error: function(xhr){
           	 alert('에러정보 :' + xhr.status )
           	 window.close();
        	 },
         
        	 dataType: 'json'
     	})
      }else if(vpwd != vpwd_re){
    	  alert('비밀번호가 일치하지 않습니다.')
      }
     
   })

})    


</script>
</head>

<body>
<h1>비밀번호 변경</h1>
<form class="form-horizontal" id="form" >
	<div id="pwdForm">
			
			<label>새 비밀번호</label>
			<input type="password" name="new_pwd" id="new_pwd" class="form-control"  
			style="width: 200px; height: 30px;" placeholder="변경할 비밀번호를 입력하세요"><br><br>
			
			<label>새 비밀번호확인</label>
			<input type="password" name="new_pwd_re" id="new_pwd_re" class=" form-control"  
			style="width: 200px; height: 30px;" placeholder="변경할 비밀번호를 재입력하세요"><br><br>
			<input type="button"  id="changeBtn"  class="btn btn-primary btn-sm " value="변경하기">
	</div>
</form>
</body>
</html>