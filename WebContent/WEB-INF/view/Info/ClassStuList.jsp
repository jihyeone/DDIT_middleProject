<%@page import="com.sun.org.apache.regexp.internal.REUtil"%>
<%@page import="kr.or.ddit.middle.vo.ProfessorVO"%>
<%@page import="kr.or.ddit.middle.vo.ClassClassVO"%>
<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<StudentVO> slist = (List<StudentVO>)request.getAttribute("classStuList");	// 학생 목록
	
	ProfessorVO loginpro = (ProfessorVO)session.getAttribute("LoginProfessor");	// 로그인 중인 교수 아이디
			
%>


    <div class="container-content">
        <div class="row">
            <div class="row col-md-12">
                <div class="frame content">
                    <div class="stumanage">
                        <div class="title stuttl">학생관리</div>
                        <div class="btngroup">
                        </div>
                        <div id="list">
                        <table class="table"  id="Cboard">
                            <thead class="table-success">
                                <tr>
                                    <td>학생번호</td>
                                    <td>학생명</td>
                                    <td>주소</td>
                                    <td>전화번호</td>
                                    <td>우편번호</td>
                                    <td>메일</td>
                                    <td>수강여부</td>
                                </tr>
                            </thead>
	<%
		if(slist != null){
			for(StudentVO stuvo : slist){
				String drop = "";
				if(stuvo.getStu_drop().equals("Y")){
					drop = "수강 포기";
				}else{
					drop = "수강 중";
				}
				
	%>
                                <tr>
                                    <td><%=stuvo.getStu_id()%></td>
                                    <td><a href="<%= request.getContextPath()%>/detailStudent.do?stu_id=<%=stuvo.getStu_id()%>"><%=stuvo.getStu_nm()%></a></td>
                                    <td><%=stuvo.getStu_addr()%></td>
                                    <td><%=stuvo.getStu_zip()%></td>
                                    <td><%=stuvo.getStu_tel()%></td>
                                    <td><%=stuvo.getStu_mail()%></td>
                                    <td><%=stuvo.getStu_drop()%></td>
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