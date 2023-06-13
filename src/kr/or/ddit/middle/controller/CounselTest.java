package kr.or.ddit.middle.controller;

import java.util.List;

import kr.or.ddit.middle.service.CounselServiceImpl;
import kr.or.ddit.middle.service.ICounselService;
import kr.or.ddit.middle.vo.CounselVO;

public class CounselTest {
	
	
	private ICounselService service;
	private CounselTest() {
		service = CounselServiceImpl.getInstance();
	}
	
	public static void main(String[] args) {
		new CounselTest().start();
	}

	private void start() {
		
		System.out.println("상담 목록");
		System.out.println("---------------------------");
		
		List<CounselVO> list = service.getCounselList();
		
		System.out.println("글번호   학생ID    제목");
		System.out.println("---------------------------");
		for (CounselVO conlist : list) {
			System.out.print(conlist.getCns_no() + "\t");
			System.out.print(conlist.getStu_id() + "\t");
			System.out.println(conlist.getCns_ttl());
		}
		
	}

}
