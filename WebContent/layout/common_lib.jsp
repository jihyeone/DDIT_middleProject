<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%-- /layout/common_lib.jsp 문서 --%> 

<%-- 공통으로 사용할 CSS파일과 JavaScript파일(js)을 등록 --%>
    

<!-- Bootstrap core CSS 파일 등록 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Saira+Extra+Condensed:500,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Muli:400,400i,800,800i" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath() %>/css/styles.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/studentMain.css">

<!-- javascript 파일 등록 -->
	<!-- Core theme JS-->
	<script src="<%=request.getContextPath() %>/js/jquery-3.6.3.min.js"></script>
<!-- 	캘린더 -->
<!-- 	<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.1/index.global.min.js'></script> -->
	<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.4/index.global.min.js'></script>
	

	<!-- Bootstrap core JS-->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="<%= request.getContextPath() %>/js/jquery.serializejson.min.js" type="text/javascript"></script>
	