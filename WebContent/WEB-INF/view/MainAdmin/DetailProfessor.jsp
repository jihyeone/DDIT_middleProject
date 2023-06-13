<%@page import="kr.or.ddit.middle.vo.ProfessorVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.3.min.js"></script>

<script type="text/javascript">

$(function(){
	$("#btnUpdate").on("click", function(){
		var form = document.getElementById("professorForm");
		form.method = "GET";
		form.action = "<%=request.getContextPath()%>/UpdateProfessor.do";
		form.submit();
	});
	
	$("#btnDelete").on("click", function(){
		var form = document.getElementById("professorForm");
		form.action = "<%=request.getContextPath()%>/DeleteProfessor.do";
		form.submit();
	});
	$("#btnList").on("click", function(){
		location.href = "<%=request.getContextPath()%>/ManagerProfessor.do";
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
</style>
</head>
<body>
<% 
ProfessorVO proVo = (ProfessorVO)request.getAttribute("professorVo");
%>

<div class="container-content">
 <div class="row">
  <div class="row col-md-12">
   <div class="frame content">
    <div class="stumanage">
     <div class="title stuttl">교수 상세 정보</div>
      <div class="container rounded bg-white mt-5 mb-5">
       <div class="row infoForm">
        <div class="col-md-4 border-right">
         <div class="d-flex flex-column align-items-center text-center p-3 py-5">
         <img src="<%=request.getContextPath()%>/Info/ProImageView.do?proId=<%=proVo.getPro_id()%>"  id="profil"width="300" height="300"></div>
        </div>
          <div class="col-md-8 border-right">
           <div class="p-1 py-1">
		    <div class="btngroup">
			<input id="btnUpdate" type="button" class="btn btn-primary btn-sm counsleBtn" value="수정"> 
			<input id="btnDelete" type="button" class="btn btn-primary btn-sm counsleBtn" value="삭제"> 
			<input type="button" id="btnList" class="btn btn-primary btn-sm counsleBtn" value="목록"></td>
	        </div>	
            <form class="list" name="professorForm" id="professorForm">
			<input type="hidden" id="pro_id"name="pro_id" value="<%=proVo.getPro_id() %>">
            <div class="d-flex justify-content-between align-items-center mb-1">
                    <h4 class="text-right">Profile Settings</h4>
            </div>
              <div class="row mt-3">
                    <div class="col-md-8"><label class="labels">교수ID</label><input type="text" class="form-control" value="" disabled="disabled" placeholder="<%=proVo.getPro_id() %>"></div>
                    <div class="col-md-8"><label class="labels">교수 이름</label><input type="text" class="form-control" disabled="disabled" placeholder="<%=proVo.getPro_nm()%>" value=""></div>
                    <div class="col-md-8"><label class="labels">주민 번호</label><input type="text" class="form-control" disabled="disabled" placeholder="<%=proVo.getPro_reg().substring(0,7) + "*******" %>" value=""></div>
                    <div class="col-md-8"><label class="labels">비밀 번호</label><input type="text" class="form-control" disabled="disabled" placeholder="<%=proVo.getPro_pw() %>" value=""></div>
                    <div class="col-md-8"><label class="labels">주소</label><input type="text" class="form-control" disabled="disabled" placeholder="<%=proVo.getPro_addr() %>" value=""></div>
                    <div class="col-md-8"><label class="labels">메일</label><input type="text" class="form-control" disabled="disabled" placeholder="<%=proVo.getPro_mail() %>" value=""></div>
                    <div class="col-md-8"><label class="labels">전화번호</label><input type="text" class="form-control" disabled="disabled" placeholder="<%=proVo.getPro_tel() %>" value=""></div>
                    <div class="col-md-8"><label class="labels">과목코드</label><input type="text" class="form-control" disabled="disabled" placeholder="<%=proVo.getSub_cd() %>" value=""></div>
                    <div class="col-md-8"><label class="labels">우편 번호</label><input type="text" class="form-control" disabled="disabled" placeholder="<%=proVo.getPro_zip() %>" value=""></div>
          </div>
          </form>
<!--                 <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="button">Save Profile</button></div> -->
        </div>
       </div>
      </div>
     </div>
    </div>
   </div>
  </div>
 </div>
</div>
</body>
</html>