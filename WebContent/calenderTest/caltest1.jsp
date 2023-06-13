<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="../js/jquery-3.6.3.min.js"></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.1/index.global.min.js'></script>
<script type="text/javascript" src="../js/ko.global.js"></script>

<script type="text/javascript">

var calendar = null;

$(document).ready(function() {
	 var Calendar = FullCalendar.Calendar;
	    var Draggable = FullCalendar.Draggable;

	    var containerEl = document.getElementById('external-events');
	    var calendarEl = document.getElementById('calendar');
	    var checkbox = document.getElementById('drop-remove');

	    // initialize the external events
	    // -----------------------------------------------------------------

	    new Draggable(containerEl, {
	      itemSelector: '.fc-event',
	      eventData: function(eventEl) {
	        return {
	          title: eventEl.innerText
	        };
	      }
	    });

	    // initialize the calendar
	    // -----------------------------------------------------------------

	    calendar = new Calendar(calendarEl, {
	      headerToolbar: {
	        left: 'prev,next today', // 이전 다음 오늘 버튼
	        center: 'title', // 센터에 타이틀
	        right: 'dayGridMonth,timeGridWeek,timeGridDay' // 상단 월/주/일 버튼
	      },
	   
	      editable: true, // 수정가능 여부 false = 수정 불가능
	      // 캘린더 안으로 드롭이 가능하다
	      droppable: true, // this allows things to be dropped onto the calendar
	      drop: function(info) {
	        // is the "remove after drop" checkbox checked?
	        // 클릭했을때 해당 객체를 제거하라
	        if (checkbox.checked) {
	          // if so, remove the element from the "Draggable Events" list
	          info.draggedEl.parentNode.removeChild(info.draggedEl);
	        }
	      },
	      eventAdd: function () { // 이벤트가 추가되면 발생하는 이벤트
              console.log("이벤트///")
          },
	      select : function(arg) { // 캘린더에서 드래그로 이벤트 생성
	    	var title = prompt('입력할 일정:');
	      	if(title){
	      		$.ajax({
	      			url : '<%= request.getContextPath()%>/calenderDBtest.do',
	      			type : 'post',
	      			data : {
	      				title: title,
						start: arg.startStr,
						end: arg.endStr,
						allDay: arg.allDay
	      			},
	  			    traditional: true,
				    async: false, //동기
				    success : function(res) {
					alert("성공")
				},
				error : function(xhr) {
					alert("오류 : " + xhr.status);
				}
	      		}); // ajax
	      		
	      		calendar.addEvent({
					title: title,
					start: arg.startStr,
					end: arg.endStr,
					allDay: arg.allDay
				})
	      	}
	      	calendar.unselect()// 달력 선택 취소??
			
		},
	      locale : 'ko'
	    });

	    calendar.render();
	    
// 전체 이벤트 데이터 추출해서 ajax에 보낸다.

	$('#savebtn').on('click', function() {
		// calendar.getEvents() 모든 정보 array로 가져옴
		allevent =  calendar.getEvents();
		console.log(allevent);
		// 가져올것..
		// allDay : true = 하루종일 진행되는 이벤트
		// allDay : false = 특정한 날 특정 시간때
		// instance : range - end / start
		
		var  events = new Array(); // 배열생성하고 push로 obj객체 저장
		
		for(var i = 0; i < allevent.length; i ++){
			
			var obj = new Object(); // 객체
			
			obj.title = allevent[i]._def.title; // 이벤트 명칭 일정 제목 띄우기
			obj.allday = allevent[i]._def.allDay; // 하루종일의 이벤트인지 true/false
			obj.start = allevent[i]._instance.range.start; // 시작 날짜 및 시간
			obj.end = allevent[i]._instance.range.end; // 종료날짜 및 시간
			
			events.push(obj); // for문을 돌면서 이벤트들이 배열 형태로 json객체 형태로 저장
		}
		
		// 문자열 형태로전달하기 위해
		var jsondata = JSON.stringify(events);
		console.log(jsondata);
		
		savedata(jsondata);
		
		
	})
	
// 	function savedata(jsondata) {
		
// 	$.ajax({
<%-- 		url : '<%= request.getContextPath()%>/savedatadata.do', --%>
// 		type : 'post',
// 		data : {"alldata" : jsondata },
// 		dataType : 'text',
// 		async : false // 동기식으로 넘기기
		
// 	})
// 	.done(function(res) {
		
// 	}) //성공했을때
// 	.fail(function (request, status, error) {
// 		alert("에러 발생 : " + error);
// 	}) // 실패했을때
// }

	    
});
	  
</script>
<style>

  html, body {
    margin: 0;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #external-events {
    position: fixed;
    z-index: 2;
    top: 20px;
    left: 20px;
    width: 150px;
    padding: 0 10px;
    border: 1px solid #ccc;
    background: #eee;
    border: 1px solid red;
  }

  .demo-topbar + #external-events { /* will get stripped out */
    top: 60px;
    
  }

  #external-events .fc-event {
    cursor: move;
    margin: 3px 0;
  }

  #calendar-container {
    position: relative;
    z-index: 1;
    margin-left: 200px;
  }

  #calendar {
    max-width: 1100px;
    margin: 20px auto;
  }
.fc-event{
	margin: 5px;
	cursor : move;
}
</style>

</head>
<body>



<button id="savebtn">전체저장</button>
  <div id='external-events' style="margin-top: 100px;">
    <p>
      <strong>아래 과목을 드래그하여 배정하세요</strong>
    </p>
	
	<div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'
    	data-event='{ "title": "데이터베이스", "duration": "day : 3" }'
    >
      <div class='fc-event-main'>데이터베이스</div>
    </div>
	
<!--     <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'> -->
<!--       <div class='fc-event-main'>데이터베이스</div> -->
<!--     </div> -->
    <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
      <div class='fc-event-main'>Basic JAVA</div>
    </div>
    <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
      <div class='fc-event-main'>High JAVA</div>
    </div>
    <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
      <div class='fc-event-main'>jQuery</div>
    </div>
    <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
      <div class='fc-event-main'>WEB APP</div>
    </div>
    <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
      <div class='fc-event-main'>Python</div>
    </div>
    <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
      <div class='fc-event-main'>WEB PRO</div>
    </div>
    <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
      <div class='fc-event-main'>PROJECT</div>
    </div>

    <p>
      <input type='checkbox' id='drop-remove' />
      <label for='drop-remove'>드로그앤 드롭</label>
    </p>
  </div>



  <div id='calendar-container'>
  	<div><input type="button" class="add" value="강의 추가하기"></div>
    <div id='calendar'></div>
  </div>


</body>
</html>