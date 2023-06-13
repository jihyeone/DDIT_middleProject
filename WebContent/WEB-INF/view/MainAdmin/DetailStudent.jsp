<%@page import="kr.or.ddit.middle.vo.AdminVO"%>
<%@page import="kr.or.ddit.middle.vo.ProfessorVO"%>
<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.3.min.js"></script>

<%  
	ProfessorVO loginpro = (ProfessorVO)session.getAttribute("LoginProfessor");	// 로그인 중인 교수 아이디
	AdminVO loginadmin = (AdminVO)session.getAttribute("LoginAdmin");	// 로그인 중인 관리자
	StudentVO stuVo = (StudentVO)request.getAttribute("studentVo");
	String stuFin = "";
	if(stuVo.getStu_fin().equals("N")){
		stuFin = "수강중";
	} else{
		stuFin = "수료";
	}
	String stuDrop="";
	if(stuVo.getStu_drop().equals("N")){
		stuDrop ="수강중";
	} else{
		stuDrop = "수강포기";
	}
%>


<script type="text/javascript">

$(function(){
	$("#btnUpdate").on("click", function(){
		var form = document.getElementById("studentForm");
		form.method = "GET";
		form.action = "<%=request.getContextPath()%>/updateStudent.do";
		form.submit();
	});
	
	$("#btnDelete").on("click", function(){
		var form = document.getElementById("studentForm");
		form.action = "<%=request.getContextPath()%>/deleteStudent.do";
		form.submit();
	});
	

	$("#btnList").on("click", function(){
		<% if(loginadmin != null){ %>
			location.href = "<%=request.getContextPath()%>/managerStudent.do";
		<% } else if(loginpro != null){ %>
			location.href = "<%= request.getContextPath() %>/classListPro.do?proId=<%= loginpro.getPro_id() %>"
		<% } %>
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







<div class="container-content">
 <div class="row">
  <div class="row col-md-12">
   <div class="frame content">
    <div class="stumanage">
     <div class="title stuttl">학생 상세 정보</div>
      <div class="container rounded bg-white mt-5 mb-5">
       <div class="row infoForm">
        <div class="col-md-4 border-right">
         <div class="d-flex flex-column align-items-center text-center p-3 py-5">
         <img src="<%=request.getContextPath()%>/Info/StuImageView.do?stuId=<%=stuVo.getStu_id()%>"  id="profil"width="300" height="300"></div>
        </div>
          <div class="col-md-8 border-right">
           <div class="p-1 py-1">
		    <div class="btngroup">
		    
		  <%
				if(loginadmin != null){		    
		    %>
		    
			<input id="btnUpdate" type="button" class="btn btn-primary btn-sm stuModifyBtn" value="수정"> 
			<input id="btnDelete" type="button" class="btn btn-primary btn-sm stuDelBtn" value="삭제"> 
			
			<%
				}
			%>
			
			
			<input type="button" id="btnList" class="btn btn-primary btn-sm StuListBtn" value="목록">
	        </div>	
            <form class="list" name="studentForm" id="studentForm">
			<input type="hidden" id="stu_id"name="stu_id" value="<%=stuVo.getStu_id() %>">
            <div class="d-flex justify-content-between align-items-center mb-1">
                    <h4 class="text-right">Profile Settings</h4>
            </div>
              <div class="row mt-3">
                    <div class="col-md-8"><label class="labels">학생ID</label><input type="text" class="form-control" value="" disabled="disabled" placeholder="<%=stuVo.getStu_id()%>"></div>
                    <div class="col-md-8"><label class="labels">학생 이름</label><input type="text" class="form-control" disabled="disabled" placeholder="<%=stuVo.getStu_nm()%>" value=""></div>
                    <div class="col-md-8"><label class="labels">주민 번호</label><input type="text" class="form-control" disabled="disabled" placeholder="<%=stuVo.getStu_reg().substring(0,7) + "*******" %>" value=""></div>
                    <div class="col-md-8"><label class="labels">비밀 번호</label><input type="text" class="form-control" disabled="disabled" placeholder="<%=stuVo.getStu_pw() %>" value=""></div>
                    <div class="col-md-8"><label class="labels">우편 번호</label><input type="text" class="form-control" disabled="disabled" placeholder="<%=stuVo.getStu_zip() %>" value=""></div>
                    <div class="col-md-8"><label class="labels">주소</label><input type="text" class="form-control" disabled="disabled" placeholder="<%=stuVo.getStu_addr() %>" value=""></div>
                    <div class="col-md-8"><label class="labels">메일</label><input type="text" class="form-control" disabled="disabled" placeholder="<%=stuVo.getStu_mail() %>" value=""></div>
                    <div class="col-md-8"><label class="labels">전화번호</label><input type="text" class="form-control" disabled="disabled" placeholder="<%=stuVo.getStu_tel() %>" value=""></div>
                    <div class="col-md-8"><label class="labels">학급</label><input type="text" class="form-control" disabled="disabled" placeholder="<%=stuVo.getClass_no() %>" value=""></div>
                    <div class="col-md-8"><label class="labels">수료여부</label><input type="text" class="form-control" disabled="disabled" placeholder="<%=stuFin %>" value=""></div>
                    <div class="col-md-8"><label class="labels">수강여부</label><input type="text" class="form-control" disabled="disabled" placeholder="<%=stuDrop %>" value=""></div>
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