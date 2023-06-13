<%@page import="kr.or.ddit.middle.vo.ClassClassVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>Insert title here</title>



<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.3.min.js"></script>
<script type="text/javascript">
function proc1() {
	// 새창열기
   	window.open("<%=request.getContextPath()%>/insertStudent.do",
   				"InsertStudent", "width = 600 height = 900 left = 300 top = 20");
}

$(function() {
	$('.selectClass').on('click', function() {
		// 학급검색을 클릭하면 그 학급의 학생 리스트를 불러옴
		vClass = $('#classFind option:selected').val();
		console.log(vClass);
		location.href="<%= request.getContextPath()%>/managerStudent.do?classNo=" + vClass;
	})
})
</script>
<style type="text/css">
#classFind{
	float: left;
}
</style>
</head>

<%
	List<StudentVO> list = (List<StudentVO>)request.getAttribute("list");
	List<ClassClassVO> classList = (List<ClassClassVO>) request.getAttribute("classList");
	
%>

 <div class="container-content">
        <div class="row">
            <div class="row col-md-12">
                <div class="frame content">
                    <div class="stumanage">
               
	    	 		<div class="title stuttl">학생관리</div>
	    	 	
	    	 		  <div class="btngroup">	    	 		  
	    	 		 <select name="class_no" id="classFind" class="form-select" style="width: 100px; height : 35px; margin-right: 5px;">
	    	 		 <option value="allClass">전체</option>
	    	 		<% for(ClassClassVO clist : classList) {
	  				out.print("<option value='"+ clist.getClass_no()+"'>"+  clist.getClass_no() +"</option>");
	  				} %>
	    	 		 </select>
	    	 		 <input type="button" class="btn btn-primary btn-sm selectClass" value="학급검색" >  
	    	 		    <input type="button" class="btn btn-primary btn-sm counsleBtn" value="학생추가" onclick="proc1()">
	    	 		    </div>
                        <div id="list">
 <table border="1"  class="table" id="Cboard">
 	<thead class="table-success">
        <tr>
        <td>학생ID</td>
        <td>학생PW</td>
        <td>학생이름</td>
        <td>주민번호</td>
        <td>학생번호</td>
        <td>학생메일</td>
        <td>학생주소</td>
        <td>우편번호</td>
        <td>수강여부</td>
        </tr>
	</thead>

<% 
	if(list == null || list.size() == 0){
%>
	<tr>
		<td colspan="9">학생 목록이 없습니다.</td>
	</tr>
<%    
}else{
	
	for(StudentVO slist : list){
		String dropYN = "";
		
		if( slist.getStu_drop().equals("Y")){
			dropYN = "수강포기";
		}else{
			dropYN = "수강중";
		}
		
%> 		
	<tr>
	<td><a href="<%=request.getContextPath()%>/detailStudent.do?stu_id=<%=slist.getStu_id()%>"><%=slist.getStu_id()%></a></td>
	<td><%=slist.getStu_pw()%></td>
	<td><%=slist.getStu_nm()%></td>
	<td><%=slist.getStu_reg().substring(0,7) + "*******" %></td>
	<td><%=slist.getStu_tel() %></td>
	<td><%=slist.getStu_mail() %></td>
	<td><%=slist.getStu_addr() %></td>
	<td><%=slist.getStu_zip() %></td>
	<td><%=dropYN %></td>
	
	
	</tr>
<%
	}
}  	 	
%>
</table>

<!-- 	<div id="pagging">페이징</div> -->
	     </div>
    <br>
	    	 	</div>
    	 	</div>
    	 </div>
    </div>
  </div>
    </html>