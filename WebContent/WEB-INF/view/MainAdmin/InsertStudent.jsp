<%@page import="kr.or.ddit.middle.vo.ClassClassVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/layout/common_lib.jsp" flush="false" />
<title>Insert title here</title>
</head>
<style>
body {
	background: #F7E9B4;
	margin: 0
}

.form {
	width: 500px;
	height: 800px;
	background: #e6e6e6;
	border-radius: 8px;
	box-shadow: 0 0 40px -10px #000;
	margin: 0 auto;
	padding: 20px 30px;
	max-width: calc(100vw - 40px);
	box-sizing: border-box;
	font-family: 'Montserrat', sans-serif;
	position: relative;
	top : 10px;
}

h2 {
	margin: 10px 0;
	padding-bottom: 10px;
	width: 150px;
	color: #78788c;
	border-bottom: 3px solid #78788c
}

input {
	width: 100%;
	padding: 5px;
	box-sizing: border-box;
	background: none;
	outline: none;
	resize: none;
	border: 0;
	font-family: 'Montserrat', sans-serif;
	transition: all .3s;
	border-bottom: 2px solid #bebed2
}

input:focus {
	border-bottom: 2px solid #78788c
}

p:before {
	content: attr(type);
	display: block;
	margin: 5px 0 0;
	font-size: 14px;
	color: #5a5a5a
}

button {
	float: right;
	padding: 8px 8px;
	margin: 8px 0 0;
	font-family: 'Montserrat', sans-serif;
	border: 2px solid #78788c;
	background: 0;
	color: #5a5a6e;
	cursor: pointer;
	transition: all .3s
}

button:hover {
	background: #78788c;
	color: #fff
}

div {
	content: 'Hi';
	position: absolute;
	bottom: -15px;
	right: -20px;
	background: #50505a;
	color: #fff;
	width: 320px;
	padding: 16px 4px 16px 0;
	border-radius: 6px;
	font-size: 13px;
	box-shadow: 10px 10px 40px -14px #000
}

span {
	margin: 0 5px 0 15px
}
</style>

<script>
	$(function(){
		$('#btnList').on('click', function(){
			window.close();
		})
	})
	
</script>

<%
	List<ClassClassVO> clist = (List<ClassClassVO>) request.getAttribute("clist");

%>

<body>
	<form class="form" id="StudentForm" method="post" action="<%=request.getContextPath()%>/insertStudent.do" enctype="multipart/form-data">
	<h3>CONTACT US</h3>
		<p type="학생 ID">
			<input type="text" name="stu_id" id="stu_id">
		</p>
		
		<p type="학생 이름">
			<input name="stu_nm" id="stu_nm">
		</p>
		
		<p type="주민번호">
			<input name="stu_reg" id="stu_reg">
		</p>
		
		<p type="전화번호">
			<input name="stu_tel" id="stu_tel">
		</p>
		
		<p type="학생 주소">
			<input name="stu_addr" id="stu_addr">
		</p>
		
		<p type="학생 메일">
			<input name="stu_mail" id="stu_mail">
		</p>
		
		<p type="학급">
			<select name="class_no">
				<%
					for(ClassClassVO list : clist){
						out.print("<option value='"+ list.getClass_no() +"'>"+ list.getClass_no() +"</option>");
					}
				%>
			</select>
		</p>
		
		<p type="프로필 사진">
			<input type="file" name="stu_poto" id="stu_poto">
		</p>
		
		<p type="우편번호">
			<input name="stu_zip" id="stu_zip">
		</p>
		<button>Send Message</button>
	</form>

</body>
</html>