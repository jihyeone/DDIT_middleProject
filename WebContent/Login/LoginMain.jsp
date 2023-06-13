<%@page import="kr.or.ddit.middle.vo.AdminVO"%>
<%@page import="kr.or.ddit.middle.vo.ProfessorVO"%>
<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DDIT LoginPage</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="<%= request.getContextPath() %>/js/jquery-3.6.3.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <title>LoginMain</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/Loginstyle.css">

</head>
<body>


<!-- LSY 작성 세션스토리지 -->


<%

	StudentVO LoginStudent = (StudentVO)session.getAttribute("LoginStudent");
	ProfessorVO LoginProfessor = (ProfessorVO)session.getAttribute("LoginProfessor");
 	AdminVO LoginAdmin = (AdminVO)session.getAttribute("LoginAdmin");
	
	String radio = (String)session.getAttribute("radio");
%>

<%
if(LoginStudent==null && LoginProfessor==null && LoginAdmin==null){
%>

<!--                                                                   -->



        <div class="parent">
        <div class="child left">
            <img src="<%= request.getContextPath() %>/images/it.jpg" class="imgcut">
            <!-- <img src="../images/dditmain.png" id="benericon"> -->
        </div>
        <div class="child center" id="center">
            <div id="main">
                <div id="Loginform">
                <h3>DDIT 포탈 서비스</h3>
                <form action="<%=request.getContextPath()%>/LoginMain.do" method="post">
                <div class="form-group">
                   <!-- <label>User Name</label> -->
                   <input type="text" class="form-control" placeholder="User Name" name="userid">
                </div>
                <div class="form-group">
                   <!-- <label>Password</label> -->
                   <input type="password" class="form-control" name="userpass" id="userpass" placeholder="Password">
                </div>
                <span class="check"><input type="radio" id="sutudent" name="ddit" value="학생">학생</span>&nbsp;&nbsp;
                <span class="check"><input type="radio" id="professor" name="ddit" value="교직원">교직원</span>
                <br><br>
                <button type="submit" class="btn btn-black" id="loginbtn">Login</button><br>
                <br>
                <span class="find"><a class="find" href="<%=request.getContextPath()%>/find/findId.do">아이디찾기</a></span>&nbsp;&nbsp;
                <span class="find"><a class="find" href="<%=request.getContextPath()%>/find/findPw.do">비밀번호 찾기</a></span>
             </form>
            </div>
            </div>
        </div>
        <div class="child right" id="right">
            <img src="<%= request.getContextPath() %>/images/dditmain.png" id="benericon">
        </div>
    </div>
    
    
    <%
	}else if(LoginStudent!=null && LoginProfessor==null && LoginAdmin ==null){
	%>
	<a href="<%=request.getContextPath() %>/studentLogin.do"></a>
	<%
	}else if(LoginProfessor!=null && LoginStudent==null && LoginAdmin==null){
	%>
	<a href="<%=request.getContextPath() %>/loginProfessor.do"></a>
	<%
	}else if(LoginAdmin!=null && LoginStudent!=null && LoginProfessor==null){
	%>
	<a href="<%=request.getContextPath() %>/loginAdmin.do"></a>
	<%
	}
	%>
    
    
</body>
</html>