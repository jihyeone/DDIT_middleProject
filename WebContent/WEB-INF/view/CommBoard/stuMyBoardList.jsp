<%@page import="kr.or.ddit.middle.vo.Comm_CodeVO"%>
<%@page import="kr.or.ddit.middle.vo.Comm_BoardVO"%>
<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@page import="kr.or.ddit.middle.vo.CounselVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%


	
	List<Comm_BoardVO> list = (List<Comm_BoardVO>) request.getAttribute("AllMyBoard");
	List<Comm_CodeVO> codeList = (List<Comm_CodeVO>)request.getAttribute("codeList");
	StudentVO Loginstu = (StudentVO) session.getAttribute("LoginStudent");
	
%> 


<style>
.form{
	margin: 0 auto;
}
.td{
	padding: 20px;
}
</style>

    <div class="container-content">
        <div class="row">
            <div class="row col-md-12">
                <div class="frame content">
                    <div class="stumanage">
	    	 		<div class="title stuttl">내가 쓴글 목록</div>
	    	 		  <div class="btngroup">
	    	 		    </div>
                        <div id="list">
 <table border="1"  class="table" id="Cboard">
 	<thead class="table-success">
        <tr>
        <td>No</td>
        <td style="width: 800px;">제목</td>
        <td>작성날짜</td>
        <td>조회수</td>
        </tr>
	</thead>

<%
		if(list.size() == 0 || list == null){
%>
	
   		<tr>
   			<td colspan="4">내가 쓴 글이 없습니다.</td>
   		</tr>
        
<%
		}else{
			for(Comm_BoardVO clist : list){
%>

        <tr>
        <td><a href="<%= request.getContextPath()%>/myboard/MyboardDetail.do?cNo=<%=clist.getComm_no()%>">
        	<%= clist.getComm_no() %></a></td>
        <td><a href="<%= request.getContextPath()%>/myboard/MyboardDetail.do?cNo=<%=clist.getComm_no()%>">
        	<%= clist.getComm_ttl() %></a></td>
        <td><%= clist.getComm_date().substring(0,10) %></td>
        <td><%= clist.getComm_rc() %></td>
                                  
       
       
        </tr>
<%
		}
		}
	
%>        
    </table>

<!--       <div id="pagging">페이징</div> -->
       </div>
    <br>
	    	 	</div>
    	 	</div>
    	 </div>
    </div>
  </div>
  
  

  
    