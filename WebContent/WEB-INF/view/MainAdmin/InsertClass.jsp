<%@page import="kr.or.ddit.middle.vo.TrainingVO"%>
<%@page import="kr.or.ddit.middle.vo.RoomVO"%>
<%@page import="kr.or.ddit.middle.vo.ProfessorVO"%>
<%@page import="kr.or.ddit.middle.vo.ClassClassVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/layout/common_lib.jsp" flush="false" />
<!DOCTYPE html>
<html>
<head>
<body>
	<script type="text/javascript">
	function proc1() {
    	location.href="<%=request.getContextPath()%>/ClassList.do";
		}
	</script>
	<style>
#button {
	float: right;
	margin: 5px;
}

.insert {
	height: 20px;
	width: 100px;
}
#selectOption{
	display : block;
	margin: 0 auto;
}
#selecttd{
	padding-right: 25px;
}
</style>
	<%
		List<ClassClassVO> list = (List<ClassClassVO>) request.getAttribute("classList");
		List<ProfessorVO> plist = (List<ProfessorVO>) request.getAttribute("professorList");
		List<RoomVO> rlist = (List<RoomVO>)request.getAttribute("roomList");
		List<TrainingVO> tlist = (List<TrainingVO>)request.getAttribute("trList");
	%>


	<div class="container-content">
		<div class="row">
			<div class="row col-md-12">
				<div class="frame content">
					<div class="stumanage">
						<div class="title stuttl">학급관리</div>
						<div id="list">
							<table class="table" id="Cboard">
								<thead class="table-success">
									<tr>
										<td>학급번호</td>
										<td>강의실</td>
										<td>교수아이디</td>
										<td>훈련과정ID</td>
										<td>시작날짜</td>
										<td>종료날짜</td>
									</tr>
								</thead>
								<%
									if (list == null) {
								%>
								<tr>
									<td colspan="3">학급 목록이 없습니다..</td>
								</tr>
								<%
									} else {

								for (ClassClassVO clist : list) {
								%>
								<tr>
									<td><%=clist.getClass_no()%></td>
									<td><%=clist.getRm_no()%></td>
									<td><%=clist.getPro_id()%></td>
									<td><%=clist.getTr_id()%></td>
									<td><%=clist.getS_date()%></td>
									<td><%=clist.getE_date()%></td>
								</tr>
								<%
									}
								}
								%>
								<form id="ClassForm" method="post" action="<%=request.getContextPath()%>/InsertClass.do">
									<tr><td><input type="text" name="class_no" class="insert"></td>
										<td id="selecttd"><select id="selectOption" name="rm_no" >
							<%
								for(RoomVO rrlist : rlist){
							%>
										<option value="<%=rrlist.getRm_no()%>"><%=rrlist.getRm_no() %></option>								
							<%
									}
							 %>
										</select></td>
										<td id="selecttd"><select id="selectOption" name="pro_id" >
							<%
								for(ProfessorVO pplist : plist){
							%>
											<option value="<%=pplist.getPro_id() %>"><%=pplist.getPro_id() %></option>								
							<%
									}
							 %>
										</select></td>
										<td id="selecttd"><select id="selectOption" name="tr_id" >
										<%
								for(TrainingVO ttlist : tlist){
							%>
											<option value="<%=ttlist.getTr_id()%>"><%=ttlist.getTr_id()%></option>								
							<%
									}
							 %>
							 	</select>
										<td><input type="text" name="s_date" class="insert"></td>
										<td><input type="text" name="e_date" class="insert"></td>
									</tr>
											<input type="submit" value="저장" class="btn btn-primary btn-sm crud" id="button"> 
											<input type="button" value="뒤로" class="btn btn-primary btn-sm crud" id="button" onclick="proc1()">
								</form>

								</tbody>
							</table>
<!-- 							<div id="pagging">페이징</div> -->
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</head>
</html>