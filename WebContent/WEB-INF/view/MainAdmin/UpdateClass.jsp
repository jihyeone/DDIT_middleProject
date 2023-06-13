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
	$(function () {
		$('#btnList').on('click', function() {
			location.href="<%= request.getContextPath() %>/ClassList.do";
		})
	})
</script>
<style>

.list{
	 margin-bottom : 100px;
}
form{
	margin: 0 auto;
}
</style>
 <%
 	ClassClassVO classVo = (ClassClassVO)request.getAttribute("classvo");
 	String finCheck = "";
	if (classVo.getCl_fin().equals("Y")){
		 finCheck = "수료";
	} else{
		 finCheck = "미수료";
	}
 	
	List<ProfessorVO> plist = (List<ProfessorVO>) request.getAttribute("plist");
	
 %>
    
    
    <div class="container-content">
 <div class="row">
  <div class="row col-md-12">
   <div class="frame content">
    <div class="stumanage">
     <div class="title stuttl">학급관리</div>
      <div class="container rounded bg-white mt-5 mb-5">
       <div class="row infoForm">
        <div class="col-md-4 border-right">
        </div>
          <div class="col-md-8 border-right">
           <div class="p-1 py-1">
            <form class="list" name="classForm" id="classForm" action="<%=request.getContextPath()%>/UpdateClass.do" 
           	 method="post"  id="update">
            <div class="d-flex justify-content-between align-items-center mb-1">
                    <h4 class="text-right">Class Settings</h4>
            </div>
              <div class="row mt-3">
                    <div class="col-md-8"><label class="labels">학급번호</label>
                    <input type="text" class="form-control"  readonly name="class_no" value="<%=classVo.getClass_no()%>"></div>
                   
                    <div class="col-md-8"><label class="labels">강의실</label>
                    <input type="text" class="form-control" name="rm_no" value="<%=classVo.getRm_no()%>" ></div>
                    
                    <div class="col-md-8"><label class="labels">담당교수</label>
                    <select name="pro_id" class="form-control" >
                    	<%
                    		for(ProfessorVO proList : plist){
                    			if(proList.getPro_id().equals(classVo.getPro_id())){
                    				out.print("<option selected value='"+ proList.getPro_id() +"'>"+proList.getPro_nm()+"</option>");
                    			} else{
	                    			out.print("<option value='"+ proList.getPro_id() +"'>"+proList.getPro_nm()+"</option>");
                    			}
                    		}
                    	%>
                    </select></div>
                    
<%--                     <input type="text" class="form-control" name="pro_id" value="<%=classVo.getPro_id() %>" ></div> --%>
                    
                    <div class="col-md-8"><label class="labels">훈련과정</label>
                    <input type="text" class="form-control" name="tr_id" value="<%=classVo.getTr_id() %>" ></div>
                    <div class="col-md-8"><label class="labels">시작날짜</label>
                    <input type="text" class="form-control" name="s_date" value="<%=classVo.getS_date() %>" ></div>
                    <div class="col-md-8"><label class="labels">종료날짜</label>
                    <input type="text" class="form-control" name="e_date" value="<%=classVo.getE_date() %>" ></div>
                    <div class="col-md-8"><label class="labels">수료여부</label>
                  
                    <select name="cl_fin" class="form-control"> 
                    <%
                    if (classVo.getCl_fin().equals("Y")){
               			out.print("<option value='Y' selected>수료</option>");
               			out.print("<option value='N' >미수료</option>");
                    } else{
                    	out.print("<option value='Y'>수료</option>");
               			out.print("<option value='N' selected >미수료</option>");
               		}
                    %>
                    </select>
<%--                     <input type="text" class="form-control" disabled="disabled" placeholder="<%= finCheck %>" ></div> --%>
          		 	<div class="mt-5 text-center">
          		 	<button class="btn btn-primary profile-button" type="submit">저장</button>
          		 	<button class="btn btn-primary profile-button" type="button" id="btnList">목록</button></div>
          </div>
          	</div>
          </form>
          </div>
<!--                 <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="button">Save Profile</button></div> -->
        </div>
       </div>
      </div>
     </div>
    </div>
   </div>
  </div>
 </div>
    
    
    
    
    
    
    
    
    
<!--     <div class="container-content"> -->
<!--         <div class="row"> -->
<!--             <div class="row col-md-12"> -->
<!--                 <div class="frame content"> -->
<!--                     <div class="stumanage"> -->
<!--                         <div class="title stuttl">학급관리</div> -->
<!--                  		<div class="list"> -->
<%--                         <form action="<%=request.getContextPath()%>/UpdateClass.do" --%>
<!-- 		method="post"  id="update"> -->
		
<!-- 		<table> -->
<!-- 			<tbody> -->
<!-- 				<tr> -->
<!-- 					<td>학급번호</td> -->
<%-- 					<td><input type="text" name="class_no" value="<%=classVo.getClass_no()%>"></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>강의실</td> -->
<%-- 					<td><input type="text" name="rm_no" value="<%=classVo.getRm_no()%>"></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>교수아이디</td> -->
<!-- 					<td><input type="text" name="pro_id" -->
<%-- 						value="<%=classVo.getPro_id()%>"></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>훈련과정ID</td> -->
<!-- 					<td><input type="text" name="tr_id" -->
<%-- 						value="<%=classVo.getTr_id()%>"></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>시작날짜</td> -->
<!-- 					<td><input type="text" name="s_date" -->
<%-- 						value="<%=classVo.getS_date()%>"></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>종료날짜</td> -->
<!-- 					<td><input type="text" name="e_date" -->
<%-- 						value="<%=classVo.getE_date()%>"></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td colspan="2" style="text-align: center;"><input -->
<!-- 						type="submit" value="저장"> <input type="reset" value="취소"> -->
<!-- 						<input type="button" id="btnList" value="회원목록"></td> -->
<!-- 				</tr> -->
<!-- 			</tbody> -->
<!-- 		</table> -->
<!-- 	</form> -->
<!--                     <div id="pagging">페이징</div> -->
<!--                     </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--     </div> -->
<!--        </div> -->
<!--        </div> -->
          </head>
          </html>