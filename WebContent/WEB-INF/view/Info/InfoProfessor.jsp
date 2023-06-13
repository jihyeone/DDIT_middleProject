<%@page import="kr.or.ddit.middle.vo.ProfessorVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<script type="text/javascript">
    
    function proc1() {
    	// 새창열기
    	window.open("<%=request.getContextPath()%>/InsertProfessor.do",
    				"InsertProfessor", "width = 600 height = 900 left = 300 top = 20");
	}
    </script>
<%

	
	List<ProfessorVO> list = (List<ProfessorVO>)request.getAttribute("proList");
%> 
    <div class="container-content">
        <div class="row">
            <div class="row col-md-12">
                <div class="frame content">
                    <div class="stumanage">
	    	 		<div class="title stuttl">교수정보</div>
	    	 		  <div class="btngroup">	    	 		  
	    	 		    <input type="button" class="btn btn-primary btn-sm insertPro" value="교수추가" onclick="proc1()">
	    	 		    </div>
                        <div id="list">
 <table border="1"  class="table" id="Cboard">
 	<thead class="table-success">
        <tr>
        <td>교수ID</td>
        <td>교수PW</td>
        <td>교수이름</td>
        <td>주민등록번호</td>
        <td>교수번호</td>
        <td>교수메일</td>
        <td>우편번호</td>
        <td>주소</td>
        </tr>
	</thead>

<% 
	if(list ==null || list.size() == 0){
%>
	<tr>
		<td colspan="8">교수 목록이 없습니다..</td>
	</tr>
<%    
}else{
	
	for(ProfessorVO flist : list){
%> 		
	<tr>
	<td><a href="<%=request.getContextPath()%>/DetailProfessor.do?pro_id=<%=flist.getPro_id()%>"><%=flist.getPro_id()%></a></td>
	<td><%=flist.getPro_pw()%></td>
	<td><a href="<%=request.getContextPath()%>/DetailProfessor.do?pro_id=<%=flist.getPro_id()%>"><%=flist.getPro_nm()%></a></td>
	<td><%=flist.getPro_reg().substring(0,7) + "*******" %></td>
	<td><%=flist.getPro_tel() %></td>
	<td><%=flist.getPro_mail() %></td>
	<td><%=flist.getPro_zip() %></td>
	<td><%=flist.getPro_addr() %></td>
	
	
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
