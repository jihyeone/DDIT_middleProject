<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    

<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.6.3.min.js"></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.1/index.global.min.js'></script>
<script type="text/javascript" src="../js/ko.global.js"></script>

<script>
var calendar = null;
var initialLocaleCode = 'ko';
var localeSelectorEl = document.getElementById('locale-selector');

    $(document).ready(function (){

            var calendarEl = document.getElementById('calendar');
            calendar = new FullCalendar.Calendar(calendarEl, {
                initialDate: '2022-02-07',
                initialView: 'timeGridWeek',
                headerToolbar: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
                },
                navLinks: true,
                editable: true,
                selectable: true,
                droppable: true, // this allows things to be dropped onto the calendar

                eventAdd: function () { // 이벤트가 추가되면 발생하는 이벤트
                    console.log()
                },

                // eventChange: function (obj) { // 이벤트가 수정되면 발생하는 이벤트
                //     allEvent = calendar.getEvents();
                //     console.log(allEvent);
                // },
                // eventRemove: function (obj) { // 이벤트가 삭제되면 발생하는 이벤트
                //     console.log(obj);
                // },
                /**
                 * 드래그로 이벤트 추가하기
                 */
                select: function (arg) { // 캘린더에서 드래그로 이벤트를 생성할 수 있다.
                    var title = prompt('일정을 입력해주세요.');
                    if (title) {
                        calendar.addEvent({
                            title: title,
                            start: arg.start,
                            end: arg.end,
                            allDay: arg.allDay,
                        })
                    }
                    var allEvent = calendar.getEvents(); // .getEvents() 함수로 모든 이벤트를 Array 형식으로 가져온다. (FullCalendar 기능 참조)

                    var events = new Array(); // Json 데이터를 받기 위한 배열 선언
                    for (var i = 0; i < allEvent.length; i++) {
                        var obj = new Object();     // Json 을 담기 위해 Object 선언
                        // alert(allEvent[i]._def.title); // 이벤트 명칭 알람
                        obj.title = allEvent[i]._def.title; // 이벤트 명칭  ConsoleLog 로 확인 가능.
                        obj.start = allEvent[i]._instance.range.start; // 시작
                        obj.end = allEvent[i]._instance.range.end; // 끝

                        events.push(obj);
                    }
                    var jsondata = JSON.stringify(events);
                    console.log(jsondata);
                    // saveData(jsondata);

                    $(function saveData(jsondata) {
                        $.ajax({
                            url: "/full-calendar/calendar-admin-update",
                            method: "POST",
                            dataType: "json",
                            data: JSON.stringify(events),
                            contentType: 'application/json',
                        })
                            .done(function (result) {
                                // alert(result);
                            })
                            .fail(function (request, status, error) {
                                 alert("에러 발생" + error);
                            });
                        calendar.unselect()
                    });
                }

                // drop: function (arg) {
                //     // is the "remove after drop" checkbox checked?
                //     if (document.getElementById('drop-remove').checked) {
                //         // if so, remove the element from the "Draggable Events" list
                //         arg.draggedEl.parentNode.removeChild(arg.draggedEl);
                //     }
                // }
            });
            calendar.render();
            
            
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
    	data-event='{ "title": "데이터베이스", "duration": "24:00" }'
    >
      <div class='fc-event-main'>데이터베이스</div>
    </div>
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
  <div>
  <input type="button" class="add" value="강의 추가하기"
  	  	 data-bs-toggle="modal" data-bs-target="#addevent"></div>
    <div id='calendar'></div>
  </div>



  <!-- The Modal -->
<div class="modal" id="addevent">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">강의추가</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <form id="addschedule">
        	<label>과목 : </label>
       			<select id="selectsub">
       				<option value="데이터베이스">데이터베이스</option>
       				<option value="Basic JAVA">Basic JAVA</option>
       				<option value="High JAVA">High JAVA</option>
       				<option value="jQuery">jQuery</option>
       				<option value="WEB APP">WEB APP</option>
       				<option value="Python">Python</option>
       				<option value="WEB PRO">WEB PRO</option>
       				<option value="PROJECT">PROJECT</option>
       			</select><br>
       		<label>시작날짜 :</label><input type="date" name="start" id="sdate"><br>
       		<label>종료날짜 :</label><input type="date" name="end" id="edate"><br>
        	<input type="button" value="제출" class="btn btn-primary btn sendCnsOK">
        	<input type="reset" value="취소" class="btn btn-primary btn cancelCns">
        </form>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">닫기</button>
      </div>

    </div>
  </div>
</div>
  
