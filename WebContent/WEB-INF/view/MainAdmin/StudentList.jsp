<%@page import="org.apache.tomcat.jni.Stdlib"%>
<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@page import="kr.or.ddit.middle.vo.ClassClassVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/layout/common_lib.jsp" flush="false" />
<!DOCTYPE html>
<html>
	<script type="text/javascript">
	
	function proc1(){
		ival = $('#sword').val();
				console.log(ival);
		class_no = $('#class_no').val();
				console.log(class_no);
		location.href="<%=request.getContextPath()%>/Search.do?ival="+ival+"&class_no="+class_no;
	}
$(function(){
	
	$('#goback').on('click', function() {
		location.href="<%=request.getContextPath()%>/ClassList.do";
	})
	
	$("#btnDelete").on("click", function(){
		var form = document.getElementById("ClassForm");
		form.action = "<%=request.getContextPath()%>/deleteClass.do";
		form.submit();
	});
		$("#btnUpdate").on("click", function(){
			var form = document.getElementById("ClassForm");
			form.method = "GET";
			form.action = "<%=request.getContextPath()%>/UpdateClass.do";
			form.submit();
		});
});
		
</script>
	<style>
select {
	float: right auto;
	margin: 5px auto;
}
#professor{
	float: right;
	font-size: 25px;
}
strong{
	font-size: 30px;
	font-style: bold;
}

</style>
<head>
<body>
	<%
		List<StudentVO> slist = (List<StudentVO>) request.getAttribute("studentList");
		ClassClassVO classVo  = (ClassClassVO)request.getAttribute("classVo");
		ClassClassVO classVO2 = (ClassClassVO)request.getAttribute("classList");
	%>


	<div class="container-content">
		<div class="row">
			<div class="row col-md-12">
				<div class="frame content">
					<div class="stumanage">
						<div class="title stuttl">학생관리</div>
						<div class="btngroup" style="margin-right: 5px; margin-bottom: 5px;"  >
							<form class="d-flex" name="ClassForm" id="ClassForm">
							<input type="hidden" id="class_no" name="class_no" value="<%=classVo.getClass_no() %>">
								<input id="sword"  type="text" name="Searchinput"
									placeholder="이름 검색" style="margin-right: 5px;">
								<button class="btn btn-primary"  type="button" id="search" style="margin-right: 5px;" onclick="proc1()">검색</button>
								<input id="btnUpdate" type="button" class="btn btn-primary btn-sm crud modify" style="margin-right: 5px;"  value="수정">
                            	<input id="btnDelete" type="button" class="btn btn-primary btn-sm crud delete" style="margin-right: 5px;" value="제거">
								<button class="btn btn-primary"  type="button" id="goback"  >뒤로</button>
							</form>
						</div>
							<br><br>
						<strong>[<%=classVO2.getTr_nm()%>]</strong><strong id="professor">담당 교수: <%=classVO2.getPro_nm() %></strong>
						<div id="slist">
							<table class="table" id="Cboard">
								<thead class="table-success">
									<tr>
										<td>학급번호</td>
										<td>학생번호</td>
										<td>학생이름</td>
										<td>학생전화번호</td>
										<td>이메일</td>
								</thead>
								<%
									if (slist == null || slist.size() ==0) {
								%>
								<tr>
									<td colspan="5">학급 목록이 없습니다..</td>
								</tr>
								<%
									} else {

								for (StudentVO stdlist : slist) {
								%>
								<tr>
									<td><%=stdlist.getClass_no()%></td>
									<td><%=stdlist.getStu_id()%></td>
									<td><%=stdlist.getStu_nm()%></td>
									<td><%=stdlist.getStu_tel()%></td>
									<td><%=stdlist.getStu_mail()%></td>
								</tr>
								<%
									}
								}
								%>
								</tbody>
							</table>
<!-- 							<div id="pagging">페이징</div> -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
