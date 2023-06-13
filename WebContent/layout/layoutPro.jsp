<%@page import="kr.or.ddit.middle.vo.ProfessorVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>layoutPro</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%--
	공통으로 사용할 CSS파일과 JavaScript파일을 등록하는 부분
	<jsp:include page="/layout/common_lib.jsp" flush="false"/>
 --%>

<jsp:include page="/layout/common_lib.jsp" flush="false"/>
<style type="text/css">
.row{
	--bs-gutter-y: 2.11rem;
}
footer{
	padding-top: 15px;

}

footer p{
	padding-left: 300px;
}
.container-fluid{
--bs-gutter-x: 0rem;
}
.stuttl{
    width: 100%;
    height: 50px;
/*     border-bottom: 1px solid #bd5d38; */
    background-color: #FDE1B4;
    color: #f39c12;
	font-size: 1.7em;;
	font-weight: bold;
/*     text-shadow: 1px 1px gray; */
    text-indent: 10px;
    margin-bottom: 5px;
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
}
#list{
    padding: 10px;
}
.stumanage{
    margin: 10px auto;
    width: 100%;
/*     border: 1px solid blue; */
/*     background-color:  #f3f0f0; */
    position: relative;
}
.btngroup{
    float: right;
    margin-top: 5px;
    margin-right: 20px;
}

.ft{
	margin-top: 130px;
}
a{
	text-decoration: none;
}
li{
	list-style-type: none;	
}
</style>


<%
	ProfessorVO Loginpro = (ProfessorVO) session.getAttribute("LoginProfessor");
%>

   <script type="text/javascript">
   $(function(){
		$("#logoutbtn").on("click", function(){
			alert("로그아웃하셧습니다.");
			location.href="<%=request.getContextPath()%>/logout/ProfessorLogout.do";
		})
		
		$('#homebtn').on('click', function() {
		location.href="<%= request.getContextPath()%>/LoginMain.do?userid=<%=Loginpro.getPro_id() %>&userpass=<%= Loginpro.getPro_pw() %>&ddit=교직원";
	})
		
	
	
		$('#Cns').on('click', function() {
			location.href="<%= request.getContextPath()%>/counsel/CounselListPro.do?proId=<%= Loginpro.getPro_id() %>&spage=1"
		})
		
	
	
	   })
	   
	  function counsel() {
	   location.href="<%= request.getContextPath()%>/counsel/CounselListPro.do?proId=<%= Loginpro.getPro_id() %>&spage=1";
	} 
   
	  function lboard() {
		location.href="<%= request.getContextPath() %>/lboard/lBoardProList.do?pro_id=<%= Loginpro.getPro_id() %>&spage=1";
	}
	   
   </script>
    <style>
        #new{
            float: right;
            width: 25px;
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
		if(leftPage == null || "".equals(leftPage)) leftPage = "/layout/leftProfessor.jsp";
		
		String viewPage = (String)request.getAttribute("viewPage");
		if(viewPage == null || "".equals(viewPage)) viewPage = "/WEB-INF/view/MainProfessor/Mainpropage.jsp";

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
		

<!--   <div class="container-fluid footer ft"> -->
<!--         <div class="row">    <footer> -->
<!--         <p>기관명 : (재)대덕인재개발원주소 : 대전광역시 중구 계룡로 846, 3-4층   대표전화 : 042-222-8202 -->
<!-- 		<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;COPYRIGHT © 2020 (재)대덕인재개발원 ALL RIGHTS RESERVED</p> -->
<!--    		 </footer> -->
<!--         </div> -->
<!--         </div> -->



<body>


</body>
</html>