<%@page import="kr.or.ddit.middle.vo.CounselVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    
<%
	List<CounselVO> clistAll = (List<CounselVO>) request.getAttribute("clistAll");

	
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
	    	 		<div class="title stuttl">상담예약</div>
	    	 		  <div class="btngroup">
	    	 		    <input type="button"  style="display: none;" class="btn btn-primary btn-sm counsleBtn" value="상담예약"
	    	 		    data-bs-toggle="modal" data-bs-target="#cnsModal">
	    	 		    </div>
                        <div id="list">
 <table border="1"  class="table" id="Cboard">
 	<thead class="table-success">
        <tr>
        <td>No</td>
        <td>학생ID</td>
        <td>교수ID</td>
        <td id="cnsttl" style="width: 1000px;">제목</td>
        <td>작성날짜</td>
        <td>답변확인</td>
        </tr>
	</thead>


<%
		if(clistAll == null || clistAll.size() == 0){
%>
	
   		<tr>
   			<td colspan="3">상담 요청 목록이 없습니다.</td>
   		</tr>
        
<%
		} else{
			
			for(CounselVO cnslist : clistAll){
				String check = "";
				
				if(cnslist.getCheckyn().equals("Y")){
					check = "답변완료";
				} else{
					check = "미확인";
				}
%>

        <tr>
        <td><%= cnslist.getCns_no() %></td>
        <td><%= cnslist.getStu_id() %></td>
        <td><%= cnslist.getPro_id() %></td>
        <td><a href="<%= request.getContextPath()%>/counsel/CncDetailpro.do?cnsNo=<%=cnslist.getCns_no()%>">
        	<%= cnslist.getCns_ttl() %></a></td>
        <td><%= cnslist.getReg_date().substring(0,10) %></td>
        <td><%= check %></td>
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
  