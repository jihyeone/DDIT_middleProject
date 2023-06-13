<%@page import="kr.or.ddit.middle.vo.RoomVO"%>
<%@page import="kr.or.ddit.middle.vo.ClassClassVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:include page="/layout/common_lib.jsp" flush="false" />
 <!DOCTYPE html>
    <html>
<script>
function proc1() {
	location.href="<%=request.getContextPath()%>/InsertClass.do";
}	
</script>
<style>
select{
	float: right;
	margin: 5px;
}
</style>
    <head>
	<body>
 <%
 	List<ClassClassVO> list = (List<ClassClassVO>)request.getAttribute("classList");
 %>   
    
    
    <div class="container-content">
        <div class="row">
            <div class="row col-md-12">
                <div class="frame content">
                    <div class="stumanage">
                        <div class="title stuttl">학급관리</div>
                        <div class="btngroup">
                            <input type="button" class="btn btn-primary btn-sm crud insert" value="추가" onclick="proc1()">
                        </div>
                        <div id="list">
                        <table class="table"  id="Cboard">
                            <thead class="table-success">
                                <tr>
                                    <td>학급번호</td>
                                    <td name ="rm_type" id="rm_type" value="강의실" >강의실</td>
                                    <td>교수아이디</td>
                                    <td>훈련과정ID</td>
                                    <td>시작날짜</td>
                                    <td>종료날짜</td>
                                    <td>수료여부</td>
                            </thead>
<%
	if(list ==null ||list.size()==0){
%>                            
   <tr>
   	<td colspan="6">학급 목록이 없습니다..</td>
   </tr>
   <%
	}else{
		
		for(ClassClassVO clist : list){
			String finCheck = "";
			if(clist.getCl_fin().equals("Y")){
				finCheck = "수료";
			} else{
				finCheck = "미수료";
			}
   %>            
                                <tr>
                                    <td><a href="<%=request.getContextPath()%>/StudentList.do?class_no=<%=clist.getClass_no() %>"><%=clist.getClass_no() %></a></td>
                                    <td><%=clist.getRm_no() %></td>
                                    <td><%=clist.getPro_id() %></td>
                                    <td><%=clist.getTr_id() %></td>
                                    <td><%=clist.getS_date() %></td>
                                    <td><%=clist.getE_date() %></td>
                                    <td><%=finCheck %></td>
                                </tr>
<%
		}
	}
%>	
                    </tbody>
                    </table>
<!--                     <div id="pagging">페이징</div> -->
                    </div>
                    </div>
                </div>
    </div>
       </div>
       </div>
       
          </head>
          </html>