<%@page import="kr.or.ddit.middle.vo.ProfessorVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>Insert title here</title>



<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.3.min.js"></script>

<script type="text/javascript">

			
$(function(){
					
	$('#pwChange').on("click", function(){
		window.open("<%=request.getContextPath()%>/Info/ProPwChange.do", "_blank","width=600px, height=500px");
	});
					
});
</script>
<style type="text/css">
.list{
	 margin-bottom : 100px;
}
form{
	margin: left auto;
}
#profil{
	margin-top: 80px;
}

.form-control:focus {
    box-shadow: none;
    border-color: #BA68C8
}

.profile-button {
    background: rgb(99, 39, 120);
    box-shadow: none;
    border: none
}

.profile-button:hover {
    background: #682773
}

.profile-button:focus {
    background: #682773;
    box-shadow: none
}

.profile-button:active {
    background: #682773;
    box-shadow: none
}

.back:hover {
    color: #682773;
    cursor: pointer
}

.labels {
     font-size: 13px;
}

.add-experience:hover {
    background: #BA68C8;
    color: #fff;
    cursor: pointer;
    border: solid 1px #BA68C8
   
}
.container {

}
.row{
	--bs-gutter-y : 0rem;
}

.pbtn {
	margin: 3px;
	float: right;
 }
</style>
</head>
<body>
<%
	ProfessorVO proVo = (ProfessorVO)request.getAttribute("proVO");
%>






<div class="container-content">
 <div class="row">
  <div class="row col-md-12">
   <div class="frame content">
    <div class="stumanage">
     <div class="title stuttl">내 정보</div>
      <div class="container rounded bg-white mt-5 mb-5">
       <div class="row infoForm">
        <div class="col-md-4 border-right">
         <div class="d-flex flex-column align-items-center text-center p-3 py-5">
         <img src="<%=request.getContextPath()%>/Info/ProImageView.do?proId=<%= proVo.getPro_id()%>"  id="profil"width="300" height="300"></div>
        </div>
          <div class="col-md-8 border-right">
           <div class="p-1 py-1">
		    <div class="btngroup">
<!-- 			<input id="btnUpdate" type="button" class="btn btn-primary btn-sm " value="수정">  -->
<!-- 			<input type="button" id="btnList" class="btn btn-primary btn-sm " value="목록"> -->
	        </div>	
	        
            <form action="<%=request.getContextPath()%>/Info/proModify.do" 
				  method="post" enctype="multipart/form-data" id="form">
				  
			<input type="hidden" name="pro_id" id="pro_id" value="<%=proVo.getPro_id() %>">
			<input type="hidden" name="old_photo" value="<%=proVo.getPro_poto()%>">	  
				  
            <div class="d-flex justify-content-between align-items-center mb-1">
                    <h4 class="text-right">Profile Settings</h4>
            </div>
              <div class="row mt-3">
                    <div class="col-md-8"><label class="labels">교수ID</label><input type="text" class="form-control" disabled value="<%=proVo.getPro_id()%>"></div>
                    <div class="col-md-8"><label class="labels">교수 이름</label><input type="text" class="form-control" disabled value="<%=proVo.getPro_nm()%>"></div>
                    <div class="col-md-8"><label class="labels">주민 번호</label><input type="text" class="form-control" disabled value="<%=proVo.getPro_reg() %>" ></div>
                    <div class="col-md-8"><label class="labels">비밀 번호</label>
                    <input type="text" class="form-control"  value="<%=proVo.getPro_pw() %>" id="pro_pw" style="width: 200px; display: inline; margin-bottom: 5px; margin-top: 5px; ">
                    <input type="button" id="pwChange" value="비밀번호 변경" style="text-align: center;"  class="btn btn-primary btn-sm ">
                    </div>
                    <div class="col-md-8"><label class="labels">우편 번호</label>
					<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" style="margin-bottom: 5px;"  class="btn btn-primary btn-sm "><br>
                    <input type="text" id="sample6_postcode" class="form-control" name="pro_zip" style="width: 200px; display: inline; margin-bottom: 5px;" value="<%=proVo.getPro_zip() %>">
					<input type="text"  class="form-control" id="sample6_address" name="pro_addr" placeholder="주소" value="<%=proVo.getPro_addr()%>"><br>
                    
                    </div>
<%--                     <div class="col-md-8"><label class="labels">주소</label><input type="text" class="form-control"  placeholder="<%=stdVo.getStu_addr() %>" value=""></div> --%>
                    <div class="col-md-8"><label class="labels">메일</label><input type="text" class="form-control" name="pro_mail" value="<%=proVo.getPro_mail() %>" ></div>
                    <div class="col-md-8"><label class="labels">전화번호</label><input type="text" class="form-control" name="pro_tel" value="<%=proVo.getPro_tel() %>" ></div>
          			<div class="col-md-8"><label class="labels">프로필사진</label><input type="file" name="pro_photo">
		            <br>
		            <input type="submit" id="send" value="정보수정" class="btn btn-primary btn-sm pbtn" > 
				    <input type="reset" id="cancel" value="취소" class="btn btn-primary btn-sm pbtn" >
          </div><br>
          </div>
          </form>
        </div>
       </div>
      </div>
     </div>
    </div>
   </div>
  </div>
 </div>
</div>



<!-- <h2>회원 정보</h2> -->
<%-- <form action="<%=request.getContextPath()%>/Info/proModify.do"  --%>
<!-- 	method="post" enctype="multipart/form-data" id="form"> -->
<%-- 	<input type="hidden" name="pro_id" value="<%=proVo.getPro_id() %>"> --%>
<%-- 	<input type="hidden" name="old_photo" value="<%=proVo.getPro_poto() %>"> --%>
<!-- <table border="1"> -->
<!-- <tbody> -->
<!-- 	<tr> -->
<%-- 		<td colspan="2" style="text-align:center;"><img src="<%=request.getContextPath() %>/Info/ProImageView.do?proId=<%=proVo.getPro_id() %>" width="200" height="140"></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>교수ID</td> -->
<%-- 		<td><input type="id" name="pro_id" disabled value="<%=proVo.getPro_id()%>"></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>비밀번호</td> -->
<%-- 		<td><input type="password" name="pro_pass" value="<%=proVo.getPro_pw()%>"> --%>
<!-- 			<input type="button" id="pwChange" value="비밀번호 변경" style="text-align: center;"></td> -->
		
<!-- 	</tr> -->
<!-- 		<td>이름</td> -->
<%-- 		<td><input type="text" name="pro_nm" disabled value="<%=proVo.getPro_nm()%>"></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>전화번호</td> -->
<%-- 		<td><input type="text" name="pro_tel" value="<%=proVo.getPro_tel()%>"></td> --%>
<!-- 	</tr>	 -->
<!-- 	<tr> -->
<!-- 		<td>주소</td> -->
<%-- 		<td><input type="text" id="sample6_postcode" name="pro_zip" placeholder="우편번호" value="<%=proVo.getPro_zip()%>"> --%>
<!-- 			<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br> -->
<%-- 			<input type="text" id="sample6_address" name="pro_addr" placeholder="주소" value="<%=proVo.getPro_addr()%>"><br> --%>
<!-- 	</tr>	 -->
<!-- 	<tr> -->
<!-- 		<td>메일주소</td> -->
<%-- 		<td><input type="text" name="pro_mail" value="<%=proVo.getPro_mail()%>"></td> --%>
<!-- 	</tr> -->

<!-- 	<tr> -->
<!-- 		<td>프로필 사진</td> -->
<!-- 		<td><input type="file" name="pro_photo"></td> -->
<!-- 	</tr>	 -->
<!-- 	<tr> -->
<!-- 		<td colspan="2" style="text-align:center;"><input type="submit" id="send" value="정보수정">  -->
<!-- 		<input type="reset" id="cancel" value="취소">  -->
<!-- 	</tr> -->
<!-- </tbody> -->
<!-- </table> -->
<!-- </form> -->










</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
              
            }
        }).open();
    }
</script>

</html>