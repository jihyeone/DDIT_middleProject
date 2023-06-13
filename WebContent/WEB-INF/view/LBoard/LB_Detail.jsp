<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.middle.vo.LB_AttachVO"%>
<%@page import="kr.or.ddit.middle.vo.ProfessorVO"%>
<%@page import="kr.or.ddit.middle.vo.LB_CodeVO"%>
<%@page import="kr.or.ddit.middle.vo.Lect_BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Lect_BoardVO lbVO = (Lect_BoardVO) request.getAttribute("lbVO");
	LB_CodeVO codeVO = (LB_CodeVO) request.getAttribute("codeVO");
	ProfessorVO proVO = (ProfessorVO) request.getAttribute("proVO");
	LB_AttachVO fileVO = (LB_AttachVO) request.getAttribute("fileVO");
	
// 	List<LB_AttachVO> fileList = (List<LB_AttachVO>) request.getAttribute("fileList");
%>

<style type="text/css">
label{
/* 	font-size: 1.3em; */
	font-weight: bold;
	display: inline-block;
	width: 100px;
}
#cndiv{
	margin-left: 15px;
}

</style>

<script>
$(function() {
	// 목록으로
	$('.LbList').on('click', function() {
		location.href="<%= request.getContextPath() %>/lboard/lBoardList.do?spage=1";
	})
	
})

</script>

	<div class="container-content container mt-3">
		<div class="row">
			<div class="row col-md-12">
			<div class="frame content">
			<div class="stumanage">
				<div class="title stuttl">강의게시판</div>
				<div class="btngroup">
				<input type="button" class="btn btn-primary btn-sm LbList" value="목록으로 돌아가기">
				</div>
				
				<%
				if(lbVO == null) {
					out.println("<h3>해당 게시글이 없습니다.</h3>");
				}else {
				%>
					<form name="lbDetailForm">
					<br>
					<label>&nbsp;&nbsp;분&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;류 : </label>
					<span class="Lbspan">&nbsp;<%= codeVO.getLb_nm() %></span><br>
					<label>&nbsp;&nbsp;제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목 : </label>
					<span class="Lbspan">&nbsp;<%= lbVO.getLboard_ttl() %></span><br>
					<label>&nbsp;&nbsp;작 성 자 : </label>
					<span class="Lbspan">&nbsp;<%= proVO.getPro_nm() %></span><br>
					<label>&nbsp;&nbsp;날&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;짜 : </label>
					<span class="Lbspan">&nbsp;<%= lbVO.getLboard_date().substring(0, 10) %></span><br>
					<label>&nbsp;&nbsp;조 회 수 : </label>
					<span class="Lbspan">&nbsp;<%= lbVO.getLboard_rc() %></span><br>
					<label>&nbsp;&nbsp;내&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;용 : </label><br>
					<div class="Lbspan" id="cndiv"><%= lbVO.getLboard_cn() %></div>
					<hr>
					<label>&nbsp;&nbsp;첨부파일</label><br>
					<input type="hidden" name="lfile_no" value="<%= fileVO.getLfile_no() %>">
					<span>&nbsp;<%= fileVO.getLfile_nm() %></span>&nbsp;&nbsp;
					<a href="<%= request.getContextPath() %>/lboard/lBoardFileDown.do?lfile_no=<%= fileVO.getLfile_no() %>">[DownLoad]</a>
					<br>
					</form>
				<%	
				}
				%>
			</div>
			</div>
			</div>
		</div>
	</div>
