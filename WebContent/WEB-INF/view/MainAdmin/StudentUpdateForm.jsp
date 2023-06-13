<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-3.6.3.min.js"></script>

<script type="text/javascript">
$(function(){
	
	$("#btnList").on("click", function(){
		location.href = "<%=request.getContextPath()%>/managerStudent.do";
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
	StudentVO stuVo = (StudentVO) request.getAttribute("stuVo");
%>
	 
	 
	 
	 
	 <div class="container-content">
 <div class="row">
  <div class="row col-md-12">
   <div class="frame content">
    <div class="stumanage">
     <div class="title stuttl">학생 상세 정보</div>
      <div class="container rounded bg-white mt-5 mb-5">
       <div class="row infoForm">
        <div class="col-md-4 border-right">
         <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img src="<%=request.getContextPath()%>/adminImageViewSt.do?stuID=<%=stuVo.getStu_id()%>"  id="profil"width="300" height="300"></div>
        </div>
          <div class="col-md-8 border-right">
           <div class="p-1 py-1">
		    <div class="btngroup">
			<input id="btnDelete" type="button" class="btn btn-primary btn-sm counsleBtn" value="삭제"> 
			<input type="button" id="btnList" class="btn btn-primary btn-sm counsleBtn" value="목록">
	        </div>	
            <form action="<%=request.getContextPath()%>/updateStudent.do"
				  method="post"  id="update" enctype="multipart/form-data">

			<input type="hidden" id="stu_id"name="stu_id" value="<%=stuVo.getStu_id() %>">
			<input type="hidden" name="old_poto" value="<%=stuVo.getStu_poto()%>">
            <div class="d-flex justify-content-between align-items-center mb-1">
                    <h4 class="text-right">Profile Settings</h4>
            </div>
              <div class="row mt-3">
                    <div class="col-md-8"><label class="labels">학생ID</label><input type="text" disabled class="form-control" name="stu_id" value="<%=stuVo.getStu_id()%>"></div>
                    <div class="col-md-8"><label class="labels">학생 이름</label><input type="text" class="form-control"  name="stu_nm" value="<%=stuVo.getStu_nm()%>"></div>
                    <div class="col-md-8"><label class="labels">주민 번호</label><input type="text" class="form-control" name="stu_reg" value="<%=stuVo.getStu_reg() %>"></div>
                    <div class="col-md-8"><label class="labels">비밀 번호</label><input type="text" class="form-control"  name="stu_pw" value="<%=stuVo.getStu_pw() %>"></div>
                    <div class="col-md-8"><label class="labels">우편 번호</label><input type="text" class="form-control" name="stu_zip" value="<%=stuVo.getStu_zip() %>"></div>
                    <div class="col-md-8"><label class="labels">주소</label><input type="text" class="form-control" name="stu_addr" value="<%=stuVo.getStu_addr() %>"></div>
                    <div class="col-md-8"><label class="labels">메일</label><input type="text" class="form-control" name="stu_mail" value="<%=stuVo.getStu_mail() %>"></div>
                    <div class="col-md-8"><label class="labels">전화번호</label><input type="text" class="form-control" name="stu_tel" value="<%=stuVo.getStu_tel() %>"></div>
                    <div class="col-md-8"><label class="labels">학급</label><input type="text" class="form-control"  name="class_no" value="<%=stuVo.getClass_no() %>"></div>
                    <div class="col-md-8"><label class="labels">수료여부</label>
                    <select name="stu_fin" class="form-select">
                    	<option value="수강중">수강중</option>
                    	<option value="수료">수료</option>
                    </select>
<!--                     <input type="text" class="form-control"  > -->
                    </div>
                    <div class="col-md-8"><label class="labels">수강여부</label>
<!--                     <input type="text" class="form-control"  > -->
                    <select name="stu_drop" class="form-select">
                    	<option value="수강중">수강중</option>
                    	<option value="수강포기">수강포기</option>
                    </select>
                    </div><br>
                    <div class="col-md-8"><label class="labels">프로필 변경</label><input type="file"  name="stu_poto"></div>
          </div>
                <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit">저장</button></div>
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
	 
</body>
</html>