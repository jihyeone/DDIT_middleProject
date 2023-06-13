<%@page import="kr.or.ddit.middle.vo.ProfessorVO"%>
<%@page import="kr.or.ddit.middle.vo.ClassClassVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<ClassClassVO> classList = (List<ClassClassVO>)request.getAttribute("classList");	// 학급 목록
	ProfessorVO loginpro = (ProfessorVO)session.getAttribute("LoginProfessor");	// 세션에 저장된 로그인 중인 교수 정보
%>

	
 <div class="container-content">
        <div class="row">
            <div class="row col-md-12">
                <div class="frame content">
                    <div class="stumanage">
                        <div class="title stuttl">학급 관리</div>
                        <div class="btngroup">
                        </div>
                        <div id="list">
                        <table class="table"  id="Cboard">
                            <thead class="table-success">
                                <tr>
                                    <td>학급 번호</td>
                                    <td>교실 번호</td>
                                    <td style="width: 500px;">과정명</td>
                                    <td>시작 날짜</td>
                                    <td>종료 날짜</td>
                                    <td>수료 여부</td>
                                </tr>
                            </thead>
	<%
		if(classList != null){
			for(ClassClassVO clsvo : classList){
				String finCheck = "";
				if(clsvo.getCl_fin().equals("Y")){
					finCheck = "수료";
				} else{
					finCheck = "미수료";
				}
				
	%>
                                <tr>
                                    <td><a href="<%=request.getContextPath() %>/classStuList.do?class_no=<%=clsvo.getClass_no()%>"><%=clsvo.getClass_no()%></a></td>
                                    <td><%=clsvo.getRm_no() %></td>
                                    <td><a href="<%=request.getContextPath() %>/classStuList.do?class_no=<%=clsvo.getClass_no()%>"><%=clsvo.getTr_nm() %></a></td>
                                    <td><%=clsvo.getS_date() %></td>
                                    <td><%=clsvo.getE_date() %></td>
                                    <td><%=finCheck %></td>
                                </tr>
<%
			}
		} else{
%>
				<tr>
					<td colspan = "6" >담당 학급이 없습니다.</td>
				</tr>
<%
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

