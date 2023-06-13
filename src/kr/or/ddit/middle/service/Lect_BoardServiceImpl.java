package kr.or.ddit.middle.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.middle.dao.ILect_BoardDao;
import kr.or.ddit.middle.dao.Lect_BoardDaompl;
import kr.or.ddit.middle.vo.LB_AttachVO;
import kr.or.ddit.middle.vo.LB_CodeVO;
import kr.or.ddit.middle.vo.Lect_BoardVO;
import kr.or.ddit.middle.vo.PageVO;

public class Lect_BoardServiceImpl implements ILect_BoardService{
	
	
	private ILect_BoardDao dao;
	private static ILect_BoardService service;
	
	private Lect_BoardServiceImpl() {
		dao = Lect_BoardDaompl.getInstance();
	}
	
	public static ILect_BoardService getInstance() {
		if(service == null) service = new Lect_BoardServiceImpl();
		return service;
	}
	
	
	@Override
	public List<Lect_BoardVO> getAllLBList() {
		return dao.getAllLBList();
	}
	
	@Override
	public int getLBoardCount(Map<String, Object> map) {
		return dao.getLBoardCount(map);
	}
	
	@Override
	public List<Lect_BoardVO> joinlboard() {
		return dao.joinlboard();
	}
	
	@Override
	public Lect_BoardVO getLBDetail(String lbNo) {
		return dao.getLBDetail(lbNo);
	}
	
	@Override
	public int insertLBoard(Lect_BoardVO lbVO) {
		return dao.insertLBoard(lbVO);
	}
	
	@Override
	public int deleteLBoard(String lbNo) {
		return dao.deleteLBoard(lbNo);
	}
	
	@Override
	public int updateLBoard(Lect_BoardVO lbVO) {
		return dao.updateLBoard(lbVO);
	}

	@Override
	public int updateLBoardRC(String lbNo) {
		return dao.updateLBoardRC(lbNo);
	}

	@Override
	public List<Lect_BoardVO> recentLBoardList() {
		return dao.recentLBoardList();
	}
	
//	**********************************************************
	
	@Override
	public List<LB_CodeVO> getAllCode() {
		return dao.getAllCode();
	}

	@Override
	public LB_CodeVO joinLBCode(String lbNo) {
		return dao.joinLBCode(lbNo);
	}
	
//	**********************************************************
	
	@Override
	public int insertLBfile(LB_AttachVO lbatVO) {
		return dao.insertLBfile(lbatVO);
	}
	
	@Override
	public List<LB_AttachVO> selectAllLBfile() {
		return dao.selectAllLBfile();
	}
	
	@Override
	public LB_AttachVO getlbFileNo(String lfNo) {
		return dao.getlbFileNo(lfNo);
	}
	
	@Override
	public LB_AttachVO getFileLBNo(String lbNo) {
		return dao.getFileLBNo(lbNo);
	}
	
	@Override
	public LB_AttachVO joinLBFile(String lfNo) {
		return dao.joinLBFile(lfNo);
	}
	
	@Override
	public int deleteFile(String lbNo) {
		return dao.deleteFile(lbNo);
	}
	
//	@Override
//	public int updateFile(LB_AttachVO lbatVO) {
//		return dao.updateFile(lbatVO);
//	}
	
//	**********************************************************

	//----------------- 혜연 페이징
	@Override
	public List<Lect_BoardVO> LBListBypage(Map<String, Object> map) {
		return dao.LBListBypage(map);
	}

	@Override
	public int LBtotalCount(Map<String, Object> map) {
		return dao.LBtotalCount(map);
	}

	@Override
	public PageVO pageInfoLB(int page, String stype, String sword) {
		PageVO vo = new PageVO();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("stype", stype);
		map.put("sword", sword);
		
		// 전체 페이지 수 구하기
		int count = this.LBtotalCount(map);
		
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
