package kr.or.ddit.middle.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.middle.vo.LB_AttachVO;
import kr.or.ddit.middle.vo.LB_CodeVO;
import kr.or.ddit.middle.vo.Lect_BoardVO;
import kr.or.ddit.middle.vo.PageVO;

public interface ILect_BoardService {
	
	// 글 전체 리스트
		public List<Lect_BoardVO> getAllLBList();
		
		// 글 전체 갯수
		public int getLBoardCount(Map<String, Object> map);
		
		// 조인 - 게시판
		public List<Lect_BoardVO> joinlboard();
		
		// 글 내용 - 조건
		public Lect_BoardVO getLBDetail(String lbNo);
		
		// 글 등록
		public int insertLBoard(Lect_BoardVO lbVO);
		
		// 글 삭제
		public int deleteLBoard(String lbNo);
		
		// 글 수정
		public int updateLBoard(Lect_BoardVO lbVO);
		
		// 조회수 증가
		public int updateLBoardRC(String lbNo);
		
		// 메인화면 = 최신 글 4개
		public List<Lect_BoardVO> recentLBoardList();
		
//		**********************************************************
		
		// 코드 전체 리스트
		public List<LB_CodeVO> getAllCode();
		
		// 코드이름 - 조인
		public LB_CodeVO joinLBCode(String lbNo);
		
//		**********************************************************
		
		// 파일등록
		public int insertLBfile(LB_AttachVO lbatVO);
		
		// 파일 전체 리스트
		public List<LB_AttachVO> selectAllLBfile();
		
		// 파일 리스트 - 조건(lfile_no)에 따라
		public LB_AttachVO getlbFileNo(String lfNo);
		
		// 파일 리스트 - 조건(lboard_no)에 따라
		public LB_AttachVO getFileLBNo(String lbNo);
		
		// 파일이름 - 조인
		public LB_AttachVO joinLBFile(String lfNo);
		
		// 파일삭제
		public int deleteFile(String lbNo);
		
//		// 파일수정
//		public int updateFile(LB_AttachVO lbatVO);
		
//		**********************************************************	
		
		//-----------------------혜연 -------페이징---------------
		
		public List<Lect_BoardVO> LBListBypage(Map<String, Object> map);
		
		public int LBtotalCount(Map<String, Object> map);
		
		// 페이지
		public PageVO pageInfoLB(int page, String stype, String sword);
}
