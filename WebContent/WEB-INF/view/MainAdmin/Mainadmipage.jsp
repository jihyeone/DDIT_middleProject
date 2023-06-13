<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	List<StudentVO> list = (List<StudentVO>)request.getAttribute("list"); // 학생 목록
    	List<StudentVO> slist = (List<StudentVO>) session.getAttribute("list");
    %>
    
<script>
function selectAll(selectAll)  {
	  const checkboxes = document.getElementsByName("student");
	  
	  checkboxes.forEach((checkbox) => {
	    checkbox.checked = selectAll.checked;
	  })
	}
</script>


    <div class="container-content">
        <div class="row">
            <div class="row col-md-12">
                <div class="frame content">
                    <div class="stumanage">
                        <div class="title stuttl">학생정보</div>
                        <div class="btngroup">
<!--                             <input type="button" class="btn btn-primary btn-sm crud modify" value="수정"> -->
<!--                             <input type="button" class="btn btn-primary btn-sm crud insert" value="추가"> -->
<!--                             <input type="button" class="btn btn-primary btn-sm crud delect" value="삭제"> -->
                        </div>
                        <div id="list">
                        <table class="table"  id="Cboard">
                            <thead class="table-success">
                                <tr>
                                    <td>학생번호</td>
                                    <td>학생명</td>
                                    <td>주민번호</td>
                                    <td>주소</td>
                                    <td>전화번호</td>
                                    <td>메일</td>
                                    <td>학급</td>
                                    <td>수료여부</td>
                                    <td>수강여부</td>
<!--                                     <td><input type='checkbox' name='student' value='selectall' onclick='selectAll(this)'></td> -->
                                </tr>
                            </thead>
	<%
		if(slist != null){
			for(StudentVO stuvo : slist){
				
	%>
                                <tr>
                                    <td><%=stuvo.getStu_id()%></td>
                                    <td><%=stuvo.getStu_nm()%></td>
                                    <td><%=stuvo.getStu_reg().substring(0,7) + "*******"%></td>
                                    <td><%=stuvo.getStu_addr()%></td>
                                    <td><%=stuvo.getStu_tel()%></td>
                                    <td><%=stuvo.getStu_mail()%></td>
                                    <td><%=stuvo.getClass_no()%></td>
                                    <td><%=stuvo.getStu_fin()%></td>
                                    <td><%=stuvo.getStu_drop()%></td>
<!--                                     <td><input type='checkbox' name="student"></td> -->
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