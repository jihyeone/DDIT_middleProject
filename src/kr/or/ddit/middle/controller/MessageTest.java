package kr.or.ddit.middle.controller;

import java.util.List;

import kr.or.ddit.middle.service.IMessageService;
import kr.or.ddit.middle.service.MessageServiceImpl;
import kr.or.ddit.middle.vo.MessageVO;

public class MessageTest {
	
	private IMessageService service;
	
	private MessageTest() {
		service = MessageServiceImpl.getInstance();
	}
	
	
	public static void main(String[] args) {
		new MessageTest().start();
	}


	private void start() {
		
		System.out.println("전체 쪽지 리스트 출력 = 지금 1개 있음");
		
		List<MessageVO> list = service.getAllMessage();
		
		System.out.println("번호	보낸사람ID	받는사람ID	제목			일시			열람");
		System.out.println("------------------------------------------------------------------------------------------------");
		for (MessageVO msgList : list) {
			System.out.print(msgList.getMsg_no() + "\t");
			System.out.print(msgList.getSend_id() + "\t\t");
			System.out.print(msgList.getReciv_id() + "\t\t");
			System.out.print(msgList.getMsg_ttl() + "\t");
			System.out.print(msgList.getSend_date() + "\t\t");
			System.out.println(msgList.getReadyn() + "\t\t");
		}
		System.out.println("------------------------------------------------------------------------------------------------");
		
		
		
	}

}
