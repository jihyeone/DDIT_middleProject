<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.middle.vo.SubjectVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/layout/common_lib.jsp" flush="false" />
<title>Insert title here</title>



<style type="text/css">
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
</head>
<script type="text/javascript">
	$(function() {
		$('#btnList').on('click', function() {
			window.close();
		})
	})
</script>

<%
	String newproId = (String) session.getAttribute("newproId");
%>

<body>
	<form class="form"id="ProfessorForm" method="post"
							action="<%=request.getContextPath()%>/InsertProfessor.do"
							enctype="multipart/form-data">
		<h3>CONTACT US</h3>
		<p type="교수ID">
			<input type="text" name="pro_id" id="pro_id"
				value="pro<%=newproId.trim()%>" readonly></input>
		</p>
		<p type="교수이름">
			<input name="pro_nm" id="pro_nm"></input>
		</p>
		<p type="주민번호:">
			<input name="pro_reg" id="pro_reg"></input>
		</p>
		<p type="전화번호:">
			<input name="pro_tel"></input>
		</p>
		<p type="회원주소:">
			<input name="pro_addr"></input>
		</p>
		<p type="교수 이메일:">
			<input name="pro_mail"></input>
		</p>
		<p type="과목코드">
			<select name="sub_cd">
				<option value="1">데이터베이스</option>
				<option value="2">초급JAVA</option>
				<option value="3">고급JAVA</option>
				<option value="4">WEBPRO</option>
				<option value="5">JQUERY</option>
				<option value="6">AJAX</option>
				<option value="7">PYTHON</option>
			</select>
		</p>
		<p type="프로필 사진">
			<input type="file" name="pro_poto"></input>
		</p>
		<p type="우편번호">
			<input name="pro_zip"></input>
		</p>
		<button>Send Message</button>

	</form>
</body>
</html>