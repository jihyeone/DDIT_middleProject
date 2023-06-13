<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>layoutstu</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%--
	공통으로 사용할 CSS파일과 JavaScript파일을 등록하는 부분
	<jsp:include page="/layout/common_lib.jsp" flush="false"/>
 --%>

<jsp:include page="/layout/common_lib.jsp" flush="false"/>

<style type="text/css">
.container-fluid{
--bs-gutter-x: 0rem;
}
.stuttl{
    width: 100%;
    height: 50px;
/*     border-bottom: 1px solid #bd5d38; */
    background-color: #FDE1B4;
/*     text-shadow: 1px 1px gray; */
	color: #f39c12;
	font-size: 1.7em;;
	font-weight: bold;
    text-indent: 10px;
}
#pagging{
    position: relative;
    bottom: 20px;
    text-align: center;
    display: block;
/*     border: 1px solid red; */
    margin: 100px auto;
    width: 100px;
}
table{
    height: 80%;
/*     border: 1px solid green; */
	
}
.content{
/*     border: 1px solid red; */
	margin-top: 30px;
}
#list{
    padding: 20px;
/*     border: 1px dotted green;  */
}
.stumanage{
    margin: 0px auto;
    width: 100%;
    height : 750px;
/*     border: 1px solid blue; */
/*     background-color:  #f3f0f0; */
    position: relative;
}
.btngroup{
    float: right;
    margin-top: 5px;
    margin-right: 20px;
}
a{
	text-decoration: none;
}
li{
	list-style-type: none;	
}
</style>


</head>
<%--

		각 레이아웃 영역에 나타낼 문서를 가져오고 지정된 문서가 없을 때 기본적으로 나타낼 문서를 지정하는 부분
		String topPage = (String)request.getAttribute("topPage");
		if(topPage == null || "".equals(topPage)) topPage = "/layout/header.jsp";
		
		String leftPage = (String)request.getAttribute("leftPage");
		if(leftPage == null || "".equals(leftPage)) leftPage = "/layout/left.jsp";
	
		String viewPage = (String)request.getAttribute("viewPage");
		if(viewPage == null || "".equals(viewPage)) viewPage = "/WEB-INF/view/main/main.jsp";


 --%>
<%

		// 각 레이아웃 영역에 나타낼 문서를 가져오고 지정된 문서가 없을 때 기본적으로 나타낼 문서를 지정하는 부분
		String topPage = (String)request.getAttribute("topPage");
		if(topPage == null || "".equals(topPage)) topPage = "/layout/header.jsp";
		
		String leftPage = (String)request.getAttribute("leftPage");
		if(leftPage == null || "".equals(leftPage)) leftPage = "/layout/leftStudent.jsp";
		
		String viewPage = (String)request.getAttribute("viewPage");
		if(viewPage == null || "".equals(viewPage)) viewPage = "/WEB-INF/view/MainStudent/Mainstupage.jsp";

%>


<jsp:include page="<%=topPage %>" flush="false"/>
<div class="container-fluid">
        <div class="row">
            <div class="col-md-2" >
                <jsp:include page="<%=leftPage %>" flush="false"/>
            </div>
            <div class="col-md-10" >
                <jsp:include page="<%=viewPage %>" flush="false"/>
            </div>
        </div>
    </div>
		

<!--       <footer class="footer"> -->
<!--         <div class="container"> -->
<!--         <p>기관명 : (재)대덕인재개발원주소 : 대전광역시 중구 계룡로 846, 3-4층   대표전화 : 042-222-8202 -->
<!-- 		<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;COPYRIGHT © 2020 (재)대덕인재개발원 ALL RIGHTS RESERVED</p> -->
<!--         </div> -->
<!--     </footer> -->

<%

	StudentVO Loginstu = (StudentVO) session.getAttribute("LoginStudent");

%>


<script type="text/javascript">
$(function(){
	$("#logoutbtn").on("click", function(){
		alert("로그아웃하셧습니다.");
		location.href="<%=request.getContextPath()%>/logout/StudentLogout.do";
	})
	
	
	$('#homebtn').on('click', function() {
		location.href="<%= request.getContextPath()%>/LoginMain.do?userid=<%=Loginstu.getStu_id() %>&userpass=<%=Loginstu.getStu_pw() %>&ddit=학생";
	})
	
	
	$('#memo').on('click', function() {
		location.href="<%= request.getContextPath() %>/message/messageList.do?stuId=<%= Loginstu.getStu_id()%>"
	})
	
	
	
  })
  
function counsel() {
	location.href="<%=request.getContextPath() %>/counsel/CounselListstu.do?stuId=<%= Loginstu.getStu_id()%>&spage=1"
}


function lboard() {
    location.href="<%= request.getContextPath() %>/lboard/lBoardList.do?&spage=1";
 }

</script>



<body>


</body>
</html>