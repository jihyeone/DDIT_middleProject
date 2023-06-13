package kr.or.ddit.middle.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.middle.vo.Comm_BoardVO;
import kr.or.ddit.middle.vo.Comm_CodeVO;
import kr.or.ddit.middle.vo.PageVO;

public interface ICBoardService {
	/**
	 * 커뮤니티게시판 리스트 불러오기
	 * @return
	 */
	public List<Comm_BoardVO> getAllCBoardList();
	
	//내가쓴글//
	
		/**
		 * 내가쓴글 리스트 불러오기
		 * @param String
		 * @return list
		 */
		public List<Comm_BoardVO> AllMyBoard(String stu_id);
		
		/**
		 * 내가쓴글 상세보기
		 * @param String
		 * @return list
		 */
		public Comm_BoardVO ViewMyBoard(String comm_no);
		
		/**
		 * 내가쓴글 수정하기
		 * @param vo
		 * @return 성공 : 1, 실패 : 0
		 */
		public int UpdateMyBoard(Comm_BoardVO comm_boardvo);
		
		/**
		 * 내가쓴글 지우기
		 * @param String
		 * @return 성공 : 1, 실패 : 0
		 */
		public int DeleteMyBoard(String comm_no);
		
		/**
		 * 글종류 가져오기
		 * @return
		 */
		public List<Comm_CodeVO> getcomcode();
		
		/**
		 * 내가쓴글 글종류 가져오기
		 * @return
		 */
		public Comm_CodeVO getcomgu(String comm_no);
		
//커뮤니티//	
		/**
		 * 커뮤니티 글 리스트 가져오기
		 * @return
		 */
		public List<Comm_BoardVO> ComBoardList();
		 
		/**
		 * 커뮤니티글 상세보기
		 * @param comm_no
		 * @return
		 */
		public Comm_BoardVO ViewComBoard(String comm_no);
		
		/**
		 * 커뮤니티글 수정하기
		 * @param comm_boardvo
		 * @return
		 */
		public int modifyComBoard(Comm_BoardVO comm_boardvo);
		
		/**
		 * 커뮤니티글 삭제하기
		 * @param comm_boardvo
		 * @return
		 */
		public int deleteComBoard(Comm_BoardVO comm_boardvo);
		
		/**
		 * 조회수 증가
		 * @param comm_no
		 * @return
		 */
		public int updateRc (String comm_no);
		
		/**
		 * 커뮤니티에 글 등록하기
		 * @param comm_boardvo
		 * @return
		 */
		public int insertComboard(Comm_BoardVO comm_boardvo);
		
		//--------------혜연 학생메인화면 최근 글 4개 
		/**
		 * 최근 글 4개 리스트 뽑기
		 * @return
		 */
		public List<Comm_BoardVO> recentCBoardList();
		
		//----------- 페이징
		public List<Comm_BoardVO> CBoardBypage(Map<String, Object> map);
		
		public int totalCount(Map<String, Object> map);
		
		public PageVO pageInfo(int page, String stype, String sword);
}
