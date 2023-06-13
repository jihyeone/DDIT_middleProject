<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>Insert title here</title>



<script type="text/javascript">

$(function(){
	

	
	
	$('#pwChange').on("click", function(){
		 window.open("<%=request.getContextPath()%>/Info/StdPwChange.do", "_blank","width=600px, height=500px");
	});

	
	
});

</script>
<style type="text/css">
.list{
	 margin-bottom : 100px;
}
form{
	margin: left auto;
}
#profil{
	margin-top: 80px;
}

.form-control:focus {
    box-shadow: none;
    border-color: #BA68C8
}

.profile-button {
    background: rgb(99, 39, 120);
/*     box-shadow: none; */
/*     border: none; */
}

.profile-button:hover {
    background: #682773
}

.profile-button:focus {
    background: #682773;
    box-shadow: none
}

.profile-button:active {
    background: #682773;
    box-shadow: none
}

.back:hover {
    color: #682773;
    cursor: pointer
}

.labels {
    font-size: 13px;
}

.add-experience:hover {
    background: #BA68C8;
    color: #fff;
    cursor: pointer;
    border: solid 1px #BA68C8
   
}
.sbtn {
	float: right;
	margin-top: 5px;
	margin: 3px;
}
</style>
</head>
<body>
<%
	StudentVO stdVo = (StudentVO)request.getAttribute("stdVo");
	
%>






<div class="container-content">
 <div class="row">
  <div class="row col-md-12">
   <div class="frame content">
    <div class="stumanage">
     <div class="title stuttl">내 정보</div>
      <div class="container rounded bg-white mt-5 mb-5">
       <div class="row infoForm">
        <div class="col-md-4 border-right">
         <div class="d-flex flex-column align-items-center text-center p-3 py-5">
         <img src="<%=request.getContextPath()%>/Info/StuImageView.do?stuId=<%=stdVo.getStu_id()%>"  id="profil"width="300" height="300"></div>
         
        </div>
          <div class="col-md-8 border-right">
           <div class="p-1 py-1">
		    <div class="btngroup">
<!-- 			<input id="btnUpdate" type="button" class="btn btn-primary btn-sm " value="수정">  -->
<!-- 			<input type="button" id="btnList" class="btn btn-primary btn-sm " value="목록"> -->
	        </div>	
	        
            <form action="<%=request.getContextPath()%>/Info/stuModify.do" 
				  method="post" enctype="multipart/form-data" id="form">
				  
			<input type="hidden" name="stu_id" id="stu_id" value="<%=stdVo.getStu_id()%>">
			<input type="hidden" name="old_photo" value="<%=stdVo.getStu_poto()%>">	  
				  
            <div class="d-flex justify-content-between align-items-center mb-1">
                    <h4 class="text-right">Profile Settings</h4>
            </div>
              <div class="row mt-3">
                    <div class="col-md-8"><label class="labels">학생ID</label><input type="text" class="form-control" disabled value="<%=stdVo.getStu_id()%>"></div>
                    <div class="col-md-8"><label class="labels">학생 이름</label><input type="text" class="form-control" disabled value="<%=stdVo.getStu_nm()%>"></div>
                    <div class="col-md-8"><label class="labels">주민 번호</label><input type="text" class="form-control" disabled value="<%=stdVo.getStu_reg().substring(0,7) + "*******" %>" ></div>
                    <div class="col-md-8"><label class="labels">비밀 번호</label>
                    <input type="text" class="form-control"  value="<%=stdVo.getStu_pw() %>" id="stu_pw" style="width: 200px; display: inline; margin-bottom: 5px; margin-top: 5px; ">
                    <input type="button" id="pwChange" value="비밀번호 변경" style="text-align: center;"  class="btn btn-primary btn-sm ">
                    </div>
                    <div class="col-md-8"><label class="labels">우편 번호</label>
					<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" style="margin-bottom: 5px;"  class="btn btn-primary btn-sm "><br>
                    <input type="text" id="sample6_postcode" class="form-control" name="stu_zip" style="width: 200px; display: inline; margin-bottom: 5px;" value="<%=stdVo.getStu_zip() %>">
					<input type="text"  class="form-control" id="sample6_address" name="stu_addr" placeholder="주소" value="<%=stdVo.getStu_addr()%>"><br>
                    </div>
                    <div class="col-md-8"><label class="labels">메일</label><input type="text" class="form-control" name="stu_mail" value="<%=stdVo.getStu_mail() %>" ></div>
                    <div class="col-md-8"><label class="labels">전화번호</label><input type="text" class="form-control" name="stu_tel" value="<%=stdVo.getStu_tel() %>" ></div>
                    <div class="col-md-8"><label class="labels">학급</label><input type="text" class="form-control" disabled name="class_no" value="<%=stdVo.getClass_no() %>" ></div>
        			<div class="col-md-8"><label class="labels">프로필사진</label><input type="file" name="stu_photo"><br>
        			<br>
        			<input type="submit" id="send" value="정보수정" class="btn btn-primary btn-sm sbtn" > 
		  			<input type="reset" id="cancel" value="취소" class="btn btn-primary btn-sm sbtn" >
          </div><br>
      </div>
          </form>
        </div>
       </div>
      </div>
     </div>
    </div>
   </div>
  </div>
 </div>
</div>


</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
              
            }
        }).open();
    }
</script>

</html>