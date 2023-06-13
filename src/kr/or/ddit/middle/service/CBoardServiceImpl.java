package kr.or.ddit.middle.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.middle.dao.CBoardDaoImpl;
import kr.or.ddit.middle.dao.ICBoardDao;
import kr.or.ddit.middle.vo.Comm_BoardVO;
import kr.or.ddit.middle.vo.Comm_CodeVO;
import kr.or.ddit.middle.vo.PageVO;

public class CBoardServiceImpl implements ICBoardService {
	
	private ICBoardDao dao;
	
	private static ICBoardService service;
	
	private CBoardServiceImpl() {
		dao = CBoardDaoImpl.getInstance();
	}
	
	public static ICBoardService getInstance() {
		if(service == null) service = new CBoardServiceImpl();
		return service;
	}

	@Override
	public List<Comm_BoardVO> getAllCBoardList() {
		return dao.getAllCBoardList();
	}

	@Override
	public List<Comm_BoardVO> AllMyBoard(String stu_id) {
		// TODO Auto-generated method stub
		return dao.AllMyBoard(stu_id);
	}

	@Override
	public Comm_BoardVO ViewMyBoard(String comm_no) {
		// TODO Auto-generated method stub
		return dao.ViewMyBoard(comm_no);
	}

	@Override
	public int UpdateMyBoard(Comm_BoardVO comm_boardvo) {
		// TODO Auto-generated method stub
		return dao.UpdateMyBoard(comm_boardvo);
	}

	@Override
	public int DeleteMyBoard(String comm_no) {
		// TODO Auto-generated method stub
		return dao.DeleteMyBoard(comm_no);
	}

	@Override
	public List<Comm_CodeVO> getcomcode() {
		// TODO Auto-generated method stub
		return dao.getcomcode();
	}

	@Override
	public Comm_CodeVO getcomgu(String comm_no) {
		// TODO Auto-generated method stub
		return dao.getcomgu(comm_no);
	}

//커뮤니티 게시판
	@Override
	public List<Comm_BoardVO> ComBoardList() {
		// TODO Auto-generated method stub
		return dao.ComBoardList();
	}

	@Override
	public Comm_BoardVO ViewComBoard(String comm_no) {
		// TODO Auto-generated method stub
		return dao.ViewComBoard(comm_no);
	}

	@Override
	public int modifyComBoard(Comm_BoardVO comm_boardvo) {
		// TODO Auto-generated method stub
		return dao.modifyComBoard(comm_boardvo);
	}

	@Override
	public int deleteComBoard(Comm_BoardVO comm_boardvo) {
		// TODO Auto-generated method stub
		return dao.deleteComBoard(comm_boardvo);
	}

	@Override
	public int updateRc(String comm_no) {
		// TODO Auto-generated method stub
		return dao.updateRc(comm_no);
	}

	@Override
	public int insertComboard(Comm_BoardVO comm_boardvo) {
		// TODO Auto-generated method stub
		return dao.insertComboard(comm_boardvo);
	}

	
	//-----------------혜연
	@Override
	public List<Comm_BoardVO> recentCBoardList() {
		return dao.recentCBoardList();
	}
	
	
	
	//----------------------------- 페이징
	@Override
	public List<Comm_BoardVO> CBoardBypage(Map<String, Object> map) {
		return dao.CBoardBypage(map);
	}

	@Override
	public int totalCount(Map<String, Object> map) {
		return dao.totalCount(map);
	}
	
	@Override
	public PageVO pageInfo(int page, String stype, String sword) {
		PageVO vo = new PageVO();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("stype", stype);
		map.put("sword", sword);
		
		// 전체 페이지 수 구하기
		int count = this.totalCount(map);
		
		// 전체 글 갯수 구하기 - totalPage
		int perList = PageVO.getPerList();
		int totalPage = (int) (Math.ceil((double) count / perList));
		
		// 글을 삭제해서 페이지수 변경처리
		// page가 8 마지막 페이지였는데 글 모두 지워져서 8페이지가 사라짐
		// => 페이지 번호를 7 (앞의 번호로) 수정
		if(page > totalPage) page = totalPage;
		
		
		
		// start. end 구하기 - page기준으로
		int start = (page - 1) * perList + 1;
		int end = start + perList - 1;
		
		if(end > count) end = count;
		
		
		// startPage , endPage
		int perPage = PageVO.getPerPage() ;
		int startPage = ((page - 1) / perPage * perPage) + 1 ;
		int endPage = startPage + perPage - 1;
		
		if(endPage > totalPage) endPage = totalPage;
		
		// pageVo 에 저장
		vo.setStart(start);
		vo.setEnd(end);
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		vo.setTotalPage(totalPage);
		
		return vo;
	}

}
